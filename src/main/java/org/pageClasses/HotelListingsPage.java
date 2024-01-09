package org.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import static org.pageClasses.HotelDetailsPage.bookRoom;
import static org.pageClasses.HotelDetailsPage.bookRoomCombo;
import static org.pageClasses.HotelSearchPage.inputRoomAndAdultDetails;
import static org.pageClasses.SoftAssertion.verifyEquals;
import static org.pageClasses.SoftAssertion.verifyTrue;

public class HotelListingsPage extends BaseClass {
    public static String wb_firstSearchHotel = "(//img[@alt='hotelImg'])[1]";
    public static String wb_noResultsFound = "//div[contains(@class,'errorWrap')]/p[2]";
    public static String searchResultMsg1 = "Sold Out on\n" +
            "MakeMyTrip.";
    public static String wb_noResultsFoundMsg = "//div[contains(@class,'errorWrap')]/p[3]";
    public static String searchResultMsg2 = "Sorry, all hotels in Lakshadweep are sold out on MakeMyTrip. Please change your dates/ search criteria.";
    public static String wb_pickAnotherDate = "//a[text()='PICK ANOTHER DATE']";

    public static void bookHotel() throws IOException {
        validateSearchResults();
        selectFirstHotel();
    }

    public static void validateSearchResults() {
        try {
            wait = new WebDriverWait(driver, 10);
            WebElement msg1 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_noResultsFound)));
            verifyEquals(msg1.getText(), searchResultMsg1, "Search Result Verification Failed");

            WebElement msg2 = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_noResultsFoundMsg)));
            verifyEquals(msg2.getText(), searchResultMsg2, "Search Result Verification Failed");

            WebElement pickDate = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_pickAnotherDate)));
            verifyTrue(pickDate.isDisplayed(), "Pick another date is not displayed");
            captureScreenshot();

        } catch (Exception e) {
            System.out.println("Hotels List is displayed");
        }
    }

    public static void selectFirstHotel() throws IOException {
        try {
            wait = new WebDriverWait(driver, 15);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_firstSearchHotel))).click();
            captureScreenshot();
            String parentWindow = driver.getWindowHandle();
            System.out.println("Parent Window ID : "+parentWindow);
            Set<String> windowIds = driver.getWindowHandles();
            Iterator<String> it = windowIds.iterator();
            while (it.hasNext()) {
                String childWindow = it.next();
                if (!parentWindow.equalsIgnoreCase(childWindow)) {
                    System.out.println("Child Window ID : "+childWindow);
                    driver.switchTo().window(childWindow);
                    captureScreenshot();
                    break;
                }
            }
            inputRoomAndAdultDetails();
            bookRoom();
            bookRoomCombo();
        }catch (Exception e){
            System.out.println("Hotels not Found for the given dates");
        }
    }
}
