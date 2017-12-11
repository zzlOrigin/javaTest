package com.kaishengit.pojo;

import java.util.Set;

public class Shop {

    private Integer id;

    private String name;

    private Set<Produce> produces;

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

    public Set<Produce> getProduces() {
        return produces;
    }

    public void setProduces(Set<Produce> produces) {
        this.produces = produces;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", produce=" + produces +
                '}';
    }
}
