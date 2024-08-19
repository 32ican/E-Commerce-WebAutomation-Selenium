package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.ComputersPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.PageObjectManager;
import utils.WebDriverManager;

import org.testng.Assert;

public class AddToCartSteps {

	PageObjectManager pageOpjectManager = new PageObjectManager(WebDriverManager.getDriver());
	
	HomePage homePage = pageOpjectManager.getHomePage();
	ComputersPage computer = pageOpjectManager.getComputersPage();
	LoginPage loginPage = pageOpjectManager.getLoginPage();
	ProductsPage product = pageOpjectManager.getProductsPage();
	
	
	
	@Given("^user logged in to his account with username \"([^\"]*)\" and password \"([^\"]*)\"$")
	public void user_logged_in_to_his_account_with_username_something_and_password_something(String email,
			String password) throws Throwable {
		ExtentManager.log("logging into account.....");
		
		homePage.clickLogin();
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickLoginBtn();
		
		String  expected = ConfigLoader.getProperty("email");
     	String actual = homePage.getAccountConfirmationMsg();
     	
     	Assert.assertEquals(actual, expected, "Login was not successful");

	}

	@When("^user select a new product: simple computer$")
	public void user_could_select_a_new_product_simple_computer() throws Throwable {
		ExtentManager.log("adding item to the shopping cart.....");
		
		computer.selectComputer();		
	}

	@And("^add it to cart$")
	public void add_it_to_cart() throws Throwable {

		product.addComputerToCart();
		
		// wait until item added to cart
		Commands.waitForVisibilty(product.confitmationMsg(), 10);
	}

	@Then("^confirmation message: \"([^\"]*)\" should appear$")
	public void confirmation_message_something_should_appear(String confirmMsg) throws Throwable {
	
		String expectedCartConfirmationMsg = ConfigLoader.getProperty("CartConfirmationMsg");
		String actualCartConfirmationMsg = product.getConfirmationMsg();
		System.out.println(actualCartConfirmationMsg);
		Assert.assertEquals(expectedCartConfirmationMsg, actualCartConfirmationMsg);	

	}

}
