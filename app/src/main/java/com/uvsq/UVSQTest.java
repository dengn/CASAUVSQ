package com.uvsq;

import android.content.Context;
import android.os.Bundle;

import com.uvsq.CASA.DetectParameterChange;
import com.uvsq.CASA.EventCondition;
import com.uvsq.CASA.GetData;
import com.uvsq.CASA.GlobalData;
import com.uvsq.CASA.Historique;
import com.uvsq.connect2datanex.GetNSet;

import java.util.Date;
import java.util.HashMap;

import de.greenrobot.event.EventBus;


/**
 * Created by etudiant on 28/07/2015.
 */
public class UVSQTest<E> {

    private static final String[] table = { "CASA.UVSQ.Message.level",
            "CASA.UVSQ.Message.levelForce", "CASA.UVSQ.Message.category",
            "CASA.UVSQ.Message.value", "CASA.UVSQ.Message.question",
            "CASA.UVSQ.DrivingScore", "CASA.UVSQ.Image",
            "CASA.UVSQ.ActiveLampsRearFog", "CASA.UVSQ.ActiveLampsRearFog",
            "CASA.UVSQ.ActiveAirConditioning", "CASA.UVSQ.ActiveAirRecycling",
            "CASA.UVSQ.EngineStatus" };
    private static String[] value = { "", "", "", "", "", "", "", "", "", "", "", "" };
    private static boolean arretStopBool = false;
    private static long initTime = 0;
    private static long currentTime = 0;
    private static boolean fogLightFlag = false;
    private static int currentID = 0;



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


    public void setStopIt(boolean stopFlag){
        this.stopFlag = stopFlag;
    }

    public void setContext(Context context){
        this.context = context;
    }



    private String getSignals(GetNSet<E> getNSet, String signal){
        Bundle input = new Bundle();
        input.putString(signal, "");
        Bundle bundle = getNSet.get(input);
        String value1 = bundle.getString(signal);
        return value1;
    }

    private void setSignals(GetNSet<E> getNSet, Bundle bundle){
        getNSet.set(bundle);
    }


    //***********************************
    public void sendMessageToServer(GetNSet<E> getNSet){
        try {
            // messages are different, send output to the CEA server
            // -----------------------------------------------------
            Bundle bundle = new Bundle();
            for (int i = 0; i < table.length; i++)
                bundle.putString(table[i], value[i]);
            setSignals(getNSet, bundle);
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
    }//end method


    //**********************************
    private void initialState(GetNSet<E> getNSet) {

        for (int i = 0; i < table.length; i++)
            value[i] = "";
        value[5] = Integer.toString(0);
        value[11] = "OFF";
        GlobalData.drivingScore = 0;
        GlobalData.presentDirection = 0;
        GlobalData.previousDirection = 0;

        try {// get message
            String data = "CASA.UVSQ.ActiveLampsLowBeamOn";

            Bundle bundle = new Bundle();
            bundle.putString(data, "0");
            setSignals(getNSet, bundle);
        }
        catch (Exception e) {}
        sendMessageToServer(getNSet);
    }// end method


    // ***************************************
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

        for (int i = 0; i < table.length; i++)
            value[i] = "";
        value[5] = Integer.toString(0);
        value[11] = "ON";
        GlobalData.drivingScore = 0;
        sendMessageToServer(getNSet);
    }// end method


    //****************************************
    private void processEmptyMessage(GetNSet<E> getNSet) {
        for (int i = 0; i < 12; i++)
            value[i] = "";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[11] = "ON";
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processEmptyMessages", value)));
        sendMessageToServer(getNSet);
    }//end method



    //**********************************************************
    private void processExcessiveSpeed(double speedLimit, GetNSet<E> getNSet){
        int deduction = 0;
        value[0] = "Alert";
        double speed = GlobalData.new_VehicleSpeed;
        // calculate level force (from 6 to 8)
        // ------------------------------------
        double temp = (speedLimit * 0.20) / 3;
        if (speed <= temp + GlobalData.new_SpeedLimit)
            value[1] = "6";
        else if ((speed > temp + speedLimit)
                && (speed <= 2 * temp + speedLimit))
            value[1] = "7";
        else
            value[1] = "8";
        value[2] = "Behavior";
        value[3] = "Slow down. Speed limit is " + ((int) speedLimit) + " km per hour.";
        value[4] = "False";
        // compute deduction
        // -----------------------------------------------------
        if ((speed > speedLimit) && (speed <= 1.10 * speedLimit))
            deduction = 1;
        else if ((speed > 1.10 * speedLimit) && (speed <= 1.20 * speedLimit))
            deduction = 2;
        GlobalData.drivingScore -= deduction;
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_infractionSurvitesse++;

        //Log.i(Util.TAG, Util.writeLog("processExcessiveSpeed", value));

        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processExcessiveSpeed", value)));

        sendMessageToServer(getNSet);
        currentID = 1;
    }//end method

    //**********************************************************
    private void processDangerousSpeed(double speedLimit, GetNSet<E> getNSet){
        double speed = GlobalData.new_VehicleSpeed;
        int deduction = 0;
        value[0] = "Alert";
        // calculate level force (from 9 to 10)
        // --------------------------------------------------------------
        if ((speed > speedLimit * 1.20) && (speed <= speedLimit * 1.30))
            value[1] = "9";
        else
            value[1] = "10";
        value[2] = "Behavior";
        value[3] = "Slow down. Speed limit is " + ((int) speedLimit) + " km per hour";
        value[4] = "False";
        // infraction
        // ---------------------------------------------------
        deduction = 3;
        GlobalData.drivingScore -= deduction;
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_infractionSurvitesse++;
        sendMessageToServer(getNSet);

        //Log.i(Util.TAG, Util.writeLog("processDangerousSpeed", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processDangerousSpeed", value)));
        currentID = 1;
    }//end method


    //***************************************
    private void detectOverspeeding(GetNSet<E> getNSet) {
        if ((EventCondition.isExcessiveSpeeding()) && (currentID != 1))
            processExcessiveSpeed(GlobalData.new_SpeedLimit, getNSet);
        if ((EventCondition.isDangerousSpeeding()) && (currentID != 1))
            processDangerousSpeed(GlobalData.new_SpeedLimit, getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 1) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }//end if

    }//end method

    //******************************************
    private void processStopSignal(GetNSet<E> getNSet){
        value[0] = "Notification";
        value[1] = "5";
        value[2] = "Behavior";
        value[3] = "Get ready to stop.";
        value[4] = "FALSE";
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processStopSignal", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processStopSignal", value)));
        currentTime +=10000;
        currentID = 2;
    }//end method


    //*******************************
    private void detectStop(GetNSet<E> getNSet){
        if ((EventCondition.isStopSignalDetected()) && (currentID != 2))
            processStopSignal(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 2) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }
    }//end method


    //*******************************************************
    private void processRunningOnStopSignalMessage(GetNSet<E> getNSet) {
        value[0] = "Alert";
        value[1] = "8";
        value[2] = "Behavior";
        value[3] = "You just run a stop";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_Oubli_arreter_au_Stop++;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processRunningOnStopSignalMessage", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processRunningOnStopSignalMessage", value)));
        currentID = 3;
    }

    //******************************************
    private void detectStoppingAtStop(GetNSet<E> getNSet) {
        arretStopBool |= (EventCondition.isStoppingAtStop());
        if (GlobalData.changed_IntersectionDirectionFromOktal && (currentTime>20000)) {
            if ((!arretStopBool) && (currentID != 3)) {
                processRunningOnStopSignalMessage(getNSet);
            }//end if
            //to avoid repetition of the same message while on the same condition
            if (currentID == 3){
                processEmptyMessage(getNSet);
                currentID = 0;
            }//end if
            arretStopBool = false;
        }//end if

    }//end method


    //*************************************************************
    private void processForgotTurningLeftSignalIndicator(GetNSet<E> getNSet) {
        value[0] = "Notification";
        value[1] = "7";
        value[2] = "Behavior";
        value[3] = "You forgot to turn your signal on.";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_Oubli_clignoter_gauche++;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processForgotTurningLeftSignalIndicator", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processForgotTurningLeftSignalIndicator", value)));
        currentID = 4;
    }	//end method

    //**************************************************************
    private void processForgotTurningRightSignalIndicator(GetNSet<E> getNSet) {
        value[0] = "Notification";
        value[1] = "7";
        value[2] = "Behavior";
        value[3] = "You forgot to turn your signal on.";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_Oubli_clignoter_droite++;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processForgotTurningRightSignalIndicator", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processForgotTuringRightSignalIndicator", value)));
        currentID = 4;
    }	//end method


    //****************************************************
    private void detectTurningDirectionIndicator(GetNSet<E> getNSet) {
        currentTime = new Date().getTime();

        ////Log.i(Util.TAG, "detectTuringDirectionIndicator: "+"EventCondition.isLeftSignalOff(): "+EventCondition.isLeftSignalOff()+" currentId: "+currentID);

        if ((EventCondition.isLeftSignalOff()) && (currentID != 4))
            processForgotTurningLeftSignalIndicator(getNSet);
        if ((EventCondition.isRightSignalOff()) && (currentID != 4))
            processForgotTurningRightSignalIndicator(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 4) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }//end if
        initTime = new Date().getTime();
        currentTime = initTime + 10000;
    }//end method



    //**************************************
    private void processFogMessage(GetNSet<E> getNSet){
        value[0] = "Alert";
        value[1] = "8";
        value[2] = "Danger";
        value[3] = "Low visibility. Do you want to activate fog lights ?";
        value[4] = "TRUE";
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        //value[7] = "2";
        try {// get message
            String data = "CASA.UVSQ.ActiveLampsLowBeamOn";
            Bundle bundle = new Bundle();
            bundle.putString(data, "2");
            setSignals(getNSet, bundle);
        }
        catch (Exception e) {}
        fogLightFlag = true;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processFogMessage", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processFogMessage", value)));
        currentID = 5;
    }//end method


    //***********************************
    private void detectFoggyZone(GetNSet<E> getNSet){
        if ((EventCondition.isStartOfFoggyZone()) && (!fogLightFlag) && (currentID !=5))
            processFogMessage(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 5) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }//end if
    }//end method


    //***************************************************
    private void detectVehicleSpeedInFoggyZone(GetNSet<E> getNSet) {
        double speedLimit = 50;
        if (EventCondition.isWithinFoggyZone()) {
            if ((EventCondition.isExcessiveSpeedingInFoggyZone()) && (currentID != 1))
                processExcessiveSpeed(speedLimit, getNSet);
            if ((EventCondition.isDangerousSpeedingInFoggyZone()) && (currentID != 1))
                processDangerousSpeed(speedLimit, getNSet);
            //to avoid repetition of the same message while on the same condition
            if (currentID == 1) {
                processEmptyMessage(getNSet);
                currentID = 0;
            }//end if
        }//end if
    }//end method


    //******************************************
    private void processExitFoggyZone(GetNSet<E> getNSet) {

        value[0] = "Notification";
        value[1] = "5";
        value[2] = "Behavior";
        value[3] = "You're leaving the foggy zone"; // exiting foggy area
        value[4] = "False";
        value[5] = Integer.toString(GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";

        try {// get message
            String data = "CASA.UVSQ.ActiveLampsLowBeamOn";

            Bundle bundle = new Bundle();
            bundle.putString(data, "0");
            setSignals(getNSet, bundle);
        }
        catch (Exception e) {}
        fogLightFlag = false;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processExitFoggyZone", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processExitFoggyZone", value)));
        currentID = 6;
    }//end method


    //*****************************************
    private void detectExitFoggyZone(GetNSet<E> getNSet) {
        if ((EventCondition.isleavingFoggyZone()) && (currentID != 6))
            processExitFoggyZone(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 6) {
            processEmptyMessage(getNSet);
            currentID = 6;
        }
    }//end method


    //*************************************************************************
    private void processSecurityDistanceMessageWithVehicularObstacle(GetNSet<E> getNSet) {
        value[0] = "Alert";
        value[1] = "8";
        value[2] = "Danger";
        value[3] = "Slow down. Vehicle ahead.";
        // + "" Possible collision in "
        // + ((int) collisionTime) + " seconds.";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Danger";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processSecurityDistanceMessageWithVehicularObstacle", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processSecurityDistanceMessageWithVehicularObstacle", value)));
        currentID = 7;

    }//end method

    //*****************************************************
    private void detectVehicularObstacleSecurityDistance(GetNSet<E> getNSet){
        // Alerte obstacle vehicule
        if ((EventCondition.isVehicularObstacleDistanceDangerous()) && (currentID != 7))
            processSecurityDistanceMessageWithVehicularObstacle(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 7) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }//end if
    }//end method


    //*************************************************************
    private void processPedestrianSecurityDistanceMessage(GetNSet<E> getNSet) {
        value[0] = "Alert";
        value[1] = "8";
        value[2] = "Danger";
        value[3] = "Slow down. Pedestrian ahead.";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Danger";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processPedestrianSecurityDistanceMessage", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processPedestrianSecurityDistanceMessage", value)));
        currentID = 8;
    }//end method


    //*****************************************************
    private void detectPedestrianSecurityDistance(GetNSet<E> getNSet) {
        if ((EventCondition.isPedestrianObstaclePresent()) && (currentID != 8))
            processPedestrianSecurityDistanceMessage(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 8) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }//end if
    }//end method


    //********************************************************
    private void processRunningPedestrianLaneMessage(GetNSet<E> getNSet) {
        value[0] = "Alert";
        value[1] = "8";
        value[2] = "Behavior";
        value[3] = "You just run a pedestrian lane.";
        value[4] = "FALSE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Behavior";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        Historique.nombre_Oubli_arreter_au_PassagePieton++;
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processRunningPedestrianLaneMessage", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processRunningPedestrianLaneMessage", value)));
        currentID = 9;
    }//end method


    //****************************************************
    private void detectStoppingOnPedestrianLane(GetNSet<E> getNSet) {

        if ((EventCondition.isRunningOnPedestrianLane()) && (currentID != 9))
            processRunningPedestrianLaneMessage(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 9) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }
    }//end method


    //*****************************************************
    private void processDriverDisturbanceMessage(GetNSet<E> getNSet) {
        // Danger! Driver not focused on driving
        value[1] = "Alerte";
        value[2] = "9";
        value[3] = "You are tired. Do you want to activate energizing mode?";
        value[4] = "TRUE";
        value[5] = Integer.toString(--GlobalData.drivingScore);
        value[6] = "Ability";
        for (int i = 7; i < 11; i++)
            value[i] = "FALSE";
        value[11] = "ON";
        sendMessageToServer(getNSet);
        //Log.i(Util.TAG, Util.writeLog("processDriverDisturbanceMessage", value));
        EventBus.getDefault().post(new MessageEvent(Util.writeLog("processDriverDisturbanceMessage", value)));
        currentID = 10;
    }//end method


    //*********************************************
    private void detectDriverDisturbance(GetNSet<E> getNSet) {
        if ((EventCondition.isDriverTired()) && (currentID != 10))
            processDriverDisturbanceMessage(getNSet);
        //to avoid repetition of the same message while on the same condition
        if (currentID == 10) {
            processEmptyMessage(getNSet);
            currentID = 0;
        }
    }//end method


    private void processIMATIASignal(GetNSet<E> getNSet){
        String incitation = getSignals(getNSet, "CASA.IMATIA.RemoveFoot.Incitation");
        Bundle inputIncitation = new Bundle();
        inputIncitation.putString("CASA.UVSQ.RemoveFoot.Incitation", incitation);
        setSignals(getNSet, inputIncitation);

        String explicitSpeedLimit = getSignals(getNSet, "CASA.IMATIA.RemoveFoot.ExplicitSpeedLimit");
        Bundle inputExplicitSpeedLimit = new Bundle();
        inputExplicitSpeedLimit.putString("CASA.UVSQ.RemoveFoot.ExplicitSpeedLimit", explicitSpeedLimit);
        setSignals(getNSet, inputExplicitSpeedLimit);
    }

    //*********************************
    private void selectEvent(GetNSet<E> getNSet) {
        //changes in speed or speed limit triggers detection of over-speeding
        if ((GlobalData.changed_VehicleSpeed) || (GlobalData.changed_SpeedLimit))
            detectOverspeeding(getNSet);
        //changes in intersection distance and intersection type will trigger detection of Stop signal
        if ((GlobalData.changed_IntersectionDistance) || (GlobalData.changed_IntersectionType))
            detectStop(getNSet);
        //changes in direction triggers checking if driver stops on Stop signal
        if ((GlobalData.changed_IntersectionDistance) || (GlobalData.changed_VehicleSpeed)) {
            detectStoppingAtStop(getNSet);
        }
//        else{
//            if(GlobalData.changed_IntersectionDirectionFromOktal)
//                detectTurningDirectionIndicator(getNSet);
//        }


        //changes in direction triggers checking if the driver has put on the direction signal indicator
        if ((GlobalData.changed_IntersectionDirectionFromOktal) || (currentTime - initTime < 2000))
            detectTurningDirectionIndicator(getNSet);

        //changes in fog distance triggers detection of fog
        if (GlobalData.changed_FogVisibilityDistance) {
            detectFoggyZone(getNSet);
            detectVehicleSpeedInFoggyZone(getNSet);
            detectExitFoggyZone(getNSet);
        }//end if
        //changes in obstacle type triggers detection of security distance with the obstacle
        //if (GlobalData.changed_ObstacleType) {
        if(GlobalData.changed_ObstacleTimeToCollision) {
            detectVehicularObstacleSecurityDistance(getNSet);
            detectPedestrianSecurityDistance(getNSet);
            //detectStoppingOnPedestrianLane(); //to be checked
        }//end if
        //changes in driver disturbance indicator triggers sending message to tired driver
        if (GlobalData.changed_DriverDisturbance)
            detectDriverDisturbance(getNSet);


        processIMATIASignal(getNSet);
    }//end method


    /*
	The method to run UVSQ Algorithm
	 */
    public void run(){
        GetNSet<E> getNSet = new GetNSet<>(e);
        detectParameterChange = new DetectParameterChange<>(getNSet);

        while (!stopFlag) {
            initialState(getNSet);
            if (engineStatusIsOn(getNSet)) {
                startSimulation(getNSet);
                getData.getCurrentData(getNSet);
            }//end if

            while (engineStatusIsOn(getNSet))	{
                getData.getNextData(getNSet);
                detectParameterChange.detectDataChange();
                selectEvent(getNSet);
                getData.updateAllData();
                GlobalData.delay();
            }//end while
        }//end while (true)
    }
}
