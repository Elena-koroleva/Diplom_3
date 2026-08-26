package config;
/**
 * Конфигурационный класс для хранения эндпоинтов API
 */
public class ApiConfig {
    //ручка на создание пользователя
    public static final String USER_CREATE_PATH = "/api/auth/register";
    //ручка на авторизацию пользователя
    public static final String USER_LOGIN_PATH = "/api/auth/login";
    //ручка на удаление пользователя
    public static final String USER_DELETE_PATH = "/api/auth/user";
}
