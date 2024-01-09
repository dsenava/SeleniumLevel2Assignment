package org.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

import static org.pageClasses.ReadProperties.*;

public class HotelSearchPage extends BaseClass{
    public static String wb_hotelsMenu = "//li[@data-cy='menu_Hotels']";
    public static String wb_city = "//input[@id='city']";
    public static String wb_searchCity = "//input[@title='Where do you want to stay?']";
    public static String wb_cityListBox = "//ul[@role='listbox']/li[1]";
    public static String wb_checkIn = "//input[@id='checkin']";
    public static String wb_selectCheckInDate = "//div[@aria-label='%s']";
    public static String wb_checkOut = "//input[@id='checkout']";
    public static String wb_selectCheckOutDate = "//div[@aria-label='%s']";
    public static String wb_roomsGuests = "//input[@id='guest']";
    public static String wb_rooms = "//span[@data-testid='room_count']";
    public static String wb_roomCount = "//span[@data-testid='room_count']/following::ul/li[%s]";
    public static String wb_adults = "//span[@data-testid='adult_count']";
    public static String wb_adultCount = "//span[@data-testid='adult_count']/..//following-sibling::ul/li[%s]";
    public static String wb_applyBtn = "//button[text()='Apply']";
    public static String wb_searchHotelBtn = "//button[@id='hsw_search_button']";
    public static int count=0;

    public static void navigateToHotelsPage() throws IOException {
        driver.findElement(By.xpath(wb_hotelsMenu)).click();
        captureScreenshot();
    }

    public static void inputHotelBookingDetails(String city, String inDate, String outDate, String rooms, String adults) throws IOException {
        readHotelBookingDetails(city, inDate, outDate, rooms, adults);
        act = new Actions(driver);
        wait = new WebDriverWait(driver,10);
        WebElement city_Name = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_city)));
        city_Name.click();
        WebElement searchcity = driver.findElement(By.xpath(wb_searchCity));
        searchcity.sendKeys(cityName);
        Boolean bool = wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(wb_cityListBox),cityName));
        act.moveToElement(searchcity).sendKeys(Keys.ARROW_DOWN, Keys.ENTER).build().perform();

        driver.findElement(By.xpath(wb_checkIn)).click();
        WebElement check_In_Date = dynamicallyDesignedXpath(wb_selectCheckInDate,checkInDate);
        check_In_Date.click();
        driver.findElement(By.xpath(wb_checkOut)).click();
        WebElement check_Out_Date = dynamicallyDesignedXpath(wb_selectCheckOutDate,checkOutDate);
        check_Out_Date.click();

        inputRoomAndAdultDetails();
        driver.findElement(By.xpath(wb_searchHotelBtn)).click();
        captureScreenshot();
    }

    public static void inputRoomAndAdultDetails() throws IOException {

        driver.findElement(By.xpath(wb_roomsGuests)).click();
        driver.findElement(By.xpath(wb_rooms)).click();
        WebElement no_Of_Rooms = dynamicallyDesignedXpath(wb_roomCount,roomCount);
        no_Of_Rooms.click();
        driver.findElement(By.xpath(wb_adults)).click();
        if(count==1) {
            WebElement no_Of_Adults = dynamicallyDesignedXpath(wb_adultCount, updatedNoOfAdults);
            no_Of_Adults.click();
            count++;
        }else{
            WebElement no_Of_Adults = dynamicallyDesignedXpath(wb_adultCount, noOfAdults);
            no_Of_Adults.click();
            count++;
        }
        act.moveToElement(driver.findElement(By.xpath(wb_applyBtn))).click().build().perform();
        captureScreenshot();
    }
}
