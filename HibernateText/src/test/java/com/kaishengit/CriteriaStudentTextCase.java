package com.kaishengit;

import com.kaishengit.pojo.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class CriteriaStudentTextCase {

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

    public void showList(List<Student> students){
        for (Student student:students){
            System.out.println(student);
        }
    }
    @Test
    public void findAll(){
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> students = criteria.list();
        showList(students);
    }
    @Test
    public void where(){
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.eq("stuName","赵琪"));
        Student student = (Student) criteria.uniqueResult();
        System.out.println(student);
    }

    @Test
    public void like(){
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.like("stuName","王", MatchMode.ANYWHERE));
        List<Student> students = criteria.list();
        showList(students);
    }
    @Test
    public void and(){
        Criteria criteria = session.createCriteria(Student.class);
        criteria.add(Restrictions.like("stuName","王",MatchMode.ANYWHERE));
        criteria.add(Restrictions.eq("stuAge",20));
        List<Student> students = criteria.list();
        showList(students);
    }
    @Test
    public void or(){
        Criteria criteria = session.createCriteria(Student.class);

        List<Student> students = criteria.add(Restrictions.or(Restrictions.like("stuName","王",MatchMode.ANYWHERE),Restrictions.eq("stuAge",12))).list();
        showList(students);
    }
    @Test
    public void order(){
        Criteria criteria = session.createCriteria(Student.class);
        criteria.addOrder(Order.desc("id"));
        List<Student> students = criteria.list();
        showList(students);
    }
    @Test
    public void page(){
        Criteria criteria = session.createCriteria(Student.class);
        criteria.setFirstResult(1);
        criteria.setMaxResults(2);
        showList(criteria.list());
    }
    @Test
    public void count(){
        Criteria criteria = session.createCriteria(Student.class);
        //criteria.setProjection(Projections.rowCount());
        //criteria.setProjection(Projections.count("stuAge"));
        ProjectionList projectionList = Projections.projectionList();
        projectionList.add(Projections.count("stuAge"));
        projectionList.add(Projections.rowCount());
        criteria.setProjection(projectionList);
        Object[] objects= (Object[]) criteria.uniqueResult();
        System.out.println(objects[0]);
        System.out.println(objects[1]);
    }
}
