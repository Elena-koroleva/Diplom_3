package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Модель данных для авторизации пользователя
 */
@AllArgsConstructor
@Setter
@Getter
public class LoginModel {
    private String email;
    private String password;
}
