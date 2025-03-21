package SeleniumFramework.Tests;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import SeleniumFramework.PageObject.*;
import SeleniumFramework.TestComponents.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {

    @Test

    public void submitOrder() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";
        //WebDriverManager.chromedriver().setup();

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

    }

}
