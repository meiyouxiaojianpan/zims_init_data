package com.helios.gao.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.*;
import java.util.Arrays;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/6/6
 */
public class CsvUtil {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\sqlresult_3924894.csv");
        InputStream inputStream = new FileInputStream(file);
        CSVParser parser = new CSVParser(new InputStreamReader(inputStream, "GBK"), CSVFormat.DEFAULT);
        List<CSVRecord> records = parser.getRecords();
        for (int i = 1; i < records.size(); i++) {
            CSVRecord csvRecord = records.get(i);
            System.out.println("DELETE FROM `art_invoice_attachment` WHERE `invoice_id` = '" + csvRecord.get(0) + "' AND `attachment_id` = '" + csvRecord.get(1) + "';");
        }
        parser.close();
        inputStream.close();
    }
}
