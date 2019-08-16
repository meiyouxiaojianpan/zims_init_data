package com.helios.gao.util;

import org.apache.poi.xssf.usermodel.*;

import java.io.IOException;

/**
*@author : gaozhiwen
*@date : 2019/3/30
*
*/
public class CreateExcel {
    public static void main(String[] args) throws IOException {
        try (XSSFWorkbook xssfWorkbook = new XSSFWorkbook(XSSFWorkbookType.XLSX)) {
            XSSFSheet sheet = xssfWorkbook.createSheet();
            XSSFRow row = sheet.createRow(0);
            XSSFCell cell = row.createCell(0);
        }
    }
}
