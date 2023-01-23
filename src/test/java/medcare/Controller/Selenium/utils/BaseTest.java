package medcare.Controller.Selenium.utils;

import org.apache.commons.lang3.SystemUtils;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    protected static WebDriver driver;
    protected static String URL = "http://localhost:8080";

    @Before
    public void setUp() throws InterruptedException {

        driver = getDriver();
        driver.get(URL);
    }

    @After
    public void tearDown() {
        driver.quit();
        driver = null;
    }

    public static WebDriver getDriver() {
//        if (driver == null) {
//            WebDriverManager.firefoxdriver().setup();
//
//            FirefoxProfile profile = new FirefoxProfile();
//            FirefoxOptions firefoxOptions = new FirefoxOptions();
//            firefoxOptions.setCapability("marionette", true);
//            firefoxOptions.setProfile(profile);
//            firefoxOptions.setHeadless(false);
//
//
//            driver = new FirefoxDriver(firefoxOptions);
//            driver.manage().window().maximize();
//            driver.get(URL);
//        }
        org.openqa.selenium.chrome.ChromeOptions chrome_options = new ChromeOptions();
//         chrome_options.addArguments("--headless", "--window-size=1920x1080");
        if(SystemUtils.IS_OS_WINDOWS){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_win32_96/chromedriver.exe").toString());
        }
        else if (SystemUtils.IS_OS_MAC){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_mac64_m1_96/chromedriver").toString());
        }
        else if (SystemUtils.IS_OS_LINUX){
            System.setProperty("webdriver.chrome.driver",
                    Paths.get("src/test/resources/chromedriver_linux64_96/chromedriver").toString());
        }
        if (driver == null)
            driver = new ChromeDriver(chrome_options);

//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

}

