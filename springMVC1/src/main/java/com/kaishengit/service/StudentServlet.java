package com.kaishengit.service;

import com.kaishengit.entity.Student;
import org.springframework.stereotype.Service;

public interface StudentServlet {

    Student findById(Integer id);

    void save(Student student);

}
