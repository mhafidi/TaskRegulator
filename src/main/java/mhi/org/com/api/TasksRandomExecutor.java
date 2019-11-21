package mhi.org.com.api;

import mhi.org.com.core.TasksManager;
import mhi.org.util.ThreadUtil;

public class TasksRandomExecutor implements Runnable
{
    TasksManager tasksManager;
    long timeBeforeExecutingATask;
    boolean stopped;

    public TasksRandomExecutor(TasksManager tasksManager, long timeBeforeExecutingATask)
    {

        this.tasksManager = tasksManager;
        this.timeBeforeExecutingATask = timeBeforeExecutingATask;
    }

    @Override
    public void run()
    {
        while(!stopped)
        {

            if(tasksManager.submitARandomTask())
            {
                ThreadUtil.waitTask(timeBeforeExecutingATask);
            }
        }
    }

    public boolean isStopped()
    {

        return stopped;
    }

    public void setStopped(boolean stopped)
    {

        this.stopped = stopped;
    }
}
