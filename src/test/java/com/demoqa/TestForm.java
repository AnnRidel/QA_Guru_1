package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class TestForm {

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("ivan@ivanov.ru");
        $("#gender-radio-3").doubleClick();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1993");
        $(".react-datepicker__day--008").click();
        $("#subjectsInput").setValue("English").pressEnter();
        $("#subjectsInput").setValue("Computer Science").pressEnter();       $(byText("Submit")).scrollTo();
        $("#hobbiesWrapper").$(byText("Reading")).click();
        $("#hobbiesWrapper").$(byText("Music")).click();
        $("#uploadPicture").uploadFile (new File("src/test/resources/sampleFile.jpeg"));
        $("#currentAddress").setValue("Somewhere");
        $("#state").click();
        $("#react-select-3-input").val("NCR").pressEnter();
        $("#city").click();
        $("#react-select-4-input").val("Noida").pressEnter();
        $("#submit").click();

        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        $$("tbody tr").filter(text("Student name")).shouldHave(texts("Ivan Ivanov"));
        $$("tbody tr").filter(text("Student Email")).shouldHave(texts("ivan@ivanov.ru"));
        $$("tbody tr").filter(text("Gender")).shouldHave(texts("Other"));
        $$("tbody tr").filter(text("Mobile")).shouldHave(texts("1234567890"));
        $$("tbody tr").filter(text("Date of Birth")).shouldHave(texts("08 November,1993"));
        $$("tbody tr").filter(text("Subjects")).shouldHave(texts("English, Computer Science"));
        $$("tbody tr").filter(text("Hobbies")).shouldHave(texts("Reading, Music"));
        $$("tbody tr").filter(text("Picture")).shouldHave(texts("sampleFile.jpeg"));
        $$("tbody tr").filter(text("Address")).shouldHave(texts("Somewhere"));
        $$("tbody tr").filter(text("State and City")).shouldHave(texts("NCR Noida"));
    }
}