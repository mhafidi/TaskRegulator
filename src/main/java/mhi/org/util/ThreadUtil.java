package mhi.org.util;

public class ThreadUtil
{
    public static void waitTask(long timeToSleep)
    {
        try
        {
            Thread.sleep(timeToSleep);
        }
        catch (InterruptedException e)
        {
            System.out.println(e.getMessage());
        }
    }
}
