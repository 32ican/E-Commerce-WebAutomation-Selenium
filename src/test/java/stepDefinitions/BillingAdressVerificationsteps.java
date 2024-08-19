package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import org.testng.Assert;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ExtentManager;
import utils.PageObjectManager;
import utils.WebDriverManager;

public class BillingAdressVerificationsteps {

	PageObjectManager pageOpjectManager = new PageObjectManager(WebDriverManager.getDriver());
	CartPage cart  = pageOpjectManager.getCartPage();
	CheckoutPage checkout = pageOpjectManager.getCheckoutPage();
	

	@And("^agree the terms of service$")
	public void agree_the_terms_of_service() throws Throwable {
		cart.agreeTermsOfService();
	}

	@And("^click on check out$")
	public void click_on_check_out() throws Throwable {
		cart.checkout();
		String checkoutTitle = checkout.getCheckoutTitle();
		Assert.assertTrue(checkoutTitle.toLowerCase().contains("checkout"));

	}

	@When("^user click on continue without selecting a country$")
	public void user_click_on_continue_without_selecting_a_country() throws Throwable {
		checkout.clickContinueAfterBillingAddress();
	}

	// assert that error message: data is required will appear
	@Then("^error message will appear that: \"([^\"]*)\"$")
	public void error_message_will_appear_that_something(String errorMsg) throws Throwable {
		List<String> actual = checkout.getBillingAddressErrorMsgsAsString();
		Assert.assertTrue(actual.contains(errorMsg));
	}

	@When("^user select country \"([^\"]*)\" and click on continue$")
	public void user_select_a_country_something_and_click_on_continue(String country) throws Throwable {
		ExtentManager.log("Filling Billing Address.....");

		checkout.selectCountry(country);
		checkout.clickContinueAfterBillingAddress();

	}

	@When("^user select Country and fill the City as follows$")
	public void user_select_country_and_fill_the_city_as_follows(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.enterCity(dataTable.cell(1, 1));
		checkout.clickContinueAfterBillingAddress();
	}

	@When("^user fill the address as follows and click continue$")
	public void user_fill_the_address_something_and_click_continue(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.enterCity(dataTable.cell(1, 1));
		checkout.enterStreet(dataTable.cell(1, 2));
		checkout.clickContinueAfterBillingAddress();
	}

	@When("^user fill the zip code and click on continue$")
	public void user_fill_the_zip_code_something_and_click_on_continue(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.enterCity(dataTable.cell(1, 1));
		checkout.enterStreet(dataTable.cell(1, 2));
		checkout.enterPostalCode(dataTable.cell(1, 3));
		checkout.clickContinueAfterBillingAddress();

	}

}
