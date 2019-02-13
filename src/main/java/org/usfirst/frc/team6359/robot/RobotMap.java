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
	public static int BL = 1;
	public static int FL = 3;
	public static int BR = 0;
	public static int FR = 2;

	public static final int vacuum = 1;
	public static final int dd = 2;
	public static final int lift1 = 3;
	public static final int lift2 = 4;
	public static final int lift3 = 5;
	public static final int intake = 0;
	public static final int wrist = -1;
	public static final int arm = 4;
	
	public static final int liftEncoder1 = 1;
	public static final int liftEncoder2 = 0;
	public static final int armEncoder1 = 2;
	public static final int armEncoder2 = 3;
	public static final int wristEncoder1 = 4;
	public static final int wristEncoder2 = 5;
	
	public static final int solenoidIntake1 = -1;
	public static final int solenoidIntake2 = -1;
	public static final int solenoidBrake1 = 4;
	public static final int solenoidBrake2 = 5;
	
	
	
	
	//Intake Positions [liftHeight, thetaArm, thetaWrist] x is inches from lift, y is inches from ground
	
	public static double[] setPointHatchFloor = {0, 0, 0};
	public static double[] setPointHatchCommonLow = {303, -984, 0};
	public static double[] setPointHatchRocketMid = {1430, -984, 0};
	public static double[] setPointHatchRocketHigh = {2500, -897, -128};
	public static double[] setPointCargoFloor = {0, 0, 0};
	public static double[] setPointCargoCorral = {0, 0, 0};
	public static double[] setPointCargoRocketLow = {0, 0, 0};
	public static double[] setPointCargoRocketMid = {0, 0, 0};
	public static double[] setPointCargoRocketHigh = {0, 0, 0};
	
	//counts per degree
	public static double cpd = 1000 / 90;
	
}
