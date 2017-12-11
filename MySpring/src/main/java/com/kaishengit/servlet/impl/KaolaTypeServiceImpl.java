package com.kaishengit.servlet.impl;

import com.kaishengit.entity.kaolaType;
import com.kaishengit.servlet.KaolaTypeService;
import com.kaishengit.mapper.kaolaTypeMapper;
import com.kaishengit.entity.kaolaTypeExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaolaTypeServiceImpl implements KaolaTypeService {

    @Autowired
    private kaolaTypeMapper kaolaTypeMapper;
    @Override
    public List<kaolaType> findAll() {

        return kaolaTypeMapper.selectByExample(new kaolaTypeExample());
    }
}
