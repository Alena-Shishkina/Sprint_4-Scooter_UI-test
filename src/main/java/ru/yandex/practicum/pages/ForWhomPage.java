package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class ForWhomPage {

    //    Locators for the form for whom the scooter is
    private final By inputName = By.cssSelector("input[placeholder = '* Имя']");
    private final By inputSurname = By.cssSelector("input[placeholder = '* Фамилия']");
    private final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private final By inputMetro = By.cssSelector(".select-search__input");
    private final By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final WebDriver driver;
    private WebDriverWait wait;

    public ForWhomPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }


    //    Methods for filling out the form
    public void inputName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }


    public void inputSurname(String surname) {
        driver.findElement(inputSurname).sendKeys(surname);
    }


    public void inputAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }


    public void inputMetro(String metro) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputMetro));
        input.click();
        input.sendKeys(metro);

        By option = By.xpath("//div[contains(@class,'Order_Text') and text()='" + metro + "']");
        WebElement dropdownItem = wait.until(ExpectedConditions.visibilityOfElementLocated(option));
        dropdownItem.click();
    }


    public void inputPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }


    public AboutRentPage clickNextButton() {
        driver.findElement(nextButton).click();
        return new AboutRentPage(driver);
    }


    public void fillForm(String name, String surname, String address, String metro, String phoneNumber) {
        inputName(name);
        inputSurname(surname);
        inputAddress(address);
        inputMetro(metro);
        inputPhoneNumber(phoneNumber);
    }
}
