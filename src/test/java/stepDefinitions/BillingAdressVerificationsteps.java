package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import base.BaseClass;
import pages.CartPage;
import pages.CheckoutPage;
import utils.ExtentManager;
import utils.PageObjectManager;

public class BillingAdressVerificationsteps extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);
	CartPage cart  = pageOpjectManager.getCartPage();
	CheckoutPage checkout = pageOpjectManager.getCheckoutPage();
	

	@And("^agree the terms of service$")
	public void agree_the_terms_of_service() throws Throwable {
		cart.getAgreeBtn().click();
	}

	@And("^click on check out$")
	public void click_on_check_out() throws Throwable {
		cart.getCheckoutBtn().click();
		String checkoutTitle = checkout.getCheckoutTitle().getText();
		Assert.assertTrue(checkoutTitle.toLowerCase().contains("checkout"));

	}

	@When("^user click on continue without selecting a country$")
	public void user_click_on_continue_without_selecting_a_country() throws Throwable {
		checkout.getBillingAddressContinueBtn().click();
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

		Select selectCountry = new Select(checkout.getCountryField());
		selectCountry.selectByVisibleText(country);
		checkout.getBillingAddressContinueBtn().click();

	}

	@When("^user select Country and fill the City as follows$")
	public void user_select_country_and_fill_the_city_as_follows(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.getCityField().sendKeys(dataTable.cell(1, 1));
		checkout.getBillingAddressContinueBtn().click();
	}

	@When("^user fill the address as follows and click continue$")
	public void user_fill_the_address_something_and_click_continue(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.getCityField().sendKeys(dataTable.cell(1, 1));
		checkout.getAddress1Field().sendKeys(dataTable.cell(1, 2));
		checkout.getBillingAddressContinueBtn().click();
	}

	@When("^user fill the zip code and click on continue$")
	public void user_fill_the_zip_code_something_and_click_on_continue(DataTable dataTable) throws Throwable {
	
		checkout.selectCountry(dataTable.cell(1, 0));
		checkout.getCityField().sendKeys(dataTable.cell(1, 1));
		checkout.getAddress1Field().sendKeys(dataTable.cell(1, 2));
		checkout.getPostalCodeField().sendKeys(dataTable.cell(1, 3));
		checkout.getBillingAddressContinueBtn().click();

	}

}
