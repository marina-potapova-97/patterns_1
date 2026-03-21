package ru.netology.data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import com.github.javafaker.Faker;
import lombok.Value;

public class GeneratorDate {
    private GeneratorDate() {}

    public static String generateDate(int days, String pattern) {
        return LocalDate.now().plusDays(days).format(DateTimeFormatter.ofPattern(pattern));
    }

    public static String generateCity(Faker faker) {
        return faker.address().city();
    }

    public static String generateName(Faker faker) {
        return faker.name().lastName() + " " + faker.name().firstName();
    }

    public static String generatePhone(Faker faker) {
        return faker.phoneNumber().cellPhone();
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale) {
            faker = new Faker(new Locale(locale));
            // TODO: добавить логику для создания пользователя user с использованием методов generateCity(faker),
            // generateName(faker), generatePhone(faker)
            return new UserInfo(generateCity(faker), generateName(faker), generatePhone(faker));
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String name;
        String phone;
    }
}
