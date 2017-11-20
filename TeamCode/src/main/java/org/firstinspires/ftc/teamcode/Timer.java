package org.firstinspires.ftc.teamcode;

/**
 * Created by aircr on 11/20/2017.
 */

public class Timer extends Thread
{
    public int time;

    public boolean isDone;

    public Timer (int millis)
    {
        time = millis;
        isDone = false;
    }

    @Override
    public void run()
    {
        isDone = false;
        try {
            Thread.sleep(time);
        } catch ( InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        isDone = true;
    }
}