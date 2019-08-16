package com.helios.gao;

import com.helios.gao.util.SqlUtil;
import com.helios.gao.util.ZimsClient;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gaozhiwen
 * @date : 2019/1/25
 */
public class SendEmail {
    public static void main(String[] args) {
        List<String> list = SqlUtil.pipeline();
        list.remove("c159294b-834d-4e57-b9fe-6e9250477c21");
        list.remove("655caf3a-c75d-4e14-b720-fc0e3fe328f8");
        list.remove("9deee8e2-0857-415a-94be-ed5e24d84b89");
        for (String s : list) {
            ZimsClient.sendEmail(s);
        }

    }
}
