package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductsPage {

	private WebDriver driver;

	// Constructor
	public ProductsPage(WebDriver driver) {
		this.driver = driver;
	}

	// Locators
	By quantyBtn = By.xpath("//input[@name=\"addtocart_13.EnteredQuantity\"]");
	By addBookToCartBtn = By.id("add-to-cart-button-13");
	By addSimpleComputerToCartBtn = By.id("add-to-cart-button-75");
	By confirmationMsg = By.className("content");
	
	
	public WebElement getquantyBtn () {
		return driver.findElement(quantyBtn);
	}
	
	public WebElement getAddBookToCartBtn () {
		return driver.findElement(addBookToCartBtn);
	}
	
	public String getConfirmationMsg() {
		return driver.findElement(confirmationMsg).getText();
	}
	
	public WebElement confitmationMsg() {
		return driver.findElement(confirmationMsg);
	}
	
	public void addComputerToCart() {
		 driver.findElement(addSimpleComputerToCartBtn).click();
	}
	
	
	
}
