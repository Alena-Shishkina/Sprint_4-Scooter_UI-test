package ru.yandex.practicum.error.test;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.ForWhomPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.test.DriverFactory;

public class EndToEndFormTest {

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testEndToEndForm() {
        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var forWhomPage = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.clickOrderButton();
        forWhomPage.endToEndCheckFieldValidation();
    }
}
