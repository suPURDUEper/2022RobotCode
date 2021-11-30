// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    //PWM's for wiring
    public static final int Left_Front = 1;
    public static final int Left_Back = 2;
	public static final int Right_Front = 3;
    public static final int Right_Back = 4;
    public static final int climbMotor = 3;
    //Controller axis
	public static final int XBOX_LEFT_Y_AXIS = 1;
    public static final int XBOX_RIGHT_X_AXIS = 4;
    public static final int XBOX_RIGHT_Y_AXIS = 5;
    //Autonomous constants
	public static final double DRIVE_FORWARD_TIME = 3;
    public static final double AUTONOMOUS_SPEED = 0.4;
    //Joystick setup
    public static final int DRIVE_JOYSTICK_NUMBER = 0;
    public static final int OPERATOR_JOYSTICK_NUMBER = 1;
    //DriveTrain variables
    public static final double BoostActive = 0.9;
    public static final double BoostInactive = 0.7;
    public static final double DRIVETRAINCURVE = 0.15;
    public static final double DEADZONE = 0.05;
    public static final double BASEVELOCITY = 0.14;
    public static final double DRIVETRAINSPEED = .7;
}
