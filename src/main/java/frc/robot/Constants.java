/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The constants for the different robot subsystems
 */
public final class Constants {
    public static final class DriveConstants {
        //Drive Controllers
        public static final int kLeftMotorFrontPort = 0; //PWM on victors, can id on Falcons
        public static final int kLeftMotorRearPort = 2; //PWM on victors, can id on Falcons
        public static final int kRightMotorFrontPort = 1; //PWM on victors, can id on Falcons
        public static final int kRightMotorRearPort = 3; //PWM on victors, can id on Falcons
        //Wheel encoders
        public static final int kLeftEncoderPort1 = 0;
        public static final int kLeftEncoderPort2 = 1;
        public static final int kRightEncoderPort1 = 2;
        public static final int kRightEncoderPort2 = 3;

        public static final int kGyroPort = 0; //Analog in

        public static final boolean kIsStudentDriver = true;
    }
    public static final class CameraConstants {
    // Constants such as camera and target height stored. Change per robot and goal!
    // How high is the camera installed
    public static double kCameraHeightInches = 24;
    // how high is the target
    public static double kTargetHeightInches = 60;
    //angle the camera is installed
    public static double KCameraPitchDegrees = 0;
    // How far from the target we want to be
    public static double kGoalRangeInches = 36;

    }
    public static final class IntakeConstants {
        public static final int kIntakeMotor = 6; //pwm
        public static final int kIntakeSolenoid1 = 3;
        public static final int kIntakeSolenooid2 = 0;
    }
    public static final class StorageConstants {
        public static final int kStorageLeft = 4; //pwm
        public static final int kStorageRight = 7; //pwm
        //public static final int kFlapperSolenoid1 = 2;
        //public static final int kFlapperSolenoid2 = 3;
        public static int kPhotoEye1 = 0; //DIO
        public static int kPhotoEye2 = 1; //DIO
    }
    public static final class ShooterConstants{
        public static final int kFrontFollowerMotorPort = 0; //
        public static final int kFrontMotorPort = 1; //
        public static final int kBackFollowerMotorPort = 2; //
        public static final int kBackMotorPort = 3; //
        public static final int kShooterSolenoid1 = 4;
        public static final int kShooterSolenoid2 = 1;
    }
    public static final class ClimberConstants{
        public static final int kClimberMotorLeft = 5; //pwm
        public static final int kClimberMotorRight = 8; //pwm
        public static final int kClimberSolenoid1 = 6;
        public static final int kClimberSolenoid2 = 7;
    }
}
