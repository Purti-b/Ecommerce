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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData",groups = "Purchase")

    public void submitOrder(String email, String password, String productName) throws InterruptedException, IOException {
        //WebDriverManager.chromedriver().setup();

        ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);

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

    @Test(dependsOnMethods = "submitOrder",dataProvider = "getData")
    public void viewOrder(String email, String password, String productName) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);
        OrderPage orderPage = productCatalogue.goToOrder();

        Boolean match = orderPage.verifyOrderDisplay(productName);
        Assert.assertTrue(match);
    }

    @DataProvider
    public Object[][] getData() {
        return new Object[][] {{"purti@gmail.com","Purti@2278","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
    }

}
