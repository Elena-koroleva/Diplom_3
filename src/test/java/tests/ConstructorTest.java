package tests;

import base.FactoryDriver;
import io.qameta.allure.Description;
import io.qameta.allure.*;
import io.qameta.allure.junit4.DisplayName;
import org.junit.*;
import pages.MainPage;

import static config.AppConfig.BASE_URL;
import static org.junit.Assert.assertTrue;

@Epic("Пользовательский интерфейс")
@Feature("Раздел 'Конструктор'")
public class ConstructorTest {
    @Rule //правило для управления браузером
    public FactoryDriver factoryDriver = new FactoryDriver();

    private MainPage mainPage;

    @Before
    public void setUp() {
        // Перед каждым тестом открываем сайт и создаем объект страницы
        factoryDriver.getDriver().get(BASE_URL);
        mainPage = new MainPage(factoryDriver.getDriver());
    }
    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Story("Навигация по вкладкам Конструктора")
    @Description("Активируется и подсвечивается вкладка 'Соусы' при клике на нее в Конструкторе.<br>" +
            "<b>Ожидаемый результат:</b> Вкладка 'Соусы' становится активной и подсвечивается.")
    public void checkTransitionToSaucesTabTest() {
        mainPage.clickSaucesTab(); // Нажимаем на вкладку Соусы
        assertTrue("Вкладка 'Соусы' должна стать активной", mainPage.isTabActive("Соусы"));
    }

    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Story("Навигация по вкладкам Конструктора")
    @Description("Активируется и подсвечивается вкладка 'Начинки' при клике на нее в Конструкторе.<br>" +
            "<b>Ожидаемый результат:</b> Вкладка 'Начинки' становится активной и подсвечивается.")
    public void checkTransitionToFillingsTabTest() {
        mainPage.clickFillingsTab(); // Нажимаем на вкладку Начинки
        assertTrue("Вкладка 'Начинки' должна стать активной", mainPage.isTabActive("Начинки"));
    }

    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Story("Навигация по вкладкам Конструктора")
    @Description("Возвращается активация и подсветка вкладки 'Булки' после переключения из другого раздела Конструктора.<br>" +
            "<b>Ожидаемый результат:</b> Вкладка 'Булки' снова возвращается в активное состояние и подсвечивается.")
    public void checkTransitionToBunsTabTest() {
        mainPage.clickSaucesTab(); // Нажимаем на вкладку Соусы
        mainPage.isTabActive("Соусы"); // Подождали, пока вкладка Соусы станет активной
        mainPage.clickBunsTab(); // Нажимаем на вкладку Булки
        assertTrue("Вкладка 'Булки' должна вернуться в активное состояние", mainPage.isTabActive("Булки"));
    }
}
