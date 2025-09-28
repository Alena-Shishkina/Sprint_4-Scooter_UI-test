package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class StatusPage {

    //    Locator status page
    private final By orderFailed = By.cssSelector("img[alt='Not found']");

    private final WebDriver driver;
    private WebDriverWait wait;

    public StatusPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));

    }


    public void checkErrorImage() {
        WebElement error = wait.until(ExpectedConditions
                .visibilityOfElementLocated(orderFailed));
        Assert.assertTrue("Картинка ошибки не отображается", error.isDisplayed());

    }
}
