package TestScripts;

import PageObjects.*;
import Reusables.Setup;
import TestObjects.Product;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class TestCases extends Setup {
    Home homepage;
    Contact contactPage;
    WebDriver driver;
    Shop productPage;
    Cart cartPage;

    @BeforeMethod
    public void InitiateApp() throws Exception {
        driver = setUp();
    }

    @Test
    public void TestCase1() throws Exception {
        homepage = new Home(driver);
        homepage.navigateToContactPage();

        contactPage = new Contact(driver);
        contactPage.submitEmptyFormAndValidate();
        contactPage.submitValidDataAndValidate();
    }

    @Test(invocationCount = 5)
    public void Testcase2() throws Exception {
        homepage = new Home(driver);
        homepage.navigateToContactPage();

        contactPage = new Contact(driver);
        contactPage.submitValidDataAndValidate();
        contactPage.navigateToHomePage();
    }

    @Test
    public void Testcase3() throws Exception
    {
        homepage = new Home(driver);
        homepage.startShopping();

        productPage = new Shop(driver);
        productPage.addCountProductWithName("Funny Cow",2);
        productPage.addCountProductWithName("Fluffy Bunny",1);
        productPage.navigateToCart();

        cartPage = new Cart(driver);
        cartPage.checkTheColumns();

        cartPage.verifyProductCountInCart("Funny Cow", 2);
        cartPage.verifyProductCountInCart("Fluffy Bunny", 1);
        cartPage.emptyCart();
    }

    @Test
    public void Testcase4() throws Exception {
        List<Product> products;

        homepage = new Home(driver);
        homepage.startShopping();

        productPage = new Shop(driver);
        products = productPage.saveAllItemDetails();

        productPage.addCountProductWithName("Stuffed Frog",2);
        productPage.addCountProductWithName("Fluffy Bunny",5);
        productPage.addCountProductWithName("Valentine Bear",3);
        productPage.navigateToCart();

        cartPage = new Cart(driver);
        cartPage.checkTheColumns();
        cartPage.verifyPricesOfItemsInCart(products);
        cartPage.verifySubTotals();
        cartPage.verifyTotal();
    }

    @AfterMethod
    public void teardown(){
        driver.quit();
    }
}


