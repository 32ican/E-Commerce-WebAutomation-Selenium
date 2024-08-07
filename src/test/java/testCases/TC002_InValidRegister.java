package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.HomePage;
import pages.RegisterPage;
import pages.RegisterResultPage;
import utils.ConfigLoader;

public class TC002_InValidRegister extends Hooks{
	
	private String gender = ConfigLoader.getProperty("gender");
	private String firstName = ConfigLoader.getProperty("firstName");
	private String lastName = ConfigLoader.getProperty("LastName");
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	
	private String expected = ConfigLoader.getProperty("unsuccessfulRegisterMsg");
	private String actual;

	@Test(groups = {"Regression"})
	public void registerExsitingCustomerTest() {
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
		actual = registerResultPage.getUnsuccessfulRegisterMsg().getText();
		
		Assert.assertEquals(actual, expected);
	}
	
}
