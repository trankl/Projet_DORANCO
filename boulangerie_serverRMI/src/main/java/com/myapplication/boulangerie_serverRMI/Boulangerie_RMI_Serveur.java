package com.myapplication.boulangerie_serverRMI;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Boulangerie_RMI_Serveur {

	public static void main(String[] args) {
		try {
			System.out.println("Debut du RMI serveur");
			// Il faut demarrer l'annuaire pour la gestionnaire de l'annuaire avec le port 1099
			LocateRegistry.createRegistry(1099);
			
			// Creer objet distant service
			BoulangerieService boulangerie = new BoulangerieService();
			
			// Afficher reference d'objet
			System.out.println("Reference de l'objet: " + boulangerie.toString());
			
			// Connect a l’annuaire pour publier la reference d'objet dans l'annuaire
			Naming.rebind("rmi://localhost:1099/BOULANGERIE", boulangerie);
			
		} catch (Exception e) {
			
			e.printStackTrace();
			System.out.println("ERREUR: " + e.toString());
		}
	}

}
