package com.helios.gao.util;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/1/1
 */
public class SqlUtil {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\admin\\Desktop\\费用附件.xlsx");
//        File file = new File("C:\\Users\\admin\\Downloads\\分摊模板文件.xlsx");
        try {
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
//            List<String> list = new ArrayList<>();
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println("'" + row.getCell(0) + "',");
//                list.add(row.getCell(1).toString());
//                String name = row.getCell(5).toString();
//                String groupCode = row.getCell(6).toString();
//                System.out.println("update org_group set group_code = '" + groupCode + "' where group_name = '" + name + "';");
            }
//            System.out.println(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> pipeline() {
        File file = new File("C:\\Users\\admin\\Desktop\\011111.xlsx");
        List<String> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println(row.getCell(0));
                list.add(row.getCell(0).toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }

    public static List<String> printPdf() {
        File file = new File("C:\\Users\\admin\\Desktop\\sqlresult_3570159.xlsx");
        List<String> list = new ArrayList<>();
        try {
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                System.out.println("'" + row.getCell(1) + "',");
                list.add(row.getCell(1).toString());
            }
            System.out.println(list.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
