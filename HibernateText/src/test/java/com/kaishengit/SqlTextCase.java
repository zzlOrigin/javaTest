package com.kaishengit;

import com.kaishengit.pojo.Student;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class SqlTextCase {

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
    public void findAll(){
        String sql = "select * from t_student";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Student.class);
        List<Student> students = sqlQuery.list();
        for (Student student:students){
            System.out.println(student);
        }
    }
    @Test
    public void where(){
        String sql = "select * from t_student where id = ?";
        SQLQuery sqlQuery = session.createSQLQuery(sql).addEntity(Student.class);
        sqlQuery.setParameter(0,3);
        System.out.println(sqlQuery.uniqueResult());
    }
}
