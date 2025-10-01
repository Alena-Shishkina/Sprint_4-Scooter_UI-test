package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;
import java.util.Map;

public class ForWhomPage {

    //    Locators for the form for whom the scooter is
    private final By inputName = By.cssSelector("input[placeholder = '* Имя']");
    private final By inputSurname = By.cssSelector("input[placeholder = '* Фамилия']");
    private final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    private final By inputMetro = By.cssSelector(".select-search__input");
    private final By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    private final By nextButton = By.xpath("//button[text()='Далее']");

    private final By errorNane = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректное имя']");
    private final By errorSurname = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректную фамилию']");
    private final By errorAddress = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6'and text()='Введите корректный адрес']");
    private final By errorMetro = By.xpath("//div[@class='Order_MetroError__1BtZb' and text()='Выберите станцию']");
    private final By errorPhoneNumber = By.xpath("//div[@class='Input_ErrorMessage__3HvIb Input_Visible___syz6' and text()='Введите корректный номер']");

    private final Map<By, By> errorLocator = Map.of(
            inputName, errorNane,
            inputSurname, errorSurname,
            inputAddress, errorAddress,
            inputMetro, errorMetro,
            inputPhoneNumber, errorPhoneNumber
    );

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


    //    Check error message name
    public void checkFieldValidationName(String inValid, String expectedError) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputName));
        input.sendKeys(inValid);
        input.sendKeys(Keys.TAB);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorNane));
        Assert.assertEquals(expectedError, errorMessage.getText());
    }

    //    Check error message surname
    public void checkFieldValidationSurname(String inValid, String expectedError) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputSurname));
        input.sendKeys(inValid);
        input.sendKeys(Keys.TAB);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorSurname));
        Assert.assertEquals(expectedError, errorMessage.getText());
    }

    //    Check error message address
    public void checkFieldValidationAddress(String inValid, String expectedError) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputAddress));
        input.sendKeys(inValid);
        input.sendKeys(Keys.TAB);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorAddress));
        Assert.assertEquals(expectedError, errorMessage.getText());
    }

    //    Check error message metro
    public void checkFieldValidationMetro(String inValid, String expectedError) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputMetro));
        input.sendKeys(inValid);
        input.sendKeys(Keys.TAB);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMetro));
        Assert.assertEquals(expectedError, errorMessage.getText());
    }

    //    Check error message phone number
    public void checkFieldValidationPhoneNumber(String inValid, String expectedError) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputPhoneNumber));
        input.sendKeys(inValid);
        input.sendKeys(Keys.TAB);

        WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorPhoneNumber));
        Assert.assertEquals(expectedError, errorMessage.getText());
    }


    public void endToEndCheckFieldValidation() {

        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(nextButton));
        input.click();
        for (Map.Entry<By, By> entry : errorLocator.entrySet()) {
            try {
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(entry.getValue()));
                Assert.assertTrue("Ошибка поля " + entry.getKey() + ", не отображается ", errorMessage.isDisplayed());
            } catch (TimeoutException e) {
                throw new AssertionError("Не дождались появления ошибки у поля: " + entry.getKey(), e);
            }
        }
    }
}