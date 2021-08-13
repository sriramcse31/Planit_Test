package PageObjects;

import Reusables.ReuseMethods;
import Reusables.Setup;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.Reporter;

public class Contact extends ReuseMethods {

    private By btnSubmit = By.xpath("//a[normalize-space() = 'Submit']");
    private By txtForename = By.id("forename");
    private By txtSurname = By.id("surname");
    private By txtEmail = By.id("email");
    private By txtTelephone = By.id("telephone");
    private By txtMessage = By.id("message");
    private By errorMessage = By.cssSelector("div[class*='alert alert']");
    private By errForeName = By.id("forename-err");
    private By errEmail = By.id("email-err");
    private By errMessage = By.id("message-err");

    private By lnkHome = By.cssSelector("li#nav-home");

    WebDriver driver;

    public Contact(WebDriver driver) {
        this.driver = driver;
    }

    public void submitEmptyFormAndValidate() throws InterruptedException {
        driver.findElement(btnSubmit).click();

        Assert.assertEquals(driver.findElement(errForeName).isDisplayed(), true, "Error message for ForeName");
        Reporter.log("Error message for ForeName is visible");
        Assert.assertEquals(driver.findElement(errEmail).isDisplayed(), true, "Error message for Email");
        Reporter.log("Error message for Email is visible");
        Assert.assertEquals(driver.findElement(errMessage).isDisplayed(), true, "Error message for Message");
        Reporter.log("Error message for Message field is visible");
    }

    public void submitValidDataAndValidate()
    {
        driver.findElement(txtForename).sendKeys("Sriram");
        driver.findElement(txtSurname).sendKeys("Tester");
        driver.findElement(txtEmail).sendKeys("sri@test.com");
        driver.findElement(txtTelephone).sendKeys("98765432");
        driver.findElement(txtMessage).sendKeys("This is a test activity!!");

        driver.findElement(btnSubmit).click();

        waitForAnElementWithTextTobeVisible(driver, errorMessage, "we appreciate your feedback");
        Reporter.log("Form is successfully submitted and redirected!!");
    }

    public void navigateToHomePage(){
        driver.findElement(lnkHome).click();
    }
}