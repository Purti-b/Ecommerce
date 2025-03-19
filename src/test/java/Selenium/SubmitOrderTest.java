package Selenium;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import ReusableComponent.ReusableComponent;
import Selenium.pageobject.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SubmitOrderTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        String productName = "ZARA COAT 3";
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        LandingPage landingPage = new LandingPage(driver);
        landingPage.goTo();
        ProductCatalogue productCatalogue = landingPage.loginApplication("purti@gmail.com","Purti@2278");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCart();

        Boolean productPresent = cartPage.verifyProductDisplay(productName);
        Assert.assertTrue(productPresent);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();

        checkOutPage.selectCountry("india");
        PlacedOrderPage placedOrderPage = checkOutPage.checkOut();

        String confirmMessage = placedOrderPage.getTitle();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        driver.close();

    }

}
