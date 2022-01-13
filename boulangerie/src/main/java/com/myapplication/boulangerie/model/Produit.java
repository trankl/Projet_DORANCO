package com.myapplication.boulangerie.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.json.bind.annotation.JsonbTransient;

@Entity
public class Produit implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY )
	private int produit_id;
	private String produit_nom; 
	private double produit_prix; 
	private int produit_quantite;
	
	// Utilisation Hibernate MANY-TO-MANY : 1 produit peut avoir plusieurs mp et 1 mp peut etre dans plusieurs produits
		@ManyToMany(fetch = FetchType.LAZY)
		@JsonbTransient
		@JoinTable(name="ingredient",
		joinColumns = @JoinColumn(name="produit_id"),
		inverseJoinColumns = @JoinColumn (name="mp_id"))
		private List<MatierePremiere> listeMPs;
	
	// Many to One: 1 Categorie a plusieur produit mais 1 produit peut etre dans 1 seul categorie
		 @JsonBackReference
		 @JsonbTransient
		 @ManyToOne @JoinColumn(name="categorie_id", nullable=false)
		 private Categorie produitCategorie;
	
	// initialisation constructeur
	public Produit() {
		this.produit_nom = "inconnue";
		this.produit_prix = 0.0;
		this.produit_quantite = 0;
		
	}

	//Constructor avec 3 parametres
	public Produit(String produit_nom, double produit_prix, int produit_quantite) {
		this.produit_nom = produit_nom;
		this.produit_prix = produit_prix;
		this.produit_quantite = produit_quantite;
	}
	
	
	//Constructor avec 4 parametres
	public Produit(String produit_nom, double produit_prix, int produit_quantite,
		 Categorie produitCategorie) {
		super();
		this.produit_nom = produit_nom;
		this.produit_prix = produit_prix;
		this.produit_quantite = produit_quantite;
		this.produitCategorie = produitCategorie;
	}

	@Override
	public String toString() {
		return "Produit [produit_id=" + produit_id + ", produit_nom=" + produit_nom + ", produit_prix=" + produit_prix
				+ ", produit_quantite=" + produit_quantite + "]";
	}

	public String 	getProduit_nom() 							{return produit_nom;}

	public void 	setProduit_nom(String produit_nom) 			{this.produit_nom = produit_nom;}
	
	public double 	getProduit_prix() 							{return produit_prix;}

	public void 	setProduit_prix(double produit_prix)		{this.produit_prix = produit_prix;}

	public int 		getProduit_quantite() 						{return produit_quantite;}

	public void 	setProduit_quantite(int produit_quantite) 	{this.produit_quantite = produit_quantite;}

	public int 		getProduit_id() 							{return produit_id;}

	public List<MatierePremiere> 	getListeMPs() 								{return listeMPs;}

	public void 					setListeMPs(List<MatierePremiere> listeMPs) {this.listeMPs = listeMPs;}

	public Categorie 	getProduitCategorie() 							{return produitCategorie;}

	public void 		setProduitCategorie(Categorie produitCategorie) {this.produitCategorie = produitCategorie;}
	
	

}
