package com.demoqa.pages;

import com.codeborne.selenide.SelenideElement;
import com.demoqa.components.Calendar;

import java.io.File;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$$;
import static java.lang.String.format;

public class RegistrationPage {

    private final static String FORM_TITLE = "Student Registration Form";
    private final static String RESULTS_TITLE = "Thanks for submitting the form";

    private SelenideElement modal = $("[role=dialog]");

    private Calendar calendar = new Calendar();

    public void openPage() {
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text(FORM_TITLE));
    }

    public RegistrationPage typeFirstName(String firstName) {
        $("#firstName").val(firstName);
        return this;
    }

    public RegistrationPage typeLastName(String lastName) {
        $("#lastName").val(lastName);
        return this;
    }

    public RegistrationPage typeEmail(String email) {
        $("#userEmail").val(email);

        return this;
    }

    public RegistrationPage selectGenger(String gender) {

        $(format("[name=gender][value=%s]", gender)).parent().click();

        return this;
    }

    public RegistrationPage setDateOfBirth(String month, String year, String day) {
        calendar.setDate(month, year, day);
        return this;

    }

    public RegistrationPage typePhone(String phone) {
        $("#userNumber").val(phone);

        return this;
    }

    public RegistrationPage typeSubject(String subject) {
        $("#subjectsInput").val(subject).pressEnter();

        return this;
    }

    public RegistrationPage selectHobbies(String hobby) {

        $("#hobbiesWrapper").$(byText(hobby)).click();
        return this;
    }

    public RegistrationPage uploadFile(String pathname) {
        $("#uploadPicture").uploadFile(new File(pathname));
        return this;
    }

    public RegistrationPage typeAddress(String address) {
        $("#currentAddress").setValue(address);

        return this;
    }

    public RegistrationPage selectState(String state) {
        $("#state").scrollTo().click();
        $("#stateCity-wrapper").$(byText(state)).click();
        return this;
    }

    public RegistrationPage selectCity(String city) {
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        return this;
    }

    public void submitForm() {
        $("#submit").click();
    }

    public void checkResultsTitle() {
        modal.$(".modal-title").shouldHave(text(RESULTS_TITLE));
    }

    public void checkResultsValue(String fullName, String email, String sex,
                                  String phone, String dateOfBirth, String subject,
                                  String hobby, String file, String adress, String stateAndCity) {
        $$("tbody tr").filter(text("Student name")).shouldHave(texts(fullName));
        $$("tbody tr").filter(text("Student Email")).shouldHave(texts(email));
        $$("tbody tr").filter(text("Gender")).shouldHave(texts(sex));
        $$("tbody tr").filter(text("Mobile")).shouldHave(texts(phone));
        $$("tbody tr").filter(text("Date of Birth")).shouldHave(texts(dateOfBirth));
        $$("tbody tr").filter(text("Subjects")).shouldHave(texts(subject));
        $$("tbody tr").filter(text("Hobbies")).shouldHave(texts(hobby));
        $$("tbody tr").filter(text("Picture")).shouldHave(texts(file));
        $$("tbody tr").filter(text("Address")).shouldHave(texts(adress));
        $$("tbody tr").filter(text("State and City")).shouldHave(texts(stateAndCity));
    }

}
