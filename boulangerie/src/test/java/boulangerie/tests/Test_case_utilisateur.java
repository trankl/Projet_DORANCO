package boulangerie.tests;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.myapplication.boulangerie.model.Utilisateur;
import com.myapplication.boulangerie.resource.UtilisateurResource;
import com.myapplication.boulangerie.service.UtilisateurService;


class Test_case_utilisateur {

	private UtilisateurService userService= new UtilisateurService();
	private UtilisateurResource userResource = new UtilisateurResource();
	
	
	@Test
	void WhenGetAllUser() throws Exception {
		
		List<Utilisateur> listUsers= userService.getAllUtilisateur();
		
		assertEquals(2,listUsers.size());
		System.out.println("TEST GET ALL USER --> OK");
	}

	
	@Test
	void WhenFindUserByName() throws Exception {
		
		String name = "Jean";
		
		Utilisateur user = userService.findByName(name);
		
		assertEquals("12345",user.getUser_password());
		System.out.println("TEST FIND USER BY NAME --> OK");
	}	
	
	@Test
	void WhenCreateNewUser_CheckPass() throws Exception {
		
		Utilisateur user = new Utilisateur("Marie", "azerty");
		
		userResource.addUtilisateur(user);
		
		assertNotSame("azerty",user.getUser_password());
		
		System.out.println(" Test CREATE USER WITH CIPHER PASSWORD 1 -> OK");
		
	}
	
	/*
	 * @Test void WhenCreateNewUser_CheckPassChiffre() throws Exception {
	 * 
	 * Utilisateur user = new Utilisateur("Marie", "azerty");
	 * 
	 * Utilisateur user2 = userResource.addUtilisateur(user);
	 * 
	 * byte[] pass_user_chiffre =
	 * ChiffrementPassword.encrypter(user.getUser_password(), user2.getCle());
	 * 
	 * assertEquals(new String(pass_user_chiffre),user2.getUser_password());
	 * 
	 * System.out.println(" Test CREATE USER WITH CIPHER PASSWORD 2 -> OK");
	 * 
	 * }
	 */
}
