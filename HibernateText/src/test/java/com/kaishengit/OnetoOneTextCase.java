package com.kaishengit;

import com.kaishengit.pojo.Card;
import com.kaishengit.pojo.Child;
import com.kaishengit.pojo.Father;
import com.kaishengit.pojo.People;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class OnetoOneTextCase {


    private Session session;

    @Before
    public void before() {
        session = HibernateUtil.getSession();
        session.beginTransaction();
    }

    @After
    public void after() {
        session.getTransaction().commit();
    }

    @Test
    public void oneText() {
        Card card = new Card();
        card.setCardNum("123456");

        People people = new People();
        people.setName("张三");
        people.setAge(1);
        people.setCard(card);

        session.save(card);
        session.save(people);
    }

    @Test
    public void find() {
        /*Card card = (Card) session.get(Card.class,1);*/
        People people = (People) session.get(People.class, 1);
        System.out.println(people);
    }

    @Test
    public void findText() {
        People people = (People) session.get(People.class, 1);
        System.out.println(people);
        /*System.out.println(people.getCard());*/
    }

    @Test
    public void dele() {
        Card card = (Card) session.get(Card.class, 1);
        session.delete(card);
    }
    @Test
    public void save(){
        Father father = new Father();
        father.setName("周志龙的儿子");

        Child child = new Child();
        child.setName("赵琪的儿子");

        child.setFather(father);

        session.save(child);
        session.save(father);
    }
    @Test
    public void findByChild(){
        Father father = (Father) session.get(Father.class,1);
        System.out.println(father);
    }
}
