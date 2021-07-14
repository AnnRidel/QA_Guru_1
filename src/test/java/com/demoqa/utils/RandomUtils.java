package com.demoqa.utils;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.List;

public class RandomUtils {

    public static String getRandomString(String[] parametres) {

        SecureRandom random = new SecureRandom();
        List list = Arrays.asList(parametres);
        Object result = list.get(random.nextInt(list.size()));
        return result.toString();
    }

    public String getRandomGender() {
        String[] sex = {"Male", "Female", "Other"};
        return getRandomString(sex);
    }

    public String getRandomFile() {
        String[] file = {"sampleFile.jpeg", "778.png", "852.png", "char-eevee.png", "pika.jpg"};
        return getRandomString(file);
    }

    public String getRandomHobby() {
        String[] hobby = {"Sports", "Reading", "Music"};
        return getRandomString(hobby);
    }

    public String getRandomSubject() {
        String[] subject = {"English", "Maths", "Physics", "Chemistry", "Computer Science",
                "Economics", "Arts", "Social Studies", "History", "Civics"};
        return getRandomString(subject);
    }

    public String getRandomState() {
        String[] state = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
        return getRandomString(state);
    }

    public String getRandomCity(String state) {
        //выбор города зависит от выбранного штата
        switch (state) {
            case "NCR":
                return getRandomString(new String[]{"Delhi", "Gurgaon", "Noida"});
            case "Uttar Pradesh":
                return getRandomString(new String[]{"Agra", "Lucknow", "Merrut"});
            case "Haryana":
                return getRandomString(new String[]{"Karnal", "Panipat"});
            case "Rajasthan":
                return getRandomString(new String[]{"Jaipur", "Jaiselmer"});

            default:
                return getRandomString(new String[]{"Check State"});
        }

    }

    public static int getRandomNumber(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }

    public String getRandomYear() {
        return Integer.toString((getRandomNumber(1990, 2005)));
    }

    public String getRandomMonth() {
        String[] month = {"January", "February", "March",
                "April", "May", "June", "July", "August",
                "September", "October", "November", "December"};
        return getRandomString(month);
    }

    public String getRandomDay(String year, String month) {
        //максимальное число дней зависит от месяца и от того, является ли год високосным (для февраля)
        int chekYear = Integer.parseInt(year);
        int day;
        if (month.equals("February")) {
            if ((chekYear % 400 == 0) || ((chekYear % 4 == 0) && (chekYear % 100 != 0))) {
                day = (getRandomNumber(1, 29));
            } else {
                day = (getRandomNumber(1, 28));
            }
            if (day < 10) {
                return ("0" + day);
            } else {
                return Integer.toString(day);
            }
        } else {
            switch (month) {
                case "January":
                case "March":
                case "May":
                case "July":
                case "August":
                case "October":
                case "December":
                    day = (getRandomNumber(1, 31));
                    if (day < 10) {
                        return ("0" + day);
                    } else {
                        return Integer.toString(day);
                    }
                case "April":
                case "June":
                case "September":
                case "November":
                    day = (getRandomNumber(1, 30));
                    if (day < 10) {
                        return ("0" + day);
                    } else {
                        return Integer.toString(day);
                    }
                default:
                    return Integer.toString(getRandomNumber(10, 28));
            }
        }
    }
}

