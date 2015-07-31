package com.uvsq;

/**
 * Created by etudiant on 28/07/2015.
 */
public class Util {

    public static final String TAG = "UVSQTest";

    public static String writeLog(String functionName, String[] values){
        StringBuilder result = new StringBuilder();
        for(String str: values){
            result.append(str).append(" ");
        }
        return functionName+": "+result.toString();
    }


    public static String getCurrentMethodName(){
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }
}
