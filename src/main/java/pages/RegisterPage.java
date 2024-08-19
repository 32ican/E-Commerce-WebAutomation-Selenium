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

	public void register(String firstname, String lastname, String email, String password, String confirmPassword) {
		getFirstNameField().sendKeys(firstname);
		getLastNameField().sendKeys(lastname);
		getEmailField().sendKeys(email);
		getPasswordField().sendKeys(password);
		getConfirmPasswordField().sendKeys(password);

	}

	public void clickRegisterBtn() {
		 driver.findElement(registerBtn).click();
	}

	public WebElement enterGender(String gender) {
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

}
