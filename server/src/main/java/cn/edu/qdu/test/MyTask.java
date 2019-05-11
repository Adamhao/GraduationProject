package cn.edu.qdu.test;

/**
 * Created by Adam on 2019/4/27 16:53.
 */
public class MyTask implements Runnable{

    private int taskId;
    private String taskName;

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public MyTask() {
    }

    public MyTask(int taskId, String taskName) {
        this.taskId = taskId;
        this.taskName = taskName;
    }

    @Override
    public void run() {

        System.out.println("taskId:" + taskId + ",taskName:" + taskName);
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
