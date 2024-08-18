package stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.CartPage;
import utils.ConfigLoader;
import utils.PageObjectManager;

import org.testng.Assert;

import base.BaseClass;


public class TermsOfServiceVerificationSteps extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);
	CartPage cart  = pageOpjectManager.getCartPage();
	
    
    @When("^user try to check out the product$")
    public void user_try_to_check_out_the_product() throws Throwable {
		cart.getCheckoutBtn().click();
    }

    // assert that waring dialogue box will appear
    @Then("^warning box will appear contains: \"([^\"]*)\"$")
    public void warning_box_will_appear_contains_something(String warning) throws Throwable {
    	String expectedMsg = ConfigLoader.getProperty("termsAndConditionsMsg");
    	String actualMsg = cart.getTermsAndCondtionsMsg().getText();
		Assert.assertEquals(actualMsg ,expectedMsg, "Can not be check out without accepting terms of service");  
    }
}
