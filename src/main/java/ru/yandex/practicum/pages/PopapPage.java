package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class PopapPage {

    //    Locator popup page
    private final By buttonYes = By.xpath("//button[@class = 'Button_Button__ra12g Button_Middle__1CSJM' and text() = 'Да']");
    private final By confirmation = By.xpath("//div[@class = 'Order_Text__2broi' and text() = 'Номер заказа: ']");

    private final WebDriver driver;

    public PopapPage(WebDriver driver) {

        this.driver = driver;
    }


    public void clickButtonYes() {

        driver.findElement(buttonYes).click();
    }


    public void checkConfirmation() {

        Assert.assertTrue(driver.findElement(confirmation).isDisplayed());
    }

}
