package tests;

import base.FactoryDriver;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import model.UserModel;
import org.junit.*;
import pages.*;

import static config.AppConfig.*;
import static data.UserData.getRandomUser;
import static java.net.HttpURLConnection.HTTP_OK;
import static org.junit.Assert.assertTrue;
import static steps.UserSteps.createUser;
import static steps.UserSteps.deleteUser;

@Epic("Пользовательский интерфейс")
@Feature("Авторизация пользователя")
public class LoginTest {
    @Rule // Правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    private UserModel user; // Переменная для создания пользователя
    private String accessToken; // Переменная для хранения токена

    @Before
    public void setUp() {
        // Генерируем случайные данные пользователя
        user = getRandomUser();
        // Создаем пользователя через API
        Response response = createUser(user);
        // Вытаскиваем токен, чтобы в конце теста удалить пользователя
        if (response.getStatusCode() == HTTP_OK) {
            accessToken = response.path("accessToken");
        }
    }

    @Test
    @DisplayName("Вход по кнопке 'Войти в аккаунт' на главной")
    @Story("Авторизация с главной страницы")
    @Description("Можно авторизоваться по кнопке 'Войти в аккаунт' на главной странице.<br>" +
            "<b>Ожидаемый результат:</b> Сайт перенаправляет на форму логина. Происходит успешная авторизация." +
            "При переходе в 'Личный кабинет' видна подсказка профиля")
    public void loginViaMainPageButtonTest() {
        // Открываем главную страницу
        factoryDriver.getDriver().get(BASE_URL);
        MainPage mainPage = new MainPage(factoryDriver.getDriver());
        // Кликаем по кнопке входа на главной, происходит переход на LoginPage
        LoginPage loginPage = mainPage.clickLoginAccountButton();
        // Авторизуемся. Метод fillLoginForm вернет MainPage обратно
        mainPage = loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        // Переходим в Личный кабинет для проверки, что вход действительно выполнен успешно
        ProfilePage profilePage = mainPage.clickPersonalAccountLinkAsUser();
        assertTrue("Пользователь должен успешно авторизоваться по кнопке 'Войти в аккаунт'",
                profilePage.isProfileHintDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет' в шапке")
    @Story("Авторизация через кнопку 'Личный кабинет'")
    @Description("Можно авторизоваться по ссылке 'Личный кабинет' неавторизованным пользователем.<br>" +
            "<b>Ожидаемый результат:</b> Сайт перенаправляет на форму логина. Происходит успешная авторизация." +
            "При переходе в 'Личный кабинет' видна подсказка профиля")
    public void loginViaPersonalAccountLinkTest() {
        factoryDriver.getDriver().get(BASE_URL);
        MainPage mainPage = new MainPage(factoryDriver.getDriver());
        // Кликаем на "Личный кабинет" в шапке (т.к. мы не вошли, сайт перекинет на страницу логина)
        LoginPage loginPage = mainPage.clickPersonalAccountLinkAsGuest();
        // Авторизуемся. Метод fillLoginForm вернет MainPage обратно
        mainPage = loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        // Переходим в Личный кабинет для проверки, что вход действительно выполнен успешно
        ProfilePage profilePage = mainPage.clickPersonalAccountLinkAsUser();
        assertTrue("Пользователь должен успешно авторизоваться через кнопку 'Личный кабинет'",
                profilePage.isProfileHintDisplayed());
    }

    @Test
    @DisplayName("Вход через ссылку 'Войти' в форме регистрации")
    @Story("Авторизация со страницы регистрации")
    @Description("Можно авторизоваться по ссылке 'Войти' под формой регистрации.<br>" +
            "<b>Ожидаемый результат:</b> Сайт перенаправляет на форму логина. Происходит успешная авторизация." +
            "При переходе в 'Личный кабинет' видна подсказка профиля")
    public void loginViaRegisterPageLinkTest() {
        // Открываем сразу страницу регистрации
        factoryDriver.getDriver().get(REGISTER_URL);
        RegisterPage registerPage = new RegisterPage(factoryDriver.getDriver());
        // Нажимаем ссылку "Войти" внизу формы регистрации
        LoginPage loginPage = registerPage.clickLoginLink();
        // Авторизуемся. Метод fillLoginForm вернет MainPage обратно
        MainPage mainPage = loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        // Переходим в Личный кабинет для проверки, что вход действительно выполнен успешно
        ProfilePage profilePage = mainPage.clickPersonalAccountLinkAsUser();
        assertTrue("Пользователь должен успешно авторизоваться через ссылку 'Войти' в форме регистрации",
                profilePage.isProfileHintDisplayed());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Story("Авторизация со страницы восстановления пароля")
    @Description("Можно авторизоваться через ссылку 'Войти' на странице восстановления пароля.<br>" +
            "<b>Ожидаемый результат:</b> Сайт перенаправляет на форму логина. Происходит успешная авторизация." +
            "При переходе в 'Личный кабинет' видна подсказка профиля")
    public void loginViaForgotPasswordPageLinkTest() {
        // Открываем сразу страницу восстановления пароля
        factoryDriver.getDriver().get(FORGOT_PASSWORD_URL);
        ForgotPasswordPage forgotPasswordPage = new ForgotPasswordPage(factoryDriver.getDriver());
        // Нажимаем ссылку "Войти" под формой восстановления пароля
        LoginPage loginPage = forgotPasswordPage.clickLoginLink();
        // Авторизуемся. Метод fillLoginForm вернет MainPage обратно
        MainPage mainPage = loginPage.fillLoginForm(user.getEmail(), user.getPassword());
        // Переходим в Личный кабинет для проверки, что вход действительно выполнен успешно
        ProfilePage profilePage = mainPage.clickPersonalAccountLinkAsUser();
        assertTrue("Пользователь должен успешно авторизоваться через кнопку в форме восстановления пароля",
                profilePage.isProfileHintDisplayed());
    }

    @After
    public void tearDown() {
        // Чистим базу данных бэкенда после каждого теста
        if (accessToken != null) {
            deleteUser(accessToken);
        }
    }
}
