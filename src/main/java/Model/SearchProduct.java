package Model;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.List;
import java.util.Random;
public class SearchProduct extends LoginPage {
    public SearchProduct(WebDriver driver){
        super(driver);
    }
    public WebElement SearchWord(){
        return driver.findElement(By.cssSelector("input#searchData"));
    }
    public WebElement SearchData(){
        return driver.findElement(By.cssSelector("a.searchBtn"));
    }
    public void ChooseRandomProduct(){
        Random rnd = new Random();
        int index = rnd.nextInt(28) + 1;
        List<WebElement> products=driver.findElements(By.cssSelector("h3.productName"));
        products.get(index).click();
    }
    public void BasketControl(){
        WebElement element=driver.findElement(By.cssSelector("h2.title"));
        if(element.isEnabled())
            System.out.println("Empty Basket");
        else
            System.out.println("Filled Basket");
    }
}
