package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.CartPage;

public class TC008_TermsOfServiceTest extends Hooks {

	private String expectedMsg = getProperty("termsAndConditionsMsg");
	private String email = getProperty("email");
	private String password = getProperty("password");
	private String discountCode = getProperty("discountCode");

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
