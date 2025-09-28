package ru.yandex.practicum.test;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import ru.yandex.practicum.pages.AboutRentPage;
import ru.yandex.practicum.pages.ForWhomPage;
import ru.yandex.practicum.pages.MainPage;
import ru.yandex.practicum.pages.PopapPage;

@RunWith(Parameterized.class)

public class OrderTest {

    private final boolean choiceOrderButton;
    private final String name;
    private final String surname;
    private final String address;
    private final String metro;
    private final String phoneNumber;
    private final String data;
    private final boolean daySelection;
    private final boolean colorSelection;
    private final String comments;


    public OrderTest(boolean choiceOrderButton, String name, String surname, String address, String metro, String phoneNumber, String data, boolean daySelection, boolean colorSelection, String comments) {
        this.choiceOrderButton = choiceOrderButton;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.metro = metro;
        this.phoneNumber = phoneNumber;
        this.data = data;
        this.daySelection = daySelection;
        this.colorSelection = colorSelection;
        this.comments = comments;
    }

    @Parameterized.Parameters
    public static Object[][] getOrderForWhomPage() {
        return new Object[][]
                {{
                        true,
                        "Альбус",
                        "Дамблдор",
                        "Москва, Проспект Мира 38",
                        "Войковская",
                        "79161112233",
                        "30",
                        true,
                        true,
                        "Счастье можно найти даже в тёмные времена, если не забывать обращаться к свету."
                }, {
                        false,
                        "Сириус",
                        "Блек",
                        "Москва, Стремянный переулок, 9",
                        "Павелецкая",
                        "79166664455",
                        "15",
                        false,
                        false,
                        "Мир не разделён на хороших и плохих, в каждом есть и тёмная, и светлая сторона. Главное в том, какую ты выбрал — это определяет всё."
                }};
    }

    @Rule
    public DriverFactory factory = new DriverFactory();

    @Test
    public void testOrder() {

        WebDriver driver = factory.getDriver();
        var mainPage = new MainPage(driver);
        var forWhomPage = new ForWhomPage(driver);
        mainPage.openMainPage();
        mainPage.clickCookie();
        mainPage.choiceOrderButton(choiceOrderButton);
        forWhomPage.fillForm(name, surname, address, metro, phoneNumber);
        AboutRentPage rentPage = forWhomPage.clickNextButton();
        rentPage.rentForm(data, daySelection, colorSelection, comments);
        PopapPage popPapPage = rentPage.clickOrderButton();
        popPapPage.clickButtonYes();
        popPapPage.checkConfirmation();
    }
}
