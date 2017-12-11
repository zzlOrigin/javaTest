package com.kaishengit.servlet.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaExample;
import com.kaishengit.mapper.KaolaMapper;
import com.kaishengit.servlet.KaolaService;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class kaolaServiceImpl implements KaolaService{

    @Autowired
    private KaolaMapper kaolaMapper;
    @Override
    public Kaola findById(Integer id) {
      return kaolaMapper.selectByPrimaryKey(id);
    }

    @Override
    public PageInfo<Kaola> findByPageNo(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        KaolaExample kaolaExample = new KaolaExample();
        kaolaExample.setOrderByClause("id desc");

        List<Kaola> kaolas = kaolaMapper.selectByExample(kaolaExample);
        return new PageInfo<Kaola>(kaolas);
    }

    @Override
    public PageInfo<Kaola> findWithType(Integer pageNo) {
        PageHelper.startPage(pageNo,10);
        List<Kaola> kaolas = kaolaMapper.selectWithType();
        return new PageInfo<>(kaolas);
    }


    @Override
    public void save(Kaola kaola) {
        kaola.setCommentNum(0);
        kaolaMapper.insertSelective(kaola);
    }

    @Override
    public void editKaola(Kaola kaola) {
        kaolaMapper.updateByPrimaryKeySelective(kaola);
    }

    @Override
    public void deleteByid(Integer id) {
        kaolaMapper.deleteByPrimaryKey(id);
    }

    @Override
    public PageInfo<Kaola> findByQueryWithType(Integer pageNo, String productName) {
        PageHelper.startPage(pageNo,10);
        List<Kaola> kaolas = kaolaMapper.findByQueryWithType(productName);
        return new PageInfo<>(kaolas);

    }

    @Override
    public PageInfo<Kaola> findByAllQueryWithType(Map<String, Object> map) {
        PageHelper.startPage((Integer) map.get("pageNo"),10);
        List<Kaola> kaolas = kaolaMapper.findByAllQueryWithType(map);
        return new PageInfo<>(kaolas);
    }
}
