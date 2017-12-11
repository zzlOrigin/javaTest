package com.kaishengit.service.impl;

import com.kaishengit.entity.Student;
import com.kaishengit.mapper.StudentMapper;
import com.kaishengit.service.StudentServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServletImpl implements StudentServlet {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student findById(Integer id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public void save(Student student) {
        studentMapper.insertSelective(student);
    }
}
