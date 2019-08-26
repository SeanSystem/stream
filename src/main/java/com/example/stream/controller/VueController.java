package com.example.stream.controller;

import com.example.stream.model.Employee;
import com.example.stream.model.ExcelData;
import com.example.stream.model.Experience;
import com.example.stream.model.Person;
import com.example.stream.utils.ExcelUtils;
import pres.lnk.jxlss.JxlsBuilder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * @author Sean
 * @date 2019/7/10 21:01
 */
@Controller
@ResponseBody
public class VueController {

    @GetMapping("people")
    public List<Person> getPersonInfo(String id) {
        System.out.println(id);
        ArrayList<Person> list = new ArrayList<>();
        Person person = new Person("sean", 18, "男");
        Person person2 = new Person("jhon", 18, "男");
        list.add(person);
        list.add(person2);
        return list;
    }

    @PostMapping("/register")
    public Person uerRegister(@RequestBody Person person) {
        return person;
    }

    @GetMapping("/export")
    public void exportExcell() throws Exception {
        String outPath = "E:/out_employee.xlsx";
        String imgRoot = Objects.requireNonNull(VueController.class.getClassLoader().getResource("jxls_templates/dome")).getPath();
        Employee emp = getEmployee();
        List<Experience> educationList = getEducationList();
        List<Experience> workList = getWorkList();
        JxlsBuilder jxlsBuilder = JxlsBuilder
                /* -- 加载模板方式 -- */
                //使用文件流加载模板
//                .getBuilder(inputStream)
                //使用文件加载模板
//                .getBuilder(file)
                //使用路径加载模板，可以是相对路径，也可以绝对路径
                .getBuilder("dome/employee.xlsx")

                /* -- 输出文件方式 -- */
                //指定输出的文件流
//                .out(outputStream)
                //指定输出的文件
//                .out(file)
                //指定输出的路径
                .out(outPath)

                /* 添加生成的数据 */
                .putVar("emp", emp)
                .putVar("educationList", educationList)
                .putVar("workList", workList)
                //设置图片路径的根目录
                .imageRoot(imgRoot)
                //设置如果图片缺失不终止生成
                .ignoreImageMiss(true)
                //添加自定工具类
//                .addFunction("jx", new JxlsUtil())
                .build();
        System.out.println("导出成功");
        System.out.println(jxlsBuilder.getOutFile().getAbsolutePath());
    }

    @GetMapping("/downExcel")
    public void downExcel(HttpServletResponse response) throws Exception {
        String fileName = "遍历";
        String templateFileName = "each.xlsx";
        Map<String, Object> data = new HashMap<>(16);
        data.put("list", getEmployees());
        ExcelUtils.downExcel(fileName, templateFileName, data, response);
    }

    @PostMapping("/downExc")
    public void downExcel(HttpServletResponse response,@RequestBody ExcelData excelData) throws Exception {
        Map<String, Object> data = new HashMap<>(16);
        data.put("list", excelData.getList());
        ExcelUtils.downExcel(excelData.getFileName(), excelData.getTemplateFileName(), data, response);
    }

    @GetMapping("/data")
    public List<Employee> getData() {
        return getEmployees();
    }

    @GetMapping("/downWithImage")
    public void downExcelWithImage(HttpServletResponse response) throws Exception {
        String fileName = "个人简历";
        String templateFileName = "employee.xlsx";
        Map<String, Object> data = new HashMap<>(16);
        Employee emp = getEmployee();
        List<Experience> educationList = getEducationList();
        List<Experience> workList = getWorkList();
        data.put("emp", emp);
        data.put("educationList", educationList);
        data.put("workList", workList);
        String imgRoot = "jxls_templates/dome";
        ExcelUtils.downExcel(fileName, templateFileName, data, response, imgRoot);
    }

    private static Employee getEmployee() {
        return new Employee();
    }

    private static List<Experience> getWorkList() {
        return new ArrayList<>();
    }

    private static List<Experience> getEducationList() {
        List<Experience> list = new ArrayList<>();
        list.add(new Experience("xxx大学"));
        list.add(new Experience("xxx高中"));
        list.add(new Experience("xxx初中"));
        return list;
    }

    private static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();
        list.add(new Employee());
        Employee e = new Employee();
        e.setName("李四");
        list.add(e);
        e = new Employee();
        e.setName("王五");
        list.add(e);
        return list;
    }
}
