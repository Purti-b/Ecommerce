package Selenium.pageobject;

import ReusableComponent.ReusableComponent;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PlacedOrderPage extends ReusableComponent {

    WebDriver driver;

    public PlacedOrderPage(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(css = ".hero-primary")
    WebElement pageTitle;

    public String getTitle(){
        return pageTitle.getText();
    }
}
