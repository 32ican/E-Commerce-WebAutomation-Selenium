package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.HomePage;
import pages.RegisterPage;
import pages.RegisterResultPage;
import utils.ConfigLoader;

public class TC001_ValidRegisterTest extends Hooks{
	
	private String gender = ConfigLoader.getProperty("gender");
	private String firstName = ConfigLoader.getProperty("firstName");
	private String lastName = ConfigLoader.getProperty("LastName");
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	
	private String expected = ConfigLoader.getProperty("SuccessfulRegisterMsg");
	private String actual;

	@Test(groups = {"Smoke"})
	public void registerNewCustomerTest() {
		//test steps
		HomePage homePage = new HomePage(getDriver());
		homePage.getRegisterBtn().click();
		
		RegisterPage registerPage = new RegisterPage(getDriver());
		registerPage.getGenderRadioBtn(gender).click();
		registerPage.getFirstNameField().sendKeys(firstName);
		registerPage.getLastNameField().sendKeys(lastName);
		registerPage.getEmailField().sendKeys(email);
		registerPage.getPasswordField().sendKeys(password);
		registerPage.getConfirmPasswordField().sendKeys(password);
		registerPage.getRegisterBtn().click();
		
		// Verification
		RegisterResultPage registerResultPage = new RegisterResultPage(getDriver());
		actual = registerResultPage.getSuccessfulRegisterMsg().getText();
		
		Assert.assertEquals(actual, expected);
	}
	
}
