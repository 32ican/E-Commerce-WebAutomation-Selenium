package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterResultPage {

	private WebDriver driver;
	
	// Locators
	private By SuccessfulRegisterMsg = By.cssSelector(".result"); 
	private By UnsuccessfulRegisterMsg = By.cssSelector(".validation-summary-errors");
	
	// Constructor
	public RegisterResultPage(WebDriver driver) {
		this.driver = driver;
	}
	
	//Methods
	
	public WebElement getSuccessfulRegisterMsg () {
		return driver.findElement(SuccessfulRegisterMsg);
	}
	
	public WebElement getUnsuccessfulRegisterMsg () {
		return driver.findElement(UnsuccessfulRegisterMsg);
	}
}
