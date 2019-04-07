package org.usfirst.frc.team6359.robot;

import edu.wpi.first.wpilibj.Joystick;

public class OI {
	public static Joystick controller1;
	public static Joystick controller2;

	public OI() {
		controller1 = new Joystick(0);
		controller2 = new Joystick(1);
	}
}
