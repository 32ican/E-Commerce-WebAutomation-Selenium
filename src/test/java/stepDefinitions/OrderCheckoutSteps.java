package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CheckoutPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.PageObjectManager;
import utils.WebDriverManager;
import org.testng.Assert;

public class OrderCheckoutSteps{
	
	PageObjectManager pageOpjectManager = new PageObjectManager(WebDriverManager.getDriver());
	CheckoutPage checkout = pageOpjectManager.getCheckoutPage();
	
	
	@And("^add the address and continue$")
	public void add_the_address_and_continue(DataTable dataTable) throws Throwable {
		String country = dataTable.row(1).get(0);
		String city = dataTable.row(1).get(1);
		String street = dataTable.row(1).get(2);
		String postalCode = dataTable.row(1).get(3);
		String phone = dataTable.row(1).get(4);

		try {
			ExtentManager.log("Filling Billing Address.....");
			checkout.selectCountry(country);
			checkout.enterCity(city);
			checkout.enterStreet(street);
			checkout.enterPostalCode(postalCode);
			checkout.enterPhoneNumber(phone);
		} catch (Exception e) {
			e.getMessage();
		}
		
		checkout.clickContinueAfterBillingAddress();
	}

	@And("^continue to payment method and enter the payment information:$")
	public void continue_to_payment_method_and_enter_the_payment_information(DataTable cardInfo) throws Throwable {
		String cardType = cardInfo.cell(0, 1);
		String cardHolder = cardInfo.cell(1, 1);
		String cardNumber = cardInfo.cell(2, 1);
		String expMonth = cardInfo.cell(3, 1);
		String expYear = cardInfo.cell(4, 1);
		String cardCode = cardInfo.cell(5, 1);

		checkout.clickShippingAddressContinueBtn();
		checkout.clickShippingMethodContinueBtn();
		checkout.clickCreditRadioBtn();
		checkout.clickContinueToPaymentBtn();

		checkout.enterCridetCardCredentials(cardType, cardHolder, cardNumber, expMonth, expYear, cardCode);

		checkout.clickContinueToConfirmOrderBtn();
		checkout.clickConfirmBtn();

	}

	// assert verification message that order has successfully processed!
	@Then("^user could see confirmation message: \"([^\"]*)\"$")
	public void user_could_see_confirmation_message_something(String comfirmMsg) throws Throwable {

		// wait until confirmation order page be loaded
		Commands.waitForInvisibilty(checkout.getConfirmBtn(), 10);
		
		String ExpectedOrderConfirmationMsg = ConfigLoader.getProperty("orderConfirmationMsg");
		String actualConfirmationMsg = checkout.getOrderConfirmationMsg();
		System.out.println(actualConfirmationMsg);
		Assert.assertEquals(ExpectedOrderConfirmationMsg, actualConfirmationMsg);

		ExtentManager.pass("Order confirmed Successfully and the test case passed! ");
	}

}
