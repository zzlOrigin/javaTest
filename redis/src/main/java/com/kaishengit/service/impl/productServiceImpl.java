package com.kaishengit.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.gson.Gson;
import com.kaishengit.entity.Product;
import com.kaishengit.entity.ProductExample;
import com.kaishengit.mapper.ProductMapper;
import com.kaishengit.service.Exception.ServiceException;
import com.kaishengit.service.ProductService;
import com.kaishengit.service.quartz.ChangeNumQuartz;
import com.qiniu.common.Zone;
import com.qiniu.http.Response;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.util.Auth;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.quartz.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.io.*;
import java.util.List;
import java.util.UUID;

@Service
public class productServiceImpl implements ProductService {

    Logger logger = LoggerFactory.getLogger(productServiceImpl.class);
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private JedisPool jedisPool;
    @Value("${qiniuAK}")
    private String qiniuAK;
    @Value("${qiniuSK}")
    private String qiniuSK;
    @Value("${qiniuBuket}")
    private String qiniuBuket;
    @Autowired
    private JmsTemplate jmsTemplate;
    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;
    /**
     * 新增商品,
     *
     * @param product     商品信息
     * @param inputStream 商品图片
     */
    @Override
    @Transactional(rollbackFor = RuntimeException.class)
    public void addNewProduct(Product product, InputStream inputStream) {
        String key = uploadQiniu(inputStream);
        //保存Product对象
        product.setImage(key);
        productMapper.insert(product);

        try(Jedis jedis = jedisPool.getResource()){
            String json = JSON.toJSONString(product);

            jedis.set("product-"+product.getId(),json);

            for (int i = 0; i < product.getProductNum(); i++){
                jedis.lpush("product-"+product.getId()+"-list",Integer.toString(i));
            }
        }
        addSchedulerJob(product.getEndTime().getTime(),product.getId());
    }

    /**
     * 添加一个定时任务
     * @param endTime
     * @param productId
     */
    private void addSchedulerJob(Long endTime,Integer productId) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.putAsString("productId",productId);

        JobDetail jobDetail = JobBuilder
                .newJob(ChangeNumQuartz.class)
                .setJobData(jobDataMap)
                .withIdentity(new JobKey("taskID:"+productId,"productInventoryGroup"))
                .build();

        DateTime dateTime = new DateTime(endTime);

        StringBuilder cron = new StringBuilder("0")
                .append(" ")
                .append(dateTime.getMinuteOfHour())
                .append(" ")
                .append(dateTime.getHourOfDay())
                .append(" ")
                .append(dateTime.getDayOfMonth())
                .append(" ")
                .append(dateTime.getMonthOfYear())
                .append(" ? ")
                .append(dateTime.getYear());

        logger.info("CRON EX: {}" ,cron.toString());

        ScheduleBuilder scheduleBuilder =
                CronScheduleBuilder.cronSchedule(cron.toString());
        Trigger trigger = TriggerBuilder.newTrigger()
                .withSchedule(scheduleBuilder)
                .build();

        Scheduler scheduler = schedulerFactoryBean.getScheduler();
        try {
            scheduler.scheduleJob(jobDetail, trigger);
            scheduler.start();
        } catch (Exception ex) {
            throw new ServiceException("添加定时任务异常",ex);
        }
    }
    /**
     * 查询所有商品
     *
     * @return
     */
    @Override
    public List<Product> findAll() {
        ProductExample productExample = new ProductExample();
        productExample.setOrderByClause("start_time asc");
        return productMapper.selectByExample(productExample);
    }

    /**
     * 根据ID查找用户
     *
     * @param id
     * @return
     */
    @Override
    public Product findById(Integer id) {
        try( Jedis jedis = jedisPool.getResource()){
            String json = jedis.get("product-"+id);
            Product product = JSON.parseObject(json,Product.class);
            if (product == null){
               product = productMapper.selectByPrimaryKey(id);
            }
            return product;
        }
    }

    /**
     * 秒杀商品
     * @param id
     */
    @Override
    public void killProduct(Integer id) throws ServiceException {
        try(Jedis jedis = jedisPool.getResource()) {
            Product product = JSON.parseObject(jedis.get("product-"+id),Product.class);
            if (!product.isstart()){
                throw new ServiceException("你来早了,秒杀还没开始");
            }
            String value = jedis.lpop("product-"+id+"-list");
            if (value == null){
                logger.info("库存不足,秒杀失败");
                throw new ServiceException("抢光了");
            }else{
                logger.info("秒杀成功");
            }

            product.setProductNum(product.getProductNum() -1);
            jedis.set("product-"+product.getId(),JSON.toJSONString(product));
        }
    }

    private String uploadLocalhost(InputStream inputStream){

        String name = UUID.randomUUID().toString()+".jpg";
        try {
            OutputStream outputStream = new FileOutputStream(new File("d:/test", name));
            IOUtils.copy(inputStream, outputStream);
        }catch (IOException e){
            throw new RuntimeException("上传到本地出错",e);
        }
        return name;
    }

    private String uploadQiniu(InputStream inputStream) throws RuntimeException {
        Configuration configuration = new Configuration(Zone.zone1());
        UploadManager uploadManager = new UploadManager(configuration);

        Auth auth = Auth.create(qiniuAK, qiniuSK);
        String uploadToken = auth.uploadToken(qiniuBuket);
        try {
            Response response = uploadManager.put(IOUtils.toByteArray(inputStream), null, uploadToken);
            DefaultPutRet defaultPutRet = new Gson().fromJson(response.bodyString(), DefaultPutRet.class);
            return defaultPutRet.key;
        } catch (IOException e) {
            throw new RuntimeException("上传文件到七牛异常",e);
        }
    }
}
