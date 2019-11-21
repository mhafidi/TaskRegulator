package mhi.org.com.core;

import mhi.org.util.JavaNumberUtils;
import org.apache.log4j.Logger;

public class MyTaskToBeDone extends Object implements Runnable
{
    long idNanoSecond;
    String taskID;
    TaskType taskType;
    boolean terminated =false;
    boolean started= false;
    static Logger logger = Logger.getLogger(MyTaskToBeDone.class);
    public MyTaskToBeDone(long idNanoSecond, String taskID, TaskType taskType)
    {

        this.idNanoSecond = idNanoSecond;
        this.taskID = taskID;
        this.taskType = taskType;
    }

    public void run()
    {
        started=true;
        long randomWorkingTime= JavaNumberUtils.getRandomLongBetween(4000,120000);
        long startTime=System.currentTimeMillis();
        long diff = System.currentTimeMillis() - startTime;
        logger.info("Task["+this+"] started it will last["+randomWorkingTime/1000+"] seconds");

        while(diff <randomWorkingTime)
        {
            diff = System.currentTimeMillis() - startTime;

        }
        terminated =true;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof MyTaskToBeDone)) return false;

        MyTaskToBeDone that = (MyTaskToBeDone) o;

        if (taskID != null ? !taskID.equals(that.taskID) : that.taskID != null) return false;
        return taskType == that.taskType;
    }

    public boolean isTerminated()
    {

        return terminated;
    }

    @Override
    public String toString()
    {

        return "MyTaskToBeDone{" +
                "idNanoSecond=" + idNanoSecond +
                ", taskID='" + taskID + '\'' +
                ", taskType=" + taskType +
                '}';
    }

    public boolean isStarted()
    {

        return started;
    }

    public void setTerminated(boolean terminated)
    {

        this.terminated = terminated;
    }
}
