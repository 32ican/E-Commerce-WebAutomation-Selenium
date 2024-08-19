package pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class CheckoutPage {

	private WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
	}

	private By checkoutTitle = By.cssSelector("h1");
	private By billingAdressContinueBtn = By.cssSelector("#billing-buttons-container input");
	private By billingAdderssErrorMsgs = By.className("field-validation-error");
	private By country = By.id("BillingNewAddress_CountryId");
	private By city = By.id("BillingNewAddress_City");
	private By addres1 = By.id("BillingNewAddress_Address1");
	private By postalCode = By.id("BillingNewAddress_ZipPostalCode");
	private By phone = By.id("BillingNewAddress_PhoneNumber");
	private By shippingAddressContinueBtn = By.cssSelector("#shipping-buttons-container [title]");
	private By shippingMethodContinueBtn = By.xpath("//input[@onclick=\"ShippingMethod.save()\"]");
	private By creditRadioBtn = By.id("paymentmethod_2");
	private By continueToPaymentBtn = By.xpath("//input[@onclick=\"PaymentMethod.save()\"]");
	private By cardTypeMenu = By.id("CreditCardType");
	private By cardHolderName = By.id("CardholderName");
	private By cardNumber = By.id("CardNumber");
	private By expiratinoMonthMenu = By.id("ExpireMonth");
	private By expirationYearMenu = By.id("ExpireYear");
	private By cardCode = By.id("CardCode");
	private By continueToConfirmOrderBtn = By
			.xpath("//div[@id='payment-info-buttons-container']/input[@value='Continue']");
	private By confirmBtn = By.xpath("//input[@onclick=\"ConfirmOrder.save()\"]");
	private By orderConfirmationMsg = By.cssSelector(".title");

	public String getCheckoutTitle() {
		return driver.findElement(checkoutTitle).getText();
	}

	public void clickContinueAfterBillingAddress() {
		driver.findElement(billingAdressContinueBtn).click();
		;
	}

	public List<WebElement> getBillingAddressErrorMsgs() {
		return driver.findElements(billingAdderssErrorMsgs);
	}

	public List<String> getBillingAddressErrorMsgsAsString() {
		List<WebElement> elements = getBillingAddressErrorMsgs();
		List<String> errorMsgs = new ArrayList<>();
		for (int i = 0; i < elements.size(); i++) {
			errorMsgs.add(elements.get(i).getText());
			// System.out.println(elements.get(i).getText());
		}
		return errorMsgs;

	}

	public String getBillingAddressErrorMsgsAsString(int index) {

		return getBillingAddressErrorMsgsAsString().get(index);

	}

	public void selectCountry(String c) {
		Select selectCountry = new Select(driver.findElement(country));
		selectCountry.selectByVisibleText(c);

	}

	public void enterCity(String c) {
		driver.findElement(city).sendKeys(c);
		;
	}

	public void enterStreet(String street) {
		driver.findElement(addres1).sendKeys(street);
		;
	}

	public void enterPostalCode(String code) {
		driver.findElement(postalCode).sendKeys(code);
		;
	}

	public void enterPhoneNumber(String phoneNumber) {
		driver.findElement(phone).sendKeys(phoneNumber);
		;
	}

	public void clickShippingAddressContinueBtn() {
		driver.findElement(shippingAddressContinueBtn).click();
	}

	public void clickShippingMethodContinueBtn() {
		driver.findElement(shippingMethodContinueBtn).click();
	}

	public void clickCreditRadioBtn() {
		 driver.findElement(creditRadioBtn).click();
	}

	public void clickContinueToPaymentBtn() {
		 driver.findElement(continueToPaymentBtn).click();
	}

	public void enterCridetCardCredentials(String cartType,String cardHolder, String cardNumber, String expMonth,
			String expYear, String cardCode) {
		Select selectCardType = new Select(getCardTypeMenu());
		selectCardType.selectByVisibleText(cartType);

		getCardHolderField().sendKeys(cardHolder);
		getCardNumberField().sendKeys(cardNumber);

		Select selectMonth = new Select(getExpireMonthMenu());
		selectMonth.selectByVisibleText(expMonth);

		Select selectYear = new Select(getExpireYearMenu());
		selectYear.selectByVisibleText(expYear);

		getCardCodeField().sendKeys(cardCode);

	}
	
	public WebElement getCardTypeMenu() {
		return driver.findElement(cardTypeMenu);
	}

	public WebElement getCardHolderField() {
		return driver.findElement(cardHolderName);
	}

	public WebElement getCardNumberField() {
		return driver.findElement(cardNumber);
	}

	public WebElement getExpireMonthMenu() {
		return driver.findElement(expiratinoMonthMenu);
	}

	public WebElement getExpireYearMenu() {
		return driver.findElement(expirationYearMenu);
	}

	public WebElement getCardCodeField() {
		return driver.findElement(cardCode);
	}

	public void clickContinueToConfirmOrderBtn() {
		 driver.findElement(continueToConfirmOrderBtn).click();
	}

	public void clickConfirmBtn() {
		 driver.findElement(confirmBtn).click();
	}
	
	public WebElement getConfirmBtn() {
		return driver.findElement(confirmBtn);
	}

	public String getOrderConfirmationMsg() {
		return driver.findElement(orderConfirmationMsg).getText();
	}

}
