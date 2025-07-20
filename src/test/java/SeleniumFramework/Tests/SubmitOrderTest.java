package SeleniumFramework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import SeleniumFramework.PageObject.*;
import SeleniumFramework.TestComponents.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SubmitOrderTest extends BaseTest {
    //String productName = "ZARA COAT 3";

    @Test(dataProvider = "getData",groups = "Purchase")

    public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
        //WebDriverManager.chromedriver().setup();

        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));

        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage = productCatalogue.goToCart();

        Boolean productPresent = cartPage.verifyProductDisplay(input.get("productName"));
        Assert.assertTrue(productPresent);
        CheckOutPage checkOutPage = cartPage.goToCheckOut();

        checkOutPage.selectCountry("india");
        PlacedOrderPage placedOrderPage = checkOutPage.checkOut();

        String confirmMessage = placedOrderPage.getTitle();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

    }

    @Test(dependsOnMethods = "submitOrder",dataProvider = "getData")
    public void viewOrder(HashMap<String,String> input) throws IOException, InterruptedException {
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        OrderPage orderPage = productCatalogue.goToOrder();

        Boolean match = orderPage.verifyOrderDisplay(input.get("productName"));
        Assert.assertTrue(match);
    }

    public String getScreenshot(String testCaseName) throws IOException {
        TakesScreenshot ts = (TakesScreenshot)driver;
        File source = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
        FileUtils.copyFile(source,dest);
        return (System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
    }

    //using Json
    @DataProvider
    public Object[][] getData() throws IOException {

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\test\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
        return new Object[][] {{data.get(0)},{data.get(1)}};
    }

    /*@DataProvider
    public Object[][] getData() {
        return new Object[][] {{"purti@gmail.com","Purti@2278","ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
    }*/

    //using hashmap
    /*@DataProvider
    public Object[][] getData() throws IOException {
        HashMap<String, String> map = new HashMap<>();
        map.put("email","purti@gmail.com");
        map.put("password","Purti@2278");
        map.put("productName","ZARA COAT 3");

        HashMap<String, String> map1 = new HashMap<>();
        map1.put("email","purti1@gmail.com");
        map1.put("password","Purti2278@");
        map1.put("productName","ADIDAS ORIGINAL");

        List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir") + "\\src\\main\\java\\SeleniumFramework\\data\\PurchaseOrder.json");
        return new Object[][] {{map},{map1}};
    }*/

}
