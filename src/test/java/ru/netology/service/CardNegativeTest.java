package ru.netology.service;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardNegativeTest {
    @BeforeEach
    void setupTest() {
        open("http://localhost:9999");
    }

    public String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldFillInValidDataCity() {
        $("[data-test-id='city'] input").setValue("Moskva");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid")
                .should(Condition.text("Доставка в выбранный город недоступна"));
    }

    @Test
    void shouldFillInValidDataCity1() {
        $("[data-test-id='city'] input").setValue("");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='city'].input_invalid")
                .should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFillInValidDataName() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid")
                .should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFillInValidDataName1() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Anna");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='name'].input_invalid")
                .should(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    void shouldFillInValidDataPhone() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid")
                .should(Condition.text("Поле обязательно для заполнения"));
    }

    @Test
    void shouldFillInValidDataPhone1() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+7999");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='phone'].input_invalid")
                .should(Condition.text("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    void shouldFillCheckbox() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(3, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $(".button").click();
        $("[data-test-id='agreement'].input_invalid .checkbox__text")
                .should(Condition.text("Я соглашаюсь с условиями обработки и использования моих персональных данных"));

    }

    @Test
    void shouldFillDate() {
        $("[data-test-id='city'] input").setValue("Москва");
        String date = generateDate(2, "dd.MM.yyyy");
        $("[data-test-id='date'] input").doubleClick().sendKeys(date);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid")
                .should(exactText("Заказ на выбранную дату невозможен"));
    }

    @Test
    void shouldFillDateNull() {
        $("[data-test-id='city'] input").setValue("Москва");
        $("[data-test-id='date'] input").doubleClick().sendKeys(Keys.DELETE);
        $("[data-test-id='name'] input").setValue("Иванов Иван");
        $("[data-test-id='phone'] input").setValue("+79000000000");
        $("[data-test-id='agreement']").click();
        $(".button").click();
        $("[data-test-id='date'] .input_invalid")
                .should(exactText("Неверно введена дата"));
    }
}
