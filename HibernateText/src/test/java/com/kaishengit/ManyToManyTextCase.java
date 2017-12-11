package com.kaishengit;

import com.kaishengit.pojo.Produce;
import com.kaishengit.pojo.Shop;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Set;

public class ManyToManyTextCase {


    private Session session;
    @Before
    public void before(){
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }
    @After
    public void after(){
        session.getTransaction().commit();
    }
    @Test
    public void save(){

        Produce produce = (Produce) session.get(Produce.class,1);
        Shop shop = (Shop) session.get(Shop.class,1);
        Set<Produce> produces = shop.getProduces();
        produces.add(produce);


        shop.setProduces(produces);

    }
    @Test
    public void findProduce(){
        Produce produce = (Produce) session.get(Produce.class,1);
        System.out.println(produce.getName());

        Set<Shop> shops = produce.getShops();
        for (Shop shop:shops){
            System.out.println(shop.getId()+"--->"+shop.getName());
        }
    }
}
