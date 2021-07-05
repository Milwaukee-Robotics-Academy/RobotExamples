/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonPipelineResult;
import org.photonvision.PhotonUtils;

import edu.wpi.first.wpilibj.util.Units;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.CameraConstants;
import io.github.oblarg.oblog.Loggable;
import io.github.oblarg.oblog.annotations.Log;

public class Camera extends SubsystemBase implements Loggable {

  private final PhotonCamera m_Camera;
      // Constants such as camera and target height stored. Change per robot and goal!
      final double m_CameraHeightMeters = Units.inchesToMeters(CameraConstants.kCameraHeightInches);
      final double m_TargetHeightMeters = Units.inchesToMeters(CameraConstants.kTargetHeightInches);
      // Angle between horizontal and the camera.
      final double m_CameraPitchRadians = Units.degreesToRadians(CameraConstants.KCameraPitchDegrees);
  
      // How far from the target we want to be
      final double m_GoalRangeMeters = Units.inchesToMeters(CameraConstants.kGoalRangeInches);
  

  /**
   * Creates a new Vision.
   */
  public Camera() {
    // Change this to match the name of your camera
    m_Camera = new PhotonCamera("photonvision");
  }

  public double getTargetRange(){
    return PhotonUtils.calculateDistanceToTargetMeters(m_CameraHeightMeters, m_TargetHeightMeters,
    m_CameraPitchRadians, Units.degreesToRadians(getLatestResult().getBestTarget().getPitch()));

  }

  public double getTargetRange(double targetPitchRadians){
    return PhotonUtils.calculateDistanceToTargetMeters(m_CameraHeightMeters, m_TargetHeightMeters,
    m_CameraPitchRadians, targetPitchRadians);

  }
  @Log.BooleanBox
  public boolean hasTargets(){
    return m_Camera.hasTargets();
  }

  public void setDriverMode(boolean mode){
    m_Camera.setDriverMode(mode);
  }

  public PhotonPipelineResult getLatestResult(){
    return m_Camera.getLatestResult();
  }

public double getGoalRange(){
  return m_GoalRangeMeters;
}
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
