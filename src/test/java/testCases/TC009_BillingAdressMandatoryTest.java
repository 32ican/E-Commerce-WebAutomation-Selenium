package testCases;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import pages.CheckoutPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;

public class TC009_BillingAdressMandatoryTest extends Hooks {
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	
	CheckoutPage checkout = pageObjectManager.getCheckoutPage();
	
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
		Commands commands = new Commands();
		commands.login(email, password);
		commands.addBookToCart();
		commands.applyCopun(discountCode);
		
		CartPage cart = new CartPage(getDriver());
		cart.getAgreeBtn().click();
		cart.getCheckoutBtn().click();

		String checkoutTitle = checkout.getCheckoutTitle().getText();
		Assert.assertTrue(checkoutTitle.toLowerCase().contains("checkout"));

		checkout.getBillingAddressContinueBtn().click();

		List<String> actualErrorMessages = checkout.getBillingAddressErrorMsgsAsString();
		Assert.assertEquals(expectedErrorMessages, actualErrorMessages);

	}
}
