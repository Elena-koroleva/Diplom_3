package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
/**
 * Модель данных для регистрации пользователя
 */
@AllArgsConstructor
@Setter
@Getter
public class UserModel {
    private String email;
    private String password;
    private String name;
}
