package com.uvsq.CASA;

import android.os.Bundle;

import com.uvsq.connect2datanex.GetNSet;

import java.util.HashMap;

public class GetData<E> {

    private String getSignals(GetNSet<E> getNSet, String signal) {
        Bundle input = new Bundle();
        input.putString(signal, "");
        Bundle bundle = getNSet.get(input);
        String value1 = bundle.getString(signal);
        return value1;
    }

    private void setSignals(GetNSet<E> getNSet, Bundle bundle) {
        getNSet.set(bundle);
    }

    //****************************************
    public String getEngineStatus(GetNSet<E> getNSet) {
        //************************************
        //This method obtains the status of the car engine (on or off) from Nextyad
        String status = "";
        String signal = "CASA.Nexyad.EngineStatus";
        // String signal = "CASA.Oktal.EngineStatus";
        try {

            status = getSignals(getNSet, signal);
            GlobalData.engineStatus = status.equalsIgnoreCase("undefined") ? "ON" : status;
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        //System.out.println("Engine status: " + GlobalData.engineStatus);
        return GlobalData.engineStatus;
    }// end method


    // ***************************************
    public double getVehicleSpeed(GetNSet<E> getNSet) {
        // ***********************************
        //This method obtains the speed of the vehicle from Nextyad
        double speed = 0.0;
        try {
            String signal = "CASA.Nexyad.VehicleSpeed";
            // String signal = "CASA.Oktal.VehicleSpeed";
            speed = Double.parseDouble(getSignals(getNSet, signal));
            speed = (speed < 0.0) ? GlobalData.previousSpeed : speed;
            GlobalData.presentSpeed = speed;
            ContextVehicule.setVitesse(speed);
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        } //end catch
        return speed;
    }// end method


    // *************************************
    public double getSpeedLimit(GetNSet<E> getNSet) {
        // *************************************
        //This method obtains the speed limit in a certain section of a road
        double speedLimit = 0.0;
        try {
            String signal = "CASA.Nexyad.SpeedLimit";
            // String signal = "CASA.Oktal.SpeedLimit";
            speedLimit = Double.parseDouble(getSignals(getNSet, signal));
            // Double.valueOf(http.sendGet2(signal1));
            speedLimit = (speedLimit < 0) ? GlobalData.previousSpeedLimit : speedLimit;
            GlobalData.presentSpeedLimit = speedLimit;
            // System.out.println("Speed limit = " + speedLimit);
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return speedLimit;
    }// end method


    //***********************************************
    public double getIntersectionDistance(GetNSet<E> getNSet) {
        // ******************************************
        //This method gets the distance of the vehicle from the next intersection
        double distance = 0.0;
        String signal = "CASA.Nexyad.NextIntersectionDistance";
        try {
            distance = Double.parseDouble(getSignals(getNSet, signal));
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return distance;
    }// end method


    // *****************************************************
    public double getIntersectionSignalDistance(GetNSet<E> getNSet) {
        // *****************************************************
        //This method gets the distance of the vehicle to the signal sign
        //in the intersection or corner of the road
        double distance = 0.0;
        String signal = "CASA.Nexyad.NextIntersectionSignalDistance";
        try {
            distance = Double.parseDouble(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return distance;
    }// end method


    // ****************************************
    public int getIntersectionType(GetNSet<E> getNSet) {
        // ****************************************
        //This method obtains the type of road intersection
        int intersectionType = -1;

        String signal = "CASA.Nexyad.NextIntersectionType";
        try {
            intersectionType = Integer.valueOf(getSignals(getNSet,signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return intersectionType;
    }// end method


    // *****************************************
    public boolean getDirectionLeft(GetNSet<E> getNSet) {
        // *****************************************
        boolean directionLeft = false;
        String signal = "CASA.Nexyad.IntersectionDirectionLeft";
        try {
            directionLeft = Boolean.valueOf(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return directionLeft;
    }// end method


    // ******************************************
    public boolean getDirectionRight(GetNSet<E> getNSet) {
        // ******************************************
        boolean directionRight = false;
        String signal = "CASA.Nexyad.IntersectionDirectionRight";
        try {
            directionRight = Boolean.valueOf(getSignals(getNSet, signal));
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return directionRight;
    }// end method


    // ******************************************
    public boolean getDirectionAhead(GetNSet<E> getNSet) {
        // ******************************************
        boolean directionAhead = false;
        String signal = "CASA.Nexyad.IntersectionDirectionAhead";
        try {
            directionAhead = Boolean.valueOf(getSignals(getNSet, signal));
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return directionAhead;
    }// end method


    // ***************************************
    public int getDirectionSignal(GetNSet<E> getNSet) {
        // ***************************************
        int directionClignotant = -2;
        // returns -1: right / 0: none / 1: left

        String signal = "CASA.Nexyad.Indicators";
        try {
            directionClignotant = Integer.valueOf(getSignals(getNSet, signal));
            // System.out.println("Direction signal: " + directionClignotant);

            //Log.i(Util.TAG, "getDirectionSignal: "+"directionClignotant: "+directionClignotant);
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return directionClignotant;
    }// end method


    // ************************************************
    public double getFogVisibilityDistance(GetNSet<E> getNSet) {
        // ************************************************
        double fogDistance = -1;

        // System.out.println("Testing - Obtaining distance of fog. If distance is 5000 meters, no fog");
        String signal = "CASA.Nexyad.MeteoFogVisibilityDistance";
        try {
            fogDistance = Double.parseDouble(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return fogDistance;
    }// end method


    // ********************************************
    public String getLampsRearFogStatus(GetNSet<E> getNSet) {
        // ********************************************
        String lampsRearFogStatus = "";


        String signal = "CASA.Nexyad.LampsRearFog";
        try {
            lampsRearFogStatus = getSignals(getNSet, signal);
            lampsRearFogStatus = lampsRearFogStatus.equalsIgnoreCase("UNDEFINED") ? "ON" : lampsRearFogStatus;
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return lampsRearFogStatus;
    }// end method

    // *************************************
    public int getLightingStatus(GetNSet<E> getNSet) {
        // *************************************
        int lightingStatus = -1;

        String signal = "CASA.Nexyad.Lighting";
        try {
            lightingStatus = Integer.valueOf(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return lightingStatus;
    }// end method

    // ************************************
    public int getObstacleType(GetNSet<E> getNSet) {
        // ************************************
        int obstacleType = 0;

        String signal = "CASA.Nexyad.ObstacleType";
        try {
            obstacleType = Integer.valueOf(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return obstacleType;
    }// end method

    // *******************************************
    public double getObstacleDistance(GetNSet<E> getNSet) {
        // *******************************************
        double obstacleDistance = 0.0;

        String signal = "CASA.Nexyad.ObstacleDistance";
        try {
            obstacleDistance = Double.parseDouble(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return obstacleDistance;
    }// end method

    // ****************************************
    public double getObstacleSpeed(GetNSet<E> getNSet) {
        // ****************************************
        double obstacleSpeed = 0.0;

        String signal = "CASA.Nexyad.ObstacleSpeed";
        try {
            obstacleSpeed = Double.parseDouble(getSignals(getNSet, signal));
            obstacleSpeed = (obstacleSpeed < 0.0) ? 0.0 : obstacleSpeed;
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return obstacleSpeed;
    }// end method


    // **************************************************
    public double getObstacleTimeToCollision(GetNSet<E> getNSet) {
        // **************************************************
        double obstacleTimeToCollision = -1.0;

        String signal = "CASA.Nexyad.ObstacleTimeToCollision";
        try {
            obstacleTimeToCollision = Double.parseDouble(getSignals(getNSet, signal));
        }//end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }//end catch
        return obstacleTimeToCollision;
    }// end method

    // **************************************
    public int getLaneNumber(GetNSet<E> getNSet) {
        // **************************************
        int laneNumber = -1;

        try {
            String signal = "CASA.Nexyad.LaneNumber";
            laneNumber = Integer.valueOf(getSignals(getNSet, signal));
            GlobalData.presentLane = laneNumber;
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
        return laneNumber;
    }// end method

    // *********************************************
    public boolean getLaneChangedStatus(GetNSet<E> getNSet) {
        // *********************************************
        boolean laneChanged = false;

        try {
            String signal = "CASA.Nexyad.LaneChanged";
            laneChanged = Boolean.valueOf(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
        return laneChanged;
    }// end method

    // *********************************************
    public int getDriverDisturbance(GetNSet<E> getNSet) {
        // *********************************************
        int driverDisturbance = 0;

        try {
            String signal = "CASA.Nexyad.DriverDisturbance";
            driverDisturbance = Integer.valueOf(getSignals(getNSet, signal));
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
        return driverDisturbance;
    }// end method

    // ******************************************************
    public int getIntersectionDirectionFromOktal(GetNSet<E> getNSet) {
        // ******************************************************


        int direction = 0;

        try {

            String signal = "CASA.Nexyad.IntersectionDirection";
            direction = Integer.valueOf(getSignals(getNSet, signal));

            //Log.i(Util.TAG, "getIntersectionDirectionFromOktal: "+direction);
        } //end try
        catch (Exception e) {
            System.out.println(e.getMessage());
        }// end catch
        return direction;
    }// end method

    //************************************
    public void getCurrentData(GetNSet<E> getNSet) {
        //This method gets all parameters
        GlobalData.old_VehicleSpeed = getVehicleSpeed(getNSet);
        GlobalData.old_SpeedLimit = getSpeedLimit(getNSet);
        GlobalData.old_IntersectionDistance = getIntersectionDistance(getNSet);
        GlobalData.old_IntersectionSignalDistance = getIntersectionSignalDistance(getNSet);
        GlobalData.old_IntersectionType = getIntersectionType(getNSet);
        GlobalData.old_DirectionSignal = getDirectionSignal(getNSet);
        GlobalData.old_IntersectionDirectionFromOktal = getIntersectionDirectionFromOktal(getNSet);
        GlobalData.old_FogVisibilityDistance = getFogVisibilityDistance(getNSet);
        GlobalData.old_ObstacleType = getObstacleType(getNSet);
        GlobalData.old_ObstacleSpeed = getObstacleSpeed(getNSet);
        GlobalData.old_ObstacleDistance = getObstacleDistance(getNSet);
        GlobalData.old_ObstacleTimeToCollision = getObstacleTimeToCollision(getNSet);
        GlobalData.old_DriverDisturbance = getDriverDisturbance(getNSet);

    }//end method

    //********************************
    public  void getNextData(GetNSet<E> getNSet) {
        //This method gets all parameters
        GlobalData.new_VehicleSpeed = getVehicleSpeed(getNSet);
        GlobalData.new_SpeedLimit = getSpeedLimit(getNSet);
        GlobalData.new_IntersectionDistance = getIntersectionDistance(getNSet);
        GlobalData.new_IntersectionSignalDistance = getIntersectionSignalDistance(getNSet);
        GlobalData.new_IntersectionType = getIntersectionType(getNSet);
        GlobalData.new_DirectionSignal = getDirectionSignal(getNSet);
        GlobalData.new_IntersectionDirectionFromOktal = getIntersectionDirectionFromOktal(getNSet);
        GlobalData.new_FogVisibilityDistance = getFogVisibilityDistance(getNSet);
        GlobalData.new_ObstacleType = getObstacleType(getNSet);
        GlobalData.new_ObstacleSpeed = getObstacleSpeed(getNSet);
        GlobalData.new_ObstacleDistance = getObstacleDistance(getNSet);
        GlobalData.new_ObstacleTimeToCollision = getObstacleTimeToCollision(getNSet);
        GlobalData.new_DriverDisturbance = getDriverDisturbance(getNSet);

    }//end method


    //**********************************
    public  void updateAllData() {
        GlobalData.old_VehicleSpeed = GlobalData.new_VehicleSpeed;
        GlobalData.old_SpeedLimit = GlobalData.new_SpeedLimit;
        GlobalData.old_IntersectionDistance = GlobalData.new_IntersectionDistance;
        GlobalData.old_IntersectionSignalDistance = GlobalData.new_IntersectionSignalDistance;
        GlobalData.old_IntersectionType = GlobalData.new_IntersectionType;
        GlobalData.old_DirectionSignal = GlobalData.new_DirectionSignal;
        GlobalData.old_IntersectionDirectionFromOktal = GlobalData.new_IntersectionDirectionFromOktal;
        GlobalData.old_FogVisibilityDistance = GlobalData.new_FogVisibilityDistance;
        GlobalData.old_ObstacleType = GlobalData.new_ObstacleType;
        GlobalData.old_ObstacleSpeed = GlobalData.new_ObstacleSpeed;
        GlobalData.old_ObstacleDistance = GlobalData.new_ObstacleDistance;
        GlobalData.old_ObstacleTimeToCollision = GlobalData.new_ObstacleTimeToCollision;
        GlobalData.old_DriverDisturbance = GlobalData.new_DriverDisturbance;

    }//end method


}//end class
