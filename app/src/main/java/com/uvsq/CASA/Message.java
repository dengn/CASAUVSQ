package com.uvsq.CASA;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by etudiant on 31/08/2015.
 */
public class Message implements Comparable<Message>{

    private String[] value = new String[12];
	/*
	 *  index no. and parameter association
	 *  "0- CASA.UVSQ.Message.level",
		"1 - CASA.UVSQ.Message.levelForce",
		"2 - CASA.UVSQ.Message.category",
		"3 - CASA.UVSQ.Message.value",
		"4 - CASA.UVSQ.Message.question",
		"5 - CASA.UVSQ.DrivingScore",
		"6 - CASA.UVSQ.Image",
		"7 - CASA.UVSQ.ActiveLampsRearFog",
		"8 - CASA.UVSQ.ActiveLampsRearFog",
		"9 - CASA.UVSQ.ActiveAirConditioning",
		"10 - CASA.UVSQ.ActiveAirRecycling",
		"11 - CASA.UVSQ.EngineStatus" };
	 */


    //****************
    public Message() {
        for (int i = 0; i < value.length; i++)
            value[i] = "";
    }; //constructor

    public Message(String[] message) {
        value = message;
    }; //constructor

    public void setMessage(String[] message){
        value = message;
    }

    public String[] getMessage(){
        return value;
    }
    //*****************************
    public void setEmptyMessage() {
        for (int i = 0; i < value.length; i++)
            value[i] = "";
    }//end method

    //***********************************************
    public void setMessageLevel(String messageLevel){
        value[0] = messageLevel;
    }//end method

    //********************************
    public String getMessageLevel() {
        return value[0];
    }//end method

    //*********************************************************
    public void setMessageLevelForce(String messageLevelForce){
        value[1] = messageLevelForce;
    }//end method

    //************************************
    public String getMessageLevelForce() {
        return value[1];
    }//end method

    //******************************************************
    public void setMessageCategory(String messageCategory){
        value[2] = messageCategory;
    }//end method

    //**********************************
    public String getMessageCategory() {
        return value[2];
    }//end method


    //******************************************************
    public void setMessageValue(String messageValue){
        value[3] = messageValue;
    }//end method

    //**********************************
    public String getMessageValue() {
        return value[3];
    }//end method

    //******************************************************
    public void setMessageQuestion(String messageQuestion){
        value[4] = messageQuestion;
    }//end method

    //**********************************
    public String getMessageQuestion() {
        return value[4];
    }//end method

    //************************************************
    public void setDrivingScore(String drivingScore){
        value[5] = drivingScore;
    }//end method

    //********************************
    public String getDrivingScore() {
        return value[5];
    }//end method

    //******************************************************
    public void setMessageImage(String messageImage){
        value[6] = messageImage;
    }//end method

    //**********************************
    public String getMessageImage() {
        return value[6];
    }//end method

    //*****************************************************************
    public void setActiveLampsRearFog(String activeLampsRearFogStatus){
        value[7] = activeLampsRearFogStatus;
    }//end method

    //*******************************************
    public String getActiveLampsRearFogStatus() {
        return value[7];
    }//end method

    //*****************************************************************
    public void setActiveLampsRearFog2(String activeLampsRearFogStatus){
        value[8] = activeLampsRearFogStatus;
    }//end method

    //********************************************
    public String getActiveLampsRearFog2Status() {
        return value[8];
    }//end method

    //***********************************************************************
    public void setActiveAirConditioning(String activeAirConditioningStatus){
        value[9] = activeAirConditioningStatus;
    }//end method

    //**********************************************
    public String getActiveAirConditioningStatus() {
        return value[9];
    }//end method


    //*****************************************************************
    public void setActiveAirRecycling(String activeAirRecyclingStatus){
        value[10] = activeAirRecyclingStatus;
    }//end method

    //********************************************
    public String getActiveAirRecyclingStatus() {
        return value[10];
    }//end method

    //************************************************
    public void setEngineStatus(String engineStatus){
        value[11] = engineStatus;
    }//end method

    //********************************
    public String getEngineStatus() {
        return value[11];
    }//end method

    //**************************
    public void printMessage() {
        System.out.println("CASA.UVSQ.Message.level: " + getMessageLevel());
        System.out.println("CASA.UVSQ.Message.levelForce: " + getMessageLevelForce());
        System.out.println("CASA.UVSQ.Message.category: " + getMessageCategory());
        System.out.println("CASA.UVSQ.Message.value: " + getMessageValue());
        System.out.println("CASA.UVSQ.Message.question: " + getMessageQuestion());
        System.out.println("CASA.UVSQ.DrivingScore: " + getDrivingScore());
        System.out.println("CASA.UVSQ.Image: " + getMessageImage());
        System.out.println("CASA.UVSQ.ActiveLampsRearFog: " + getActiveLampsRearFogStatus());
        System.out.println("CASA.UVSQ.ActiveLampsRearFog: " + getActiveLampsRearFog2Status());
        System.out.println("CASA.UVSQ.ActiveAirConditioning: " + getActiveAirConditioningStatus());
        System.out.println("CASA.UVSQ.ActiveAirRecycling: " + getActiveAirRecyclingStatus());
        System.out.println("CASA.UVSQ.EngineStatus: " + getEngineStatus());

    }//end method

    @Override
    public String toString(){
        return Arrays.toString(value);
    }

    @Override
    public int compareTo(Message another) {
        int localLevel = Integer.parseInt(value[1]);
        int outsideLevel = Integer.parseInt(another.getMessageLevelForce());
        return outsideLevel-localLevel;
    }






}//end class
