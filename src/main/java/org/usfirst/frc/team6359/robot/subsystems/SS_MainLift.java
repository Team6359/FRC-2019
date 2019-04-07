package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * Add your docs here.
 */
public class SS_MainLift extends PIDSubsystem {

	public static SpeedController lift1, lift2, lift3;
	public static double encVal;

	public static Solenoid brake1, brake2;

	public static int tolerance = 20;

	private boolean xPressed = false;

	public static float kP = 0.0048f, kI = 0f, kD = 0.0f;

	private double lastRbPos = 0;

	public SS_MainLift() {
		super("MainLift", kP, kI, kD);

		setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);

		setSetpoint(0);
		enable();

		lift1 = new Victor(RobotMap.lift1); // down
		lift2 = new Victor(RobotMap.lift2); // up
		lift3 = new Victor(RobotMap.lift3); // up

		brake1 = new Solenoid(RobotMap.solenoidBrake1);
		brake2 = new Solenoid(RobotMap.solenoidBrake2);

	}

	@Override
	public void initDefaultCommand() {

	}

	public void lift(double speed) {
		System.out.println(speed);
		encVal = Robot.sensors.liftEncoder(false);
		if (xPressed) {
			lift1.set(0);
			lift2.set(0);
			lift3.set(0);
			return;
		}

		if (encVal > getSetpoint()) {
			lift1.set(0);
			lift2.set(0);
			lift3.set(0);
		} else {
			lift1.set(speed * -1);
			lift2.set(speed);
			lift3.set(speed);
		}
	}

	public void setLiftBrake(boolean brake) {
		brake1.set(!brake);
		brake2.set(brake);
		SmartDashboard.putBoolean("Brake", brake);
	}

	boolean overRide = false;

	private double newSetpoint = 0;
	private boolean triggerOn = false;

	public void setSetpoint2(double setPoint) {
		newSetpoint = setPoint;
	}

	public void update() {
		if (Math.abs(Robot.sensors.liftEncoder(false) - getSetpoint()) < tolerance && !xPressed && !triggerOn) {
			setLiftBrake(true);
		} else {
			setLiftBrake(false);
		}
		enable();

		if (Robot.sensors.liftLimitLow()) {
			Robot.sensors.liftEncoder(true);
			if (getSetpoint() == 0) {
				overRide = true;
			}
		}

		if (getSetpoint() != 0) {
			overRide = false;
		}

		if (Robot.arm.getSetpoint() == 0) {
			setSetpoint(newSetpoint);
		}

		SmartDashboard.putBoolean("Lift PID Enabled", getPIDController().isEnabled());
		SmartDashboard.putNumber("Lift Setpoint", getSetpoint());
	}

	public void sendX(boolean x) {
		xPressed = x;
	}

	public void triggerAggregate(double trigger) {
		triggerOn = Math.abs(trigger) > 0.1;
	}

	protected double returnPIDInput() {
		encVal = Robot.sensors.liftEncoder(false);
		return encVal;
	}

	protected void usePIDOutput(double output) {
		if (!overRide)
			lift(output * -1);
		else
			lift(0);
		SmartDashboard.putNumber("Lift PID Output", output * -1);
	}

	public void setLastRbPos(double rBPos) {
		lastRbPos = rBPos;
	}

	public double getLastRbPos() {
		return lastRbPos;
	}
}
