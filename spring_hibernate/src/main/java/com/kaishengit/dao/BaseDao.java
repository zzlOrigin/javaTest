package com.kaishengit.dao;

import com.kaishengit.pojo.Kaola;
import com.kaishengit.util.RequestUtil;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseDao<T,PK extends Serializable> {

    private Class clazz;

    @Autowired
    private SessionFactory sessionFactory;

    public BaseDao(){
        ParameterizedType parameterizedType = (ParameterizedType) getClass().getGenericSuperclass();
        clazz = (Class) parameterizedType.getActualTypeArguments()[0];
    }

    protected Session getSession(){
        return sessionFactory.getCurrentSession();
    }

    public T findById(PK id){
       return (T) getSession().get(clazz,id);
    }

    public void save(T t){
        getSession().saveOrUpdate(t);
    }
    public void dele(T t){
        getSession().delete(t);
    }
    public void deleById(PK id){
        dele(findById(id));
    }

    public List<T> findAll(Integer page){
        Criteria criteria = getSession().createCriteria(clazz);
        criteria.addOrder(Order.desc("id"));
        criteria.setFirstResult(page*100);
        criteria.setMaxResults(100);
        return criteria.list();
    }



}
