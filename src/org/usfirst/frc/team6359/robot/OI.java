package org.usfirst.frc.team6359.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	public static Joystick controller1;
	
	public OI() {
		controller1 = new Joystick(0);
	}
}
