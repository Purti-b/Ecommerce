package SeleniumFramework.PageObject;

import SeleniumFramework.ReusableComponent.ReusableComponent;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class OrderPage extends ReusableComponent {

    WebDriver driver;

    public OrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }


    @FindBy(css = "tr td:nth-child(3)")
    List<WebElement> verifyOrders;

    public Boolean verifyOrderDisplay(String productName) throws InterruptedException {
        Boolean match = verifyOrders.stream().anyMatch(verifyOrder -> verifyOrder.getText().contentEquals(productName));
        return match;
    }

}
