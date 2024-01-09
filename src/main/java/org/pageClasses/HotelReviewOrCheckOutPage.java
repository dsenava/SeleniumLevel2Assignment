package org.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.pageClasses.HotelDetailsPage.flag;
import static org.pageClasses.ReadProperties.*;
import static org.pageClasses.SoftAssertion.verifyEquals;
import static org.pageClasses.SoftAssertion.verifyTrue;

public class HotelReviewOrCheckOutPage extends BaseClass {

    public static String wb_reviewPageHeader = "//div[@class='rvHdrContent']/h2";
    public static String reviewPageHeaderTxt = "Review your Booking";
    public static String wb_verifyCheckIn = "(//p[@class='prptChk__date'])[1]";
    public static String wb_verifyCheckOut = "(//p[@class='prptChk__date'])[2]";
    public static String wb_verifycountOfDays = "//div[contains(@class,'prptChkCont__col')]/p/span[1]";
    public static String wb_verifyAdultCount = "//div[contains(@class,'prptChkCont__col')]/p/span[3]";
    public static String wb_verifyRoomCount = "//div[contains(@class,'prptChkCont__col')]/p/span[4]";
    public static String wb_payNow = "//a[text()='Pay Now']";

    public static void validateReviewPage() throws IOException {

        wait = new WebDriverWait(driver,15);
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath(wb_reviewPageHeader),reviewPageHeaderTxt));

        WebElement verifyCheckIn = driver.findElement(By.xpath(wb_verifyCheckIn));
        WebElement verifyCheckOut = driver.findElement(By.xpath(wb_verifyCheckOut));
        captureScreenshot();

        verifyEquals(verifyCheckIn.getText(),expectedCheckInDate,"CheckIn Date does not match");
        verifyEquals(verifyCheckOut.getText(),expectedCheckOutDate,"CheckOut Date does not match");
        WebElement daysCount = driver.findElement(By.xpath(wb_verifycountOfDays));
        verifyEquals(daysCount.getText(),String.valueOf(noOfDays),"No. of days does not match");

        WebElement countOfAdults = driver.findElement(By.xpath(wb_verifyAdultCount));
        verifyEquals(countOfAdults.getText(),String.valueOf(Integer.parseInt(updatedNoOfAdults)+1),"No. of Adults does not match");
        WebElement countOfRooms = driver.findElement(By.xpath(wb_verifyRoomCount));
        if(flag) {
            verifyEquals(countOfRooms.getText(), roomCount + " Rooms","No. of rooms does not match");
        }else{
            //As a combo is selected- Only 1 room gets booked
            verifyEquals(countOfRooms.getText(),"1 Room","No. of rooms does not match");
        }
        captureScreenshot();
        WebElement pay_Now = driver.findElement(By.xpath(wb_payNow));
        act.moveToElement(pay_Now).build().perform();
        verifyTrue(pay_Now.isDisplayed(),"pay now is not displayed");
        captureScreenshot();

    }

}

