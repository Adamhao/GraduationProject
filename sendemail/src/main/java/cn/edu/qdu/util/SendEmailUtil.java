package cn.edu.qdu.util;

import cn.edu.qdu.email.EmailTask;
import cn.edu.qdu.email.OhMyEmail;
import cn.edu.qdu.email.SendEmail;
import cn.edu.qdu.exception.SendTooManyTimesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 发送邮件的工具类
 * Created by Adam on 2019/4/30 15:32.
 */
public class SendEmailUtil {

    private static final int CORE_SIZE = 10;//线程池核心线程数
    private static final int MAX_RUNNING = 100;//线程池最大运行数
    private static final int MAX_WAITING = 1000;//线程池最大队列数
    private static final int KEEP_ALIVE_TIME = 30;//空闲线程的最大存活时间

    public static final int MAX_SEND_TIME = 1;//一天内最大邮件发送次数
    private static Set<String> blackList;//黑名单(ip地址或者用户id)
    private static Map<String, Integer> sendTimesMap;//用户当天发送邮件次数
    //private static ArrayBlockingQueue<EmailTask> waitingQueue;//等待队列
    private static ThreadPoolExecutor threadPoolExecutor;//线程池

    private static final String usernameAndPass = "NzUyMTA0NDg2QHFxLmNvbSx2em10Z3piamRwZGRiZWZmO2FkYW1fc3VuaGFvQDE2My5jb20sc3VuMTgwNw==";
    private static List<OhMyEmail> senderList;//邮件发送账号集合

    private static volatile SendEmailUtil singleton = null;

    private static int index = 0;

    private static final Logger logger = LoggerFactory.getLogger(SendEmailUtil.class);

    //初始化变量
    static {
        blackList = new HashSet<>();
        sendTimesMap = new HashMap<>();
        senderList = new ArrayList<>();
        //waitingQueue = new ArrayBlockingQueue<EmailTask>(3000);
        //创建线程池
        threadPoolExecutor = new ThreadPoolExecutor(CORE_SIZE, // coreSize
                MAX_RUNNING, // maxSize
                KEEP_ALIVE_TIME, //空闲线程最大存活时间
                TimeUnit.SECONDS, new PriorityBlockingQueue<>(MAX_WAITING) // 无界队列
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy());
        initAdminList();
        logger.info("SendEmailUtil初始化成功");
    }

    /*
    * 加单例模式其实也没有什么必要,只是想加 :)
    * */
    private SendEmailUtil(){}

    public static SendEmailUtil getSingleton(){
        if(singleton == null){
            synchronized (SendEmailUtil.class){
                if(singleton == null){
                    logger.info("SendEmailUtil创建新对象");
                    singleton = new SendEmailUtil();
                }
            }
        }
        return singleton;
    }

    public OhMyEmail getSender() {
        int random = (int) (Math.random() * senderList.size());
        System.out.println(senderList.size());
        return senderList.get(random);
    }

    public void send(EmailTask emailTask) {
        if(emailTask == null || emailTask.getRecipient() == null ||
                emailTask.getSubject() == null || emailTask.getContent() == null) {
            throw new NullPointerException("EmailTask Or It's Attribute is NULL");
        }
        if(sendTimesMap.getOrDefault(emailTask.getRecipient(),0) >= MAX_SEND_TIME) {
            throw new SendTooManyTimesException("发送邮件次数超过每日最大限制次数");
        }
        SendEmail sendEmail = new SendEmail(emailTask);
        threadPoolExecutor.execute(sendEmail);
    }

    public Map<String,Integer> getSendTimesMap() {
        return sendTimesMap;
    }

    public void resetSendTimesMap() {
        sendTimesMap.clear();
    }

    //初始化邮件发送账号密码
    private static void initAdminList() {
        String[] accounts = Base64Strategy.decode(usernameAndPass).split(";");
        for (String account : accounts) {
            String[] temp = account.split(",");
            //创建账号密码
            OhMyEmail email = new OhMyEmail();
            if(temp[0].contains("@163.com")) {
                email.config(email.SMTP_163(false),temp[0],temp[1]);
            } else if(temp[0].contains("@qq.com")) {
                email.config(email.SMTP_QQ(false),temp[0],temp[1]);
            }
            senderList.add(email);
        }
    }
}
