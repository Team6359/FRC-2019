package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.Robot;
import org.usfirst.frc.team6359.robot.RobotMap;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.wpi.first.wpilibj.command.PIDSubsystem;

/**
 *
 */
public class SS_Wrist extends PIDSubsystem {

	private static final float kP = 0.009f, kI = 0f, kD = 0f;
    double tolerance = 10; // 1/4 in tolerance
    double newSetpoint = 0;
    double startTime = 0;
    boolean overRide = false;
    
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

    public void customSetpoint(double setpoint){
        if (setpoint != newSetpoint)
            startTime = System.currentTimeMillis();
        newSetpoint = setpoint;
    }
    double rY2;
//MoveLift1
    private double moveSpeed = 25;
    protected double returnPIDInput() {

        if (getSetpoint() == 0 && Math.abs(Robot.sensors.wristEncoder(false, false)) < 100){
            Robot.sensors.wristEncoder(true, true);
            overRide = true;
        } else {
            overRide = false;
        }

      

        if (System.currentTimeMillis() - startTime > 500 && Robot.arm.getSetpoint() == 0){
            if (newSetpoint < getSetpoint() && Robot.arm.getSetpoint() == 0 && Math.abs(newSetpoint - getSetpoint()) > 25){
                setSetpoint(getSetpoint() - moveSpeed);
            } else if (newSetpoint > getSetpoint() && Robot.arm.getSetpoint() == 0 && Math.abs(newSetpoint - getSetpoint()) > 25){
                setSetpoint(getSetpoint() + moveSpeed);
            }
        } 

        if (Math.abs(rY2) > 0.2){
            setSetpoint(newSetpoint);
        }

       

        SmartDashboard.putNumber("Setpoint Wrist", -1 * getSetpoint());
        SmartDashboard.putNumber("Wrist Encoder", Robot.sensors.wristEncoder(false, false));
    
        return Robot.sensors.wristEncoder(false, false);
    }

    protected void usePIDOutput(double output) {
        double motorSpeed = output;
        if (motorSpeed < 0 && motorSpeed > -0.11){
            motorSpeed = -0.1;
        }
        SmartDashboard.putNumber("Wrist output", motorSpeed);
        wristMotor.set(ControlMode.PercentOutput, overRide ? 0 : motorSpeed);
       // System.out.println(output);
    }

    public boolean atSetpoint(){
        return Math.abs(newSetpoint - getSetpoint()) <= 25;
    }

    public void setOverride(boolean input){
        overRide = input;
    }

    public void sendBypass(double input){
        rY2 = input;  
    }
}

//Hatch Vertical -509
//Hatch floor -1705