package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.StatusPage;

public class ScooterTest {
    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testNonExistingOrderNotFound() {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        mainPage.openMainPage();
        mainPage.clickOnStatusButton();
        mainPage.enterOrderIn("123");
        StatusPage statusPage = mainPage.clickOnGoButton();
        statusPage.checkErrorImage();
    }
}
