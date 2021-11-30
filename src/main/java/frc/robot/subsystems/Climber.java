// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Climber extends SubsystemBase {
  TalonFX climbMotor;
  /** Creates a new Climber. */
  public Climber() {
    climbMotor = new TalonFX(Constants.climbMotor);
    climbMotor.setInverted(false);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void climb(XboxController controller) {
    climbMotor.set(ControlMode.PercentOutput , controller.getRawAxis(Constants.XBOX_RIGHT_Y_AXIS));
  }
}
