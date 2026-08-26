package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Страница авторизации (входа) веб-приложения Stellar Burgers
 */
public class LoginPage extends BasePage {
    //локатор поля почты
    private final By emailField = By.xpath(".//label[text()='Email']/../input");
    //локатор поля пароль
    private final By passwordField = By.xpath(".//label[text()='Пароль']/../input");
    //локатор кнопки "Войти"
    private final By loginButton = By.xpath(".//button[text()='Войти']");
    //локатор на заголовок "Вход"
    private final By loginHeader = By.xpath(".//h2[text()='Вход']");

    public LoginPage(WebDriver driver) {
        super(driver);
    }
    @Step("Ввести email на странице входа")
    public void inputEmail(String email) {
        waitForElement(emailField).sendKeys(email);
    }

    @Step("Ввести пароль на странице входа")
    public void inputPassword(String password) {
        waitForElement(passwordField).sendKeys(password);
    }

    @Step("Нажать кнопку 'Войти' на странице входа")
    public void clickLoginButton() {
        waitForClick(loginButton).click();
    }

    @Step("Заполнить форму авторизации: email='{email}', password и нажать кнопку 'Войти'")
    public MainPage fillLoginForm(String email, String password) {
        inputEmail(email);
        inputPassword(password);
        clickLoginButton();
        return new MainPage(driver); // Возвращаем объект Главной страницы, куда попадаем после входа
    }

    @Step("Проверить, что заголовок 'Вход' отображается")
    public boolean isLoginHeaderDisplayed() {
        return waitForElement(loginHeader).isDisplayed();
    }
}
