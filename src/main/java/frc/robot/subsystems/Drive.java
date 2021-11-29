/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
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
  private final SpeedControllerGroup m_LeftMotors = new SpeedControllerGroup(m_LeftMotor, m_LeftFollowerMotor);
  @Log.Encoder
  private final Encoder m_LeftEncoder = new Encoder(DriveConstants.kLeftEncoderPort1, DriveConstants.kLeftEncoderPort2);
  private final Victor m_RightMotor = new Victor(DriveConstants.kRightMotorFrontPort);
  private final Victor m_RightFollowerMotor = new Victor(DriveConstants.kRightMotorRearPort);
  private final SpeedControllerGroup m_RightMotors = new SpeedControllerGroup(m_RightMotor, m_RightFollowerMotor);
  @Log.Encoder
  private final Encoder m_RightEncoder = new Encoder(DriveConstants.kRightEncoderPort1,
      DriveConstants.kRightEncoderPort2);
  

  @Log.Gyro
  private final Gyro gyro = new AnalogGyro(DriveConstants.kGyroPort);
  // private final AHRS m_gyroscope = new AHRS(SPI.Port.kMXP);

  @Log.DifferentialDrive
  private final DifferentialDrive m_robotDrive = new DifferentialDrive(m_LeftMotors, m_RightMotors);

  /**
   * Creates a new drive.
   */
  public Drive() {
    m_LeftMotors.setInverted(true);
    m_RightMotors.setInverted(false);
    m_robotDrive.setRightSideInverted(false);
    m_RightEncoder.setReverseDirection(true);

  }

  /**
   * This leverages the CheesyDrive model. One gamepad access determines the
   * forward/backward speed, and another determines the rate of rotation (left or
   * right)
   * 
   * @param throttle
   * @param rotation
   */
  public void drive(double throttle, double rotation) {
    m_robotDrive.curvatureDrive(this.deadband(throttle), this.deadband(-rotation), true);
  }

  /**
   * This method allows us to do 2 things. if the the gamepad values are coming in
   * very small, that is a joystick is only very slightly (or doesn't return
   * exactly to the center when released) we ignore it.
   * 
   * We also use this to reduce the speed for student drivers. This helps new
   * drivers learn to drive.
   * 
   * @param value
   * @return
   */
  public double deadband(double value) {
    // Upper Deadband//
    if (value >= +0.2)
      return ((DriveConstants.kIsStudentDriver) ? value / 2 : value);

    // Lower Deadband//
    if (value <= -0.2)
      return ((DriveConstants.kIsStudentDriver) ? value / 2 : value);

    // Deadband//
    return 0;
  }

  /**
   * This allows you to pass in a split arcade. That is usually implemented by the
   * right trigger on the game pad passes in forward speed, left trigger passes in
   * reverse speed. This method will combine them for a single throttle and use
   * another axis for rotation
   * 
   * @param rightThrottle
   * @param leftThrottle
   * @param rotation
   */
  public void drive(double rightThrottle, double leftThrottle, double rotation) {
    drive(rightThrottle - leftThrottle, rotation);
  }

  public void driveTank(double d, double e) {
    m_robotDrive.tankDrive(-deadband(d), -deadband(e));
  }

  public void vision() {

  }

  @Log(name = "Distance Average")
  public Double getDistanceAverage() {
    return (m_LeftEncoder.getDistance()+m_RightEncoder.getDistance())/2;
  }

  public void resetEncoders() {
    m_LeftEncoder.reset();
    m_RightEncoder.reset();
  }
  public void stopDrive() {
    m_robotDrive.stopMotor();
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  @Log(name = "Left Encoder")
  public Double getLeftEncoderPosition() {
    return m_LeftEncoder.getDistance();
  }

  @Log(name = "Right Encoder")
  public Double getRightEncoderPosition() {
    return m_RightEncoder.getDistance();
  }
}