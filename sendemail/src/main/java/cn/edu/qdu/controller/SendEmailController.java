package cn.edu.qdu.controller;

import cn.edu.qdu.email.EmailTask;
import cn.edu.qdu.model.HttpClientResult;
import cn.edu.qdu.util.SendEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


/**
 * Created by Adam on 2019/5/3 12:32.
 */
@Controller
@RequestMapping("/api/sendEmail")
public class SendEmailController {

    private static SendEmailUtil sendEmailUtil = SendEmailUtil.getSingleton();

    private static final Logger logger = LoggerFactory.getLogger(SendEmailController.class);

    @PostMapping("/send")
    @ResponseBody
    public HttpClientResult send(@RequestBody Map<String, String> params) {
        //String subject, String recipient, String content
        if (params.get("subject") == null) {
            return new HttpClientResult(500, "发送失败,标题为空");
        }
        if (params.get("recipient") == null) {
            return new HttpClientResult(500, "发送失败,接收人为空");
        }
        if (params.get("content") == null) {
            return new HttpClientResult(500, "发送失败,内容为空");
        }
        String subject = params.get("subject");
        String recipient = params.get("recipient");
        String content = params.get("content");

        EmailTask emailTask = new EmailTask(subject, recipient, content);
        try {
            sendEmailUtil.send(emailTask);
            return new HttpClientResult(200, "加入发送队列成功");
        } catch (Exception e) {
            logger.error("收件人:" + recipient + ";主题:" + subject + ";错误原因:" + e.getMessage());
            return new HttpClientResult(500, "邮件发送失败:" + e.getMessage());
        }
    }

    @PostMapping("/resetSendTimes")
    @ResponseBody
    public HttpClientResult reset() {
        try {
            sendEmailUtil.resetSendTimesMap();
            return new HttpClientResult(200, "清空发送次数成功");
        } catch (Exception e) {
            logger.error("重置邮件发送次数失败:" + e.getMessage());
            return new HttpClientResult(500, "重置邮件发送次数失败:" + e.getMessage());
        }
    }
}
