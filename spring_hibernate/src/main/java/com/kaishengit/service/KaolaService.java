package com.kaishengit.service;

import com.kaishengit.dao.KaolaDao;
import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.PageHelp;
import com.kaishengit.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@Transactional(rollbackFor = RuntimeException.class)
public class KaolaService {

    @Autowired
    private KaolaDao kaolaDao;

    public List<Kaola> findAll(Integer page){
        return kaolaDao.findAll(page);
    }

    public void save(Kaola kaola){
        kaolaDao.save(kaola);
    }

    public Kaola findById(Integer id) {
        return kaolaDao.findById(id);
    }

    public void update(Kaola kaola) {
        kaolaDao.save(kaola);
    }

    public void deleById(Integer id) {
        kaolaDao.deleById(id);
    }

    public List<Kaola> findByProductName(String productName) {
        return kaolaDao.findByProductName(productName);
    }

    public PageHelp<Kaola> findByRequest(List<RequestUtil> requestUtils, Integer pageNo) {
       return kaolaDao.findByRequestUtil(requestUtils,pageNo);
    }
}
