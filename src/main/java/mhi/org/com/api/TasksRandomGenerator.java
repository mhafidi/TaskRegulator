package mhi.org.com.api;

import mhi.org.com.core.MyTaskToBeDone;
import mhi.org.com.core.TaskType;
import mhi.org.com.core.TasksManager;
import mhi.org.util.JavaNumberUtils;
import mhi.org.util.ThreadUtil;

public class TasksRandomGenerator implements Runnable
{
    TasksManager tasksManager;
    int minId,maxId;
    long timerToFill;
    boolean terminated=false;



    public TasksRandomGenerator(TasksManager tasksManager, int minId,
                                int maxId, long timerToFill)
    {

        this.tasksManager = tasksManager;
        this.minId = minId;
        this.maxId = maxId;
        this.timerToFill=timerToFill;
    }

    @Override
    public void run()
    {
        String taskFullID;
        int[] taskIds;
        while(!terminated)
        {
            ThreadUtil.waitTask(timerToFill);
            taskIds= JavaNumberUtils.getStreamOfRandomIntsWithRange(10,minId,maxId);

            for(int i=0;i<taskIds.length;i++)
            {
                taskFullID=Constants.TASK_PREFIX+taskIds[i];
                tasksManager.addTask(new MyTaskToBeDone(System.nanoTime(),taskFullID,
                        TaskType.getTaskType(JavaNumberUtils.getRandomIntBetween(1,4))));
            }


        }
    }

    public boolean isTerminated()
    {

        return terminated;
    }

    public void setTerminated(boolean terminated)
    {

        this.terminated = terminated;
    }
}
