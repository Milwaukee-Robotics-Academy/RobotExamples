/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

/**
 * This command drives the robot over a given distance with simple proportional control This command
 * will drive a given distance limiting to a maximum speed.
 */
public class DriveForward extends CommandBase { 
 
  private final Drive m_drive;
  private final double m_driveForwardSpeed;
  private final double m_distance;
  private double m_error;
  private static final double kTolerance = 0.1;
  private static final double kP = -1.0 / 5.0;


  public DriveForward(double distance, double maxSpeed, Drive drive) {
    m_distance = distance;
    m_driveForwardSpeed = maxSpeed;
    m_drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }


  @Override
  public void initialize() {
    m_drive.resetEncoders();
  }

  @Override
  public void execute() {
    m_error = m_distance - m_drive.getDistanceAverage();
    System.out.println (m_error);
    if ( m_error >= 0) {
     m_drive.driveTank(1,1);
    } 
  }

  @Override
  public boolean isFinished() {
    return m_error <= 0;
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.stopDrive();
  }
}
