package com.uvsq.CASA;

import java.util.StringTokenizer;

public class Vector {
	private double x;
	private double y;
	private double z;

	public Vector() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}
	
	public Vector(double xx, double yy, double zz) {
	    x = xx;
	    y = yy;
	    z = zz;
	}
	
	public Vector(Vector parVector){
		x = parVector.x;
		y = parVector.y;
		z = parVector.z;
	}
	
	public Vector extractVector(String vectorString){
		String[] data = new String[3];
		int index = 0;
		StringTokenizer st = new StringTokenizer(vectorString, "x");
	     while (st.hasMoreTokens()) {
	         data[index] = st.nextToken();
	         System.out.println(data[index]);
	         index++;
	     }
	     Vector myVector = new Vector();
	     myVector.x = Double.valueOf(data[0]);
	     myVector.y = Double.valueOf(data[1]);
	     myVector.z = Double.valueOf(data[2]);
	     return myVector;
	}
	
	public String toString() {
		return (" x = " + this.x + "\n" + " y = " + this.y + "\n" + " z = " + this.z + "\n"); 
	}
	
	
	public double getVectorXCoordinate() {
		return x;
	}
	
	public double getVectorYCoordinate() {
		return y;
	}
	
	public double getVectorZCoordinate() {
		return z;
	}

}//end class
