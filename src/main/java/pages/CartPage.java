package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CartPage {

	private WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
	}
	
	private By discountCode = By.className("discount-coupon-code");
	private By applyCopunBtn = By.xpath("//input[@name=\"applydiscountcouponcode\"]");
	private By subTotalAmount = By.cssSelector(".cart-total tr:nth-of-type(1) .product-price");
	private By totalAmount = By.cssSelector(".product-price strong");
	private By disCountText = By.xpath("//span[contains(text(), 'Discount')]");
	private By checkoutBtn = By.cssSelector("button#checkout");
	private By termsClosingBtn = By.className("ui-button-icon-primary");
	private By termsMsg = By.cssSelector("p");
	private By agreeBtn = By.id("termsofservice");
	private By quatity = By.className("qty-input");
	
	public WebElement getDiscountCodeField() {
		return driver.findElement(discountCode);
	}
	
	public WebElement getApplyCopunBtn() {
		return driver.findElement(applyCopunBtn);
	}
	
	public WebElement getSubTotalAmount() {
		return driver.findElement(subTotalAmount);
	}
	
	public WebElement getTotalAmount() {
		return driver.findElement(totalAmount);
	}
	
	public WebElement getDiscountText() {
		return driver.findElement(disCountText);
	}
	
	public WebElement getCheckoutBtn() {
		return driver.findElement(checkoutBtn);
	}
	
	public WebElement getTermsClosingBtn() {
		return driver.findElement(termsClosingBtn);
	}
	
	public WebElement getTermsAndCondtionsMsg() {
		return driver.findElement(termsMsg);
	}
	
	public WebElement getAgreeBtn () {
		return driver.findElement(agreeBtn);
	}
	
	public WebElement getQuantity() {
		return driver.findElement(quatity);
	}
	
	
}
