package org.pageClasses;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.*;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.pageClasses.ReadProperties.*;
import static org.pageClasses.SoftAssertion.assertAll;

public class BaseClass {
    public static WebDriver driver;
    public static WebDriverWait wait;
    public static JavascriptExecutor js;
    public static Actions act;

    public static void setUp() throws IOException {

        readPropertiesFile();
        String browserName = getBrowserName();
        if(browserName.equalsIgnoreCase("chrome")) {

            ChromeOptions chromeOptions = new ChromeOptions();
            WebDriverManager.chromedriver().driverVersion("120.0.6099.131").setup();
            driver = new ChromeDriver(chromeOptions);

        }else{

            WebDriverManager.edgedriver().driverVersion("120.0.2210.91").setup();
            driver = new EdgeDriver();

        }
    }

    public static void tearDown(){
        assertAll();
        driver.quit();
    }

    public static void launchURL() throws IOException {

        String url = getUrl();
        driver.get(url);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    public static WebElement dynamicallyDesignedXpath(String element,String variable){
        return driver.findElement(By.xpath(String.format(element,variable)));
    }

    public static void captureScreenshot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File src = ts.getScreenshotAs(OutputType.FILE);
        File dest = new File("C:\\Users\\VA338RM\\GitImports\\SeleniumLevel2Assignment\\Screenshot\\Snapshot_"+formattedDate()+".png");
        FileUtils.copyFile(src,dest);
    }

    public static String formattedDate(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY hhmmss");
        String formattedDate = sdf.format(d);
        return formattedDate;
    }
}
