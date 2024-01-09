package org.pageClasses;

import org.testng.asserts.SoftAssert;

public class SoftAssertion {
    private static SoftAssert softAssert = new SoftAssert();

    public static void verifyTrue(boolean condition, String message) {
        softAssert.assertTrue(condition, message);
    }

    public static void verifyEquals(String actual, String expected, String message) {
        softAssert.assertEquals(actual, expected, message);
    }

    public static void assertAll() {
        softAssert.assertAll();
    }
}
