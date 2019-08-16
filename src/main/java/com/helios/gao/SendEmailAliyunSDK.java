package com.helios.gao;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dm.model.v20151123.SingleSendMailRequest;
import com.aliyuncs.dm.simple.Email;
import com.aliyuncs.dm.simple.MailSender;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.profile.DefaultProfile;


/**
*@author : gaozhiwen
*@date : 2019/4/28
*
*/
public class SendEmailAliyunSDK {
    public static void main(String[] args) {
        MailSender mailSender = null;
        SingleSendMailRequest request = new SingleSendMailRequest();
        DefaultProfile profile = DefaultProfile.getProfile();
        IAcsClient client = new DefaultAcsClient();
        //发信地址
        request.setAccountName("");
        //发信人
        request.setFromAlias("");
        request.setAddressType(1);
        //目标地址
        request.setToAddress("");
        //邮件主题
        request.setSubject("");
        //邮件正文
        request.setHtmlBody("");
        try {
            client.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
    }
}
