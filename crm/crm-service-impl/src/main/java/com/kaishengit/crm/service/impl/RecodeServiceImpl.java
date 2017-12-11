package com.kaishengit.crm.service.impl;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.Example.ChanceExample;
import com.kaishengit.crm.Example.RecordExample;
import com.kaishengit.crm.entity.Chance;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.Record;
import com.kaishengit.crm.entity.Staff;
import com.kaishengit.crm.mapper.ChanceMapper;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.mapper.RecordMapper;
import com.kaishengit.crm.service.RecodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class RecodeServiceImpl implements RecodeService {

    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private ChanceMapper chanceMapper;

    @Autowired
    private RecordMapper recordMapper;

    @Value("#{'${sales.progress}'.split(',')}")
    private List<String> progressList;

    @Override
    public List<String> findAllSalesProgress() {
        return progressList;
    }

    @Override
    public void addRecode(Integer saleId, String content) {
        //添加跟进记录
        Record record = new Record();
        record.setContext(content);
        record.setChanceId(saleId);
        record.setSetup(new Date());
        recordMapper.insertSelective(record);
        //更改该机会的最后跟进时间
        Chance chance = chanceMapper.selectByPrimaryKey(saleId);
        chance.setFollow(new Date());
        chanceMapper.updateByPrimaryKeySelective(chance);
        //更改客户的跟踪时间
        Customer customer = customerMapper.selectByPrimaryKey(chance.getCustId());
        customer.setFollowTime(new Date());
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void updateProgress(Integer id, String progress) {
        //更改机会表中的状态
        Chance chance = chanceMapper.selectByPrimaryKey(id);
        chance.setProgress(progress);
        chance.setFollow(new Date());
        chanceMapper.updateByPrimaryKeySelective(chance);
        //添加到跟踪记录表中一条记录
        Record record = new Record();
        record.setContext("将当前进度修改为 ["+progress+"]");
        record.setChanceId(id);
        record.setSetup(new Date());
        recordMapper.insertSelective(record);
        //更改客户最后的跟踪时间
        Customer customer = customerMapper.selectByPrimaryKey(chance.getCustId());
        customer.setFollowTime(new Date());
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void deleById(Integer id) {
        //删除机会表
        Chance chance = chanceMapper.selectByPrimaryKey(id);
        int sid = chance.getStaffId();
        chanceMapper.deleteByPrimaryKey(chance.getId());
        //删除所有的跟进记录
        RecordExample recordExample = new RecordExample();
        recordExample.createCriteria().andChanceIdEqualTo(id);
        recordMapper.deleteByExample(recordExample);
        //更改客户表中的最后跟进时间
        Customer customer = customerMapper.selectByPrimaryKey(sid);
        customer.setFollowTime(new Date());
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public List<Chance> findAllChance(Integer id) {
        ChanceExample chanceExample = new ChanceExample();
        chanceExample.createCriteria().andCustIdEqualTo(id);
        return chanceMapper.selectByExample(chanceExample);
    }

    @Override
    public List<Map<String, Object>> findStaticNumber() {
        return recordMapper.findStaticNum();
    }


    @Override
    public PageInfo<Chance> findAllByStaffId(Integer id,Integer pageNo) {
        PageHelper.startPage(pageNo,15);
        List<Chance> chances = chanceMapper.findAllChange(id);
        return new PageInfo<>(chances);
    }

    @Override
    public Chance findById(Integer id) {
        return chanceMapper.selectByPrimaryKey(id);
    }

    /*
    *
    * 查询所有的跟进记录,
    * 传入机会表ID
    * */
    @Override
    public List<Record> findSalesChanceRecodeListBySalesId(Integer id) {
        RecordExample recordExample = new RecordExample();
        recordExample.createCriteria().andChanceIdEqualTo(id);
        return recordMapper.selectByExampleWithBLOBs(recordExample);
    }

    @Override
    public void addChance(Chance chance) {
        //添加机会,到机会表中
        chance.setCustId(chance.getId());
        chance.setFollow(new Date());
        chance.setId(null);
        chanceMapper.insert(chance);
        //设置客户的最后跟进时间
        Customer customer = customerMapper.selectByPrimaryKey(chance.getCustId());
        customer.setFollowTime(new Date());
        customerMapper.updateByPrimaryKeySelective(customer);
        //在跟踪记录表中添加记录
        Record record = new Record();
        record.setSetup(new Date());
        if (chance.getContent().isEmpty()){
            record.setContext("暂无跟进记录");
        }else{
            record.setContext(chance.getContent());
        }
        record.setChanceId(chance.getId());
        recordMapper.insert(record);

    }



}
