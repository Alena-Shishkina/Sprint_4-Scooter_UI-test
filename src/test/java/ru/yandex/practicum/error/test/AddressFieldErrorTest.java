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

public class AddressFieldErrorTest {

    private final String inValidAddress;
    private final String expectedErrorMessage;


    public AddressFieldErrorTest(String inValidAddress, String expectedErrorMessage) {
        this.inValidAddress = inValidAddress;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getAddressFieldErrorForWhomPage() {
        return new Object[][]{
                {" ", "Введите корректный адрес"},
                {"Сокол", "Введите корректный адрес"},
                {"abc", "Введите корректный адрес"},
                {"Дом", "Введите корректный адрес"},
                {"333", "Введите корректный адрес"},
                {"#$%", "Введите корректный адрес"},
                {"Со45", "Введите корректный адрес"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testAddressFieldError() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var nameFieldErrorTest = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        nameFieldErrorTest.checkFieldValidationAddress(inValidAddress, expectedErrorMessage);
    }
}
