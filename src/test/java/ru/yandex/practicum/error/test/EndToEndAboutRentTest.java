package ru.yandex.practicum.error.test;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.AboutRentPage;
import ru.yandex.practicum.pages.ForWhomPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.test.DriverFactory;

public class EndToEndAboutRentTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testEndToEndAboutRent() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var aboutRentPage = new AboutRentPage(driver);
        var forWhomPage = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        forWhomPage.fillForm("Альбус", "Дамблдор", "Москва, Проспект Мира 38", "Войковская", "79161112233");
        forWhomPage.clickNextButton();
        aboutRentPage.endToEndCheckFieldValidationAboutRentPage();
    }
}
