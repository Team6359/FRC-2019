package org.usfirst.frc.team6359.robot.commands;

import org.usfirst.frc.team6359.robot.OI;
import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.subsystems.SS_Lift;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class MoveController extends Command {

	public MoveController() {
		// Use requires() here to declare subsystem dependencies

		requires(Robot.driveTrain);
	}

	protected void initialize() {
		System.out.println("MoveController");

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

		@SuppressWarnings("unused")
		boolean up, down, a, b, x, y, lB, rB, back, start, lClick, rClick, l3, lB2, rB2;
		@SuppressWarnings("unused")
		double lX, lY, rX, rY, lT, rT;
		int DPad, DPad2;
		boolean start2;

		double lT2, rT2;
		double lY2, rY2;

		boolean a2, b2, x2, y2;

		lX = OI.controller1.getRawAxis(0);
		lY = OI.controller1.getRawAxis(1);
		rX = OI.controller1.getRawAxis(4);
		rY = OI.controller1.getRawAxis(5);
		lT = OI.controller1.getRawAxis(2);
		rT = OI.controller1.getRawAxis(3);

		lY2 = OI.controller2.getRawAxis(1);
		rY2 = OI.controller2.getRawAxis(5);

		a = OI.controller1.getRawButton(1);
		b = OI.controller1.getRawButton(2);
		x = OI.controller1.getRawButton(3);
		y = OI.controller1.getRawButton(4);
		lB = OI.controller1.getRawButton(5);
		rB = OI.controller1.getRawButton(6);
		back = OI.controller1.getRawButton(7);
		start = OI.controller1.getRawButton(8);
		lClick = OI.controller1.getRawButton(9);
		rClick = OI.controller1.getRawButton(10);
		DPad = OI.controller1.getPOV();
		DPad2 = OI.controller2.getPOV();

		up = OI.controller1.getRawButton(10);
		down = OI.controller1.getRawButton(9);

		rT2 = OI.controller2.getRawAxis(3);
		lT2 = OI.controller2.getRawAxis(2);

		a2 = OI.controller2.getRawButton(1);
		b2 = OI.controller2.getRawButton(2);
		x2 = OI.controller2.getRawButton(3);
		y2 = OI.controller2.getRawButton(4);

		lB2 = OI.controller2.getRawButton(5);
		rB2 = OI.controller2.getRawButton(6);

		start2 = OI.controller2.getRawButton(8);

		Robot.driveTrain.ControllerDrive(lX, lY, rX, rY, DPad2);
		if (rClick) {
			Robot.driveTrain.setSlowMode(true);
		}

		if (lClick) {
			Robot.driveTrain.setSlowMode(false);
		}

		Robot.robotController.update(a, b, DPad, rB, lB, back, y, start, start2);

		Robot.mathHandler.setCurrentPosition(Robot.mathHandler.getCurrentPosition()[0] + 10 * (rT - lT),
				Robot.mathHandler.getCurrentPosition()[1],
				Robot.mathHandler.getCurrentPosition()[2] + (Math.abs(rY2) > 0.2 ? (rY2 * 6) : 0));

		Robot.lift.triggerAggregate(rT - lT);

		Robot.wrist.sendBypass(rY2);
		Robot.arm.delayBypass(lY2, x2, rB2, lB2, y2);

		if (a2) {
			Robot.wrist.setSetpoint(0);
			Robot.wrist.customSetpoint(0);
			Robot.mathHandler.setCurrentPosition(Robot.mathHandler.getCurrentPosition()[0],
					Robot.mathHandler.getCurrentPosition()[2], 0);
			Robot.sensors.wristEncoder(true, false);
		}

		if (b2) {
			Robot.arm.setSetpoint(0);
			Robot.arm.customSetpoint(0);
			Robot.mathHandler.setCurrentPosition(Robot.mathHandler.getCurrentPosition()[0], 0,
					Robot.mathHandler.getCurrentPosition()[2]);
			Robot.sensors.armEncoder(true, false);
		}

		if (start) {
			Robot.lift.getPIDController().reset();
		} else {
			Robot.lift.enable();
		}

		Robot.lift.enable();
		Robot.mathHandler.update();
		Robot.intake.update();
		Robot.lift.update();
		Robot.lift.sendX(x);

		// Robot.lift.lift(0);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}