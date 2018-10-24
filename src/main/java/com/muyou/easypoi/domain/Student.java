package com.muyou.easypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Student implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;
    @Excel(name = "姓名", orderNum = "1",isImportField = "name")
    private String name;
    @Excel(name = "年龄", orderNum = "2",isImportField = "age")
    private int age;
    @Excel(name = "性别", orderNum = "3",isImportField = "gender")
    private char gender;
    @Excel(name = "薪资", orderNum = "4",isImportField = "salary")
    private double salary;
    @Excel(name = "家庭住址",width = 30,orderNum = "5", isImportField = "address")
    private String address;
    @Excel(name = "创建时间",width = 20,orderNum = "6",isImportField = "createtime")
    private String createtime;

    public Student() {
    }

    public Student(String name, int age, char gender, double salary, String address, String createtime) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.salary = salary;
        this.address = address;
        this.createtime = createtime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", gender=" + gender +
                ", salary=" + salary +
                ", address='" + address + '\'' +
                ", createtime='" + createtime + '\'' +
                '}';
    }


}
