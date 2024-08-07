package testCases;


import org.testng.annotations.Test;

import base.Hooks;
import org.testng.Assert;
import pages.ProductsPage;
import utils.ConfigLoader;

public class TC006_AddingProductToCartTest extends Hooks {

	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String expectedMsg = ConfigLoader.getProperty("CartConfirmationMsg");

	@Test(groups = {"Smoke"})
	public void addBookToCartTest() {
		
		login(email, password);
		addBookToCart();
		
		// assertion
		ProductsPage product = new ProductsPage(getDriver());
		String actualCartConfirmationMsg = product.getConfirmationMsg().getText();
		Assert.assertEquals(expectedMsg, actualCartConfirmationMsg);
	}
}
