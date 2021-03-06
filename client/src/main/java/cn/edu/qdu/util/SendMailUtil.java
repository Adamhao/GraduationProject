package cn.edu.qdu.util;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class SendMailUtil {
    public static void sendEmail(String email,String context) throws MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置发件人邮箱地址
        message.setFrom(new InternetAddress("372558609@qq.com"));
        // 设置收件人邮箱地址
        //message.setRecipients(Message.RecipientType.TO, new InternetAddress[]{new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com"),new InternetAddress("xxx@qq.com")});
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));//一个收件人
        // 设置邮件标题
        message.setSubject("xmqtest");
        // 设置邮件内容
        Multipart mp = new MimeMultipart();
        MimeBodyPart mbp = new MimeBodyPart();
        mbp.setContent(context,"text/html;charset=GB2312");
        mp.addBodyPart(mbp);
        message.setContent(mp);
//        message.setText(context);
        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("372558609@qq.com", "xpnzixqyiwnmcabh");// 密码为QQ邮箱开通的stmp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
