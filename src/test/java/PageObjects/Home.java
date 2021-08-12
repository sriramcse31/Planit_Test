package PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Home {
    WebDriver driver;
    private By lnkContact = By.cssSelector("li#nav-contact");
    private By btnShop = By.cssSelector("a[class='btn btn-success btn-large']");

    public Home(WebDriver driver) throws Exception {
        this.driver = driver;
    }

    public void navigateToContactPage(){
        driver.findElement(lnkContact).click();
    }

    public void startShopping(){
        driver.findElement(btnShop).click();
    }
}
