package ru.netology.bank.data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.util.Locale;

public class DataHelper {
    private DataHelper(){
    }
    private static final Faker faker = new Faker(new Locale("en"));

    @Value
    public static class AuthInfo {
         String login;
         String password;
    }
    public static AuthInfo getAuthInfoTest() {
        return new AuthInfo("vasya", "qwerty123");
    }
    public static  AuthInfo generateRandomUser(){
        return new AuthInfo(generateRandomLogin(),generateRandomPassword());
    }
    private static String generateRandomLogin(){
     return faker.name().username();
    }
    private static String generateRandomPassword(){
        return faker.internet().password();
    }

    @Value
    public static class VerificationCode {
        String code;
    }
    public static  VerificationCode generateRanVerificationCode(){
        return new VerificationCode(faker.numerify("######"));
    }
}
