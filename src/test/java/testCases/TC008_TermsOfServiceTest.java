package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;

public class TC008_TermsOfServiceTest extends Hooks {

	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	CartPage cart = pageObjectManager.getCartPage();
	
	private String expectedMsg = ConfigLoader.getProperty("termsAndConditionsMsg");
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String discountCode = ConfigLoader.getProperty("discountCode");

	@Test(groups = { "Regression" })
	public void verifyMandatoryTermsOfServiceTest() {
		Commands commands = new Commands();
		commands.login(email, password);
		commands.addBookToCart();
		commands.applyCopun(discountCode);
		
		cart.getCheckoutBtn().click();

		String actualMsg = cart.getTermsAndCondtionsMsg().getText();
		Assert.assertEquals(expectedMsg, actualMsg);
	}
}
