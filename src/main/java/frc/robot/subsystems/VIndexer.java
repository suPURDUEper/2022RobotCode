// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;


public class VIndexer extends SubsystemBase {
  VictorSPX leftIndexer;
  VictorSPX rightIndexer;
  CANSparkMax midIndexer;
  /** Creates a new VIndexer. */
  public VIndexer() {
leftIndexer = new VictorSPX(Constants.Left_Indexer);
leftIndexer.setInverted(true);
rightIndexer = new VictorSPX(Constants.Right_Indexer);
rightIndexer.setInverted(false);
midIndexer = new CANSparkMax(Constants.Mid_Indexer, MotorType.kBrushless);
midIndexer.setInverted(true);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

 public void index(XboxController controller){
   if(controller.getRawAxis(Constants.XBOX_LEFT_TRIGGER) > Constants.IntakeDeadZone){
     if(RobotContainer.Break_Beam_1 == false && RobotContainer.Break_Beam_2 == false && RobotContainer.Break_Beam_3 == false && RobotContainer.Break_Beam_4 == false){
      leftIndexer.set(ControlMode.PercentOutput, 0);
      rightIndexer.set(ControlMode.PercentOutput, 0);
      midIndexer.set(0);
     }else{
       leftIndexer.set(ControlMode.PercentOutput, -Constants.IndexerSpeed);
       rightIndexer.set(ControlMode.PercentOutput, Constants.IndexerSpeed);
       midIndexer.set(Constants.IndexerSpeed);
     }
   }else{
    leftIndexer.set(ControlMode.PercentOutput, 0);
    rightIndexer.set(ControlMode.PercentOutput, 0);
    midIndexer.set(0);
   }
 }
 public void purge() {
   midIndexer.set(-Constants.IndexerSpeed);
 }


}
