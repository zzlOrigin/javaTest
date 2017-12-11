package com.kaishengit.pojo;

public class Card {

    private Integer id;
    private String cardNum;

    private People people;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public People getPeople() {
        return people;
    }

    public void setPeople(People people) {
        this.people = people;
    }

    @Override
    public String toString() {
        return "Card{" +
                "id=" + id +
                ", cardName='" + cardNum + '\'' +
                '}';
    }
}
