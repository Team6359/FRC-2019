package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_Lift extends PIDSubsystem {

	public static SpeedController lift1, lift2, lift3;
	public static Solenoid brake1, brake2;
	public static double encVal;

	double tolerance = 10; // 1/4 in tolerance

	boolean manual = false;

	double triggerTolerance = 0.5;

	public int liftPos = 0; // Start in drive position
	private static final float kP = 0.0045f, kI = 0f, kD = 0f;


	
	public SS_Lift() {

		super("Lift", kP, kI, kD);

		encVal = Robot.sensors.liftEncoder(true);

		
		setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);

		setSetpoint(0);
		enable();
		
		lift1 = new Victor(RobotMap.lift1); //down
		lift2 = new Victor(RobotMap.lift2); //up
		lift3 = new Victor(RobotMap.lift3); //up
		
		//brake1 = new Solenoid(RobotMap.solenoidBrake1);
		//brake2 = new Solenoid(RobotMap.solenoidBrake2);

	}

	public void initDefaultCommand() {
		
	}
	
	public void lift(double speed) {
		if(Math.abs(getPosition() - getSetpoint()) < tolerance) {
		//	brake1.set(true);
			//brake2.set(false);
		} else {
			//brake1.set(false);
		//	brake2.set(true);
		}
		encVal = Robot.sensors.liftEncoder(false);
		lift1.set(-speed);
		lift2.set(speed);
		lift3.set(speed);
	}

	public void resetEnc() {
		Robot.sensors.liftEncoder(true);
	}


	protected double returnPIDInput() {
		encVal = Robot.sensors.liftEncoder(false);
		System.out.println(encVal);
		return encVal;
	}

	protected void usePIDOutput(double output) {
		lift(output * -1);
		SmartDashboard.putNumber("Lift PID output", output * -1);
		SmartDashboard.putNumber("Lift Movement output", ((encVal)/360) * (1.9 * Math.PI));
	}
}