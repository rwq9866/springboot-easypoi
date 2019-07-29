package com.muyou.easypoi.controller;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.ExcelImportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import cn.afterturn.easypoi.excel.entity.ImportParams;
import com.muyou.easypoi.domain.CourseEntity;
import com.muyou.easypoi.domain.Student;
import com.muyou.easypoi.service.EasypoiService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EasypoiController {

    @Resource
    private EasypoiService easypoiService;

    // 添加学生信息
    @RequestMapping("addeasypoi")
    public void addeasypoi(Student student){
        easypoiService.addep(student);
    }

    // 删除学生信息
    @RequestMapping("deletestudent")
    public void deletestudent(String id){
        easypoiService.deletestu(id);
    }

    // 加载全部数据
    @RequestMapping("loadeasypoi")
    public List<Student> loadeasypoi(){
        return easypoiService.findeasypoi();
    }

    // 分页加载数据
    @RequestMapping("loadeasypoipage")
    public Map<String,Object> loadeasypoipage(String pageNo,String pageSize){
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNo) - 1,Integer.parseInt(pageSize));
        Page<Student> pages = easypoiService.findAll(pageable);
        Map<String,Object> map = new HashMap<>();
        map.put("pageNumber",pages.getNumber() + 1); // 当前页数
        map.put("totalPages",pages.getTotalPages()); // 总页数
        map.put("totalElements",pages.getTotalElements()); // 数据总数
        map.put("list",pages.getContent());
        return map;
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

    // 导入全部数据
    @RequestMapping("importData")
    public void importData(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) throws Exception {
        String fileName = file.getOriginalFilename();
        // 下面的filepath 获取的是上传到tomcat 数据缓存目录中的位置
        // String filePath = request.getServletContext().getRealPath("uploads");
        // 这个filepath 是项目中的static 下的自定义文件存放目录
        String filePath = "src/main/resources/static/uploads";
        uploadFile(file.getBytes(), filePath, fileName);
        ImportParams params = new ImportParams();
        params.setTitleRows(1);
        params.setHeadRows(1);
        long t = System.currentTimeMillis();
        List<Student> list = ExcelImportUtil.importExcel(new File(filePath + File.separator + fileName), Student.class, params);
        easypoiService.saveAll(list);
        long t1 = System.currentTimeMillis();
        System.out.println(t1 - t);
    }

    // 大数据导出全部数据
    // @RequestMapping("downloadExcel")
//    public void bigDataExport(HttpServletResponse res) throws Exception {
//        long t1 = System.currentTimeMillis();
//        // 默认的为application/json,而谷歌浏览器所期望的值应该是text/html
//        res.setHeader("Content-type", "text/html");
//        // 设置响应头 设置 导出的表的名称
//        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("学生信息表", "utf-8") + ".xlsx");
//        // 设置响应数据编码格式
//        res.setCharacterEncoding("utf-8");
//        List<Student> list1 = new ArrayList<Student>();
//        Workbook workbook = null;
//        ExportParams params = new ExportParams("大数据测试-学生个人信息表", "信息表");
//        List<Student> list = easypoiService.findAll();
//        int listSize = list.size();
//        int num = listSize / 10000;
//        for (Student t : list) {
//            list1.add(t);
//            if (list1.size() % 10000 == 0 || (num == 0 && list1.size() == listSize % 10000)) {
//                num--;
//                workbook = ExcelExportUtil.exportBigExcel(params, Student.class, list1);
//                list1.clear();
//            }
//        }
//        ExcelExportUtil.closeExportBigExcel();
//        workbook.write(res.getOutputStream());
//    }

    // 上传文件的保存
    public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if (!targetFile.exists()) {
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath + File.separator + fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public String zhongwen(String name){
        String asciiDemo = "";
        for(int i = 0;i < name.length();i++){
            asciiDemo += (int)(name.charAt(i));
            if(i != name.length() - 1) asciiDemo += " ";
        }
        return asciiDemo;
    }

    // 导出课程的详细信息
    @RequestMapping("dc")
    public void dc(HttpServletResponse res) throws IOException {
        // 默认的为application/json,而谷歌浏览器所期望的值应该是text/html
        res.setHeader("Content-type", "text/html");
        // 设置响应头 设置 导出的表的名称
        res.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("测试信息表", "utf-8") + ".xlsx");
        // 设置响应数据编码格式
        res.setCharacterEncoding("utf-8");
        List<CourseEntity> courseEntitys = easypoiService.findCourses();
        Workbook workbook = ExcelExportUtil.exportExcel(new ExportParams("学生基本信息", "学生"), CourseEntity.class, courseEntitys);
        workbook.write(res.getOutputStream());
    }
}
