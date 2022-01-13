package boulangerie.tests;

import static org.junit.Assert.assertEquals;
import java.util.List;
import org.junit.Test;
import com.myapplication.boulangerie.model.Ingredient;
import com.myapplication.boulangerie.model.MatierePremiere;
import com.myapplication.boulangerie.model.Produit;
import com.myapplication.boulangerie.resource.ProduitResource;
import com.myapplication.boulangerie.service.CategorieService;
import com.myapplication.boulangerie.service.IngredientService;
import com.myapplication.boulangerie.service.MPService;
import com.myapplication.boulangerie.service.ProduitService;


class Test_case_produit {

    private ProduitService produitService = new ProduitService();
    private CategorieService categorieService = new CategorieService();
    private MPService mpService = new MPService();
	private IngredientService ingredientService = new IngredientService();
    private ProduitResource produitResource = new ProduitResource();
    
    @Test
	public void WhenUpdateProduit_thenUpdateQuantiteMP() throws Exception {
		int id = 18;
    	int[] tabQuantiteMP = new int[100];
    	int[] tabQuantiteIngredient = new int[100];
		Produit produit = produitService.findById(id);
		
		int ecart_quantiteProduit = produit.getProduit_quantite();
		
		produit.setProduit_quantite(3);
		if (produit.getProduit_quantite()>ecart_quantiteProduit) {
			ecart_quantiteProduit = produit.getProduit_quantite() - ecart_quantiteProduit;
		} else ecart_quantiteProduit = 0;
	
		List<Ingredient> listIngredient = ingredientService.findByProduitId(id);
		int i = 0;
		for (Ingredient ingredient : listIngredient) {
			tabQuantiteIngredient[i] = ingredient.getIngredient_quantite();
			MatierePremiere mp = mpService.findById(ingredient.getMp_id());
			tabQuantiteMP[i] = mp.getMp_quantite();
			i++;
		}
		
		produitResource.updateProduit(produit, id);
		
		
		listIngredient = ingredientService.findByProduitId(id);
		i = 0;
		for (Ingredient ingredient : listIngredient) {
			MatierePremiere mp = mpService.findById(ingredient.getMp_id());
			assertEquals(tabQuantiteMP[i]-(tabQuantiteIngredient[i]*ecart_quantiteProduit), mp.getMp_quantite());
			i++;
		}
		
		
		
		System.out.println("Test UPDATE PRODUIT -> OK");
		
	}
    
    
  
    @Test
	public void whenCreateProduitinProduitResource() throws Exception {
		
	
		int categorie_id= 1;
		
		Produit produit = new Produit("pain",1.2,10);
		
		Produit prodb = produitResource.addProduit(produit, categorie_id);
		
		assertEquals("Pains",prodb.getProduitCategorie().getCategorie_nom());
	
	    System.out.println("test CREATE NEW PRODUCT so CREATE NEW CATEGORY IF NOT EXISTED -> OK");
		
	}
    

    
    /*
	@Test
	public void whenCreateProduit() throws Exception {
		
	
		Categorie categorie = categorieService.findById(1);
		
		Produit produit = new Produit("PainXL",1.05,7,categorie );
		
		Produit prodb = produitService.insertProduit(produit);
		
		assertEquals("Pains",prodb.getProduitCategorie().getCategorie_nom());
	
	    System.out.println("test CREATE NEW PRODUCT so CREATE NEW CATEGORY IF NOT EXISTED -> OK");
		
	}
    
 

	
	@Test
	public void whenDeleting1Produit_thenCategoriesShouldAlsoBeExisted() throws Exception {
		
		int i = produitService.getAllProduit().size();
		int j = categorieService.getAllCategorie().size();
		
		produitService.removeProduit(8);;
		
		assertEquals(i-1, produitService.getAllProduit().size());
		assertEquals(j, categorieService.getAllCategorie().size());
	    
	    System.out.println("test DELETE 1 PRODUCT -> OK");
	}

	@Test
	public void whenDeleting1Produit_thenDeletingHISMPDansTableIngredientsTOO() throws Exception {
		
		int i = produitService.getAllProduit().size();
		int j = ingredientService.getAllIngredient().size();
		int z= ingredientService.findByProduitId(7).size();
		
		produitService.removeProduit(7);
		
		assertEquals(i-1, produitService.getAllProduit().size());
		assertEquals(j-z,ingredientService.getAllIngredient().size() );
	    
	    System.out.println("test DELETE 1 PRODUCT with its ingredients -> OK");
	}
	
	
	@Test
	void whenFindProduitByName() throws Exception {
		
		String name = "Croissant";
		
		Produit produit = produitService.findByName(name);
		
		assertEquals(3, produit.getProduit_id());
		
		System.out.println("Test FIND 1 PRODUCT BY NAME --> OK");
	}
*/
    
    @Test
	void whenFindListProduitByCategorieID() throws Exception {
		
		int id = 4;
		
		List<Produit> listProduit = produitService.findListProduitByCategorieID(id);
		
		assertEquals(4, listProduit.size());
    	System.out.println("Test GET LIST PRODUIT DU CATEGORIE -> OK");
	}
    
}
