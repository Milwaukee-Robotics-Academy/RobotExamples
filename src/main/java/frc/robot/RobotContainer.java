package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AlignToGoal;
import frc.robot.commands.Autonomous;
import frc.robot.commands.SplitArcadeDrive;
import frc.robot.subsystems.Camera;
import frc.robot.subsystems.Drive;

public class RobotContainer {
  private final Drive m_Drive = new Drive();
  private final Camera m_Camera = new Camera();

 // private final PowerDistributionPanel pdp = new PowerDistributionPanel();
  private final XboxController driverController = new XboxController(0);
  private final CommandBase m_autonomousCommand = new Autonomous(m_Drive).withTimeout(5);

public RobotContainer() {

  
  configureButtonBindings();
  
  /**
   * Setting then default command for the Drive subsystem. Unless no other commands are using the {@link Drive} class
   * (such as {@Link AlignToGoal}) the SplitArcadeDrive command will run. This lets the driver drive
   */
    m_Drive.setDefaultCommand(new SplitArcadeDrive(() -> driverController.getTriggerAxis(GenericHID.Hand.kLeft),
         () -> driverController.getTriggerAxis(GenericHID.Hand.kRight), () -> driverController.getX(GenericHID.Hand.kLeft), m_Drive));

}

/**
 * This tells what buttons are being used for which commands
 */
public void configureButtonBindings() {

/**
 * Setting up the Driver Controller "A" button to control using the vision "AlignToGoal". This means
 * as long as you hold the A button down, the robot will look for a target, and if there is one locked in, it will 
 * drive towards it. Turning so that it is centered on the target, and moving forward until it is close enough (as 
 * determined by how far away we want to be, set in Constants... kGoalRangeInches )
 */
  final JoystickButton driverA = new JoystickButton(driverController, XboxController.Button.kA.value);
  driverA.whenHeld(new AlignToGoal(m_Camera,m_Drive),true);

}

public void shuffleBoard(){}

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_autonomousCommand;
  }

}