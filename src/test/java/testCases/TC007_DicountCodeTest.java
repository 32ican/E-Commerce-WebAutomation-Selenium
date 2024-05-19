package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.Hooks;
import pages.CartPage;

public class TC007_DicountCodeTest extends Hooks {

	private String email = getProperty("email");
	private String password = getProperty("password");
	private String discountCode = getProperty("discountCode");

	@Test(groups = { "Regression" })
	public void addDiscountCodeTest() {

		login(email, password);
		addBookToCart();
		applyCopun(discountCode);

		CartPage cart = new CartPage(getDriver());
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
