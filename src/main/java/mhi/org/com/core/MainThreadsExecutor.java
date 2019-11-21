package mhi.org.com.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import org.apache.log4j.Logger;

public class MainThreadsExecutor
{
    static Logger logger = Logger.getLogger(MainThreadsExecutor.class);
    private static final int CORE_POOL_SIZE=Integer.MAX_VALUE;
    static ExecutorService mainThreadService = Executors.newFixedThreadPool(CORE_POOL_SIZE);




    static public int getCorePoolUsage()
    {
        return ((ThreadPoolExecutor)mainThreadService).getActiveCount();
    }

    public static void submit(String aInClassName,Runnable aInTask)
    {
        if(logger.isDebugEnabled())
        {
            logger.debug("Class: "+aInClassName+" submitted task to the MainThreadsExecutor");
            logger.debug("Main Thread Pool Executor Usage is: ["+getCorePoolUsage()+"]");
        }
        mainThreadService.submit(aInTask);
    }
    static public void closeAllThreads()
    {
        mainThreadService.shutdown();
    }



}