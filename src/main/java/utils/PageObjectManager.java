package utils;

import org.openqa.selenium.WebDriver;
import pages.CartPage;
import pages.CheckoutPage;
import pages.ComputersPage;
import pages.HomePage;
import pages.LoginPage;
import pages.ProductsPage;
import pages.RegisterPage;
import pages.RegisterResultPage;
import pages.BooksPage;

public class PageObjectManager {

	private WebDriver driver;
	
	public PageObjectManager (WebDriver driver) {
		this.driver = driver;
	}
	
	public CartPage getCartPage() {
		return new CartPage(driver);
	}
	
	public CheckoutPage getCheckoutPage() {
		return new CheckoutPage(driver);
	}
	public ComputersPage getComputersPage() {
		return new ComputersPage(driver);
	}
	public LoginPage getLoginPage() {
		return new LoginPage(driver);
	}
	public HomePage getHomePage() {
		return new HomePage(driver);
	}
	public ProductsPage getProductsPage() {
		return new ProductsPage(driver);
	}
	public RegisterPage getRegisterPage() {
		return new RegisterPage(driver);
	}
	public RegisterResultPage getRegisterResultPage() {
		return new RegisterResultPage(driver);
	}
	
	public BooksPage getBookPage() {
		return new BooksPage(driver);
	}
	
	
}
