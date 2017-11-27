package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

/**
 * Created by Brian Towne on 10/30/2017.
 */

public class Hardware
{

    /* Public OpMode members. */

    // Base Motors
    public DcMotor FR = null; // Front Right Motor
    public DcMotor FL = null; // Front Left Motor
    public DcMotor BR = null; // Back Right Motor
    public DcMotor BL = null; // Back Left Motor

    // Arm Motors
    public DcMotor Swing = null; // Shoulder Joint of Arm
    public DcMotor Elbow = null; // Elbow Joint of Arm

    // Arm Servos
    public Servo Pivot = null; // Pivot of Arm
    public Servo Claw = null; // End Effector of Arm

    // Mirror Servo
    // Servo to rotate mirror below camera
    public Servo Mirror = null;


    /* local OpMode members. */

    HardwareMap hwMap           =  null; // Hardware Map of Robot

    public ElapsedTime clock  = new ElapsedTime(); // Internal Robot Time (IRT)
    public Timer correctionTimer = new Timer(1000);


    /* Constructor */
    public Hardware(){}

    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap)
    {
        // Save reference to Hardware map
        hwMap = ahwMap;

        // Define and Initialize Motors

        // Base Motors
        FR = hwMap.get(DcMotor.class, "FR");
        FL = hwMap.get(DcMotor.class, "FL");
        BR = hwMap.get(DcMotor.class, "BR");
        BL = hwMap.get(DcMotor.class, "BL");

        FR.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        FL.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors
        BR.setDirection(DcMotor.Direction.FORWARD); // Set to REVERSE if using AndyMark motors
        BL.setDirection(DcMotor.Direction.REVERSE);// Set to FORWARD if using AndyMark motors

        // Arm Motors
        Swing = hwMap.get(DcMotor.class, "swing");
        //Elbow = hwMap.get(DcMotor.class, "elbow");

        // Set all motors to zero power
        FR.setPower(0);
        FL.setPower(0);
        BR.setPower(0);
        BL.setPower(0);

        Swing.setPower(0);
        //Elbow.setPower(0);


        // Set Motors to Run with or Without Encoders
        FR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        Swing.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        //Elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        // Define and initialize ALL installed servos.
        Pivot = hwMap.get(Servo.class, "pivot");
        Claw  = hwMap.get(Servo.class, "claw");

        //Mirror = hwMap.get(Servo.class, "mirror");

        Pivot.setPosition(0);
        Claw.setPosition(0);

        //Mirror.setPosition(0);
    }

}
