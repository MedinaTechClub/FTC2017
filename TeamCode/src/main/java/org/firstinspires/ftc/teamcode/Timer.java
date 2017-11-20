package org.firstinspires.ftc.teamcode;

/**
 * Created by Brian Towne on 11/20/2017.
 */

public class Timer extends Thread
{
    /**
     * !!! This classes uses Multithreading. !!!
     *
     * This class is used to create a timer for the robot.
     * It will push this task onto another thread so it will not interfere with the main functions
     *
     * Check VuforiaNavigation for use.
     */

    public int time; // The amount of time to elapse

    public boolean isDone; // To check if the timer is done

    // Constructor
    public Timer (int millis)
    {
        time = millis;
        isDone = false;
    }

    // This is what will run when Timer.start() is ran.
    @Override
    public void run()
    {
        isDone = false; // Make sure the timer is not done

        try {
            Thread.sleep(time); // Make the thread sleep for the allotted time.
        } catch ( InterruptedException e) {
            Thread.currentThread().interrupt(); // Make sure the thread is interrupted for the full time.
        }
        isDone = true; // Make the timer done.
    }
}