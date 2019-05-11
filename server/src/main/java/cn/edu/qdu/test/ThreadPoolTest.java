package cn.edu.qdu.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Adam on 2019/4/27 16:12.
 */
public class ThreadPoolTest {

    public static void main(String[] args) throws InterruptedException {

        ThreadPoolExecutor pool = new ThreadPoolExecutor(// 自定义一个线程池
                1, // coreSize
                2, // maxSize
                60, // 60s
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3) // 有界队列，容量是3个
                , Executors.defaultThreadFactory()
                , new ThreadPoolExecutor.AbortPolicy()
        );

        /*ThreadPoolExecutor pool = new ThreadPoolExecutor(// 自定义一个线程池
                1, // coreSize
                2, // maxSize
                60, // 60s
                TimeUnit.SECONDS, new ArrayBlockingQueue<>(3) // 有界队列，容量是3个
                , Executors.defaultThreadFactory()
                , new MyRejected()
        );*/

        pool.execute(new MyTask(1, "任务1"));
        System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());

        pool.execute(new MyTask(2, "任务2"));
        System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());

        pool.execute(new MyTask(3, "任务3"));
        System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());

        pool.execute(new MyTask(4, "任务4"));
        System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());

        pool.execute(new MyTask(5, "任务5"));
        System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());

        try {
            pool.execute(new MyTask(6, "任务6"));
            System.out.println("活跃的线程数："+pool.getActiveCount() + ",核心线程数：" + pool.getCorePoolSize() + ",线程池大小：" + pool.getPoolSize() + ",队列的大小" + pool.getQueue().size());
        } catch(Exception e) {
            e.printStackTrace();
        }

        pool.shutdown();

    }
}

