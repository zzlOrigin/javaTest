package com.kaishengit;

import com.kaishengit.pojo.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.Test;

import java.util.List;

public class StudentTextCase {

    @Test
    public void save(){
        //读取Hibernate的配置文件
        Configuration configuration = new Configuration().configure();
        //创建SessionFactory
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();
        SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        //创建Session
        Session session = sessionFactory.getCurrentSession();
        //创建事务
        Transaction transaction = session.beginTransaction();
        Student student = new Student();
        student.setStuName("张三");
        student.setStuAge(12);
        session.save(student);
        //结束事务
        transaction.commit();
        //释放资源

    }
    @Test
    public void dele(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class,1);
        session.delete(student);
        session.getTransaction().commit();
    }
    @Test
    public void update(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        Student student = (Student) session.get(Student.class,2);
        student.setStuName("李四");
        session.getTransaction().commit();
    }
    @Test
    public void findAll(){
        Session session = HibernateUtil.getSession();
        session.beginTransaction();
        //HQL
        String hql = "from Student";
        Query query = session.createQuery(hql);
        List<Student> students = query.list();
        for (Student student:students){
            System.out.println(student);
        }
        session.getTransaction().commit();
    }



}
