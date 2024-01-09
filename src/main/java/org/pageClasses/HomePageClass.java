package org.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.pageClasses.SoftAssertion.verifyEquals;
import static org.pageClasses.SoftAssertion.verifyTrue;

public class HomePageClass extends BaseClass{
    public static String wb_closeAdPopUp = "//a[@class='close']";
    public static String wb_adFrame = "//iframe[contains(@id,'notification-frame')]";
    public static String wb_mmtLogo = "//a[@data-cy='mmtLogo']/picture/img";
    public static String wb_superOffers = "//p[text()='Super Offers']";
    public static String wb_businessTravel = "//p[text()='Business Travel Solution']";
    public static String wb_myTrips = "//p[text()='My Trips']";
    public static String wb_loginHeaderText = "//p[text()='Login or Create Account']";
    public static String homePageTitle = "MakeMyTrip - #1 Travel Website 50% OFF on Hotels, Flights & Holiday";
    public static String logoText = "Make My Trip";
    public static String logoImageSrc = "https://imgak.mmtcdn.com/pwa_v3/pwa_hotel_assets/header/mmtLogoWhite.png";
    public static String wb_flightsMenu = "//li[@data-cy='menu_Flights']/descendant::span[text()='Flights']";
    public static String wb_hotelsMenu = "//li[@data-cy='menu_Hotels']/descendant::span[text()='Hotels']";
    public static String wb_homeStays = "//li[@data-cy='menu_Homestays']/descendant::span[text()='Homestays & Villas']";
    public static String wb_holidayPackages = "//li[@data-cy='menu_Holidays']/descendant::span[text()='Holiday Packages']";
    public static String wb_trains = "//li[@data-cy='menu_Trains']/descendant::span[text()='Trains']";
    public static String wb_buses = "//li[@data-cy='menu_Buses']/descendant::span[text()='Buses']";
    public static String wb_cabs = "//li[@data-cy='menu_Cabs']/descendant::span[text()='Cabs']";
    public static  String wb_forexCard = "//li[@data-cy='menu_Forex']/descendant::span[text()='Forex Card & Currency']";
    public static String wb_travelInsurance = "//li[@data-cy='menu_TravelInsurance']/descendant::span[text()='Travel Insurance']";
    public static String wb_footerDesc1 = "//h2[text()='Why MakeMyTrip?']";
    public static String wb_footerDesc2 = "//h2[text()='Booking Flights with MakeMyTrip']";
    public static String wb_footerDesc3 = "//h2[text()='Domestic Flights with MakeMyTrip']";
    public static String wb_twitterIcon = "//span[@class='twiiterIcon landingSprite']";
    public static String wb_facebookIcon = "//span[@class='facebookIcon landingSprite']";

    public static void closePopupsOrAds() throws IOException {

        try{
            wait = new WebDriverWait(driver,10);
            WebElement ad_Frame = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_adFrame)));
            captureScreenshot();
            driver.switchTo().frame(ad_Frame);
            driver.findElement(By.xpath(wb_closeAdPopUp)).click();
            driver.switchTo().defaultContent();
            captureScreenshot();
        }catch (Exception e){
            System.out.println("Advertisement Popup not found");
        }
    }

    public static void validateHomePageUrlAndTitle(){

        //validate webpage url
        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, "https://www.makemytrip.com/");
        //validate title of webpage
        String currentPageTitle = driver.getTitle();
        verifyEquals(currentPageTitle,homePageTitle,"Page Title verification failed");
    }

    public static void validateOptionsOnTopOfHomePage(){

        //validate makemytrip logo
        String altText = driver.findElement(By.xpath(wb_mmtLogo)).getAttribute("alt");
        String imgSource = driver.findElement(By.xpath(wb_mmtLogo)).getAttribute("src");
        verifyEquals(altText, logoText,"Logo Verification failed");
        verifyEquals(imgSource, logoImageSrc,"Image verification failed");
    }
    public static void validateHomePageHeader() throws IOException {

        //validate options in Header
        Boolean bool = driver.findElement(By.xpath(wb_superOffers)).isDisplayed();
        verifyTrue(bool, "Super offers option is not displayed");
        bool = driver.findElement(By.xpath(wb_businessTravel)).isDisplayed();
        verifyTrue(bool, "Business Travel option is not displayed");
        bool = driver.findElement(By.xpath(wb_myTrips)).isDisplayed();
        verifyTrue(bool, "My Trips option is not displayed");
        bool = driver.findElement(By.xpath(wb_loginHeaderText)).isDisplayed();
        verifyTrue(bool, "Login or Create Account option is not displayed");
        bool = driver.findElement(By.xpath(wb_flightsMenu)).isDisplayed();
        verifyTrue(bool, "Flights menu is not displayed");
        bool = driver.findElement(By.xpath(wb_hotelsMenu)).isDisplayed();
        verifyTrue(bool, "Hotels menu is not displayed");
        bool = driver.findElement(By.xpath(wb_homeStays)).isDisplayed();
        verifyTrue(bool, "Homestays menu is not displayed");
        bool = driver.findElement(By.xpath(wb_holidayPackages)).isDisplayed();
        verifyTrue(bool, "Holiday Packages menu is not displayed");
        bool = driver.findElement(By.xpath(wb_trains)).isDisplayed();
        verifyTrue(bool, "Trains menu is not displayed");
        bool = driver.findElement(By.xpath(wb_buses)).isDisplayed();
        verifyTrue(bool, "Buses menu is not displayed");
        bool = driver.findElement(By.xpath(wb_cabs)).isDisplayed();
        verifyTrue(bool, "Cabs menu is not displayed");
        bool = driver.findElement(By.xpath(wb_forexCard)).isDisplayed();
        verifyTrue(bool, "Forex card menu is not displayed");
        bool = driver.findElement(By.xpath(wb_travelInsurance)).isDisplayed();
        verifyTrue(bool, "Travel Insurance menu is not displayed");
        captureScreenshot();
    }
    public static void validateHomePageFooter(){
        try {

            //Navigate to the bottom of the page
            js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0,document.body.scrollHeight)");
            captureScreenshot();
            //Validate Footer
            Boolean bool = driver.findElement(By.xpath(wb_footerDesc1)).isDisplayed();
            verifyTrue(bool,"Why make my Trip is not displayed");
            bool = driver.findElement(By.xpath(wb_footerDesc2)).isDisplayed();
            verifyTrue(bool,"Booking flights with MakeMyTrip is not displayed");
            bool = driver.findElement(By.xpath(wb_footerDesc3)).isDisplayed();
            verifyTrue(bool,"Domestic flights with MakeMyTrip is not displayed");

            bool = driver.findElement(By.xpath(wb_twitterIcon)).isDisplayed();
            verifyTrue(bool, "Twitter Icon is not displayed");
            bool = driver.findElement(By.xpath(wb_facebookIcon)).isDisplayed();
            verifyTrue(bool, "Facebook Icon is not displayed");

            js.executeScript("window.scrollTo(0,0)");
            captureScreenshot();
        }catch(Exception e){
            System.out.println("Element Not Found");
        }
    }
}
