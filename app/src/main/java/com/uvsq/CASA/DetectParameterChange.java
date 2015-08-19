package com.uvsq.CASA;


import com.uvsq.connect2datanex.GetNSet;

public class DetectParameterChange<E> {

	private GetData<E> getData = new GetData<>();
	private GetNSet<E> getNSet;

	public DetectParameterChange(GetNSet<E> getNSet){
		this.getNSet = getNSet;
	}
	
	//**********************************************
	public void detectChange_VehicleSpeed(){
		GlobalData.new_VehicleSpeed = getData.getVehicleSpeed(getNSet);
		if (GlobalData.new_VehicleSpeed != GlobalData.old_VehicleSpeed)
			GlobalData.changed_VehicleSpeed = true;
		else
			GlobalData.changed_VehicleSpeed = false;
			
	}//end method

	
	//*******************************************
	public  void detectChange_SpeedLimit(){
		GlobalData.new_SpeedLimit = getData.getSpeedLimit(getNSet);
		if (GlobalData.new_SpeedLimit != GlobalData.old_SpeedLimit)
			GlobalData.changed_SpeedLimit = true;
		else
			GlobalData.changed_SpeedLimit = false;
	}//end method
	
	
	//*****************************************************
	public  void detectChange_IntersectionDistance(){
		GlobalData.new_IntersectionDistance = getData.getIntersectionDistance(getNSet);
		if (GlobalData.new_IntersectionDistance != GlobalData.old_IntersectionDistance)
			GlobalData.changed_IntersectionDistance = true;
		else
			GlobalData.changed_IntersectionDistance = false;
	}//end method
	
	
	//***********************************************************
	public  void detectChange_IntersectionSignalDistance(){
		GlobalData.new_IntersectionSignalDistance = getData.getIntersectionSignalDistance(getNSet);
		if (GlobalData.new_IntersectionSignalDistance != GlobalData.old_IntersectionSignalDistance)
			GlobalData.changed_IntersectionSignalDistance = true;
		else
			GlobalData.changed_IntersectionSignalDistance = false;
	}//end method
		
	
	//*************************************************
	public  void detectChange_IntersectionType(){
		GlobalData.new_IntersectionType = getData.getIntersectionType(getNSet);
		if (GlobalData.new_IntersectionType != GlobalData.old_IntersectionType)
			GlobalData.changed_IntersectionType = true;
		else
			GlobalData.changed_IntersectionType = false;
	}//end method
	
	
	//************************************************
	public  void detectChange_DirectionSignal(){
		GlobalData.new_DirectionSignal = getData.getDirectionSignal(getNSet);
		if (GlobalData.new_DirectionSignal != GlobalData.old_DirectionSignal)
			GlobalData.changed_DirectionSignal = true;
		else
			GlobalData.changed_DirectionSignal = false;
	}//end method
	
	
	//*******************************************************
	public  void detectChange_FogVisibilityDistance(){
		GlobalData.new_FogVisibilityDistance = getData.getFogVisibilityDistance(getNSet);
		if (GlobalData.new_FogVisibilityDistance != GlobalData.old_FogVisibilityDistance)
			GlobalData.changed_FogVisibilityDistance = true;
		else
			GlobalData.changed_FogVisibilityDistance = false;
	}//end method
	
	
	//**********************************************
	public  void detectChange_ObstacleType(){
		GlobalData.new_ObstacleType = getData.getObstacleType(getNSet);
		if (GlobalData.new_ObstacleType != GlobalData.old_ObstacleType)
			GlobalData.changed_ObstacleType = true;
		else
			GlobalData.changed_ObstacleType = false;
			
	}//end method
	
	
	//**********************************************
	public  void detectChange_ObstacleSpeed(){
		GlobalData.new_ObstacleSpeed = getData.getObstacleSpeed(getNSet);
		if (GlobalData.new_ObstacleSpeed != GlobalData.old_ObstacleSpeed)
			GlobalData.changed_ObstacleSpeed = true;
		else
			GlobalData.changed_ObstacleSpeed = false;
	}//end method
	
	
	//**************************************************
	public  void detectChange_ObstacleDistance(){
		GlobalData.new_ObstacleDistance = getData.getObstacleDistance(getNSet);
		if (GlobalData.new_ObstacleDistance != GlobalData.old_ObstacleDistance)
			GlobalData.changed_ObstacleDistance = true;
		else
			GlobalData.changed_ObstacleDistance = false;
	}//end method
	
	
	//*********************************************************
	public  void detectChange_ObstacleTimeToCollision(){
		GlobalData.new_ObstacleTimeToCollision = getData.getObstacleTimeToCollision(getNSet);
		if (GlobalData.new_ObstacleTimeToCollision != GlobalData.old_ObstacleTimeToCollision)
			GlobalData.changed_ObstacleTimeToCollision = true;
		else
			GlobalData.changed_ObstacleTimeToCollision = false;
	}//end method
	
	
	//***************************************************
	public  void detectChange_DriverDisturbance(){
		GlobalData.new_DriverDisturbance = getData.getDriverDisturbance(getNSet);
		if (GlobalData.new_DriverDisturbance != GlobalData.old_DriverDisturbance)
			GlobalData.changed_DriverDisturbance = true;
		else
			GlobalData.changed_DriverDisturbance = false;
	}//end method
	
	
	//***************************************************************
	public  void detectChange_IntersectionDirectionFromOktal(){
		GlobalData.new_IntersectionDirectionFromOktal = getData.getIntersectionDirectionFromOktal(getNSet);
		if (GlobalData.new_IntersectionDirectionFromOktal != GlobalData.old_IntersectionDirectionFromOktal)
			GlobalData.changed_IntersectionDirectionFromOktal = true;
		else
			GlobalData.changed_IntersectionDirectionFromOktal = false;
	}//end method
	
	
	//**************************************
	public void detectDataChange() {
		detectChange_VehicleSpeed();
		detectChange_SpeedLimit();
		detectChange_IntersectionDistance();
		detectChange_IntersectionSignalDistance();
		detectChange_IntersectionType();
		detectChange_IntersectionDirectionFromOktal();
		detectChange_DirectionSignal();
		detectChange_FogVisibilityDistance();
		detectChange_ObstacleType();
		detectChange_ObstacleSpeed();
		detectChange_ObstacleDistance();
		detectChange_ObstacleTimeToCollision();
		detectChange_DriverDisturbance();
	}//end method
	
	
	
}//end class
