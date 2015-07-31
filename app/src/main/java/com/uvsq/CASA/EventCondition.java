package com.uvsq.CASA;

import android.util.Log;

import com.uvsq.CASA.GlobalData;
import com.uvsq.Util;

public class EventCondition {
	
	//********************************************
	public static boolean isExcessiveSpeeding() {
		boolean excessiveSpeeding = false;
		boolean condition1 = (GlobalData.new_VehicleSpeed > GlobalData.new_SpeedLimit);
		boolean condition2 = (GlobalData.new_VehicleSpeed <= GlobalData.new_SpeedLimit * 1.20);
		excessiveSpeeding = (condition1 && condition2);
		return excessiveSpeeding;
	}//end method

	//********************************************
	public static boolean isDangerousSpeeding() {
		boolean dangerousSpeeding = false;
		boolean condition1 = (GlobalData.new_VehicleSpeed > GlobalData.new_SpeedLimit * 1.20);
		dangerousSpeeding = condition1;
		return dangerousSpeeding;
	}//end method
	
	//*********************************************
	public static boolean isStopSignalDetected() {
		boolean stopDetected = false;
		boolean condition1 = (GlobalData.new_IntersectionDistance >= 40) && (GlobalData.new_IntersectionDistance <= 80);
		boolean condition2 = (GlobalData.new_IntersectionType == 0);
		stopDetected = (condition1 && condition2);
		return stopDetected;
	}//end method
	
	//****************************************
	public static boolean isStoppingAtStop() {
		boolean stoppingAtStop = false;
		int intersectionType = GlobalData.new_IntersectionType;
		double intersectionDistance = GlobalData.new_IntersectionDistance;
		double speed = GlobalData.new_VehicleSpeed;
		boolean condition1 = ((intersectionDistance >= 2.0) && (intersectionDistance <= 20.0));
		boolean condition2 = (intersectionType == 0);
		boolean condition3 = (speed < 8.0);
		stoppingAtStop = (condition1 && condition2 && condition3);
		return stoppingAtStop;
	}//end method
	
	//***************************************
	public static boolean isLeftSignalOff() {
		boolean leftSignalOff = false;



		int direction = GlobalData.new_IntersectionDirectionFromOktal;
		int directionClignotant = GlobalData.new_DirectionSignal;

		//Log.i(Util.TAG, "isLeftSigalOff: "+"direction: "+direction+" directionClignotant: "+directionClignotant);

		boolean condition1 = (direction == 1);
		boolean condition2 = (directionClignotant != 1);
		leftSignalOff = (condition1 && condition2);
		return leftSignalOff;
	}//end method
	
	//*****************************************
	public static boolean isRightSignalOff() {
		boolean rightSignalOff = false;
		int direction = GlobalData.new_IntersectionDirectionFromOktal;
		int directionClignotant = GlobalData.new_DirectionSignal;

		//Log.i(Util.TAG, "isRightSignalOff: "+"direction: "+direction+" directionClignotant: "+directionClignotant);

		boolean condition1 = (direction == 2);
		boolean condition2 = (directionClignotant != -1);
		rightSignalOff = (condition1 && condition2);
		return rightSignalOff;
	}//end method
	
	//******************************************
	public static boolean isStartOfFoggyZone() {
		boolean foggyZone = false;
		double fogDistance = GlobalData.new_FogVisibilityDistance;
		boolean condition1 = (fogDistance >= 0.0) && (fogDistance <= 300.0);
		foggyZone = condition1;
		return foggyZone;
	}//end method
	
	//******************************************
	public static boolean isWithinFoggyZone() {
		boolean inFoggyZone = false;
		double fogDistance = GlobalData.new_FogVisibilityDistance;
		boolean condition1 = (fogDistance >= 0) && (fogDistance <= 300);
		boolean condition2 = (GlobalData.old_FogVisibilityDistance >= 3000);
		inFoggyZone = (condition1 && condition2);
		return inFoggyZone;
	}//end method
	
	
	//******************************************************
	public static boolean isExcessiveSpeedingInFoggyZone() {
		boolean excessiveSpeeding = false;
		double speed = GlobalData.new_VehicleSpeed;
		double speedLimitInFoggyZone = 50;
		
		boolean condition1 = (speed > speedLimitInFoggyZone);
		boolean condition2 = (speed <= speedLimitInFoggyZone * 1.20); 
		excessiveSpeeding = (condition1 && condition2);
		return excessiveSpeeding;
	}//end method
	
	
	//******************************************************
	public static boolean isDangerousSpeedingInFoggyZone() {
		boolean dangerousSpeeding = false;
		double speed = GlobalData.new_VehicleSpeed;
		double speedLimitInFoggyZone = 50;
		boolean condition1 = (speed > speedLimitInFoggyZone * 1.20);
		dangerousSpeeding = condition1;
		return dangerousSpeeding;
	}//end method
	
	
	//******************************************
	public static boolean isleavingFoggyZone() {
		boolean leavingFoggyZone = false;
		double fogDistance = GlobalData.new_FogVisibilityDistance;
		boolean condition1 = (fogDistance >= 310);
		boolean condition2 = (GlobalData.old_FogVisibilityDistance >= 0) && (GlobalData.old_FogVisibilityDistance <= 300);
		leavingFoggyZone = (condition1 && condition2);
		return leavingFoggyZone;
	}//end method
	
	// ******************************************
	public static boolean obstacleIsPresent() {
		
		boolean obstaclePresent = false;
		double timeToCollision = GlobalData.new_ObstacleTimeToCollision;
		if ((timeToCollision > 0) && (timeToCollision <= 5))
			obstaclePresent = true;
		else {
			obstaclePresent = false;
		}
		return obstaclePresent;
	}// end method
	
	
	//***********************************************************
	public static boolean isVehicularObstacleDistanceDangerous(){
		boolean dangerousVehicleSafetyDistance = false;
		double speed = GlobalData.new_VehicleSpeed;
		double obstacleSpeed = GlobalData.new_ObstacleSpeed;
		int obstacleType = GlobalData.new_ObstacleType;
		boolean condition1 = (obstacleType == 1);
		boolean condition2 = (speed > obstacleSpeed);
		boolean condition3 = obstacleIsPresent();
		dangerousVehicleSafetyDistance = (condition1 && condition2 && condition3);
		return dangerousVehicleSafetyDistance;
	}//end method
	

	// *******************************************
	public static boolean pedestrianIsPresent() {
	// *******************************************
		boolean obstaclePresent = false;
		int obstacleType = GlobalData.new_ObstacleType;
		double timeToCollision = GlobalData.new_ObstacleTimeToCollision;
		if (obstacleType == 2) {
			if ((timeToCollision > 0) && (timeToCollision <= 6))
				obstaclePresent = true;
			else {
				obstaclePresent = false;
			}//end if-else
		}// end if
		return obstaclePresent;
	}// end method
	
	//**************************************************
	public static boolean isPedestrianObstaclePresent() {
		boolean pedestrianDistanceDangerous = false;
		int obstacleType = GlobalData.new_ObstacleType;
		boolean condition1 = (obstacleType == 2);
		boolean condition2 = pedestrianIsPresent();
		pedestrianDistanceDangerous = (condition1 && condition2);
		return pedestrianDistanceDangerous;
	}//end method
	
	
	//*************************************************
	public static boolean isRunningOnPedestrianLane() {
		boolean runningOnPedestrianLane = false;
		int obstacleType = GlobalData.new_ObstacleType;
		double obstacleDistance = GlobalData.new_ObstacleDistance;
		double speed = GlobalData.new_VehicleSpeed;
		boolean condition1 = (obstacleType == 2);
		boolean condition2 = (obstacleDistance >= 0) && (obstacleDistance <= 15);
		boolean condition3 = (speed > 5); 
		runningOnPedestrianLane = (condition1 && condition2 && condition3);
		return runningOnPedestrianLane;
	}//end method
	
	
	//**************************************
	public static boolean isDriverTired() {
		boolean driverTired = false;
		int driverIsFatigued = GlobalData.new_DriverDisturbance;
		boolean condition1 = (driverIsFatigued == 1);
		driverTired = condition1;
		return driverTired;
	}//end method
	
	
}//end class
