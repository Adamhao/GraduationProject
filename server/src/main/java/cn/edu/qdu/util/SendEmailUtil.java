package cn.edu.qdu.util;

import cn.edu.qdu.email.OhMyEmail;

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

    private static final int MAX_SEND_TIME = 5;//一天内最大邮件发送次数
    private static Set<String> blackList;//黑名单(ip地址或者用户id)
    private static Map<String, Integer> sendTimesMap;//用户当天发送邮件次数
    //private static ArrayBlockingQueue<EmailTask> waitingQueue;//等待队列
    private static ThreadPoolExecutor threadPoolExecutor;//线程池

    private static final String usernameAndPass = "NzUyMTA0NDg2QHFxLmNvbSx2em10Z3piamRwZGRiZWZmO2FkYW1fc3VuaGFvQDE2My5jb20sc3VuMTgwNw==";
    private static List<OhMyEmail> senderList;//邮件发送账号集合

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
    }

    public static OhMyEmail getSender() {
        return null;
    }

    //初始化邮件发送账号密码
    private static void initAdminList() {
        String[] accounts = Base64Strategy.decode(usernameAndPass).split(";");
        for (String account : accounts) {
            String[] temp = account.split(",");
            //创建账号密码
            senderList.add(null);
        }
    }

}
