/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Config;
import io.github.oblarg.oblog.annotations.Log;

import static frc.robot.Constants.ShooterConstants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;


import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Shooter extends SubsystemBase implements Loggable {
  /**
   * Creates a new Shooter.
   */ 
private static VictorSPX frontFollowerMotor = new VictorSPX(ShooterConstants.kFrontFollowerMotorPort);
@Log.SpeedController(name="Front")
private static TalonSRX frontMotor = new TalonSRX(ShooterConstants.kFrontMotorPort);
private static VictorSPX backFollowerMotor = new VictorSPX(ShooterConstants.kBackFollowerMotorPort);
@Log.SpeedController(name="Back")
private static TalonSRX backMotor = new TalonSRX(ShooterConstants.kBackMotorPort);
private final DoubleSolenoid shooterSolenoid = new DoubleSolenoid(ShooterConstants.kShooterSolenoid1, ShooterConstants.kShooterSolenoid2);

public Shooter (){
  frontMotor.configFactoryDefault();
  frontFollowerMotor.configFactoryDefault();
  backMotor.configFactoryDefault();
  backFollowerMotor.configFactoryDefault();

  frontFollowerMotor.follow(frontMotor);
  backFollowerMotor.follow(backMotor);

  frontMotor.setInverted(false);
  frontFollowerMotor.setInverted(true);
  backMotor.setInverted(true);
  backFollowerMotor.setInverted(true);
  //This has the Victors follow the Talons
}
  /**
   * This raises the shooter
   */
  @Config
  public void up() {
    shooterSolenoid.set(DoubleSolenoid.Value.kForward);
  }

  /**
   * This lowers the shooter
   */
  @Config
  public void down() {
    shooterSolenoid.set(DoubleSolenoid.Value.kReverse);
  }

  /**
   * This will have the motors run full speed
   **/
  @Config
  public void runShooter() {
    frontMotor.set(ControlMode.PercentOutput, .9);
    backMotor.set(ControlMode.PercentOutput, .9);
  }

  /**
   * This will have the motors run at a specific speed
   * @param speed
   */
  public void runShooter(Double speed){
    frontMotor.set(ControlMode.PercentOutput, speed);
   // backMotor.set(ControlMode.PercentOutput, speed);
  }

  public void stopShooter(){
    frontMotor.set(ControlMode.PercentOutput, 0);
    backMotor.set(ControlMode.PercentOutput, 0);
  }
  @Config
public void runShooter(double front, double back) {
  frontMotor.set(ControlMode.PercentOutput, front);
  backMotor.set(ControlMode.PercentOutput, back);
}
}
