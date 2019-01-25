/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team6359.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	// For example to map the left and right motors, you could define the
	// following variables to use with your drivetrain subsystem.
	// public static int leftMotor = 1;
	// public static int rightMotor = 2;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
	public static int BL = -1;
	public static int FL = -1;
	public static int BR = -1;
	public static int FR = -1;
	
	public static final int rEncoder1 = 8;
	public static final int rEncoder2 = 7;
	public static final int lEncoder1 = 6;
	public static final int lEncoder2 = 5;
	public static final int liftEncoder1 = 1;
	public static final int liftEncoder2 = 0;
	public static final int liftLimitHigh = 2;
	public static final int liftLimitLow = 3;
	
	public static double cpiLift = (3811.0 / 2) / 3.141;
	// Inches * cpiLift
	public static double liftSetPointFloor = 0;
	public static double liftSetPointDrive = 4 * cpiLift;
	public static double liftSetPointSwitch = 34 * cpiLift;
	//public static double liftSetPointScaleLow = 62 * cpiLift;
	public static double liftSetPointScaleNeutral = 72 * cpiLift;
	public static double liftSetPointScaleHigh = 49000;
	
	
	//Intake Positions [liftHeight, thetaArm, thetaWrist] x is inches from lift, y is inches from ground
	
	public static double[] setPointHatchFloor = {0, 0, 0};
	public static double[] setPointHatchCommonLow = {0, 0, 0};
	public static double[] setPointHatchRocketMid = {0, 0, 0};
	public static double[] setPointHatchRocketHigh = {0, 0, 0};
	public static double[] setPointCargoFloor = {0, 0, 0};
	public static double[] setPointCargoCorral = {0, 0, 0};
	public static double[] setPointCargoRocketLow = {0, 0, 0};
	public static double[] setPointCargoRocketMid = {0, 0, 0};
	public static double[] setPointCargoRocketHigh = {0, 0, 0};
	
}
