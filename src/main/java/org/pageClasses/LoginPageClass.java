package org.pageClasses;

import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.IOException;

import static org.pageClasses.HomePageClass.wb_loginHeaderText;
import static org.pageClasses.ReadProperties.*;
import static org.pageClasses.SoftAssertion.verifyEquals;
import static org.pageClasses.SoftAssertion.verifyTrue;

public class LoginPageClass extends BaseClass{

    public static String wb_myBizAccount = "//li[text()='MyBiz Account']";
    public static String wb_workEmail = "//input[@name='username']";
    public static String wb_continueBtn = "//span[text()='CONTINUE']";
    public static String wb_accountDetailsHeader = "//p[text()='Your Account Details']";
    public static String wb_fullName = "//input[@name='name']";
    public static String wb_mobileNumber = "//input[@name='phoneNumber']";
    public static String wb_companyName = "//input[@name='orgName']";
    public static String wb_noOfEmpDropDown = "//div[text()='Select']";
    public static String wb_noOfEmp = "//li[text()='50-200']";
    public static String wb_provideIdLaterCheckBox = "//span[@class='checkBoxLabel']/preceding-sibling::span";
    public static String wb_continueWithoutId = "//span[text()='CONTINUE WITHOUT ID']";
    public static String wb_successIcon = "//span[contains(@class,'successAccountIcon')]";
    public static String wb_message = "//span[contains(@class,'successAccountIcon')]/following-sibling::div";
    public static String wb_modalclose = "//span[@class='mybizLoginClose']";
    public static String wb_emailError = "//p[@data-cy='otpError']";
    public static String expectedError = "Please enter valid Email address";
    public static String expectedAccountCreationMsg = "myBiz Account created\n" +
            "Please verify";

    public static void navigateToLoginPage(){
        driver.findElement(By.xpath(wb_loginHeaderText)).click();
    }

    public static void navigateToMyBiz() throws IOException {
        wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(wb_myBizAccount))).click();
        captureScreenshot();
    }
    public static void inputUserName(String user) throws IOException {
        readPropertiesFile();
        String userName = getUsername(user);
        WebElement workEmail = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_workEmail)));
        workEmail.sendKeys(new String(Base64.decodeBase64(userName)));
        driver.findElement(By.xpath(wb_continueBtn)).click();
        captureScreenshot();
        validateEmail();
    }

    public static void addPersonalDetailsAndContinue(String name, String mobileNum, String companyName) throws IOException {
        readLoginDetails(name,mobileNum,companyName);
        wait = new WebDriverWait(driver,10);
        WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_accountDetailsHeader)));
        verifyTrue(header.isDisplayed(),"Your Account Details page is not displayed");
        WebElement fullName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_fullName)));
        fullName.sendKeys(full_Name);
        driver.findElement(By.xpath(wb_mobileNumber)).sendKeys(mobileNo);
        driver.findElement(By.xpath(wb_companyName)).sendKeys(company_Name);
        driver.findElement(By.xpath(wb_noOfEmpDropDown)).click();
        driver.findElement(By.xpath(wb_noOfEmp)).click();
        driver.findElement(By.xpath(wb_provideIdLaterCheckBox)).click();
        captureScreenshot();
        driver.findElement(By.xpath(wb_continueWithoutId)).click();
    }

    public static void validateEmail() throws IOException {

        try{

            wait = new WebDriverWait(driver,10);
            WebElement emailError = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_emailError)));
            emailError.isDisplayed();
            String errorMsg = emailError.getText();
            verifyEquals(errorMsg,expectedError,"Email or Username verification failed");
            System.out.println(errorMsg);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_modalclose))).click();

        }catch (Exception e){
            System.out.println("Email address is valid");
            captureScreenshot();
        }
    }

    public static void validateLogin() throws IOException {
        try {

            driver.findElement(By.xpath(wb_successIcon)).isDisplayed();
            WebElement message = driver.findElement(By.xpath(wb_message));
            System.out.println(message.getText());
            Assert.assertEquals(message.getText(), expectedAccountCreationMsg);
            captureScreenshot();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(wb_modalclose))).click();

        }catch(Exception e){
            System.out.println("Login failed");
        }
    }

}
