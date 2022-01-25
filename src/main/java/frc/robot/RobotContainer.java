// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import frc.robot.commands.DriveWithBoost;
import frc.robot.commands.Climb;
import frc.robot.commands.DriveForwardTimed;
import frc.robot.commands.DriveWithJoysticks;
import frc.robot.commands.FlywheelSpeedUp;
import frc.robot.commands.Intake;
import frc.robot.commands.LimelightAim;
import frc.robot.commands.Purge;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.Conveyer;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.VIndexer;
import frc.robot.subsystems.Vision;
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
  private final Conveyer conveyer;
  private final VIndexer vIndexer;
  private final Flywheel flywheel;
  private final Vision vision;
  //shuffleboard info is the container for all the shuffleboard pieces
  private Shuffleboardinfo m_instance;
  //digital input declare
  public static DigitalInput botBreakBeam;
  public static DigitalInput botMidBreakBeam;
  public static DigitalInput topMidBreakBeam;
  public static DigitalInput topBreakBeam;
  public static boolean Break_Beam_1;
  public static boolean Break_Beam_2;
  public static boolean Break_Beam_3;
  public static boolean Break_Beam_4;

  //command declare
  private final DriveWithJoysticks driveWithJoysticks;
  private final DriveForwardTimed driveForwardTimed;
  private final DriveWithBoost driveWithBoost;
  private final Climb climb;
  private final Intake intake;
  private final FlywheelSpeedUp flywheelSpeedUp;
  private final Purge purge;
  private final LimelightAim limelightAim;

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
    conveyer = new Conveyer();
    vIndexer = new VIndexer();
    flywheel = new Flywheel();
    vision = new Vision();
    //add didgital inputs
    botBreakBeam = new DigitalInput(Constants.Bot_Break_Beam);
    botMidBreakBeam = new DigitalInput(Constants.Bot_Mid_Break_Beam);
    topMidBreakBeam = new DigitalInput(Constants.Top_Mid_Break_Beam);
    topBreakBeam = new DigitalInput(Constants.Top_Break_Beam);
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

    intake = new Intake(conveyer, vIndexer);
    intake.addRequirements(conveyer);
    intake.addRequirements(vIndexer);
    conveyer.setDefaultCommand(intake);
    vIndexer.setDefaultCommand(intake);

    flywheelSpeedUp = new FlywheelSpeedUp(flywheel);
    flywheelSpeedUp.addRequirements(flywheel);

    purge = new Purge(conveyer, vIndexer);
    purge.addRequirements(conveyer);
    purge.addRequirements(vIndexer);

    limelightAim = new LimelightAim(driveTrain, vision);
    limelightAim.addRequirements(driveTrain, vision);
    // Configure the button bindings
    configureButtonBindings();
    //sets up shuffleboard
    setupShuffleBoard();
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
    JoystickButton flywheelButton = new JoystickButton(operatorJoystick, XboxController.Button.kA.value);
    flywheelButton.whenHeld(SpeedUpFlywheel(flywheel));
    JoystickButton purgeButton = new JoystickButton(driverJoystick, XboxController.Button.kLeftBumper.value);
    purgeButton.whileHeld(Outtake(conveyer, vIndexer));
    JoystickButton limelightAimButton = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    limelightAimButton.whileHeld(limelightCommand(driveTrain, vision));
  }

  private Command BoostSpeed(DriveTrain driveTrain) {
    return driveWithBoost;
  }
  private Command SpeedUpFlywheel(Flywheel flywheel) {
    return flywheelSpeedUp;
  }
  private Command Outtake(Conveyer conveyer, VIndexer vIndexer) {
    return purge;
  }
  private Command limelightCommand(DriveTrain driveTrain, Vision vision){
    return limelightAim;
  }
  private void setupShuffleBoard(){
    m_instance = Shuffleboardinfo.getInstance();
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
