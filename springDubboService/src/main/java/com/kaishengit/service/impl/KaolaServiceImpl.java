package com.kaishengit.service.impl;

import com.kaishengit.entity.Kaola;
import com.kaishengit.entity.KaolaExample;
import com.kaishengit.mapper.KaolaMapper;
import com.kaishengit.service.KaolaService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class KaolaServiceImpl implements KaolaService {

    @Autowired
    private KaolaMapper kaolaMapper;

    @Override
    public List<Kaola> findAll(Integer page) {
        int start = page * 10;
        return kaolaMapper.findAll(start);
    }

    @Override
    public Kaola findById(Integer id) {
        return kaolaMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Kaola kaola) {
        kaolaMapper.insert(kaola);
    }

    @Override
    public void edit(Kaola kaola) {
        kaolaMapper.updateByPrimaryKeySelective(kaola);
    }

    @Override
    public void deleById(Integer id) {
        kaolaMapper.deleteByPrimaryKey(id);
    }
}
