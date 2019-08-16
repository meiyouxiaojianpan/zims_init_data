package com.helios.gao;

import com.helios.gao.util.UrlUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : gaozhiwen
 * @date : 2019/8/3
 * 使用Jsoup解析html文件
 */
public class ParseHtml {

    public static final String REGULAR = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&:/~\\+#]*[\\w\\-\\@?^=%&/~\\+#])?";

    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\admin\\Desktop\\漫画\\[韩漫],解禁初始的快感,13-14,[85p],卡通漫畫,2048.tw - 2048核基地.html");
        String resultPath = "C:\\Users\\admin\\Desktop\\漫画\\[韩漫]解禁初始的快感13-14\\";
        Document document = Jsoup.parse(file, "UTF-8");

        Element readTpc = document.getElementById("read_tpc");
        Elements imgs = readTpc.getElementsByTag("img");
        Pattern pattern = Pattern.compile(REGULAR);
        List<String> imgUrls = new ArrayList<>();
        for (Element element : imgs) {
//            System.out.println(element);
            List<Attribute> attributes = element.attributes().asList();
            String urlStr = getUrl(attributes, pattern);
            System.out.println(urlStr);
            imgUrls.add(urlStr);
        }

        for (String imgUrl : imgUrls) {

            getimg(imgUrl, resultPath + UrlUtil.getUrlLastPart(imgUrl));
        }


    }

    public static String getUrl(List<Attribute> attributes, Pattern pattern) {
        for (Attribute attribute : attributes) {
            if ("onclick".equals(attribute.getKey())) {
                Matcher matcher = pattern.matcher(attribute.getValue());
                if (matcher.find()) {
                    return matcher.group(0);
                }
            }
        }
        return null;
    }

    private static void getimg(String imgUrl, String imgPath) throws IOException {
        System.out.println("开始下载：" + imgUrl);
        URL url = new URL(imgUrl);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.addRequestProperty("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/75.0.3770.142 Safari/537.36");
        InputStream inputStream = httpURLConnection.getInputStream();
        FileOutputStream outputStream = new FileOutputStream(new File(imgPath));
        int i = 0;
        while ((i = inputStream.read()) != -1) {
            outputStream.write(i);
        }
        System.out.println("下载完成：" + imgUrl);
        outputStream.close();
        inputStream.close();
    }
}
