package com.myapplication.boulangerie_serverRMI;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import com.myapplication.boulangerie.model.Categorie;
import com.myapplication.boulangerie.model.Ingredient;
import com.myapplication.boulangerie.model.MatierePremiere;
import com.myapplication.boulangerie.model.Produit;
import com.myapplication.boulangerie.model.Utilisateur;

public interface IBoulangerieRemote extends Remote{
	public List<Produit> getAllProduit () throws Exception, RemoteException;
	
	public Produit addProduit (Produit produit, int categorie_id) throws Exception, RemoteException;
	
	public Produit updateProduit (Produit produit, int id) throws Exception, RemoteException;
	
	public boolean deleteProduit ( int id) throws Exception, RemoteException;
	
	public Produit getProduitByID ( int id) throws Exception, RemoteException;
	
	public Produit getProduitByName ( String name) throws Exception, RemoteException;
	
	public List<Categorie> getAllCategorie () throws Exception, RemoteException;
	
	public Categorie addCategorie (Categorie categorie) throws Exception, RemoteException;
	
	public Categorie updateCategorie (Categorie categorie,  int id) throws Exception, RemoteException;
	
	public boolean deleteCategorie ( int id) throws Exception, RemoteException;
	
	public Categorie getCategorieByID ( int id) throws Exception, RemoteException;
	
	public Categorie getCategorieByName ( String name) throws Exception, RemoteException;
	
	public List<MatierePremiere> getAllMP () throws Exception, RemoteException;
	
	public MatierePremiere addMP (MatierePremiere mp) throws Exception, RemoteException;
	
	public MatierePremiere updateMP (MatierePremiere mp,  int id) throws Exception, RemoteException;
	
	public boolean deleteMP ( int id) throws Exception,RemoteException;
	
	public MatierePremiere getMPByID ( int id) throws Exception, RemoteException;
	
	public MatierePremiere getMPByName ( String name) throws Exception, RemoteException;
	
	public List<Ingredient> getAllIngredient () throws Exception, RemoteException;
	
	public Ingredient addIngredient (Ingredient ingredient) throws Exception , RemoteException;
	
	public Ingredient updateIngredient (Ingredient ingredient,  int id) throws Exception, RemoteException;
	
	public boolean deleteIngredient ( int id) throws Exception,RemoteException;
	
	public List<Ingredient> getIngredientByProduitID ( int id) throws Exception,RemoteException;

	public List<Utilisateur> getAllUtilisateur () throws Exception,RemoteException;
	
	public Utilisateur addUtilisateur (Utilisateur user) throws Exception, RemoteException;
	
	public Utilisateur updateUtilisateur (Utilisateur user,  int id) throws Exception, RemoteException;
	
	public boolean deleteUtilisateur ( int id) throws Exception, RemoteException;
	
	public Utilisateur getUtilisateurByID ( int id) throws Exception, RemoteException;
	
	public Utilisateur getUtilisateurByName ( String name) throws Exception, RemoteException;

	public List<Produit> getListeProduitsCategorieByID ( int id) throws Exception, RemoteException;
	
	public Ingredient getIngredientByID(int id) throws Exception, RemoteException;

}
