package com.uvsq.connect2datanex;

import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

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


	public Bundle get(Bundle signals) {

		for (Method method : methods) {
			if (method.getName().equals("get")) {
				try {
					signals = (Bundle) method.invoke(e, new Object[]{signals});
				} catch (IllegalAccessException e) {
					Log.e("[UVSQ]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				} catch (IllegalArgumentException e) {
					Log.e("[UVSQ]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				} catch (InvocationTargetException e) {
					Log.e("[UVSQ]: ",
							Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
				}
				return signals;
			}
		}
		return new Bundle();
	}


	/**
	 * Set Method
	 *
	 * @param signals
	 */
	public void set(Bundle signals) {
		for (Method method : methods) {
			if (method.getName().equals("set")) {
				execute(method, signals);
				return;
			}
		}
	}

	private void execute(Method method, Bundle signals) {

		try {
			method.invoke(e, new Object[]{signals});
		} catch (IllegalAccessException e) {
			Log.e("[UVSQ]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		} catch (IllegalArgumentException e) {
			Log.e("[UVSQ]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		} catch (InvocationTargetException e) {
			Log.e("[UVSQ]: ",
					Arrays.toString(e.getStackTrace()) + "\n" + e.getMessage());
		}
	}

}

