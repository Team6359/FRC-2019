package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Sensors extends Subsystem {

	public Encoder encLeft = new Encoder(RobotMap.lEncoder1, RobotMap.lEncoder2, true);
	public Encoder encRight = new Encoder(RobotMap.rEncoder1, RobotMap.rEncoder2, true);

	Encoder encLift;
	DigitalInput limitSwitchHigh;
	DigitalInput limitSwitchLow;
	DigitalInput cubeIntake;
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	public SS_Sensors() {
		encLift  = new Encoder(RobotMap.liftEncoder1, RobotMap.liftEncoder2, false, Encoder.EncodingType.k4X);
		limitSwitchHigh = new DigitalInput(2);
		limitSwitchLow = new DigitalInput(3);
		cubeIntake = new DigitalInput(4);
		encRight.setReverseDirection(true);
		encLeft.setReverseDirection(false);
		encLeft.reset();
		encRight.reset();
	}
	public double rightEncoder(boolean reset) {
		if (reset) {
			encRight.reset();
		}
		SmartDashboard.putNumber("Right Encoder", encRight.getRaw());
		return encRight.getRaw();
	}

	public double leftEncoder(boolean reset) {
		if (reset) {
			encLeft.reset();
		}
		SmartDashboard.putNumber("Left Encoder", encLeft.getRaw());
		return encLeft.getRaw();
	}
	
	public double liftEncoder(boolean reset) {
		if (reset) {
			encLift.reset();
		}
		SmartDashboard.putNumber("Lift Encoder", encLift.getRaw());
		return encLift.getRaw();
	}

	public boolean liftLimitHigh() {
		SmartDashboard.putBoolean("Limit Switch High", !limitSwitchHigh.get());
		return !limitSwitchHigh.get();
	}
	
	public boolean liftLimitLow() {
		SmartDashboard.putBoolean("Limit Switch Low", !limitSwitchLow.get());
		return !limitSwitchLow.get();
	}
	public double gyro(boolean reset) {
		if (reset) {
			gyro.reset();
		}
		return gyro.getAngle();
	}
	
	public boolean cubeIntake() {
		SmartDashboard.putBoolean("Cube Intake Switch", !cubeIntake.get());
		return !cubeIntake.get();
	}

	public void initDefaultCommand() {

	}
}