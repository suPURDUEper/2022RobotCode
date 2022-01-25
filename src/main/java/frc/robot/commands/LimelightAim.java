// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Shuffleboardinfo;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Vision;


public class LimelightAim extends CommandBase {
  /** Creates a new LimelightAim. */
  Vision vision;
  DriveTrain driveTrain;
  //constants
  private double leftCommand;
  private double rightCommand;
  private double mSteeringKp = 0.05;
  private double mDriveKp = 0.80;
  //Network Table Entries
  NetworkTableEntry mKpSteer, mMinTa, mDrive_Kp;
  public LimelightAim(DriveTrain dt, Vision v) {
    // Use addRequirements() here to declare subsystem dependencies.
    driveTrain = dt;
    vision = v;
    addRequirements(driveTrain, vision);

    mKpSteer = Shuffleboardinfo.getInstance().getKpSteer();
    mDrive_Kp = Shuffleboardinfo.getInstance().getKpDrive();
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    mSteeringKp = mKpSteer.getDouble(0);
    mDriveKp = mDrive_Kp.getDouble(0);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double tx = vision.getTx();
    double minCommand = 0.05;
    double headingError = -tx;
    double steeringAdjust = 0.0;
    double turncommand;

    if (tx > 1.0) {
      steeringAdjust = mSteeringKp * headingError - minCommand;
    } else if (tx < 1.0) {
      steeringAdjust = mSteeringKp * headingError +minCommand;
    }

    leftCommand += steeringAdjust;

    rightCommand -= steeringAdjust;

    if (Math.abs(leftCommand) < Math.abs(rightCommand)) {
      turncommand = leftCommand;
    } else if (Math.abs(rightCommand) < Math.abs(leftCommand)) {
      turncommand = rightCommand;
    } else {
      turncommand = 0;
    }

    driveTrain.drive.arcadeDrive(0, turncommand);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driveTrain.drive.arcadeDrive(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
