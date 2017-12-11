package com.kaishengit;

import com.kaishengit.pojo.Customer;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.Test;

public class LockTextCase {

    @Test
    public void save(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Customer customer = new Customer();
        customer.setName("");

        session.save(customer);
        session.getTransaction().commit();
    }

    @Test
    public void lockHappy() throws InterruptedException {
        Session session = HibernateUtil.getSession();
        session.beginTransaction();

        Customer customer = (Customer) session.get(Customer.class,"402881ae6007036e0160070370fa0000", LockOptions.UPGRADE);
        customer.setName("奥特曼");
        session.saveOrUpdate(customer);

        Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();
                Customer customer1 = (Customer) session1.get(Customer.class,"402881ae6007036e0160070370fa0000");

                customer1.setName("网易");

                session1.getTransaction().commit(); }
        });
        thread.start();

        Thread.sleep(10000);

        session.getTransaction().commit();

    }

    public void update(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
    }
}
