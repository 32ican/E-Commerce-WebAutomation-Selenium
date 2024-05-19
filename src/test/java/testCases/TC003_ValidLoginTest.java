package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import base.Hooks;
import pages.HomePage;
import pages.LoginPage;
import utils.ExtentManager;

@Listeners(utils.Listeners.class)
public class TC003_ValidLoginTest extends Hooks{
	
	private String email = getProperty("email");
	private String password = getProperty("password");
	private String expected = getProperty("email");
	
	
	@Test (groups = {"Smoke"})
	public void loginWithValidDataTest() {
		ExtentManager.log("logging in ...");
		HomePage homePage = new HomePage(getDriver());
		homePage.getLoginBtn().click();
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
		
		String actual = homePage.getAccount().getText();
		Assert.assertEquals(expected, actual);
	}

}
