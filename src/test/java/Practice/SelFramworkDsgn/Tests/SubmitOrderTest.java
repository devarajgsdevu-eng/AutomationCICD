package Practice.SelFramworkDsgn.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Practice.SelFrameworkDesign.TestComponents.BaseTest;
import Practice.SelFramworkDsgn.pageobjects.CartPage;
import Practice.SelFramworkDsgn.pageobjects.CheckOutPage;
import Practice.SelFramworkDsgn.pageobjects.ConfirmationPage;
import Practice.SelFramworkDsgn.pageobjects.LandingPage;
import Practice.SelFramworkDsgn.pageobjects.OrderPage;
import Practice.SelFramworkDsgn.pageobjects.ProductCatalog;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider = "getData", groups = { "purchase" },retryAnalyzer= Practice.SelFrameworkDesign.TestComponents.Retry.class)
	public void SubmitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalog productcatlog = landingpage.loginApplication(input.get("email"), input.get("password"));
		// ProductCatalog productcatlog = new ProductCatalog(driver);
		List<WebElement> products = productcatlog.getProducts();
		productcatlog.AddProductToCart(input.get("product"));
		CartPage cartpage = productcatlog.goToCartPage();
		Boolean match = cartpage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckOutPage checkoutpage = cartpage.goTocheckOut();
		checkoutpage.selectCountry("india");
		ConfirmationPage confirmationpage = checkoutpage.submitOrder();
		String confirmMessage = confirmationpage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}

//To verify ZARA COAT 3 is displayed on  order page.
	@Test(dependsOnMethods = {"SubmitOrder"})
	public void OrderHistoryTest() {
		ProductCatalog productcatlog = landingpage.loginApplication("devaraj.s@happiestminds.com", "Welcome@123");
		OrderPage orderspage = productcatlog.goToOrderPage();
		Assert.assertTrue(orderspage.VerifyOrderDisplay(productName));
	}
	

	
	

	@DataProvider
	public Object[][] getData() throws IOException {
		List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")
				+ "\\src\\test\\java\\Practice\\SelFrameworkDesign\\Data\\purchaseOrder.json");
		return new Object[][] { { data.get(0) }, { data.get(1) } };
	}

//	HashMap<String,String> map =new HashMap<String,String>();
//	map.put("email", "devaraj.s@happiestminds.com");
//	map.put("password", "Welcome@123");
//	map.put("product", "ZARA COAT 3");
//	
//	HashMap<String,String> map1 =new HashMap<String,String>();
//	map1.put("email", "niledevaraj@gmail.com");
//	map1.put("password", "Devu@123");
//	map1.put("product", "ADIDAS ORIGINAL");
}
