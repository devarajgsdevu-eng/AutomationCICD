package Practice.StepDefinations;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import Practice.SelFrameworkDesign.TestComponents.BaseTest;
import Practice.SelFramworkDsgn.pageobjects.CartPage;
import Practice.SelFramworkDsgn.pageobjects.CheckOutPage;
import Practice.SelFramworkDsgn.pageobjects.ConfirmationPage;
import Practice.SelFramworkDsgn.pageobjects.LandingPage;
import Practice.SelFramworkDsgn.pageobjects.ProductCatalog;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinationImp extends BaseTest {

	public LandingPage landingPage;
	public ProductCatalog productcatlog;
	public CheckOutPage checkoutpage;
	public ConfirmationPage confirmationpage;

	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException {
		landingPage = launchApplication();
	}

	@Given("^logged in with username (.+) and password(.+)$")
	public void logged_in_username_and_password(String username, String password) {
		productcatlog = landingpage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_prduct_to_cart(String productName) throws InterruptedException {
		List<WebElement> products = productcatlog.getProducts();
		productcatlog.AddProductToCart(productName);
	}
	
	@When("Checkout (.+) and submit the order")
	public void checkout_submit_order(String prodcutName) {
		CartPage cartpage = productcatlog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(prodcutName);
		Assert.assertTrue(match);
		checkoutpage = cartpage.goTocheckOut();
		confirmationpage = checkoutpage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmation page.")
	public void message_displayed_confirmationpage(String string) {
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
	}
	
	@Then(" ^\"([^\"]*)\" message is displayed.")
	public void something_message_is_displayed(String strArg1) {
		Assert.assertEquals(strArg1, landingpage.getErrorMessage());
		driver.close();
	}
	
}
	
