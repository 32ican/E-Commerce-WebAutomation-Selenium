package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ComputersPage {
	
	private WebDriver driver;
	
	public ComputersPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By computers = By.linkText("COMPUTERS");
	private By desktops = By.linkText("Desktops");
	private By simpleComputer = By.linkText("Simple Computer");
	private By processorRadioBtn = By.id("product_attribute_75_5_31_96");
	
	public WebElement getComputers() {
		return driver.findElement(computers);
	}
	
	public WebElement getDesktops() {
		return driver.findElement(desktops);
	}
	
	public WebElement getSimpleComputer() {
		return driver.findElement(simpleComputer);
	}
	
	public WebElement getProcessorRadioBtn() {
		return driver.findElement(processorRadioBtn);
	}
	
}
