package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {

    private WebDriver driver;

    //поле Имя
    private final By inputName = By.xpath(".//label[text()='Имя']//following-sibling::input");
    //поле Email
    private final By inputEmail = By.xpath(".//label[text()='Email']//following-sibling::input");
    //поле Пароль
    private final By inputPassword = By.xpath(".//input[@name='Пароль']");
    //кнопка зарегистрироваться
    private final By registerButton = By.xpath(".//button[text()='Зарегистрироваться']");
    //сообщение об ошибке в поле Пароль
    private final By errorMessagePassword = By.xpath(".//p[text()='Некорректный пароль']");
    //кнопка Войти внизу страницы
    private final By enterButton = By.xpath(".//a[text()='Войти']");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Заполнить форму регистрации")
    public void registerUser(String name, String email, String password) {
        setName(name);
        setEmail(email);
        setPassword(password);
        clickRegisterButton();
    }

    @Step("Заполнить поле Имя")
    public void setName(String name) {
        driver.findElement(inputName).sendKeys(name);
    }

    @Step("Заполнить поле Email")
    public void setEmail(String email) {
        driver.findElement(inputEmail).sendKeys(email);
    }

    @Step("Заполнить поле Пароль")
    public void setPassword(String password) {
        driver.findElement(inputPassword).sendKeys(password);
    }

    @Step("Клик по кнопке Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }

    @Step("Получение текста сообщения об ошибке в поле Пароль")
    public String getErrorMessagePasswordField(){
        return driver.findElement(errorMessagePassword).getText();
    }

    @Step("Клик по кнопке Войти на странице регистрации")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

}