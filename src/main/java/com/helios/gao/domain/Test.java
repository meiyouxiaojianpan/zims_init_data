package com.helios.gao.domain;

import com.alibaba.excel.EasyExcelFactory;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.metadata.Sheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.google.common.base.Joiner;
import com.helios.gao.domain.enumeration.ReceiptPropertyEnum;
import com.helios.gao.domain.enumeration.SexagesimalEnum;
import com.helios.gao.util.JsonUtil;
import com.helios.gao.util.LocationUtil;
import com.helios.gao.util.UrlUtil;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.util.ObjectUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZonedDateTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * @author : gaozhiwen
 * @date : 2019/4/18
 */
public class Test {
    public static void main(String[] args) throws IOException {
//        MsgCryptUtil msgCryptUtil = new MsgCryptUtil("abc", "M9FrH93VaqKNMjNU6vxHuf", "9012acc8-aabf-4ffe-b8ad-19374ac511f8");
//        String s = "";
//        System.out.println(msgCryptUtil.encrypt(s));
        List<Long> longList = new ArrayList<>();
//        longList.add(111L);
        System.out.println(longList.contains(111L));

    }

    public static boolean cc(Object o1, Object o2) {
        System.out.println(o1.getClass());
        System.out.println(o2.getClass());
        if (o1.getClass() == o2.getClass()) {
            return true;
        }
        return false;
    }
}
