package org.package1.stepDefinitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.pageClasses.HomePageClass.*;

public class HomePage {

    @Then("User should see the homepage with the appropriate title and url")
    public void userShouldSeeTheHomepageWithTheAppropriateTitleAndUrl() {
        validateHomePageUrlAndTitle();
    }

    @Then("User should see the homepage with all required options on top of page")
    public void userShouldSeeTheHomepageWithAllRequiredOptionsOnTopOfPage() {
        validateOptionsOnTopOfHomePage();
    }

    @Then("User should see the homepage with all services provided in the Header")
    public void userShouldSeeTheHomepageWithAllServicesProvidedInTheHeader() throws IOException {
        validateHomePageHeader();
    }

    @Then("User should see the homepage with all icons and links provided in the Footer")
    public void userShouldSeeTheHomepageWithAllIconsAndLinksProvidedInTheFooter() {
        validateHomePageFooter();
    }
}
