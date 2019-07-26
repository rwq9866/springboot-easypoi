package com.muyou.easypoi.service;

import com.muyou.easypoi.domain.CourseEntity;
import com.muyou.easypoi.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EasypoiService {

    void addep(Student student);

    List<Student> findeasypoi();

    List<Student> findAll();

    void deletestu(String id);

    void saveAll(List<Student> list);

    Page<Student> findAll(Pageable pageable);

    List<CourseEntity> findCourses();
}
