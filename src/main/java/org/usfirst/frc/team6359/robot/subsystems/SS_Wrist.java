package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SS_Wrist extends PIDSubsystem {

	private static final float kP = 0f, kI = 0f, kD = 0f;
	double tolerance = 10; // 1/4 in tolerance
	
	private VictorSPX wristMotor;

	
    // Initialize your subsystem here
    public SS_Wrist() {
    	
    	super("Wrist", kP, kI, kD);
    	
    	setAbsoluteTolerance(tolerance);
		setOutputRange(-1, 1);
    	
    	setSetpoint(0);
    	enable();
    	
    	wristMotor = new VictorSPX(RobotMap.wrist);
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }


    protected double returnPIDInput() {
        SmartDashboard.putNumber("Wrist Encoder", Robot.sensors.wristEncoder(false, false));
        return 1 - Math.cos(Math.toRadians(Robot.sensors.wristEncoder(false, true) + Robot.sensors.armEncoder(false, true)));
    }

    protected void usePIDOutput(double output) {
      //  wristMotor.set(ControlMode.PercentOutput, output);
    }
}
