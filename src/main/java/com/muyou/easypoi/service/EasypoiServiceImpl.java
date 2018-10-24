package com.muyou.easypoi.service;

import com.muyou.easypoi.domain.EasypoiRepository;
import com.muyou.easypoi.domain.Student;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class EasypoiServiceImpl implements EasypoiService {

    @Resource
    private EasypoiRepository easypoiRepository;

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
}
