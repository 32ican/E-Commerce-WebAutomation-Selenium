package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
	
	private WebDriver driver;
	
	private By emailField = By.id("Email");
	private By passwordField = By.cssSelector("#Password");
	private By loginBtn = By.cssSelector(".button-1.login-button");
	private By FailledLoginErrorMsg = By.cssSelector(".validation-summary-errors span");
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
	}
	
//	public WebElement getPasswordField () {
//		return driver.findElement(passwordField);
//	}
	
	public void enterPassword (String password) {
		 driver.findElement(passwordField).sendKeys(password);;
		
	}
	
	
	public void enterEmail (String email) {
		 driver.findElement(emailField).sendKeys(email);;
	}
	
	public void clickLoginBtn () {
		 driver.findElement(loginBtn).click();
	}
	
	public String getFailledLoginErrorMsg () {
		return driver.findElement(FailledLoginErrorMsg).getText();
	}
	
}
