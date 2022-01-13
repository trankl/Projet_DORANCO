package com.myapplication.boulangerie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.myapplication.boulangerie.model.MatierePremiere;


public class MPService {

	
	// Demarer le systeme
			EntityManagerFactory entityManagerFactory = null;
			EntityManager entityManager = null;

			// Methode pour ajouter nouveau matierePremiere dans database------------------------CREATE--------
			public MatierePremiere insertMatierePremiere(MatierePremiere matierePremiere) throws Exception{

				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
					System.out.println("Get connect");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");
					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					//commencer la transaction
					trans.begin(); 
					
					// Synchronyser valeur au donnee pour ajouter nouveau matierePremiere
					entityManager.persist(matierePremiere);

					// finir la transaction
					trans.commit();

					// Afficher nouveau matierePremiere
					System.out.println("Cree nouveau "+matierePremiere);

					return matierePremiere;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}


			// Methode pour afficher list des matierePremieres----------------------------LIST PPRODUIT----------
			public List<MatierePremiere> getAllMatierePremiere() throws Exception {
				// Declarer list des variables matierePremieres
				List<MatierePremiere> listMatierePremiere = new ArrayList<>();
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
					System.out.println("Get connect");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Afficher message
					System.out.println("List des matierePremieres");
					// Envoyer requete pour afficher et get la table de donnees (ou bien list des objets): Attention nom de table dans Querry est la Class dans JAVA
					listMatierePremiere = entityManager.createQuery("from MatierePremiere", MatierePremiere.class).getResultList();

					// Afficher list des matierePremieres dans log console
					for (MatierePremiere matierePremiere2 : listMatierePremiere) {
						System.out.println(matierePremiere2);
					}

					return listMatierePremiere;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}

			// Methode pour supprimer 1 matierePremiere----------------------------DELETE 1 PPRODUIT BY ID----------
			public void removeMatierePremiere(int id) throws Exception{
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id = 1
					MatierePremiere matierePremiere = entityManager.find(MatierePremiere.class,id );

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction


					// Supprimer 1 object
					entityManager.remove( matierePremiere );
					System.out.println("Supprimee " + matierePremiere);

					trans.commit();// finir la transaction

				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}
			
			
			// Methode pour modifier matierePremiere----------------------------UPDATE PPRODUIT BY ID----------
			public MatierePremiere updateMatierePremiere(MatierePremiere matierePremiere, int id) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id 
						MatierePremiere matierePremiereDB = entityManager.find(MatierePremiere.class,id);
						System.out.println("afficher "+matierePremiereDB);

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction

					// Modifier object
					matierePremiereDB.setMp_nom(matierePremiere.getMp_nom());
					matierePremiereDB.setMp_prix(matierePremiere.getMp_prix());
					matierePremiereDB.setMp_quantite(matierePremiere.getMp_quantite());
					matierePremiereDB.setMp_unite(matierePremiere.getMp_unite());
					matierePremiereDB.setMp_marque(matierePremiere.getMp_marque());
									
					// Synchronyser valeur au donnee 
					entityManager.persist(matierePremiereDB);
					System.out.println("Modifier matierePremiere id: " +id+" est "+ matierePremiereDB);
					
					// finir la transaction
					trans.commit();
					return matierePremiereDB;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}
			
			// Methode pour chercher 1 matierePremiere----------------------------FIND PPRODUIT BY ID----------
			public MatierePremiere findById(int id) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");
					
					
					// Appel 1 seul objet au id
					MatierePremiere matierePremiere = entityManager.find(MatierePremiere.class,id);
					System.out.println("afficher "+matierePremiere);
					
					/*
					// Afficher ses instruments 
					System.out.println("Il joue les instruments :");
					List<Instrument> matierePremiereInstruments = matierePremiere.getInstruments();
					for (Instrument instrument : matierePremiereInstruments) {
						System.out.println(instrument);
					}
					
					// Afficher ses concerts
					System.out.println("Il joue au concert :");
					List<Concert> matierePremiereConcerts = matierePremiere.getConcerts();
					for (Concert concert : matierePremiereConcerts) {
						System.out.println(concert);
					}
					*/
					
					return matierePremiere;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
			
			// Methode pour chercher 1 matierePremiere----------------------------FIND PPRODUIT BY NOM----------
			
			public MatierePremiere findByName(String name) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id
					MatierePremiere matierePremiere = entityManager.find(MatierePremiere.class,name);
					System.out.println("afficher "+matierePremiere);
					
					return matierePremiere;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
}
