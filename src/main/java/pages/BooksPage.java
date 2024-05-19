package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BooksPage {
	
	private WebDriver driver;
	
	public BooksPage(WebDriver driver) {
		this.driver = driver;
	}
	//Locators:
	private By computingAndInternetBook = By.linkText("Computing and Internet");
	
	public WebElement getComputingAndInternetBook() {
		return driver.findElement(computingAndInternetBook);
	}
	
}
