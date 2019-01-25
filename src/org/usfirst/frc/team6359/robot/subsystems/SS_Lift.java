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
//		leftWheelMotor = new Spark(RobotMap.liftWheelLeft);
//		rightWheelMotor = new Spark(RobotMap.liftWheelRight);
//		lift1 = new Spark(RobotMap.liftMotor1);
//		lift2 = new Spark(RobotMap.liftMotor2);

		leftWheelMotor.setInverted(false);
		rightWheelMotor.setInverted(true);
		lift1.setInverted(false);
		lift2.setInverted(false);

		encVal = Robot.sensors.liftEncoder(true);

		setSetpoint(0);

		enable();

	}

	public void runWheels(double speedLeft, double speedRight) {
		// if ((speedLeft > 0 || speedRight > 0) && !Robot.sensors.cubeIntake()) {
		// speedLeft = 0;
		// speedRight = 0;
		// }
		leftWheelMotor.set(speedLeft);
		rightWheelMotor.set(speedRight);

	}

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	public void Control(double lT, double rT, boolean lB, boolean rB, int DPad, boolean a, boolean back,
			boolean start, double lT2, double rT2, boolean lClick, boolean lB2, boolean rB2) {

//		if (Robot.sensors.liftLimitLow() && !limitDebounce) {
//			Robot.sensors.liftEncoder(true); // Reset to zero at bottom
//			liftPos = 0;
//			limitDebounce = true;
//		} else {
//			limitDebounce = false;
//		}

		double inputSpeed = rT - lT;
		double overrideSpeed = rT2 - lT2;
		if (rB || (lB && rB))
			runWheels(1, 1);
		else if (lB)
			runWheels(-1, -1);
		else
			runWheels(0, 0);

		if (start)
			runWheels(1, -0.5);

		if (back)
			runWheels(-0.5, 1);

		boolean up = DPad == 0;
		boolean right = DPad == 90;
		boolean down = DPad == 180;
		boolean left = DPad == 270;
		
		if (lB2) {
			overRide = true;
		}
		
		if (rB2) {
			overRide = false;
		}
		
		if (Math.abs(inputSpeed) < 0.1 && !liftReset) {
			liftReset = true;
		} else if (Math.abs(inputSpeed) >= 0.1) {
			double futSetPoint = Robot.sensors.liftEncoder(false) + inputSpeed * Math.abs(inputSpeed) * 10000;
			if (futSetPoint < 5000 && futSetPoint < getSetpoint() && !allowUp) {
				cutPower = true;
				allowUp = true;
				futSetPoint = getSetpoint();
			}else if (futSetPoint > 5000){
				allowUp = false;
			}else {
				cutPower = false;
			}
			if (futSetPoint > 45000) {
				futSetPoint = Robot.sensors.liftEncoder(false) + inputSpeed * Math.abs(inputSpeed) * 4000;
			}
			setSetpoint(futSetPoint);
			liftReset = false;
		}
		
		if (Robot.sensors.liftEncoder(false) < 0) {
			Robot.sensors.liftEncoder(true);
		}
		
		if (Math.abs(overrideSpeed) > 0.1) {
			disable();
			Lift(overrideSpeed);
			setPointDebounce = false;
		} else if (!setPointDebounce){
			setSetpoint(Robot.sensors.liftEncoder(false));
			enable();
			setPointDebounce = true;
		}
		
		if (overRide) {
			disable();
			Lift(inputSpeed);
			setPointDebounce = false;
		} else if (!setPointDebounce) {
			setSetpoint(Robot.sensors.liftEncoder(false));
			enable();
			setPointDebounce = true;
		}
		
		SmartDashboard.putNumber("DPad", DPad);
		if (up && !debounce) {
			increment();
			System.out.println("INCREMENT");
		} else if (down && !debounce) {
			decrement();
			System.out.println("DECREMENT");
		} else if (right && !debounce) {
			liftPos = 3;
			liftTo(3);
		} else if (left && !debounce) {
			liftPos = 1;
			liftTo(1);
		}
		
		if (lClick) {
			Robot.sensors.liftEncoder(true);
			setSetpoint(0);
		}

		debounce = up || down;

		System.out.println("ENC: " + Robot.sensors.liftEncoder(false));
		Robot.sensors.liftEncoder(false);
		Robot.sensors.cubeIntake();

		SmartDashboard.putNumber("LIFTSETPOINT", getSetpoint());

		if (ACCELSCALE < 1) {
			ACCELSCALE += ACCELRATE;
		}

	}

	public void resetEnc() {
		Robot.sensors.liftEncoder(true);
	}

	public void Lift(double speed) {

		boolean liftLimitHigh = Robot.sensors.liftLimitHigh();
		boolean liftLimitLow = Robot.sensors.liftLimitLow();
		encVal = Robot.sensors.liftEncoder(false);
//		if (Robot.bypassLimits) {
//			liftLimitHigh = false;
//			liftLimitLow = false;
//		}

		if (speed == 0) {
			lift1.set(0);
			lift2.set(0);
		}

		if (speed > 0 && !liftLimitHigh) {
			lift1.set(speed * 0.8);
			lift2.set(speed * 0.8);
		} else if (speed > 0) {
			lift1.set(0);
			lift2.set(0);
		}

		if (speed < 0 && !liftLimitLow) {
			lift1.set(speed * 0.65);
			lift2.set(speed * 0.65);
		} else if (speed < 0) {
			lift1.set(0);
			lift2.set(0);
		}

		if (getPIDController().isEnabled()) {
			if (getSetpoint() <= encVal) {
				// Going up
				getPIDController().setPID(0.0001, 0.0, 0.0);
			} else {
				// Going down
				getPIDController().setPID(0.0001, 0.0, 0.0);
			}
		}

		SmartDashboard.putNumber("Lift Speed", speed);
		SmartDashboard.putNumber("Lift Enc", encVal);
	}

	public void increment() {
		if (liftPos < 4)
			liftTo(++liftPos);
		enable();
		ACCELSCALE = 1;
	}

	public void decrement() {
		if (liftPos > 0) {
			liftTo(--liftPos);
		}
		enable();
		ACCELSCALE = 1;
	}

	public void liftTo(int index) {
		SmartDashboard.putNumber("Index", index);
		ACCELSCALE = 0;
		switch (index) {
		case 0:
			setSetpoint(RobotMap.liftSetPointFloor);
			break;
		case 1:
			setSetpoint(RobotMap.liftSetPointDrive);
			break;
		case 2:
			setSetpoint(RobotMap.liftSetPointSwitch);
			break;
		case 3:
			setSetpoint(RobotMap.liftSetPointScaleNeutral);
			break;
		case 4:
			setSetpoint(RobotMap.liftSetPointScaleHigh);
		}
	}

	protected double returnPIDInput() {
		return encVal;
	}

	protected void usePIDOutput(double output) {
		if (cutPower) {
			Lift(0);
		}else {
			Lift(output * ACCELSCALE);
			Lift(output * ACCELSCALE);	
		}
		SmartDashboard.putNumber("PID ", output);
		SmartDashboard.putNumber("Setpoint", getSetpoint());
	}
}