package com.kaishengit;

import com.kaishengit.pojo.Room;
import com.kaishengit.pojo.Student;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

public class ManyTextCase {

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
        Criteria criteria = session.createCriteria(Student.class);
        List<Student> students = criteria.list();
        for (Student student:students){
            System.out.println(student);
            Room room = student.getRoom();
            System.out.println(room.getRoom());
        }

    }
    @Test
    public void setRoom(){
        Criteria criteria = session.createCriteria(Room.class);
        List<Room> rooms = criteria.list();
        for (Room room:rooms){
            System.out.println(room);
            Set<Student> students = room.getStudents();
            for (Student student:students){
                System.out.println(student);
            }
        }
    }
}
