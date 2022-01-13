package com.myapplication.boulangerie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.myapplication.boulangerie.model.Utilisateur;

public class UtilisateurService {


	
	// Demarer le systeme
			EntityManagerFactory entityManagerFactory = null;
			EntityManager entityManager = null;		
			
			
			// Methode pour ajouter nouveau user dans database------------------------CREATE--------
			public Utilisateur insertUtilisateur(Utilisateur user) throws Exception{

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
					
					
					// Synchronyser valeur au donnee pour ajouter nouveau user
					entityManager.persist(user);

					// finir la transaction
					trans.commit();

					// Afficher nouveau user
					System.out.println("Cree nouveau "+user);

					return user;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}


			// Methode pour afficher list des users----------------------------LIST PPRODUIT----------
			public List<Utilisateur> getAllUtilisateur() throws Exception {
				// Declarer list des variables users
				List<Utilisateur> listUtilisateur = new ArrayList<>();
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
					System.out.println("Get connect");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Afficher message
					System.out.println("List des users");
					// Envoyer requete pour afficher et get la table de donnees (ou bien list des objets): Attention nom de table dans Querry est la Class dans JAVA
					listUtilisateur = entityManager.createQuery("from Utilisateur", Utilisateur.class).getResultList();

					// Afficher list des users dans log console
					for (Utilisateur user2 : listUtilisateur) {
						System.out.println(user2);
					}

					return listUtilisateur;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}

			// Methode pour supprimer 1 user----------------------------DELETE 1 USER BY ID----------
			public void removeUtilisateur(int id) throws Exception{
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id = 1
					Utilisateur user = entityManager.find(Utilisateur.class,id );

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction


					// Supprimer 1 object
					entityManager.remove( user );
					System.out.println("Supprimee " + user);

					trans.commit();// finir la transaction

				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}
			
			
			// Methode pour modifier user----------------------------UPDATE USER BY ID----------
			public Utilisateur updateUtilisateur(Utilisateur user, int id) throws Exception {
				try {
					
					
					
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id 
						Utilisateur userDB = entityManager.find(Utilisateur.class,id);
						System.out.println("afficher "+userDB);

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction

					// Modifier object
					userDB.setUser_nom(user.getUser_nom());
					userDB.setUser_password(user.getUser_password());
					
					// Synchronyser valeur au donnee 
					entityManager.persist(userDB);
					System.out.println("Modifier user id: " +id+" est "+ userDB);
					
					// finir la transaction
					trans.commit();
					return userDB;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}
			
			// Methode pour chercher 1 user----------------------------FIND USER BY ID----------
			public Utilisateur findById(int id) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");
					
					
					// Appel 1 seul objet au id
					Utilisateur user = entityManager.find(Utilisateur.class,id);
					System.out.println("afficher "+user);
					
					
					return user;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
			
			// Methode pour chercher 1 user----------------------------FIND USER BY NOM----------
			
			public Utilisateur findByName(String name) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au nom
					Utilisateur user = entityManager.createQuery("from Utilisateur where user_nom = '"+name+"'",Utilisateur.class).getSingleResult();
					System.out.println("afficher "+user);
					
			
					return user;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
}
