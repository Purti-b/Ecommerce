package SeleniumFramework.Tests;

import java.time.Duration;
import java.util.List;

import SeleniumFramework.PageObject.LandingPage;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class StandAloneTest {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub

        String productName = "ZARA COAT 3";
        //WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/client");

        LandingPage lp = new LandingPage(driver);

        driver.findElement(By.id("userEmail")).sendKeys("purti@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Purti@2278");
        driver.findElement(By.id("login")).click();

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));

        List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
        WebElement prod =	products.stream().filter(product->
                product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
        prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        //ng-animating
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));

        WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*='cart']"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", cartButton);

        List<WebElement> cartProducts = driver.findElements(By.xpath("//*[@class='cartSection']/h3"));
        Boolean productPresent = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().contentEquals(productName));
        Assert.assertTrue(productPresent);

        driver.findElement(By.cssSelector(".subtotal button")).click();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
        WebElement country = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("input[placeholder='Select Country']")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()", country);

        //WebElement country=driver.findElement(By.cssSelector("input[placeholder=\"Select Country\"]"));
        country.click();
        country.sendKeys("india");

        //Actions a = new Actions(driver);
        //a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")), "india").build().perform();


        List<WebElement> countryNames = driver.findElements(By.cssSelector(".ta-item"));
        WebElement foundCountry = countryNames.stream().filter(countryName->
                countryName.findElement(By.cssSelector("span")).getText().equalsIgnoreCase("india")).findAny().orElse(null);
        //wait.until(ExpectedConditions.visibilityOf(foundCountry));
        foundCountry.click();

        //wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(By.cssSelector(".action__submit"))));

        WebElement placeOrder = driver.findElement(By.cssSelector(".action__submit"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].click()",placeOrder);


        String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        driver.close();

    }

}
