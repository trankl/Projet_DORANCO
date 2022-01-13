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
import javax.persistence.Table;

import jakarta.json.bind.annotation.JsonbTransient;

@Entity
@Table(name = "matiere_premiere")
public class MatierePremiere  implements Serializable{
	
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY )
	private int mp_id;
	private String mp_nom;
	private Double mp_prix;
	private int mp_quantite;
	private String mp_unite;
	private String mp_marque;
	
	// Utilisation Hibernate MANY-TO-MANY : 1 mp peut etre dans plusieurs produits et 1 produit peut avoir plusieurs mp
		 @ManyToMany(fetch = FetchType.LAZY)
		 @JsonbTransient
		 @JoinTable(name="ingredient", 
		 joinColumns = @JoinColumn(name="mp_id"), 
		 inverseJoinColumns = @JoinColumn (name="produit_id"))
		 private List<Produit> listeProduits;
	
	
	// initialisation constructeur
	public MatierePremiere() {
		super();
		this.mp_nom = "inconnue";
		this.mp_prix= 0.0;
		this.mp_quantite = 0;
		this.mp_unite = "inconnue";
		this.mp_marque = "inconnue";
	}

	
	@Override
	public String toString() {
		return "MatierePremiere [mp_id=" + mp_id + ", mp_nom=" + mp_nom + ", mp_prix=" + mp_prix + ", mp_quantite="
				+ mp_quantite + ", mp_unite=" + mp_unite + ", mp_marque=" + mp_marque + "]";
	}


	//Constructor avec 5 parametres
	public MatierePremiere(String mp_nom, Double mp_prix, int mp_quantite, String mp_unite, String mp_marque) {
		super();
		this.mp_nom = mp_nom;
		this.mp_prix = mp_prix;
		this.mp_quantite = mp_quantite;
		this.mp_unite = mp_unite;
		this.mp_marque = mp_marque;
	}

	// Methode pour modifier quantite du MP quand on ajouter ingredient du produit 
	public int updateQuantiteDuMP(int valeurAReduit) {
		this.mp_quantite -= valeurAReduit;
		return this.mp_quantite;
	}
	
	
	// Getter et setter
	public String getMp_nom() {return mp_nom;}

	public void setMp_nom(String mp_nom) {this.mp_nom = mp_nom;}

	public int getMp_quantite() {return mp_quantite;}

	public void setMp_quantite(int mp_quantite) {this.mp_quantite = mp_quantite;}

	public String getMp_unite() {return mp_unite;}

	public void setMp_unite(String mp_unite) {this.mp_unite = mp_unite;}

	public String getMp_marque() {return mp_marque;}

	public void setMp_marque(String mp_marque) {this.mp_marque = mp_marque;}

	public Double getMp_prix() {return mp_prix;}

	public void setMp_prix(Double mp_prix) {this.mp_prix = mp_prix;}

	public int getMp_id() {return mp_id;}


	public List<Produit> getListeProduits() {return listeProduits;}

	public void setListeProduits(List<Produit> listeProduits) {this.listeProduits = listeProduits;}
	
	
	
	
	

}
