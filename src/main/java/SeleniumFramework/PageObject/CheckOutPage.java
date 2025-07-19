package SeleniumFramework.PageObject;

import SeleniumFramework.ReusableComponent.ReusableComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends ReusableComponent {

    WebDriver driver;

    public CheckOutPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = "input[placeholder='Select Country']")
    WebElement sendCountry;

    @FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
    WebElement selectCountry;

    @FindBy(css = ".action__submit")
    WebElement checkOut;

    By waitCountry = By.cssSelector(".ta-results");

    public void selectCountry(String country) throws InterruptedException {
        Actions a = new Actions(driver);
        a.sendKeys(sendCountry, country).build().perform();
        waitForElementToAppear(waitCountry);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", selectCountry);
        waitForElementToDisappear();
    }

    public PlacedOrderPage checkOut(){

        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkOut);
        return new PlacedOrderPage(driver);
    }
    
}
