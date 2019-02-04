package org.usfirst.frc.team6359.robot;

public class RobotController {

	private int object = -1; // -1 = not selected, 0 = hatch, 1 = ball
	
	private boolean shotBall = false;
	
	public void update(boolean a, boolean b, int DPad, boolean rB, boolean lB, boolean back) {
		// Select Ball/Hatch
		if (a) {
			object = 0;
		} else if (b) {
			object = 1;
		}

		// Do Command
		if (object == 0) {
			switch (DPad) {
			case -1:
				break;
			case 0:
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchRocketHigh[0],
						RobotMap.setPointHatchRocketHigh[1], RobotMap.setPointHatchRocketHigh[2]);
				break;
			case 90:
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchRocketMid[0],
						RobotMap.setPointHatchRocketMid[1], RobotMap.setPointHatchRocketMid[2]);
				break;
			case 180:
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchCommonLow[0],
						RobotMap.setPointHatchCommonLow[1], RobotMap.setPointHatchCommonLow[2]);	
				break;
			case 270:
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchFloor[0],
						RobotMap.setPointHatchFloor[1], RobotMap.setPointHatchFloor[2]);	
				break;
			}
		} else if(object == 1) {
				switch (DPad) {
				case -1:
					break;
				case 0:
					Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoRocketHigh[0],
							RobotMap.setPointCargoRocketHigh[1], RobotMap.setPointCargoRocketHigh[2]);
					break;
				case 90:
					Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoRocketMid[0],
							RobotMap.setPointCargoRocketMid[1], RobotMap.setPointCargoRocketMid[2]);
					break;
				case 180:
					Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoRocketLow[0],
							RobotMap.setPointCargoRocketLow[1], RobotMap.setPointCargoRocketLow[2]);	
					break;
				case 270:
					Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoFloor[0],
							RobotMap.setPointCargoFloor[1], RobotMap.setPointCargoFloor[2]);	
					break;
				}
			}
		
		if (object == 1) {
			
			if (rB) {
				Robot.intake.runIntake(1);
				if(!Robot.intake.getClamp()) {
					Robot.intake.setClamp(true);
				}
			} else {
				Robot.intake.runIntake(0);
			}
			
			if (lB) {
				Robot.intake.runIntake(-1);
				shotBall = true;
			}
			
			if (!lB && shotBall) {
				Robot.intake.setClamp(false);
			}
			
			
		}
		

	}
}
