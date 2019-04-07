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
	public static final int vacuum2 = 2;
	public static final int lift1 = 3;
	public static final int lift2 = 4;
	public static final int lift3 = 5;
	public static final int intake = 0;
	public static final int wrist = 5;
	public static final int arm = 4;

	public static final int liftEncoder1 = 1;
	public static final int liftEncoder2 = 0;
	public static final int armEncoder1 = 2;
	public static final int armEncoder2 = 3;
	public static final int wristEncoder1 = 6;
	public static final int wristEncoder2 = 7;
	public static final int laserDist = 4;

	public static final int solenoidBrake1 = 4;
	public static final int solenoidBrake2 = 5;

	public static final int solenoidVac1 = 2;
	public static final int solenoidVac2 = 3;

	public static final int solenoidEndgame1 = 0;
	public static final int solenoidEndgame2 = 1;
	public static final int vacSensor = 0;

	// Intake Positions [liftHeight, thetaArm, thetaWrist] x is inches from lift, y
	// is inches from ground

	public static double[] setPointHatchFloor = { 200, 0, -730 };
	public static double[] setPointHatchCommonLow = { 303, 0, -230 };
	public static double[] setPointHatchRocketMid = { 1430, 0, -230 };
	public static double[] setPointHatchRocketHigh = { 2500, 0, -230 };
	public static double[] setPointHatchTravel = { 0, 0.9, -500 };
	public static double[] setPointCargoFloor = { 130, 0, -820 };
	public static double[] setPointCargoCorral = { 0, 0, 0 };
	public static double[] setPointCargoRocketLow = { 694, 0, -582 };
	public static double[] setPointCargoRocketMid = { 1756, 0, -582 };
	public static double[] setPointCargoRocketHigh = { 2450, 0, -480 };
	public static double[] setPointCargoIntake = { 0, 0, 0 };
	public static double[] setPointCargoTravel = { 0, 1.1, -710 };
	// Please move lift

	public static double[] setPointTravel = { 0, 0, -100 };

	public static double[] setPointTest = { 264, 0.9, -100 };

	// .8 = actual .75
	// .35 = actual .25
	// counts per degree
	public static double cpd = 1000 / 90;

}
