/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;

public class Turn extends CommandBase { 
  private final Drive m_drive;
  private double m_Speed = 0;
  private double m_DegreesTurn = 0;
  private double m_error;  


  /**
   * Creates a new Turn.
   */
  public Turn(double degrees, double spd, Drive drive) {
    m_drive = drive;
    m_DegreesTurn = degrees;
    m_Speed = spd;
    m_drive.resetGyro();
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.resetGyro();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if (m_DegreesTurn > 0){
      m_error = m_DegreesTurn - m_drive.getAngle();
      if ( m_error >= 0) {
        m_drive.drive(0, m_Speed);
      } 
    }else {
        m_error = m_DegreesTurn - m_drive.getAngle();
        if ( m_error <= 0) {
          m_drive.drive(0, -m_Speed);
      }
    }  
}

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.stopDrive();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
