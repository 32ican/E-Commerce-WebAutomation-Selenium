package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import base.Hooks;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigLoader;
import utils.ExtentManager;

@Listeners(utils.Listeners.class)
public class TC003_ValidLoginTest extends Hooks{
	
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String expected = ConfigLoader.getProperty("email");
	
	
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
