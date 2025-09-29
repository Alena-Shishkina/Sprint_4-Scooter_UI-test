package ru.yandex.practicum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;

public class AboutRentPage {

    //    Rent form locators
    private final By inputData = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    private final By inputRentalPeriod = By.cssSelector(".Dropdown-root");
    private final By dropdownMenu = By.cssSelector(".Dropdown-menu");
    private final By oneDay = By.cssSelector(".Dropdown-option:nth-of-type(1)");
    private final By fourDays = By.cssSelector(".Dropdown-option:nth-of-type(4)");
    private final By blackScooter = By.cssSelector(".Checkbox_Input__14A2w[id='black']");
    private final By greyScooter = By.cssSelector(".Checkbox_Input__14A2w[id='grey']");
    private final By inputComments = By.cssSelector("input[placeholder='Комментарий для курьера']");
    private final By orderButton = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Заказать']");

    private final WebDriver driver;
    private WebDriverWait wait;

    public AboutRentPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }

    //    Methods for filling out the form
    public void inputData(String day) {
        WebElement input = wait.until(ExpectedConditions.elementToBeClickable(inputData));
        input.click();

        By dayLocator = By.xpath("//div[contains(@class,'react-datepicker__day') and text()='" + day + "']");
        WebElement dateToSelect = wait.until(ExpectedConditions.elementToBeClickable(dayLocator));
        dateToSelect.click();
    }

    public void selectRentalPeriod(boolean daySelection) {
        driver.findElement(inputRentalPeriod).click();
        wait.until(ExpectedConditions.elementToBeClickable(dropdownMenu));

        By clickRentalPeriod = (daySelection) ? oneDay : fourDays;
        driver.findElement(clickRentalPeriod).click();

    }


    public void clickBlackScooter(boolean colorSelection) {
        By clickScooterColor = (colorSelection) ? blackScooter : greyScooter;
        driver.findElement(clickScooterColor).click();

    }


    public void inputComments(String comments) {
        driver.findElement(inputComments).sendKeys(comments);

    }


    public PopapPage clickOrderButton() {
        driver.findElement(orderButton).click();
        return new PopapPage(driver);

    }


    public void rentForm(String data, boolean daySelection, boolean colorSelection, String comments) {
        inputData(data);
        selectRentalPeriod(daySelection);
        clickBlackScooter(colorSelection);
        inputComments(comments);

    }
}
