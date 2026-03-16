package no.genie.accountiae2etestsselenium.helpers;

import net.datafaker.Faker;

import java.util.Locale;

public class DataFakerHelper {
    private static Faker faker;

    private static Faker createDataFaker() {
        faker = new Faker(new Locale("en-US"));
        return faker;
    }

    public static Faker getDataFaker() {
        if (faker == null) {
            faker = createDataFaker();
        }
        return faker;
    }

    public static void resetDataFaker(Faker faker) {
        DataFakerHelper.faker = faker;
    }
}
