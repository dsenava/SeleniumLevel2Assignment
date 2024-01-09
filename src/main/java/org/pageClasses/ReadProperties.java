package org.pageClasses;

import org.apache.commons.codec.binary.Base64;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.util.Properties;

public class ReadProperties {

    static Properties prop;
    static FileInputStream fis;
    public static String work_Email;
    protected static String full_Name;
    protected static String mobileNo;
    protected static String company_Name;
    protected static String invalidUserName;
    public static String cityName;
    public static String checkInDate;
    public static String checkOutDate;
    public static String roomCount;
    public static String noOfAdults;
    public static int noOfDays;
    public static String expectedCheckInDate;
    public static String expectedCheckOutDate;
    public static String updatedNoOfAdults;

    public static void readPropertiesFile() throws IOException {
        prop = new Properties();
        File file = new File("C:\\Users\\VA338RM\\GitImports\\SeleniumLevel2Assignment\\src\\test\\resources\\config.properties");
        fis = new FileInputStream(file);
        prop.load(fis);
    }

    public static String getBrowserName(){
        return prop.getProperty("browser");
    }

    public static String getUrl(){
        return prop.getProperty("url");
    }

    public static String getUsername(String user){
        return prop.getProperty(user);
    }

    public static void readLoginDetails(String fullName, String mobileNum, String companyName) throws IOException {
        readPropertiesFile();
        full_Name = prop.getProperty(fullName);
        mobileNo = prop.getProperty(mobileNum);
        company_Name = prop.getProperty(companyName);
        //System.out.println(work_Email+" "+full_Name+" "+mobileNo+" "+company_Name);
    }

    public static void readHotelBookingDetails(String city, String inDate, String outDate, String rooms, String adults) throws IOException {
        readPropertiesFile();
        cityName = prop.getProperty(city);
        checkInDate = prop.getProperty(inDate);
        checkOutDate = prop.getProperty(outDate);
        roomCount = prop.getProperty(rooms);
        expectedCheckInDate = prop.getProperty("expectedCheckInDate");
        expectedCheckOutDate = prop.getProperty("expectedCheckOutDate");
        noOfAdults = String.valueOf(Integer.parseInt(prop.getProperty(adults))-1);
        //System.out.println(cityName+" "+noOfAdults+" "+checkInDate+" "+checkOutDate+" "+roomCount);
        updatedNoOfAdults = String.valueOf(Integer.parseInt(prop.getProperty("updatedNoOfAdults"))-1);
        noOfDays = (int) ChronoUnit.DAYS.between(convertStringToDate(checkInDate),convertStringToDate(checkOutDate));

        System.out.println("Number of days of stay: "+noOfDays);
    }

    public static LocalDate convertStringToDate(String date){

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd yyyy", Locale.ENGLISH);
        LocalDate dates = LocalDate.parse(date, formatter);
        //System.out.println(dates);
        return dates;
    }
}
