package utils;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import pages.BooksPage;
import pages.CartPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;

public class Commands extends BaseClass {
	
	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	HomePage homePage = pageObjectManager.getHomePage();
	LoginPage loginPage = pageObjectManager.getLoginPage();
	BooksPage booksPage = pageObjectManager.getBookPage();
	ProductsPage product = pageObjectManager.getProductsPage();
	CartPage cart = pageObjectManager.getCartPage();

	
	public void login(String email, String password) {
		ExtentManager.log("logging in ...");
		homePage.getLoginBtn().click();
		
		loginPage.getEmailField().sendKeys(email);
		loginPage.getPasswordField().sendKeys(password);
		loginPage.getLoginBtn().click();
	}

	public void addBookToCart() {
		ExtentManager.log("adding item to Cart ...");
		homePage.getBooksLink().click();
		
		booksPage.getComputingAndInternetBook().click();

		product.getquantyBtn().clear();
		product.getquantyBtn().sendKeys("1");
		product.getAddBookToCartBtn().click();

		WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(product.getConfirmationMsg()));
	}

	public void applyCopun(String discountCode) {
		homePage.getCartLink().click();
		cart.getQuantity().clear();
		cart.getQuantity().sendKeys("1");
		cart.getQuantity().sendKeys(Keys.ENTER);
		cart.getDiscountCodeField().sendKeys(discountCode);
		cart.getApplyCopunBtn().click();
	}
}
