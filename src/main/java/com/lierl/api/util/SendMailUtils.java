package com.lierl.api.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class SendMailUtils {

    private static final String host = "smtp.163.com";
    private static final String username = "lierlei0515";
    private static final String password = "erlei0515";
    private static final String from = "lierlei0515@163.com";

    public static void sendMail(String to, String title,String content) throws Exception {
        Properties props = new Properties(); //可以加载一个配置文件
        // 使用smtp：简单邮件传输协议
        props.put("mail.smtp.host", host);//存储发送邮件服务器的信息
        props.put("mail.smtp.auth", "true");//同时通过验证

        Session session = Session.getInstance(props,
                new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
//        session.setDebug(true); //有他会打印一些调试信息。

        MimeMessage message = new MimeMessage(session);//由邮件会话新建一个消息对象
        message.setFrom(new InternetAddress(from));//设置发件人的地址

        message.setRecipient(Message.RecipientType.TO, new InternetAddress(to));//设置收件人,并设置其接收类型为TO

//        message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));//设置抄送

        message.setSubject(title);//设置标题
        //设置信件内容
//        message.setText(mailContent); //发送 纯文本 邮件 todo
        message.setContent(content, "text/html;charset=gbk"); //发送HTML邮件，内容样式比较丰富
        message.setSentDate(new Date());//设置发信时间
        message.saveChanges();//存储邮件信息

        //发送邮件
//        Transport transport = session.getTransport("smtp");
        Transport transport = session.getTransport("smtp");
        transport.connect(host,username, password);
        transport.sendMessage(message, message.getAllRecipients());//发送邮件,其中第二个参数是所有已设好的收件人地址
        transport.close();
    }
}
