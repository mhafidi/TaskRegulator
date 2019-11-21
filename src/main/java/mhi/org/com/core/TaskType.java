package mhi.org.com.core;

public enum TaskType
{
    ping,
    upload,
    getPropety;

   static public TaskType getTaskType(int aInID)
    {
        switch (aInID)
        {
            case 1:
                return ping;
            case 2:
                return upload;
            default:
                    return getPropety;

        }
    }
}
