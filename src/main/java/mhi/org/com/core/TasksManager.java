package mhi.org.com.core;

import mhi.org.util.ThreadUtil;

import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.log4j.Logger;

public class TasksManager implements Runnable
{
    private ConcurrentHashMap<MyTaskToBeDone,Long> tasksAndRegistrationTime;
    int maxQueueSize;
    long taskTimoutDrop;
    boolean stopped=false;
    static Logger logger = Logger.getLogger(TasksManager.class);
    public TasksManager(int maxQueueSize,long taskTimoutDrop)
    {
        this.maxQueueSize = maxQueueSize;
        tasksAndRegistrationTime= new ConcurrentHashMap<>();
        this.taskTimoutDrop=taskTimoutDrop;
    }

    public void addTask(MyTaskToBeDone aInMyTaskToBeDone)
    {
        if(tasksAndRegistrationTime.size()<maxQueueSize)
        {
            //do not need to check if the task exists in the concurrent hashmap due to
            //method override
            tasksAndRegistrationTime.put(aInMyTaskToBeDone,System.currentTimeMillis());
        }
    }

    @Override
    public void run()
    {
        long startTime= System.currentTimeMillis();
        while(!stopped)
        {
            ThreadUtil.waitTask(1000);
            if(System.currentTimeMillis()-startTime>20000)
            {
                startTime= System.currentTimeMillis();
                logger.info("Tasks Queue Occupation ["+tasksAndRegistrationTime.size()+"]");
            }
            for(MyTaskToBeDone myTaskToBeDone:tasksAndRegistrationTime.keySet())
            {
                if(myTaskToBeDone.isTerminated() || timeOutTask(myTaskToBeDone))
                {
                    if(myTaskToBeDone.isStarted())
                    {
                        logger.warn("The following task["+myTaskToBeDone+"] will be dropped due to timeOut ");
                    }
                    else
                    {
                        logger.info("The following task["+myTaskToBeDone+"] is terminated");
                    }

                    tasksAndRegistrationTime.remove(myTaskToBeDone);

                }
            }


        }
    }

    private boolean timeOutTask(MyTaskToBeDone myTaskToBeDone)
    {
        long registrationTime=tasksAndRegistrationTime.get(myTaskToBeDone);
        long diff = System.currentTimeMillis() - registrationTime;
        if(diff >taskTimoutDrop)
        {
            logger.info("Task Time out Task info["+myTaskToBeDone+"]");
            return true;
        }
        return false;
    }

    public void setStopped(boolean stopped)
    {
        this.stopped = stopped;
    }

    public boolean submitARandomTask()
    {
        if(tasksAndRegistrationTime.size()>0)
        {
            Object[] crunchifyKeys =  tasksAndRegistrationTime.keySet().toArray();
            Object crunchTask = crunchifyKeys[new Random().nextInt(crunchifyKeys.length)];
            MyTaskToBeDone randomTask=(MyTaskToBeDone)crunchTask;

            if (randomTask.isStarted() || randomTask.isTerminated())
                return false;
            else
            {
                logger.info("The Following Task[" + randomTask + "] is submitted");
                MainThreadsExecutor.submit(MyTaskToBeDone.class.getName(), randomTask);
                return true;
            }
        }
        return false;

    }
}
