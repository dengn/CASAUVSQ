package com.uvsq.CASA;



public class ContextFusion  {
	ContextConducteur contextConducteur;
	ContextVehicule contextVehicule;
	ContextEnvironnement contextEnvironnement;
	ContextInfrastructure contextInfrastructure;
	
	//Les attributs de conduit
	
	
	//constructeur
	public ContextFusion() {
		contextConducteur = new ContextConducteur();
		contextVehicule = new ContextVehicule();
		contextEnvironnement = new ContextEnvironnement();
		contextInfrastructure = new ContextInfrastructure();	
	}
	
	public void driving() {
		System.out.println("Now driving");
	}
	
	//STOP
	public static void setDistance_avec_Stop(double parDistance_avec_Stop){
		GlobalData.distance_avec_Stop = parDistance_avec_Stop;
		if (GlobalData.distance_avec_Stop <= 200.00)
			GlobalData.informerConducteur_distance_avec_Stop = true;
	}

	public double getDistance_avec_Stop(){
		return GlobalData.distance_avec_Stop;
	}
	
	public void displayDistance_avec_Stop(){
		System.out.println("Distance voiture et Stop = " + GlobalData.distance_avec_Stop);
	}
	
	public void detecteVitesse_dans_Stop(){
		contextVehicule.displayVitesse();
		if (contextVehicule.vitesse == 0.0)
			System.out.println("V�hicule arrete devant le panneau de Stop");
		else {
			System.out.println("V�hicule n'arrete pas devant le panneau de Stop");
			GlobalData.informerConducteur_oubli_arreter_au_Stop = true;}
	}
	
	//Passage pi�ton
	public void setDistance_avec_PassagePieton(double parDistance_avec_PassagePieton) {
		GlobalData.distance_avec_PassagePieton = parDistance_avec_PassagePieton;
		if (GlobalData.distance_avec_PassagePieton <= 200.00)
			GlobalData.informerConducteur_distance_avec_PassagePieton = true;
	}
	
	public void displayDistance_avec_PassagePieton() {
		System.out.println("Distance voiture et passage pi�ton = " + GlobalData.distance_avec_PassagePieton);
	}
	
	public void detecteVitesse_dans_PassagePieton(){
		contextVehicule.displayVitesse();
		if (contextVehicule.vitesse == 0.0)
			System.out.println("V�hicule arrete devant le passage pi�ton");
		else {
			System.out.println("V�hicule n'arrete pas devant le passage pi�ton");
			GlobalData.informerConducteur_oubli_arreter_au_PassagePieton = true;
		}
	}
	
	//Intersection
	public static void setDistance_avec_Intersection(double parDistance_avec_Intersection){
		GlobalData.distance_avec_Intersection = parDistance_avec_Intersection;
		if (GlobalData.distance_avec_Intersection <= 150.00)
			GlobalData.informerConducteur_distance_avec_Intersection = true;
	}

	public double getDistance_avec_Intersection(){
		return GlobalData.distance_avec_Intersection;
	}
	
	public void displayDistance_avec_Intersection(){
		System.out.println("Distance voiture et Intersection = " + GlobalData.distance_avec_Intersection);
	}
	
	//Turn right
	public void checkClignotant_Tourner_a_droite() {
		System.out.println("Activation de clignotant � droite: " + getClignotant_Tourner_a_droite());
		if (!contextVehicule.clignotantDroiteOn)
			GlobalData.informerConducteur_oubli_clignotant_droite = true;
	}
	
	public boolean getClignotant_Tourner_a_droite() {
		return contextVehicule.clignotantDroiteOn;
	}
	
	//Turn left
	public void checkClignotant_Tourner_a_gauche() {
		System.out.println("Activation de clignotant � gauche: " + getClignotant_Tourner_a_gauche());
		if (!contextVehicule.clignotantGaucheOn)
			GlobalData.informerConducteur_oubli_clignotant_gauche = true;
	}
	
	public boolean getClignotant_Tourner_a_gauche() {
		return contextVehicule.clignotantGaucheOn;
	}
	
	public void determineVitesseDansBrouillard(double vitesse) {
		contextVehicule.displayVitesse();
		if ((vitesse >= 30.0) && (vitesse <= 40.0)) {
			GlobalData.vitesseAdaptee_sur_le_brouillard = true;
			System.out.println("Vitesse adapt�e sur le brouillard");
			}
		else if (((vitesse >= 41.0) && (vitesse <= 50.0))){
			GlobalData.vitesseExcessive_sur_le_brouillard = true;
			GlobalData.informerConducteur_vitesseExcessive_sur_le_brouillard = true;
			System.out.println("Vitesse excessive sur le brouillard");
			}
		else{
			GlobalData.vitesseDangereuse_sur_le_brouillard = true;
			GlobalData.informerConducteur_vitesseDangereuse_sur_le_brouillard = true;
			System.out.println("Vitesse dangereuse sur le brouillard");
			}
	}
	
	public void setRespect_sur_distance_de_securite(boolean parRespectDistance) {
		if (!parRespectDistance)
			GlobalData.respectDistance_de_securite = false;
		else
			GlobalData.respectDistance_de_securite = true;
		System.out.println("Respect sur la distance de securit� entre voitures: " + GlobalData.respectDistance_de_securite);
	}
	
	public void setActivation_phares_de_brouillard(boolean parActivation_phares_de_brouillard) {
		if (parActivation_phares_de_brouillard) {
			contextVehicule.pharesAntibrouillardOn = true;
			GlobalData.activationPharesBrouillard = true;
			GlobalData.informerConducteur_echecActivation_phares_de_brouillard = false;
		}	
		else
			{
			contextVehicule.pharesAntibrouillardOn = false;
			GlobalData.activationPharesBrouillard = false;
			GlobalData.informerConducteur_echecActivation_phares_de_brouillard = true;
		}
		System.out.println("Activation de phares de brouillard: " + contextVehicule.pharesAntibrouillardOn);
	}
	
	public void identifyObstacle(){
		System.out.println("Information sur l'obstacle: ");
		System.out.println("----------------------------");
		System.out.println("Type d'obstacle: " + contextEnvironnement.type_dobstacle);
		System.out.println("Position d'obstacle: " + contextEnvironnement.position_dobstacle);
		System.out.println("Taille d'obstacle: " + contextEnvironnement.taille_dobstacle);
		System.out.println("Vitesse d'obstacle: " + contextEnvironnement.vitesse_dobstacle);
	}
	
	public void determineVitesse_face_a_lobstacle() {
		contextVehicule.displayVitesse();
		if ((contextVehicule.vitesse >= 20) && (contextVehicule.vitesse <= 40)) { //vitesse adapt�e 
			GlobalData.vitesseAdaptee_face_a_lobstacle = true;
			System.out.println("Vitesse adapt�e face � l'obstacle.");
			}
		else 
			if ((contextVehicule.vitesse >= 41) && (contextVehicule.vitesse <= 50)) { //vitesse excessive
				GlobalData.vitesseExcessive_face_a_lobstacle = true;
				GlobalData.informerConducteur_vitesseExcessive_face_a_lobstacle = true;
				System.out.println("Vitesse excessive face � l'obstacle.");
			} 
			else {//vitesse dangereuse
				GlobalData.vitesseDangereuse_face_a_lobstacle = true;
				GlobalData.informerConducteur_vitesseDangereuse_face_a_lobstacle = true;
				System.out.println("Vitesse dangereuse face � l'obstacle.");
			}
	}
	
	public void setTrafficLibre_VoieOpposee(boolean parTrafficLibre_VoieOpposee) {
		if (!parTrafficLibre_VoieOpposee) {
			GlobalData.trafficLibre_VoieOpposee = false;
			GlobalData.informerConducteur_trafficPasLibre_VoieOpposee = true;
		}
		else {
			GlobalData.trafficLibre_VoieOpposee = true;
			GlobalData.informerConducteur_trafficPasLibre_VoieOpposee = false;
		}
		System.out.println("Traffic dans la voie oppos�e, est-il libre? : " + parTrafficLibre_VoieOpposee);
	}
	
	
	//Passage pi�ton
	public void setDistance_avec_Passage_pieton(double parDistance_avec_Passage_pieton){
		GlobalData.distance_avec_PassagePieton = parDistance_avec_Passage_pieton;
	}

	public double getDistance_avec_Passage_pieton(){
		return GlobalData.distance_avec_PassagePieton;
	}
	
	public void setDistance_avec_vehicule_avant(double parDistance_avec_Vehicule_avant){
		GlobalData.distance_avec_Vehicule_avant = parDistance_avec_Vehicule_avant;
	}

	public double getDistance_avec_Vehicule_avant(){
		return GlobalData.distance_avec_Vehicule_avant;
	}
	
	public void setDistance_avec_vehicule_derriere(double parDistance_avec_Vehicule_derriere){
		GlobalData.distance_avec_Vehicule_derriere = parDistance_avec_Vehicule_derriere;
	}

	public double getDistance_avec_Vehicule_derriere(){
		return GlobalData.distance_avec_Vehicule_derriere;
	}
	
	//Attention du conducteur sur la conduite
	public void identifyAttention_du_conducteur_sur_la_conduite() {
	    System.out.println("Indicateur attention du conducteur sur la conduite: " +
	      contextConducteur.getIndicateur_attention_sur_la_conduite());
		if (contextConducteur.indicateur_attention_sur_la_conduite == contextConducteur.INDICATEUR_ATTENTION_SUR_LA_CONDUITE[1]) {
			GlobalData.attentionConducteur_en_baisse = true;
		}
		
	}
	
	
	
	
}
