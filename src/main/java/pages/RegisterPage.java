package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class RegisterPage {

	private WebDriver driver;

	// Locators
	private By genderMaleRadioBtn = By.id("gender-male");
	private By genderFemaleRadioBtn = By.id("gender-female");
	private By firstNameField = By.id("FirstName");
	private By lastNameField = By.id("LastName");
	private By emailField = By.id("Email");
	private By passwordField = By.cssSelector("#Password");
	private By confirmPasswordField = By.cssSelector("#ConfirmPassword");
	private By registerBtn = By.xpath("//input[@id=\"register-button\"]");

	// constructor
	public RegisterPage(WebDriver driver) {
		this.driver = driver;
	}

	// Methods
	public WebElement getGenderRadioBtn(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			return driver.findElement(genderMaleRadioBtn);
		}

		return driver.findElement(genderFemaleRadioBtn);

	}

	public WebElement getFirstNameField() {
		return driver.findElement(firstNameField);
	}

	public WebElement getLastNameField() {
		return driver.findElement(lastNameField);
	}

	public WebElement getEmailField() {
		return driver.findElement(emailField);
	}

	public WebElement getPasswordField() {
		return driver.findElement(passwordField);
	}

	public WebElement getConfirmPasswordField() {
		return driver.findElement(confirmPasswordField);
	}

	public WebElement getRegisterBtn() {
		return driver.findElement(registerBtn);
	}

}
