package org.usfirst.frc.team6359.robot.subsystems;

import org.usfirst.frc.team6359.robot.RobotMap;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SS_Intake extends Subsystem {

    private boolean vacRunning = false;
    private SpeedController belts, vacuum, vacuum2;

    private double releaseTime = -1000;
    
    private Solenoid vacSolenoid1, vacSolenoid2;

    public SS_Intake() {
        belts = new Victor(RobotMap.intake);
        vacuum = new Victor(RobotMap.vacuum);
        vacuum2 = new Victor(RobotMap.vacuum2);


        vacSolenoid1 = new Solenoid(RobotMap.solenoidVac1);
        vacSolenoid2 = new Solenoid(RobotMap.solenoidVac2);
    }


  
    public void update() {    
        vacuum.set(vacRunning ? 0.5 : 0.0);
        vacuum2.set(vacRunning ? 0.5 : 0.0);
        if (System.currentTimeMillis() - releaseTime >= 0.25){
            vacSolenoid1.set(true);
            vacSolenoid2.set(false);
        }
    }
   
    public void runIntake(double speed) {
    	belts.set(speed);
    }

    public void setVac(boolean on){
        vacRunning = on;
        if (!on){
            vacSolenoid1.set(false);
            vacSolenoid2.set(true);
            releaseTime = System.currentTimeMillis();
        }
    }

    public void initDefaultCommand() {
    }
}