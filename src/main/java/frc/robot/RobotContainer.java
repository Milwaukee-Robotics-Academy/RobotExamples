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
  
    m_Drive.setDefaultCommand(new SplitArcadeDrive(() -> driverController.getTriggerAxis(GenericHID.Hand.kLeft),
         () -> driverController.getTriggerAxis(GenericHID.Hand.kRight), () -> driverController.getX(GenericHID.Hand.kLeft), m_Drive));

}

/**
 * This tells what buttons are being used for which commands
 */
public void configureButtonBindings() {


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