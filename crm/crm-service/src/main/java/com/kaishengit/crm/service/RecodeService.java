package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Chance;
import com.kaishengit.crm.entity.Record;

import java.util.List;
import java.util.Map;

public interface RecodeService {

    PageInfo<Chance> findAllByStaffId(Integer id, Integer pageNo);

    Chance findById(Integer id);

    List<Record> findSalesChanceRecodeListBySalesId(Integer id);

    void addChance(Chance chance);

    List<String> findAllSalesProgress();

    void addRecode(Integer saleId, String content);

    void updateProgress(Integer id, String progress);

    void deleById(Integer id);

    List<Chance> findAllChance(Integer id);

    List<Map<String,Object>> findStaticNumber();


}
