package com.myapplication.boulangerie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.myapplication.boulangerie.model.Ingredient;

public class IngredientService {


	// Demarer le systeme
	EntityManagerFactory entityManagerFactory = null;
	EntityManager entityManager = null;
	List<Ingredient> listIngredient = new ArrayList<>();;

	// Methode pour ajouter nouveauingredient dans database------------------------CREATE--------
	public Ingredient insertIngredient(Ingredient ingredient) throws Exception{

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

			// Synchronyser valeur au donnee pour ajouter nouveauingredient
			entityManager.persist(ingredient);

			// finir la transaction
			trans.commit();

			// Afficher nouveauingredient
			System.out.println("Cree nouveau "+ ingredient);

			return ingredient;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}


	// Methode pour afficher list desingredients----------------------------LISTE DES INGREDIENTS----------
	public List<Ingredient> getAllIngredient() throws Exception {
		// Declarer list des variablesingredients

		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); 
			System.out.println("Get connect");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Afficher message
			System.out.println("List des ingredients");
			// Envoyer requete pour afficher et get la table de donnees (ou bien list des objets): Attention nom de table dans Querry est la Class dans JAVA
			listIngredient = entityManager.createQuery("from Ingredient", Ingredient.class).getResultList();

			// Afficher list desingredients dans log console
			for (Ingredient ingredient2 : listIngredient) {
				System.out.println(ingredient2);
			}

			return listIngredient;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}
	}

	// Methode pour supprimer 1ingredient----------------------------DELETE 1 INGREDIENT BY ID----------
	public void removeIngredientByID(int ingredient_id) throws Exception{
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel 1 seul objet au id = 1
			Ingredient ingredient = entityManager.find(Ingredient.class,ingredient_id );

			// Transaction des donnes -> pour Modifier les objets 
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin(); //commencer la transaction


			// Supprimer 1 object
			entityManager.remove(ingredient );
			System.out.println("Supprimee " +ingredient);

			trans.commit();// finir la transaction

		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}


	// Methode pour modifieringredient----------------------------UPDATE INGREDIENT BY ID----------
	public Ingredient updateIngredient(Ingredient ingredient, int ingredient_id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");

			// Appel 1 seul objet au id 
			Ingredient ingredientDB = entityManager.find(Ingredient.class,ingredient_id);
			System.out.println("afficher "+ingredientDB);

			// Transaction des donnes -> pour Modifier les objets 
			EntityTransaction trans = entityManager.getTransaction();
			trans.begin(); //commencer la transaction

			// Modifier object
			ingredientDB.setMp_id(ingredient.getMp_id());
			ingredientDB.setIngredient_quantite(ingredient.getIngredient_quantite());
			ingredientDB.setIngredient_unite(ingredient.getIngredient_unite());


			// Synchronyser valeur au donnee 
			entityManager.persist(ingredientDB);
			System.out.println("Modifier ingredient id: " +ingredient_id+" est "+ingredientDB);

			// finir la transaction
			trans.commit();
			return ingredientDB;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}
	}

	// Methode pour chercher list ingredients d'une produit------------------------FIND INGREDIENT BY PRODUIT ID----------
	public List<Ingredient> findByProduitId(int produit_id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");


			// Appel 1 seul objet au id
			listIngredient = entityManager.createQuery("From Ingredient where produit_id = "+produit_id,Ingredient.class).getResultList();
			System.out.println("afficher liste des ingredient du produit id " + produit_id);

			// Afficher list desingredients dans log console
			for (Ingredient ingredient2 : listIngredient) {
				System.out.println(ingredient2);
			}


			return listIngredient;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}

	// Methode pour chercher 1 ingredient----------------------------FIND 1 INGREDIENT BY ID----------
	public Ingredient findById(int id) throws Exception {
		try {
			// Appel fichier persistence.xml
			entityManagerFactory = Persistence.createEntityManagerFactory("app-DB"); // "app-DB" est nom de fichier persistence
			System.out.println("Get connect to database...");
			// Demander la connection au DB
			entityManager = entityManagerFactory.createEntityManager();
			System.out.println("Connected");


			// Appel 1 seul objet au id
			Ingredient ingredient = entityManager.find(Ingredient.class,id);
			System.out.println("afficher "+ingredient);


			return ingredient;
		}
		finally {
			// fermer la connection au DB
			if (entityManager!=null) { entityManager.close();}
			if (entityManagerFactory!=null) {entityManagerFactory.close();
			System.out.println("Disconnected");}
		}

	}



}
