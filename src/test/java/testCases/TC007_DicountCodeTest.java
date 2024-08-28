package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.CartPage;
import utils.Commands;
import utils.ConfigLoader;
import utils.Hooks;
import utils.PageObjectManager;

public class TC007_DicountCodeTest extends Hooks {

	PageObjectManager pageObjectManager = new PageObjectManager(getDriver());
	CartPage cart = pageObjectManager.getCartPage();
	
	private String email = ConfigLoader.getProperty("email");
	private String password = ConfigLoader.getProperty("password");
	private String discountCode = ConfigLoader.getProperty("discountCode");

	@Test(groups = { "Regression" })
	public void addDiscountCodeTest() {
		Commands commands = new Commands();
		commands.login(email, password);
		commands.addBookToCart();
		commands.applyCopun(discountCode);

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
}
