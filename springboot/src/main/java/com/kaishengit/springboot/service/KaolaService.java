package com.kaishengit.springboot.service;

import com.kaishengit.springboot.entiey.Kaola;
import com.kaishengit.springboot.mapper.KaolaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KaolaService {

    @Autowired
    private KaolaMapper kaolaMapper;

    public List<Kaola> findAll(Integer page){
        return kaolaMapper.findAll(page * 10);
    }
    public Kaola findById(Integer id){
        return kaolaMapper.findById(id);
    }


    public void saveKaola(Kaola kaola){
        kaolaMapper.save(kaola);
    }

}
