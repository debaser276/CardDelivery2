package ru.netology.domain;

import com.codeborne.selenide.SelenideElement;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDelivery2Test {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    public void shouldDateChange() {
        CardRequest request = DataGenerator.Request.generate();
        $("[data-test-id='city'] [placeholder='Город']").sendKeys(request.getCity());
        $("[data-test-id='name'] [name='name']").sendKeys(request.getName());
        $("[data-test-id='phone'] [name='phone']").sendKeys(request.getPhone());
        $("[data-test-id='agreement']").click();
        $(withText("Запланировать")).click();

        SelenideElement dateElement = $("[data-test-id='date'] [placeholder='Дата встречи']");
        dateElement.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        LocalDate date = LocalDate.now().plusDays(5);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        dateElement.sendKeys(date.format(formatter));
        $(withText("Запланировать")).click();

        $(".notification__title").shouldHave(text("Успешно!"));
    }

}
