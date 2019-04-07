package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_Arm extends PIDSubsystem {

	private static final float kPBall = -0.9f, kIBall = 0f, kDBall = 0f;

	double tolerance = 10; // 1/4 in tolerance

	private VictorSPX armMotor;

	// Initialize your subsystem here
	public SS_Arm() {

		super("Arm", kPBall, kIBall, kDBall);

		setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);

		armMotor = new VictorSPX(RobotMap.arm);

		setSetpoint(0);
		enable();

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	double newSetpoint = 0;
	double startTime = -1000;
	boolean x2;

	public void customSetpoint(double setpoint) {
		if (setpoint != newSetpoint)
			startTime = System.currentTimeMillis();
		newSetpoint = setpoint;
	}

	private double moveDownSpeed = 0.03;
	private double lY2;
	private boolean rB2, lB2, y2;

	protected double returnPIDInput() {

		if (newSetpoint < getSetpoint()) {
			setSetpoint(getSetpoint() - moveDownSpeed);
			if (Math.abs(getSetpoint() - moveDownSpeed) <= moveDownSpeed) {
				setSetpoint(newSetpoint);
			}
		}

		if (Math.abs(lY2) > 0.05) {
			setSetpoint(newSetpoint);
		}

		if (System.currentTimeMillis() - startTime > 1000 && newSetpoint > getSetpoint() && Robot.wrist.atSetpoint()) {
			setSetpoint(newSetpoint);
		}

		// Return your input value for the PID loop
		// e.g. a sensor, like a potentiometer:
		// yourPot.getAverageVoltage() / kYourMaxVoltage;
		SmartDashboard.putNumber("Arm Encoder COS",
				1 - Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true))));
		SmartDashboard.putNumber("Arm Encoder", Robot.sensors.armEncoder(false, false));
		SmartDashboard.putNumber("Arm Setpont", getSetpoint());
		// System.out.println("ARMENCODERCOS: " + (1 -
		// Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true)))));
		return 1 - Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true)));
	}

	boolean xPressed = false;

	protected void usePIDOutput(double output) {
		// Use output to drive your system, like a motor
		// e.g. yourMotor.set(output);
		double power = output;
		// if (output > 0.1 && 1 -
		// Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true))) > 0.7){
		// power = 0.1;
		// }
		// if (output < -0.1 && 1 -
		// Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true))) > 0.7){
		// power = -0.1;
		// }
		if (power > 0 && 1 - Math.cos(Math.toRadians(Robot.sensors.armEncoder(false, true))) < 0.8) {
			power = 0;
		}

		if (y2) {
			power = 0;
			setSetpoint(0);
			armMotor.set(ControlMode.PercentOutput, 0);
		}

		SmartDashboard.putNumber("Arm PID Output", power);
		if (!x2) {
			armMotor.set(ControlMode.PercentOutput, power);
		} else if (rB2) {
			armMotor.set(ControlMode.PercentOutput, 0.2);
		} else if (lB2) {
			armMotor.set(ControlMode.PercentOutput, -0.1);
		}

		if (x2) {
			Robot.sensors.armEncoder(true, false);
		}

	}

	public void delayBypass(double input, boolean input2, boolean input3, boolean input4, boolean input5) {
		lY2 = input;
		x2 = input2;
		rB2 = input3;
		lB2 = input4;
		y2 = input5;
	}
}
