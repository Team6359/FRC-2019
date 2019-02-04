package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SS_Lift extends PIDSubsystem {

	public static SpeedController leftWheelMotor, rightWheelMotor, lift1, lift2;
	public static double encVal;

	public static double ACCELSCALE = 1;
	public static double ACCELRATE = 0.05;

	double tolerance = 10; // 1/4 in tolerance

	boolean manual = false;

	double triggerTolerance = 0.5;

	public int liftPos = 0; // Start in drive position

	boolean debounce = false;
	boolean limitDebounce = false;
	boolean liftReset = false;
	boolean softLimitLow = false;
	boolean allowUp = false;
	boolean cutPower = false; 
	boolean setPointDebounce = false;
	boolean overRide = false;
	
	public SS_Lift() {

		super("Lift", 0.00000013, 0.0, 0.0);
		setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);

		encVal = Robot.sensors.liftEncoder(true);

		setSetpoint(0);

		enable();

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}


	public void resetEnc() {
		Robot.sensors.liftEncoder(true);
	}


	protected double returnPIDInput() {
		return encVal;
	}

	protected void usePIDOutput(double output) {
		
	}
}