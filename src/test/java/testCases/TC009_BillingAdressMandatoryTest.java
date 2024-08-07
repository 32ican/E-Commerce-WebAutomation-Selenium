package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ConfigLoader;

public class TC009_BillingAdressMandatoryTest extends Hooks {

	private String counrtyMsg = ConfigLoader.getProperty("contryMsg");
	private String cityMsg = ConfigLoader.getProperty("cityMsg");
	private String streetMsg = ConfigLoader.getProperty("streetMsg");
	private String postalCodeMsg = ConfigLoader.getProperty("postalCodeMsg");
	private String phoneMsg = ConfigLoader.getProperty("phoneMsg");

	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String discountCode = ConfigLoader.getProperty("discountCode");
	
	private List<String> expectedErrorMessages = Arrays.asList(counrtyMsg, cityMsg, streetMsg, postalCodeMsg, phoneMsg);

	@Test(groups = {"Regression"})
	public void verifyMandatoryTermsOfServiceTest() {
		
		login(email, password);
		addBookToCart();
		applyCopun(discountCode);
		
		CartPage cart = new CartPage(getDriver());
		cart.getAgreeBtn().click();
		cart.getCheckoutBtn().click();

		CheckoutPage checkout = new CheckoutPage(getDriver());
		String checkoutTitle = checkout.getCheckoutTitle().getText();
		Assert.assertTrue(checkoutTitle.toLowerCase().contains("checkout"));

		checkout.getBillingAddressContinueBtn().click();

		List<String> actualErrorMessages = checkout.getBillingAddressErrorMsgsAsString();
		Assert.assertEquals(expectedErrorMessages, actualErrorMessages);

	}
}
