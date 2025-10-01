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

public class SurnameFieldErrorTest {

    private final String inValidSurname;
    private final String expectedErrorMessage;


    public SurnameFieldErrorTest(String inValidSurname, String expectedErrorMessage) {
        this.inValidSurname = inValidSurname;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getSurnameFieldErrorForWhomPage() {
        return new Object[][]{
                {" ", "Введите корректную фамилию"},
                {"abc", "Введите корректную фамилию"},
                {"Ы", "Введите корректную фамилию"},
                {"333", "Введите корректную фамилию"},
                {"#$%", "Введите корректную фамилию"},
                {"Петрович79", "Введите корректную фамилию"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testSurnameFieldError() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var nameFieldErrorTest = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        nameFieldErrorTest.checkFieldValidationSurname(inValidSurname, expectedErrorMessage);
    }
}
