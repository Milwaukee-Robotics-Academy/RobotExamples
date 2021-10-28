/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drive;

public class TurnToTarget extends CommandBase {
  private final Drive m_drive;
  private final Camera m_camera;
  PIDController controller = new PIDController(.1, 0, 0);
  /**
   * Creates a new TurnToTarget.
   */
  public TurnToTarget(Drive drive, Camera camera) {
    m_drive = drive;
    m_camera = camera;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_drive);
    addRequirements(m_camera);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double rotationSpeed = 0;
    if (m_camera.hasTargets()) {
      // Rotation speed is the output of the PID controller
      rotationSpeed = controller.calculate(m_camera.getTargetYaw(), 0);
    } else {
      // If we have no targets, stay still.
      rotationSpeed = 0;
    }
    m_drive.drive(0, rotationSpeed);
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.drive(0,0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
