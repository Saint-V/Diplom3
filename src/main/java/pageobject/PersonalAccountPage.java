package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;  // Добавленный импорт

public class PersonalAccountPage {

    private WebDriver driver;

    //кнопка Выход
    private final By exitButton = By.xpath(".//button[text()='Выход']");
    //кнопка Конструктор
    private final By constructorButton = By.xpath(".//a[@class='AppHeader_header__link__3D_hX' and @href='/']");
    //логотип в шапке
    private final By logoBurger = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']/a[@href='/']");

    public PersonalAccountPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Выход в личном кабинете")
    public void clickExitButton() {
        driver.findElement(exitButton).click();
    }

    @Step("Ожидание загрузки страницы личного кабинета")
    public void waitOfVisibilityExitButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))  // Исправленная строка
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

    @Step("Проверка видимости кнопки Выйти")
    public boolean exitButtonIsDisplayed() {
        return driver.findElement(exitButton).isDisplayed();
    }

    @Step("Нажатие на кнопку Конструктор")
    public void clickConstructorButton() {
        driver.findElement(constructorButton).click();
    }

    @Step("Нажатие на логотип бургерной")
    public void clickLogoBurger() {
        driver.findElement(logoBurger).click();
    }
}