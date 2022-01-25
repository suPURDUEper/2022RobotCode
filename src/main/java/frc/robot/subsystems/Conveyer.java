// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;

public class Conveyer extends SubsystemBase {
  TalonSRX conveyerMotor;
  /** Creates a new Conveyer. */
  public Conveyer() {
    conveyerMotor = new TalonSRX(Constants.Conveyer_Motor);
    conveyerMotor.setInverted(false);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void convey(XboxController controller) {
    if (controller.getRawAxis(Constants.XBOX_LEFT_TRIGGER) > Constants.IntakeDeadZone) {
      if (RobotContainer.Break_Beam_3 == false && RobotContainer.Break_Beam_4 == true) {
        conveyerMotor.set(ControlMode.PercentOutput, Constants.ConveyerSpeed);
      } else {
        conveyerMotor.set(ControlMode.PercentOutput, 0);
      }
    } else {
      conveyerMotor.set(ControlMode.PercentOutput, 0);
    }
  }
   public void purge() { 
    conveyerMotor.set(ControlMode.PercentOutput, -Constants.ConveyerSpeed);
  }
}
