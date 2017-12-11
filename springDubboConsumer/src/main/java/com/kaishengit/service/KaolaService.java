package com.kaishengit.service;

import com.kaishengit.entity.Kaola;
import org.springframework.stereotype.Service;

import java.util.List;


public interface KaolaService {

    List<Kaola> findAll(Integer page);

    Kaola findById(Integer id);

    void save(Kaola kaola);

    void edit(Kaola kaola);

    void deleById(Integer id);
}
