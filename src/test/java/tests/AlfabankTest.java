package tests;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class AlfabankTest {
    final String URL = "https://alfabank.ru/";

    @BeforeEach
    void siteOpen() {
        Selenide.open(URL);
    }
    
    @Test
    void archiveDeposit() {
        // Перейти на страницу Вклады
        $(byXpath(".//a[contains(text(),'Вклады')]")).click();
        // Перейти на страницу Депозиты
        $$(byXpath(".//a[contains(text(),'Депозиты')]")).find(visible).click();
        // Перейти на страницу Архивные депозиты
        $(byXpath(".//a[contains(text(),'Архивные депозиты')]")).click();
        // Проверить отображение 3х депозитов
        $$(byXpath("//*[@class='product-cell__cell-back']")).shouldHaveSize(3);
    }

    @Test
    void closestAlfa() {
        // Найти кнопку "Получить карту" в блоке "2 в 1: кредитка без % и наличные"
        // и кликнуть на его ближайшую ссылку (по сути перейти по ссылке в блоке)
        $$(byXpath(".//button[contains(text(),'Получить карту')]")).find(visible)
                .closest("a").click();
    }

    @Test
    void parentAlfa() {
        // Найти текст в блоке "2 в 1: кредитка без % и наличные"
        // и проверить, что родитель содержит нужную подстроку
        $(byXpath(".//p[contains(text(),'100 дней без процентов')]"))
                .parent().shouldHave(text("2 в 1"));
    }

    @Test
    void siblingAlfa() {
        // Найти текст в блоке "2 в 1: кредитка без % и наличные"
        // и проверить, что ближайший соседний эл-т (вниз по дереву) содержит нужную подстроку
        $(byXpath(".//p[contains(text(),'2 в 1: кредитка без %')]"))
                .sibling(0).shouldHave(text("100 дней без процентов"));
    }

    @Test
    void precedingAlfa() {
        // Найти текст в блоке "2 в 1: кредитка без % и наличные"
        // и проверить, что ближайший соседний эл-т (вверх по дереву) содержит нужную подстроку
        $(byXpath(".//p[contains(text(),'100 дней без процентов')]"))
                .preceding(0).shouldHave(text("2 в 1: кредитка без %"));
    }

}
