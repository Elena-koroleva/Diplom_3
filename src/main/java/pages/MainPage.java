package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
/**
 * Главная страница веб-приложения Stellar Burgers
 */
public class MainPage extends BasePage {
    //локатор на кнопку «Войти в аккаунт», когда пользователь не авторизован
    private final By loginAccountButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //локатор на ссылку «Личный Кабинет» в шапке сайта
    private final By personalAccountLink = By.xpath(".//p[text()='Личный Кабинет']");

    //локаторы на вкладки разделов в Конструкторе
    private final By bunsTabDiv = By.xpath(".//span[text()='Булки']/parent::div");
    private final By saucesTabDiv = By.xpath(".//span[text()='Соусы']/parent::div");
    private final By fillingsTabDiv = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        super(driver);
    }

    @Step("Нажать кнопку 'Войти в аккаунт' на Главной странице")
    public LoginPage clickLoginAccountButton() {
        waitForClick(loginAccountButton).click();
        return new LoginPage(driver);
    }

    @Step("Нажать кнопку 'Личный Кабинет' (неавторизованным пользователем)")
    public LoginPage clickPersonalAccountLinkAsGuest() {
        waitForClick(personalAccountLink).click();
        return new LoginPage(driver); // Возвращает LoginPage, так как гостя сайт просит войти
    }

    @Step("Нажать кнопку 'Личный Кабинет' (авторизованным пользователем)")
    public ProfilePage clickPersonalAccountLinkAsUser() {
        waitForClick(personalAccountLink).click();
        return new ProfilePage(driver); // Возвращает ProfilePage, так как юзера сайт пускает внутрь
    }

    @Step("Нажать вкладку 'Булки' на Главной странице")
    public void clickBunsTab() {
        waitForClick(bunsTabDiv).click();
    }

    @Step("Нажать вкладку 'Соусы' на Главной странице")
    public void clickSaucesTab() {
        waitForClick(saucesTabDiv).click();
    }

    @Step("Нажать вкладку 'Начинки' на Главной странице")
    public void clickFillingsTab() {
        waitForClick(fillingsTabDiv).click();
    }

    @Step("Проверить, что вкладка '{tabName}' стала активной")
    public boolean isTabActive(String tabName) {
        By targetTab;
        switch (tabName) {
            case "Булки": targetTab = bunsTabDiv; break;
            case "Соусы": targetTab = saucesTabDiv; break;
            case "Начинки": targetTab = fillingsTabDiv; break;
            default: throw new IllegalArgumentException("Неизвестная вкладка: " + tabName);
        }
        return waitForAttributeContains(targetTab, "class", "tab_type_current");
    }
}
