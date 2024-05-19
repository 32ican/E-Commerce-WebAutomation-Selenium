package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {

	private WebDriver driver;

	// Constructor
	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	private By registerBtn = By.cssSelector(".ico-register");
	private By loginBtn = By.linkText("Log in");
	private By account = By.cssSelector(".header-links .account");
	private By books = By.cssSelector(".block-category-navigation [href='\\/books']");
	private By cartLink = By.linkText("shopping cart");
	private By logOutBtn = By.linkText("Log out");
	

	// methods
	public WebElement getRegisterBtn() {
		return driver.findElement(registerBtn);
	}

	public WebElement getLoginBtn() {
		return driver.findElement(loginBtn);
	}

	public WebElement getAccount() {
		return driver.findElement(account);
	}

	public WebElement getBooksLink() {
		return driver.findElement(books);
	}
	
	public WebElement getCartLink() {
		return driver.findElement(cartLink);
	}

	public WebElement getLogOutBtn() {
		return driver.findElement(logOutBtn);
	}
}
