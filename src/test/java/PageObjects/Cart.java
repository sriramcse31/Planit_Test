package PageObjects;

import Reusables.ReuseMethods;
import TestObjects.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Cart extends ReuseMethods {

    private WebDriver driver;
    private By itemsInCart = By.xpath("//tr[@class='cart-item ng-scope']");
    private By btnEmptyCart = By.cssSelector("a.btn.btn-danger.ng-scope");
    private By btnAccept = By.cssSelector("div.popup.modal.hide.ng-scope.in div a.btn.btn-success");
    private By cartTotal = By.cssSelector("strong.total.ng-binding");
    private By tableHeaders = By.tagName("th");

    ArrayList<String> columns = new ArrayList<String>();

    public Cart(WebDriver driver){
        this.driver = driver;
    }

    public void checkTheColumns()
    {
        for (WebElement th:driver.findElements(tableHeaders)) {
            columns.add(th.getText());
        }
    }

    public void verifyProductCountInCart(String productName, int quantity){
        String cartItemName="";
        Double price = 0D;
        int itemCount = 0;

        for (WebElement item : driver.findElements(itemsInCart)) {
            cartItemName = item.findElements(By.tagName("td")).get(columns.indexOf("Item")).getText();
            price = Double.valueOf(item.findElements(By.tagName("td")).get(columns.indexOf("Price")).getText().replace("$",""));
            itemCount = Integer.parseInt(item.findElements(By.tagName("input")).get(0).getAttribute("value"));

            if(cartItemName.equals(productName)){
                Assert.assertEquals( quantity, itemCount, "Product "+ productName + " is added "+ quantity +" times as expected");
                break;
            }
        }
    }

    public void emptyCart()
    {
        driver.findElement(btnEmptyCart).click();
        driver.findElement(btnAccept).click();
    }

    public void printItemsInCart()
    {
        System.out.println(driver.findElements(itemsInCart).size());
    }

    public void verifyPricesOfItemsInCart(List<Product> products){
        String cartItemName="";
        Double price = 0D;
        int itemCount = 0;

        for (WebElement item : driver.findElements(itemsInCart)) {
            cartItemName = item.findElements(By.tagName("td")).get(columns.indexOf("Item")).getText();
            price = Double.valueOf(item.findElements(By.tagName("td")).get(columns.indexOf("Price")).getText().replace("$",""));
            itemCount = Integer.parseInt(item.findElements(By.tagName("input")).get(0).getAttribute("value"));

            for(Product prd:products)
            {
                if(prd.productName.equals(cartItemName))
                {
                    Assert.assertEquals(prd.productPrice, price, "Product "+ cartItemName +"'s price is validated again shopping page");
                    break;
                }
            }
        }
    }

    public void verifySubTotals(){
        String cartItemName="";
        Double price = 0D;
        int itemCount = 0;
        Double subTotal = 0D;
        Double expectedTotal = 0D;

        for (WebElement item : driver.findElements(itemsInCart)) {
            cartItemName = item.findElements(By.tagName("td")).get(columns.indexOf("Item")).getText();
            price = Double.valueOf(item.findElements(By.tagName("td")).get(columns.indexOf("Price")).getText().replace("$",""));
            itemCount = Integer.parseInt(item.findElements(By.tagName("input")).get(0).getAttribute("value"));
            subTotal = Double.valueOf(item.findElements(By.tagName("td")).get(columns.indexOf("Subtotal")).getText().replace("$",""));

            expectedTotal = itemCount * price;

            Assert.assertEquals(expectedTotal, subTotal,  "Product "+ cartItemName +"'s price is validated again shopping page");
        }
    }

    public void verifyTotal()
    {
        String cartItemName="";
        Double price = 0D;
        int itemCount = 0;
        Double subTotal = 0D;
        Double expectedTotal = 0D;

        for (WebElement item : driver.findElements(itemsInCart)) {
            subTotal = Double.valueOf(item.findElements(By.tagName("td")).get(columns.indexOf("Subtotal")).getText().replace("$",""));
            expectedTotal += subTotal;
        }

        Assert.assertEquals(Double.valueOf(driver.findElement(cartTotal).getText().replace("Total: ","")), expectedTotal, "Validating the total value of the cart");
    }
}
