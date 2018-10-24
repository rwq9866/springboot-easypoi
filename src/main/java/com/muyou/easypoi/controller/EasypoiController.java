package com.muyou.easypoi.controller;

import com.muyou.easypoi.domain.Student;
import com.muyou.easypoi.service.EasypoiService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class EasypoiController {

    @Resource
    private EasypoiService easypoiService;

    @RequestMapping("demo")
    public String demo(){
        return "Spring boot!!!";
    }

    @RequestMapping("addeasypoi")
    public void addeasypoi(Student student){
        easypoiService.addep(student);
    }

    @RequestMapping("loadeasypoi")
    public List<Student> loadeasypoi(){
        return easypoiService.findeasypoi();
    }

}
