package com.helios.gao;

import com.helios.gao.util.SqlUtil;
import com.helios.gao.util.ZimsClient;

import java.util.List;

/**
*@author : gaozhiwen
*@date : 2019/2/26
*
*/
public class PrintPDF {
    public static void main(String[] args) throws InterruptedException {
        List<String> list = SqlUtil.printPdf();
        for (String s : list) {
            ZimsClient.printPDF(s);
            Thread.sleep(10000);
        }
    }
}
