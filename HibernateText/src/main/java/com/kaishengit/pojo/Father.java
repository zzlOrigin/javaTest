package com.kaishengit.pojo;

public class Father {

    private Integer id;

    private String name;

    private Child child;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Child getChild() {
        return child;
    }

    public void setChild(Child child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Father{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
