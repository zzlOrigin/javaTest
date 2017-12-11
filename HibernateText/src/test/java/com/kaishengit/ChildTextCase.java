package com.kaishengit;

import com.kaishengit.pojo.Child;
import org.hibernate.LockOptions;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ChildTextCase {

    private Session session;
    @Before
    public void before(){
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }
    @Test
    public void child(){
        Child child = (Child) session.get(Child.class,1);

        session.getTransaction().commit();
        System.out.println(child);
        Session session1 = HibernateUtil.getSession();
        session1.beginTransaction();
        Child child1 = (Child) session1.get(Child.class,1);
        System.out.println(child1);
        session1.getTransaction().commit();
    }

    @Test
    public void lock() throws InterruptedException {
        Child child = (Child) session.get(Child.class,1,LockOptions.UPGRADE);
        System.out.println(child);
        child.setName("网吧");
        System.out.println(child);


        Thread thread = new Thread(new Runnable() {
            public void run() {
                Session session1 = HibernateUtil.getSession();
                session1.beginTransaction();

                Child child1 = (Child) session1.get(Child.class,1);
                child1.setName("焦作3");
                session1.getTransaction().commit();

                System.out.println("线程2结束");
            }
        });
        thread.start();

        Thread.sleep(10000);
        System.out.println("主线程结束");

        session.getTransaction().commit();
    }
}
