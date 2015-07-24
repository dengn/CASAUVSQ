package com.uvsq.CASA;

public class ContextVehicule {
	//Les constantes sur la position du v�hicule
	final String[] POSITION_DU_VEHICULE = {"Distance centre voie", "Distance bord de voie droite", 
			                               "Distance bord de voie gauche", "Ecart direction axe voie et axe vehicule"};
	final String[] INDICATEUR_ACTION_VEHICULE = {"Probabilit� de sortie de voie", "Boul�en changement de voie",
			                              "Evaluation de la tenue de cap (M�sure de l'oscillation autour du centre de la voie"};
	
	//Les constantes sur l'acc�l�ration du v�hicule
	final String[] ACCELERATION_LONGITUDINALE = {"Nulle", "Faible", "Moyenne", "Forte"};
	final String[] FREINAGE_LONGITUDINAL = {"Nulle", "Faible", "Moyenne", "Forte"};
	final String[] ACCELERATION_TRANSVERSALE = {"Nulle", "Faible", "Moyenne", "Forte"};
	
	//Les constantes sur la vitesse du v�hicule
	final String[] VITESSE_DU_VEHICULE = {"Adapt�e", "Excessive", "Dangereuse"};
	final double MARGE_VITESSE = 0.20;
	final String[] VARIATION_VITESSE_DU_VEHICULE = {"Fixe", "Diminution", "Augmentation"};
	
	//Les constantes sur l'action du conducteur
	final String[] ACTION_CONDUCTEUR_SUR_LE_VOLANT = {"Fixe", "Rotation horaire lente", "Rotation horaire rapide", 
			                                          "Rotation anti-horaire lente", "Rotation anti-horaire rapide"};
	final String[] ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR = {"Moyenne", "Faible", "Forte"};
	final String[] ACTION_CONDUCTEUR_VARIATION_PRESSION_ACCELERATEUR = {"Diminution", "Augmentation"};
	final String[] ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE = {"Moyenne", "Faible", "Forte"}; 
	final String[] ACTION_CONDUCTEUR_VARIATION_PRESSION_FREINAGE = {"Diminution", "Augmentation"}; 
	
	//Les constantes sur l'environnement interne
	final String[] ENVIRONNEMENT_INTERNE_CLIMATISATION = {"Allum�e", "Non allum�e"};
	final String[] ENVIRONNEMENT_INTERNE_MUSIQUE = {"Allum�e", "Non allum�e"};
	final String[] ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE = {"Attach�e", "Non attach�e"};
	static final String[] ENVIRONNEMENT_INTERNE_CLIGNOTANTS = {"Aucun", "Droit", "Gauche", "Warning"};
	final String[] ENVIRONNEMENT_INTERNE_ECLAIRAGE = {"Aucun", "Position", "Croisement", "Route", "AB avant", "AB arriere"};
	
	//Les attributs sur la position de la voiture
	String position_du_vehicule;
	String indicateur_action_vehicule;
	
	//Les attributs sur l'acc�leration du v�hicule
	String acceleration_longitudinale;
	String freinage_longitudinal;
	String acceleration_transversale;
	
	//Les attributs sur la vitesse du v�hicule
	public static double vitesse = 0.0;
	public static double limitationVitesse;
	public static boolean vitesseAdaptative = false;
	public static boolean vitesseExcessive = false;
	public static boolean vitesseDangereuse = false;
	String vitesse_du_vehicule;
	String variation_vitesse_du_vehicule;
	
	
	//Les attributs sur l'action du conducteur
	String action_conducteur_sur_le_volant;
	String action_conducteur_niveau_pression_accelerateur;
	String action_conducteur_variation_pression_accelerateur;
	String action_conducteur_niveau_pression_freinage;
	String action_conducteur_variation_pression_freinage;
	
	//Les attributs sur l'environnement interne
	public static String environnement_interne_climatisation;
	//Boolean environnement_interne_climatisation; //(un autre option pour la climatisation interne)
	public static String  environnement_interne_musique;
	//Boolean environnement_interne_musique; //(un autre option pour la musique)
	public static String environnement_interne_ceinture_de_securite;
	//Boolean environnement_interne_ceinture_de_securite; //(un autre option pour la ceinture de securite)
	public static String environnement_interne_clignotants;
	public static String environnement_interne_eclairage;

	public static boolean clignotantDroiteOn;
	public static boolean clignotantGaucheOn;
	public static boolean pharesAntibrouillardOn;
	
	//constructeur par defaut
	public ContextVehicule() {
		//Les attributs sur la position du v�hicule par defaut
		position_du_vehicule = POSITION_DU_VEHICULE[0];
		indicateur_action_vehicule = INDICATEUR_ACTION_VEHICULE[0];
		
		//Les attributs sur l'acc�leration du v�hicule par defaut
		acceleration_longitudinale = ACCELERATION_LONGITUDINALE[0];
		freinage_longitudinal = FREINAGE_LONGITUDINAL[0];
		acceleration_transversale = ACCELERATION_TRANSVERSALE[0];
		
		//Les attributs sur la vitesse du v�hicule par defaut
		vitesse_du_vehicule = VITESSE_DU_VEHICULE[0];
		variation_vitesse_du_vehicule = VARIATION_VITESSE_DU_VEHICULE[0];
		
		//Les attributs sur l'action du conducteur par defaut
		action_conducteur_sur_le_volant = ACTION_CONDUCTEUR_SUR_LE_VOLANT[0];
		action_conducteur_niveau_pression_accelerateur = ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[0];
		action_conducteur_variation_pression_accelerateur = ACTION_CONDUCTEUR_VARIATION_PRESSION_ACCELERATEUR[0];
		action_conducteur_niveau_pression_freinage = ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[0];
		action_conducteur_variation_pression_freinage = ACTION_CONDUCTEUR_VARIATION_PRESSION_FREINAGE[0];
		
		//Les attributs sur l'environnement interne par defaut
		environnement_interne_climatisation = ENVIRONNEMENT_INTERNE_CLIMATISATION[0];
		//Boolean environnement_interne_climatisation = true; //(un autre option pour la climatisation interne)
		environnement_interne_musique = ENVIRONNEMENT_INTERNE_MUSIQUE[0];
		//Boolean environnement_interne_musique = true; //(un autre option pour la musique)
		environnement_interne_ceinture_de_securite = ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE[0];
		//Boolean environnement_interne_ceinture_de_securite = true; //(un autre option pour la ceinture de securite)
		environnement_interne_clignotants = ENVIRONNEMENT_INTERNE_CLIGNOTANTS[0];
		environnement_interne_eclairage = ENVIRONNEMENT_INTERNE_ECLAIRAGE[0];
		
		clignotantDroiteOn = false;
		clignotantGaucheOn = false;
		pharesAntibrouillardOn = false;
	}
	
	public String toString(){
		String result = "Contexte du v�hicule:\n\n";
		result += "Position du v�hicule: " + position_du_vehicule + "\n";
		result += "Indicateur action v�hicule: " + indicateur_action_vehicule + "\n";
		result += "Acc�leration longitudinale: " + acceleration_longitudinale + "\n";
		result += "Freinage longitudinal: " + freinage_longitudinal + "\n";
		result += "Acc�leration transversale: " + acceleration_transversale + "\n";
		result += "Vitesse: " + vitesse + "\n";
		result += "Vitesse du v�hicule: " + vitesse_du_vehicule + "\n";
		result += "Variation vitesse du v�hicule: " + variation_vitesse_du_vehicule + "\n";
		result += "Action conducteur sur le volant: " + action_conducteur_sur_le_volant + "\n";
		result += "Action conducteur niveau pression accelerateur: " + action_conducteur_niveau_pression_accelerateur + "\n";
		result += "Action conducteur variation pression acc�lerateur: " + action_conducteur_variation_pression_accelerateur + "\n";
		result += "Action conducteur niveau pression freinage: " + action_conducteur_niveau_pression_freinage + "\n";
		result += "Action conducteur variation pression freinage: " + action_conducteur_variation_pression_freinage + "\n\n";
		result += "Environnement interne climatisation: " + environnement_interne_climatisation + "\n";
		result += "Environnement interne musique: " + environnement_interne_musique + "\n";
		result += "Environnement interne ceinture de securit�: " + environnement_interne_ceinture_de_securite + "\n";
		result += "Environnement interne clignotants: " + environnement_interne_clignotants + "\n";
		result += "Environnement interne eclairage: " + environnement_interne_eclairage + "\n\n";
		return result;
	}
	
	
	public void setPosition_du_vehicule(String parPosition_du_vehicule) throws ContextException{
		if ((parPosition_du_vehicule != POSITION_DU_VEHICULE[0]) && (parPosition_du_vehicule != POSITION_DU_VEHICULE[1]) &&
		    (parPosition_du_vehicule != POSITION_DU_VEHICULE[2]) && (parPosition_du_vehicule != POSITION_DU_VEHICULE[3]))
		   throw new ContextException ("Position du vehicule: " + POSITION_DU_VEHICULE[0] + " or " + POSITION_DU_VEHICULE[1]);	
		position_du_vehicule = parPosition_du_vehicule;
	}
	
	public String getPosition_du_vehicule() {
		return position_du_vehicule;
	}

	public void setIndicateur_action_vehicule(String parIndicateur_action_vehicule) throws ContextException{
		if ((parIndicateur_action_vehicule != INDICATEUR_ACTION_VEHICULE[0]) && (parIndicateur_action_vehicule != INDICATEUR_ACTION_VEHICULE[1]) && 
			(parIndicateur_action_vehicule != INDICATEUR_ACTION_VEHICULE[2]))
		   throw new ContextException ("Indicateur action v�hicule: " + INDICATEUR_ACTION_VEHICULE[0] + " or " + INDICATEUR_ACTION_VEHICULE[1]
				                                             + " or " + INDICATEUR_ACTION_VEHICULE[2]);	
		indicateur_action_vehicule = parIndicateur_action_vehicule;
	}
	
	public String getIndicateur_action_vehicule() {
		return indicateur_action_vehicule;
	}
	
	public void setAcceleration_longitudinale(String parAcceleration_longitudinale) throws ContextException{
		if ((parAcceleration_longitudinale != ACCELERATION_LONGITUDINALE[0]) && (parAcceleration_longitudinale != ACCELERATION_LONGITUDINALE[1]) && 
			(parAcceleration_longitudinale != ACCELERATION_LONGITUDINALE[2]) && (parAcceleration_longitudinale != ACCELERATION_LONGITUDINALE[3]))
		   throw new ContextException ("Acceleration longitudinale: " + ACCELERATION_LONGITUDINALE[0] + " or " + ACCELERATION_LONGITUDINALE[1]
				                                             + " or " + ACCELERATION_LONGITUDINALE[2] + " or " + ACCELERATION_LONGITUDINALE[3]);	
		acceleration_longitudinale = parAcceleration_longitudinale;
	}
	
	public String getAcceleration_longitudinale() {
		return acceleration_longitudinale;
	}
	
	public void setFreinage_longitudinale(String parFreinage_longitudinal) throws ContextException{
		if ((parFreinage_longitudinal != FREINAGE_LONGITUDINAL[0]) && (parFreinage_longitudinal != FREINAGE_LONGITUDINAL[1]) && 
			(parFreinage_longitudinal != FREINAGE_LONGITUDINAL[2]) && (parFreinage_longitudinal != FREINAGE_LONGITUDINAL[3]))
		   throw new ContextException ("Freinage longitudinal: " + FREINAGE_LONGITUDINAL[0] + " or " + FREINAGE_LONGITUDINAL[1]
				                                             + " or " + FREINAGE_LONGITUDINAL[2] + " or " + FREINAGE_LONGITUDINAL[3]);	
		freinage_longitudinal = parFreinage_longitudinal;
	}
	
	public String getFreinage_longitudinal() {
		return freinage_longitudinal;
	}
	
	public void setAcceleration_transversale(String parAcceleration_transversale) throws ContextException{
		if ((parAcceleration_transversale != ACCELERATION_TRANSVERSALE[0]) && (parAcceleration_transversale != ACCELERATION_TRANSVERSALE[1]) && 
			(parAcceleration_transversale != ACCELERATION_TRANSVERSALE[2]) && (parAcceleration_transversale != ACCELERATION_TRANSVERSALE[3]))
		   throw new ContextException ("Acceleration transversale: " + ACCELERATION_TRANSVERSALE[0] + " or " + ACCELERATION_TRANSVERSALE[1]
				                                             + " or " + ACCELERATION_TRANSVERSALE[2] + " or " + ACCELERATION_TRANSVERSALE[3]);	
		acceleration_transversale = parAcceleration_transversale;
	}
	
	public String getAcceleration_transversale() {
		return acceleration_transversale;
	}
	
	public static void setVitesse(double parVitesse){
		vitesse = parVitesse;
	}
	
	//-------------------------------------------------------------------
	public static void setLimitationVitesse(double parLimitationVitesse){
		limitationVitesse = parLimitationVitesse;
	}
	
	//--------------------------------
	public static double getVitesse() {
		return vitesse;
	}
	
	//-------------------------------------
	public static double getLimitationVitesse() {
		return limitationVitesse;
	}
	
	//-----------------------------------
	public static void displayVitesse() {
		System.out.println("Current speed: " + getVitesse() + " kmh");
	}
	
	//----------------------------------------------
	public static void detectDangerDeLaVitesse() {
		if (vitesse <= limitationVitesse)
		{
			vitesseAdaptative = true;
			vitesseExcessive = false;
			vitesseDangereuse = false;
		}// vitesse adaptative
		else
			if ((vitesse > limitationVitesse) && (vitesse <= limitationVitesse * 1.20)) {
				vitesseAdaptative = false;
				vitesseExcessive = true;
				vitesseDangereuse = false;
			} //vitesse excessive
			else {
				vitesseAdaptative = false;
				vitesseExcessive = false;
				vitesseDangereuse = true;
			}//vitesse dangereuse	
	}
	
	
	public void setVitesse_du_vehicule(String parVitesse_du_vehicule) throws ContextException{
		if ((parVitesse_du_vehicule != VITESSE_DU_VEHICULE[0]) && (parVitesse_du_vehicule != VITESSE_DU_VEHICULE[1]) && 
			(parVitesse_du_vehicule != VITESSE_DU_VEHICULE[2]))
		   throw new ContextException ("Vitesse du v�hicule: " + VITESSE_DU_VEHICULE[0] + " or " + VITESSE_DU_VEHICULE[1] + " or " + VITESSE_DU_VEHICULE[2]);	
		vitesse_du_vehicule = parVitesse_du_vehicule;
	}
	
	public String getVitesse_du_vehicule() {
		return vitesse_du_vehicule;
	}
	
	public void setVariation_vitesse_du_vehicule(String parVariation_vitesse_du_vehicule) throws ContextException{
		if ((parVariation_vitesse_du_vehicule != VARIATION_VITESSE_DU_VEHICULE[0]) && (parVariation_vitesse_du_vehicule != VARIATION_VITESSE_DU_VEHICULE[1]) && 
			(parVariation_vitesse_du_vehicule != VARIATION_VITESSE_DU_VEHICULE[2]))
		   throw new ContextException ("Variation vitesse du v�hicule: " + VARIATION_VITESSE_DU_VEHICULE[0] + " or " + VARIATION_VITESSE_DU_VEHICULE[1] 
				                       + " or " + VARIATION_VITESSE_DU_VEHICULE[2]);	
		variation_vitesse_du_vehicule = parVariation_vitesse_du_vehicule;
	}
	
	public String getVariation_vitesse_du_vehicule() {
		return variation_vitesse_du_vehicule;
	}
	
	public void setAction_conducteur_sur_le_volant(String parAction_conducteur_sur_le_volant) throws ContextException{
		if ((parAction_conducteur_sur_le_volant != ACTION_CONDUCTEUR_SUR_LE_VOLANT[0]) && (parAction_conducteur_sur_le_volant != ACTION_CONDUCTEUR_SUR_LE_VOLANT[1]) && 
			(parAction_conducteur_sur_le_volant != ACTION_CONDUCTEUR_SUR_LE_VOLANT[2]) && (parAction_conducteur_sur_le_volant != ACTION_CONDUCTEUR_SUR_LE_VOLANT[3]))
		   throw new ContextException ("Action conducteur sur le volant: " + ACTION_CONDUCTEUR_SUR_LE_VOLANT[0] + " or " + ACTION_CONDUCTEUR_SUR_LE_VOLANT[1] 
				                       + " or " + ACTION_CONDUCTEUR_SUR_LE_VOLANT[2] + " or " + ACTION_CONDUCTEUR_SUR_LE_VOLANT[3]);	
		action_conducteur_sur_le_volant = parAction_conducteur_sur_le_volant;
	}
	
	public String getAction_conducteur_sur_le_volant() {
		return action_conducteur_sur_le_volant;
	}
	
	public void setAction_conducteur_niveau_pression_accelerateur(String parAction_conducteur_niveau_pression_accelerateur) throws ContextException{
		if ((parAction_conducteur_niveau_pression_accelerateur != ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[0]) && 
			(parAction_conducteur_niveau_pression_accelerateur != ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[1]) && 
			(parAction_conducteur_niveau_pression_accelerateur != ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[2]))
		   throw new ContextException ("Action conducteur niveau pression acc�lerateur: " + ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[0] + " or " +  
				                        ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[1] + " or " + ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[2]);	
		action_conducteur_niveau_pression_accelerateur = parAction_conducteur_niveau_pression_accelerateur;
	}
	
	public String getAction_conducteur_niveau_pression_accelerateur() {
		return action_conducteur_niveau_pression_accelerateur;
	}
	
	public void setAction_conducteur_variation_pression_accelerateur(String parAction_conducteur_variation_pression_accelerateur) throws ContextException{
		if ((parAction_conducteur_variation_pression_accelerateur != ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[0]) && 
			(parAction_conducteur_variation_pression_accelerateur != ACTION_CONDUCTEUR_NIVEAU_PRESSION_ACCELERATEUR[1]))
		   throw new ContextException ("Action conducteur variation pression acc�lerateur: " + ACTION_CONDUCTEUR_VARIATION_PRESSION_ACCELERATEUR[0] + " or " +  
				                        ACTION_CONDUCTEUR_VARIATION_PRESSION_ACCELERATEUR[1]);	
		action_conducteur_variation_pression_accelerateur = parAction_conducteur_variation_pression_accelerateur;
	}
	
	public String getAction_conducteur_variation_pression_accelerateur() {
		return action_conducteur_variation_pression_accelerateur;
	}
	
	public void setAction_conducteur_niveau_pression_freinage(String parAction_conducteur_niveau_pression_freinage) throws ContextException{
		if ((parAction_conducteur_niveau_pression_freinage != ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[0]) && 
			(parAction_conducteur_niveau_pression_freinage  != ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[1]) && 
			(parAction_conducteur_niveau_pression_freinage  != ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[2]))
		   throw new ContextException ("Action conducteur niveau pression freinage: " + ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[0] + " or " +  
				                        ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[1] + " or " + ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[2]);	
		action_conducteur_niveau_pression_freinage = parAction_conducteur_niveau_pression_freinage;
	}
	
	public String getAction_conducteur_niveau_pression_freinage() {
		return action_conducteur_niveau_pression_freinage;
	}
	
	public void setAction_conducteur_variation_pression_freinage(String parAction_conducteur_variation_pression_freinage) throws ContextException{
		if ((parAction_conducteur_variation_pression_freinage != ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[0]) && 
			(parAction_conducteur_variation_pression_freinage != ACTION_CONDUCTEUR_NIVEAU_PRESSION_FREINAGE[1]))
		   throw new ContextException ("Action conducteur variation pression freinage: " + ACTION_CONDUCTEUR_VARIATION_PRESSION_FREINAGE[0] + " or " +  
				                        ACTION_CONDUCTEUR_VARIATION_PRESSION_FREINAGE[1]);	
		action_conducteur_variation_pression_freinage = parAction_conducteur_variation_pression_freinage;
	}
	
	public String getAction_conducteur_variation_pression_freinage() {
		return action_conducteur_variation_pression_freinage;
	}
	
	//Les attributs sur l'environnement interne
	//String environnement_interne_eclairage;
	
	//Les constantes sur l'environnement interne
	//final String[] ENVIRONNEMENT_INTERNE_ECLAIRAGE = {"Aucun", "Position", "Croisement", "Route", "AB avant", "AB arriere"};
	
	public void setEnvironnement_interne_climatisation(String parEnvironnement_interne_climatisation) throws ContextException{
		if ((parEnvironnement_interne_climatisation != ENVIRONNEMENT_INTERNE_CLIMATISATION[0]) && 
			(parEnvironnement_interne_climatisation != ENVIRONNEMENT_INTERNE_CLIMATISATION[1]))
		   throw new ContextException ("Environnement interne climatisation: " + ENVIRONNEMENT_INTERNE_CLIMATISATION[0] + " or " +  
				                        ENVIRONNEMENT_INTERNE_CLIMATISATION[1]);	
			environnement_interne_climatisation= parEnvironnement_interne_climatisation;
	}
	
	public String getEnvironnement_interne_climatisation() {
		return environnement_interne_climatisation;
	}
	
	public void setEnvironnement_interne_musique(String parEnvironnement_interne_musique) throws ContextException{
		if ((parEnvironnement_interne_musique != ENVIRONNEMENT_INTERNE_MUSIQUE[0]) && 
			(parEnvironnement_interne_musique != ENVIRONNEMENT_INTERNE_MUSIQUE[1]))
		   throw new ContextException ("Environnement interne musique: " + ENVIRONNEMENT_INTERNE_MUSIQUE[0] + " or " +  
				                        ENVIRONNEMENT_INTERNE_MUSIQUE[1]);	
			environnement_interne_musique= parEnvironnement_interne_musique;
	}
	
	public String getEnvironnement_interne_musique() {
		return environnement_interne_musique;
	}
	
	public void setEnvironnement_interne_ceinture_de_securite(String parEnvironnement_interne_ceinture_de_securite) throws ContextException{
		if ((parEnvironnement_interne_ceinture_de_securite != ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE[0]) && 
			(parEnvironnement_interne_ceinture_de_securite != ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE[1]))
		   throw new ContextException ("Environnement interne ceinture de securit�: " + ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE[0] + " or " +  
				                        ENVIRONNEMENT_INTERNE_CEINTURE_DE_SECURITE[1]);	
			environnement_interne_ceinture_de_securite= parEnvironnement_interne_ceinture_de_securite;
	}
	
	public String getEnvironnement_interne_ceinture_de_securite() {
		return environnement_interne_ceinture_de_securite;
	}
	
	public void setEnvironnement_interne_clignotants(String parEnvironnement_interne_clignotants) throws ContextException{
		if ((parEnvironnement_interne_clignotants != ENVIRONNEMENT_INTERNE_CLIGNOTANTS[0]) && 
			(parEnvironnement_interne_clignotants != ENVIRONNEMENT_INTERNE_CLIGNOTANTS[1]) &&
			(parEnvironnement_interne_clignotants != ENVIRONNEMENT_INTERNE_CLIGNOTANTS[2]) &&
			(parEnvironnement_interne_clignotants != ENVIRONNEMENT_INTERNE_CLIGNOTANTS[3]))
		   throw new ContextException ("Environnement interne clignotants: " + ENVIRONNEMENT_INTERNE_CLIGNOTANTS[0] + " or " +  ENVIRONNEMENT_INTERNE_CLIGNOTANTS[1]
				                       + " or " + ENVIRONNEMENT_INTERNE_CLIGNOTANTS[2] + " or " + ENVIRONNEMENT_INTERNE_CLIGNOTANTS[3]);	
			environnement_interne_clignotants = parEnvironnement_interne_clignotants;
	}
	
	static public String getEnvironnement_interne_clignotants() {
		return environnement_interne_clignotants;
	}
	
	public void setEnvironnement_interne_eclairage(String parEnvironnement_interne_eclairage) throws ContextException{
		if ((parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[0]) && (parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[1]) &&
			(parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[2]) && (parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[3]) &&
			(parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[4]) && (parEnvironnement_interne_eclairage != ENVIRONNEMENT_INTERNE_ECLAIRAGE[5]))
		   throw new ContextException ("Environnement interne eclairage: " + ENVIRONNEMENT_INTERNE_ECLAIRAGE[0] + " or " +  ENVIRONNEMENT_INTERNE_ECLAIRAGE[1]
				                       + " or " + ENVIRONNEMENT_INTERNE_ECLAIRAGE[2] + " or " + ENVIRONNEMENT_INTERNE_ECLAIRAGE[3]
				                       + " or " + ENVIRONNEMENT_INTERNE_ECLAIRAGE[4] + " or " + ENVIRONNEMENT_INTERNE_ECLAIRAGE[5]);	
			environnement_interne_eclairage = parEnvironnement_interne_eclairage;
	}
	
	public String getEnvironnement_interne_eclairage() {
		return environnement_interne_eclairage;
	}
	
	
}//end class
