Feature:Launch makemytrip application,validate homepage, validate login using valid and invalid credentials and book a hotel
  Background:
    Given User launches makemytrip application

  Scenario: Homepage Validation - Page Title and URL
    Then User should see the homepage with the appropriate title and url

  Scenario: Homepage Validation - All required options
    Then User should see the homepage with all required options on top of page

  Scenario: Homepage Validation - Header
    Then User should see the homepage with all services provided in the Header

  Scenario: Homepage Validation - Footer
    Then User should see the homepage with all icons and links provided in the Footer

  Scenario Outline: Validate User is able to login into makemytrip application with valid credentials
    And User is on the Login or signup page
    When User inputs valid username "<username>" and signup details FullName "<FullName>", mobile number"<MobileNumber>" and company Name "<CompanyName>"
    Then User Account gets created successfully
    Examples:
      |username  |FullName|MobileNumber|CompanyName|
      |$username |$FullName|$MobileNumber|$CompanyName|

  Scenario Outline: Validate User is not able to login into makemytrip application with invalid credentials
    And User is on the Login or signup page
    When User inputs invalid username "<username>"
    Then User should see an error message indicating failed login
    Examples:
      |username  |
      |$invalidUserName|

  Scenario Outline: Booking a hotel
    And User is on the hotel booking page
    When User fills the booking form with valid details as city "<city>",checkin date "<checkInDate>",checkout date "<checkOutDate>", no of rooms "<noOfRooms>", no of adults "<noOfAdults>"
    Then User should see checkout page with all booking details
    Examples:
      | city |checkInDate|checkOutDate|noOfRooms|noOfAdults|
      |$city|$checkInDate|$checkOutDate|$noOfRooms|$noOfAdults|

  Scenario Outline: Booking a hotel - No Hotels found (Negative scenario)
    And User is on the hotel booking page
    When User fills the booking form with valid details as city "<nameOfCity>",checkin date "<checkInDateForSearch>",checkout date "<checkOutDateForSearch>", no of rooms "<Rooms>", no of adults "<Adults>"
    Then User should see appropriate message
    Examples:
      | nameOfCity |checkInDateForSearch|checkOutDateForSearch|Rooms|Adults|
      |$nameOfCity|$checkInDateForSearch|$checkOutDateForSearch|$Rooms|$Adults|


