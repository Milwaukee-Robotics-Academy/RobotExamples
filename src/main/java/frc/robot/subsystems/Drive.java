/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Drive extends SubsystemBase implements Loggable {
  private final Victor m_LeftMotor = new Victor(DriveConstants.kLeftMotorFrontPort);
  private final Victor m_LeftFollowerMotor = new Victor(DriveConstants.kLeftMotorRearPort);
  private final SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(m_LeftMotor,m_LeftFollowerMotor);
  @Log.Encoder
  private final Encoder m_LeftEncoder = new Encoder(DriveConstants.kLeftEncoderPort1, DriveConstants.kLeftEncoderPort2);
  private final Jaguar m_RightMotor = new Jaguar(DriveConstants.kRightMotorFrontPort);
  private final Jaguar m_RightFollowerMotor = new Jaguar(DriveConstants.kRightMotorRearPort);
  private final SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(m_RightMotor,m_RightFollowerMotor);
  @Log.Encoder
  private final Encoder m_RightEncoder = new Encoder(DriveConstants.kRightEncoderPort1, DriveConstants.kRightEncoderPort2);

  @Log.Gyro
  private final Gyro gyro = new AnalogGyro(DriveConstants.kGyroPort);
 // private final AHRS m_gyroscope = new AHRS(SPI.Port.kMXP);

  @Log.DifferentialDrive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_LeftMotors, m_RightMotors);

 /**
   * Creates a new drive.
   */
  public Drive() {

    m_robotDrive.setRightSideInverted(false);
  
  }

  public void drive(double rightThrottle, double leftThrottle, double rotation) {
    m_robotDrive.curvatureDrive(this.deadband(rightThrottle - leftThrottle), this.deadband(-rotation), true);
   //  m_robotDrive.arcadeDrive(this.deadband(rightThrottle - leftThrottle), this.deadband(-rotation));
    }
    public double deadband(double value){
      //Upper Deadband//
      if (value >= +0.2)
        return ((DriveConstants.kIsStudentDriver) ? value/2 : value);

      //Lower Deadband//
      if (value <= -0.2)
      return ((DriveConstants.kIsStudentDriver) ? value/2 : value);

      //Deadband//
      return 0;
    }

  public void driveTank(double d, double e){
    m_robotDrive.tankDrive(deadband(d), deadband(e));
  }

  public void vision() {

  }

  public void stopDrive(){
    m_robotDrive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
@Log(name = "Left Encoder")
  public Double getLeftEncoderPosition(){
    return m_LeftEncoder.getDistance();
  }

  @Log(name = "Right Encoder")
  public Double getRightEncoderPosition(){
    return m_RightEncoder.getDistance();
  }
}