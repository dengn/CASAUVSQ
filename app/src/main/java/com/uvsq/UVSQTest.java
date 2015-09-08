package com.uvsq;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.uvsq.CASA.DetectParameterChange;
import com.uvsq.CASA.EventCondition;
import com.uvsq.CASA.GetData;
import com.uvsq.CASA.GlobalData;
import com.uvsq.CASA.Historique;
import com.uvsq.CASA.Message;
import com.uvsq.connect2datanex.GetNSet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import de.greenrobot.event.EventBus;


/**
 * Created by etudiant on 28/07/2015.
 */
public class UVSQTest<E> {

    private static final String[] table = {"CASA.UVSQ.Message.level",
            "CASA.UVSQ.Message.levelForce", "CASA.UVSQ.Message.category",
            "CASA.UVSQ.Message.value", "CASA.UVSQ.Message.question",
            "CASA.UVSQ.DrivingScore", "CASA.UVSQ.Image",
            "CASA.UVSQ.ActiveLampsRearFog", "CASA.UVSQ.ActiveLampsRearFog",
            "CASA.UVSQ.ActiveAirConditioning", "CASA.UVSQ.ActiveAirRecycling",
            "CASA.UVSQ.EngineStatus"};
    //private static String[] value = { "", "", "", "", "", "", "", "", "", "", "", "" };
    private static Message myMessage = new Message();
    private static boolean arretStopBool = false;
    private static long initTime = 0;
    private static long currentTime = 0;
    private static boolean fogLightFlag = false;
    private static int currentID = 0;


    //Flags to see if rules detected
    private boolean overSpeedingDetected = false;
    private boolean stopDetected = false;
    private boolean stoppingAtStopDetected = false;
    private boolean turingDirectionIndicatorDetected = false;
    private boolean foggyZoneDetected = false;
    private boolean vehicleSpeedInFoggyZoneDetected = false;
    private boolean exitFoggyZoneDetected = false;
    private boolean vehicularObstacleSecurityDistanceDetected = false;
    private boolean pedestrianSecurityDistanceDetected = false;
    private boolean driverDisturbuanceDetected = false;


    private ArrayList<Message> messages = new ArrayList<>();


    private Object e;

    private boolean stopFlag = false;
    private Context context;


    private GetData<E> getData = new GetData<E>();
    private DetectParameterChange<E> detectParameterChange;


    /**
     * Constructor with IRemoteInterface object
     *
     * @param e
     */
    public UVSQTest(Object e) {
        this.e = e;
    }


    /**
     * Set the thread to stop
     *
     * @param stopFlag
     */
    public void setStopIt(boolean stopFlag) {
        this.stopFlag = stopFlag;
    }

    /**
     * Set the Android UI context for further use
     *
     * @param context
     */
    public void setContext(Context context) {
        this.context = context;
    }


    /**
     * The main function to communicate with the service to get signals.
     *
     * @param getNSet the object which manages the communication
     * @param signal  the signal name
     * @return result
     */
    private String getSignals(GetNSet<E> getNSet, String signal) {
        Bundle input = new Bundle();
        input.putString(signal, "");
        Bundle bundle = getNSet.get(input);
        String value1 = bundle.getString(signal);
        return value1;
    }

    /**
     * The function to set signals to service.
     *
     * @param getNSet the object which manages the communication
     * @param bundle  the information carrier
     */
    private void setSignals(GetNSet<E> getNSet, Bundle bundle) {
        getNSet.set(bundle);
    }


    /**
     * Send the final processing result to Server.
     *
     * @param getNSet the object which manages the communication
     */
    //***********************************
    public void sendMessageToServer(GetNSet<E> getNSet) {
        try {
            // -----------------------------------------------------
            Bundle bundle = new Bundle();
            //Put all signals to the bundle, send it to service
            bundle.putString(table[0], myMessage.getMessageLevel());
            bundle.putString(table[1], myMessage.getMessageLevelForce());
            bundle.putString(table[2], myMessage.getMessageCategory());
            bundle.putString(table[3], myMessage.getMessageValue());
            bundle.putString(table[4], myMessage.getMessageQuestion());
            bundle.putString(table[5], myMessage.getDrivingScore());
            bundle.putString(table[6], myMessage.getMessageImage());
            bundle.putString(table[7], myMessage.getActiveLampsRearFogStatus());
            bundle.putString(table[8], myMessage.getActiveLampsRearFog2Status());
            bundle.putString(table[9], myMessage.getActiveAirConditioningStatus());
            bundle.putString(table[10], myMessage.getActiveAirRecyclingStatus());
            bundle.putString(table[11], myMessage.getEngineStatus());

            setSignals(getNSet, bundle);
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
    }


    /**
     * Send empty message to empty DPS UI
     *
     * @param getNSet the object which manages the communication
     */
    //****************************************
    public void processEmptyMessage(GetNSet<E> getNSet) {
        myMessage = new Message();
        myMessage.setEmptyMessage();
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        sendMessageToServer(getNSet);
    }//end method

    //**********************************
    //Set the initial state to Engine OFF.
    private void initialState(GetNSet<E> getNSet) {


        myMessage.setEmptyMessage();
        myMessage.setDrivingScore(Integer.toString(0));
        myMessage.setEngineStatus("OFF");
        GlobalData.drivingScore = 0;
        GlobalData.presentDirection = 0;
        GlobalData.previousDirection = 0;

        sendMessageToServer(getNSet);
    }// end method


    // ***************************************
    //Check if the engine status is ON
    private boolean engineStatusIsOn(GetNSet<E> getNSet) {
        // ***************************************
        boolean engineOn = false;
        String status = getData.getEngineStatus(getNSet);
        if (status.equalsIgnoreCase("ON"))
            engineOn = true;
        else
            engineOn = false;
        return engineOn;
    }//end method

    // *************************************
    private void startSimulation(GetNSet<E> getNSet) {

        myMessage = new Message();
        myMessage.setEmptyMessage();
        myMessage.setDrivingScore(Integer.toString(0));
        myMessage.setEngineStatus("ON");
        GlobalData.drivingScore = 0;
        sendMessageToServer(getNSet);
    }// end method


    //**********************************************************
    private void processExcessiveSpeed(double speedLimit) {
        myMessage = new Message();
        int deduction = 0;
        myMessage.setMessageLevel("Alert");
        double speed = GlobalData.new_VehicleSpeed;
        // calculate level force (from 6 to 8)
        // ------------------------------------
        double temp = (speedLimit * 0.20) / 3;
        if (speed <= temp + GlobalData.new_SpeedLimit)
            myMessage.setMessageLevelForce("6");
        else if ((speed > temp + speedLimit)
                && (speed <= 2 * temp + speedLimit))
            myMessage.setMessageLevelForce("7");
        else
            myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("Slow down. Speed limit is " + ((int) speedLimit) + " km per hour.");
        myMessage.setMessageQuestion("False");
        // compute deduction
        // -----------------------------------------------------
        if ((speed > speedLimit) && (speed <= 1.10 * speedLimit))
            deduction = 1;
        else if ((speed > 1.10 * speedLimit) && (speed <= 1.20 * speedLimit))
            deduction = 2;
        GlobalData.drivingScore -= deduction;
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        Historique.nombre_infractionSurvitesse++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 1;
    }//end method

    //**********************************************************
    private void processDangerousSpeed(double speedLimit) {

        myMessage = new Message();
        double speed = GlobalData.new_VehicleSpeed;
        int deduction = 0;
        myMessage.setMessageLevel("Alert");
        // calculate level force (from 9 to 10)
        // --------------------------------------------------------------
        if ((speed > speedLimit * 1.20) && (speed <= speedLimit * 1.30))
            myMessage.setMessageLevelForce("9");
        else
            myMessage.setMessageLevelForce("10");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("Slow down. Speed limit is " + ((int) speedLimit) + " km per hour");
        myMessage.setMessageQuestion("False");
        // infraction
        // ---------------------------------------------------
        deduction = 3;
        GlobalData.drivingScore -= deduction;
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        Historique.nombre_infractionSurvitesse++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 1;
    }//end method


    //**********************************************************
    private void processOutOfRoad() {
        myMessage = new Message();

        int deduction = 0;
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("6");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You are off the road.");
        myMessage.setMessageQuestion("False");
        // infraction
        // ---------------------------------------------------
        deduction = 3;
        GlobalData.drivingScore -= deduction;
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        Historique.nombre_infractionSurvitesse++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 1;
    }//end method


    //***************************************
    private void detectOverspeeding() {
        //Firstly detect if the Vehicle is out of road
        if (EventCondition.isOutOfRoad()) {
            processOutOfRoad();
            //Set corresponding flag
            overSpeedingDetected = true;
        } else if ((EventCondition.isExcessiveSpeeding())) {
            processExcessiveSpeed(GlobalData.new_SpeedLimit);
            overSpeedingDetected = true;
        } else if ((EventCondition.isDangerousSpeeding())) {
            processDangerousSpeed(GlobalData.new_SpeedLimit);
            overSpeedingDetected = true;
        } else {
            overSpeedingDetected = false;
        }


    }//end method

    //******************************************
    private void processStopSignal() {
        myMessage = new Message();
        myMessage.setMessageLevel("Notification");
        myMessage.setMessageLevelForce("5");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("Get ready to stop.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentTime += 10000;
        currentID = 2;
    }//end method


    //*******************************
    private void detectStop() {
        if ((EventCondition.isStopSignalDetected())) {
            processStopSignal();
            stopDetected = true;
        } else {
            stopDetected = false;
        }

    }//end method


    //*******************************************************
    private void processRunningOnStopSignalMessage() {
        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You just run a stop.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 3;
    }

    //******************************************
    private void detectStoppingAtStop() {
        arretStopBool |= (EventCondition.isStoppingAtStop());
        if (GlobalData.changed_IntersectionDirectionFromOktal) {
            if ((!arretStopBool)) {
                processRunningOnStopSignalMessage();
                stoppingAtStopDetected = true;
            }//end if
            else {
                stoppingAtStopDetected = false;
            }
            arretStopBool = false;
        }//end if
        else {
            stoppingAtStopDetected = false;
        }

    }//end method


    //*************************************************************
    private void processForgotTurningLeftSignalIndicator() {
        myMessage = new Message();
        myMessage.setMessageLevel("Notification");
        myMessage.setMessageLevelForce("7");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You forgot to turn your signal on.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        Historique.nombre_Oubli_clignoter_gauche++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 4;
    }    //end method

    //**************************************************************
    private void processForgotTurningRightSignalIndicator() {
        myMessage = new Message();
        myMessage.setMessageLevel("Notification");
        myMessage.setMessageLevelForce("7");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You forgot to turn your signal on.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        Historique.nombre_Oubli_clignoter_droite++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 4;
    }    //end method


    //****************************************************
    private void detectTurningDirectionIndicator() {
        currentTime = new Date().getTime();

        if ((EventCondition.isLeftSignalOff()) && GlobalData.changed_IntersectionDirectionFromOktal) {
            processForgotTurningLeftSignalIndicator();
            turingDirectionIndicatorDetected = true;
        } else if ((EventCondition.isRightSignalOff()) && GlobalData.changed_IntersectionDirectionFromOktal) {
            processForgotTurningRightSignalIndicator();
            turingDirectionIndicatorDetected = true;
        } else {
            turingDirectionIndicatorDetected = false;
        }
        initTime = new Date().getTime();
        currentTime = initTime + 10000;
    }//end method


    //**************************************
    private void processFogMessage() {

        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Danger");
        myMessage.setMessageValue("Low visibility. Do you want to activate fog lights ?");
        myMessage.setMessageQuestion("TRUE");
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        fogLightFlag = true;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);

        currentID = 5;
    }//end method


    //***********************************
    private void detectFoggyZone() {
        if ((EventCondition.isStartOfFoggyZone()) && (!fogLightFlag)) {
            processFogMessage();
            foggyZoneDetected = true;
        } else {
            foggyZoneDetected = false;
        }

    }//end method


    //***************************************************
    private void detectVehicleSpeedInFoggyZone() {
        double speedLimit = 50;
        if (EventCondition.isWithinFoggyZone()) {
            if ((EventCondition.isExcessiveSpeedingInFoggyZone())) {
                processExcessiveSpeed(speedLimit);
                vehicleSpeedInFoggyZoneDetected = true;
            } else if ((EventCondition.isDangerousSpeedingInFoggyZone())) {
                processDangerousSpeed(speedLimit);
                vehicleSpeedInFoggyZoneDetected = true;
            } else {
                vehicleSpeedInFoggyZoneDetected = false;
            }
        }//end if
        else {
            vehicleSpeedInFoggyZoneDetected = false;
        }
    }//end method


    //******************************************
    private void processExitFoggyZone() {

        myMessage = new Message();
        myMessage.setMessageLevel("Notification");
        myMessage.setMessageLevelForce("5");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You are leaving the foggy zone.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        fogLightFlag = false;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 6;
    }//end method


    //*****************************************
    private void detectExitFoggyZone() {
        if ((EventCondition.isleavingFoggyZone())) {
            processExitFoggyZone();
            exitFoggyZoneDetected = true;
        } else {
            exitFoggyZoneDetected = false;
        }
    }//end method


    //*************************************************************************
    private void processSecurityDistanceMessageWithVehicularObstacle() {

        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Danger");
        myMessage.setMessageValue("Slow down. Vehicle ahead.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Danger");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 7;

    }//end method

    //*****************************************************
    private void detectVehicularObstacleSecurityDistance() {
        // Alerte obstacle vehicule
        if ((EventCondition.isVehicularObstacleDistanceDangerous())) {
            processSecurityDistanceMessageWithVehicularObstacle();
            vehicleSpeedInFoggyZoneDetected = true;
        } else {
            vehicleSpeedInFoggyZoneDetected = false;
        }

    }//end method


    //*************************************************************
    private void processPedestrianSecurityDistanceMessage() {

        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Danger");
        myMessage.setMessageValue("Slow down. Pedestrian ahead.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Danger");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 8;
    }//end method


    //*****************************************************
    private void detectPedestrianSecurityDistance() {
        if ((EventCondition.isPedestrianObstaclePresent())) {
            processPedestrianSecurityDistanceMessage();
            pedestrianSecurityDistanceDetected = true;
        } else {
            pedestrianSecurityDistanceDetected = false;
        }

    }//end method


    //********************************************************
    private void processRunningPedestrianLaneMessage() {
        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("8");
        myMessage.setMessageCategory("Behavior");
        myMessage.setMessageValue("You just run a pedestrian lane.");
        myMessage.setMessageQuestion("FALSE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Behavior");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");

        Historique.nombre_Oubli_arreter_au_PassagePieton++;

        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 9;
    }//end method


    //****************************************************
    //Not used
    private void detectStoppingOnPedestrianLane() {

        if (EventCondition.isRunningOnPedestrianLane())
            processRunningPedestrianLaneMessage();

    }//end method


    //*****************************************************
    private void processDriverDisturbanceMessage() {
        // Danger! Driver not focused on driving
        myMessage = new Message();
        myMessage.setMessageLevel("Alert");
        myMessage.setMessageLevelForce("9");
        myMessage.setMessageCategory("Ability");
        myMessage.setMessageValue("You are tired. Do you want to activate energizing mode?");
        myMessage.setMessageQuestion("TRUE");
        myMessage.setDrivingScore(Integer.toString(--GlobalData.drivingScore));
        myMessage.setMessageImage("Ability");
        myMessage.setActiveLampsRearFog("FALSE");
        myMessage.setActiveLampsRearFog2("FALSE");
        myMessage.setActiveAirConditioning("FALSE");
        myMessage.setActiveAirRecycling("FALSE");
        myMessage.setEngineStatus("ON");
        //For UVSQ Activity Test Use
        EventBus.getDefault().post(new MessageEvent(myMessage.getMessageValue()));

        //Add message to priority list
        messages.add(myMessage);
        currentID = 10;
    }//end method


    //*********************************************
    private void detectDriverDisturbance() {
        if ((EventCondition.isDriverTired())) {
            processDriverDisturbanceMessage();
            driverDisturbuanceDetected = true;
        } else {
            driverDisturbuanceDetected = false;
        }

    }//end method


    private void processIMATIASignal(GetNSet<E> getNSet) {
        String incitation = getSignals(getNSet, "CASA.IMATIA.RemoveFoot.Incitation");
        Bundle inputIncitation = new Bundle();
        inputIncitation.putString("CASA.UVSQ.RemoveFoot.Incitation", incitation);
        setSignals(getNSet, inputIncitation);

        String explicitSpeedLimit = getSignals(getNSet, "CASA.IMATIA.RemoveFoot.ExplicitSpeedLimit");
        Bundle inputExplicitSpeedLimit = new Bundle();
        inputExplicitSpeedLimit.putString("CASA.UVSQ.RemoveFoot.ExplicitSpeedLimit", explicitSpeedLimit);
        setSignals(getNSet, inputExplicitSpeedLimit);
    }

    //***********************************************
    private void executeTopPriorityMessage(GetNSet<E> getNSet) {
        Collections.sort(messages);

        //number of elements in the list
        int i = messages.size();
        if (i > 0) {


            Message temp = messages.get(0); //this one on top has the highest priority
            myMessage = new Message();
            myMessage = temp;
            sendMessageToServer(getNSet);
            messages.clear();

        }//end if

        messages.clear();              //clear the arrayList
    }//end method

    //*********************************
    private void selectEvent(GetNSet<E> getNSet) {
        detectOverspeeding();
        detectStop();
        detectStoppingAtStop();
        detectTurningDirectionIndicator();
        detectFoggyZone();
        detectVehicleSpeedInFoggyZone();
        detectExitFoggyZone();
        detectVehicularObstacleSecurityDistance();
        detectPedestrianSecurityDistance();
        detectDriverDisturbance();

        //Send the most important message
        executeTopPriorityMessage(getNSet);

        processIMATIASignal(getNSet);

        //When there is no rules detected, we send empty message
        if (!(overSpeedingDetected || stopDetected || stoppingAtStopDetected || turingDirectionIndicatorDetected || foggyZoneDetected || vehicleSpeedInFoggyZoneDetected || exitFoggyZoneDetected || vehicularObstacleSecurityDistanceDetected || pedestrianSecurityDistanceDetected || driverDisturbuanceDetected)) {
            try {
                Thread.currentThread().sleep(1800);
            } catch (Exception e) {
            }
            processEmptyMessage(getNSet);
        }
    }//end method


    /*
    The method to run UVSQ Algorithm
	 */
    public void run() {
        GetNSet<E> getNSet = new GetNSet<>(e);
        detectParameterChange = new DetectParameterChange<>(getNSet);

        while (!stopFlag) {
            initialState(getNSet);
            if (engineStatusIsOn(getNSet)) {
                startSimulation(getNSet);
                getData.getCurrentData(getNSet);
            }//end if

            while (engineStatusIsOn(getNSet)) {
                getData.getNextData(getNSet);
                detectParameterChange.detectDataChange();
                selectEvent(getNSet);
                getData.updateAllData();
                GlobalData.delay();
            }//end while
        }//end while (true)
    }
}
