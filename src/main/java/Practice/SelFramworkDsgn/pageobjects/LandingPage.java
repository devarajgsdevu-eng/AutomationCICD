package Practice.SelFramworkDsgn.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Practice.SelFramworkDsgn.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {

	WebDriver driver; // local variable

	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver = driver; // pointing to local variable
		PageFactory.initElements(driver, this);
	}

//	WebElement userEmail = driver.findElement(By.id("userEmail"));
	// PageFactory

	@FindBy(id = "userEmail")
	WebElement userEmail;
	
	@FindBy(id = "userPassword")
	WebElement passwordEle;
	
	@FindBy(id = "login")
	WebElement submit;
//	.ng-tns-c4-41.ng-star-inserted.ng-trigger.ng-trigger-flyInOut.ngx-toastr.toast-error
	
	@FindBy(css = "[class*='flyInOut']")
	WebElement errormessage;
	
	public String getErrorMessage() {
		waitForWebElementToAppear(errormessage);
		return errormessage.getText();
	}
	
	public ProductCatalog loginApplication(String email, String password) {
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalog productcatlog = new ProductCatalog(driver);
		return productcatlog;
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}
}
