package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import lombok.Getter;
import org.junit.rules.ExternalResource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static config.AppConfig.BASE_URL;
/**
 * Класс управления жизненным циклом WebDriver
 */
@Getter
public class FactoryDriver extends ExternalResource {

    // Метод для получения драйвера
    private WebDriver driver;

    /**
 * Метод выполняется автоматически перед каждым тестом.
 * Отвечает за инициализацию нужного браузера и его настройки.
 */
    @Override
    protected void before() {
//Считываем переменную "browser", переданную из консоли. Если её нет, используем по дефолту "chrome"
        String browser = System.getProperty("browser", "chrome");
        System.out.println("Запуск теста для браузера: " + browser);

        if ("yandex".equalsIgnoreCase(browser)) {
//Указываем путь к скачанному исполняемому файлу yandexdriver
            System.setProperty("webdriver.chrome.driver", "drivers/yandexdriver.exe");
//Создаем объект настроек (Яндекс Браузер работает на базе Chromium, поэтому используем ChromeOptions)
            ChromeOptions yandexOptions = new ChromeOptions();
//Указываем точный путь к установленному в системе Яндекс Браузеру
            yandexOptions.setBinary("C:\\Users\\Елена\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
//Добавляем обязательные аргументы для стабильной работы драйвера
            yandexOptions.addArguments("--no-sandbox", "--disable-dev-shm-usage", "--remote-allow-origins=*");
//Инициализируем ChromeDriver с конфигурацией под Яндекс
            driver = new ChromeDriver(yandexOptions);
        } else { //Логика инициализации Chrome по умолчанию
//Автоматически скачиваем и настраиваем подходящую версию chromedriver через WebDriverManager
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options); //Инициализируем стандартный ChromeDriver
        }
        driver.manage().window().maximize(); //Разворачиваем окно браузера на весь экран
        // Устанавливаем базовый URL для автоматической подстановки во все запросы RestAssured
        RestAssured.baseURI = BASE_URL;
    }
/**
 * Метод выполняется автоматически после каждого теста.
 * Гарантирует закрытие сессии браузера, даже если тест упал с ошибкой.
 */
    @Override
    protected void after() {
        if (driver != null) {
            driver.quit();
        }
    }
}
