package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.CartPage;
import utils.ConfigLoader;

public class TC008_TermsOfServiceTest extends Hooks {

	private String expectedMsg = ConfigLoader.getProperty("termsAndConditionsMsg");
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String discountCode = ConfigLoader.getProperty("discountCode");

	@Test(groups = { "Regression" })
	public void verifyMandatoryTermsOfServiceTest() {

		login(email, password);
		addBookToCart();
		applyCopun(discountCode);
		
		CartPage cart = new CartPage(getDriver());
		cart.getCheckoutBtn().click();

		String actualMsg = cart.getTermsAndCondtionsMsg().getText();
		Assert.assertEquals(expectedMsg, actualMsg);
	}
}
