package com.myapplication.boulangerie.resource;

import java.util.List;

import com.myapplication.boulangerie.model.Utilisateur;
import com.myapplication.boulangerie.service.UtilisateurService;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;



@Path ("/user")
public class UtilisateurResource {


	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Got it!";
	}

	private UtilisateurService service = new UtilisateurService();

	

	//cette methode a definit la partie de l'url d'acces a webservice sous path /getAllUtilisateurs
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path ("/getAllUtilisateurs")
	@GET  
	@Produces(MediaType.APPLICATION_JSON) 
	public List<Utilisateur> getAllUtilisateur () throws Exception {
		// On demande au service d'executer la methode "getAll" et retour la liste
		return service.getAllUtilisateur();
	}



	//cette methode a definit la partie de l'url d'acces a webservice sous path /insertionUtilisateur
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path ("/insertionUtilisateur")
	@POST  
	@Consumes(MediaType.APPLICATION_JSON) 
	public Utilisateur addUtilisateur (Utilisateur user) throws Exception {
		return service.insertUtilisateur(user);
	}	


	//cette methode a definit la partie de l'url d'acces a webservice sous path /updateUtilisateur
	//elle s'utilise avec POST
	//elle prend du JSON en entree
	@Path("/updateUtilisateur/{id}")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Utilisateur updateUtilisateur (Utilisateur user, @PathParam("id") int id) throws Exception {
		// On demande au service d'executer la methode "update" 
		return service.updateUtilisateur(user, id);

	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path /deleteUtilisateur/{id}
	//elle s'utilise avec DELETE
	//elle prend du JSON en entree
	@Path ("/deleteUtilisateur/{id}")
	@DELETE  
	@Consumes(MediaType.APPLICATION_JSON) 
	public boolean deleteUtilisateur (@PathParam("id") int id) throws Exception {
		boolean resultal = false;
		
		try {
		// On demande au service d'executer la methode "remove"
		service.removeUtilisateur(id);
		resultal = true;
		return resultal;
		} catch (Exception e) {
			throw new Exception("ne peut pas supprimer cette categorie " + e.toString());
		}


	}

	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDUtilisateur/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyIDUtilisateur/{id}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur getUtilisateurByID (@PathParam("id") int id) throws Exception {
		 Utilisateur user  = service.findById(id);
		// On demande au service d'executer la methode "findById(id)" et retour la user avec id recherche
		return user;
	}


	//cette methode a definit la partie de l'url d'acces a webservice sous path //findbyIDUtilisateur/{id}
	//elle s'utilise avec GET
	//elle prend du JSON en entree
	@Path("/findbyNameUtilisateur/{name}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Utilisateur getUtilisateurByName (@PathParam("name") String name) throws Exception {
		Utilisateur user = service.findByName(name);
		// On demande au service d'executer la methode "findById(id)" et retour la user avec id recherche
		return user;
	}

}
