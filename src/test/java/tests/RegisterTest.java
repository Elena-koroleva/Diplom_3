package tests;

import base.FactoryDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.LoginModel;
import model.UserModel;
import org.junit.*;
import pages.LoginPage;
import pages.RegisterPage;

import static config.AppConfig.REGISTER_URL;
import static data.UserData.getRandomUser;
import static data.UserData.getUserWithShortPassword;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertTrue;
import static steps.UserSteps.deleteUser;
import static steps.UserSteps.loginUser;
@Epic("Пользовательский интерфейс")
@Feature("Регистрация пользователя")
public class RegisterTest {
    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();
    // Переменная для хранения токена, чтобы удалить юзера после теста
    private String accessToken;

    @Test
    @DisplayName("Успешная регистрация пользователя")
    @Story("Создание аккаунта с валидными данными")
    @Description("Создается пользователь через форму регистрации " +
            "при вводе валидных данных name, email и password(от 6 символов).<br>" +
            "<b>Ожидаемый результат:</b> Автоматический переход на страницу логина (/login).")
    public void successRegistrationTest() {
        // Генерируем случайные валидные данные пользователя (пароль> 6 символов)
        UserModel user = getRandomUser();
        // Открываем сразу страницу регистрации
        factoryDriver.getDriver().get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(factoryDriver.getDriver());
        // Заполняем форму и нажимаем кнопку. Метод возвращает LoginPage, так как сайт перенаправляет туда
        LoginPage loginPage = registerPage.fillRegistrationFormSuccess(user.getName(), user.getEmail(), user.getPassword());
        // Проверяем, что перешли на страницу логина (появился заголовок "Вход")
        assertTrue("Заголовок 'Вход' должен отображаться после успешной регистрации",
                loginPage.isLoginHeaderDisplayed());
        // Вытаскиваем токен созданного юзера, чтобы удалить его в @After
        LoginModel loginModel = new LoginModel(user.getEmail(), user.getPassword());
        Response loginResponse = loginUser(loginModel);
        if (loginResponse.getStatusCode() == HTTP_OK) {
            accessToken = loginResponse.path("accessToken");
        }
    }

    @Test
    @DisplayName("Ошибка регистрации при некорректном пароле")
    @Story("Создание аккаунта с невалидным паролем")
    @Description("Появляется сообщение об ошибке на форме регистрации, если длина пароля менее 6 символов.<br>" +
            "<b>Ожидаемый результат:</b> Под полем пароль появляется ошибка 'Некорректный пароль'.")
    public void registrationWithShortPasswordReturnsErrorTest() {
        // Генерируем случайные данные пользователя с невалидным паролем
        UserModel user = getUserWithShortPassword();
        // Открываем страницу регистрации
        factoryDriver.getDriver().get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(factoryDriver.getDriver());
        // Заполняем форму с невалидным паролем и нажимаем кнопку. Остаемся на той же странице
        registerPage.fillRegistrationFormWithInvalidPassword(user.getName(), user.getEmail(), user.getPassword());
        // Проверяем, что отобразилась ошибка "Некорректный пароль" под полем пароля
        assertTrue("Должна отображаться ошибка некорректного пароля",
                registerPage.isPasswordErrorDisplayed());
    }
    @After
    public void tearDown() {
        // Если в ходе теста токен был получен (пользователь успешно создался), удаляем его
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }
}
