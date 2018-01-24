package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.Range;

@TeleOp(name="TeleOp: 1", group="TeleOp")
public class TeleOp1 extends OpMode
{

    /* Declare OpMode members. */
    Hardware robot   = new Hardware();

    double position;

    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        /* Initialize the hardware variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);
        robot.FL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.FL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
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

        //double armRot = robot.Pivot.getPosition();

        double deadzone = 0.2;

        double trnSpdMod = 0.5;

        float xValueRight = gamepad1.right_stick_x;
        float yValueLeft = -gamepad1.left_stick_y;

        xValueRight = Range.clip(xValueRight, -1, 1);
        yValueLeft = Range.clip(yValueLeft, -1, 1);

        // Pressing "A" opens the claw
        //if (gamepad1.a) {

            //robot.Claw.setPosition(.4);

            // Pressing "B" closes the claw
        //} else if (gamepad1.b) {

            //robot.Claw.setPosition(0);
        //}

        // Turn left/right, overrides forward/back
        if (Math.abs(xValueRight) > deadzone) {

            robot.FL.setPower(xValueRight * trnSpdMod);
            robot.FR.setPower(-xValueRight * trnSpdMod);
            robot.BL.setPower(xValueRight * trnSpdMod);
            robot.BR.setPower(-xValueRight * trnSpdMod);


        } else {//Forward/Back On Solely Left Stick
            if (Math.abs(yValueLeft) > deadzone) {
                robot.FL.setPower(yValueLeft);
                robot.FR.setPower(yValueLeft);
                robot.BL.setPower(yValueLeft);
                robot.BR.setPower(yValueLeft);
            }
            robot.FL.setPower(0);
            robot.FR.setPower(0);
            robot.BL.setPower(0);
            robot.BR.setPower(0);
        }


        telemetry.addData("Encoder ticks", robot.FL.getCurrentPosition());
        /*
        // THIS IS THE OLD STUFF I DON'T LIKE IT I WONT USE IT!
        // Group a is Front Left and Rear Right, Group b is Front Right and Rear Left
        float a;
        float b;
        float turnPower;
        if(!gamepad1.x) {
            if (Math.abs(xValueRight) <= deadzone && Math.abs(yValueRight) <= deadzone) {
                // And is used here because both the y and x values of the right stick should be less than the deadzone

                a = Range.clip(yValueLeft + xValueLeft, -1, 1);
                b = Range.clip(yValueLeft - xValueLeft, -1, 1);


                robot.FL.setPower(a);
                robot.FR.setPower(b);
                robot.BL.setPower(b);
                robot.BR.setPower(a);

                telemetry.addData("a", "%.2f", a);
                telemetry.addData("b", "%.2f", b);

            } else if (Math.abs(xValueRight) > deadzone || Math.abs(yValueRight) > deadzone) {

                // Or is used here because only one of the y and x values of the right stick needs to be greater than the deadzone
                turnPower = Range.clip(xValueRight, -1, 1);

                robot.FL.setPower(-turnPower);
                robot.FR.setPower(turnPower);
                robot.BL.setPower(-turnPower);
                robot.BR.setPower(turnPower);

            } else {

                robot.FL.setPower(0);
                robot.FR.setPower(0);
                robot.BL.setPower(0);
                robot.BR.setPower(0);
            }

        } else {

            if (Math.abs(xValueRight) <= deadzone && Math.abs(yValueRight) <= deadzone) {

                // And is used here because both the y and x values of the right stick should be less than the deadzone
                a = Range.clip(yValueLeft + xValueLeft, -0.6f, 0.6f);
                b = Range.clip(yValueLeft - xValueLeft, -0.6f, 0.6f);


                robot.FL.setPower(a);
                robot.FR.setPower(b);
                robot.BL.setPower(b);
                robot.BR.setPower(a);

                telemetry.addData("a", "%.2f", a);
                telemetry.addData("b", "%.2f", b);

            } else if (Math.abs(xValueRight) > deadzone || Math.abs(yValueRight) > deadzone) {

                // Or is used here because only one of the y and x values of the right stick needs to be greater than the deadzone
                turnPower = Range.clip(xValueRight, -1, 1);

                robot.FL.setPower(-turnPower);
                robot.FR.setPower(turnPower);
                robot.BL.setPower(-turnPower);
                robot.BR.setPower(turnPower);

            } else {

                robot.FL.setPower(0);
                robot.FR.setPower(0);
                robot.BL.setPower(0);
                robot.BR.setPower(0);
            }
        }


        if (gamepad1.dpad_up) {

            robot.Swing.setPower(.6);

        } else if (gamepad1.dpad_down) {

            robot.Swing.setPower(-.6);

        } else {

            robot.Swing.setPower(0);
        }

        if(gamepad1.a){

            robot.Claw.setPosition(.4);

        } else if(gamepad1.b){

            robot.Claw.setPosition(0);
        }

        if(gamepad1.left_bumper) {

            robot.Pivot.setPosition(armRot+0.0005);

        } else if(gamepad1.right_bumper) {

            robot.Pivot.setPosition(armRot-0.0005);

        } else{

            robot.Pivot.setPosition(armRot);
        }

        telemetry.addData("position", position);

    */
    /*
     * Code to run ONCE after the driver hits STOP
     */
    }
    @Override
    public void stop() {
    }
}

