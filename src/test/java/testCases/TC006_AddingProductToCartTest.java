package testCases;


import org.testng.annotations.Test;
import org.testng.Assert;
import pages.ProductsPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;

public class TC006_AddingProductToCartTest extends Hooks {
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	ProductsPage product = pageObjectManager.getProductsPage();
	
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String expectedMsg = ConfigLoader.getProperty("CartConfirmationMsg");

	@Test(groups = {"Smoke"})
	public void addBookToCartTest() {
		Commands command = new Commands();
		command.login(email, password);
		command.addBookToCart();
		
		// assertion
	
		String actualCartConfirmationMsg = product.getConfirmationMsg().getText();
		Assert.assertEquals(expectedMsg, actualCartConfirmationMsg);
	}
}
