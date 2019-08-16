package com.helios.gao;

import java.io.*;

/**
 * @author : gaozhiwen
 * @date : 2019/4/22
 */
public class SubtitleFormatting {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\admin\\Downloads\\冰与火之歌_权力的游戏.Game.Of.Thrones.S01-S05.BluRay.中英字幕￡CMCT[最终版]\\Game.of.Thrones.S01.BluRay.Chn&Eng YYeTs字幕\\冰与火之歌S01.权力的游戏.Game.Of.Thrones.S01E02.2011.720P.BluRay.x264.AC3-CMCT.YYeTs字幕.ass";
        String path1 = "C:\\Users\\admin\\Downloads\\冰与火之歌_权力的游戏.Game.Of.Thrones.S01-S05.BluRay.中英字幕￡CMCT[最终版]\\Game.of.Thrones.S01.BluRay.Chn&Eng YYeTs字幕";
//        format(path);
        format1(path1);
    }

    private static void format(String path) throws IOException {
        File file = new File(path);
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        String fileName = file.getName().substring(0, file.getName().indexOf(".")) + file.getName().substring(file.getName().indexOf("E"), (file.getName().indexOf("E") + 3));
        InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-16");
        File file1 = new File("C:\\Users\\admin\\Desktop\\" + fileName);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
        BufferedReader br = new BufferedReader(reader);
        String line = br.readLine();
        while (line != null) {
            if (line.startsWith("Dialogue")) {
                int index = line.indexOf(",,");
                if (line.substring(index + 2, index + 3).matches(regex)) {
//                    System.out.println(line);
                    if (line.contains("\\")) {
                        bw.write(line.substring(line.indexOf(",,") + 2, line.indexOf("\\")));
                    } else {
                        bw.write(line.substring(line.indexOf(",,") + 2));
                    }
                    bw.newLine();
                    bw.write(line.substring(line.lastIndexOf("}") + 1));
                    bw.newLine();
                    bw.newLine();
                }
            }
            line = br.readLine();
        }
        bw.flush();
        bw.close();
    }

    private static void format1(String path) throws IOException {
        File folder = new File(path);
        File[] files = folder.listFiles();
        String regex = "^[a-z0-9A-Z\u4e00-\u9fa5]+$";
        for (File file : files) {
            String fileName = file.getName().substring(0, file.getName().indexOf(".")) + file.getName().substring(file.getName().indexOf("E"), (file.getName().indexOf("E") + 3));
            InputStreamReader reader = new InputStreamReader(new FileInputStream(file), "UTF-16");
            File file1 = new File(folder.getAbsolutePath() + "\\" + fileName);
            BufferedWriter bw = new BufferedWriter(new FileWriter(file1));
            BufferedReader br = new BufferedReader(reader);
            String line = br.readLine();
            while (line != null) {
                if (line.startsWith("Dialogue")) {
                    int index = line.indexOf(",,");
                    if (line.substring(index + 2, index + 3).matches(regex)) {
                        if (line.contains("\\")) {
                            bw.write(line.substring(line.indexOf(",,") + 2, line.indexOf("\\")));
                        } else {
                            bw.write(line.substring(line.indexOf(",,") + 2));
                        }
                        bw.newLine();
                        bw.write(line.substring(line.lastIndexOf("}") + 1));
                        bw.newLine();
                        bw.newLine();
                    }
                }
                line = br.readLine();
            }
            bw.flush();
            bw.close();
        }
    }

}
