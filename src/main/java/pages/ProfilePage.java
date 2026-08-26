package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Страница личного кабинета авторизованного пользователя веб-приложения Stellar Burgers
 */
public class ProfilePage extends BasePage {

    // Локатор текста-подсказки, который есть только в личном кабинете
    private final By profileHint = By.xpath(".//p[contains(text(), 'В этом разделе вы можете изменить свои персональные данные')]");

    public ProfilePage(WebDriver driver) {
        super(driver);
    }

    @Step("Проверить отображение авторизованного профиля пользователя")
    public boolean isProfileHintDisplayed() {
        return waitForElement(profileHint).isDisplayed();
    }
}
