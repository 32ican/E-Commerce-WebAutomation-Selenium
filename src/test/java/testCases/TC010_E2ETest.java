package testCases;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import base.Hooks;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ComputersPage;
import pages.HomePage;
import pages.ProductsPage;
import utils.ExtentManager;

public class TC010_E2ETest extends Hooks {

	private String email = getProperty("email");
	private String password = getProperty("password");
	private String expectedCartConfirmationMsg = getProperty("CartConfirmationMsg");
	private String discountCode = getProperty("discountCode");
	private String country = getProperty("country");
	private String city = getProperty("city");
	private String street = getProperty("street");
	private String postalCode = getProperty("postalCode");
	private String phone = getProperty("phone");
	private String cardType = getProperty("cardType");
	private String cardHolder = getProperty("cardHolder");
	private String cardNumber = getProperty("cardNumber");
	private String expMonth = getProperty("expMonth");
	private String expYear = getProperty("expYear");
	private String cardCode = getProperty("cardCode");
	private String ExpectedOrderConfirmationMsg = getProperty("orderConfirmationMsg");

	@Test(groups = {"Smoke"})
	public void E2ETest() {

		login(email, password);

		ExtentManager.log("adding item to the shopping cart.....");

		ComputersPage computer = new ComputersPage(getDriver());
		computer.getComputers().click();
		computer.getDesktops().click();
		computer.getSimpleComputer().click();
		computer.getProcessorRadioBtn().click();

		ProductsPage product = new ProductsPage(getDriver());
		product.getAddSimpleComputerToCartBtn().click();

		// wait until item added to cart
		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(product.getConfirmationMsg()));

		String actualCartConfirmationMsg = product.getConfirmationMsg().getText();
		System.out.println(actualCartConfirmationMsg);
		Assert.assertEquals(expectedCartConfirmationMsg, actualCartConfirmationMsg);

		ExtentManager.log("Navigating to the shopping cart.....");

		HomePage homePage = new HomePage(getDriver());
		homePage.getCartLink().click();

		CartPage cart = new CartPage(getDriver());
		cart.getQuantity().clear();
		cart.getQuantity().sendKeys("1");
		cart.getQuantity().sendKeys(Keys.ENTER);

		ExtentManager.log("Applying discount code.....");
		cart.getDiscountCodeField().sendKeys(discountCode);
		cart.getApplyCopunBtn().click();

		double subTotal;
		double total;
		double portion = .1;
		try {
			subTotal = Double.parseDouble(cart.getSubTotalAmount().getText());
			total = Double.parseDouble(cart.getTotalAmount().getText());
			portion = total / subTotal;

		} catch (NumberFormatException e) {
			e.getMessage();
		}

		String discount = cart.getDiscountText().getText().toLowerCase();
		Assert.assertTrue(portion == .8 && discount.contains("discount"));

		// ExtentManager.log("checking out.....");
		cart.getAgreeBtn().click();
		cart.getCheckoutBtn().click();

		CheckoutPage checkout = new CheckoutPage(getDriver());
		String checkoutTitle = checkout.getCheckoutTitle().getText();
		Assert.assertTrue(checkoutTitle.toLowerCase().contains("checkout"));

		try {
			// ExtentManager.log("Filling Billing Address.....");
			Select selectCountry = new Select(checkout.getCountryField());
			selectCountry.selectByVisibleText(country);
			checkout.getCityField().sendKeys(city);
			checkout.getAddress1Field().sendKeys(street);
			checkout.getPostalCodeField().sendKeys(postalCode);
			checkout.getPhoneField().sendKeys(phone);
		} catch (Exception e) {
			e.getMessage();
		}

		ExtentManager.log("Payment informatino.....");
		checkout.getBillingAddressContinueBtn().click();
		checkout.getShippingAddressContinueBtn().click();
		checkout.getShippingMethodContinueBtn().click();
		checkout.getCreditRadioBtn().click();
		checkout.getContinueToPaymentBtn().click();

		Select selectCardType = new Select(checkout.getCardTypeMenu());
		selectCardType.selectByVisibleText(cardType);

		checkout.getCardHolderField().sendKeys(cardHolder);
		checkout.getCardNumberField().sendKeys(cardNumber);

		Select selectMonth = new Select(checkout.getExpireMonthMenu());
		selectMonth.selectByVisibleText(expMonth);

		Select selectYear = new Select(checkout.getExpireYearMenu());
		selectYear.selectByVisibleText(expYear);

		checkout.getCardCodeField().sendKeys(cardCode);
		checkout.getContinueToConfirmOrderBtn().click();
		checkout.getConfirmBtn().click();

		// wait until confirmation order page be loaded

		wait.until(ExpectedConditions.invisibilityOf(checkout.getConfirmBtn()));

		String actualConfirmationMsg = checkout.getOrderConfirmationMsg().getText();
		System.out.println(actualConfirmationMsg);
		Assert.assertEquals(ExpectedOrderConfirmationMsg, actualConfirmationMsg);

		ExtentManager.pass("Order confirmed Successfully and the test case passed! ");
	}
}
