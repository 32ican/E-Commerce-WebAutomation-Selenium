package stepDefinitions;

import org.testng.Assert;

import base.BaseClass;
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

public class RegistrationStep extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);

	HomePage home = new PageObjectManager(driver).getHomePage();
	RegisterPage registerPage = pageOpjectManager.getRegisterPage();
	RegisterResultPage registerResultPage = pageOpjectManager.getRegisterResultPage();
	
	
	@Given("^user at home page and clicks on register$")
    public void user_at_home_page_and_clicks_on_register() throws Throwable {

		HomePage homePage = new HomePage(driver);
		homePage.getRegisterBtn().click();
		
		
    }

    @When("^user enters (.+), (.+), (.+) and (.+)$")
    public void user_enters_and(String firstname, String lastname, String email, String password) throws Throwable {
    	
		//registerPage.getGenderRadioBtn(gender).click();
		registerPage.getFirstNameField().sendKeys(firstname);
		registerPage.getLastNameField().sendKeys(lastname);
		registerPage.getEmailField().sendKeys(email);
		registerPage.getPasswordField().sendKeys(password);
		registerPage.getConfirmPasswordField().sendKeys(password);

		
    }

    @Then("^User could register successfully$")
    public void user_could_register_successfully_and_confirmation_message_should_appear() throws Throwable {
 
    	registerPage.getRegisterBtn().click();
    }

    @And("^confirmation message should appear$")
    public void confirmation_messaage_should_appear (){
    	// verification
		String actual = registerResultPage.getSuccessfulRegisterMsg().getText();
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
    
    	registerPage.getRegisterBtn().click();
    	Thread.sleep(5000);
    }

    @And("error message should appear")
    public void error_message_should_appear(){
        String expected = ConfigLoader.getProperty("unsuccessfulRegisterMsg");
		String actual = registerResultPage.getUnsuccessfulRegisterMsg().getText();
		
		Assert.assertEquals(actual, expected);
    }
    
    public void logOut() {
		// logout
		try {
			
			home.getLogOutBtn().click();
		} catch (Exception e) {
			e.getStackTrace();
		}
		ExtentManager.log("*********Logging out.......*******");
	}
}
