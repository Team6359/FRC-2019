package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

public class SS_Arm extends PIDSubsystem {

	private static final float kP = 0f, kI = 0f, kD = 0f;
	double tolerance = 10; // 1/4 in tolerance
	
	private VictorSPX armMotor;

    // Initialize your subsystem here
    public SS_Arm() {
    	
    	super("Arm", kP, kI, kD);
    	
    	setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);
    	
		armMotor = new VictorSPX(RobotMap.arm);
		
    	setSetpoint(0);
    	enable();
    	
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return Robot.sensors.armEncoder(false);
    }

    protected void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	armMotor.set(ControlMode.PercentOutput, output);
    }
}
