import mhi.org.com.core.MyTaskToBeDone;
import mhi.org.com.core.TaskType;
import mhi.org.util.JavaNumberUtils;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestTaskTobeDone
{
    @Test
    public void testTaskTobeDoneNotNull()
    {
        MyTaskToBeDone myTaskToBeDone = new MyTaskToBeDone(System.nanoTime(),"TASK-1", TaskType.ping);
        assertNotNull(myTaskToBeDone);
    }
    @Test
    public void testTaskTobeDoneNotEqual()
    {
        MyTaskToBeDone myTaskToBeDone1 = new MyTaskToBeDone(System.nanoTime(),"TASK-1", TaskType.ping);
        MyTaskToBeDone myTaskToBeDone2 = new MyTaskToBeDone(System.nanoTime(),"TASK-1", TaskType.getPropety);

        assertNotEquals(myTaskToBeDone1,myTaskToBeDone2);

    }
    @Test
    public void testTaskTobeDoneEqual()
    {
        MyTaskToBeDone myTaskToBeDone1 = new MyTaskToBeDone(System.nanoTime(),"TASK-1", TaskType.ping);
        MyTaskToBeDone myTaskToBeDone2 = new MyTaskToBeDone(System.nanoTime(),"TASK-1", TaskType.ping);

        assertEquals(myTaskToBeDone1,myTaskToBeDone2);
    }

    @Test
    public void testNumberGeneration()
    {
        int[] nums= JavaNumberUtils.getStreamOfRandomIntsWithRange(10,1,10);
        for(int i=0;i<nums.length;i++)
        {
        //    System.out.print(nums[i]+" ");
        }
        System.out.println(TaskType.getTaskType(JavaNumberUtils.getRandomIntBetween(1,4)));
    }

}
