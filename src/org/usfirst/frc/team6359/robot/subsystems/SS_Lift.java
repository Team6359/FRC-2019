package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class SS_Lift extends PIDSubsystem {

	public static SpeedController lift1, lift2, lift3;
	public static Solenoid brake1, brake2;
	public static double encVal;

	double tolerance = 10; // 1/4 in tolerance

	boolean manual = false;

	double triggerTolerance = 0.5;

	public int liftPos = 0; // Start in drive position

	public static double kP = 0.0;
	public static double kI = 0.0;
	public static double kD = 0.0;

	
	public SS_Lift() {

		super("Lift", kP, kI, kD);
		setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);

		encVal = Robot.sensors.liftEncoder(true);

		setSetpoint(0);

		enable();
		
		lift1 = new Victor(RobotMap.lift1);
		lift2 = new Victor(RobotMap.lift2);
		lift3 = new Victor(RobotMap.lift3);
		
		brake1 = new Solenoid(RobotMap.solenoidBrake1);
		brake2 = new Solenoid(RobotMap.solenoidBrake2);

	}

	public void initDefaultCommand() {
		
	}
	
	public void lift(double speed) {
		if(Math.abs(getPosition() - getSetpoint()) < tolerance) {
			brake1.set(true);
			brake2.set(false);
		} else {
			brake1.set(false);
			brake2.set(true);
		}
		encVal = Robot.sensors.liftEncoder(false);
	}

	public void resetEnc() {
		Robot.sensors.liftEncoder(true);
	}


	protected double returnPIDInput() {
		return encVal;
	}

	protected void usePIDOutput(double output) {
		//lift(output);
	}
}