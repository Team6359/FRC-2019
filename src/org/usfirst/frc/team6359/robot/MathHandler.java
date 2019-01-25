package org.usfirst.frc.team6359.robot;

import org.usfirst.frc.team6359.robot.subsystems.SS_Arm;
import org.usfirst.frc.team6359.robot.subsystems.SS_Lift;
import org.usfirst.frc.team6359.robot.subsystems.SS_Wrist;

public class MathHandler {
	
	private SS_Lift lift;
	private SS_Arm arm;
	private SS_Wrist wrist;
	
	private double[] currentPosition = {0, 0, 0};
	
	public MathHandler(SS_Lift lift, SS_Arm arm, SS_Wrist wrist) {
		this.lift = lift;
		this.arm = arm;
		this.wrist = wrist;
	}
	
	public void update() {
		lift.setSetpoint(currentPosition[0]);
		arm.setSetpoint(currentPosition[1]);
		wrist.setSetpoint(currentPosition[2]);
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
