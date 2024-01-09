package org.pageClasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HotelDetailsPage extends BaseClass{

    public static boolean flag;
    public static String wb_viewThisCombo = "//button[text()='VIEW THIS COMBO']";
    public static String wb_selectCombo = "//span[text()='Select Combo']";
    public static String wb_bookNow = "//a[text()='BOOK THIS NOW']";

    public static void bookRoom(){
        try {
            wait = new WebDriverWait(driver, 12);
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_bookNow))).click();
            captureScreenshot();
            flag=true;
        }catch(Exception e){
            System.out.println("Book Now option not found");
        }
    }
    public static void bookRoomCombo(){
        try {
            wait = new WebDriverWait(driver, 12);
//            WebElement viewCombo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_viewThisCombo)));
//            act.moveToElement(viewCombo).click().build().perform();
            WebElement selectCombo = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_selectCombo)));
            act.moveToElement(selectCombo).build().perform();
            wait.until(ExpectedConditions.visibilityOf(selectCombo)).click();
            captureScreenshot();
            flag = false;
        }catch(Exception e){
            System.out.println("Select This Combo option not found");
        }
    }

}
