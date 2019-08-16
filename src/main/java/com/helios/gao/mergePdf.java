package com.helios.gao;

import com.helios.gao.util.PDFHelper;
import com.itextpdf.text.pdf.PdfReader;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/8/4
 */
public class mergePdf {
    public static void main(String[] args) throws IOException {
        //获取所有需要拼接的pdf文件
        List<PdfReader> pdfReaders = new ArrayList<>();

        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感27-28.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感29-30.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感31-32.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感33-34.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感35-36.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感37-38.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感39-40.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感41-42.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感43.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感44.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感45-46.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感47-48.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感49-50.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感51-52.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感53-54.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感55-56.pdf"));
        pdfReaders.add(new PdfReader("C:\\Users\\admin\\Desktop\\漫画27-58\\[韩漫]解禁初始的快感57-58.pdf"));


        byte[] bytes = PDFHelper.mergePdf(pdfReaders);
        OutputStream outputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\漫画27-58\\27-58.pdf");
        outputStream.write(bytes);
        outputStream.close();
        System.out.println("图片转换完成");
    }
}
