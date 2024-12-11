package ru.netology.service;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardPositiveTest {
    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldFillValidData() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='notification']")
                .should(visible, Duration.ofSeconds(15))
                .should(Condition.text("Встреча успешно забронирована на " + date));
    }

}
