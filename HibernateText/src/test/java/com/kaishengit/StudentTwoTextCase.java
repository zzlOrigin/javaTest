package com.kaishengit;

import com.kaishengit.pojo.Student;
import org.hibernate.Query;
import org.hibernate.Session;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class StudentTwoTextCase {

    private Session session;
    @Before
    public void setSession(){
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }
    /*@After
    public void closeSession(){
        session.getTransaction().commit();
    }*/
    @Test
    public void load(){
        Student student = (Student) session.load(Student.class,2);
        System.out.println(student);
    }
    @Test
    public void saveOrUpdate(){
        Student student = new Student();
        student.setStuName("王五");
        student.setStuAge(15);
        session.saveOrUpdate(student);
        System.out.println(student.getId());
        student.setStuAge(20);
        session.saveOrUpdate(student);
    }

    @Test
    public void persist(){
        Student student = new Student();
        student.setStuName("钱七");
        student.setStuAge(21);
        session.persist(student);
        System.out.println(student.getId());
    }
    @Test
    public void merge(){
        Student student = (Student) session.load(Student.class,3);
        System.out.println(student);
        session.getTransaction().commit();

        Session session2 = HibernateUtil.getSession();
        session2.beginTransaction();

        student.setStuAge(46);
        session2.merge(student);

        session2.getTransaction().commit();
    }
    @Test
    public void flush(){
        Student student = (Student) session.get(Student.class,2);
        System.out.println(student);
        student.setStuName("赵琪二逼");
        session.getTransaction().commit();
    }
    @Test
    public void Hql(){
        String hql = "from Student where id = ?";
        Query query = session.createQuery(hql);
        query.setParameter(0,3);
        Student student = (Student) query.uniqueResult();
        System.out.println(student);
        session.getTransaction().commit();
    }
    @Test
    public void byName(){
        String hql = "from Student where stu_name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name","赵琪");
        Student student = (Student) query.uniqueResult();
        System.out.println(student);
        session.getTransaction().commit();
    }

    @Test
    public void findSingleNameColumn(){
        String hql = "select stuName from Student";
        Query query = session.createQuery(hql);
        List<String> strings = query.list();
        for (String str:strings){
            System.out.println(str);
        }
        session.getTransaction().commit();
    }
    @Test
    public void findNameAndAge(){
        String hql = "select stuName,stuAge from Student";
        Query query = session.createQuery(hql);
        List<Object[]> objects = query.list();
        for (Object[] objects1:objects){
            System.out.println(objects1[0]+"---->"+objects1[1]);
        }
        session.getTransaction().commit();
    }
    @Test
    public void Limit(){
        String hql = "from Student order by id desc";
        Query query = session.createQuery(hql);
        query.setFirstResult(0);
        query.setMaxResults(3);
        List<Student> students = query.list();
        for (Student student:students){
            System.out.println(student);
        }
        session.getTransaction().commit();
    }
}
