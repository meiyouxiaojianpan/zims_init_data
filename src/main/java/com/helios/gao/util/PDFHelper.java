package com.helios.gao.util;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class PDFHelper {

    private static final int A4_WIDTH = (int) PageSize.A4.getWidth();
    private static final int A4_HEIGHT = (int) PageSize.A4.getHeight();

    /**
     * 合并PDF电子发票（四合一）
     *
     * @param readers
     * @return
     */
    public static byte[] mergeReceiptPdf(List<PdfReader> readers) {
        log.info("mergeReceiptPdf");
        if (CollectionUtils.isEmpty(readers)) {
            return new byte[0];
        }
        List<PdfReader> result = new ArrayList<>();
        try {
            result.add(new PdfReader(manipulatePdf(mergeReceiptFirstPdf(readers))));
            result.add(new PdfReader(mergeReceiptOtherPdf(readers)));
        } catch (IOException e) {
            log.error("error to create document,method:mergeReceiptPdf,error message:{}", e.getMessage());
        }
        return mergePdf(result);
    }

    /**
     * 将多个pdf的第一页合并为一个pdf
     * @param readers
     * @return
     */
    private static byte[] mergeReceiptFirstPdf(List<PdfReader> readers){
        log.info("mergeReceiptFirstPdf");
        assert readers != null;
        ByteArrayOutputStream outputStream = null;
        Document document = null;
        PdfCopy pdfCopy = null;
        try {
            outputStream = new ByteArrayOutputStream();
            document = new Document();
            pdfCopy = new PdfCopy(document, outputStream);
            document.open();
            for (PdfReader pdfReader : readers) {
                pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, 1));
                pdfCopy.freeReader(pdfReader);
            }
        }catch (DocumentException e) {
            log.error("error to create document,method:mergeReceiptFirstPdf,error message:{}", e.getMessage());
        } catch (IOException e) {
            log.error("error to operate pdfCode,method:mergeReceiptFirstPdf,error message:{}", e.getMessage());
        }finally {
            if (pdfCopy != null) {
                pdfCopy.close();
            }
            if (document != null) {
                document.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("mergeReceiptFirstPdf",e);
                }
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 将多个pdf除第一页之外的所有页合并为一个pdf
     *
     * @param readers
     * @return
     */
    private static byte[] mergeReceiptOtherPdf(List<PdfReader> readers) {
        log.info("mergeReceiptOtherPdf");
        assert readers != null;
        ByteArrayOutputStream outputStream = null;
        Document document = null;
        PdfCopy pdfCopy = null;
        try {
            outputStream = new ByteArrayOutputStream();
            document = new Document();
            pdfCopy = new PdfCopy(document, outputStream);
            document.open();
            for (PdfReader pdfReader : readers) {
                if (pdfReader.getNumberOfPages() > 1) {
                    for (int i = 2; i < pdfReader.getNumberOfPages() + 1; i++) {
                        pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, i));
                    }
                    pdfCopy.freeReader(pdfReader);
                }
            }
        } catch (DocumentException e) {
            log.error("error to create document,method:mergeReceiptOtherPdf,error message:{}", e.getMessage());
        } catch (IOException e) {
            log.error("error to operate pdfCode,method:mergeReceiptOtherPdf,error message:{}", e.getMessage());
        } finally {
            if (pdfCopy != null) {
                pdfCopy.close();
            }
            if (document != null) {
                document.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("mergeReceiptOtherPdf", e);
                }
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 合并PDF
     *
     * @param readers
     * @return
     */
    public static byte[] mergePdf(List<PdfReader> readers) {
        log.info("mergePdf");
        if (CollectionUtils.isEmpty(readers)) {
            return new byte[0];
        }
        ByteArrayOutputStream outputStream = null;
        Document document = null;
        PdfCopy pdfCopy = null;
        try {
            outputStream = new ByteArrayOutputStream();
            document = new Document();
            pdfCopy = new PdfCopy(document, outputStream);
            document.open();
            int n;
            for (PdfReader pdfReader : readers) {
                n = pdfReader.getNumberOfPages();
                for (int page = 0; page < n; ) {
                    pdfCopy.addPage(pdfCopy.getImportedPage(pdfReader, ++page));
                }
                pdfCopy.freeReader(pdfReader);
            }
        } catch (DocumentException e) {
            log.error("error to create document,method:mergePdf,error message:{}", e.getMessage());
        } catch (IOException e) {
            log.error("error to operate pdfCode,method:mergePdf,error message:{}", e.getMessage());
        } finally {
            if (pdfCopy != null) {
                pdfCopy.close();
            }
            if (document != null) {
                document.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    log.error("mergePdf",e);
                }
            }
        }
        return outputStream.toByteArray();
    }


    /**
     * 将2的pow次方PDF合并至一页
     * @param src
     * @param pow
     * @return
     */
    public static byte[] manipulatePdf(byte[] src, int pow) {
        log.info("manipulatePdf");
        ByteArrayOutputStream outputStream = null;
        PdfReader reader = null;
        Document document = null;
        try {
            outputStream = new ByteArrayOutputStream();
            // reader for the src stream
            reader = new PdfReader(src);
            // initializations
            Rectangle pageSize = reader.getPageSize(1);
            Rectangle newSize = (pow % 2) == 0 ?
                    new Rectangle(pageSize.getWidth(), pageSize.getHeight()) :
                    new Rectangle(pageSize.getHeight(), pageSize.getWidth());
            Rectangle unitSize = new Rectangle(pageSize.getWidth(), pageSize.getHeight());
            for (int i = 0; i < pow; i++) {
                unitSize = new Rectangle(unitSize.getHeight() / 2, unitSize.getWidth());
            }
            int n = (int) Math.pow(2, pow);
            int r = (int) Math.pow(2, pow / 2d);
            int c = n / r;
            // step 1
            document = new Document(newSize, 0, 0, 0, 0);
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
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
                offsetY = newSize.getHeight() - (unitSize.getHeight() * (((i % n) / (float) c) + 1))
                        + (unitSize.getHeight() - (currentSize.getHeight() * factor)) / 2f;
                page = writer.getImportedPage(reader, i);
                cb.addTemplate(page, factor, 0, 0, factor, offsetX, offsetY);
            }
        } catch (DocumentException e) {
            log.error("error to create document,method:manipulatePdf,error message:{}", e.getMessage());
        } catch (IOException e) {
            log.error("error to operate pdfCode,method:manipulatePdf,error message:{}", e.getMessage());
        } finally {
            // step 5
            if (document != null) {
                document.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }

    /**
     * 专用于合并四张发票到一张A4纸，更准确
     * @param src
     * @return
     */
    private static byte[] manipulatePdf(byte[] src) {
        assert src != null;
        ByteArrayOutputStream outputStream = null;
        PdfReader reader = null;
        Document document = null;
        try {
            outputStream = new ByteArrayOutputStream();
            reader = new PdfReader(src);
            Rectangle newSize = new Rectangle(A4_HEIGHT, A4_WIDTH);
            double[] offsetX = new double[]{0, 0.5, 0, 0.5};
            double[] offsetY = new double[]{0.5, 0.5, 0, 0};
            int n = 4;
            document = new Document(newSize, 0, 0, 0, 0);
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            document.open();
            PdfContentByte cb = writer.getDirectContent();
            PdfImportedPage page;
            int total = reader.getNumberOfPages();
            for (int i = 0; i < total; i++) {
                if (i % n == 0) {
                    document.newPage();
                }
                page = writer.getImportedPage(reader, i + 1);
                cb.addTemplate(page, 0.7, 0, 0, 0.7, offsetX[i%4] * A4_HEIGHT, offsetY[i%4] * A4_WIDTH);
            }
        }catch (DocumentException e) {
            log.error("error to create document,method:manipulatePdf,error message:{}", e.getMessage());
        } catch (IOException e) {
            log.error("error to operate pdfCode,method:manipulatePdf,error message:{}", e.getMessage());
        } finally {
            // step 5
            if (document != null) {
                document.close();
            }
            if (reader != null) {
                reader.close();
            }
            if (outputStream != null) {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return outputStream.toByteArray();
    }
}

