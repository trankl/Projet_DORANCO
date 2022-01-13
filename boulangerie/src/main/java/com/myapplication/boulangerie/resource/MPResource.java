package com.myapplication.boulangerie.resource;

import java.util.List;

import com.myapplication.boulangerie.model.MatierePremiere;
import com.myapplication.boulangerie.service.MPService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path ("/matierepremiere")
public class MPResource {
	
	  
	    @GET
	    @Produces(MediaType.TEXT_PLAIN)
	    public String getIt() {
	        return "Got it!";
	    }

	private MPService service = new MPService();
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /getAllMPs
		//elle s'utilise avec GET
		//elle prend du JSON en entree
		@Path ("/getAllMPs")
		@GET  
		@Produces(MediaType.APPLICATION_JSON) 
		public List<MatierePremiere> getAllMP () throws Exception {
		// On demande au service d'executer la methode "getAll" et retour la liste
		return service.getAllMatierePremiere();
		}
	
	
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /insertionMP
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path ("/insertionMP")
	@POST  
	@Consumes(MediaType.APPLICATION_JSON) 
	public MatierePremiere addMP (MatierePremiere mp) throws Exception {
		// On demande au service d'executer la methode "insertMP" et retour mp
		return service.insertMatierePremiere(mp);
	}	

	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /updateMP
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path("/updateMP/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public MatierePremiere updateMP (MatierePremiere mp, @PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "update" 
		return service.updateMatierePremiere(mp, id);
		
	}
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path /deleteMP/{id}
	//elle s'utilise avec DELETE
	//elle prend du JSON en entree
	@Path ("/deleteMP/{id}")
	@DELETE  
	@Consumes(MediaType.APPLICATION_JSON) 
	public boolean deleteMP (@PathParam("id") int id) throws Exception {
boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove" 
		service.removeMatierePremiere(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette MP " + e.toString());
		}
		
	}
	
	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDMP/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyIDMP/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MatierePremiere getMPByID (@PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la mp avec id recherche
		return service.findById(id);
	}


	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDMP/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyNameMP/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public MatierePremiere getMPByName (@PathParam("name") String name) throws Exception {
		// On demande au service d'executer la methode "findById(id)" et retour la mp avec id recherche
		return service.findByName(name);
	}
	

}
