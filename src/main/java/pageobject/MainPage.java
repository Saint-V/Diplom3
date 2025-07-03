package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {

    private WebDriver driver;

    //кнопка Личный кабинет
    private final By personalAccountButton = By.xpath(".//p[text()='Личный Кабинет']");
    //кнопка Войти в аккаунт
    private final By loginButton = By.xpath(".//button[text()='Войти в аккаунт']");
    //кнопка Оформить заказ
    private final By createOrderButton = By.xpath(".//button[text()='Оформить заказ']");
    //кнопка выбора раздела конструктора 'Булки'
    private final By sectionBunsButton = By.xpath(".//span[text()='Булки']");
    //кнопка выбора раздела конструктора 'Соусы'
    private final By sectionSaucesButton = By.xpath(".//span[text()='Соусы']");
    //кнопка выбора раздела конструктора 'Начинки'
    private final By sectionFillingsButton = By.xpath(".//span[text()='Начинки']");


    //элемент для проверки перехода в Булки
    private final By elementForCheckSwitchToBuns = By.xpath(".//span[text()='Булки']/parent::div");
    //элемент для проверки перехода в Соусы
    private final By elementForCheckSwitchToSauces = By.xpath(".//span[text()='Соусы']/parent::div");
    //элемент для проверки перехода в Начинки
    private final By elementForCheckSwitchToFillings = By.xpath(".//span[text()='Начинки']/parent::div");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Войти в аккаунт")
    public void clickLoginButton() {
        driver.findElement(loginButton).click();
    }

    @Step("Нажатие на кнопку Личный кабинет")
    public void clickPersonalAccountButton() {
        driver.findElement(personalAccountButton).click();
    }

    @Step("Ожидание загрузки главной страницы")
    public void waitLoadMainPage() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(createOrderButton));
    }

    @Step("Нажатие на раздел конструктора \"Булки\"")
    public void clickBunsButton() {
        driver.findElement(sectionBunsButton).click();
    }

    @Step("Нажатие на раздел конструктора \"Соусы\"")
    public void clickSaucesButton() {
        driver.findElement(sectionSaucesButton).click();
    }

    @Step("Нажатие на раздел конструктора \"Начинки\"")
    public void clickFillingsButton() {
        driver.findElement(sectionFillingsButton).click();
    }

    @Step("Получение атрибута class когда выбран раздел \"Булки\"")
    public String getClassBunsSection() {
        return driver.findElement(elementForCheckSwitchToBuns).getAttribute("class");
    }

    @Step("Получение атрибута class когда выбран раздел \"Соусы\"")
    public String getClassSaucesSection() {
        return driver.findElement(elementForCheckSwitchToSauces).getAttribute("class");
    }

    @Step("Получение атрибута class когда выбран раздел \"Начинки\"")
    public String getClassFillingsSection() {
        return driver.findElement(elementForCheckSwitchToFillings).getAttribute("class");
    }
}