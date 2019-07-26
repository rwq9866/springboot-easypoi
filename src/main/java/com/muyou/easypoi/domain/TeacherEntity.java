package com.muyou.easypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@ExcelTarget("teacher")
@Table(name = "t_teacher_entity")
public class TeacherEntity implements Serializable {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 36)
    private String id;
    /** name */
    @Column(name = "name", length = 36)
    @Excel(name = "主讲老师_major,代课老师_absent", orderNum = "1",needMerge = true, isImportField = "true_major,true_absent")
    private String name;

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
}
