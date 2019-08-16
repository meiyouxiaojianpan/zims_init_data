package com.helios.gao;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.IoUtil;
import org.apache.pdfbox.pdmodel.PDDocument;

import java.io.*;

/**
*@author : gaozhiwen
*@date : 2019/4/29
*
*/
public class AnalysisPDF {
    public static void main(String[] args) {
        File file = new File("C:\\Users\\admin\\Desktop\\ER01655015.pdf");
        try {
            String s = "%PDF-";
            byte[] bytes = s.getBytes();
            InputStream in = new FileInputStream(file);
            byte[] bytes1 = IoUtil.readBytes(in, 1024);
            String type = FileTypeUtil.getType(in);
            System.out.println(type);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
