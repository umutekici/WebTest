import Model.LoginPage;
import Model.SearchProduct;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import static java.util.concurrent.TimeUnit.SECONDS;
public class TestWebAutomation {
    public static WebDriver driver;
    private static String loginUrl;
    protected static String BaseUrl = "https://www.n11.com/";
    public static String email = "flynnb41@zwwaltered.com";
    public static String password = "1q2w3e4r5t";
    public static LoginPage loginPage;
    public static SearchProduct searchProduct;

    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\umut_\\Desktop\\chromedriver.exe");
        driver = new ChromeDriver();
        loginUrl = "https://www.n11.com/giris-yap";
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().timeouts().pageLoadTimeout(200, SECONDS);
        loginPage = new LoginPage(driver);
        searchProduct = new SearchProduct(driver);
    }

    @Test
    public void TestWeb() throws Exception {

        //open home page operation
        driver.get(BaseUrl);
        Assert.assertEquals(driver.getTitle(), "n11.com - Alışverişin Uğurlu Adresi");
        System.out.println("Home Page passed");
        driver.get(loginUrl);

        //login operation
        loginPage.Email().clear();
        loginPage.Email().sendKeys(email);
        loginPage.Password().clear();
        loginPage.Password().sendKeys(password);
        loginPage.Login().click();
        String actualUrl = "https://www.n11.com/";
        String expectedUrl = driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl, actualUrl);
        System.out.println("Login Page passed");
        Thread.sleep(2000);

        //search word operation
        driver.findElement(By.cssSelector("input#searchData")).clear();
        Thread.sleep(2000);
        searchProduct.SearchWord().sendKeys("bilgisayar");
        Thread.sleep(2000);
        searchProduct.SearchData().click();
        Thread.sleep(2000);

        //open Second Page Operations
        driver.findElement(By.linkText("2")).click();
        Thread.sleep(2000);

        //choose Random Product Operations
        searchProduct.ChooseRandomProduct();
        Thread.sleep(2000);

        //prices comparison operations
        String productPagePrice = driver.findElement(By.cssSelector("div.newPrice")).getText();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a.btn.btnGrey.btnAddBasket")).click();
        Thread.sleep(2000);
        driver.findElement(By.cssSelector("a.myBasket")).click();
        String basketPrice = driver.findElement(By.cssSelector("div.priceArea")).getText() + "\n" + "KDV\n" + "DAHİL";
        Assert.assertEquals(productPagePrice, basketPrice);
        System.out.println("Two Prices Equal passed");
        Thread.sleep(2000);

        //number increase operations
        driver.findElement(By.cssSelector("span.spinnerUp.spinnerArrow")).click();
        String expectedNumber=driver.findElement(By.cssSelector("input.quantity")).getAttribute("value");
        Assert.assertEquals(expectedNumber,"2");
        System.out.println("Two Numbers Equal passed");
        Thread.sleep(2000);

        //basket control operations
        driver.findElement(By.cssSelector("span.removeProd.svgIcon.svgIcon_trash")).click();
        Thread.sleep(2000);
        searchProduct.BasketControl();
    }
    @After
    public void tearDown() throws Exception{
       //driver.quit();
    }
}
