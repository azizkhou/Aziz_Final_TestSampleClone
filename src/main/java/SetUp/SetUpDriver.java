package SetUp;

import PageObjects.HomePage;
import TestCases.HomePageTest;
import Utils.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import static TestCases.HomePageTest.prop;



public class SetUpDriver {
    public WebDriver driver;
    public HomePage homePage;
    public HomePageTest homePageTest;
    public WebDriverWait wait ;
    //Properties prop;




    @BeforeClass(alwaysRun = true)
    public void tearUp() {
        driver = launchBrowser(prop.getProperty("browser"));
        System.out.println("Launching Browser");
        driver.get(prop.getProperty("url"));
        homePage = new HomePage(driver);
        homePageTest = new HomePageTest(driver);
    }

    @BeforeMethod (alwaysRun = true)
    public void beforeMethod() {
        driver.get(prop.getProperty("url"));
        Util.holdExecution(2);
    }
    @AfterClass (alwaysRun = true)
    public void TearDown() {
        driver.quit();
        System.out.println("Closing Browser");
    }
    public static WebDriver launchBrowser(String browserName) {
        WebDriver driver;
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources" + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + File.separator + "resources" + File.separator + "drivers" + File.separator + "geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browserName.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else if (browserName.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", System.getProperty("user.dir") + File.separator + "resources" + File.separator + "drivers" + File.separator + "msedgedriver.exe");
            driver = new EdgeDriver();
        } else {
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + File.separator + "resources" + File.separator + "drivers" + File.separator + "chromedriver.exe");
            driver = new ChromeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
       WebDriverWait wait= new WebDriverWait(driver,10);

        return driver;
    }
 }




