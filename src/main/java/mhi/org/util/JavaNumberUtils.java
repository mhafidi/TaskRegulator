package mhi.org.util;

import java.util.Random;

public class JavaNumberUtils
{

    public static long getRandomLongBetween(long x, long y)
    {

        Random r = new Random();
        return x + ((long) (r.nextDouble() * (y - x)));
    }
    public static int getRandomIntBetween(int x, int y)
    {

        Random r = new Random();
        return x + ((int) (r.nextDouble() * (y - x)));
    }
    public static double getRandomDoubleNumberBetween(double aInMin, double aInMax)
    {

        double x;
        x = (Math.random() * (aInMax - aInMin)) + aInMin;
        return x;
    }

    public static int[] getStreamOfRandomIntsWithRange(int num, int min, int max)
    {

        Random random = new Random();
        return random.ints(num, min, max).sorted().toArray();
    }
    public static double[] getStreamOfRandomDoublesWithRange(int num, double min, double max)
    {
        Random random = new Random();
        return random.doubles(num,min,max).sorted().toArray();
    }
    public static int getMinNumber(int alpha,int beta)
    {
        return Math.min(alpha,beta);
    }
}
