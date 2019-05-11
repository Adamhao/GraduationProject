package cn.edu.qdu.test;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Adam on 2019/4/27 17:05.
 */
public class MyRejected implements RejectedExecutionHandler{

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        MyTask task = (MyTask) r;
        System.out.println("报警信息："+task.getTaskName()+" 被线程池拒绝，没有被执行");
        //可以往消息队列中间件里面放 可以发Email等等
    }
}
