package steps;

import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import model.LoginModel;
import model.UserModel;

import static config.ApiConfig.*;
import static io.restassured.RestAssured.given;
/**
 * Класс шагов для взаимодействия с API (подготовка и очистка данных)
 */
public class UserSteps {
    @Step("Создать пользователя в системе")
    public static Response createUser(UserModel user){
        return given()
                .log().all().contentType(ContentType.JSON)
                .body(user)
                .when().post(USER_CREATE_PATH);
    }
    @Step("Авторизовать пользователя в системе")
    public static Response loginUser(LoginModel loginModel) {
        return given()
                .log().all().contentType(ContentType.JSON)
                .body(loginModel)
                .when().post(USER_LOGIN_PATH);
    }
    @Step("Удалить пользователя из системы")
    public static Response deleteUser(String accessToken){
        return given()
                .log().all().contentType(ContentType.JSON)
                .header("Authorization",accessToken)
                .when().delete(USER_DELETE_PATH);
    }
}
