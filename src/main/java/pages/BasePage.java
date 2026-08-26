package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
/**
 * Базовый класс для всех страниц (Page Object)
 * Содержит общую логику инициализации драйвера и настройки явных ожиданий
 */
public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

//Конструктор базовой страницы, который вызывается в наследниках через super(driver)
    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //Метод ждет появления элемента на экране и возвращает его
    protected WebElement waitForElement(By locator) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    //Метод ждет, когда элемент станет кликабельным
    protected WebElement waitForClick(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    //Метод ждет, пока указанный атрибут элемента будет содержать нужный текст
    protected boolean waitForAttributeContains(By locator, String attribute, String value) {
        return wait.until(ExpectedConditions.attributeContains(locator, attribute, value));
    }
}
