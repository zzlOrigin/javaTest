package com.kaishengit.springboot.mapper;

import com.kaishengit.springboot.entiey.Kaola;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface KaolaMapper {

    void save(Kaola kaola);

    List<Kaola> findAll(Integer page);

    Kaola findById(Integer id);

    void deleById(Integer id);

    void editKaola(Kaola kaola);
}
