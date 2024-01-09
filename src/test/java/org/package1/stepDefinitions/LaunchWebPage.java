package org.package1.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;

import java.io.IOException;

import static org.pageClasses.BaseClass.*;
import static org.pageClasses.HomePageClass.closePopupsOrAds;

public class LaunchWebPage {

    @Before
    public void browserSetUp() throws IOException {
        setUp();
    }

    @After
    public void browserTearDown(){
        tearDown();
    }

    @Given("User launches makemytrip application")
    public void user_launches_makemytrip_application() throws IOException {
        launchURL();
        closePopupsOrAds();
    }
}
