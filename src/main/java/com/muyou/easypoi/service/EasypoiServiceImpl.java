package com.muyou.easypoi.service;

import com.muyou.easypoi.domain.CourseEntity;
import com.muyou.easypoi.domain.CourseRepository;
import com.muyou.easypoi.domain.EasypoiRepository;
import com.muyou.easypoi.domain.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EasypoiServiceImpl implements EasypoiService {

    @Resource
    private EasypoiRepository easypoiRepository;

    @Resource
    private CourseRepository courseRepository;

    @Override
    public void addep(Student student) {
        String createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        student.setCreatetime(createtime);
        easypoiRepository.save(student);
    }

    @Override
    public List<Student> findeasypoi() {
        return easypoiRepository.findAll();
    }

    @Override
    public List<Student> findAll() {
        return easypoiRepository.findAll();
    }

    @Override
    public void deletestu(String id) {
        easypoiRepository.deleteById(id);
    }

    @Override
    public void saveAll(List<Student> list) {
        easypoiRepository.saveAll(list);
    }

    @Override
    public Page<Student> findAll(Pageable pageable) {
        return easypoiRepository.findAll(pageable);
    }

    @Override
    public List<CourseEntity> findCourses() {
        return courseRepository.findAll();
    }
}
