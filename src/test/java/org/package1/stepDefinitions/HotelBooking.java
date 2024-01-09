package org.package1.stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.io.IOException;

import static org.pageClasses.HotelReviewOrCheckOutPage.*;
import static org.pageClasses.HotelListingsPage.bookHotel;
import static org.pageClasses.HotelSearchPage.inputHotelBookingDetails;
import static org.pageClasses.HotelSearchPage.navigateToHotelsPage;
import static org.pageClasses.ReadProperties.readHotelBookingDetails;

public class HotelBooking {
    @And("User is on the hotel booking page")
    public void userIsOnTheHotelBookingPage() throws IOException {
        navigateToHotelsPage();
    }

    @When("User fills the booking form with valid details as city {string},checkin date {string},checkout date {string}, no of rooms {string}, no of adults {string}")
    public void userFillsTheBookingFormWithValidDetailsAsCityCheckinDateCheckoutDateNoOfRoomsNoOfAdults(String city, String inDate, String outDate, String rooms, String adults) throws IOException {
        inputHotelBookingDetails(city, inDate, outDate, rooms, adults);
    }

    @Then("User should see checkout page with all booking details")
    public void userShouldSeeCheckoutPageWithAllBookingDetails() throws IOException {
        //Code written only till Review Page
        bookHotel();
        validateReviewPage();
    }


    @Then("User should see appropriate message")
    public void userShouldSeeAppropriateMessage() throws IOException {
        bookHotel();
    }
}
