package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class MainPage {

    //    Main page locators
    private final By cookieButton = By.cssSelector(".App_CookieButton__3cvqF");
    private final By orderHeaderButton = By.xpath(".//div[contains(@class,'Header')]/button[text() = 'Заказать']");
    private final By orderHomeButton = By.xpath(".//div[contains(@class,'Home')]/button[text() = 'Заказать']");
    private final By goButton = By.cssSelector(".Button_Button__ra12g.Header_Button__28dPO");
    private final By orderIn = By.cssSelector(".Input_Input__1iN_Z.Header_Input__xIoUq");
    private final By statusButton = By.cssSelector(".Header_Link__1TAG7");

    private final WebDriver driver;
    private WebDriverWait wait;


    public MainPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }


    //    Open main page
    public void openMainPage() {

        driver.get(EnvConfig.BASE_URL);
    }


    //    Method main page
    public StatusPage clickOnGoButton() {

        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(goButton));
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }


    public void enterOrderIn(String orderNumber) {

        driver.findElement(orderIn).sendKeys(orderNumber);
    }


    public void clickOnStatusButton() {

        driver.findElement(statusButton).click();
    }


    public void choiceOrderButton(boolean choiceOrderButton) {

        By orderButton = (choiceOrderButton) ? orderHeaderButton : orderHomeButton;
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(orderButton));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);
        button.click();
    }


    public void clickCookie() {

        driver.findElement(cookieButton).click();
    }
}
