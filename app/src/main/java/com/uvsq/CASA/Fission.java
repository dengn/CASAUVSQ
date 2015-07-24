package com.uvsq.CASA;




public class Fission {
	
	final String[] typeDeMessage = {"User request info","Notification", "Alerte"};
	final String[] categorie = {"Danger", "Capacit� physique", "Comportement"};
	final String[] reponseALaQuestion = {"Oui", "Non"};
	
	Historique historique = new Historique();
	
	public void informerConducteur_DistanceAvecStop() {
		if (GlobalData.informerConducteur_distance_avec_Stop)
			System.out.println("MESSAGE (Audio & Visual): Get ready to stop in " + GlobalData.distance_avec_Stop + " metres" + "\n");
	}

	public void informerConducteur_DistanceAvecPassagePieton() {
		if (GlobalData.informerConducteur_distance_avec_PassagePieton)
			System.out.println("MESSAGE (Audio & Visual): Slow down! Pedestrians in " + GlobalData.distance_avec_PassagePieton + " metres" + "\n");
	}
	
	public void informerConducteur_DistanceAvecIntersection() {
		if (GlobalData.informerConducteur_distance_avec_Intersection)
			System.out.println("MESSAGE (Audio & Visual): Intersection in " + GlobalData.distance_avec_Intersection + " metres" + "\n");
	}
	
	public void informerConducteur_OubliArreterAuStop() {
		if (GlobalData.informerConducteur_oubli_arreter_au_Stop)
		{
			System.out.println("MESSAGE (Audio & Visual): Danger! You failed to stop in Stop Sign" + "\n");
			historique.nombre_Oubli_arreter_au_Stop ++;
			GlobalData.drivingScore --;
		}	
	}
	
	public void informerConducteur_OubliArreterAuPassagePieton() {
		if (GlobalData.informerConducteur_oubli_arreter_au_PassagePieton)
		{
			System.out.println("MESSAGE (Audio & Visual): Danger! You failed to stop in pedestrian lane" + "\n");
			historique.nombre_Oubli_arreter_au_PassagePieton ++;
			GlobalData.drivingScore --;
		}	
	}
	
	public void informerConducteur_OubliClignotantDroite() {
		if (GlobalData.informerConducteur_oubli_clignotant_droite)
		{
			System.out.println("MESSAGE (Audio or Visual): Warning: You forgot to turn signal going right" + "\n");
			historique.nombre_Oubli_clignoter_droite ++;
			GlobalData.drivingScore --;
		}	
	}
	
	public void informerConducteur_OubliClignotantGauche() {
		if (GlobalData.informerConducteur_oubli_clignotant_gauche)
		{
			System.out.println("MESSAGE (Audio or Visual): Warning: You forgot to turn signal going left" + "\n");
			historique.nombre_Oubli_clignoter_gauche ++;
			GlobalData.drivingScore --;
		}	
	}
	
	public void informerConducteur_VitesseExcessiveSurBrouillard() {
		if (GlobalData.informerConducteur_vitesseExcessive_sur_le_brouillard)
		{
			System.out.println("MESSAGE (Audio & Visual): Slow Down! Your speed is excessive in a foggy area" + "\n");
			historique.nombre_vitesseExcessive_sur_le_brouillard ++;
			GlobalData.drivingScore --;
		}
	}
	
	public void informerConducteur_VitesseDangereuseSurBrouillard() {
		if (GlobalData.informerConducteur_vitesseDangereuse_sur_le_brouillard)
		{
			System.out.println("MESSAGE (Audio & Visual): Slow Down! Your speed is dangerous in a foggy area" + "\n");
			historique.nombre_vitesseDangereuse_sur_le_brouillard ++;
			GlobalData.drivingScore --;
		}
	}
	
	public void informerConducteur_NonRespectDistanceDeSecurite() {
		if (!GlobalData.respectDistance_de_securite) {
			System.out.println("MESSAGE (Audio or Visual): Warning: You failed to maintain safety distance between vehicles" + "\n");
			historique.nombre_NonRespect_distance_de_securite ++;
			GlobalData.drivingScore --;
		}	
	}
	
	public void informerConducteur_EchecActivationPharesBrouillard() {
		if (!GlobalData.activationPharesBrouillard) {
			System.out.println("MESSAGE (Audio or Visual): Warning: You failed to turn on fog light" + "\n");
			historique.nombre_EchecActivation_phares_de_brouillard ++;
			GlobalData.drivingScore --;
		}
	}
	
	public void informerConducteur_VitesseExcessiveFaceObstacle() {
		if (GlobalData.informerConducteur_vitesseExcessive_face_a_lobstacle)
		{
			System.out.println("MESSAGE (Audio and Visual): Slow Down! Your speed is excessive in dealing with an obstacle.");
			historique.nombre_vitesseExcessive_face_a_lobstacle ++;
			GlobalData.drivingScore --;
		}
	}
	
	public void informerConducteur_VitesseDangereuseFaceObstacle() {
		if (GlobalData.informerConducteur_vitesseDangereuse_face_a_lobstacle)
		{
			System.out.println("MESSAGE (Audio and Visual): Slow Down! Your speed is dangerous in dealing with an obstacle.");
			historique.nombre_vitesseDangereuse_face_a_lobstacle ++;
			GlobalData.drivingScore --;
		}
	}
	
	public void informerConducteur_trafficPasLibre_VoieOpposee() {
		if (GlobalData.informerConducteur_trafficPasLibre_VoieOpposee)
		{
			System.out.println("MESSAGE (Audio & Visual): Attention! Lane in the opposite direction is not free");
			GlobalData.delay();
		}
	}
	
	public void informerConducter_attentionSurLaConduite_enBaisse() {
		if (GlobalData.attentionConducteur_en_baisse) {
			System.out.println("Danger! Driver not focused on driving ");
			System.out.println("-------------------------------------------------------");
			System.out.println("1. Stop talking with passengers or on the phone.");
			System.out.println("2. Keep your hands on the steering wheel.");
			System.out.println("3. Keep your eyes on driving.");
			System.out.println("4. Remain attentive and vigilant at all times.");
			historique.nombre_baisseAttention_sur_la_conduite ++;
			GlobalData.drivingScore --;
		}
			
	}
	
	
	
}//end class
