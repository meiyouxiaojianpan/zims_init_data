package com.helios.gao;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
*@author : gaozhiwen
*@date : 2019/5/29
*
*/
public class ItextPDF {

    private static final int A4_WIDTH = (int) PageSize.A4.getWidth();
    private static final int A4_HEIGHT = (int) PageSize.A4.getHeight();

    public static void main(String[] args) {
//        String[] files = {"C:\\Users\\admin\\Desktop\\001\\1月交通发票.pdf",
//                "C:\\Users\\admin\\Desktop\\001\\1月交通发票2.pdf",
//                "C:\\Users\\admin\\Desktop\\001\\1月交通发票3.pdf",
//                "C:\\Users\\admin\\Desktop\\001\\1月交通发票4.pdf"};
//        String desc = "C:\\Users\\admin\\Desktop\\001\\4张地铁票.pdf";
//        mergePdf(files, desc);

//        manipulatePdf("C:\\Users\\admin\\Desktop\\001\\4张地铁票.pdf", "C:\\Users\\admin\\Desktop\\001\\manPDF.pdf", 2);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] bytes = outputStream.toByteArray();

        try {
            PdfReader reader = new PdfReader(bytes);
            System.out.println("============");
        } catch (IOException e) {
            e.printStackTrace();
        }
//        try {
//            mergeReceipt("C:\\Users\\admin\\Desktop\\001\\4张地铁票.pdf", "C:\\Users\\admin\\Desktop\\001\\test4.pdf");
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (DocumentException e) {
//            e.printStackTrace();
//        }

    }

    public static void mergePdf(String[] src, String desc) throws IOException, DocumentException {
//        Document document = new Document(PageSize.A4, 36F, 36F, 72F, 36F);
        Document document = new Document();
        PdfCopy pdfCopy = new PdfCopy(document, new FileOutputStream(desc));
        document.open();
        PdfReader pdfReader;
        int n;
        for (String fileName : src) {
            pdfReader = new PdfReader(fileName);
            n = pdfReader.getNumberOfPages();
            for (int page = 0; page < n; ) {
                pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, ++page));
            }
            pdfCopy.freeReader(pdfReader);
            pdfReader.close();
        }
        document.close();
    }


    public static void manipulatePdf(String src, String dest, int pow)
            throws IOException, DocumentException {
        // reader for the src file
        PdfReader reader = new PdfReader(src);
        // initializations
        Rectangle pageSize = reader.getPageSize(1);
        Rectangle newSize = (pow % 2) == 0 ?
                new Rectangle(pageSize.getWidth(), pageSize.getHeight()) :
                new Rectangle(pageSize.getHeight(), pageSize.getWidth());
        Rectangle unitSize = new Rectangle(pageSize.getWidth(), pageSize.getHeight());
        for (int i = 0; i < pow; i++) {
            unitSize = new Rectangle(unitSize.getHeight() / 2, unitSize.getWidth());
        }
        int n = (int)Math.pow(2, pow);
        int r = (int)Math.pow(2, pow / 2);
        int c = n / r;
        // step 1
        Document document = new Document(newSize, 0, 0, 0, 0);
        // step 2
        PdfWriter writer
                = PdfWriter.getInstance(document, new FileOutputStream(String.format(dest, n)));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page;
        Rectangle currentSize;
        float offsetX, offsetY, factor;
        int total = reader.getNumberOfPages();
        for (int i = 0; i < total; ) {
            if (i % n == 0) {
                document.newPage();
            }
            currentSize = reader.getPageSize(++i);
            factor = Math.min(
                    unitSize.getWidth() / currentSize.getWidth(),
                    unitSize.getHeight() / currentSize.getHeight());
            offsetX = unitSize.getWidth() * ((i % n) % c)
                    + (unitSize.getWidth() - (currentSize.getWidth() * factor)) / 2f;
            offsetY = newSize.getHeight() - (unitSize.getHeight() * (((i % n) / c) + 1))
                    + (unitSize.getHeight() - (currentSize.getHeight() * factor)) / 2f;
            page = writer.getImportedPage(reader, i);
            cb.addTemplate(page, factor, 0, 0, factor, offsetX, offsetY);
        }
        // step 5
        document.close();
        reader.close();
    }

    public static void manipulatePdf2(String src, String dest, int pow)
            throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        // initializations
        Rectangle pageSize = reader.getPageSize(1);
        Rectangle newSize = new Rectangle(pageSize.getWidth(), pageSize.getHeight());
        Rectangle unitSize = new Rectangle(pageSize.getWidth(), pageSize.getHeight());
        for (int i = 0; i < pow; i++) {
            unitSize = new Rectangle(unitSize.getHeight() / 2, unitSize.getWidth());
        }
        int n = (int)Math.pow(2, pow);
        int r = (int)Math.pow(2, pow / 2);
        int c = n / r;
        // step 1
        Document document = new Document(newSize, 0, 0, 0, 0);
        // step 2
        PdfWriter writer
                = PdfWriter.getInstance(document, new FileOutputStream(String.format(dest, n)));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page;
        Rectangle currentSize;
        float offsetX, offsetY, factor;
        int total = reader.getNumberOfPages();
        for (int i = 0; i < total; ) {
            if (i % n == 0) {
                document.newPage();
            }
            currentSize = reader.getPageSize(++i);
            factor = Math.min(
                    unitSize.getWidth() / currentSize.getWidth(),
                    unitSize.getHeight() / currentSize.getHeight());
            offsetX = unitSize.getWidth() * ((i % n) % c)
                    + (unitSize.getWidth() - (currentSize.getWidth() * factor)) / 2f;
            offsetY = newSize.getHeight() - (unitSize.getHeight() * (((i % n) / c) + 1))
                    + (unitSize.getHeight() - (currentSize.getHeight() * factor)) / 2f;
            page = writer.getImportedPage(reader, i);
            cb.addTemplate(page, 0.4, 0, 0, factor, offsetX, offsetY);
        }
        // step 5
        document.close();
        reader.close();
    }

    public static void mergeReceipt(String src, String dest) throws IOException, DocumentException {
        PdfReader reader = new PdfReader(src);
        Rectangle newSize = new Rectangle(A4_HEIGHT, A4_WIDTH);
        double[] offsetX = new double[]{0, 0.5, 0, 0.5};
        double[] offsetY = new double[]{0.5, 0.5, 0, 0};
        int n = 4;
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(dest));
        document.open();
        PdfContentByte cb = writer.getDirectContent();
        PdfImportedPage page;
        int total = reader.getNumberOfPages();
        for (int i = 0; i < total; i++) {
            if (i % n == 0) {
                document.newPage();
            }
            page = writer.getImportedPage(reader, i + 1);
            cb.addTemplate(page, 0.7, 0, 0, 0.7, offsetX[i%n] * A4_HEIGHT, offsetY[i%n] * A4_WIDTH);
        }
        document.close();
        reader.close();
    }
}
