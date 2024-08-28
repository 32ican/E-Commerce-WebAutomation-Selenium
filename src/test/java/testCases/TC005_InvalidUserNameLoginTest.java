package testCases;

import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;


public class TC005_InvalidUserNameLoginTest extends Hooks{
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	HomePage homePage = pageObjectManager.getHomePage();
	LoginPage loginPage = pageObjectManager.getLoginPage();
	
	private String email = "not" + ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String expected = ConfigLoader.getProperty("failedLoginMsg");
	
	@Test (groups = {"Regression"})
	public void LoginWithValidUserNameAndInvalidPasswordTest() {
	
		homePage.getLoginBtn().click();
		
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
		
		String actual = loginPage.getFailledLoginErrorMsg().getText();
		Assert.assertEquals(expected, actual);
	}

}
