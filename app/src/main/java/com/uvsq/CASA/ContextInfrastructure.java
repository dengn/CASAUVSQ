package com.uvsq.CASA;

public class ContextInfrastructure {
	final String[] INFORMATION_SUR_LA_CIRCULATION = {"Fluide", "Traffic moyen", "Embouteillage", "Accident"};
	String information_sur_la_circulation;
	
	public ContextInfrastructure() {
		information_sur_la_circulation = INFORMATION_SUR_LA_CIRCULATION[0]; 
	}
	
	public String toString(){
		String result = "";
		result += "Contexte infrastructure: " + "\n";
		result += "Information sur la circulation: " + information_sur_la_circulation + "\n";
		return result;
	}
	
    public void setInformation_sur_la_circulation(String parInformation_sur_la_circulation) throws ContextException {
    	if ((parInformation_sur_la_circulation != INFORMATION_SUR_LA_CIRCULATION[0]) && (parInformation_sur_la_circulation != INFORMATION_SUR_LA_CIRCULATION[1]) &&
    	   (parInformation_sur_la_circulation != INFORMATION_SUR_LA_CIRCULATION[2]) && (parInformation_sur_la_circulation != INFORMATION_SUR_LA_CIRCULATION[3]))
    		throw new ContextException("Information sur la circulation: " + INFORMATION_SUR_LA_CIRCULATION[0] + " or " + INFORMATION_SUR_LA_CIRCULATION[1] +
    				" or " + INFORMATION_SUR_LA_CIRCULATION[2] + " or " + INFORMATION_SUR_LA_CIRCULATION[3]);
    	information_sur_la_circulation = parInformation_sur_la_circulation;
    }
	
	public String getInformation_sur_la_circulation() {
		return information_sur_la_circulation;
	}
}
