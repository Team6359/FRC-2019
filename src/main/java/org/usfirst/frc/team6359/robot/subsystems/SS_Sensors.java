package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SS_Sensors extends Subsystem {

	Encoder encLift;
	Encoder encArm;
	Encoder encWrist;
	DigitalInput limitSwitchHigh;
	DigitalInput limitLow;
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

	AnalogInput vacSensor;

	public SS_Sensors() {
		encLift  = new Encoder(RobotMap.liftEncoder1, RobotMap.liftEncoder2, false, Encoder.EncodingType.k4X);
		encArm = new Encoder(RobotMap.armEncoder1, RobotMap.armEncoder2, false, Encoder.EncodingType.k4X);
		encWrist = new Encoder(RobotMap.wristEncoder1, RobotMap.wristEncoder2, false, Encoder.EncodingType.k4X);
	//	limitSwitchHigh = new DigitalInput(2);
		 limitLow = new DigitalInput(RobotMap.laserDist);
		 vacSensor = new AnalogInput(RobotMap.vacSensor);
	}
	public double liftEncoder(boolean reset) {
		if (reset) {
			encLift.reset();
		}
		SmartDashboard.putNumber("Lift Encoder", encLift.getRaw());
		return encLift.getRaw();
	}
	
	public double wristEncoder(boolean reset, boolean degrees) {
		if (reset) {
			encWrist.reset();
		}
		//SmartDashboard.putNumber("Wrist Encoder", encWrist.getRaw());
		return degrees ? encWrist.getRaw() / RobotMap.cpd : encWrist.getRaw();
	}
	
	public double armEncoder(boolean reset, boolean degrees) {
		if (reset) {
			encArm.reset();
		}
		//SmartDashboard.putNumber("Arm Encoder", encArm.getRaw());
		return degrees ? encArm.getRaw() / RobotMap.cpd : encArm.getRaw();
	}

	public boolean liftLimitHigh() {
		SmartDashboard.putBoolean("Limit Switch High", !limitSwitchHigh.get());
		return !limitSwitchHigh.get();
	}
	
	public boolean liftLimitLow() {
		SmartDashboard.putBoolean("Limit Switch Low", limitLow.get());
		return limitLow.get();
	}

	public double gyro(boolean reset) {
		if (reset) {
			gyro.reset();
		}
		return gyro.getAngle();
	}

	public double vacSensor(){
		return vacSensor.getVoltage();
	}
	
	public void initDefaultCommand() {

	}
}