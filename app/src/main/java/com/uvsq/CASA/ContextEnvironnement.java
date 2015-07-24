package com.uvsq.CASA;

public class ContextEnvironnement {

	//Les constantes sur la condition m�t�orologique
	final String[] METEO_PLUIE = {"Non", "Pluie l�g�re", "Pluie forte"};
	final String[] METEO_TEMPERATURE = {"Moyenne", "Basse", "Chaude"};
	final String[] METEO_BROUILLARD = {"Non", "Brouillard l�ger", "Brouillard fort"};
	final String[] METEO_VENT = {"Moyen", "Fort", "Tr�s fort"}; 
	final String[] ADHERENCE_VERGLAS = {"Non", "Oui"};
	final String[] ADHERENCE_NEIGE = {"Non", "Neige fine", "Neige soutenue"};
	final String[] ADHERENCE_INONDATION = {"Non", "Inondation l�g�re", "Inondation forte"};
	
	//Les attributs sur la condition m�t�orologique
	String meteo_pluie;
	String meteo_temperature;
	double temperature;
	String meteo_brouillard;
	String meteo_vent;
	String adherence_verglas;
	String adherence_neige;
	String adherence_inondation;
	
	//Les constantes sur la visibilit�
	final String[] VISIBILITE_PAR_DISTANCE = {"Visibilit� optimale", "Visibilit� moyenne", "Visibilit� courte/nulle"};
	final String[] VISIBILITE_PAR_TYPE_DE_PERTURBATION = {"Non identifi�", "Nuit", "Brouillard", "Pluie", "Eblouissement", "Masquage"};
	
	//Les attributs sur la visibilit�
	String visibilite_par_distance;
	String visibilite_par_type_de_perturbation;
	
	//Les constantes le contexte de la route
	final String[] TYPE_DE_LA_ROUTE = {"Autoroute", "Nationale", "D�partementale", "Avenue", "Ruelle"};
	final String[] QUALITE_DE_REVETEMENT_DE_LA_ROUTE = {"Normale", "Graviers", "Trou", "Travaux", "Verre bris�e"};
	final String[] CODE_DE_LA_ROUTE = {"Intersection prot�g�", "Intersection priorit�", "Limitation de vitesse", "Stop", 
			                           "Inter distance", "Sens unique", "Feux rouges", "Travaux"};
	
	//Les attributs sur le contexte de la route
	String type_de_la_route;
	String qualite_de_revetement_de_la_route;
	String code_de_la_route;
	
	//Les constantes sur l'evenement sur le trajet
	final String[] VITESSE_DOBSTACLE = {"Fixe", "Rapprochement lent", "Rapprochement rapide"};
	final String[] TAILLE_DOBSTACLE = {"P�tite", "Moyenne", "Grande"};
	final String[] POSITION_DOBSTACLE = {"Hors trajectoire", "Sur trajectoire"};
	final String[] TYPE_DOBSTACLE= {"Humain", "V�lo", "Voiture", "Camion", "ORNI"};
	final String[] ACCIDENT_SUR_LE_TRAJET = {"Accident mineur", "Accident grave"};
	final String[] TRAVAUX_SUR_LE_TRAJET = {"Circulation non-blocqu�e", "Circulation blocqu�e"};
	final String[] EMBOUTEILLAGE_SUR_LE_TRAJET = {"Embouteillage mineur", "Embouteillage majeure"};
	final String[] MANIF_SUR_LE_TRAJET = {"Circulation non-blocqu�e", "Circulation blocqu�e"};
	
	//Les attributs sur l'evement sur le trajet
	String vitesse_dobstacle;
	String taille_dobstacle;
	String position_dobstacle;
	String type_dobstacle;
	String accident_sur_le_trajet;
	String travaux_sur_le_trajet;
	String embouteillage_sur_le_trajet;
	String manif_sur_le_trajet;
	
	public ContextEnvironnement(){
		
		//Les valeurs des attributs par defaut
		meteo_pluie = METEO_PLUIE[0];
		meteo_temperature = METEO_TEMPERATURE[0];
		double temperature = 21.0;
		meteo_brouillard = METEO_BROUILLARD[0];
		meteo_vent = METEO_VENT[0];
		adherence_verglas = ADHERENCE_VERGLAS[0];
		adherence_neige = ADHERENCE_NEIGE[0];
		adherence_inondation = ADHERENCE_INONDATION[0];	
		
		visibilite_par_distance = VISIBILITE_PAR_DISTANCE[0];
		visibilite_par_type_de_perturbation = VISIBILITE_PAR_TYPE_DE_PERTURBATION[0];
		
		type_de_la_route = TYPE_DE_LA_ROUTE[0];
		qualite_de_revetement_de_la_route = QUALITE_DE_REVETEMENT_DE_LA_ROUTE[0];
		code_de_la_route = CODE_DE_LA_ROUTE[0];
		
		vitesse_dobstacle = VITESSE_DOBSTACLE[0];
		taille_dobstacle = TAILLE_DOBSTACLE[0];
		position_dobstacle = POSITION_DOBSTACLE[0];
		type_dobstacle = TYPE_DOBSTACLE[0];
		accident_sur_le_trajet = ACCIDENT_SUR_LE_TRAJET[0];
		travaux_sur_le_trajet = TRAVAUX_SUR_LE_TRAJET[0];
		embouteillage_sur_le_trajet = EMBOUTEILLAGE_SUR_LE_TRAJET[0];
		manif_sur_le_trajet = MANIF_SUR_LE_TRAJET[0];
		
	}
	
	public String getMeteo_brouillard() {
		return meteo_brouillard;
	}
	
	
	
	
	//String type_dobstacle;
	//final String[] TYPE_DOBSTACLE= {"Humain", "V�lo", "Voiture", "Camion", "ORNI"};
	
	
	//obstacle
	public void setVitesse_dobstacle(String parVitesse_dobstacle) throws ContextException {
		if ((parVitesse_dobstacle !=  VITESSE_DOBSTACLE[0]) && (parVitesse_dobstacle !=  VITESSE_DOBSTACLE[1]) && (parVitesse_dobstacle !=  VITESSE_DOBSTACLE[2]))
			throw new ContextException("Vitesse d'obstacle: " + VITESSE_DOBSTACLE[0] + " or " + VITESSE_DOBSTACLE[1] + " or " + VITESSE_DOBSTACLE[2]);
		vitesse_dobstacle = parVitesse_dobstacle;
	}
	
	public String getVitesse_dobstacle() {
		return vitesse_dobstacle;
	}
	
	public void setTaille_dobstacle(String parTaille_dobstacle) throws ContextException {
		if ((parTaille_dobstacle !=  TAILLE_DOBSTACLE[0]) && (parTaille_dobstacle !=  TAILLE_DOBSTACLE[1]) && (parTaille_dobstacle !=  TAILLE_DOBSTACLE[2]))
			throw new ContextException("Taille d'obstacle: " + TAILLE_DOBSTACLE[0] + " or " + TAILLE_DOBSTACLE[1] + " or " + TAILLE_DOBSTACLE[2]);
		taille_dobstacle = parTaille_dobstacle;
	}
	
	public String getTaille_dobstacle() {
		return taille_dobstacle;
	}
	
	public void setPosition_dobstacle(String parPosition_dobstacle) throws ContextException {
		if ((parPosition_dobstacle !=  POSITION_DOBSTACLE[0]) && (parPosition_dobstacle !=  POSITION_DOBSTACLE[1]))
			throw new ContextException("Position d'obstacle: " + POSITION_DOBSTACLE[0] + " or " + POSITION_DOBSTACLE[1]);
		position_dobstacle = parPosition_dobstacle;
	}
	
	public String getPosition_dobstacle() {
		return position_dobstacle;
	}
	
	public void setType_dobstacle(String parType_dobstacle) throws ContextException {
		if ((parType_dobstacle !=  TYPE_DOBSTACLE[0]) && (parType_dobstacle !=  TYPE_DOBSTACLE[1]) && (parType_dobstacle !=  TYPE_DOBSTACLE[2]) 
			&& (parType_dobstacle !=  TYPE_DOBSTACLE[3]) && (parType_dobstacle !=  TYPE_DOBSTACLE[4]))
			throw new ContextException("Type d'obstacle: " + TYPE_DOBSTACLE[0] + " or " + TYPE_DOBSTACLE[1] + " or " + TYPE_DOBSTACLE[2]
										+ " or " + TYPE_DOBSTACLE[3] + " or " + TYPE_DOBSTACLE[4]);
		type_dobstacle = parType_dobstacle;
	}
	
	public String getType_dobstacle() {
		return type_dobstacle;
	}
	
}
