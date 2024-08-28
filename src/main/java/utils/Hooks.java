package utils;


import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.HomePage;



public class Hooks extends BaseClass {
	
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	HomePage homePage = pageObjectManager.getHomePage();

	@BeforeClass(alwaysRun = true)
	public void setUp() {
		String url = ConfigLoader.getProperty("url");
		getDriver().get(url);
	}

	@AfterMethod
	public void logOUt() {
		// logout and quit the browser
		try {
			HomePage home = new HomePage(getDriver());
			home.getLogOutBtn().click();
		} catch (Exception e) {
			e.getStackTrace();
		}
		getDriver().close();
	}

	@AfterClass(alwaysRun = true)
	public void tearDown() {
		WebDriverManager.cleanupDriver();
	}

	
}
