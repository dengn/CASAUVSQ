package com.uvsq.CASA;

public class ContextConducteur {
	//Les constantes sur le niveau de stress
	final String[] NERVOSITE = {"L�g�re", "Elev�e"};
	final String[] ACCELERATION = {"Normale","Brutale"}; 
	final String[] EXPRESSION_DE_VISAGE = {"Normale", "En col�re"};
	final String[] INDICATEUR_DE_STRESS ={"Pas stress�", "Stress�", "Tr�s stress�"};
	
	//Les constantes sur la fatigue du conducteur
	final String[] DETECTION_SOMNOLENCE = {"Yeux ouverts", "Yeux en somnolence"};
	final String[] MOUVEMENT_DE_VOLANT = {"Normal", "Pas de mouvement"};
	final String[] OSCILLATION_SUR_LA_VOIE = {"Non", "Oui"};
	final String[] MOUVEMENT_DE_CONDUCTEUR = {"Normal", "Absence de mouvement"};
	final String[] INDICATEUR_DE_FATIGUE = {"Non fatigu�", "Fatigu�", "Tr�s fatigu�"};
	
	//les constantes sur la detection de malaise
	final String[] RYTHME_CARDIAQUE = {"Normal", "Impulses cardiaques irreguliers"};
	final String[] GLYCEMIE = {"Normal", "En baise", "Elev�e"};
	final String[] INDICATEUR_DE_MALAISE = {"Pas de malaise", "Hypoglyc�mie", "Hyperglyc�mie", "Tachycardie", "Tachycardie et hypoglyc�mie", "Tachycardie et hyperglyc�mie"};
	
	//les constantes sur l'attention sur la conduite
	final String[] PRESENCE_PASSAGERS = {"Absence de passagers", "Presence de passagers"}; //Co-passagers dans la voiture
	final String[] CONVERSATION_AVEC_PASSAGERS = {"Non", "Oui"};
	final String[] CONVERSATION_TELEPHONIQUE = {"Pas en conversation", "En conversation"};
	final String[] POSITION_DES_MAINS = {"Mains sur le volant", "Mains pas sur le volant"};
	final String[] DIRECTION_DE_REGARD = {"Dans l'axe de la conduite", "En direction de retroviseur gauche", "Vers retroviseur interieure", 
			                              "Vers la console centrale", "Vers le passagers avant", "Ailleurs"};
	final String[] TEMPS_DE_REGARD = {"En permanence", "Soutenue", "Furtif"};
	final String[] INDICATEUR_ATTENTION_SUR_LA_CONDUITE = {"Attentif", "Pas attentif"};
	
	final String[] TYPE_DE_CONDUCTEUR = {"Calme Rapide", "Calme Lente", "Sportif", "Nerveux"};
	

	//Les attributs de niveau de stress
	String nervosite;
	String acceleration;
	String expression_de_visage;
	String indicateur_de_stress;
	
	//Les attributs de fatigue ou somnolence
	String detection_somnolence;
	String mouvement_de_volant;
	String oscillation_sur_la_voie;
	String mouvement_de_conducteur;
	String indicateur_de_fatigue;
	
	//Les attributs de malaise d�tect�e
	String rythme_cardiaque;
	String glycemie;
	String indicateur_de_malaise;
	
	//Les attributs sur l'attention sur la conduite
	String presence_passagers;
	String conversation_avec_passagers;
	String conversation_telephonique;
	String position_des_mains;
	String direction_de_regard;
	String temps_de_regard;
	String indicateur_attention_sur_la_conduite;
	
	//L'attribut sur le type de conducteur
	String type_de_conducteur;
	
	//constructeur par defaut
	public ContextConducteur() {
		//Les attributs de niveau de stress par defaut
		nervosite = NERVOSITE[0];
		acceleration = ACCELERATION[0];
		expression_de_visage = EXPRESSION_DE_VISAGE[0];
		indicateur_de_stress = INDICATEUR_DE_STRESS[0];
		
		//Les attributs de fatigue de conducteur par defaut
		detection_somnolence = DETECTION_SOMNOLENCE[0];
		mouvement_de_volant = MOUVEMENT_DE_VOLANT[0];
		oscillation_sur_la_voie = OSCILLATION_SUR_LA_VOIE[0];
		mouvement_de_conducteur = MOUVEMENT_DE_CONDUCTEUR[0];
		indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[0];
		
		//Les attributs de malaise d�tect�e par defaut
		rythme_cardiaque = RYTHME_CARDIAQUE[0];
		glycemie = GLYCEMIE[0];
		indicateur_de_malaise = INDICATEUR_DE_MALAISE[0];
		
		//Les attributs de l'attention sur la conduite par defaut
		presence_passagers = PRESENCE_PASSAGERS[0];
		conversation_avec_passagers = CONVERSATION_AVEC_PASSAGERS[0];
		conversation_telephonique = CONVERSATION_TELEPHONIQUE[0];
		position_des_mains = POSITION_DES_MAINS[0];
		direction_de_regard = DIRECTION_DE_REGARD[0];
		temps_de_regard = TEMPS_DE_REGARD[0];
		indicateur_attention_sur_la_conduite = INDICATEUR_ATTENTION_SUR_LA_CONDUITE[0];
		
		//L'attribut sur le type de conducteur par defaut
		type_de_conducteur = TYPE_DE_CONDUCTEUR[0];
		
		
	}//end constructeur par defaut
	
	public String toString(){
		String result = "Contexte du conducteur:\n\n";
		result += "Nervosite: " + nervosite + "\n";
		result += "Acc�leration: " + acceleration + "\n";
		result += "Expression de visage: " + expression_de_visage + "\n";
		result += "Indicateur de stress: " + indicateur_de_stress + "\n\n";
		result += "D�tection somnolence: " + detection_somnolence + "\n";
		result += "Mouvement de volant: " + mouvement_de_volant + "\n";
		result += "Oscillation sur la voie: " + oscillation_sur_la_voie + "\n";
		result += "Mouvement de conducteur: " + mouvement_de_conducteur + "\n";
		result += "Indicateur de fatigue: " + indicateur_de_fatigue + "\n\n";
		result += "Rythme cardiaque: " + rythme_cardiaque + "\n";
		result += "Glyc�mie: " + glycemie + "\n";
		result += "Indicateur de malaise: " + indicateur_de_malaise + "\n\n";
		result += "Presence passagers: " + presence_passagers + "\n";
		result += "Conversation avec passagers: " + conversation_avec_passagers + "\n";
		result += "Conversation t�l�phonique: " + conversation_telephonique + "\n";
		result += "Position des mains: " + position_des_mains + "\n";
		result += "Direction de regard: " + direction_de_regard + "\n";
		result += "Temps de regard: " + temps_de_regard + "\n";
		result += "Indicateur attention sur la conduite: " + indicateur_attention_sur_la_conduite + "\n\n";
		result += "Type de conducteur: " + type_de_conducteur +"\n";
		return result;
	}
	
	//set pour les constantes
	//nervosite = NERVOSITE[0];
	//acceleration = ACCELERATION[0];
	//expression_de_visage = EXPRESSION_DE_VISAGE[0];
	//indicateur_de_stress = INDICATEUR_DE_STRESS[0];
	
	//Les attributs de fatigue de conducteur par defaut
	//detection_somnolence = DETECTION_SOMNOLENCE[0];
	//mouvement_de_volant = MOUVEMENT_DE_VOLANT[0];
	//oscillation_sur_la_voie = OSCILLATION_SUR_LA_VOIE[0];
	//mouvement_de_conducteur = MOUVEMENT_DE_CONDUCTEUR[0];
	//indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[0];
	
	//Les attributs de malaise d�tect�e par defaut
	//rythme_cardiaque = RYTHME_CARDIAQUE[0];
	//glycemie = GLYCEMIE[0];
	//indicateur_de_malaise = INDICATEUR_DE_MALAISE[0];
	
	//Les attributs de l'attention sur la conduite par defaut
	//presence_passagers = PRESENCE_PASSAGERS[0];
	//conversation_avec_passagers = CONVERSATION_AVEC_PASSAGERS[0];
	//conversation_telephonique = CONVERSATION_TELEPHONIQUE[0];
	//position_des_mains = POSITION_DES_MAINS[0];
	//direction_de_regard = DIRECTION_DE_REGARD[0];
	//temps_de_regard = TEMPS_DE_REGARD[0];
	//indicateur_attention_sur_la_conduite = INDICATEUR_ATTENTION_SUR_LA_CONDUITE[0];
	
	//L'attribut sur le type de conducteur par defaut
	//type_de_conducteur = TYPE_DE_CONDUCTEUR[0];
	
	
	public void setPresencePassagersConstante(int index) throws ContextException {
		if ((index < 0) || (index > 1)) 
			throw new ContextException("Integer passed must be either 1 or 0 only");
		presence_passagers = PRESENCE_PASSAGERS[index];
	}
	
	public void setConversationAvecPassagersConstante(int index) throws ContextException {
		if ((index < 0) || (index > 1)) 
			throw new ContextException("Integer passed must be either 1 or 0 only");
		conversation_avec_passagers = CONVERSATION_AVEC_PASSAGERS[index];
	}
	
	public void setConversationTelephoniqueConstante(int index) throws ContextException {
		if ((index < 0) || (index > 1)) 
			throw new ContextException("Integer passed must be either 1 or 0 only");
		conversation_telephonique = CONVERSATION_TELEPHONIQUE[index];
	}
	
	public void setPositionDesMainsConstante(int index) throws ContextException {
		if ((index < 0) || (index > 1)) 
			throw new ContextException("Integer passed must be either 1 or 0 only");
		position_des_mains = POSITION_DES_MAINS[index];
	}
	
	public void setDirectionDeRegardConstante(int index) throws ContextException {
		if ((index < 0) || (index > 5)) 
			throw new ContextException("Integer passed must be between 0 to 5 only");
		direction_de_regard = DIRECTION_DE_REGARD[index];
	}
	
	public void setTempsDeRegardConstante(int index) throws ContextException {
		if ((index < 0) || (index > 2)) 
			throw new ContextException("Integer passed must be between 0 to 2 only");
		temps_de_regard = TEMPS_DE_REGARD[index];
	}
	
	
	//set() et get() pour la nervosit�
	public void setNervosite(String parNervosite) throws ContextException {
		if ((parNervosite != NERVOSITE[0]) && (parNervosite != NERVOSITE[1]))
			throw new ContextException("Nervosite: " + NERVOSITE[0] + " or " + NERVOSITE[1]);
		nervosite = parNervosite;
	}
	
	public String getNervosite(){
		return nervosite;
	}
	
	//set() et get() pour l'acc�leration
	public void setAcceleration(String parAcceleration) throws ContextException{
		if ((parAcceleration != ACCELERATION[0]) && (parAcceleration != ACCELERATION[1]))
			throw new ContextException("Acceleration: " + ACCELERATION[0] + " or " + ACCELERATION[1]);
		acceleration = parAcceleration;
	}
	
	public String getAcceleration(){
		return acceleration;
	}
	
	//set() et get() pour l'expression de visage
	public void setExpression_de_visage(String parExpression_de_visage) throws ContextException {
		if ((parExpression_de_visage != EXPRESSION_DE_VISAGE[0]) && (parExpression_de_visage != EXPRESSION_DE_VISAGE[1]))
			throw new ContextException("Expression de visage: " + EXPRESSION_DE_VISAGE[0] + " or " + EXPRESSION_DE_VISAGE[1]);
		expression_de_visage = parExpression_de_visage; 
	}
		
	public String getExpression_de_visage(){
		return expression_de_visage;
	}
	
	 public void determineIndicateur_de_stress(){
		 
		 if (nervosite == NERVOSITE[0]){
			 if ((acceleration == ACCELERATION[0]) && (expression_de_visage == EXPRESSION_DE_VISAGE[0]))
				 indicateur_de_stress = INDICATEUR_DE_STRESS[0];
			 else
				 if ((acceleration == ACCELERATION[0]) && (expression_de_visage == EXPRESSION_DE_VISAGE[1]))
				 indicateur_de_stress = INDICATEUR_DE_STRESS[1];
				 else
					 if ((acceleration == ACCELERATION[1]) && (expression_de_visage == EXPRESSION_DE_VISAGE[0]))
						 indicateur_de_stress = INDICATEUR_DE_STRESS[1];
					 else 
						 indicateur_de_stress = INDICATEUR_DE_STRESS[2];
		 }//end if				
		 else if (nervosite == NERVOSITE[1]){
			 if ((acceleration == ACCELERATION[0]) && (expression_de_visage == EXPRESSION_DE_VISAGE[0]))
				 indicateur_de_stress = INDICATEUR_DE_STRESS[1];
			 else
				 if ((acceleration == ACCELERATION[0]) && (expression_de_visage == EXPRESSION_DE_VISAGE[1]))
				 indicateur_de_stress = INDICATEUR_DE_STRESS[2];
				 else
					 if ((acceleration == ACCELERATION[1]) && (expression_de_visage == EXPRESSION_DE_VISAGE[0]))
						 indicateur_de_stress = INDICATEUR_DE_STRESS[2];
					 else 
						 indicateur_de_stress = INDICATEUR_DE_STRESS[2];
		 }//end else
	 }//end method
	 
	 public void setIndicateur_de_stress(String parIndicateur_de_stress) throws ContextException {
		 if ((parIndicateur_de_stress != INDICATEUR_DE_STRESS[0]) && 
			 (parIndicateur_de_stress != INDICATEUR_DE_STRESS[1]) && (parIndicateur_de_stress != INDICATEUR_DE_STRESS[2]))
				throw new ContextException("Indicateur de stress: " + INDICATEUR_DE_STRESS[0] + " or " + INDICATEUR_DE_STRESS[1] + " or " + INDICATEUR_DE_STRESS[2]);
		 indicateur_de_stress = parIndicateur_de_stress;
	 }
	 
	 public String getIndicateur_de_stress() {
		 return indicateur_de_stress;
	 }
	 
	//set() et get() pour la detection de somnolence
	public void setDetection_somnolence(String parDetection_somnolence) throws ContextException {
		if ((parDetection_somnolence != DETECTION_SOMNOLENCE[0]) && (parDetection_somnolence  != DETECTION_SOMNOLENCE[1]))
			throw new ContextException("Detection somnolence: " + DETECTION_SOMNOLENCE[0] + " or " + DETECTION_SOMNOLENCE[1]);
		detection_somnolence = parDetection_somnolence;
	}
		
	public String getDetection_somnolence(){
		
		return detection_somnolence;
	}
		
	//set() et get() pour le mouvement de volant
	public void setMouvement_de_volant(String parMouvement_de_volant) throws ContextException {
		if ((parMouvement_de_volant != MOUVEMENT_DE_VOLANT[0]) && (parMouvement_de_volant != MOUVEMENT_DE_VOLANT[1]))
			throw new ContextException("Mouvement de volant: " + MOUVEMENT_DE_VOLANT[0] + " or " + MOUVEMENT_DE_VOLANT[1]);
		mouvement_de_volant = parMouvement_de_volant;
	}
		
	public String getMouvement_de_volant(){
		return mouvement_de_volant;
	}
		
	//set() et get() pour l'oscillation sur la voie
	public void setOscillation_sur_la_voie(String parOscillation_sur_la_voie) throws ContextException {
		if ((parOscillation_sur_la_voie != OSCILLATION_SUR_LA_VOIE[0]) && (parOscillation_sur_la_voie != OSCILLATION_SUR_LA_VOIE[1]))
			throw new ContextException("Oscillation sur la voie: " + OSCILLATION_SUR_LA_VOIE[0] + " or " + OSCILLATION_SUR_LA_VOIE[1]);
		oscillation_sur_la_voie = parOscillation_sur_la_voie; 
	}
			
	public String getOscillation_sur_la_voie(){
		return oscillation_sur_la_voie;
	}
	
	//set() et get() pour le mouvement de conducteur
	public void setMouvement_de_conducteur(String parMouvement_de_conducteur) throws ContextException {
		if ((parMouvement_de_conducteur != MOUVEMENT_DE_CONDUCTEUR[0]) && (parMouvement_de_conducteur != MOUVEMENT_DE_CONDUCTEUR[1]))
			throw new ContextException("Mouvement de conducteur: " + MOUVEMENT_DE_CONDUCTEUR[0] + " or " + MOUVEMENT_DE_CONDUCTEUR[1]);
		mouvement_de_conducteur = parMouvement_de_conducteur;
	}
				
	public String getMouvement_de_conducteur(){
		return mouvement_de_conducteur;
	}
		
	public void determineIndicateur_de_fatigue(){
		if ((detection_somnolence == DETECTION_SOMNOLENCE[0]) && (mouvement_de_volant == MOUVEMENT_DE_VOLANT[0])){
			if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
				indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[0];
			else 
				if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[1]))
					indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[1];
				else 
					if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
						indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[1];
					else 
						if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
						   indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[1];
		}
		else
			if ((detection_somnolence == DETECTION_SOMNOLENCE[0]) && (mouvement_de_volant == MOUVEMENT_DE_VOLANT[1])){
				if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
					indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[1];
				else 
					if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[1]))
						indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
					else 
						if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
							indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
						else 
							if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
							   indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
			}
			else
				if ((detection_somnolence == DETECTION_SOMNOLENCE[1]) && (mouvement_de_volant == MOUVEMENT_DE_VOLANT[0])){
					if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
						indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[1];
					else 
						if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[1]))
							indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
						else 
							if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
								indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
							else 
								if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
								   indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
				}
				else
					if ((detection_somnolence == DETECTION_SOMNOLENCE[1]) && (mouvement_de_volant == MOUVEMENT_DE_VOLANT[1])){
						if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
							indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
						else 
							if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[0]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[1]))
								indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
							else 
								if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
									indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
								else 
									if ((oscillation_sur_la_voie == OSCILLATION_SUR_LA_VOIE[1]) && (mouvement_de_conducteur == MOUVEMENT_DE_CONDUCTEUR[0]))
									   indicateur_de_fatigue = INDICATEUR_DE_FATIGUE[2];
					}//end cascaded if-then-else
				
	}//end method
	
	public void setIndicateur_de_fatigue(String parIndicateur_de_fatigue) throws ContextException {
		if ((parIndicateur_de_fatigue != INDICATEUR_DE_FATIGUE[0]) && (parIndicateur_de_fatigue != INDICATEUR_DE_FATIGUE[1]))
			throw new ContextException("Indicateur de fatigue: " + INDICATEUR_DE_FATIGUE[0] + " or " + INDICATEUR_DE_FATIGUE[1]);
		indicateur_de_fatigue = parIndicateur_de_fatigue;
	}
	
	public String getIndicateur_de_fatigue(){
		return indicateur_de_fatigue;
	}
	
	public void setRythme_cardiaque(String parRythme_cardiaque) throws ContextException {
		if ((parRythme_cardiaque != RYTHME_CARDIAQUE[0]) && (parRythme_cardiaque != RYTHME_CARDIAQUE[1]))
			throw new ContextException("Rythme cardiaque: " + RYTHME_CARDIAQUE[0] + " or " + RYTHME_CARDIAQUE[1]);
		rythme_cardiaque = parRythme_cardiaque;
	}
	
	public String getRythme_cardiaque(){
		return rythme_cardiaque;
	}	
		
	public void setGlycemie(String parGlycemie) throws ContextException {
		if ((parGlycemie != GLYCEMIE[0]) && (parGlycemie != GLYCEMIE[1]) && (parGlycemie != GLYCEMIE[2]))
			throw new ContextException("Glyc�mie: " + GLYCEMIE[0] + " or " + GLYCEMIE[1] + " or " + GLYCEMIE[2]);
		glycemie = parGlycemie;
	}
	
	public String getGlycemie(){
		return glycemie;
	}
	
	public void determineIndicateur_de_malaise(){
		if (rythme_cardiaque == RYTHME_CARDIAQUE[0]){
			if (glycemie == GLYCEMIE[0])
				indicateur_de_malaise = INDICATEUR_DE_MALAISE[0];
			else 
				if (glycemie == GLYCEMIE[1])
					indicateur_de_malaise = INDICATEUR_DE_MALAISE[1];
				else 
					if (glycemie == GLYCEMIE[1])
						indicateur_de_malaise = INDICATEUR_DE_MALAISE[2];
		}
		else 
			if (rythme_cardiaque == RYTHME_CARDIAQUE[1])
			{
				if (glycemie == GLYCEMIE[0])
					indicateur_de_malaise = INDICATEUR_DE_MALAISE[3];
				else 
					if (glycemie == GLYCEMIE[1])
						indicateur_de_malaise = INDICATEUR_DE_MALAISE[4];
					else 
						if (glycemie == GLYCEMIE[1])
							indicateur_de_malaise = INDICATEUR_DE_MALAISE[5];
			}
	}//end method
	
	
	public void setIndicateur_de_malaise(String parIndicateur_de_malaise) throws ContextException {
		if ((parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[0]) && (parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[1]) && 
			(parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[2]) && (parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[3]) && 
			(parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[4]) && (parIndicateur_de_malaise != INDICATEUR_DE_MALAISE[5]))
		  throw new ContextException("Indicateur de malaise: " + INDICATEUR_DE_MALAISE[0] + " or " + INDICATEUR_DE_MALAISE[1] +
				                      INDICATEUR_DE_MALAISE[2] + " or " + INDICATEUR_DE_MALAISE[3] + " or " +
				                      INDICATEUR_DE_MALAISE[4] + " or " + INDICATEUR_DE_MALAISE[5]);	
		indicateur_de_malaise = parIndicateur_de_malaise;
	}
	
	public String getIndicateur_de_malaise() {
		return indicateur_de_malaise;
	}
	
	public void setPresence_passagers(String parPresence_passagers) throws ContextException {
		if ((parPresence_passagers != PRESENCE_PASSAGERS[0]) && (parPresence_passagers != PRESENCE_PASSAGERS[1]))
		   throw new ContextException("Presence passagers: " + PRESENCE_PASSAGERS[0] + " or " + PRESENCE_PASSAGERS[1]);	
		presence_passagers = parPresence_passagers;
	}
	
	public String getPresence_passagers(){
		return presence_passagers;
	}
	
	public void setConversation_avec_Passagers(String parConversation_avec_passagers) throws ContextException {
		if ((parConversation_avec_passagers != CONVERSATION_AVEC_PASSAGERS[0]) && (parConversation_avec_passagers != CONVERSATION_AVEC_PASSAGERS[1]))
		   throw new ContextException("Conversation avec passagers: " + CONVERSATION_AVEC_PASSAGERS[0] + " or " + CONVERSATION_AVEC_PASSAGERS[1]);	
		conversation_avec_passagers = parConversation_avec_passagers;
	}
	
	public String getConversation_avec_passagers(){
		return conversation_avec_passagers;
	}
	
	public void setConversation_telephonique(String parConversation_telephonique) throws ContextException {
		if ((parConversation_telephonique != CONVERSATION_TELEPHONIQUE[0]) && (parConversation_telephonique != CONVERSATION_TELEPHONIQUE[1]))
		   throw new ContextException("Conversation t�l�phonique: " + CONVERSATION_TELEPHONIQUE[0] + " or " + CONVERSATION_TELEPHONIQUE[1]);	
		conversation_telephonique = parConversation_telephonique;
	}
	
	public String getConversation_telephonique(){
		return conversation_telephonique;
	}
	
	public void setPosition_des_mains(String parPosition_des_mains) throws ContextException {
		if ((parPosition_des_mains != POSITION_DES_MAINS[0]) && (parPosition_des_mains != POSITION_DES_MAINS[1]))
		   throw new ContextException("Position des mains: " + POSITION_DES_MAINS[0] + " or " + POSITION_DES_MAINS[1]);	
		position_des_mains = parPosition_des_mains;
	}
	
	public String getPosition_des_mains(){
		return position_des_mains;
	}
	
	public void setDirection_de_regard(String parDirection_de_regard) throws ContextException {
		if ((parDirection_de_regard != DIRECTION_DE_REGARD[0]) && (parDirection_de_regard != DIRECTION_DE_REGARD[1]) &&
		    (parDirection_de_regard != DIRECTION_DE_REGARD[2]) && (parDirection_de_regard != DIRECTION_DE_REGARD[3]) &&
		    (parDirection_de_regard != DIRECTION_DE_REGARD[4]) && (parDirection_de_regard != DIRECTION_DE_REGARD[5]))
		   throw new ContextException("Direction de regard: " + DIRECTION_DE_REGARD[0] + " or " + DIRECTION_DE_REGARD[1] + " or " +
				                       DIRECTION_DE_REGARD[2] + " or " + DIRECTION_DE_REGARD[3] + " or " + DIRECTION_DE_REGARD[4] + " or " + DIRECTION_DE_REGARD[5]);	
		direction_de_regard = parDirection_de_regard;
	}
	
	public String getDirection_de_regard(){
		return direction_de_regard;
	}
	
	public void setTemps_de_regard(String parTemps_de_regard) throws ContextException {
		if ((parTemps_de_regard != TEMPS_DE_REGARD[0]) && (parTemps_de_regard != TEMPS_DE_REGARD[1]) && (parTemps_de_regard != TEMPS_DE_REGARD[0]))
		   throw new ContextException("Temps de regard: " + TEMPS_DE_REGARD[0] + " or " + TEMPS_DE_REGARD[1] + " or " + TEMPS_DE_REGARD[2]);	
		temps_de_regard = parTemps_de_regard;
	}
	
	public String getTemps_de_regard(){
		return temps_de_regard;
	}
	
	public void determineIndicateur_attention_sur_la_conduite(){
		if (((presence_passagers == PRESENCE_PASSAGERS[1]) && (conversation_avec_passagers == CONVERSATION_AVEC_PASSAGERS[1])) //conducteur parle avec les passagers
			|| (conversation_telephonique == CONVERSATION_TELEPHONIQUE[1]) //conducetur parle au t�l�phone
			|| (position_des_mains == POSITION_DES_MAINS[1])               //les mains ne sont pas sur le volant
			|| ((direction_de_regard == DIRECTION_DE_REGARD[1]) && (temps_de_regard == TEMPS_DE_REGARD[0])) //conducteur regarde retroviseur exterieure gauche + temps de regard = en permanence
			|| ((direction_de_regard == DIRECTION_DE_REGARD[1]) && (temps_de_regard == TEMPS_DE_REGARD[1])) //conducteur regarde retroviseur exterieure gauche + temps de regard = soutenue
			|| ((direction_de_regard == DIRECTION_DE_REGARD[2]) && (temps_de_regard == TEMPS_DE_REGARD[0])) //conducteur regarde retroviseur interieure + temps de regard = en permanence
			|| ((direction_de_regard == DIRECTION_DE_REGARD[2]) && (temps_de_regard == TEMPS_DE_REGARD[1])) //conducteur regarde retroviseur interieure + temps de regard = soutenue
			|| ((direction_de_regard == DIRECTION_DE_REGARD[3]) && (temps_de_regard == TEMPS_DE_REGARD[0])) //conducteur regarde la console centrale + temps de regard = en permanence
			|| ((direction_de_regard == DIRECTION_DE_REGARD[3]) && (temps_de_regard == TEMPS_DE_REGARD[1])) //conducteur regarde la console centrale + temps de regard = soutenue
			|| ((direction_de_regard == DIRECTION_DE_REGARD[4]) && (temps_de_regard == TEMPS_DE_REGARD[0])) //conducteur regarde le pi�ton avant + temps de regard = en permanence
			|| ((direction_de_regard == DIRECTION_DE_REGARD[4]) && (temps_de_regard == TEMPS_DE_REGARD[1])) //conducteur regarde le pi�ton avant + temps de regard = soutenue
			|| ((direction_de_regard == DIRECTION_DE_REGARD[5]) && (temps_de_regard == TEMPS_DE_REGARD[0])) //conducteur regarde ailleurs + temps de regard = en permanence
			|| ((direction_de_regard == DIRECTION_DE_REGARD[5]) && (temps_de_regard == TEMPS_DE_REGARD[1]))) //conducteur regarde ailleurs + temps de regard = soutenue
			 indicateur_attention_sur_la_conduite = INDICATEUR_ATTENTION_SUR_LA_CONDUITE[1];
		else
			 indicateur_attention_sur_la_conduite = INDICATEUR_ATTENTION_SUR_LA_CONDUITE[0];
	}
	
	public void setIndicateur_attention_sur_la_conduite(String parIndicateur_attention_sur_la_conduite) throws ContextException {
		if ((parIndicateur_attention_sur_la_conduite != INDICATEUR_ATTENTION_SUR_LA_CONDUITE[0]) && 
				(parIndicateur_attention_sur_la_conduite != INDICATEUR_ATTENTION_SUR_LA_CONDUITE[1]))
			throw new ContextException("Indicateur attention sur la conduite: " + INDICATEUR_ATTENTION_SUR_LA_CONDUITE[0] + " or " + INDICATEUR_ATTENTION_SUR_LA_CONDUITE[1]);
		indicateur_attention_sur_la_conduite = parIndicateur_attention_sur_la_conduite;
	}
	
	public String getIndicateur_attention_sur_la_conduite() {
		return indicateur_attention_sur_la_conduite;
	}
	
}//end class
