package com.demoqa.tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.demoqa.pages.RegistrationPage;
import com.demoqa.utils.Attach;
import com.demoqa.utils.RandomUtils;
import com.github.javafaker.Faker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

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
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.baseUrl = "https://demoqa.com";
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
        Configuration.startMaximized = true;
        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub/";
    }

    @AfterEach
    public void tearDown() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
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
                .typeAddress(address)
                .selectState(state)
                .selectCity(city)
                .submitForm();

        registrationPage.checkResultsTitle();
        registrationPage.checkResultsValue(firstName + " " + lastName, email, gender, phone,
                day + " " + month + "," + year, subject, hobby, file,
                address, state + " " + city);
    }
}