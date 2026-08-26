package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Страница восстановления пароля веб-приложения Stellar Burgers
 */
public class ForgotPasswordPage extends BasePage {

    //локатор ссылки "Войти"
    private final By loginLink = By.xpath(".//a[text()='Войти']");

    public ForgotPasswordPage(WebDriver driver) {
        super(driver);
    }

    // Метод кликает по ссылке "Войти"
    @Step("Нажать на ссылку 'Войти' на странице 'Восстановление пароля'")
    public LoginPage clickLoginLink() {
        waitForClick(loginLink).click();
        return new LoginPage(driver);
    }
}

