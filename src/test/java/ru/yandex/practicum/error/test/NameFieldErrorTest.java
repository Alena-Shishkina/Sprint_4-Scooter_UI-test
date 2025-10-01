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

public class NameFieldErrorTest {

    private final String inValidName;
    private final String expectedErrorMessage;


    public NameFieldErrorTest(String inValidName, String expectedErrorMessage) {
        this.inValidName = inValidName;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getNameFieldErrorForWhomPage() {
        return new Object[][]{
                {" ", "Введите корректное имя"},
                {"Кирилл123", "Введите корректное имя"},
                {"abc", "Введите корректное имя"},
                {"Ы", "Введите корректное имя"},
                {"333", "Введите корректное имя"},
                {"#$%", "Введите корректное имя"},
                {"Вольфешлегельштайнхаузенбергердорф", "Введите корректное имя"},
                {"Анна, Мария", "Введите корректное имя"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testNameFieldError() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var nameFieldErrorTest = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        nameFieldErrorTest.checkFieldValidationName(inValidName, expectedErrorMessage);
    }
}
