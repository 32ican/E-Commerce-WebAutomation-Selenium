package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import base.BaseClass;
import pages.CartPage;
import pages.HomePage;
import utils.ExtentManager;
import utils.PageObjectManager;

import org.openqa.selenium.Keys;
import org.testng.Assert;

public class DiscountCodSteps extends BaseClass {

	PageObjectManager pageOpjectManager = new PageObjectManager(driver);

	HomePage homePage = pageOpjectManager.getHomePage();
	CartPage cart  = pageOpjectManager.getCartPage();

    @When("^user go to shopping cart$")
    public void user_go_to_shopping_cart() throws Throwable {
    
		homePage.getCartLink().click();
		cart.getQuantity().clear();
		cart.getQuantity().sendKeys("1");
		cart.getQuantity().sendKeys(Keys.ENTER);
    }

    @And("^give the discount code \"([^\"]*)\"$")
    public void give_the_discount_code(String code) throws Throwable {
    	ExtentManager.log("Applying discount code.....");
    
		cart.getDiscountCodeField().sendKeys(code);
		cart.getApplyCopunBtn().click();
    }


    // assertion that confirmation message will appear that the discount was applied
    @Then("^user could see a confirmation message contains: \"([^\"]*)\"$")
    public void user_could_see_a_confirmation_message_contains_something(String message) throws Throwable {
    	
    	double subTotal;
		double total;
		double portion = .1;
		try {
			subTotal = Double.parseDouble(cart.getSubTotalAmount().getText());
			total = Double.parseDouble(cart.getTotalAmount().getText());
			portion = total / subTotal;

		} catch (NumberFormatException e) {
			e.getMessage();
		}
		
		String discount = cart.getDiscountText().getText().toLowerCase();
		Assert.assertTrue(portion == .8 && discount.contains("discount"));
    }

    // assert that the discount is 20% / (discount / total should be -0.2)
    @And("^the discount is 20%$")
    public void the_discount_is_20() throws Throwable {
    	
    	String discount = cart.getDiscountText().getText().toLowerCase();
    	Assert.assertTrue(discount.contains("discount"));
    }
}
