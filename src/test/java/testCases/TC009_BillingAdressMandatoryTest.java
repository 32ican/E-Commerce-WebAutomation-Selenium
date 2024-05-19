package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.CartPage;
import pages.CheckoutPage;

public class TC009_BillingAdressMandatoryTest extends Hooks {

	private String counrtyMsg = getProperty("contryMsg");
	private String cityMsg = getProperty("cityMsg");
	private String streetMsg = getProperty("streetMsg");
	private String postalCodeMsg = getProperty("postalCodeMsg");
	private String phoneMsg = getProperty("phoneMsg");

	private String email = getProperty("email");
	private String password = getProperty("password");
	private String discountCode = getProperty("discountCode");
	
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
