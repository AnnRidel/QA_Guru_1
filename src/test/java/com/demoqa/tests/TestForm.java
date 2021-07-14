package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.RandomUtils;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class TestForm {

    RegistrationPage registrationPage = new RegistrationPage();
    RandomUtils randomUtils = new RandomUtils();
    Faker faker = new Faker();

    String firstName = faker.name().firstName();
    String lastName = faker.name().lastName();
    String email = faker.internet().emailAddress();
    String gender = randomUtils.getRandomGender();
    String phone = faker.phoneNumber().subscriberNumber(10);
    String month = randomUtils.getRandomMonth();
    String year = randomUtils.getRandomYear();
    String day = randomUtils.getRandomDay(year, month);
    String hobby = randomUtils.getRandomHobby();
    String subject = randomUtils.getRandomSubject();
    String file = randomUtils.getRandomFile();
    String address = faker.address().fullAddress();
    String state = randomUtils.getRandomState();
    String city = randomUtils.getRandomCity(state);

    @BeforeAll
    static void setup() {
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.startMaximized = true;
    }

    @Test
    void positiveFillTest() {
        registrationPage.openPage();
        registrationPage.typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(email)
                .selectGenger(gender)
                .typePhone(phone)
                .setDateOfBirth( month, year, day)
                .typeSubject(subject)
                .selectHobbies(hobby)
                .uploadFile("src/test/resources/" + file)
                .typeAddress(address);
        registrationPage.scrollPage();
        registrationPage.selectState(state)
                .selectCity(city)
                .submitForm();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(firstName + " " + lastName, email, gender, phone,
                day + " " + month + "," + year, subject, hobby, file,
                address, state + " " + city);
    }
}