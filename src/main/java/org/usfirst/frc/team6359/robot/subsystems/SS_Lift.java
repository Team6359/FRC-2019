package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Preferences;

public class SS_Lift extends PIDSubsystem {

	public static SpeedController lift1, lift2, lift3;
	public static double encVal;

	Preferences prefs;

	double tolerance = 1; // 1/4 in tolerance

	boolean manual = false;

	double triggerTolerance = 0.5;

	double cpi = 819 / 20;
	
	public static Solenoid brake1, brake2;


	public int liftPos = 0; // Start in drive position
	private static float kP1 = 0.0048f, kI1 = 0f, kD1 = 0.0f;
	private static float kP2 = 0.0048f, kI2 = 0f, kD2 = 0f;



	public SS_Lift() {

		super("Lift", kP1, kI1, kD1);

		encVal = Robot.sensors.liftEncoder(true);
		setOutputRange(-1, 1);

		//setSetpoint(0);
		enable();
		setSetpoint(0);
		
		lift1 = new Victor(RobotMap.lift1); //down
		lift2 = new Victor(RobotMap.lift2); //up
		lift3 = new Victor(RobotMap.lift3); //up
		
		brake1 = new Solenoid(RobotMap.solenoidBrake1);
		brake2 = new Solenoid(RobotMap.solenoidBrake2);

		setAbsoluteTolerance(1);
	}

	public void initDefaultCommand() {
		
	}
	
	public void lift(double speed) {
		if(Math.abs(getPosition() - getSetpoint()) < tolerance) {
			setLiftBrake(true);
		} else {
			setLiftBrake(false);
		}
		encVal = Robot.sensors.liftEncoder(false);
		if (encVal > getSetpoint()){
			lift1.set(0);
			lift2.set(0);
			lift3.set(0);
		} else {
			lift1.set(speed * -1);
			lift2.set(speed);
			lift3.set(speed);
		}

		if (getSetpoint() > 1272){
			getPIDController().setPID(kP2, kI2, kD2);
		}

	}
//1272
	public void resetEnc() {
		Robot.sensors.liftEncoder(true);
	}

	public void setLiftBrake(boolean brake){
		brake1.set(!brake);
		brake2.set(brake);
	}


	protected double returnPIDInput() {
		encVal = Robot.sensors.liftEncoder(false);
		return encVal;
	}

	protected void usePIDOutput(double output) {
		lift(output * -1);
	}
}