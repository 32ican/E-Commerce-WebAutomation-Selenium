package testCases;

import org.testng.annotations.Test;

import base.Hooks;
import org.testng.Assert;
import pages.ProductsPage;

public class TC006_AddingProductToCartTest extends Hooks {

	private String email = getProperty("email");
	private String password = getProperty("password");
	private String expectedMsg = getProperty("CartConfirmationMsg");

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
