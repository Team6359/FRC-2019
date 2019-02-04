package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class SS_Intake extends Subsystem {

    private boolean pneumatic = false;
	private Solenoid solenoid;
	private SpeedController belts;

    public SS_Intake() {
    	solenoid = new Solenoid(RobotMap.solenoidIntake);
    	belts = new Victor(RobotMap.intake);
    }
    
    public void update() {
    	solenoid.set(pneumatic);
    }
    
    public void runIntake(double speed) {
    	belts.set(speed);
    }
	
    public boolean getClamp() {
    	return solenoid.get();
    }
    
    public void setClamp(boolean clamp) {
    	solenoid.set(clamp);
    }
    
    public void extendPneumatic() {
    	pneumatic = true;
    }
    
    public void retractPneumatic() {
    	pneumatic = false;
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

