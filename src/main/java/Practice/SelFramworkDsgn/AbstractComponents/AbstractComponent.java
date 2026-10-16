package Practice.SelFramworkDsgn.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Practice.SelFramworkDsgn.pageobjects.CartPage;
import Practice.SelFramworkDsgn.pageobjects.OrderPage;

public class AbstractComponent {

	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css = "[routerlink*='myorders']")
	WebElement orderHeader;

	// driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	public void waitForElementToAppear(By finBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(finBy));
	}

	public void waitForWebElementToAppear(WebElement finBy) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(finBy));
	}

	public CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartpage = new CartPage(driver);
		return cartpage;
	}

	public OrderPage goToOrderPage() {
		orderHeader.click();
		OrderPage orderpage = new OrderPage(driver);
		return orderpage;
	}

	public void scrollToView(WebElement ele) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView({block: 'center'});", ele);
//	js.executeScript("arguments[0].scrollIntoView(true)", w);
//		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".action__submit")));
//		w.click();
	}

	public void waitForElementToDissappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
	}
}
