package com.myapplication.boulangerie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.myapplication.boulangerie.model.Produit;


public class ProduitService {

	CategorieService categorieService;

	// Demarer le systeme
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;		


	// Methode pour ajouter nouveau produit dans database------------------------CREATE--------
	public Produit insertProduit(Produit produit) throws Exception{

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



			// Synchronyser valeur au donnee pour ajouter nouveau produit
			entityManager.persist(produit);

			// finir la transaction
			trans.commit();

			// Afficher nouveau produit
			System.out.println("Cree nouveau "+produit);

			return produit;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}


	// Methode pour afficher list des produits----------------------------LIST PPRODUIT----------
	public List<Produit> getAllProduit() throws Exception {
		// Declarer list des variables produits
		List<Produit> listProduit = new ArrayList<>();
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
			System.out.println("Get connect");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Afficher message
			System.out.println("List des produits");
			// Envoyer requete pour afficher et get la table de donnees (ou bien list des objets): Attention nom de table dans Querry est la Class dans JAVA
			listProduit = entityManager.createQuery("from Produit", Produit.class).getResultList();

			// Afficher list des produits dans log console
			for (Produit produit2 : listProduit) {
				System.out.println(produit2);
			}

			return listProduit;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}
	}

	// Methode pour supprimer 1 produit----------------------------DELETE 1 PPRODUIT BY ID----------
	public void removeProduit(int id) throws Exception{
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel 1 seul objet au id = 1
			Produit produit = entityManager.find(Produit.class,id );

			// Transaction des donnes -> pour Modifier les objets 
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin(); //commencer la transaction


			// Supprimer 1 object
			entityManager.remove( produit );
			System.out.println("Supprimee " + produit);

			trans.commit();// finir la transaction

		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}


	// Methode pour modifier produit----------------------------UPDATE PRODUIT BY ID----------
	public Produit updateProduit(Produit produit, int id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel 1 seul objet au id 
			Produit produitDB = entityManager.find(Produit.class,id);
			System.out.println("afficher "+produitDB);

			// Transaction des donnes -> pour Modifier les objets 
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin(); //commencer la transaction

			// Modifier object
			produitDB.setProduit_nom(produit.getProduit_nom());
			produitDB.setProduit_prix(produit.getProduit_prix());
			produitDB.setProduit_quantite(produit.getProduit_quantite());


			// Synchronyser valeur au donnee 
			entityManager.persist(produitDB);
			System.out.println("Modifier produit id: " +id+" est "+ produitDB);

			// finir la transaction
			trans.commit();
			return produitDB;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}
	}

	// Methode pour chercher 1 produit----------------------------FIND PRODUIT BY ID----------
	public Produit findById(int id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");


			// Appel 1 seul objet au id
			Produit produit = entityManager.find(Produit.class,id);
			System.out.println("afficher "+produit);


			return produit;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}

	// Methode pour chercher 1 produit----------------------------FIND PRODUIT BY NOM----------

	public Produit findByName(String name) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel 1 seul objet au id
			Produit produit = entityManager.createQuery("from Produit where produit_nom = '"+name+"'",Produit.class).getSingleResult();
			System.out.println("afficher "+produit);

			return produit;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}

	// Methode pour chercher liste des produits d'une categorie----------------------------FIND LIST PRODUIT BY CATEGORIE_ID----------

	public List<Produit> findListProduitByCategorieID(int categorie_id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel liste des objets au id
			List<Produit> listProduit = entityManager.createQuery("from Produit where categorie_id = '"+categorie_id+"'",Produit.class).getResultList();
			System.out.println("afficher "+listProduit);

			return listProduit;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}
}
