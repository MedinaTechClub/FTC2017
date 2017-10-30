package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name="TeleOp: 1", group="TeleOp")
public class TeleOp1 extends OpMode
{

    /* Declare OpMode members. */
    Hardware robot   = new Hardware();

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
    }

    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    @Override
    public void loop() {
        if (gamepad1.y){
            robot.Swing.setPower(1);
        }
        if (gamepad1.a){
            robot.Swing.setPower(-1);
        }
        if (gamepad1.dpad_up){
            robot.Elbow.setPower(1);
        }
        if (gamepad1.dpad_down){
            robot.Elbow.setPower(-1);
        }
        if(gamepad1.x){
            robot.Mirror.setPosition(180);
        }
        else{
            robot.Swing.setPower(0);
            robot.Elbow.setPower(0);
            robot.Mirror.setPosition(0);
        }
    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {
    }
}
