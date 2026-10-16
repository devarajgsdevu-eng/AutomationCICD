package Practice.SelFramworkDsgn.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import Practice.SelFrameworkDesign.TestComponents.BaseTest;
import Practice.SelFramworkDsgn.pageobjects.CartPage;
import Practice.SelFramworkDsgn.pageobjects.CheckOutPage;
import Practice.SelFramworkDsgn.pageobjects.ConfirmationPage;
import Practice.SelFramworkDsgn.pageobjects.ProductCatalog;

public class ErrorValidationTest extends BaseTest
{

	@Test(groups={"ErrorHandling"}, retryAnalyzer= Practice.SelFrameworkDesign.TestComponents.Retry.class)
	public void  SubmitOrder() throws IOException, InterruptedException
	{
		landingpage.loginApplication("devaraj.s@happiestminds.com", "Welcoe@123");
		Assert.assertEquals("Incorrect email or password.", landingpage.getErrorMessage());
	}


	@Test
	public void  ProductErrorValidation() throws IOException, InterruptedException
	{
		String productName = "ZARA COAT 3";  
	
		ProductCatalog productcatlog = landingpage.loginApplication("niledevaraj@gmail.com", "Devu@123");
		// ProductCatalog productcatlog = new ProductCatalog(driver);
		List<WebElement> products = productcatlog.getProducts();
		productcatlog.AddProductToCart(productName);
		CartPage cartpage = productcatlog.goToCartPage();
		
		Boolean match = cartpage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
			

	}

}
