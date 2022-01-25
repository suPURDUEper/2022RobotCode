// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;



public class DriveTrain extends SubsystemBase {
  CANSparkMax leftFront;
  CANSparkMax rightFront;
  CANSparkMax leftBack;
  CANSparkMax rightBack;
  public DifferentialDrive drive;
  public double driveTrainSpeed;
  public double driveTrainTurn;
  public static double boost = Constants.BoostInactive;
  /** 
   * Creates a new DriveTrain. */
  public DriveTrain() {
    leftFront = new CANSparkMax(Constants.Left_Front,MotorType.kBrushless);
    leftFront.setInverted(false);
    rightFront = new CANSparkMax(Constants.Right_Front,MotorType.kBrushless);
    rightFront.setInverted(false );
    leftBack = new CANSparkMax(Constants.Left_Back,MotorType.kBrushless);
    leftBack.setInverted(false);
    rightBack = new CANSparkMax(Constants.Right_Back,MotorType.kBrushless);
    rightBack.setInverted(true);

    leftBack.follow(leftFront);
    rightBack.follow(rightFront);
    drive = new DifferentialDrive(leftFront, rightFront);
    }
  

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }

  public void driveWithJoysticks(XboxController Controller, double speed) {
    if (Constants.DEADZONE * -1 < Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS) * -1 && Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)* -1 < Constants.DEADZONE) {
      driveTrainSpeed = 0;
    } else{
      if (Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS) * -1 > Constants.DEADZONE) {
        driveTrainSpeed = boost*(Constants.DRIVETRAINCURVE*(1-Constants.BASEVELOCITY)
        *Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*-1+(1-Constants.DRIVETRAINCURVE)
        *(1-Constants.BASEVELOCITY)*Math.pow(Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*-1, 5))
        +Constants.BASEVELOCITY;
      }
      if (Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*-1 < Constants.DEADZONE*-1) {
        driveTrainSpeed = boost*(Constants.DRIVETRAINCURVE*(1-Constants.BASEVELOCITY)
        *Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*-1+(1-Constants.DRIVETRAINCURVE)
        *(1-Constants.BASEVELOCITY)*Math.pow(Controller.getRawAxis(Constants.XBOX_LEFT_Y_AXIS)*-1, 5))
        -Constants.BASEVELOCITY;
      }
      }
      if (Constants.DEADZONE * -1 < Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS) && Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS) < Constants.DEADZONE) {
        driveTrainTurn = 0;
      } else{
        if (Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS) > Constants.DEADZONE) {
          driveTrainTurn = boost*(Constants.DRIVETRAINCURVE*(1-Constants.BASEVELOCITY)
          *Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS)+(1-Constants.DRIVETRAINCURVE)
          *(1-Constants.BASEVELOCITY)*Math.pow(Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS), 5))
          +Constants.BASEVELOCITY;
        }
        if (Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS) < Constants.DEADZONE*-1) {
          driveTrainTurn = boost*(Constants.DRIVETRAINCURVE*(1-Constants.BASEVELOCITY)*Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS)+(1-Constants.DRIVETRAINCURVE)*(1-Constants.BASEVELOCITY)*Math.pow(Controller.getRawAxis(Constants.XBOX_RIGHT_X_AXIS), 5))-Constants.BASEVELOCITY;
        }
        }
    drive.arcadeDrive(driveTrainSpeed, driveTrainTurn);
    //drive.arcadeDrive(xSpeed, zRotation);
  }
   
  public void AimWithLimelight(XboxController controller, Vision v) {
    // double headingError = -lt.tx;
    // if (lt.hasTarget()) {
    //   if (lt.tx > 1) {
    //     lt.steeringAdjust = lt.Kp * headingError - lt.minCommand;
    //   } else if (lt.tx < 1) {
    //     lt.steeringAdjust = lt.Kp * headingError + lt.minCommand;
    //   }
    //   lt.leftCommand += lt.steeringAdjust;
    //   lt.rightCommand -= lt.steeringAdjust;
    //   drive.arcadeDrive(0, );
    // }
  }

  public void driveForward(double speed) {
    drive.tankDrive(speed, speed);
  }
  public void stop() {
    drive.stopMotor();
  }
}
