package Practice.SelFramworkDsgn.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SelFramworkDsgn.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{
	
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(css = ".totalRow  button")
	WebElement checkOutEle;
	
	
	@FindBy(css = ".cartSection h3")
	private List<WebElement> cartProducts;
	
	public Boolean VerifyProductDisplay(String productName) {
		Boolean match = cartProducts.stream().anyMatch(Product -> Product.getText().equalsIgnoreCase(productName));
		return match;
	}
	
	public CheckOutPage goTocheckOut( ) {
		checkOutEle.click();
		return new CheckOutPage(driver);
	
	}
	

//	driver.findElement(By.cssSelector(".totalRow  button")).click();
}
