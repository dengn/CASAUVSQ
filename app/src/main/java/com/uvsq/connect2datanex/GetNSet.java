package com.uvsq.connect2datanex;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import android.os.Bundle;
import android.util.Log;

/**
 * <b>Don't change this class</b><br>
 * 
 * Class to get and set signals from DataNex service
 */

public class GetNSet<E> {

	private Method[] methods;

	private Object e;

	/**
	 * Constructor with IRemoteInterface object
	 * @param e
	 */
	
	public GetNSet(Object e) {
		this.e = e;
		methods = e.getClass().getMethods();
	}


	public Bundle get(Map<String, String> signals) {
		Bundle signalsBundle = new Bundle();
		for (Method method : methods) {
			if (method.getName().equals("get")) {
				try {
					for (String key : signals.keySet()) {
						signalsBundle.putString(key, signals.get(key));
					}
					signalsBundle = (Bundle) method.invoke(e, signalsBundle);
					for (String key : signals.keySet()) {
						signals.put(key, signalsBundle.get(key).toString());
					}
				} catch (IllegalAccessException e) {
					Log.e("[Nexyad]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				} catch (IllegalArgumentException e) {
					Log.e("[Nexyad]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				} catch (InvocationTargetException e) {
					Log.e("[Nexyad]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				}
				return signalsBundle;
			}
		}
		return new Bundle();
	}


	/**
	 * Set Method
	 *
	 * @param signals
	 */
	public void set(Map<String, String> signals) {
		for (Method method : methods) {
			if (method.getName().equals("set")) {
				execute(method, signals);
				return;
			}
		}
	}

	private void execute(Method method, Map<String, String> signals) {
		Bundle signalsBundle = new Bundle();
		try {
			for (String key : signals.keySet()) {
				signalsBundle.putString(key, signals.get(key));
			}
			method.invoke(e, signalsBundle);
			for (String key : signals.keySet()) {
				signals.put(key, signalsBundle.get(key).toString());
			}
		} catch (IllegalAccessException e) {
			Log.e("[Nexyad]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.e("[Nexyad]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		} catch (InvocationTargetException e) {
			Log.e("[Nexyad]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		}
	}

}

