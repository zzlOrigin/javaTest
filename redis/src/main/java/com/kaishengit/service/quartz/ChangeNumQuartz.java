package com.kaishengit.service.quartz;

import com.kaishengit.entity.Product;
import com.kaishengit.mapper.ProductMapper;
import org.quartz.*;
import org.springframework.context.ApplicationContext;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ChangeNumQuartz implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap jobDataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String productId = jobDataMap.getString("productId");

        try {
            ApplicationContext ctx = (ApplicationContext) jobExecutionContext.getScheduler().getContext().get("springApplicationContext");
            //Mapper
            ProductMapper productMapper = (ProductMapper) ctx.getBean("productMapper");
            //jedisPool
            JedisPool jedisPool = (JedisPool) ctx.getBean("jedisPool");
            Jedis jedis = jedisPool.getResource();
            Long size = jedis.llen("product"+productId+"-list");
            Product product = productMapper.selectByPrimaryKey(Integer.valueOf(productId));
            product.setProductNum(size.intValue());
            productMapper.updateByPrimaryKey(product);


        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
