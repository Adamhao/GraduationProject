package cn.edu.qdu.config;

import cn.edu.qdu.util.SendEmailUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by Adam on 2019/5/2 10:54.
 */
//@Configuration
public class SendEmailConfig {

    //@Bean
    public SendEmailUtil getSendEmailUtil() {
        return SendEmailUtil.getSingleton();
    }

}
