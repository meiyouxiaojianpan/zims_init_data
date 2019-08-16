package com.helios.gao.util;

import com.helios.gao.domain.enumeration.SexagesimalEnum;

import java.math.BigDecimal;

/**
*@author : gaozhiwen
*@date : 2019/7/22
*
*/
public class LocationUtil {
    private static final String DU = "°";
    private static final String FEN = "′";
    private static final String MIAO = "″";
    private static final String NEGATIVE = "-";
    private static final String NORTH_LATITUDE = "N";
    private static final String SOUTH_LATITUDE = "S";
    private static final String EAST_LONGITUDE = "E";
    private static final String WEST_LONGITUDE = "W";

    /**
     * 小数格式经纬度转换为度分秒
     *
     * @param num
     * @return
     */
    public static String convertToSexagesimal(double num) {
        int du = (int) Math.floor(Math.abs(num));
        double temp = getPoint(Math.abs(num)) * 60;
        int fen = (int) Math.floor(temp);
        double miao = getPoint(temp) * 60;
        if (num < 0) {
            return NEGATIVE + du + DU + fen + FEN + miao + MIAO;
        }
        return du + DU + fen + FEN + miao + MIAO;
    }

    /**
     * 转换度分秒的符号
     * 将正负号转换为 N S E W
     *
     * @param numStr          度分秒格式字符串
     * @param sexagesimalEnum 输入的小数为经度还是纬度
     * @return
     */
    public static String convertToSexagesimal(String numStr, SexagesimalEnum sexagesimalEnum) {
        String result = numStr;
        boolean negative = false;
        String prefix = "";
        if (NEGATIVE.equals(numStr.substring(0, 1))) {
            result = numStr.substring(1);
            negative = true;
        }
        switch (sexagesimalEnum) {
            case LATITUDE:
                if (negative) {
                    prefix = SOUTH_LATITUDE;
                } else {
                    prefix = NORTH_LATITUDE;
                }
                break;
            case LONGITUDE:
                if (negative) {
                    prefix = WEST_LONGITUDE;
                } else {
                    prefix = EAST_LONGITUDE;
                }
                break;
            default:
        }
        return prefix + result;
    }

    /**
     * 获取小数部分
     * @param num
     * @return
     */
    private static double getPoint(double num) {
        int var2 = (int) num;
        return new BigDecimal(Double.toString(num)).subtract(new BigDecimal(Integer.toString(var2))).floatValue();
    }

    /**
     * 对度分秒设置保留位数
     * 设置度的位数会丢失分秒信息
     * 设置分的位数会丢失秒信息
     * @param num
     * @param type
     * @param i
     * @return
     */
    public static String convertToSexagesimal(double num, SexagesimalEnum type, int i) {
        String result = convertToSexagesimal(num);
        int du = result.indexOf(DU);
        int fen = result.indexOf(FEN);
        int miao = result.indexOf(MIAO);
        String s = null;
        int temp;
        switch (type) {
            case DU:
                if (i <= du) {
                    s = result.substring(0, i) + DU;
                } else {
                    s = result.substring(0, du + 1);
                }
                break;
            case FEN:
                temp = fen - du - 1;
                if (i <= temp) {
                    s = result.substring(0, du + 1 + i) + FEN;
                } else {
                    s = result.substring(0, fen + 1);
                }
                break;
            case MIAO:
                temp = miao - fen - 1;
                if (i <= temp) {
                    s = result.substring(0, fen + 1 + i) + MIAO;
                } else {
                    s = result.substring(0, miao + 1);
                }
                break;
            default:
        }
        return s;
    }
}
