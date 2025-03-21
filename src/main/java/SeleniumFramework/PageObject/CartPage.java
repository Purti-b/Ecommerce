package SeleniumFramework.PageObject;

import SeleniumFramework.ReusableComponent.ReusableComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends ReusableComponent {

    WebDriver driver;

    public CartPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@class='cartSection']/h3")
    List<WebElement> cartProducts;

    @FindBy(css = ".subtotal button")
    WebElement goToCheckOut;

    public Boolean verifyProductDisplay(String productName) throws InterruptedException {
        Boolean productPresent = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().contentEquals(productName));
        return productPresent;
    }

    public CheckOutPage goToCheckOut() throws InterruptedException {
        goToCheckOut.click();
        waitForElementToDisappear();
        return new CheckOutPage(driver);
    }
    
}
