package boulangerie.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.myapplication.boulangerie.model.Categorie;
import com.myapplication.boulangerie.service.CategorieService;
import com.myapplication.boulangerie.service.MPService;
import com.myapplication.boulangerie.service.ProduitService;

class Test_case_categorie {

	  private ProduitService produitService = new ProduitService();
	    private CategorieService categorieService = new CategorieService();
	    private MPService mpService = new MPService();
	    
	/*
	 @Test 
	    public void WhenFindCategorieByName() throws Exception {
	    	
	    	String name = "Pains";
	    	
	    	Categorie categorie = categorieService.findByName(name);
	    	
	    	assertEquals(1,categorie.getCategorie_id());
			System.out.println("Test FIND CATEGORIE BY NAME --> OK");

	    	
	    	
	    }
	 
	 @Test 
	    public void WhenFindCategorieByID() throws Exception {
	    	
	    	String name = "Pains";
	    	int id  = 1;
	    	
	    	
	    	Categorie categorie = categorieService.findById(id);
	    	
			assertEquals(name,categorie.getCategorie_nom());
	    	
			System.out.println("Test FIND CATEGORIE BY ID --> OK");

	    	
	    	
	    }
	    
	 
	    
	    @Test
	    public void WhenDelete1Categorie_thenDeleteAllHisProduits() throws Exception {
	    	
	    	int i = produitService.getAllProduit().size();
			int j = categorieService.getAllCategorie().size();
			
			Categorie c = categorieService.findById(3);
			
			int z = c.getProduits().size();
			
			categorieService.removeCategorie(3);
	
			assertEquals(j-1, categorieService.getAllCategorie().size());
			assertEquals(i-z, produitService.getAllProduit().size());
			
		    System.out.println("test DELETE 1 CATEGORIE WITH ITS PRODUCTS -> OK");
	    }
	    
	    */
	    
	   
}

