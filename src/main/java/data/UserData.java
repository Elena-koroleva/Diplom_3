package data;

import com.github.javafaker.Faker;
import model.UserModel;
/**
 * Класс тестовых данных для генерации пользователей
 */
public class UserData {

    private static Faker faker = new Faker();
//Генерирует модель валидного пользователя со случайными данными
    public static UserModel getRandomUser() {
        String randomEmail = faker.regexify("[0-9]{4}") + faker.name().username() + "@yandex.ru";
        String randomPassword = "password" + faker.regexify("[0-9]{4}");
        String randomName = faker.name().firstName();
        return new UserModel(randomEmail, randomPassword, randomName);
    }
//Генерирует модель пользователя с некорректным (коротким) паролем
public static UserModel getUserWithShortPassword() {
    String randomEmail = faker.regexify("[0-9]{4}") + faker.name().username() + "@yandex.ru";
    String shortPassword = faker.regexify("[0-9]{4}"); //Пароль из 4 символов (меньше минимальных 6 по ТЗ)
    String randomName = faker.name().firstName();
    return new UserModel(randomEmail, shortPassword, randomName);
}
}
