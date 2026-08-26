package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Страница регистрации веб-приложения Stellar Burgers
 */
public class RegisterPage extends BasePage {
    //локатор поля имя
    private final By nameField = By.xpath(".//label[text()='Имя']/../input");
    //локатор поля почты
    private final By emailField = By.xpath(".//label[text()='Email']/../input");
    //локатор поля пароль
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    //локатор кнопки "Зарегистрироваться"
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //локатор на некорректный пароль
    private final By passwordError = By.xpath(".//p[text()='Некорректный пароль']");
    //локатор на ссылку "Войти"
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        super(driver);
    }
    @Step("Ввести имя в поле 'Имя'")
    public void inputName(String name) {
        waitForElement(nameField).sendKeys(name);
    }

    @Step("Ввести email в поле 'Email'")
    public void inputEmail(String email) {
        waitForElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль в поле 'Пароль'")
    public void inputPassword(String password) {
        waitForElement(passwordField).sendKeys(password);
    }

    @Step("Нажать на кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        waitForClick(registerButton).click();
    }

    @Step("Заполнить форму регистрации валидными данными и кликнуть кнопку 'Зарегистрироваться'")
    public LoginPage fillRegistrationFormSuccess(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegisterButton();
        return new LoginPage(driver);
    }

    @Step("Заполнить форму регистрации с некорректным паролем и кликнуть кнопку 'Зарегистрироваться'")
    public void fillRegistrationFormWithInvalidPassword(String name, String email, String password) {
        inputName(name);
        inputEmail(email);
        inputPassword(password);
        clickRegisterButton();
    }

    @Step("Нажать на ссылку 'Войти'")
    public LoginPage clickLoginLink() {
        waitForClick(loginLink).click();
        return new LoginPage(driver);
    }

    @Step("Проверить отображение ошибки некорректного пароля")
    public boolean isPasswordErrorDisplayed() {
        return waitForElement(passwordError).isDisplayed();
    }
}
