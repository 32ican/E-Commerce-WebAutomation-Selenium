package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.HomePage;
import pages.RegisterPage;
import pages.RegisterResultPage;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.PageObjectManager;
import utils.WebDriverManager;

public class RegistrationStep {
	
	PageObjectManager pageOpjectManager = new PageObjectManager(WebDriverManager.getDriver());

	HomePage homePage = pageOpjectManager.getHomePage();
	RegisterPage registerPage = pageOpjectManager.getRegisterPage();
	RegisterResultPage registerResultPage = pageOpjectManager.getRegisterResultPage();
	
	
	@Given("^user at home page and clicks on register$")
    public void user_at_home_page_and_clicks_on_register() throws Throwable {
		
		homePage.clickRegister();
    }

    @When("^user enters (.+), (.+), (.+) and (.+)$")
    public void user_enters_and(String firstname, String lastname, String email, String password) throws Throwable {
    	
		//registerPage.getGenderRadioBtn(gender).click();
    	registerPage.register(firstname, lastname, email, password, password);
    }

    @Then("^User could register successfully$")
    public void user_could_register_successfully_and_confirmation_message_should_appear() throws Throwable {
 
    	registerPage.clickRegisterBtn();
    }

    @And("^confirmation message should appear$")
    public void confirmation_messaage_should_appear (){
    	// verification
		String actual = registerResultPage.getSuccessfulRegisterMsg();
		String expected = ConfigLoader.getProperty("SuccessfulRegisterMsg");
		Assert.assertEquals(actual, expected);
    }

    
    
    @Then("user log out")
    public void user_log_out() {
        // Write code here that turns the phrase above into concrete actions
        logOut();
    }
    
    
    // unhappy scenario
    @When("^user enters same data \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\", \"([^\"]*)\"$")
    public void user_enters_same_data_something_something_something_something_something
            (String firstname, String lastname, String email, String password) throws Throwable {

		//registerPage.getGenderRadioBtn(gender).click();
		registerPage.getFirstNameField().sendKeys(firstname);
		registerPage.getLastNameField().sendKeys(lastname);
		registerPage.getEmailField().sendKeys(email);
		registerPage.getPasswordField().sendKeys(password);
		registerPage.getConfirmPasswordField().sendKeys(password);
    }

    @Then("^user could not register with same data$")
    public void user_could_not_register() throws Throwable {
    	registerPage.clickRegisterBtn();
    }

    @And("error message should appear")
    public void error_message_should_appear(){
        String expected = ConfigLoader.getProperty("unsuccessfulRegisterMsg");
		String actual = registerResultPage.getUnsuccessfulRegisterMsg();
		
		Assert.assertEquals(actual, expected);
    }
    
    public void logOut() {
		// logout
		try {
			
			homePage.clickLogOutBtn();
		} catch (Exception e) {
			e.getStackTrace();
		}
		ExtentManager.log("*********Logging out.......*******");
	}
}
