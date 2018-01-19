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

@Autonomous(name="Vuforia Navigation", group ="Autonomous Testing")
public class VuforiaNavigation extends LinearOpMode
{

    /* Declare OpMode members. */
    Hardware robot   = new Hardware();

    OpenGLMatrix lastLocation = null;

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    VuforiaLocalizer vuforia;

    VuforiaTrackable relicTemplate;

    @Override
    public void runOpMode()
    {

        robot.init(hardwareMap);

        /*
         * To start up Vuforia, tell it the view that we wish to use for camera monitor (on the RC phone);
         * If no camera monitor is desired, use the parameterless constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // OR...  Do Not Activate the Camera Monitor View, to save power
        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        // Our Team's Vuforia License Key
        parameters.vuforiaLicenseKey = "Ae6iepz/////AAAAGdxu4oJ4k0UZm5TaMfGYrH9j8Kw67RZxl/Jzy+lIJTjjU7oocOOH+eodtrMD" +
                "cfFveDk3sLx+EFbDNw9K2LHzflXZweUlSOAO21AT8RO34GWUVuVm7I4a969r1KHYYn+oFbJ8l5eA+tj+gBAsObQcY4oHIvRZyC" +
                "CiDIHFv/Dg5Sh+3CRbWVjpjQXjAl2FinzYzHLRV+JCpINFPiWZ+pwi9M4BqB4lShUkKk/fwfRU66FIezjKPZMsVFBhE/dVDgJ0N" +
                "cMdskK6FggRijjubvmxlmmWlTa6BucLRKp0pI7dTLQz4B+Diqw8Qe4fJimEvlwrNTGDs5s+YKV/YD3A3llIUSWdbbxkwbngtqiufMMalf03";

        /*
         * We also indicate which camera on the RC that we wish to use.
         * Here we chose the back (HiRes) camera (for greater range), but
         * for a competition robot, the front camera might be more convenient.
         */
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        this.vuforia = ClassFactory.createVuforiaLocalizer(parameters);

        VideoBackgroundConfig cfg= new VideoBackgroundConfig();
        cfg.setReflection(VIDEO_BACKGROUND_REFLECTION.VIDEO_BACKGROUND_REFLECTION_ON);
        /**
         * Load the data set containing the VuMarks for Relic Recovery. There's only one trackable
         * in this data set: all three of the VuMarks in the game were created from this one template,
         * but differ in their instance id information.
         * @see VuMarkInstanceId
         */
        VuforiaTrackables relicTrackables = this.vuforia.loadTrackablesFromAsset("RelicVuMark");
        relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate"); // can help in debugging; otherwise not necessary

        /**
         * We use units of mm here because that's the recommended units of measurement for the
         * size values specified in the XML for the ImageTarget trackables in data sets. E.g.:
         *      <ImageTarget name="stones" size="247 173"/>
         * You don't *have to* use mm here, but the units here and the units used in the XML
         * target configuration files *must* correspond for the math to work out correctly.
         */
        float mmPerInch        = 25.4f;
        float mmBotWidth       = 18 * mmPerInch;            // ... or whatever is right for your robot
        float mmFTCFieldWidth  = (12*12 - 2) * mmPerInch;   // the FTC field is ~11'10" center-to-center of the glass panels

        // Create Points for the VuMark
        OpenGLMatrix vuMarkPosition = OpenGLMatrix
                /* Translate the target off to the RED WALL. Our translation here
                is a negative translation in X.*/
                .translation(-mmFTCFieldWidth/2, 0, 0)
                .multiplied(Orientation.getRotationMatrix(
                        /* In the fixed (field) coordinate system, rotate 90deg in X, then 90 in Z */
                        AxesReference.EXTRINSIC, AxesOrder.XZX,
                        AngleUnit.DEGREES, 90, 90, 0));

        // Create Safe Zone Position
        OpenGLMatrix safeZone = OpenGLMatrix.translation(30*mmPerInch,0,16*mmPerInch);


        telemetry.addData(">", "Press Play to start");
        telemetry.update();
        waitForStart();

        relicTrackables.activate();

        while (opModeIsActive()) {
            //moveTo(safeZone);
            //drive(1,1);

            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                drive(0);
            }
            else{
                drive(0,0);
            }
        }
    }


    public void moveTo(OpenGLMatrix targetLocation)//Moves robot to new coordinates
    {

        RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);




        while(vuMark==RelicRecoveryVuMark.UNKNOWN){
            //currently, do nothing until see vuMark
        }
        telemetry.addData("VuMark Found", vuMark);
        telemetry.update();

        lastLocation = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();//Retrieves the original location
        VectorF target = targetLocation.getTranslation();//Sets current and target locations
        VectorF current = lastLocation.getTranslation();

        double dX, dZ,  aPow, bPow;
        double moveAngle, robotAngle;


        while(target.get(0)!=current.get(0) && target.get(2)!=current.get(2))//While we still want to be correcting, this runs
        {
            dX = target.get(0) - current.get(0);//Gets 'X' coordinate
            dZ = target.get(2) - current.get(2);//Gets 'Z' coordinate

            moveAngle = Math.atan(dX/dZ);
            robotAngle = Math.atan(current.get(0)/current.get(2));

            robot.clock.reset();

            //robot.correctionTimer.start();//Starts running the correction timer

            while(robot.clock.milliseconds()<1000)//While the correction timer is running this loops (!robot.correctionTimer.isDone)
            {

                drive(moveAngle+robotAngle);//Sets the motor powers for all 4 motors

            }
            while(vuMark == RelicRecoveryVuMark.UNKNOWN)
            {
                if(robotAngle <= 0)
                {
                    drive(0.5, -0.5);
                }
                else
                {
                    drive(-0.5, 0.5);
                }
            }
            lastLocation = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();//Retrieves the old location
            current = lastLocation.getTranslation();//Defines 'current' VectorF to just translation, loses rotation
        }



    }


    /*
    public void moveTo(OpenGLMatrix targetLocation)//Moves robot to new coordinates
    {
        VectorF target = targetLocation.getTranslation();//Sets current and target locations
        VectorF current = lastLocation.getTranslation();

        double dX, dZ,  aPow, bPow;
        double mirrorAngle;

        while(!lastLocation.equals(targetLocation))//While we still want to be correcting, this runs
        {
            dX = target.get(0)-current.get(0);//Gets 'X' coordinate
            dZ = target.get(2)-current.get(2);//Gets 'Z' coordinate

            aPow = getMotorPower(dX, dZ, 'a');//Gets the motor power required for the new location
            bPow = getMotorPower(dX, dZ, 'b');

            robot.correctionTimer.start();//Starts running the correction timer

            while(!robot.correctionTimer.isDone)//While the correction timer is running this loops
            {
                drive(aPow, bPow);//Sets the motor powers for all 4 motors

                lastLocation = ((VuforiaTrackableDefaultListener)relicTemplate.getListener()).getPose();//Retrieves the old location
                current = lastLocation.getTranslation();//Defines 'current' VectorF to just translation, loses rotation

                mirrorAngle = servoArcTan(current.get(0)/current.get(2));//Calculates the required mirror position for correct orientation
                robot.Mirror.setPosition(mirrorAngle);//Sets the mirror position
            }
        }
    }
    */

    /*
    public double getMotorPower(double dX, double dZ, char group)
    {
        switch (group)
        {
            case 'a':
                return ((dZ + dX)/Math.pow((2*dX*dX + 2*dZ*dZ),.5));
            case 'b':
                return ((dZ - dX)/Math.pow((2*dX*dX + 2*dZ*dZ),.5));
            default:
                return 0;
        }
    }
    */

    /*
    public double servoArcTan(double value)//Calculates the servo position given
    {
        double ans = Math.atan(value);
        ans = ans/Math.PI;

        return (ans + 0.5);
    }
    */

    public void drive(double a, double b)//Sets all 4 motor powers
    {
        robot.FR.setPower(-1*a); robot.BL.setPower(-1*a);
        robot.FL.setPower(-1*b); robot.BR.setPower(-1*b);
    }

    public void drive(double angleRadians)//Sets Robot to move at an Angle
    {
        drive(Math.cos(Math.PI/4 - angleRadians), Math.sin(Math.PI/4 - angleRadians));
    }
}

