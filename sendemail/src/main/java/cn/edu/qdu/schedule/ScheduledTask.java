package cn.edu.qdu.schedule;

import cn.edu.qdu.util.SendEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by Adam on 2019/5/3 21:34.
 */
@Component
public class ScheduledTask {

    private SendEmailUtil sendEmailUtil = SendEmailUtil.getSingleton();
    private static final Logger logger = LoggerFactory.getLogger(ScheduledTask.class);

    @Scheduled(cron = "0 0 0 * * ?")
    public void resetSendTimes() {
        try {
            sendEmailUtil.resetSendTimesMap();
            logger.info("每日重置发送邮件次数成功");
        } catch(Exception e) {
            try {
                Thread.sleep(300000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
            sendEmailUtil.resetSendTimesMap();
            logger.info("二次重置发送邮件次数成功");
        }

    }

}
