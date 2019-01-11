package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;
import org.usfirst.frc.team6359.robot.commands.MoveController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

	public static VictorSPX FR, BR, FL, BL;
	
	public SS_DriveTrain() {
		FL = new VictorSPX(RobotMap.FL);
		FR = new VictorSPX(RobotMap.FR);
		BR = new VictorSPX(RobotMap.BR);
		BL = new VictorSPX(RobotMap.BL);

		System.out.println("DriveTrain");

		FR.setInverted(true);
		BR.setInverted(true);
		FL.setInverted(false);
		BL.setInverted(false);

	}

	public void ControllerDrive(double leftXAxis, double leftYAxis, double rightXAxis, double rightYAxis) {
		Drive(leftYAxis + rightXAxis, leftYAxis - rightXAxis);
	}

	public void Drive(double leftSpeed, double rightSpeed) {
		BL.set(ControlMode.PercentOutput, leftSpeed);
		BR.set(ControlMode.PercentOutput, rightSpeed);
		FL.set(ControlMode.PercentOutput, leftSpeed);
		FR.set(ControlMode.PercentOutput, rightSpeed);

		SmartDashboard.putNumber("Drive Left Speed", leftSpeed);
		SmartDashboard.putNumber("Drive Right Speed", rightSpeed);
	}

	public void initDefaultCommand() {
		// subsystem's default command
		setDefaultCommand(new MoveController());

	}
}