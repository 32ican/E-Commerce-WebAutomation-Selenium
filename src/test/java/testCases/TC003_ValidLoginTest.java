package testCases;

import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;
import utils.ConfigLoader;
import utils.ExtentManager;
import utils.Hooks;
import utils.PageObjectManager;

@Listeners(utils.Listeners.class)
public class TC003_ValidLoginTest extends Hooks{
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	HomePage homePage = pageObjectManager.getHomePage();
	LoginPage loginPage = pageObjectManager.getLoginPage();

	
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String expected = ConfigLoader.getProperty("email");
	
	
	@Test (groups = {"Smoke"})
	public void loginWithValidDataTest() {
		ExtentManager.log("logging in ...");
		homePage.getLoginBtn().click();
		
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
		
		String actual = homePage.getAccount().getText();
		Assert.assertEquals(expected, actual);
	}

}
