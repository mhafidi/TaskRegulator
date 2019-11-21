package mhi.org.com;

import mhi.org.com.api.TasksRandomExecutor;
import mhi.org.com.api.TasksRandomGenerator;
import mhi.org.com.core.MainThreadsExecutor;
import mhi.org.com.core.TasksManager;
import mhi.org.util.ThreadUtil;

public class Application
{
    private static final int TIMER_TO_FILL = 15000;
    private static final int TIME_BEFORE_EXECUTING_A_TASK = 30000;
    private static final int TASK_TIMOUT_DROP = 50000;
    static TasksManager tasksManager;
    static TasksRandomGenerator tasksRandomGenerator;
    static TasksRandomExecutor tasksRandomExecutor;
    final static long maxTime=5*60000;


    static void init()
    {

        tasksManager= new TasksManager(10, TASK_TIMOUT_DROP);
        tasksRandomGenerator = new TasksRandomGenerator(tasksManager,1,20, TIMER_TO_FILL);
        tasksRandomExecutor = new TasksRandomExecutor(tasksManager, TIME_BEFORE_EXECUTING_A_TASK);


    }
    static public void main(String[] args)
    {
        init();
        long startTime=System.currentTimeMillis();
        MainThreadsExecutor.submit(TasksManager.class.getName(),tasksManager);
        MainThreadsExecutor.submit(TasksRandomGenerator.class.getName(),tasksRandomGenerator);
        MainThreadsExecutor.submit(TasksRandomExecutor.class.getName(),tasksRandomExecutor);
        while(System.currentTimeMillis()-startTime<maxTime)
        {
            ThreadUtil.waitTask(5000);
        }
        System.out.println("stopping all tasks");
        tasksRandomGenerator.setTerminated(true);
        tasksRandomExecutor.setStopped(true);
        tasksManager.setStopped(true);
        System.out.println("shutdown MainExecutorService");
        MainThreadsExecutor.closeAllThreads();



    }
}
