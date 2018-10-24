package com.muyou.easypoi.service;

import com.muyou.easypoi.domain.Student;

import java.util.List;

public interface EasypoiService {

    void addep(Student student);

    List<Student> findeasypoi();

    List<Student> findAll();
}
