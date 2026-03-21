package ru.netology.test;

import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;
import ru.netology.data.GeneratorDate;
import java.time.Duration;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

class DateTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    void shouldSuccessfulPlanAndReplanMeeting() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = GeneratorDate.generateDate(daysToAddForSecondMeeting, "dd.MM.yyyy");
        // Заполнение полей формы для первой встречи
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $(".notification__content").should(Condition.visible)
                .should(Condition.text("Встреча успешно запланирована на " + firstMeetingDate));
        //Перепланирование
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(secondMeetingDate);
        $("[data-test-id='date'] input").setValue(secondMeetingDate);
        $("button.button").click();
        $("[data-test-id='replan-notification']").click();
        $(".notification__content").should(Condition.visible, Duration.ofSeconds(10))
                .should(Condition.text("Встреча успешно запланирована на " + secondMeetingDate));
    }

    @Test
    void NegativeZeroCity() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void NegativeZeroDate() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Неверно введена дата"));
    }
    @Test
    void NegativeZeroName() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = GeneratorDate.generateDate(daysToAddForSecondMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void NegativeZeroPhone() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = GeneratorDate.generateDate(daysToAddForSecondMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE);
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    @Test
    void NegativeCheckBox() {
        var validUser = GeneratorDate.Registration.generateUser("ru"); // Используем GeneratorDate
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        var daysToAddForSecondMeeting = 7;
        var secondMeetingDate = GeneratorDate.generateDate(daysToAddForSecondMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("button.button").click();
        $("[data-test-id='success-notification']").shouldNotBe(Condition.visible);
    }
    @Test
    void NegativeCity() {
        var noValidUser = GeneratorDate.Registration.generateUser("ENG");
        var validUser = GeneratorDate.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(noValidUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='city'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Доставка в выбранный город недоступна"));
    }
    @Test
    void NegativeDate() {
        var validUser = GeneratorDate.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 1;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='date'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Заказ на выбранную дату невозможен"));
    }
    @Test
    void NegativeName() {
        var noValidUser = GeneratorDate.Registration.generateUser("ENG");
        var validUser = GeneratorDate.Registration.generateUser("ru");
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(noValidUser.getName());
        $("[data-test-id='phone'] input").setValue(validUser.getPhone());
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='name'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
         }
    @Test
    void NegativePhone() {
        var validUser = GeneratorDate.Registration.generateUser("ru");
        String invalidPhoneNumber = "123";
        var daysToAddForFirstMeeting = 4;
        var firstMeetingDate = GeneratorDate.generateDate(daysToAddForFirstMeeting, "dd.MM.yyyy");
        $("[data-test-id='city'] input").setValue(validUser.getCity());
        $("[data-test-id='date'] input").press(Keys.chord(Keys.SHIFT, Keys.HOME),Keys.DELETE)
                .setValue(firstMeetingDate);
        $("[data-test-id='name'] input").setValue(validUser.getName());
        $("[data-test-id='phone'] input").setValue(invalidPhoneNumber);
        $("[data-test-id='agreement']").click();
        $("button.button").click();
        $("[data-test-id='phone'] .input__sub").shouldBe(Condition.visible)
                .shouldHave(Condition.text("На указанный номер моб. тел. будет отправлен смс-код для подтверждения заявки на карту. Проверьте, что номер ваш и введен корректно."));
    }

    }


