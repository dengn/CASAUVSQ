package com.uvsq.CASA;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;


public class JSONData {
    ContextVehicule contextVehicule = new ContextVehicule();
    ContextFusion contextFusion = new ContextFusion();

    //------------------------------------
    public void createDataForTestSimulation() {
        JSONObject obj = new JSONObject();
        //generate random speed here
        Random myGenerator = new Random();
        double speed = myGenerator.nextInt(40) + 30;
        double[] limitations = {50.0, 70.0, 110.0};
        Random myGenerator2 = new Random();
        int indice = myGenerator2.nextInt(3);
        double speedLimit = limitations[indice];
        //generate random speed limit here
        try {
            obj.put("speed", speed);
            //obj.put("speedLimit", new Double(50.0));
            obj.put("speedLimit", new Double(speedLimit));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GlobalData.objEvent = obj;
    }

    //--------------------------------------
    public void generateOutputForTestSimulation() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("type de message", "Notification");
            if ((contextVehicule.vitesseExcessive) || (contextVehicule.vitesseDangereuse)) {
                obj.put("categorie", "Danger");
                obj.put("message", "Slow down. Speed limit is " + contextVehicule.getLimitationVitesse());
            } else {
                obj.put("categorie", "Pas de danger");
                obj.put("message", "Enjoy your trip");
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        GlobalData.outputEvent = obj;
    }


    //-----------------------------------
    public void createDataForEvent1() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("speed", new Double(48.5));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent1 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent1() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Smiling Face");
            obj.put("event", "Normal");
            obj.put("message", "Have a good trip!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent1 = obj;
    }

    //-----------------------------------
    public void createDataForEvent2() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("distanceFromStopSign", new Double(200.0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent2 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent2() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Stop Sign");
            obj.put("event", "Inform the driver");
            obj.put("message", "Get ready to stop in " + contextFusion.getDistance_avec_Stop() + " km");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent2 = obj;
    }

    //-----------------------------------
    public void createDataForEvent3() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("distanceFromIntersection", new Double(150.0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent3 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent3() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Intersection");
            obj.put("event", "Inform the driver");
            obj.put("message", "Intersection in " + contextFusion.getDistance_avec_Stop() + " km");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent3 = obj;
    }

    //-----------------------------------
    public void createDataForEvent4() {
        JSONObject obj = new JSONObject();
        int i = (int) Math.round(Math.random());
        try {
            obj.put("stopOnStopSign", new Integer(i));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent4 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent4() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Stop Sign");
            obj.put("event", "Information/Warning");
            obj.put("message", "Speed at Stop Sign is " + contextVehicule.getVitesse() + " km");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent4 = obj;
    }

    //-----------------------------------
    public void createDataForEvent5() {
        JSONObject obj = new JSONObject();
        try {
            int i = (int) Math.round(Math.random());
            obj.put("direction", new Integer(i));
            int j = (int) Math.round(Math.random());
            obj.put("signalOn", new Integer(j));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent5 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent5() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Intersection and Direction");
            obj.put("event", "Information/Warning");
            obj.put("message", "Direction Signal is " + ContextVehicule.getEnvironnement_interne_clignotants());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent5 = obj;
    }

    //-----------------------------------
    public void createDataForEvent6() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("speed", new Double(50.0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent6 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent6() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Smiling Face");
            obj.put("event", "Resume normal driving");
            obj.put("message", "Enjoy your trip!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent6 = obj;
    }

    //-----------------------------------
    public void createDataForEvent7() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("speed", new Double(50.0));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.objEvent6 = obj;
    }

    //--------------------------------------
    public void generateOutputForEvent7() {
        JSONObject obj = new JSONObject();
        try {
            obj.put("pictogram", "Smiling Face");
            obj.put("event", "Resume normal driving");
            obj.put("message", "Enjoy your trip!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        GlobalData.outputEvent6 = obj;
    }


}
