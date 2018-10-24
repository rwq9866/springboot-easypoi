package com.muyou.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.muyou.easypoi.domain.Student;
import com.muyou.easypoi.service.EasypoiService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

@RestController
public class EasypoiController {

    @Resource
    private EasypoiService easypoiService;

    // 添加学生信息
    @RequestMapping("addeasypoi")
    public void addeasypoi(Student student){
        easypoiService.addep(student);
    }

    // 加载全部数据
    @RequestMapping("loadeasypoi")
    public List<Student> loadeasypoi(){
        return easypoiService.findeasypoi();
    }

    // 导出全部学生数据
    @RequestMapping("exportstudent")
    public void exportstudent(HttpServletResponse res) throws IOException {
        // 默认的为application/json,而谷歌浏览器所期望的值应该是text/html
        res.setHeader("Content-type", "text/html");
        // 设置响应头 设置 导出的表的名称
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生基本信息表", "utf-8") + ".xlsx");
        // 设置响应数据编码格式
        res.setCharacterEncoding("utf-8");
        List<Student> list = easypoiService.findAll();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生基本信息", "学生"), Student.class, list);
        workbook.write(res.getOutputStream());
    }
}
