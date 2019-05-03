package cn.edu.qdu.email;

import cn.edu.qdu.exception.SendMailException;
import cn.edu.qdu.util.SendEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.net.URL;
import java.util.UUID;

/**
 * Created by Adam on 2019/5/2 11:29.
 */
public class SendEmail implements Runnable,Comparable<SendEmail>{

    private EmailTask emailTask;

    private static SendEmailUtil sendEmailUtil = SendEmailUtil.getSingleton();

    private static final Logger logger = LoggerFactory.getLogger(SendEmail.class);

    public SendEmail(EmailTask emailTask) {
        this.emailTask = emailTask;
    }

    @Override
    public void run() {

        try {
            OhMyEmail ohMyEmail = sendEmailUtil.getSender()
                    .subject(emailTask.getSubject())
                    .from("学期项目管理员")
                    .to(emailTask.getRecipient());
            if(emailTask.isHtml()) {
                ohMyEmail.html(emailTask.getContent());
            } else {
                ohMyEmail.text(emailTask.getContent());
            }
            if(emailTask.getAttachURLFile() != null && emailTask.getAttachURLFile().length != 0) {
                for(URL url : emailTask.getAttachURLFile()) {
                    //随机生成文件名，
                    ohMyEmail.attachURL(url, UUID.randomUUID().toString());
                }
            }
            synchronized(emailTask.getRecipient()) {
                if(sendEmailUtil.getSendTimesMap().getOrDefault(emailTask.getRecipient(),0) < sendEmailUtil.MAX_SEND_TIME) {
                    ohMyEmail.send();
                    sendEmailUtil.getSendTimesMap().put(emailTask.getRecipient(),
                            sendEmailUtil.getSendTimesMap().getOrDefault(emailTask.getRecipient(),0) + 1);
                    logger.info("邮件发送成功,收件人:" + emailTask.getRecipient() + " ; 邮件内容:" + emailTask.getContent() + " ; 邮件主题:" + emailTask.getRecipient());
                }
            }
        } catch (SendMailException e) {
            logger.error("邮件发送失败" + e.getMessage() + "收件地址:" + emailTask.getRecipient());
            //e.printStackTrace();
        }
    }

    @Override
    public int compareTo(SendEmail obj) {
        EmailTask emailTask1 = this.emailTask;
        EmailTask emailTask2 = obj.emailTask;
        if(emailTask1.getLevel() != emailTask2.getLevel()) {
            return emailTask2.getLevel() - emailTask1.getLevel();
        }
        if(emailTask1.getTimestamp() != emailTask2.getTimestamp()) {
            return (int)(emailTask1.getTimestamp() - emailTask2.getTimestamp());
        }
        return 0;
    }
}
