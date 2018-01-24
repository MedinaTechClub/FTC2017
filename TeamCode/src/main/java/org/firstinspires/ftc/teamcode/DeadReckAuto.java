package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.vuforia.VIDEO_BACKGROUND_REFLECTION;
import com.vuforia.VideoBackgroundConfig;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.RelicRecoveryVuMark;
import org.firstinspires.ftc.robotcore.external.navigation.VuMarkInstanceId;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.robotcore.internal.vuforia.VuforiaPoseMatrix;

/**
 * Created by Brian Towne on 11/15/2017.
 */

@Autonomous(name="DR Auto", group ="Autonomous Testing")
public class DeadReckAuto extends LinearOpMode {

    /* Declare OpMode members. */
    Hardware robot = new Hardware();

    int firstRun = 2764;
    int turn = 3994;
    int secondRun = 5999;

    @Override
    public void runOpMode() {

        robot.init(hardwareMap);

        waitForStart();

        while(opModeIsActive()){
            while(robot.FL.getCurrentPosition() < firstRun){
                robot.FL.setPower(.5);
                robot.FR.setPower(.5);
                robot.BL.setPower(.5);
                robot.BR.setPower(.5);
            }
            robot.FL.setPower(0);
            robot.FR.setPower(0);
            robot.BL.setPower(0);
            robot.BR.setPower(0);
            while(robot.FL.getCurrentPosition() < turn){
                robot.FL.setPower(.5);
                robot.FR.setPower(-.5);
                robot.BL.setPower(.5);
                robot.BR.setPower(-.5);
            }
            robot.FL.setPower(0);
            robot.FR.setPower(0);
            robot.BL.setPower(0);
            robot.BR.setPower(0);
            while(robot.FL.getCurrentPosition() < secondRun){
                robot.FL.setPower(.5);
                robot.FR.setPower(.5);
                robot.BL.setPower(.5);
                robot.BR.setPower(.5);
            }
            robot.FL.setPower(0);
            robot.FR.setPower(0);
            robot.BL.setPower(0);
            robot.BR.setPower(0);

        }


    }
}