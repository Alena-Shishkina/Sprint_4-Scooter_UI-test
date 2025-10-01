package ru.yandex.practicum.error.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.ForWhomPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.test.DriverFactory;

@RunWith(Parameterized.class)

public class PhoneNumberFieldErrorTest {

    private final String inValidPhoneNumber;
    private final String expectedErrorMessage;


    public PhoneNumberFieldErrorTest(String inValidPhoneNumber, String expectedErrorMessage) {
        this.inValidPhoneNumber = inValidPhoneNumber;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getMetroFieldErrorForWhomPage() {
        return new Object[][]{
                {" ", "Введите корректный номер"},
                {"abc", "Введите корректный номер"},
                {"Дом", "Введите корректный номер"},
                {"333", "Введите корректный номер"},
                {"#$%", "Введите корректный номер"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testPhoneNumberFieldError() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var nameFieldErrorTest = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        nameFieldErrorTest.checkFieldValidationPhoneNumber(inValidPhoneNumber, expectedErrorMessage);
    }
}
