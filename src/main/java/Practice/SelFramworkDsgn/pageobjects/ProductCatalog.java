package Practice.SelFramworkDsgn.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Practice.SelFramworkDsgn.AbstractComponents.AbstractComponent;

public class ProductCatalog extends AbstractComponent {

	WebDriver driver; // local variable

	public ProductCatalog(WebDriver driver) {
		super(driver);
		this.driver = driver; // pointing to local variable
		PageFactory.initElements(driver, this);
	}

	//List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
	@FindBy(css = ".col-lg-4")
	List<WebElement> products;
	
	@FindBy(css = ".ng-animating")
	WebElement spinner;
	
	
	
	By productsBy = By.cssSelector(".col-lg-4");
	By addToCart = By.cssSelector(".card-body button:last-of-type");
	By toastMessage = By.cssSelector("#toast-container");
	
	public List<WebElement> getProducts() {
		waitForElementToAppear(productsBy);
		return products;
	}
	
	public WebElement getProductByName(String productName) {

		WebElement prod = getProducts().stream()
				.filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		return prod;
	}
	
	public void AddProductToCart(String productName) throws InterruptedException {
		WebElement prod = getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastMessage);
		waitForElementToDissappear(spinner);
		
	}

}
