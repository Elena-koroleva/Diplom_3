package config;
/**
 * Конфигурационный класс для хранения URL-адресов веб-приложения
 */
public class AppConfig {
    //Базовый URL главной страницы
    public static final String BASE_URL = "https://stellarburgers.education-services.ru/";
    //URL страницы регистрации
    public static final String REGISTER_URL = BASE_URL + "register";
    //URL страницы Восстановление пароля
    public static final String FORGOT_PASSWORD_URL = BASE_URL + "forgot-password";
}
