package org.usfirst.frc.team6359.robot;

import org.usfirst.frc.team6359.robot.subsystems.SS_Arm;
import org.usfirst.frc.team6359.robot.subsystems.SS_Lift;
import org.usfirst.frc.team6359.robot.subsystems.SS_Wrist;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class MathHandler {

	private SS_Lift lift;
	private SS_Arm arm;
	private SS_Wrist wrist;

	private double[] currentPosition = { 0, 0, 0 };

	public MathHandler(double lift, double arm, double wrist) {
		currentPosition[0] = lift;
		currentPosition[1] = arm;
		currentPosition[2] = wrist;
	}

	public void update() {
		SmartDashboard.putNumber("CurPos", currentPosition[0]);
		Robot.lift.setSetpoint2(currentPosition[0]);
		Robot.arm.customSetpoint(currentPosition[1]);
		Robot.wrist.customSetpoint(currentPosition[2]);
	}

	public void setCurrentPosition(double liftPos, double armPos, double wristPos) {
		currentPosition[0] = liftPos;
		currentPosition[1] = armPos;
		currentPosition[2] = wristPos;
	}

	public double[] getCurrentPosition() {
		return currentPosition;
	}

}
