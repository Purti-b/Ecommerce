package SeleniumFramework.ReusableComponent;

import SeleniumFramework.PageObject.CartPage;
import SeleniumFramework.PageObject.OrderPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ReusableComponent {

    WebDriver driver;
    public ReusableComponent(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "[routerlink*='cart']")
    WebElement cart;

    @FindBy(css = "[routerlink*='/dashboard/myorders']")
    WebElement order;

    public void waitForElementToAppear(By findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    }

    public void waitForWebElementToAppear(WebElement findBy){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(findBy));
    }

    public void waitForElementToDisappear() throws InterruptedException {

        Thread.sleep(2000);
    }

    public CartPage goToCart(){
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cart);
        return new CartPage(driver);
    }

    public OrderPage goToOrder(){
        order.click();
        return new OrderPage(driver);
    }
}

