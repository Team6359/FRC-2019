package org.usfirst.frc.team6359.robot.commands;

import org.usfirst.frc.team6359.robot.OI;
import org.usfirst.frc.team6359.robot.Robot;

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
		int DPad;
		

		lX = OI.controller1.getRawAxis(0);
		lY = OI.controller1.getRawAxis(1);
		rX = OI.controller1.getRawAxis(4);
		rY = OI.controller1.getRawAxis(5);
		lT = OI.controller1.getRawAxis(2);
		rT = OI.controller1.getRawAxis(3);
		

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
		
		up = OI.controller1.getRawButton(10);
		down = OI.controller1.getRawButton(9);
		

		Robot.driveTrain.ControllerDrive(lX, lY, rX, rY);
		
		Robot.robotController.update(start, rB, DPad, rB, lB, back);
		
		Robot.mathHandler.update();
		
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
