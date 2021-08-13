package PageObjects;

import Reusables.ReuseMethods;
import TestObjects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;

import java.util.ArrayList;
import java.util.List;

public class Shop extends ReuseMethods {
    WebDriver driver;
    private By items =  By.cssSelector("h4.product-title.ng-binding");
    private By btnBuy = By.cssSelector("a.btn.btn-success");
    private By btnCart = By.id("nav-cart");
    private By priceTags = By.xpath("//span[@class='product-price ng-binding']");

    List<Product> products;

    public Shop(WebDriver driver) {
        this.driver = driver;
    }

    public void addCountProductWithName(String productName, int quantity) {
        int buttonCount = 0;
        for (WebElement item : driver.findElements(items)) {
            if (item.getText().equals(productName)) {
                for (int q = 0; q < quantity; q++)
                    driver.findElements(btnBuy).get(buttonCount).click();
                break;
            }
            buttonCount++;
        }
        Reporter.log(quantity +" quantity of item "+ productName+ " has been added to cart");
    }

    public void navigateToCart() {
        driver.findElement(btnCart).click();
    }

    public List<Product> saveAllItemDetails() {
        products = new ArrayList<Product>();
        String productName = "";
        double productPrice = 0D;

        for (WebElement item : driver.findElements(items)) {
            productName = item.getText();
            productPrice = Double.parseDouble(driver.findElements(priceTags).get(driver.findElements(items).indexOf(item)).getText().replace("$",""));
            products.add(new Product(productName, productPrice));
        }
        Reporter.log("All the items in the shopping page are added to cart!!");
        return products;
    }
}