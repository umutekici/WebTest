package Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
public class LoginPage {
    public static WebDriver driver;
    public LoginPage(WebDriver webDriver) {
        driver = webDriver;
    }
    public WebElement Email() {
        return driver.findElement(By.id("email"));
    }

    public WebElement Password() {
        return driver.findElement(By.id("password"));
    }

    public WebElement Login() {
        return driver.findElement(By.id("loginButton"));
    }

}