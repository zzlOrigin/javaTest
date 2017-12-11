package com.kaishengit.pojo;

import java.util.Set;

public class Room {

    private Integer id;
    private String room;

    private Set<Student> students;
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", room='" + room + '\'' +
                '}';
    }
}
