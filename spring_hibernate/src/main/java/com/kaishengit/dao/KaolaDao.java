package com.kaishengit.dao;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.PageHelp;
import com.kaishengit.util.RequestUtil;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

@Repository
public class KaolaDao extends BaseDao<Kaola,Integer>{

    public List<Kaola> findByProductName(String productName){
        Criteria criteria = getSession().createCriteria(Kaola.class);
        criteria.add(Restrictions.like("productName",productName, MatchMode.ANYWHERE));
        return criteria.list();
    }

    public PageHelp<Kaola> findByRequestUtil(List<RequestUtil> requestUtils,Integer pageNo){
        //price_or_marketPrice
        Criteria criteria = getSession().createCriteria(Kaola.class);
        doCriteria(requestUtils,criteria);
        Long count = count(requestUtils);
        PageHelp<Kaola> pageHelp = new PageHelp<>(pageNo,count.intValue());
        criteria.setFirstResult(pageHelp.getStart());
        criteria.setMaxResults(pageHelp.getSize());
        criteria.addOrder(Order.desc("id"));

        List<Kaola> kaolas =  criteria.list();
        pageHelp.setItems(kaolas);
        return pageHelp;
    }

    private Long count(List<RequestUtil> requestUtils){
         Criteria criteria = getSession().createCriteria(Kaola.class);
         doCriteria(requestUtils,criteria);
         criteria.setProjection(Projections.rowCount());
         return (Long) criteria.uniqueResult();
    }
    ////price_or_marketPrice
    private void doCriteria(List<RequestUtil> requestUtils,Criteria criteria){
        for (RequestUtil requestUtil:requestUtils){
            String productName = requestUtil.getRequestName();
            String method = requestUtil.getMethod();
            Object value = requestUtil.getValue();
            criteria.add(doProductName(productName,method,value));
        }
    }
    //price_or_marketPrice
    private Criterion doProductName(String productName,String method,Object value){
        if (productName.contains("_or_")) {
            String[] names = productName.split("_or_");
            Disjunction disjunction = Restrictions.disjunction();
            for(String name:names){
                disjunction.add(method(name,method,value));
            }
            return disjunction;
        }else {
             return method(productName,method,value);
        }
    }

    public  Criterion method(String productName, String method, Object value){
        if ("like".equals(method)){
            return Restrictions.like(productName,value.toString(),MatchMode.ANYWHERE);
        }else if ("eq".equals(method)){
            return Restrictions.eq(productName,value);
        }else if ("gt".equals(method)){
            return Restrictions.ge(productName,value);
        }
        return null;
    }
}
