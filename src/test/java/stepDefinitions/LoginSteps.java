package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import org.testng.Assert;

import base.BaseClass;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigLoader;
import utils.PageObjectManager;

public class LoginSteps extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);

	HomePage homePage = pageOpjectManager.getHomePage();
	LoginPage loginPage = pageOpjectManager.getLoginPage();
	@Given("^user click on log in button$")
	public void user_click_on_log_in_button() throws Throwable {
		homePage.getLoginBtn().click();
	}

	@When("^user enters an invalid email: \"([^\"]*)\" and password \"([^\"]*)\"$")
	@When("^user enters a valid email: \"([^\"]*)\" and invalid password: \"([^\"]*)\"$")
	@When("^user enters email: \"([^\"]*)\" and password: \"([^\"]*)\"$")
	public void user_enters_email_something_and_password_something(String email, String password) throws Throwable {
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
	}

	@And("^clicks on log in button$")
	public void clicks_on_log_in_button() throws Throwable {
		loginPage.getLoginBtn().click();
	}

	// assert that log out button exists
	@Then("^user could see his account$")
	public void user_could_see_his_account() throws Throwable {
		// verification that user logged in
		String expected = ConfigLoader.getProperty("email");
		String actual = homePage.getAccount().getText();

		Assert.assertEquals(actual, expected);

	}

	// assert that user couldn't log in with invalid password or with invalid user
	@Then("^user could not log in to his account and error message will appear contains \"([^\"]*)\"$")
	public void user_could_not_log_in_to_his_account_and_error_message_will_appear_contains_something(
			String errorMessaage) throws Throwable {
		String expected = ConfigLoader.getProperty("failedLoginMsg");
		String actual = loginPage.getFailledLoginErrorMsg().getText();
		Assert.assertEquals(expected, actual);
	}

}
