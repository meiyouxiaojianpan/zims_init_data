package com.helios.gao;

import com.helios.gao.util.PDFHelper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author : gaozhiwen
 * @date : 2019/8/1
 * 图片转pdf
 */
public class ImgToPdf {

//    public static void main(String[] args) throws IOException {
//        List<PdfReader> readers = new ArrayList<>();
//        readers.add(new PdfReader("C:\\Users\\admin\\Desktop\\test3.pdf"));
//        readers.add(new PdfReader("C:\\Users\\admin\\Desktop\\test.pdf"));
//        readers.add(new PdfReader("C:\\Users\\admin\\Desktop\\test1.pdf"));
//        readers.add(new PdfReader("C:\\Users\\admin\\Desktop\\test2.pdf"));
//        byte[] bytes = PDFHelper.mergePdf(readers);
//        OutputStream outputStream = new FileOutputStream("C:\\Users\\admin\\Desktop\\result1.pdf");
//        outputStream.write(bytes);
//        outputStream.close();
//    }

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\漫画\\[韩漫]解禁初始的快感57-58");
        String resultPath = file.getAbsolutePath() + ".pdf";
        File[] files = file.listFiles();
        List<String> fileNames = getSortedFileNames("C:\\Users\\admin\\Desktop\\漫画\\[韩漫],解禁初始的快感,57-58,[62p],卡通漫畫,2048.tw - 2048核基地.html");
        Map<String, String> fileMap = new HashMap<>();
        for (File f : files) {
//            fileNames.add(f.getName());
            fileMap.put(f.getName(), f.getAbsolutePath());
        }
//        fileNames.sort(new Comparator<String>() {
//            @Override
//            public int compare(String o1, String o2) {
//                return o1.compareTo(o2);
//            }
//        });
        System.out.println(fileNames.size());
        List<PdfReader> readers = new ArrayList<>();
        ByteArrayOutputStream arrayOutputStream = new ByteArrayOutputStream();
        for (String fileName : fileNames) {
            try {
                readers.add(new PdfReader(getPdf(fileMap.get(fileName), arrayOutputStream)));
            } catch (Exception e) {
                System.out.println("获取单个图片pdf出错，出错图片:{" + fileName + "},出错原因:{ " + e.getMessage() + "}");
            }
        }
        byte[] bytes = PDFHelper.mergePdf(readers);
        OutputStream outputStream = new FileOutputStream(resultPath);
        outputStream.write(bytes);
        outputStream.close();
        System.out.println("图片转换完成");
    }

    private static byte[] getPdf(String filePath, ByteArrayOutputStream out) throws IOException, DocumentException {
        Image image = Image.getInstance(filePath);
        Rectangle rectangle = new Rectangle(image.getWidth(), image.getHeight());
        Document document = new Document(rectangle, 0, 0, 0, 0);
        PdfWriter writer = PdfWriter.getInstance(document, out);
        document.open();
        document.add(image);
        document.close();
        return out.toByteArray();
    }

    private static List<String> getSortedFileNames(String htmlPath) throws IOException {
        File file = new File(htmlPath);
        org.jsoup.nodes.Document document = Jsoup.parse(file, "UTF-8");

        org.jsoup.nodes.Element readTpc = document.getElementById("read_tpc");
        Elements imgs = readTpc.getElementsByTag("img");
        Pattern pattern = Pattern.compile(ParseHtml.REGULAR);
        List<String> imgUrls = new ArrayList<>();
        for (org.jsoup.nodes.Element element : imgs) {
            List<Attribute> attributes = element.attributes().asList();
            String urlStr = ParseHtml.getUrl(attributes, pattern);
            System.out.println(urlStr);
            imgUrls.add(urlStr);
        }
        return imgUrls.stream().map(i -> i.substring(i.lastIndexOf("/") + 1)).collect(Collectors.toList());
    }
}
