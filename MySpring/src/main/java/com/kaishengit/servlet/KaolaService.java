package com.kaishengit.servlet;

import com.github.pagehelper.PageInfo;
import com.kaishengit.entity.Kaola;

import java.util.List;
import java.util.Map;

public interface KaolaService {

    Kaola findById(Integer id);

    PageInfo<Kaola> findByPageNo(Integer pageNo);

    PageInfo<Kaola> findWithType(Integer pageNo);

    void save(Kaola kaola);

    void editKaola(Kaola kaola);

    void deleteByid(Integer id);

    PageInfo<Kaola> findByQueryWithType(Integer pageNo, String productName);

    PageInfo<Kaola> findByAllQueryWithType(Map<String, Object> map);
}
