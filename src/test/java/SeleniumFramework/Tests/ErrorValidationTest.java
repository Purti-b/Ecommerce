package SeleniumFramework.Tests;

import SeleniumFramework.PageObject.CartPage;
import SeleniumFramework.PageObject.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidationTest extends BaseTest {

    @Test

    public void incorrectCredentials() throws InterruptedException, IOException {

            landingPage.loginApplication("purtib@gmail.com","Purti@2278");
            String expected = landingPage.errorValidation();
            Assert.assertEquals(expected,"Incorrect email or password.");

    }

    @Test
    public void productErrorValidation() throws InterruptedException, IOException {

        String productName = "ZARA COAT 3";
        //WebDriverManager.chromedriver().setup();

        ProductCatalogue productCatalogue = landingPage.loginApplication("purti@gmail.com", "Purti@2278");

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCart();

        Boolean productPresent = cartPage.verifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(productPresent);

    }

}
