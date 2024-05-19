package base;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import pages.BooksPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import utils.ExtentManager;
import utils.WebDriverFactory;

public class Hooks extends BaseClass {

	@BeforeClass(alwaysRun = true)
	public void openUrl() {
		getDriver().get(getProperty("url"));
	}

	@AfterClass(alwaysRun = true)
	public void quitDriver() {
		// logout and quit the browser
		try {
			HomePage home = new HomePage(getDriver());
			home.getLogOutBtn().click();

		} catch (Exception e) {
			e.getStackTrace();
		}
		getDriver().close();
		WebDriverFactory.cleanupDriver();
	}

	public void login(String email, String password) {
		 ExtentManager.log("logging in ...");
		HomePage homePage = new HomePage(getDriver());
		homePage.getLoginBtn().click();
		LoginPage loginPage = new LoginPage(getDriver());
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
	}

	public void addBookToCart() {
		ExtentManager.log("adding item to Cart ...");
		HomePage homePage = new HomePage(getDriver());
		homePage.getBooksLink().click();

		BooksPage booksPage = new BooksPage(getDriver());
		booksPage.getComputingAndInternetBook().click();

		ProductsPage product = new ProductsPage(getDriver());
		product.getquantyBtn().clear();
		product.getquantyBtn().sendKeys("1");
		product.getAddBookToCartBtn().click();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(product.getConfirmationMsg()));
	}

	public void applyCopun(String discountCode) {
		HomePage homePage = new HomePage(getDriver());
		homePage.getCartLink().click();

		CartPage cart = new CartPage(getDriver());
		cart.getQuantity().clear();
		cart.getQuantity().sendKeys("1");
		cart.getQuantity().sendKeys(Keys.ENTER);
		cart.getDiscountCodeField().sendKeys(discountCode);
		cart.getApplyCopunBtn().click();
	}
}
