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

public class MetroFieldErrorTest {

    private final String inValidMetro;
    private final String expectedErrorMessage;


    public MetroFieldErrorTest(String inValidMetro, String expectedErrorMessage) {
        this.inValidMetro = inValidMetro;
        this.expectedErrorMessage = expectedErrorMessage;
    }

    @Parameterized.Parameters
    public static Object[][] getMetroFieldErrorForWhomPage() {
        return new Object[][]{
                {" ", "Выберите станцию"},
                {"abc", "Выберите станцию"},
                {"Дом", "Выберите станцию"},
                {"333", "Выберите станцию"},
                {"#$%", "Выберите станцию"},
        };
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testMetroFieldError() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var nameFieldErrorTest = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        nameFieldErrorTest.checkFieldValidationMetro(inValidMetro, expectedErrorMessage);
    }
}
