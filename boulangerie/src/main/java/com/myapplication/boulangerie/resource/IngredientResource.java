package com.myapplication.boulangerie.resource;

import java.util.List;

import com.myapplication.boulangerie.model.Ingredient;
import com.myapplication.boulangerie.model.MatierePremiere;
import com.myapplication.boulangerie.model.Produit;
import com.myapplication.boulangerie.service.IngredientService;
import com.myapplication.boulangerie.service.MPService;
import com.myapplication.boulangerie.service.ProduitService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;


@Path ("/ingredient")
public class IngredientResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	private IngredientService service = new IngredientService();
	private MPService mpService = new MPService();
	private ProduitService produitService= new ProduitService();
	

	//cette methode a definit la partie de l'url d'acces a webservice sous path /getAllIngredients
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path ("/getAllIngredients")
	@GET  
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Ingredient> getAllIngredient () throws Exception {
		// On demande au service d'executer la methode "getAll" et retour la liste
		return service.getAllIngredient();
	}



	//cette methode a definit la partie de l'url d'acces a webservice sous path /insertionIngredient
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path ("/insertionIngredient")
	@POST  
	@Consumes(MediaType.APPLICATION_JSON) 
	public Ingredient addIngredient (Ingredient ingredient) throws Exception {
		
		Ingredient ingredientDB= service.insertIngredient(ingredient);
		
		MatierePremiere mp = mpService.findById(ingredient.getMp_id());
		
		Produit produit= produitService.findById(ingredient.getProduit_id()); 
		
		mp.updateQuantiteDuMP(ingredient.getIngredient_quantite()*produit.getProduit_quantite());
		
		mpService.updateMatierePremiere(mp, mp.getMp_id());
		
		// On demande au service d'executer la methode "insertIngredient" et retour ingredient
		return ingredientDB;
	}	


	//cette methode a definit la partie de l'url d'acces a webservice sous path /updateIngredient
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path("/updateIngredient/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Ingredient updateIngredient (Ingredient ingredient, @PathParam("id") int id) throws Exception {
		Ingredient ingredientDB= service.findById(id);
		int ecart = ingredient.getIngredient_quantite()-ingredientDB.getIngredient_quantite();
		if (ecart >0) {
			MatierePremiere mp = mpService.findById(ingredient.getMp_id());
			
			Produit produit= produitService.findById(ingredient.getProduit_id()); 
			mp.updateQuantiteDuMP(ecart*produit.getProduit_quantite());
			mp = mpService.updateMatierePremiere(mp, mp.getMp_id());
			
		}
		return service.updateIngredient(ingredient, id);

	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path /deleteIngredient/{id}
	//elle s'utilise avec DELETE
	//elle prend du JSON en entree
	@Path ("/deleteIngredient/{id}")
	@DELETE  
	@Consumes(MediaType.APPLICATION_JSON) 
	public boolean deleteIngredient (@PathParam("id") int id) throws Exception {
		boolean resultal = false;

		try {
			// On demande au service d'executer la methode "remove" 
			service.removeIngredientByID(id);
			resultal = true;
			return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}
	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path /findbyProduitID/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyProduitID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Ingredient> getIngredientByProduitID (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la liste des ingredients avec id recherche
		return service.findByProduitId(id);
	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path /findbyID/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyID/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Ingredient getIngredientByID (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la ingredient avec id recherche
		return service.findById(id);
	}
}
