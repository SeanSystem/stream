package com.example.stream.utils;

import pres.lnk.jxlss.JxlsBuilder;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 生成Excel文件的工具类
 *
 * @author Sean
 *
 * 2019/07/20
 */
public class ExcelUtils {
    private ExcelUtils() {
    }

    /**
     * 生成并下载Excel文件
     *
     * @param fileName         生成的excel文件名
     * @param templateFileName excel模板名
     * @param data             导出到excel的数据
     * @param response         HttpServletResponse对象
     * @throws Exception 异常
     */
    public static void downExcel(String fileName, String templateFileName, Map<String, Object> data,
                                 HttpServletResponse response) throws Exception {
        //使用路径加载模板，可以是相对路径，也可以绝对路径
        JxlsBuilder jxlsBuilder = JxlsBuilder.getBuilder("dome/" + templateFileName);
        downExcel(jxlsBuilder, fileName, data, response);
    }

    /**
     * 生成并下载Excel文件(excel中包含图片)
     *
     * @param fileName         生成的excel文件名
     * @param templateFileName excel模板名
     * @param data             导出到excel的数据
     * @param imgRoot          图片路径
     * @param response         HttpServletResponse对象
     * @throws Exception 异常
     */
    public static void downExcel(String fileName, String templateFileName, Map<String, Object> data,
                                 HttpServletResponse response, String imgRoot) throws Exception {
        String iRoot = ExcelUtils.class.getClassLoader().getResource(imgRoot).getPath();
        JxlsBuilder jxlsBuilder = JxlsBuilder.getBuilder("dome/" + templateFileName);
        jxlsBuilder.imageRoot(iRoot);
        downExcel(jxlsBuilder, fileName, data, response);
    }

    /**
     * 生成并下载Excel文件(多表格)
     *
     * @param fileName         生成的excel文件名
     * @param templateFileName excel模板名
     * @param data             导出到excel的数据
     * @param sheetNames       表格名集合
     * @param response         HttpServletResponse对象
     * @throws Exception 异常
     */
    public static void downExcel(String fileName, String templateFileName, Map<String, Object> data,
                                 HttpServletResponse response, List<String> sheetNames) throws Exception {
        JxlsBuilder jxlsBuilder = JxlsBuilder.getBuilder("dome/" + templateFileName);
        jxlsBuilder.putVar("sheetNames", sheetNames).removeSheet("Sheet");
        downExcel(jxlsBuilder, fileName, data, response);
    }


    /**
     * 公共生成Excel下载方法
     *
     * @param jxlsBuilder JxlsBuilder对象
     * @param fileName    文件名
     * @param data        导出数据
     * @param response    HttpServletResponse对象
     * @throws Exception 异常
     */
    private static void downExcel(JxlsBuilder jxlsBuilder, String fileName, Map<String, Object> data,
                                  HttpServletResponse response) throws Exception {
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        //设置输出流
        jxlsBuilder.out(os);
        //设置导出数据
        Set<String> keys = data.keySet();
        for (String key : keys) {
            jxlsBuilder.putVar(key, data.get(key));
        }
        //生成excel文件
        jxlsBuilder.ignoreImageMiss(true).build();
        byte[] content = os.toByteArray();
        InputStream is = new ByteArrayInputStream(content);
        // 设置response参数
        response.reset();
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream out = response.getOutputStream();
        try (BufferedInputStream bis = new BufferedInputStream(is);
             BufferedOutputStream bos = new BufferedOutputStream(out)) {
            byte[] buff = new byte[2048];
            int bytesRead;
            while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesRead);
            }
        } catch (final IOException e) {
            throw e;
        }
    }
}
