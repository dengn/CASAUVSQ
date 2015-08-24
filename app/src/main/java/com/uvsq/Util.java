package com.uvsq;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by etudiant on 28/07/2015.
 */
public class Util {

    public static final String TAG = "UVSQTest";

    public static String writeLog(String functionName, String values){
        StringBuilder result = new StringBuilder();
//        for(String str: values){
//            result.append(str).append(" ");
//        }

        Date cDate = new Date();
        String fDate = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(cDate);

        result.append(fDate+" ");
        result.append(values+"\n");
        //return functionName+": "+result.toString();
        return result.toString();
    }


    public static String getCurrentMethodName(){
        return Thread.currentThread().getStackTrace()[1].getMethodName();
    }
}
