package testCases;

import org.testng.annotations.Test;

import base.Hooks;
import org.testng.Assert;
import pages.HomePage;
import pages.LoginPage;

public class TC005_InvalidUserNameLoginTest extends Hooks{
	
	private String email = "not" + getProperty("email");
	private String password = getProperty("password");
	private String expected = getProperty("failedLoginMsg");
	
	@Test (groups = {"Regression"})
	public void LoginWithValidUserNameAndInvalidPasswordTest() {
		HomePage homePage = new HomePage(getDriver());
		homePage.getLoginBtn().click();
		
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
		
		String actual = loginPage.getFailledLoginErrorMsg().getText();
		Assert.assertEquals(expected, actual);
	}

}
