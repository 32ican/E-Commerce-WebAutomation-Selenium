package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import pages.CheckoutPage;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.PageObjectManager;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class OrderCheckoutSteps extends BaseClass {
	
	PageObjectManager pageOpjectManager = new PageObjectManager(driver);
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
			Select selectCountry = new Select(checkout.getCountryField());
			selectCountry.selectByVisibleText(country);
			checkout.getCityField().sendKeys(city);
			checkout.getAddress1Field().sendKeys(street);
			checkout.getPostalCodeField().sendKeys(postalCode);
			checkout.getPhoneField().sendKeys(phone);
		} catch (Exception e) {
			e.getMessage();
		}
		checkout.getBillingAddressContinueBtn().click();
	}

	@And("^continue to payment method and enter the payment information:$")
	public void continue_to_payment_method_and_enter_the_payment_information(DataTable cardInfo) throws Throwable {
		String cardType = cardInfo.cell(0, 1);
		String cardHolder = cardInfo.cell(1, 1);
		String cardNumber = cardInfo.cell(2, 1);
		String expMonth = cardInfo.cell(3, 1);
		String expYear = cardInfo.cell(4, 1);
		String cardCode = cardInfo.cell(5, 1);

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

	}

	// assert verification message that order has successfully processed!
	@Then("^user could see confirmation message: \"([^\"]*)\"$")
	public void user_could_see_confirmation_message_something(String comfirmMsg) throws Throwable {

		// wait until confirmation order page be loaded
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.invisibilityOf(checkout.getConfirmBtn()));

		String ExpectedOrderConfirmationMsg = ConfigLoader.getProperty("orderConfirmationMsg");
		String actualConfirmationMsg = checkout.getOrderConfirmationMsg().getText();
		System.out.println(actualConfirmationMsg);
		Assert.assertEquals(ExpectedOrderConfirmationMsg, actualConfirmationMsg);

		ExtentManager.pass("Order confirmed Successfully and the test case passed! ");
	}

}
