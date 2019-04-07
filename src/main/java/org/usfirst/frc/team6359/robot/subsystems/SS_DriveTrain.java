package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;
import org.usfirst.frc.team6359.robot.commands.MoveController;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_DriveTrain extends Subsystem {

	public static VictorSPX FR, BR, FL, BL;
	public Solenoid solenoid1;
	public Solenoid solenoid2;

	public boolean solenoidPos = false;

	private boolean slowMode = false;

	public SS_DriveTrain() {
		FL = new VictorSPX(RobotMap.FL);
		FR = new VictorSPX(RobotMap.FR);
		BR = new VictorSPX(RobotMap.BR);
		BL = new VictorSPX(RobotMap.BL);

		solenoid1 = new Solenoid(RobotMap.solenoidEndgame1);
		solenoid2 = new Solenoid(RobotMap.solenoidEndgame2);

		System.out.println("DriveTrain");

		FR.setInverted(true);
		BR.setInverted(true);
		FL.setInverted(false);
		BL.setInverted(false);

	}

	private double curSpeedLeft = 0;
	private double curSpeedRight = 0;

	double leftSpeed, rightSpeed, x, y, error;

	public void ControllerDrive(double leftXAxis, double leftYAxis, double rightXAxis, double rightYAxis, int DPad2) {
		y = Math.pow(leftYAxis, 3);
		x = Math.pow(-rightXAxis, 3);

		if (Math.abs(leftSpeed - rightSpeed) > 0.1) {
			leftSpeed *= 0.5;
			rightSpeed *= 0.5;
		}
		error = 0;
		leftSpeed = (y + x) + error;
		rightSpeed = (y - x) - error;
		if ((curSpeedLeft < leftSpeed && curSpeedLeft >= 0) || (curSpeedLeft > leftSpeed && curSpeedLeft <= 0))
			curSpeedLeft += leftSpeed / 75;
		if ((curSpeedRight < rightSpeed && curSpeedRight >= 0) || (curSpeedRight > rightSpeed && curSpeedRight <= 0))
			curSpeedRight += rightSpeed / 75;
		if ((leftSpeed < curSpeedLeft && curSpeedLeft >= 0) || (leftSpeed > curSpeedLeft && curSpeedLeft <= 0))
			curSpeedLeft -= leftSpeed / 75;
		if ((rightSpeed < curSpeedRight && curSpeedRight >= 0) || (rightSpeed > curSpeedRight && curSpeedRight <= 0))
			curSpeedRight -= rightSpeed / 75;

		if (Math.abs(leftSpeed) < 0.1) {
			curSpeedLeft = 0;
		}
		if (Math.abs(rightSpeed) < 0.1) {
			curSpeedRight = 0;
		}

		solenoid1.set(solenoidPos);
		solenoid2.set(!solenoidPos);

		if (slowMode) {
			leftSpeed /= 2;
			rightSpeed /= 2;
		}

		Drive(leftSpeed, rightSpeed);

		SmartDashboard.putBoolean("SlowMode", slowMode);
	}

	public void flipSolenoid() {
		solenoidPos = !solenoidPos;
	}

	public void Drive(double leftSpeed, double rightSpeed) {

		BL.set(ControlMode.PercentOutput, leftSpeed);
		BR.set(ControlMode.PercentOutput, rightSpeed);
		FL.set(ControlMode.PercentOutput, leftSpeed);
		FR.set(ControlMode.PercentOutput, rightSpeed);

		SmartDashboard.putNumber("Drive Left Speed", curSpeedLeft);
		SmartDashboard.putNumber("Drive Right Speed", curSpeedRight);
	}

	public void setSlowMode(boolean input) {
		slowMode = input;
	}

	public void initDefaultCommand() {
		// subsystem's default command
		setDefaultCommand(new MoveController());

	}
}