package com.example.stream.model;

import java.util.List;
import java.util.Map;

/**
 * 封装导出的Excel数据
 *
 * @author Sean
 * 2019/07/21
 */
public class ExcelData {

    private String fileName;

    private String templateFileName;

    private List<Employee> list;

    public List<Employee> getList() {
        return list;
    }

    public void setList(List<Employee> list) {
        this.list = list;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTemplateFileName() {
        return templateFileName;
    }

    public void setTemplateFileName(String templateFileName) {
        this.templateFileName = templateFileName;
    }
}
