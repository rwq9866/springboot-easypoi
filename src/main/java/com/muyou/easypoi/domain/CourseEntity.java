package com.muyou.easypoi.domain;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelEntity;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@ExcelTarget("course")
@Table(name = "t_course_entity")
public class CourseEntity implements Serializable {
    /** 主键 */
    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    @Column(name = "id", length = 36)
    private String id;

    /** 课程名称 */
    @Column(name = "name", length = 36)
    @Excel(name = "课程名称", orderNum = "1",needMerge = true, width = 25)
    private String  name;

    /** 老师主键 */
    @OneToOne(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "teacher_id")
    @ExcelEntity(id = "absent")
    private TeacherEntity teacher;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    @ExcelCollection(name = "学生", orderNum = "4")
    private List<StudentEntity> students;

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

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public List<StudentEntity> getStudents() {
        return students;
    }

    public void setStudents(List<StudentEntity> students) {
        this.students = students;
    }
}
