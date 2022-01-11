// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.commands.DriveWithBoost;
import frc.robot.commands.Climb;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  //Subsystem declare
  private final DriveTrain driveTrain;
  private final Climber climber;
  //command declare
  private final DriveWithJoysticks driveWithJoysticks;
  private final DriveForwardTimed driveForwardTimed;
  private final DriveWithBoost driveWithBoost;
  private final Climb climb;
  //controller declare
  public static XboxController driverJoystick;
  public static XboxController operatorJoystick;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    //set value for controllers
    driverJoystick = new XboxController(Constants.DRIVE_JOYSTICK_NUMBER);
    operatorJoystick = new XboxController(Constants.OPERATOR_JOYSTICK_NUMBER); 
    //set value for subsystems
    driveTrain = new DriveTrain();
    climber = new Climber();
    
    //set value for commands & set default commands
    driveWithJoysticks = new DriveWithJoysticks(driveTrain);
    driveWithJoysticks.addRequirements(driveTrain);
    driveTrain.setDefaultCommand(driveWithJoysticks);

    driveForwardTimed = new DriveForwardTimed(driveTrain);
    driveForwardTimed.addRequirements(driveTrain);

    driveWithBoost = new DriveWithBoost(driveTrain);
    driveWithBoost.addRequirements(driveTrain);

    climb = new Climb(climber);
    climb.addRequirements(climber);
    climber.setDefaultCommand(climb);

    
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton boostButton = new JoystickButton(driverJoystick, XboxController.Button.kRightBumper.value);
    boostButton.whileHeld(BoostSpeed(driveTrain));
  }

  private Command BoostSpeed(DriveTrain driveTrain) {
    return driveWithBoost;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return driveForwardTimed;
  }
}
