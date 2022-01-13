package com.myapplication.boulangerie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.myapplication.boulangerie.model.Categorie;
import com.myapplication.boulangerie.model.Produit;


public class CategorieService {

	
	// Demarer le systeme
			EntityManagerFactory entityManagerFactory = null;
			EntityManager entityManager = null;

			// Methode pour ajouter nouveaucategorie dans database------------------------CREATE--------
			public Categorie insertCategorie(Categorie categorie) throws Exception{

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
					
					// Synchronyser valeur au donnee pour ajouter nouveaucategorie
					entityManager.persist(categorie);

					// finir la transaction
					trans.commit();

					// Afficher nouveaucategorie
					System.out.println("Cree nouveau "+ categorie);

					return categorie;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}


			// Methode pour afficher list descategories----------------------------LIST PPRODUIT----------
			public List<Categorie> getAllCategorie() throws Exception {
				// Declarer list des variablescategories
				List<Categorie> listCategorie = new ArrayList<>();
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
					System.out.println("Get connect");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Afficher message
					System.out.println("List des categories");
					// Envoyer requete pour afficher et get la table de donnees (ou bien list des objets): Attention nom de table dans Querry est la Class dans JAVA
					listCategorie = entityManager.createQuery("from Categorie", Categorie.class).getResultList();

					// Afficher list descategories dans log console
					for (Categorie categorie2 : listCategorie) {
						System.out.println(categorie2);
					}

					return listCategorie;
				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}

			// Methode pour supprimer 1categorie----------------------------DELETE 1 PPRODUIT BY ID----------
			public void removeCategorie(int id) throws Exception{
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id = 1
					Categorie categorie = entityManager.find(Categorie.class,id );

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction


					// Supprimer 1 object
					entityManager.remove(categorie );
					System.out.println("Supprimee " +categorie);

					trans.commit();// finir la transaction

				}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}

			}
			
			
			// Methode pour modifiercategorie----------------------------UPDATE PPRODUIT BY ID----------
			public Categorie updateCategorie(Categorie categorie, int id) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");

					// Appel 1 seul objet au id 
						Categorie categorieDB = entityManager.find(Categorie.class,id);
						System.out.println("afficher "+categorieDB);

					// Transaction des donnes -> pour Modifier les objets 
					EntityTransaction trans = entityManager.getTransaction();
					trans.begin(); //commencer la transaction

					// Modifier object
					categorieDB.setCategorie_nom(categorie.getCategorie_nom());
			
									
					// Synchronyser valeur au donnee 
					entityManager.persist(categorieDB);
					System.out.println("Modifiercategorie id: " +id+" est "+categorieDB);
					
					// finir la transaction
					trans.commit();
					return categorieDB;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
			}
			
			// Methode pour chercher 1categorie----------------------------FIND PPRODUIT BY ID----------
			public Categorie findById(int id) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");
					
					
					// Appel 1 seul objet au id
					Categorie categorie = entityManager.find(Categorie.class,id);
					System.out.println("afficher "+categorie);
					
					
					// Afficher sa liste des produits
					System.out.println("Liste des produits dans ce categorie :");
					List<Produit>categorieProduits =categorie.getProduits();
					for (Produit prod :categorieProduits) {
						System.out.println(prod);
					}
					
					
					return categorie;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
			
			// Methode pour chercher 1categorie----------------------------FIND PPRODUIT BY NOM----------
			
			public Categorie findByName(String name) throws Exception {
				try {
					// Appel fichier persistence.xml
					entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
					System.out.println("Get connect to database...");
					// Demander la connection au DB
					entityManager = entityManagerFactory.createEntityManager();
					System.out.println("Connected");
					// Creer 1 requete pour chercher Categorie par categorie_nom
					//Query query = entityManager.createQuery("SELECT * FROM Categorie Where categorie_nom ="+name, Categorie.class);
				
					// mettre resultal à objet type Categorie
					Categorie categorie = entityManager.createQuery("FROM Categorie Where categorie_nom = '"+name+"'", Categorie.class).getSingleResult();
					System.out.println("afficher "+ categorie);
					
					return categorie;
			}
				finally {
					// fermer la connection au DB
					if (entityManager!=null) { entityManager.close();}
					if (entityManagerFactory!=null) {entityManagerFactory.close();
					System.out.println("Disconnected");}
				}
				
			}
			
			
				
	
}
