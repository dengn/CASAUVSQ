package com.uvsq.CASA;

import org.json.JSONObject;

import java.io.IOException;



public class GlobalData {
	
	public static boolean overSpeeding = false;
	public static String engineStatus = new String();
	public static boolean automaticFogLights = false;

	public static String previousMessageOverspeeding = "";
	public static String presentMessageOverspeeding = "";
	public static String previousMessageDetectStop = "";
	public static String presentMessageDetectStop = "";
	public static String previousMessageArretAuStop = "";
	public static String presentMessageArretAuStop = "";
	public static String previousMessageDetectIntersectionDirection = "";
	public static String presentMessageDetectIntersectionDirection = "";
	public static String previousMessageDetectFog = "";
	public static String presentMessageDetectFog = "";
	public static String previousMessageDetectFogLights = "";
	public static String presentMessageDetectFogLights = "";
	public static String previousMessageDetectVehicleSpeedFoggyZone = "";
	public static String presentMessageDetectVehicleSpeedFoggyZone = "";
	public static String previousMessageExitFoggyZone = "";
	public static String presentMessageExitFoggyZone = "";
	public static String previousMessageDetectVehicularObstacle = "";
	public static String presentMessageDetectVehicularObstacle = "";
	public static String previousMessageAlertDangerAvecObstacle = "";
	public static String presentMessageAlertDangerAvecObstacle = "";
	public static String previousMessageAlertPasDeDangerAvecObstacle = "";
	public static String presentMessageAlertPasDeDangerAvecObstacle = "";
	public static String previousMessageNotificationBeforeOvertakingObstacle = "";
	public static String presentMessageNotificationBeforeOvertakingObstacle = "";
	public static String previousMessageNotificationOvertakingObstacle = "";
	public static String presentMessageNotificationOvertakingObstacle = "";
	public static String previousMessageNotificationAfterOvertakingObstacle = "";
	public static String presentMessageNotificationAfterOvertakingObstacle = "";
	public static String previousMessageNotificationPedestrianLane = "";
	public static String presentMessageNotificationPedestrianLane = "";
	public static String presentMessageDetectPedestrianObstacle = "";
	public static String previousMessageDetectPedestrianObstacle = "";
	public static String presentMessageArretAuPassagePieton = "";
	public static String previousMessageArretAuPassagePieton = "";
	public static String previousMessageNotificationPossibleCollision = "";
	public static String presentMessageNotificationPossibleCollision = "";
	//static String presentMessageDetectStop = "";


	public static boolean stopMessageAlreadySent = false;
	public static boolean arretAuStopMessageAlreadySent = false;
	public static boolean intersectionDirectionMessageAlreadySent = false;
	public static boolean foggyAreaNotificationMessageAlreadySent = false;
	public static boolean fogLightsMessageAlreadySent = false;
	public static boolean foggyAreaVehicularSpeedNotificationMessageAlreadySent = false;
	public static boolean exitFoggyAreaAlreadySent = false;
	public static boolean vehicularObstacleAlertAlreadySent = false;
	public static boolean notificationBeforeOvertakingObstacleAlreadySent = false;
	public static boolean notificationOvertakingObstacleAlreadySent = false;
	public static boolean notificationAfterOvertakingObstacleAlreadySent = false;
	public static boolean notificationPedestrianLaneAlreadySent = false;
	public static boolean pedestrianObstacleAlertAlreadySent = false;
	public static boolean notificationArretAuPassagePietonAlreadySent = false;
	public static boolean notificationPossibleCollisionAlreadySent = false;
	//static boolean stopMessageAlreadySent = false;


	public static double previousFogVisibilityDistance = 10000;
	public static double presentFogVisibilityDistance = 10000;
	public static double previousVehicularObstacleDistance = 1000;
	public static double presentVehicularObstacleDistance = 1000;
	public static int previousLane = 0;
	public static int presentLane = 0;
	public static int previousDirection = 0;
	public static int presentDirection = 0;

	public static double distance_avec_Stop = 0.0;
	public static double distance_avec_Intersection = 0.0;
	public static double distance_avec_PassagePieton = 0.0;
	public static double distance_avec_Vehicule_avant = 0.0;
	public static double distance_avec_Vehicule_derriere = 0.0;
	public static double vitesse_dans_le_brouillard = 0.0;

	
	//static int drivingScore = 100;
	public static int drivingScore = 0;

	public static String previousDrivingLevel = "";
	public static String presentDrivingLevel = "";
	public static double previousSpeedLimit = 50.0;
	public static double presentSpeedLimit = 50.0;
	public static double previousSpeed = 0.0;
	public static double presentSpeed = 0.0;

	public static boolean informerConducteur_distance_avec_Stop = false;
	public static boolean informerConducteur_distance_avec_PassagePieton = false;
	public static boolean informerConducteur_distance_avec_Intersection = false;
	public static boolean informerConducteur_oubli_arreter_au_Stop = false;
	public static boolean informerConducteur_oubli_arreter_au_PassagePieton = false;
	public static boolean informerConducteur_oubli_clignotant_droite = false;
	public static boolean informerConducteur_oubli_clignotant_gauche = false;
	public static boolean vitesseAdaptee_sur_le_brouillard = false;
	public static boolean vitesseExcessive_sur_le_brouillard = false;
	public static boolean vitesseDangereuse_sur_le_brouillard = false;
	public static boolean respectDistance_de_securite = false;
	public static boolean activationPharesBrouillard = false;
	public static boolean vitesseAdaptee_face_a_lobstacle = false;
	public static boolean vitesseExcessive_face_a_lobstacle = false;
	public static boolean vitesseDangereuse_face_a_lobstacle = false;
	public static boolean trafficLibre_VoieOpposee = false;
	public static boolean attentionConducteur_en_baisse = false;

	public static boolean informerConducteur_vitesseExcessive_sur_le_brouillard = false;
	public static boolean informerConducteur_vitesseDangereuse_sur_le_brouillard = false;
	public static boolean informerConducteur_vitesseExcessive_face_a_lobstacle = false;
	public static boolean informerConducteur_vitesseDangereuse_face_a_lobstacle = false;
	public static boolean informerConducteur_trafficPasLibre_VoieOpposee = false;

	public static JSONObject objEvent,
	                  objEvent1, objEvent2, objEvent3, objEvent4, objEvent5, 
					  objEvent6, objEvent7, objEvent8, objEvent9, objEvent10;
	public static JSONObject outputEvent,
	                  outputEvent1, outputEvent2, outputEvent3,outputEvent4, outputEvent5,
					  outputEvent6, outputEvent7, outputEvent8,outputEvent9, outputEvent10;

	public static boolean informerConducteur_respecte_distance_de_securite = false;
	public static boolean informerConducteur_echecActivation_phares_de_brouillard = false;
	public static boolean zoneDeBrouillard = false;
	
	
	//***************************
	public static void delay() {
	//***************************
	  try {
	     Thread.sleep(200);      //1000 milliseconds = 1 second
	     } 
	  catch (InterruptedException ie) {
	        ie.printStackTrace();
	      }
	}//end method
	
	public static void clearScreen()  {
		for (int i = 0; i < 24; i++)
			System.out.println();
		//System.out.print("\u001b[2J");
		//System.out.flush();
		
		//Runtime.getRuntime().exec("cls");
		
		//ConsoleReader r = new ConsoleReader();
		//r.clearScreen();
		
	}

	//------------------------------------------------------------
	public static void pressAnyKeyToContinue() throws IOException { 
		/*
	   System.out.println("Press any key to continue...");
	   try {
	    	BufferedReader input = new BufferedReader(new InputStreamReader(System.in, "UTF-8"));
            char c = (char) input.read();
	    }  //end try
	    catch(Exception e) {}  
	    */
	 }
	
	//---------------------------------------
	public static void waitAndClearScreen() {
		delay();
		try {pressAnyKeyToContinue();}
		catch (IOException e) {
			System.out.println("Error: " + e.getMessage());
		}
		clearScreen();
		
		
	}//end method
	
}//end class 
