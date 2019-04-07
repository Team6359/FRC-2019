package org.usfirst.frc.team6359.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotController {

	private int object = 0; // -1 = not selected, 0 = hatch, 1 = ball
	
	private boolean rBPressed = false;
	private boolean shotBall = false;

	private double startTimeCarry = -1000;
	private boolean debounceY = false;
	
	public void update(boolean a, boolean b, int DPad, boolean rB, boolean lB, boolean back, boolean y, boolean start, boolean start2) {
		// Select Ball/Hatch
		if (a) {
			object = 0;
		} else if (b) {
			object = 1;
		}


		if (y && !debounceY){
			Robot.driveTrain.flipSolenoid();
			debounceY = true;
		}

		if (!y){
			debounceY = false;
		}

		SmartDashboard.putNumber("DPad", DPad);
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
				Robot.intake.runIntake(-0.5);
			} else {
				Robot.intake.runIntake(0);
			}
			
			if (lB) {
				Robot.intake.runIntake(0.5);
				shotBall = true;
			}
		} else if (object == 0){
			if (rB){
				Robot.intake.setVac(true);
				// if (Robot.mathHandler.getCurrentPosition()[2] == RobotMap.setPointHatchFloor[2]){
				// 	Robot.mathHandler.setCurrentPosition(Robot.mathHandler.getCurrentPosition()[0] - 0.5, Robot.mathHandler.getCurrentPosition()[1], Robot.mathHandler.getCurrentPosition()[2]);
				// 	rBPressed = true;
				// 	System.out.println("Here");
				// }
			} else if (lB){
				Robot.intake.setVac(false);
			}
		}

		if (back){
			if (object == 0){
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchCommonLow[0],
						RobotMap.setPointHatchCommonLow[1], RobotMap.setPointHatchCommonLow[2]);
			} else if (object == 1){
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoIntake[0],
						RobotMap.setPointCargoIntake[1], RobotMap.setPointCargoIntake[2]);	
			}	
		}

		if (!rB && !rBPressed){
			Robot.lift.setLastRbPos(Robot.lift.getSetpoint());
		}

		if (!rB && rBPressed){
			Robot.lift.setSetpoint(Robot.lift.getLastRbPos());
		}

		if(start){
			startTimeCarry = System.currentTimeMillis();
			Robot.mathHandler.setCurrentPosition(Robot.mathHandler.getCurrentPosition()[0], Robot.mathHandler.getCurrentPosition()[1], 0);
		}

		if (System.currentTimeMillis() - startTimeCarry > 500 && System.currentTimeMillis() - startTimeCarry < 1000) {
			if (object == 1){
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoTravel[0],
					RobotMap.setPointCargoTravel[1], RobotMap.setPointCargoTravel[2]);
			} else if (object == 0){
				Robot.mathHandler.setCurrentPosition(RobotMap.setPointHatchTravel[0],
					RobotMap.setPointHatchTravel[1], RobotMap.setPointHatchTravel[2]);
			}		
			System.out.println("FOLD");		
		}

		if (start2){
			Robot.mathHandler.setCurrentPosition(RobotMap.setPointTest[0],
						RobotMap.setPointTest[1], RobotMap.setPointTest[2]);
		}

		Robot.sensors.liftLimitLow();
		SmartDashboard.putBoolean("Object Selected", object == 0);
		SmartDashboard.putNumber("Vac Sensor", Robot.sensors.vacSensor());

		
		if (Robot.sensors.wristEncoder(false, false) > 0){
			Robot.sensors.wristEncoder(true, false);
		}

	}

	double startTime = -10000;
	// public void storeBall(){
	// 	startTime = System.currentTimeMillis();

	// }

	// public void updateStoring(){
	// 	double time = System.currentTimeMillis() - startTime;
	// 	if (time < 500) {
	// 		Robot.mathHandler.setCurrentPosition(RobotMap.setPointCargoFloor[0], RobotMap.setPointCargoFloor[1], 0);
	// 	} else if (time >= 500 && time < 1000){
	// 		Robot.wrist.setOverride(true);
	// 	} else if (time >= 1000 && time < 1100){
	// 		Robot.wrist.setOverride(false);
	// 		Robot.sensors.wristEncoder(true, false);
	// 	}
	// }
}