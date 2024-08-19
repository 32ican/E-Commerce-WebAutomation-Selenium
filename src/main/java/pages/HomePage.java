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
	private By cartLink =By.cssSelector("a[href='/cart']");
	private By logOutBtn = By.linkText("Log out");
	

	// methods
	public void clickRegister() {
		 driver.findElement(registerBtn).click();
	}



	public void  clickLogin() {
		driver.findElement(loginBtn).click();;
	}
	public String getAccountConfirmationMsg() {
		return driver.findElement(account).getText();
	}

	public WebElement getBooksLink() {
		return driver.findElement(books);
	}
	
	public void navigateToCart() {
		 driver.findElement(cartLink).click();
	}

	public void clickLogOutBtn() {
		 driver.findElement(logOutBtn).click();
	}
}
