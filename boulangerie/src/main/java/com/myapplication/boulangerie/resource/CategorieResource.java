package com.myapplication.boulangerie.resource;

import java.util.List;

import com.myapplication.boulangerie.model.Categorie;
import com.myapplication.boulangerie.service.CategorieService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path ("/categorie")
public class CategorieResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	private CategorieService service = new CategorieService();

	//cette methode a definit la partie de l'url d'acces a webservice sous path /getAllCategories
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path ("/getAllCategories")
	@GET  
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Categorie> getAllCategorie () throws Exception {
		// On demande au service d'executer la methode "getAll" et retour la liste
		return service.getAllCategorie();
	}



	//cette methode a definit la partie de l'url d'acces a webservice sous path /insertionCategorie
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path ("/insertionCategorie")
	@POST  
	@Consumes(MediaType.APPLICATION_JSON) 
	public Categorie addCategorie (Categorie categorie) throws Exception {
		// On demande au service d'executer la methode "insertCategorie" et retour categorie
		return service.insertCategorie(categorie);
	}	


	//cette methode a definit la partie de l'url d'acces a webservice sous path /updateCategorie
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path("/updateCategorie/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Categorie updateCategorie (Categorie categorie, @PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "update" 
		return service.updateCategorie(categorie, id);

	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path /deleteCategorie/{id}
	//elle s'utilise avec DELETE
	//elle prend du JSON en entree
	@Path ("/deleteCategorie/{id}")
	@DELETE  
	@Consumes(MediaType.APPLICATION_JSON) 
	public boolean deleteCategorie (@PathParam("id") int id) throws Exception {
		boolean resultal = false;

		try {
			// On demande au service d'executer la methode "remove" 
			service.removeCategorie(id);
			resultal = true;
			return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}

	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDCategorie/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyIDCategorie/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie getCategorieByID (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la categorie avec id recherche
		return service.findById(id);
	}


	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDCategorie/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyNameCategorie/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Categorie getCategorieByName (@PathParam("name") String name) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la categorie avec id recherche
		return service.findByName(name);
	}


}
