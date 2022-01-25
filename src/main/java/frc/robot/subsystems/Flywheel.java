// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase {
  public static TalonFX leftFlywheel;
  public static TalonFX rightFlywheel;
  public Flywheel() {
    leftFlywheel = new TalonFX(Constants.Left_Flywheel);
    leftFlywheel.setInverted(true);
    leftFlywheel.config_kF(Constants.kPIDLoopIdx, Constants.flywheelF, Constants.kTimeoutMs);
    leftFlywheel.config_kP(Constants.kPIDLoopIdx, Constants.flywheelP, Constants.kTimeoutMs);
    leftFlywheel.config_kI(Constants.kPIDLoopIdx, Constants.flywheelI, Constants.kTimeoutMs);
    leftFlywheel.config_kD(Constants.kPIDLoopIdx, Constants.flywheelD, Constants.kTimeoutMs);
    rightFlywheel = new TalonFX(Constants.Right_Flywheel);
    rightFlywheel.setInverted(false);
    rightFlywheel.config_kF(Constants.kPIDLoopIdx, Constants.flywheelF, Constants.kTimeoutMs);
    rightFlywheel.config_kP(Constants.kPIDLoopIdx, Constants.flywheelP, Constants.kTimeoutMs);
    rightFlywheel.config_kI(Constants.kPIDLoopIdx, Constants.flywheelI, Constants.kTimeoutMs);
    rightFlywheel.config_kD(Constants.kPIDLoopIdx, Constants.flywheelD, Constants.kTimeoutMs);

    leftFlywheel.follow(rightFlywheel);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  public void shoot () {
rightFlywheel.set(ControlMode.Velocity, Constants.flywheelVelocity);
  }
}
