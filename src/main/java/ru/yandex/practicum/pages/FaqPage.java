package ru.yandex.practicum.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.practicum.pages.util.EnvConfig;

import java.time.Duration;
import java.util.List;

public class FaqPage {

    private final By questionLocator = By.cssSelector("div[id^='accordion__heading-']");
    private final By answerLocator = By.cssSelector("div[id^='accordion__panel-']");

    private final WebDriver driver;
    private final WebDriverWait wait;

    public FaqPage(WebDriver driver) {

        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.EXPLICITY_TIMEOUT));
    }


    public void locatorFaq(int index, String expectedQuestionText, String expectedAnswersText) {

        List<WebElement> questions = driver.findElements(questionLocator);
        WebElement question = wait.until(ExpectedConditions.elementToBeClickable(questions.get(index)));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", question);

        String actualQuestionText = question.getText();
        Assert.assertEquals(expectedQuestionText, actualQuestionText);

        question.click();

        List<WebElement> answers = driver.findElements(answerLocator);
        WebElement answer = wait.until(ExpectedConditions.visibilityOf(answers.get(index)));

        String actualAnswersText = answer.getText();
        Assert.assertEquals(expectedAnswersText, actualAnswersText);
    }

}
