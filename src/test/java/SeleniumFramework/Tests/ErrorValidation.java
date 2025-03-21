package SeleniumFramework.Tests;

import SeleniumFramework.PageObject.CartPage;
import SeleniumFramework.PageObject.CheckOutPage;
import SeleniumFramework.PageObject.PlacedOrderPage;
import SeleniumFramework.PageObject.ProductCatalogue;
import SeleniumFramework.TestComponents.BaseTest;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

public class ErrorValidation extends BaseTest {

    @Test

    public void incorrectCredentials() throws InterruptedException, IOException {

            landingPage.loginApplication("purti@gmail.com","Purti@22788");
            String expected = landingPage.errorValidation();
            Assert.assertEquals(expected,"Incorrect email or password.");

    }

}
