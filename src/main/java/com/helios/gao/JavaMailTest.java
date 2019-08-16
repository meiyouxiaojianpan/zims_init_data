package com.helios.gao;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPStore;
import org.springframework.mail.javamail.MimeMailMessage;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.search.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
*@author : gaozhiwen
*@date : 2019/6/13
*
*/
public class JavaMailTest {

    public static void main(String[] args) throws MessagingException {
        new JavaMailTest().qq();
    }

    private void qq() throws MessagingException {

        String email = "ccsvc@message.cmbchina.com";

        Properties properties = new Properties();
//        properties.setProperty("mail.imap.host", "imap.qq.com");
//        properties.setProperty("mail.imap.port", "993");
        properties.setProperty("mail.imap.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        properties.setProperty("mail.transport.protocol", "imap");


        Session session = Session.getDefaultInstance(properties);
        Store store = session.getStore("imap");
        store.connect("imap.qq.com", "962697499@qq.com", "tuxyvnighlqhbdcf");
        //INBOX 收件箱
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_ONLY);
        System.out.println("未读邮件数量" + folder.getUnreadMessageCount());

        InternetAddress internetAddress = new InternetAddress();
        internetAddress.setAddress("crv_fapiao@crv.com.cn");

        FromTerm fromTerm = new FromTerm(internetAddress);

        RecipientTerm recipientTerm = new RecipientTerm(Message.RecipientType.TO, internetAddress);

        FlagTerm flagTerm = new FlagTerm(new Flags(Flags.Flag.SEEN), false);

        SearchTerm searchTerm = new SearchTerm() {
            @Override
            public boolean match(Message message) {
                try {
                    Address[] fromAddress = message.getFrom();
                    if (fromAddress != null && fromAddress.length > 0) {
                        if (fromAddress[0].toString().contains(email)) {
                            return true;
                        }
                    }
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
                return false;
            }
        };

        AndTerm andTerm = new AndTerm(searchTerm, flagTerm);

        long currentTimeMillis = System.currentTimeMillis();
        Message[] messages = folder.search(flagTerm);
        System.out.println(System.currentTimeMillis() - currentTimeMillis);
        System.out.println(messages.length);
        List<Message> messageList = this.match(messages, email);
        System.out.println(messageList.size());
        System.out.println(System.currentTimeMillis() - currentTimeMillis);

        folder.close(false);
        store.close();
    }

    private void huilianyi() {

    }

    private List<Message> match(Message[] messages, String from) {
        List<Message> messageList = new ArrayList<>();
        try {
            for (Message message : messages) {
                Address[] fromAddress = message.getFrom();
                if (fromAddress != null && fromAddress.length > 0) {
                    if (fromAddress[0].toString().contains(from)) {
                        messageList.add(message);
                    }
                }

            }
        } catch (MessagingException e) {
            e.printStackTrace();
        }
        return messageList;
    }
}
