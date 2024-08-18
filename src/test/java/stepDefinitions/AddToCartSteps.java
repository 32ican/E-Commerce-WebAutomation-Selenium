package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ComputersPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.PageObjectManager;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import base.BaseClass;

public class AddToCartSteps extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);
	
	HomePage homePage = pageOpjectManager.getHomePage();
	ComputersPage computer = pageOpjectManager.getComputersPage();
	LoginPage loginPage = pageOpjectManager.getLoginPage();
	ProductsPage product = pageOpjectManager.getProductsPage();
	
	
	
	@Given("^user logged in to his account with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void user_logged_in_to_his_account_with_username_something_and_password_something(String email,
			String password) throws Throwable {
		ExtentManager.log("logging into account.....");
		
		homePage.getLoginBtn().click();
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
		
		String  expected = ConfigLoader.getProperty("email");
     	String actual = homePage.getAccount().getText();
     	
     	Assert.assertEquals(actual, expected, "Login was not successful");

	}

	@When("^user select a new product: simple computer$")
	public void user_could_select_a_new_product_simple_computer() throws Throwable {
		ExtentManager.log("adding item to the shopping cart.....");
		
		computer.getComputers().click();
		computer.getDesktops().click();
		computer.getSimpleComputer().click();
		computer.getProcessorRadioBtn().click();
		
	}

	@And("^add it to cart$")
	public void add_it_to_cart() throws Throwable {

		product.getAddSimpleComputerToCartBtn().click();
		
		// wait until item added to cart
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(product.getConfirmationMsg()));
	}

	// assert that confirmation message that product added successfully to cart will
	// appear
	@Then("^confirmation message: \"([^\"]*)\" should appear$")
	public void confirmation_message_something_should_appear(String confirmMsg) throws Throwable {
	
		String expectedCartConfirmationMsg = ConfigLoader.getProperty("CartConfirmationMsg");
		String actualCartConfirmationMsg = product.getConfirmationMsg().getText();
		System.out.println(actualCartConfirmationMsg);
		Assert.assertEquals(expectedCartConfirmationMsg, actualCartConfirmationMsg);	

	}

}
