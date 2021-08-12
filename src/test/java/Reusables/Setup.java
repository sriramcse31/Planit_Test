package Reusables;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Setup {
    public String workingDir;
    public WebDriver driver;
    public Properties properties = new Properties();
    public FileInputStream fis ;

    public WebDriver setUp() throws Exception{
        workingDir = System.getProperty("user.dir");
        fis = new FileInputStream(workingDir + "\\src\\test\\resources\\datafile.properties");
        properties.load(fis);

        System.setProperty("webdriver.chrome.driver", workingDir + "\\src\\test\\resources\\ChromeDriver\\chromedriver.exe");
        driver = new ChromeDriver();
        String url = properties.getProperty("URL");

        driver.get(url);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();

        return driver;
    }



}
