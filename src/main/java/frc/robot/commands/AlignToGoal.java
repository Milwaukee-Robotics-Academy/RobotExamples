// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Camera;

public class AlignToGoal extends CommandBase {
  Camera m_Camera;
  Drive m_Drive;
  // PID constants should be tuned per robot
  final double LINEAR_P = 0.1;
  final double LINEAR_D = 0.0;
  PIDController forwardController = new PIDController(LINEAR_P, 0, LINEAR_D);

  final double ANGULAR_P = 0.1;
  final double ANGULAR_D = 0.0;
  PIDController turnController = new PIDController(ANGULAR_P, 0, ANGULAR_D);

  /** Creates a new AlignToGoal. */
  public AlignToGoal(Camera camera, Drive drive) {
    m_Camera = camera;
    m_Drive = drive;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(m_Camera);
    addRequirements(m_Drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double forwardSpeed = 0;
    double rotationSpeed = 0;
    // Vision-alignment mode
    // Query the latest result from PhotonVision
    var result = m_Camera.getLatestResult();

    if (m_Camera.hasTargets()) {
      // First calculate range
      double range = m_Camera.getTargetRange(Units.degreesToRadians(result.getBestTarget().getPitch()));

      // Use this range as the measurement we give to the PID controller.
      // -1.0 required to ensure positive PID controller effort _increases_ range
      forwardSpeed = -1.0 * forwardController.calculate(range, m_Camera.getGoalRange());
      // Calculate angular turn power
      // -1.0 required to ensure positive PID controller effort _increases_ yaw
      rotationSpeed = -1.0 * turnController.calculate(result.getBestTarget().getYaw(), 0);
    } else {
      // If we have no targets, stay still.
      forwardSpeed = 0;
      rotationSpeed = 0;
    }
    m_Drive.drive(forwardSpeed, rotationSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
