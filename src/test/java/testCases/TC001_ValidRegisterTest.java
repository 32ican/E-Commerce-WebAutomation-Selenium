package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.HomePage;
import pages.RegisterPage;
import pages.RegisterResultPage;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;

public class TC001_ValidRegisterTest extends Hooks{
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	HomePage homePage = pageObjectManager.getHomePage();
	RegisterPage registerPage = pageObjectManager.getRegisterPage();
	RegisterResultPage registerResultPage = pageObjectManager.getRegisterResultPage();
	
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
	
		homePage.getRegisterBtn().click();

		registerPage.getGenderRadioBtn(gender).click();
		registerPage.getFirstNameField().sendKeys(firstName);
		registerPage.getLastNameField().sendKeys(lastName);
		registerPage.getEmailField().sendKeys(email);
		registerPage.getPasswordField().sendKeys(password);
		registerPage.getConfirmPasswordField().sendKeys(password);
		registerPage.getRegisterBtn().click();
		
		// Verification
		
		actual = registerResultPage.getSuccessfulRegisterMsg().getText();
		
		Assert.assertEquals(actual, expected);
	}
	
}
