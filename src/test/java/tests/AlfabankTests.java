package tests;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;


public class AlfabankTests {

    @Test
    void archiveDepositListSize() {
        open("https://alfabank.ru/");

        $("[title=Вклады]").click(); // Перейти на страницу Вклады
        $$("[title=Инвестиции]").last().parent()
                .preceding(2).click(); // Перейти на страницу Депозиты
        $(".col-sm-frame p")
                .sibling(0).$("a").click(); // Перейти на страницу Архивные депозиты

        $(".product-cell__cell").closest("div")
                .$$(".product-cell__cell-back").shouldHaveSize(3); //Проверить отображение 3х депозитов
    }
}
