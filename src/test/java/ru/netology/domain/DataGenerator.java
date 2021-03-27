package ru.netology.domain;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {

    private DataGenerator() {}

    public static String generateDate(int shift) {
        LocalDate date = LocalDate.now().plusDays(shift);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateCity() {
        List<String> cities = Arrays.asList("Петрозаводск", "Пенза", "Пермь", "Казань", "Липецк");
        Random random = new Random();
        return cities.get(random.nextInt(cities.size()));
    }

    public static String generateName(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.name().fullName();
    }

    public static String generatePhone(String locale) {
        Faker faker = new Faker(new Locale(locale));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Request {
        private Request() {}

        public static CardRequest generate(String locale) {
            return new CardRequest(
                    DataGenerator.generateCity(),
                    DataGenerator.generateName(locale),
                    DataGenerator.generatePhone(locale)
            );
        }
    }

}
