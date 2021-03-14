package ru.netology.domain;

import com.github.javafaker.Faker;

import java.util.Locale;

public class DataGenerator {

    private DataGenerator() {}

    public static class Request {
        private Request() {}

        public static CardRequest generate() {
            Faker faker = new Faker(new Locale("ru"));
            return new CardRequest(
                    "Казань",
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

}
