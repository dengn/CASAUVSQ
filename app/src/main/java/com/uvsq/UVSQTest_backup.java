package com.uvsq;

import android.content.Context;
import android.os.Bundle;

import com.uvsq.CASA.ContextFusion;
import com.uvsq.CASA.ContextVehicule;
import com.uvsq.CASA.Fission;
import com.uvsq.CASA.GlobalData;
import com.uvsq.CASA.Historique;
import com.uvsq.CASA.JSONData;
import com.uvsq.connect2datanex.GetNSet;

import java.util.Date;
import java.util.HashMap;
import java.util.Random;

public class UVSQTest_backup<E> {





	//-------------------------------------------------------------------------------------------
	private static ContextFusion cf = new ContextFusion();
	private static Fission fission = new Fission();
	private static JSONData jsonData = new JSONData();
	private static Random generator;
	private static ContextVehicule contextVehicule = new ContextVehicule();
	private static long initTime;
	private static long currentTime;
	private static int currentId = 0;
	private static boolean arretStopBool = false;
	private static boolean fogFlag = false;

	static final String[] tableau1 = { "Speed", "Speed Limit" };
	static String[] valeur1 = { "0", "0" };
	static final String[] tableau2 = { "CASA.UVSQ.Message.level",
			"CASA.UVSQ.Message.levelForce", "CASA.UVSQ.Message.category",
			"CASA.UVSQ.Message.value", "CASA.UVSQ.Message.question",
			"CASA.UVSQ.DrivingScore", "CASA.UVSQ.Image",
			"CASA.UVSQ.ActiveLampsRearFog", "CASA.UVSQ.ActiveLampsRearFog",
			"CASA.UVSQ.ActiveAirConditioning", "CASA.UVSQ.ActiveAirRecycling",
			"CASA.UVSQ.EngineStatus" };
	static String[] valeur2 = { "", "", "", "", "", "", "", "", "", "", "", "" };
	//--------------------------------------------------------------------------------------------

	private Object e;

	private boolean stopFlag = false;
	private Context context;

	/**
	 * Constructor with IRemoteInterface object
	 * 
	 * @param e
	 */
	public UVSQTest_backup(Object e) {
		this.e = e;
	}


	public void setStopIt(boolean stopFlag){
		this.stopFlag = stopFlag;
	}

	public void setContext(Context context){
		this.context = context;
	}



	private String getSignals(GetNSet<E> getNSet, String signal){
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put(signal, "");
		Bundle bundle = getNSet.get(hashMap);
		String value1 = bundle.getString(signal);
		return value1;
	}

	private void setSignals(GetNSet<E> getNSet, HashMap<String, String> hashMap){
		getNSet.set(hashMap);
	}



	// **********************************
	private void initialState(GetNSet<E> getNSet) {
		// **********************************
		for (int i = 0; i < tableau2.length; i++)
			valeur2[i] = "";
		valeur2[5] = Integer.toString(0);
		valeur2[11] = "OFF";
		GlobalData.drivingScore = 0;
		GlobalData.presentDirection = 0;
		GlobalData.previousDirection = 0;
		try {
			// the initial state of the vehicle, machine is OFF
			// ------------------------------------------------
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet,hashMap);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch

		try {// get message
			String data = "CASA.UVSQ.ActiveLampsLowBeamOn";
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put(data, "0");
			setSignals(getNSet, hashMap);
		}
		catch (Exception e) {}

	}// end method


	// ********************************************
	private void getMessageSentToServer(GetNSet<E> getNSet) {
		// ********************************************
		String data1 = "";
		String value1 = "";
		try {// get message
			data1 = tableau2[3];
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put(data1, "");

//			Bundle bundle = getNSet.get(hashMap);
//			value1 = bundle.getString(data1);

			value1 = getSignals(getNSet, data1);
			//value1 = get(getNSet, data1);
			//value1 = getNSet.get(hashMap);
		} catch (Exception e) {
		}
		System.out.println("Message & Image sent for this situation");
		System.out.println("Recovered: " + data1 + " = " + value1);
		try {// get score
			data1 = tableau2[5];
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put(data1, "");

//			Bundle bundle = getNSet.get(hashMap);
//			value1 = bundle.getString(data1);
			value1 = getSignals(getNSet, data1);
			//value1 = get(getNSet, data1);
			//value1 = getNSet.get(hashMap);
		} catch (Exception e) {
		}
		System.out.println("Recovered: " + data1 + " = " + value1);
		try {// get image
			data1 = tableau2[6];
			HashMap<String, String> hashMap = new HashMap<>();
			hashMap.put(data1, "");

//			Bundle bundle = getNSet.get(hashMap);
//			value1 = bundle.getString(data1);
			value1 = getSignals(getNSet, data1);
			//value1 = getNSet.get(hashMap);
		} catch (Exception e) {
		}
		System.out.println("Recovered: " + data1 + " = " + value1);
		System.out.println();
	}// end method



	// **************************************
	private String getEngineStatus(GetNSet<E> getNSet) {
		// **************************************
		String status = "";

		// System.out.println("Testing - Obtaining engine status");
		String signal1 = "CASA.Nexyad.EngineStatus";
		// String signal1 = "CASA.Oktal.EngineStatus";
		try {
			// take note of start time
			// long start_time = System.nanoTime();

//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			Bundle bundle = getNSet.get(hashMap);
//			status = bundle.getString(signal1);

			status = getSignals(getNSet, signal1);
			//status = get(getNSet, signal1);


			GlobalData.engineStatus = status.equalsIgnoreCase("undefined") ? "ON"
					: status;

			// take note of end time
			// long end_time = System.nanoTime();
			// double difference = (end_time - start_time)/1e6;
			// display elapsed time to do specific method
			// System.out.println("Execution time of getEngineStatus: " +
			// difference + " ms");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Engine status: " + GlobalData.engineStatus);
		return GlobalData.engineStatus;
	}// end method
	// ***************************************

	// ***************************************
	private boolean engineStatusIsOn(GetNSet<E> getNSet) {
		// ***************************************
		boolean engineOn = false;
		String status = getEngineStatus(getNSet);
		if (status.equalsIgnoreCase("ON"))
			engineOn = true;
		else
			engineOn = false;
		return engineOn;
	}
	// ***************************************


	// *************************************
	private void debutSimulation(GetNSet<E> getNSet) {
		// *************************************
		for (int i = 0; i < tableau2.length; i++)
			valeur2[i] = "";
		valeur2[5] = Integer.toString(0);
		valeur2[11] = "ON";
		GlobalData.drivingScore = 0;
		try {
			// in the very first instance that vehicle engine is ON
			// -----------------------------------------------------
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);

			setSignals(getNSet,hashMap);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
	}// end method
	// *************************************


	// *****************************************
	private void detectOverspeeding(GetNSet<E> getNSet) {
		// *****************************************
		int deduction = 0;
		double speed = 0.0;
		double speedLimit = 0.0;
		boolean messageTheSame = true;

		// get speed and speed limit
		speed = getVehicleSpeed(getNSet);
		speedLimit = getSpeedLimit(getNSet);

		// process speed and speed limit
		ContextVehicule.setVitesse(speed);
		ContextVehicule.setLimitationVitesse(speedLimit);
		ContextVehicule.detectDangerDeLaVitesse();

		GlobalData.presentSpeedLimit = ContextVehicule.getLimitationVitesse();
		// if ((ContextVehicule.vitesseAdaptative) && (speedLimit > 0.0) &&
		// (GlobalData.presentSpeedLimit != GlobalData.previousSpeedLimit)){
		if ((ContextVehicule.vitesseAdaptative) && (speedLimit > 0.0)
				&& currentId == 3) {
			// ----------------------------------Adaptative---------------
			GlobalData.presentDrivingLevel = "Adaptative";
			GlobalData.overSpeeding = false;

			for (int i = 0; i < tableau2.length; i++)
				valeur2[i] = "";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			currentId = 0;
		}
		// else if ((ContextVehicule.vitesseExcessive) && (speedLimit > 0.0) &&
		// (GlobalData.presentSpeedLimit != GlobalData.previousSpeedLimit)){
		else if ((ContextVehicule.vitesseExcessive) && (speedLimit > 0.0)) {
			currentId = 3;
			// ---------------------------------Excessive-----------------------
			GlobalData.presentDrivingLevel = "Excessive";
			valeur2[0] = "Alert";

			// calculate level force (from 6 to 8)
			// ------------------------------------
			double temp = (speedLimit * 0.20) / 3;
			if ((speed > speedLimit) && (speed <= temp + speedLimit))
				valeur2[1] = "6";
			else if ((speed > temp + speedLimit)
					&& (speed <= 2 * temp + speedLimit))
				valeur2[1] = "7";
			else
				valeur2[1] = "8";
			valeur2[2] = "Behavior";
			valeur2[3] = "Slow down. Speed limit is "
					+ ((int) ContextVehicule.getLimitationVitesse())
					+ " km per hour.";
			valeur2[4] = "False";

			// compute deduction
			// -----------------------------------------------------
			if ((speed > speedLimit) && (speed <= 1.10 * speedLimit))
				deduction = 1;
			else if ((speed > 1.10 * speedLimit)
					&& (speed <= 1.20 * speedLimit))
				deduction = 2;
			GlobalData.drivingScore -= deduction;

			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[6] = "Behavior";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			GlobalData.overSpeeding = true;
			Historique.nombre_infractionSurvitesse++;
		} else if (ContextVehicule.vitesseDangereuse) {
			currentId = 3;
			// if ((ContextVehicule.vitesseDangereuse) && (speedLimit > 0.0) &&
			// (GlobalData.presentSpeedLimit != GlobalData.previousSpeedLimit)){
			if ((ContextVehicule.vitesseDangereuse) && (speedLimit > 0.0)) {
				// -------------------------------Dangeureuse-----------------------
				GlobalData.presentDrivingLevel = "Dangereuse";
				valeur2[0] = "Alert";
				// calculate level force (from 9 to 10)
				// --------------------------------------------------------------
				if ((speed > speedLimit * 1.20) && (speed <= speedLimit * 1.30))
					valeur2[1] = "9";
				else
					valeur2[1] = "10";
				valeur2[2] = "Behavior";
				valeur2[3] = "Slow down. Speed limit is "
						+ ((int) ContextVehicule.getLimitationVitesse())
						+ " km per hour";
				valeur2[4] = "False";

				// infraction
				// ---------------------------------------------------
				deduction = 3;
				GlobalData.drivingScore -= deduction;
				valeur2[5] = Integer.toString(GlobalData.drivingScore);
				valeur2[6] = "Behavior";
				for (int i = 7; i < 11; i++)
					valeur2[i] = "FALSE";
				valeur2[11] = "ON";
				GlobalData.overSpeeding = true;
				Historique.nombre_infractionSurvitesse++;
			}
		}// end else

		// ************************compare present vs previous
		// message************************************
		GlobalData.presentMessageOverspeeding = valeur2[3];

		// if
		// (((GlobalData.presentMessageOverspeeding.compareTo(GlobalData.previousMessageOverspeeding))
		// != 0) && (GlobalData.presentSpeedLimit !=
		// GlobalData.previousSpeedLimit)){
		if (((GlobalData.presentMessageOverspeeding
				.compareTo(GlobalData.previousMessageOverspeeding)) != 0)) {
			messageTheSame = false;
			try {
				// messages are different, send output to the CEA server
				// -----------------------------------------------------

				HashMap<String, String> hashMap = new HashMap<>();
				for (int i = 0; i < tableau2.length; i++)
					hashMap.put(tableau2[i], valeur2[i]);

				setSignals(getNSet, hashMap);
				GlobalData.previousMessageOverspeeding = valeur2[3];
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}// end catch
		}// end if
		else {
			// same message as before, do not send message. invalidate
			// deductions
			// ------------------------------------------------------------------
			GlobalData.drivingScore += deduction;
		}
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}
		// update previous data after all have been taken into account
		GlobalData.previousSpeedLimit = GlobalData.presentSpeedLimit;
		GlobalData.previousSpeed = GlobalData.presentSpeed;
		GlobalData.previousDrivingLevel = GlobalData.presentDrivingLevel;
		GlobalData.presentDrivingLevel = "";
		// GlobalData.previousMessageOverspeeding =
		// GlobalData.presentMessageOverspeeding;
		// GlobalData.presentMessageOverspeeding = "";
	}// end method
	// *****************************************


	// ***************************************
	private double getVehicleSpeed(GetNSet<E> getNSet) {
		// ***************************************
		double speed = 0.0;
		try {
			String signal1 = "CASA.Nexyad.VehicleSpeed";
			// String signal1 = "CASA.Oktal.VehicleSpeed";

//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//
//
//			Bundle bundle = getNSet.get(hashMap);
//			String value1 = bundle.getString(signal1);
			String value1 = getSignals(getNSet, signal1);

			speed = Double.parseDouble(value1);
			//speed = Double.parseDouble(getNSet.get(hashMap));


			speed = (speed < 0.0) ? GlobalData.previousSpeed : speed;
			GlobalData.presentSpeed = speed;
			ContextVehicule.setVitesse(speed);
			// System.out.println("Vehicle speed: " + speed);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return speed;
	}// end method

	// *************************************
	private double getSpeedLimit(GetNSet<E> getNSet) {
		// *************************************
		double speedLimit = 0.0;
		try {
			String signal1 = "CASA.Nexyad.SpeedLimit";
			// String signal1 = "CASA.Oktal.SpeedLimit";

//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			Bundle bundle = getNSet.get(hashMap);
//			bundle.getString(signal1)
			String value1 = getSignals(getNSet, signal1);

			speedLimit = Double.parseDouble(value1);
			speedLimit = (speedLimit < 0) ? GlobalData.previousSpeedLimit
					: speedLimit;
			GlobalData.presentSpeedLimit = speedLimit;
			// System.out.println("Speed limit = " + speedLimit);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return speedLimit;
	}// end method


	// *********************************
	private void detectStop(GetNSet<E> getNSet) {
		// *********************************
		// Detection panneau de stop
		int intersectionType = -1;
		double intersectionDistance = -1.0;
		boolean messageTheSame = true;

		intersectionDistance = getIntersectionDistance(getNSet);
		// double intersectionSignalDistance = getIntersectionSignalDistance();
		intersectionType = getIntersectionType(getNSet);

		// if (intersectionType == 0)
		// System.out.println("Next intersection is Stop");
		// System.out.println("Next intersection distance is " +
		// intersectionDistance);

		// if ((((intersectionDistance >= 0.0) && (intersectionDistance <=
		// 200.0)) || ((intersectionSignalDistance >= 0) &&
		// (intersectionSignalDistance <= 200)))

		System.out.println("Intersection distance = " + intersectionDistance);

		if (((intersectionDistance >= 40.0) && (intersectionDistance <= 80.0))
				&& (intersectionType == 0)) {
			// next intersection is a Stop and its distance from car is 200
			// meters or less
			// ---------------------------------------------------------------------------
			valeur2[0] = "Notification";
			valeur2[1] = "5";
			valeur2[2] = "Behavior";
			valeur2[3] = "Get ready to stop.";
			valeur2[4] = "FALSE";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[6] = "Behavior";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			currentId = 4;
		} else if (currentId == 4) {
			currentId = 0;
			for (int i = 0; i < 12; i++)
				valeur2[i] = "";
		}

		for (int i = 7; i < 11; i++)
			valeur2[i] = "FALSE";
		valeur2[11] = "ON";
		valeur2[5] = Integer.toString(GlobalData.drivingScore);

		GlobalData.presentMessageDetectStop = valeur2[3];

		// *********************compare previous vs. present message
		// **************************************
		// if (((GlobalData.presentMessageDetectStop
		// .compareTo(GlobalData.previousMessageDetectStop)) != 0)
		// && (!GlobalData.stopMessageAlreadySent)) {
		// this has to happen only once in a Stop intersection
		// test if previous and present messages are not the same and
		// message
		// has not yet been sent
		messageTheSame = false;
		try {
			// messages are different, send output to the CEA server
			// -----------------------------------------------------
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);

			setSignals(getNSet, hashMap);
			GlobalData.stopMessageAlreadySent = true;
			GlobalData.previousMessageDetectStop = valeur2[3];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
		// }// end if

		// Test reading message that was sent to the server
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}
		GlobalData.presentMessageDetectStop = "";

		if (intersectionDistance == 99999) {
			GlobalData.presentMessageDetectStop = "";
			GlobalData.previousMessageDetectStop = "";
			GlobalData.stopMessageAlreadySent = false;
		}
	}// end method()

	// ****************************************
	private int getIntersectionType(GetNSet<E> getNSet) {
		// ****************************************
		int intersectionType = -1;

		String signal1 = "CASA.Nexyad.NextIntersectionType";
		try {

//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			//intersectionType = Integer.valueOf(getNSet.get(hashMap));
			intersectionType = Integer.valueOf(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return intersectionType;
	}// end method


	// ***********************************************
	private double getIntersectionDistance(GetNSet<E> getNSet) {
		// ***********************************************
		double distance = 0.0;
		String signal1 = "CASA.Nexyad.NextIntersectionDistance";
		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//distance = Double.parseDouble(getNSet.get(hashMap));
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			distance = Double.parseDouble(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return distance;
	}// end method


	// ***************************************
	private void detectArretAuStop(GetNSet<E> getNSet) {
		// ***************************************
		// Detection panneau de stop
		int intersectionType = -1;
		double intersectionDistance = -1;
		// double intersectionSignalDistance = -1;
		double speed = -1;
		boolean messageTheSame = true;

		// System.out.println("Simulation: Detection if driver stops on a Stop signal");
		// System.out.println("------------------------------------------------------");

		intersectionType = getIntersectionType(getNSet);
		intersectionDistance = getIntersectionDistance(getNSet);
		// intersectionSignalDistance = getIntersectionSignalDistance();
		speed = getVehicleSpeed(getNSet);

		// if ((intersectionType == 0) && (intersectionDistance <= 10) && (speed
		// != 0)){
		// if ((((intersectionDistance >= -1) && (intersectionDistance <= 0)) ||
		// ((intersectionSignalDistance >= -1) && (intersectionSignalDistance <=
		// 0)))

		arretStopBool |= ((intersectionDistance >= 2.0)
				&& (intersectionDistance <= 20.0) && (intersectionType == 0) && (speed < 8.0));

		//System.out.println("ArretStopBool = " + arretStopBool);
		//System.out.println("Intersection distance = " + intersectionDistance);
		//System.out.println("Speed = " + speed);

		GlobalData.presentDirection = getIntersectionDirectionFromOktal(getNSet);

		if ((GlobalData.presentDirection != GlobalData.previousDirection)) {
			if (!arretStopBool) {
				// next intersection is a Stop and its distance from car is 10
				// meters or less and the car did not stop
				valeur2[0] = "Alert";
				valeur2[1] = "8";
				valeur2[2] = "Behavior";
				valeur2[3] = "You just run a stop";
				valeur2[4] = "FALSE";
				valeur2[5] = Integer.toString(--GlobalData.drivingScore);
				valeur2[6] = "Behavior";
				for (int i = 7; i < 11; i++)
					valeur2[i] = "FALSE";
				valeur2[11] = "ON";
				Historique.nombre_Oubli_arreter_au_Stop++;

				// GlobalData.previousDirection = GlobalData.presentDirection;
			}// end if
			arretStopBool = false;
		}// end if

		// send output to CEA server
		// --------------------------
		GlobalData.presentMessageArretAuStop = valeur2[3];
		// *********************compare previous vs. present message
		// **************************************
		// if
		// (((GlobalData.presentMessageArretAuStop.compareTo(GlobalData.previousMessageArretAuStop))
		// != 0)
		// && (!GlobalData.arretAuStopMessageAlreadySent)) {
		messageTheSame = false;
		try {
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet, hashMap);
			GlobalData.arretAuStopMessageAlreadySent = true;
			GlobalData.previousMessageArretAuStop = valeur2[3];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		// }
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}
		// GlobalData.presentMessageArretAuStop = "";
	}// end method()

	// ******************************************************
	private int getIntersectionDirectionFromOktal(GetNSet<E> getNSet) {
		// ******************************************************
		int direction = -1;

		try {
			String signal1 = "CASA.Oktal.IntersectionDirection";
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//direction = Integer.valueOf(getNSet.get(hashMap));
//
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			direction = Integer.valueOf(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
		return direction;
	}// end method


	// ************************************************
	private void detectIntersectionDirection(GetNSet<E> getNSet) {
		// ************************************************
		// boolean directionAhead = false;
		// boolean directionLeft = false;
		// boolean directionRight = false;
		int directionClignotant = -10;
		boolean messageTheSame = true;

		// double intersectionDistance = 100.0;
		// double intersectionSignalDistance = 100;
		int intersectionType = getIntersectionType(getNSet);
		// double speed = getVehicleSpeed();

		int direction = getIntersectionDirectionFromOktal(getNSet);
		GlobalData.presentDirection = direction;

		// intersectionDistance = getIntersectionDistance();
		// intersectionSignalDistance = getIntersectionSignalDistance();

		// System.out.println("Simulation: Detection of direction in the intersection");
		// System.out.println("------------------------------------------------------");
		// directionLeft = getDirectionLeft();
		// directionRight = getDirectionRight();
		// directionAhead = getDirectionAhead();
		directionClignotant = getDirectionSignal(getNSet);

		// if ((((intersectionDistance >= -1) && (intersectionDistance <= -5))
		// || ((intersectionSignalDistance >= -1) && (intersectionSignalDistance
		// <= -5)))
		// if (((intersectionDistance >= -1.0) && (intersectionDistance <=
		// -5.0))
		// && ((intersectionType >= 0) && (intersectionType <= 4))){
		/*
		 * System.out.println("Intersection distance: " + intersectionDistance);
		 * System.out.println("Intersection type: " + intersectionType);
		 * System.out.println("Intersection speed: " + speed);
		 * System.out.println("Direction Left: " + directionLeft);
		 * System.out.println("Direction right: " + directionRight);
		 * System.out.println("Direction clignotant: " + directionClignotant);
		 */

		//System.out.println("Present Direction: " + GlobalData.presentDirection);
		//System.out.println("Previous Direction: "
		//		+ GlobalData.previousDirection);
		//System.out.println("Intersection Type: " + intersectionType);

		if ((GlobalData.presentDirection != GlobalData.previousDirection)
				|| (currentTime - initTime < 2000)) {
			currentTime = new Date().getTime();
			//System.out
			//		.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>"
			//				+ (currentTime - initTime));
			// if ((intersectionType == 99999) && ((intersectionDistance >= 0)
			// && (intersectionDistance <= 16)) && (speed > 2)){
			// this covers all types of intersections (0 to 4)
			// vehicle going left
			// -------------------
			if ((direction == 1) && (directionClignotant != 1)) {
				// System.out.println("Direction left but left signal is not activated");
				valeur2[0] = "Notification";
				valeur2[1] = "7";
				valeur2[2] = "Behavior";
				valeur2[3] = "You forgot to turn your signal on.";
				valeur2[4] = "FALSE";
				valeur2[5] = Integer.toString(--GlobalData.drivingScore);
				valeur2[6] = "Behavior";
				for (int i = 7; i < 11; i++)
					valeur2[i] = "FALSE";
				valeur2[11] = "ON";
				Historique.nombre_Oubli_clignoter_gauche++;
			} else
				// vehicle going right
				// --------------------
				if ((direction == 2) && (directionClignotant != -1)) {
					valeur2[0] = "Notification";
					valeur2[1] = "7";
					valeur2[2] = "Behavior";
					valeur2[3] = "You forgot to turn your signal on.";
					valeur2[4] = "FALSE";
					valeur2[5] = Integer.toString(--GlobalData.drivingScore);
					valeur2[6] = "Behavior";
					for (int i = 7; i < 11; i++)
						valeur2[i] = "FALSE";
					valeur2[11] = "ON";
					Historique.nombre_Oubli_clignoter_droite++;
				} // TODO else {
			// if (((direction == 1) && (directionClignotant == 1))
			// || ((direction == 2) && (directionClignotant == -1))) {
			// tout est bien; no problem in direction signal
			// ----------------------------------------------
			// }
			// }

			currentId = 2;
		} else if (currentId == 2) {
			for (int i = 0; i < tableau2.length; i++)
				valeur2[i] = "";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			initTime = new Date().getTime();
			currentTime = initTime + 10000;// end if
			currentId = 0;
		}
		// send output to CEA server
		// --------------------------
		GlobalData.presentMessageDetectIntersectionDirection = valeur2[3];

		// *********************************compare previous vs. present message
		// **********************************************************
		// if
		// (((GlobalData.presentMessageDetectIntersectionDirection.compareTo(GlobalData.previousMessageDetectIntersectionDirection))
		// != 0)
		// && (!GlobalData.intersectionDirectionMessageAlreadySent)){
		messageTheSame = false;
		try {

			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet, hashMap);
			GlobalData.intersectionDirectionMessageAlreadySent = true;
			// GlobalData.previousMessageDetectIntersectionDirection =
			// valeur2[3];

		}// end try
		catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
		// }
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}

		// directionClignotant = -2;

		if (intersectionIsPassed(getNSet)) {
			// make way for a new intersection
			// -------------------------------
			GlobalData.previousMessageDetectIntersectionDirection = "";
			GlobalData.presentMessageDetectIntersectionDirection = "";
			GlobalData.intersectionDirectionMessageAlreadySent = false;
			// directionLeft = false;
			// directionRight = false;
			// directionAhead = false;
			// directionClignotant = -2;
		}// end if
		GlobalData.previousDirection = direction;
	}// end method

	// ***************************************
	private int getDirectionSignal(GetNSet<E> getNSet) {
		// ***************************************
		int directionClignotant = -2;
		// returns -1: right / 0: none / 1: left

		String signal1 = "CASA.Nexyad.Indicators";


		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//directionClignotant = Integer.valueOf(getNSet.get(hashMap));
//
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			directionClignotant = Integer.valueOf(value1);
			// System.out.println("Direction signal: " + directionClignotant);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return directionClignotant;
	}// end method


	// *********************************************
	private boolean intersectionIsPassed(GetNSet<E> getNSet) {
		// *********************************************
		boolean intersectionPassed = false;
		double intersectionDistance = getIntersectionDistance(getNSet);
		// double intersectionSignalDistance = getIntersectionSignalDistance();
		int intersectionType = getIntersectionType(getNSet);
		// if ((intersectionDistance <= -1) && (intersectionSignalDistance <=
		// -1) && ((intersectionType >= 0) && (intersectionType <= 4)))
		// if ((intersectionDistance <= -1.0) && ((intersectionType >= 0) &&
		// (intersectionType <= 4)))
		if ((intersectionDistance == 99999)
				&& ((intersectionType >= 0) && (intersectionType <= 4)))
			intersectionPassed = true;
		return intersectionPassed;
	}// end method



	// ****************************************************
	private void implementFoggyAreaTrafficRules(GetNSet<E> getNSet) {
		// ****************************************************
		// detect foggy zone
		detectFog(getNSet);
		// detect fog lights
		// detectFogLights();
		// detect speed in a foggy zone
		detectVehicleSpeedFoggyZone(getNSet);
		// exit foggy zone
		exitFoggyZone(getNSet);

		GlobalData.previousFogVisibilityDistance = GlobalData.presentFogVisibilityDistance;

		if (foggyAreaIsOver(getNSet)) {
			// re-initialization for fog detection
			GlobalData.previousMessageDetectFog = "";
			GlobalData.presentMessageDetectFog = "";
			GlobalData.foggyAreaNotificationMessageAlreadySent = false;

			// re-initialization for fog lights detection
			GlobalData.previousMessageDetectFogLights = "";
			GlobalData.presentMessageDetectFogLights = "";
			GlobalData.fogLightsMessageAlreadySent = false;

			// re-initialization for speed notification on a foggy area
			GlobalData.previousMessageDetectVehicleSpeedFoggyZone = "";
			GlobalData.presentMessageDetectVehicleSpeedFoggyZone = "";
			GlobalData.foggyAreaVehicularSpeedNotificationMessageAlreadySent = false;

			// re-initialization, exit from foggy area
			GlobalData.previousMessageExitFoggyZone = "";
			GlobalData.presentMessageExitFoggyZone = "";
			GlobalData.exitFoggyAreaAlreadySent = false;

			// change speed limit back to normal
			GlobalData.presentSpeedLimit = getSpeedLimit(getNSet);
		}// end if
	}// end method


	// ********************************
	private void detectFog(GetNSet<E> getNSet) {
		// ********************************
		// Detection panneau de stop
		double fogDistance = -1;
		boolean messageTheSame = true;

		// System.out.println("Simulation: Detection of fog");
		// System.out.println("----------------------------");

		fogDistance = getFogVisibilityDistance(getNSet);
		GlobalData.presentFogVisibilityDistance = fogDistance;

		System.out.println("Fog visibility distance: " + fogDistance);

		// System.out.println("Fog detected in " + fogDistance + " meters.");
		// if (((fogDistance >= 0.0) && (fogDistance <= 300.0)) &&
		// (GlobalData.previousFogVisibilityDistance >= 3000.0)){
		if ((fogDistance >= 0.0) && (fogDistance <= 300.0) && (!fogFlag))
		{
			// approaching foggy area
			System.out.println("Fog flag: " + fogFlag);
			valeur2[0] = "Alert";
			valeur2[1] = "8";
			valeur2[2] = "Danger";
			valeur2[3] = "Low visibility. Do you want to activate fog lights ?";
			valeur2[4] = "TRUE";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[6] = "Behavior";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			//valeur2[7] = "2";
			try {// get message
				String data = "CASA.UVSQ.ActiveLampsLowBeamOn";
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put(data, "2");
				setSignals(getNSet, hashMap);
			}
			catch (Exception e) {}
			currentId = 10;
			fogFlag = true;
		}//end if
		else if (currentId == 10)
		{
			for (int i = 0; i < 12; i++)
				valeur2[i] = "";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[11] = "ON";
			currentId = 0;
		}

		// send output to CEA server
		// --------------------------
		//GlobalData.presentMessageDetectFog = valeur2[3];
		// *********************compare previous vs. present message
		// **************************************
		//if ((GlobalData.presentMessageDetectFog
		//		.compareTo(GlobalData.previousMessageDetectFog)) != 0) {
		// && (!GlobalData.foggyAreaNotificationMessageAlreadySent)){
		messageTheSame = false;
		try {
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet, hashMap);
			//GlobalData.foggyAreaNotificationMessageAlreadySent = true;
			//GlobalData.previousMessageDetectFog = valeur2[3];
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		//}// end if
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}



	}// end method()


	// *************************************************
	private void detectVehicleSpeedFoggyZone(GetNSet<E> getNSet) {
		// *************************************************
		// Detection de vitesse dans une zone de brouillard
		double fogDistance = -1;
		int deduction = 0;
		double speed = 0;
		double speedLimit = 0;
		boolean messageTheSame = true;

		fogDistance = getFogVisibilityDistance(getNSet);
		GlobalData.presentFogVisibilityDistance = fogDistance;
		speed = getVehicleSpeed(getNSet);
		// speedLimit = getSpeedLimit();
		speedLimit = 50;

		// check vehicle speed in a foggy zone
		if (((fogDistance >= 0) && (fogDistance <= 300))
				&& (GlobalData.previousFogVisibilityDistance >= 3000)) {
			// adjusting speed to suite foggy zone
			// speedLimit = 0.80 * speedLimit;
			// speedLimit = 50.0;
			// process speed and speed limit
			ContextVehicule.setVitesse(speed);
			ContextVehicule.setLimitationVitesse(speedLimit);
			ContextVehicule.detectDangerDeLaVitesse();

			GlobalData.presentSpeedLimit = ContextVehicule
					.getLimitationVitesse();

			if (ContextVehicule.vitesseAdaptative) {
				// adaptative
				// --------------------------------------
				GlobalData.presentDrivingLevel = "Adaptative";
				GlobalData.overSpeeding = false;
			} else if (ContextVehicule.vitesseExcessive) {
				// excessive
				// -------------------------------------------
				GlobalData.presentDrivingLevel = "Excessive";
				valeur2[0] = "Alert";

				// calculate level force (from 6 to 8)
				double temp = (speedLimit * 0.20) / 3;
				if ((speed > speedLimit) && (speed <= temp + speedLimit))
					valeur2[1] = "6";
				else if ((speed > temp + speedLimit)
						&& (speed <= 2 * temp + speedLimit))
					valeur2[1] = "7";
				else
					valeur2[1] = "8";
				valeur2[2] = "Behavior";
				valeur2[3] = "Slow down. Speed limit is "
						+ ((int) ContextVehicule.getLimitationVitesse())
						+ " km per hour";
				valeur2[4] = "False";

				// compute deduction
				if ((speed > speedLimit) && (speed <= 1.10 * speedLimit))
					deduction = 1;
				else if ((speed > 1.10 * speedLimit)
						&& (speed <= 1.20 * speedLimit))
					deduction = 2;
				GlobalData.drivingScore -= deduction;
				valeur2[5] = Integer.toString(GlobalData.drivingScore);
				valeur2[6] = "Behavior";
				for (int i = 7; i < 11; i++)
					valeur2[i] = "FALSE";
				valeur2[11] = "ON";
				GlobalData.overSpeeding = true;
				Historique.nombre_infractionSurvitesse++;
			} else {
				// dangereuse
				// --------------------------------------------------
				GlobalData.presentDrivingLevel = "Dangereuse";
				valeur2[0] = "Alert";

				// calculate level force (from 9 to 10)
				if ((speed > speedLimit * 1.20) && (speed <= speedLimit * 1.30))
					valeur2[1] = "9";
				else
					valeur2[1] = "10";
				valeur2[2] = "Behavior";
				valeur2[3] = "Slow down. Speed limit is "
						+ ((int) ContextVehicule.getLimitationVitesse())
						+ " km per hour";
				valeur2[4] = "False";
				deduction = 3;
				GlobalData.drivingScore -= deduction;
				valeur2[5] = Integer.toString(GlobalData.drivingScore);
				valeur2[6] = "Behavior";
				for (int i = 7; i < 11; i++)
					valeur2[i] = "FALSE";
				valeur2[11] = "ON";
				GlobalData.overSpeeding = true;
				Historique.nombre_infractionSurvitesse++;
			}// end if
		}// end if
		valeur2[9] = "FALSE"; // air conditioning is off
		valeur2[10] = "FALSE"; // air recycling is off

		// send output to CEA server
		// --------------------------
		GlobalData.presentMessageDetectVehicleSpeedFoggyZone = valeur2[3];

		// *********************compare previous vs. present message
		// **************************************
		if (((GlobalData.presentMessageDetectVehicleSpeedFoggyZone
				.compareTo(GlobalData.previousMessageDetectVehicleSpeedFoggyZone)) != 0)
				&& (!GlobalData.foggyAreaVehicularSpeedNotificationMessageAlreadySent)) {
			messageTheSame = false;
			try {
				HashMap<String, String> hashMap = new HashMap<>();
				for (int i = 0; i < tableau2.length; i++)
					hashMap.put(tableau2[i], valeur2[i]);
				setSignals(getNSet, hashMap);

				GlobalData.previousMessageDetectVehicleSpeedFoggyZone = valeur2[3];
				GlobalData.foggyAreaVehicularSpeedNotificationMessageAlreadySent = true;
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}// end if

		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}// end if
		// update previous data after all have been taken into account
		GlobalData.previousSpeedLimit = GlobalData.presentSpeedLimit;
		GlobalData.previousDrivingLevel = GlobalData.presentDrivingLevel;
		GlobalData.presentDrivingLevel = "";
	}// end method()


	// ***********************************
	private void exitFoggyZone(GetNSet<E> getNSet) {
		// ***********************************
		// Detection panneau de stop
		double fogDistance = -1;
		boolean messageTheSame = true;

		// System.out.println("Simulation: Exit from foggy area");
		fogDistance = getFogVisibilityDistance(getNSet);
		// if ((!areaIsFoggy()) && ((GlobalData.previousFogVisibilityDistance >=
		// 0) && (GlobalData.previousFogVisibilityDistance <= 300))){
		// if ((!areaIsFoggy()) && ((GlobalData.previousFogVisibilityDistance >=
		// 0) && (GlobalData.previousFogVisibilityDistance <= 300))){
		if ((fogDistance >= 310)
				&& (GlobalData.previousFogVisibilityDistance >= 0)
				&& (GlobalData.previousFogVisibilityDistance <= 300)) {
			// area is not a foggy zone anymore
			GlobalData.automaticFogLights = false;
			valeur2[0] = "Notification";
			valeur2[1] = "5";
			valeur2[2] = "Behavior";
			valeur2[3] = "You're leaving the foggy zone"; // exiting foggy area
			valeur2[4] = "False";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[6] = "Behavior";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			currentId = 5;
			try {// get message
				String data = "CASA.UVSQ.ActiveLampsLowBeamOn";
				HashMap<String, String> hashMap = new HashMap<>();
				hashMap.put(data, "0");
				setSignals(getNSet, hashMap);
			}
			catch (Exception e) {}
			fogFlag = false;
		}// end if
		else if (currentId == 5) {
			currentId = 0;
			for (int i = 0; i < 12; i++)
				valeur2[i] = "";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
		}

		GlobalData.presentFogVisibilityDistance = fogDistance;
		// send output to CEA server
		// --------------------------
		GlobalData.presentMessageExitFoggyZone = valeur2[3];
		// *********************compare previous vs. present message
		// **************************************
		// if
		// (((GlobalData.presentMessageExitFoggyZone.compareTo(GlobalData.previousMessageExitFoggyZone))
		// != 0) &&
		// ((GlobalData.presentFogVisibilityDistance >= 316) &&
		// ((GlobalData.previousFogVisibilityDistance >= 0) &&
		// (GlobalData.previousFogVisibilityDistance < 310)))
		// && (!GlobalData.exitFoggyAreaAlreadySent)) {
		if (((GlobalData.presentMessageExitFoggyZone
				.compareTo(GlobalData.previousMessageExitFoggyZone)) != 0)
				&& ((GlobalData.presentFogVisibilityDistance >= 310) && ((GlobalData.previousFogVisibilityDistance >= 0) && (GlobalData.previousFogVisibilityDistance <= 300)))
				&& (!GlobalData.exitFoggyAreaAlreadySent)) {
			messageTheSame = false;
			try {
				HashMap<String, String> hashMap = new HashMap<>();
				for (int i = 0; i < tableau2.length; i++)
					hashMap.put(tableau2[i], valeur2[i]);
				setSignals(getNSet, hashMap);
				GlobalData.exitFoggyAreaAlreadySent = true;
				GlobalData.previousMessageExitFoggyZone = valeur2[3];
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}// end if
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}// end if
		GlobalData.foggyAreaVehicularSpeedNotificationMessageAlreadySent = false;
		GlobalData.presentMessageDetectVehicleSpeedFoggyZone = "";
		GlobalData.previousMessageDetectVehicleSpeedFoggyZone = "";
	}// end method()


	// ****************************************
	private boolean foggyAreaIsOver(GetNSet<E> getNSet) {
		// ****************************************
		boolean foggyAreaOver = false;
		double fogDistance = -1;
		fogDistance = getFogVisibilityDistance(getNSet);
		GlobalData.presentFogVisibilityDistance = fogDistance;
		if ((GlobalData.presentFogVisibilityDistance >= 310.0)
				&& ((GlobalData.previousFogVisibilityDistance >= 0.0) && (GlobalData.previousFogVisibilityDistance <= 300.0)))
			foggyAreaOver = true;
		return foggyAreaOver;
	}// end method


	// ************************************************
	private double getFogVisibilityDistance(GetNSet<E> getNSet) {
		// ************************************************
		double fogDistance = -1;

		// System.out.println("Testing - Obtaining distance of fog. If distance is 5000 meters, no fog");
		String signal1 = "CASA.Nexyad.MeteoFogVisibilityDistance";
		try {

//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//fogDistance = Double.parseDouble(getNSet.get(hashMap));
//
//			Bundle bundle = getNSet.get(hashMap);
			String value1 = getSignals(getNSet, signal1);

			fogDistance = Double.parseDouble(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return fogDistance;
	}// end method


	// ***************************************************************
	private void alertSecurityDistanceWithVehicularObstacle(GetNSet<E> getNSet) {
		// ***************************************************************
		// Alerte obstacle v�hicule
		double speed = 0;
		double obstacleSpeed = 0;
		boolean messageTheSame = true;

		int obstacleType = getObstacleType(getNSet);
		speed = getVehicleSpeed(getNSet);
		obstacleSpeed = getObstacleSpeed(getNSet);
		ContextVehicule.setVitesse(speed);

		if ((obstacleType == 1) && (speed > obstacleSpeed)
				&& (obstacleIsPresent(getNSet))) {
			valeur2[0] = "Alert";
			valeur2[1] = "8";
			valeur2[2] = "Danger";
			valeur2[3] = "Slow down. Vehicle ahead.";
			// + "" Possible collision in "
			// + ((int) collisionTime) + " seconds.";
			valeur2[4] = "FALSE";
			valeur2[5] = Integer.toString(--GlobalData.drivingScore);
			valeur2[6] = "Danger";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			currentId = 6;
		}
		else if (!obstacleIsPresent(getNSet) && currentId == 6) {
			currentId = 0;
			for (int i = 0; i < 11; i++)
				valeur2[i] = "";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[11] = "ON";
		}
		// send output to CEA server
		// --------------------------

		GlobalData.presentMessageAlertDangerAvecObstacle = valeur2[3];
		// ********************************************compare previous vs.
		// present message **************************************
		if (((GlobalData.presentMessageAlertDangerAvecObstacle
				.compareTo(GlobalData.previousMessageAlertDangerAvecObstacle)) != 0))
		// && (!GlobalData.notificationPossibleCollisionAlreadySent))
		{
			messageTheSame = false;
			try {
				HashMap<String, String> hashMap = new HashMap<>();
				for (int i = 0; i < tableau2.length; i++)
					hashMap.put(tableau2[i], valeur2[i]);
				setSignals(getNSet, hashMap);
				GlobalData.previousMessageAlertDangerAvecObstacle = valeur2[3];
				// GlobalData.notificationPossibleCollisionAlreadySent = true;
			}
			catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}// end if
		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}
		GlobalData.presentMessageAlertDangerAvecObstacle = "";
	}// end method

	// *************************************************************
	private void implementVehicularObstacleTrafficRules(GetNSet<E> getNSet) {
		// *************************************************************
		// detect vehicular obstacle
		// detectIdentifyVehicularObstacle();

		alertSecurityDistanceWithVehicularObstacle(getNSet);

		GlobalData.previousVehicularObstacleDistance = GlobalData.presentVehicularObstacleDistance;

		if (vehicularObstaclePassed(getNSet)) {
			// current vehicular obstacle has been passed
			// ------------------------------------------
			GlobalData.previousMessageDetectVehicularObstacle = "";
			GlobalData.presentMessageDetectVehicularObstacle = "";
			GlobalData.vehicularObstacleAlertAlreadySent = false;
		}

	}// end method

	// ************************************************
	private boolean vehicularObstaclePassed(GetNSet<E> getNSet) {
		// ************************************************
		boolean vehicularObstacleOver = false;
		double obstacleDistance = getObstacleDistance(getNSet);
		GlobalData.presentVehicularObstacleDistance = obstacleDistance;
		int obstacleType = getObstacleType(getNSet);
		if ((obstacleType == 1)
				&& ((GlobalData.presentVehicularObstacleDistance <= -1) && (GlobalData.previousVehicularObstacleDistance > 1)))
			vehicularObstacleOver = true;
		return vehicularObstacleOver;
	}// end method

	// ************************************
	private int getObstacleType(GetNSet<E> getNSet) {
		// ************************************
		int obstacleType = 0;

		String signal1 = "CASA.Nexyad.ObstacleType";
		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//obstacleType = Integer.valueOf(getNSet.get(hashMap));
//
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			obstacleType = Integer.valueOf(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obstacleType;
	}// end method




	// ****************************************
	private double getObstacleSpeed(GetNSet<E> getNSet) {
		// ****************************************
		double obstacleSpeed = 0.0;
		String signal1 = "CASA.Nexyad.ObstacleSpeed";
		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//obstacleSpeed = Double.parseDouble(getNSet.get(hashMap));
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			obstacleSpeed = Double.parseDouble(value1);
			obstacleSpeed = (obstacleSpeed < 0.0) ? 0.0 : obstacleSpeed;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obstacleSpeed;
	}// end method



	// ******************************************
	private boolean obstacleIsPresent(GetNSet<E> getNSet) {
		// ******************************************
		boolean obstaclePresent = false;
		double timeToCollision = getObstacleTimeToCollision(getNSet);
		if ((timeToCollision > 0) && (timeToCollision <= 5))
			obstaclePresent = true;
		else {
			obstaclePresent = false;
			// GlobalData.presentMessageAlertDangerAvecObstacle = "";
			GlobalData.previousMessageAlertDangerAvecObstacle = "";
			GlobalData.notificationPossibleCollisionAlreadySent = false;
		}
		return obstaclePresent;
	}// end method


	// **************************************************
	private  double getObstacleTimeToCollision(GetNSet<E> getNSet) {
		// **************************************************
		double obstacleTimeToCollision = -1.0;



		String signal1 = "CASA.Nexyad.ObstacleTimeToCollision";
		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
////			obstacleTimeToCollision = Double
////					.parseDouble(getNSet.get(hashMap));
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			obstacleTimeToCollision = Double.parseDouble(value1);
			//obstacleTimeToCollision = Double.parseDouble(get(getNSet, signal1));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obstacleTimeToCollision;
	}// end method


	// *******************************************
	private double getObstacleDistance(GetNSet<E> getNSet) {
		// *******************************************
		double obstacleDistance = 0.0;
		String signal1 = "CASA.Nexyad.ObstacleDistance";
		try {
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//obstacleDistance =
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			obstacleDistance = Double.parseDouble(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return obstacleDistance;
	}// end method


	// *******************************************************
	private void implementTrafficRulesOnPedestrian(GetNSet<E> getNSet) {
		// *******************************************************
		// detectPedestrianObstacle();
		// detectArretAuPassagePieton();

		alertSecurityDistanceWithPedestrian(getNSet);

		//detectArretAuPassagePieton(); // FIXME fix problem

		if (pedestrianPassed(getNSet)) {
			// re-initialization detection du pi�ton
			GlobalData.previousMessageDetectPedestrianObstacle = "";
			GlobalData.presentMessageDetectPedestrianObstacle = "";
			GlobalData.pedestrianObstacleAlertAlreadySent = false;

			// re-initialization arret au passage pi�ton
			GlobalData.presentMessageArretAuPassagePieton = "";
			GlobalData.previousMessageArretAuPassagePieton = "";
			GlobalData.notificationArretAuPassagePietonAlreadySent = false;
		}// end if
	}// end method


	// ********************************************************
	private void alertSecurityDistanceWithPedestrian(GetNSet<E> getNSet) {
		// ********************************************************
		// Alerte pi�ton
		double speed = 0;
		boolean messageTheSame = true;


		int obstacleType = getObstacleType(getNSet);
		speed = getVehicleSpeed(getNSet);
		ContextVehicule.setVitesse(speed);

		if ((obstacleType == 2) && (pedestrianIsPresent(getNSet))) {
			valeur2[0] = "Alert";
			valeur2[1] = "8";
			valeur2[2] = "Danger";
			valeur2[3] = "Slow down. Pedestrian ahead.";
			valeur2[4] = "FALSE";
			valeur2[5] = Integer.toString(--GlobalData.drivingScore);
			valeur2[6] = "Danger";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
			currentId = 8;
		}
		//else if (!pedestrianIsPresent() && (obstacleType == 2) && currentId == 8) {
		else if (currentId == 8) {
			currentId = 0;
			for (int i = 0; i < 11; i++)
				valeur2[i] = "";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[11] = "ON";
		}
		// send output to CEA server
		// --------------------------

		//GlobalData.presentMessageAlertDangerAvecObstacle = valeur2[3];

		messageTheSame = false;
		try {
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)
				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet, hashMap);
			//GlobalData.previousMessageAlertDangerAvecObstacle = valeur2[3];

		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}

		// Test reading message that was sent to the server
		// ------------------------------------------------
		if (!messageTheSame) {
			getMessageSentToServer(getNSet);
		}
		//GlobalData.presentMessageAlertDangerAvecObstacle = "";
	}// end method


	// *******************************************
	private boolean pedestrianIsPresent(GetNSet<E> getNSet) {
		// *******************************************
		boolean obstaclePresent = false;
		int obstacleType = getObstacleType(getNSet);
		double timeToCollision = getObstacleTimeToCollision(getNSet);
		if (obstacleType == 2) {
			if ((timeToCollision > 0) && (timeToCollision <= 6))
				obstaclePresent = true;
			else {
				obstaclePresent = false;
			}
		}// end if
		return obstaclePresent;
	}// end method


	// ****************************************
	private boolean pedestrianPassed(GetNSet<E> getNSet) {
		// ****************************************
		boolean pedestrianPassed = false;
		int obstacleType = getObstacleType(getNSet);
		double obstacleDistance = getObstacleDistance(getNSet);

		// if ((obstacleType == 2) && ((obstacleDistance <= -1) &&
		// (obstacleDistance >= -2)))
		if ((obstacleType == 2) && (obstacleDistance == 99999))
			pedestrianPassed = true;
		return pedestrianPassed;
	}// end method


	// *********************************************
	private void detectDriverDisturbance(GetNSet<E> getNSet) {
		// *********************************************
		int driverIsFatigued = getDriverDisturbance(getNSet);

		if (driverIsFatigued == 1) {
			currentId = 1;
			// Danger! Driver not focused on driving
			valeur2[1] = "Alerte";
			valeur2[2] = "9";
			valeur2[3] = "You are tired. Do you want to activate energizing mode?";
			valeur2[4] = "TRUE";
			valeur2[5] = Integer.toString(--GlobalData.drivingScore);
			valeur2[6] = "Ability";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[11] = "ON";
		}
		else if (currentId == 1) {
			currentId = 0;
			for (int i = 0; i < 12; i++)
				valeur2[i] = "";
			for (int i = 7; i < 11; i++)
				valeur2[i] = "FALSE";
			valeur2[5] = Integer.toString(GlobalData.drivingScore);
			valeur2[11] = "ON";
		}// end else
		try {
			// messages are different, send output to the CEA server
			// -----------------------------------------------------
			HashMap<String, String> hashMap = new HashMap<>();
			for (int i = 0; i < tableau2.length; i++)

				hashMap.put(tableau2[i], valeur2[i]);
			setSignals(getNSet, hashMap);
			GlobalData.previousMessageOverspeeding = valeur2[3];
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
		getMessageSentToServer(getNSet);
	}// end method


	// *********************************************
	private int getDriverDisturbance(GetNSet<E> getNSet) {
		// *********************************************
		int driverDisturbance = -1;
		try {
			String signal1 = "CASA.Oktal.DriverDisturbance";
//			HashMap<String, String> hashMap = new HashMap<>();
//			hashMap.put(signal1, "");
//			//driverDisturbance = Integer.valueOf(getNSet.get(hashMap));
//			Bundle bundle = getNSet.get(hashMap);

			String value1 = getSignals(getNSet, signal1);

			driverDisturbance = Integer.valueOf(value1);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}// end catch
		return driverDisturbance;
	}// end method

	// *************************************
	private void updateVariables() {
		// *************************************
		GlobalData.previousLane = GlobalData.presentLane;
	}// end method

//	/**
//	 * The method to run UVSQ algorithm
//	 */
//	public void run() {
//
//		GetNSet<E> getNSet = new GetNSet<>(e);
//
//		//new GetNSet<E>(e).get(new HashMap<String, String>());
//
//		// Sample driving simulation
//		while (!stopFlag) {
//			initialState(getNSet);
//
//			if (engineStatusIsOn(getNSet))
//				debutSimulation(getNSet);
//
//			while ((engineStatusIsOn(getNSet))) {
////				// long start_time = System.nanoTime();
////				// 1. Detect overspeeding
//				// ----------------------
//				detectOverspeeding(getNSet);
//
//				// 2. Detect stop
//				// --------------
//				detectStop(getNSet);
//
//				// 3. Detect arret au stop
//				// -----------------------
//				detectArretAuStop(getNSet);
//
//				// 4. Detect direction in the intersection and activation of
//				// signal
//				// ---------------------------------------------------------
//				detectIntersectionDirection(getNSet);
//
//				// 5. Detecting and implement fog zone traffic rules
//				// ------------------------------------------------
//				implementFoggyAreaTrafficRules(getNSet);
//
//				// 6. Detect and identify obstacle
//				// --------------------------------
//				implementVehicularObstacleTrafficRules(getNSet);
//
//				// 7. Implement traffic rules concerning pedestrians
//				// --------------------------------------------------
//				implementTrafficRulesOnPedestrian(getNSet);
//
//				// 8. Detect driver fatigue
//				//--------------------------
//				detectDriverDisturbance(getNSet);
//
//				// wait for some delay
//				// --------------------------
//				updateVariables();
//				GlobalData.delay();
////
////				// long end_time = System.nanoTime();
////				// double difference = (end_time - start_time) / 1e6;
////				// System.out.println("Execution time of procedures: "+
////				// difference + " ms");
////
//			}// end while
//
//			// finalState();
//			// endSimulation();
//
//		}// end while
//
//		// FIXME UVSQ algorithms
//	}






}
