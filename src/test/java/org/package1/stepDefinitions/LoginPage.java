package org.package1.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import static org.pageClasses.LoginPageClass.*;
import static org.pageClasses.ReadProperties.getUsername;
import static org.pageClasses.ReadProperties.readPropertiesFile;

public class LoginPage {

    @And("User is on the Login or signup page")
    public void userIsOnTheLoginOrSignupPage() {
        navigateToLoginPage();
    }

    @When("User inputs valid username {string} and signup details FullName {string}, mobile number{string} and company Name {string}")
    public void userInputsValidUsernameAndSignupDetailsFullNameMobileNumberAndCompanyName(String username, String fullName, String mobileNum, String companyName) throws IOException {
        navigateToMyBiz();
        inputUserName(username);
        addPersonalDetailsAndContinue(fullName,mobileNum,companyName);
    }

    @Then("User Account gets created successfully")
    public void userAccountGetsCreatedSuccessfully() throws IOException {
        //code written only till Account creation as there is OTP validation after that.
        //Also, cannot login with already registered user due to OTP validation
        validateLogin();
    }

    @When("User inputs invalid username {string}")
    public void userInputsInvalidUsername(String username) throws IOException {
        navigateToMyBiz();
        inputUserName(username);
    }

    @Then("User should see an error message indicating failed login")
    public void userShouldSeeAnErrorMessageIndicatingFailedLogin() throws IOException {
        validateLogin();
    }

}
