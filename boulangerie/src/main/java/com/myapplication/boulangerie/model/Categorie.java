package com.myapplication.boulangerie.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.json.bind.annotation.JsonbTransient;

@Entity
public class Categorie  implements Serializable{

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY )
	private int categorie_id;
	private String categorie_nom;

	// Utilisation Hibernate ONE-TO-MANY: 1 categorie a plusieurs produits
	// @JsonManagedReference is the forward part of reference which gets serialized normally.
	@JsonManagedReference
	// By default, JSONB ignores properties with a non public access. All public properties - either public fields or non public fields with public getters are serialized into JSON text. Class properties annotated with @JsonbTransient annotation are ignored by JSON Binding engine.
	@JsonbTransient
	@OneToMany( targetEntity=Produit.class, mappedBy="produitCategorie", cascade = CascadeType.ALL )
	private List<Produit> produits = new ArrayList<>();


	// initialisation constructeur
	public Categorie() {
		this.categorie_nom = "inconnue";
	}
	//Constructor avec 1 parametres
	public Categorie( String categorie_nom) {
		this.categorie_nom = categorie_nom;
	}
	@Override
	public String toString() {
		return "Categorie [categorie_id=" + categorie_id + ", categorie_nom=" + categorie_nom + "]";
	}
	public String getCategorie_nom() {
		return categorie_nom;
	}
	public void setCategorie_nom(String categorie_nom) {
		this.categorie_nom = categorie_nom;
	}
	public int getCategorie_id() {
		return categorie_id;
	}
	public List<Produit> getProduits() {
		return produits;
	}
	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}


}
