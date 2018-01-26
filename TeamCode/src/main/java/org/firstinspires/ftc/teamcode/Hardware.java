package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.ColorSensor;


/**
 * Created by Brian Towne on 10/30/2017.
 */


public class Hardware
{
    /* Public OpMode members. */

    /* Motors */
    // Base Motors
    public DcMotor FR = null; // Front Right Motor
    public DcMotor FL = null; // Front Left Motor
    public DcMotor BR = null; // Back Right Motor
    public DcMotor BL = null; // Back Left Motor
    // Winch Motor, used to extend the Pulley Arm
    public DcMotor Winch = null;
    // Forklift Motor, used to raise/lower the Forklift
  //  public DcMotor Lift = null;

    /* Servos */
    // Color Sensor Arm Servo, used to raise/lower the arm
    public Servo ColorArm = null;
    // Grip Servo on Forklift, used to capture glyphs
    public Servo Grip = null;
    // End Effectors of Pulley Arm, used to control the Relic
    public Servo Claw = null;
    public Servo Wrist = null;

    /* Sensors */
    // Color Sensor, used to detect which jewel to knock off
    ColorSensor Color = null;


    /* local OpMode members. */

    HardwareMap hwMap           =  null; // Hardware Map of Robot
    ElapsedTime clock  = new ElapsedTime(); // Internal Robot Time (IRT)


    /* Constructor */
    public Hardware(){}


    /* Initialize standard Hardware interfaces */
    public void init(HardwareMap ahwMap)
    {
        /* Save reference to Local Hardware map */
        hwMap = ahwMap;


        /* Define and Initialize Motors */

        // Declare Hardware ID
        FR = hwMap.get(DcMotor.class, "FL");
        FL = hwMap.get(DcMotor.class, "BL");
        BR = hwMap.get(DcMotor.class, "FR");
        BL = hwMap.get(DcMotor.class, "BR");
        Winch = hwMap.get(DcMotor.class, "Winch");
      //  Lift = hwMap.get(DcMotor.class, "Lift");
        // Set Motor's Directions
        FR.setDirection(DcMotor.Direction.FORWARD);     // Set to REVERSE if using AndyMark motors
        FL.setDirection(DcMotor.Direction.REVERSE);     // Set to FORWARD if using AndyMark motors
        BR.setDirection(DcMotor.Direction.FORWARD);     // Set to REVERSE if using AndyMark motors
        BL.setDirection(DcMotor.Direction.REVERSE);     // Set to FORWARD if using AndyMark motors
        Winch.setDirection(DcMotorSimple.Direction.FORWARD);    // Set to REVERSE if using AndyMark motors
       // Lift.setDirection(DcMotorSimple.Direction.FORWARD);     // Set to REVERSE if using AndyMark motors
        // Set Motors to Run with or Without Encoders
        FR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        Winch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      //  Lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);


        /* Define and Initialize Servos */

        // Declare Hardware ID
        ColorArm = hwMap.get(Servo.class, "ColorArm");
        Claw = hwMap.get(Servo.class, "Claw");
        Wrist = hwMap.get(Servo.class, "Wrist");
        Grip = hwMap.get(Servo.class, "Grip");
        // Set Servo's Initial Positions
        ColorArm.setPosition(0.5);
        Claw.setPosition(1);
        Wrist.setPosition(1);
        Grip.setPosition(0);


        /* Define and Initialize Sensors */

        // Declare Hardware ID
        Color = hwMap.get(ColorSensor.class, "Color");


    }

}
