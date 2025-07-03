package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
public class LoginPage {

    private final WebDriver driver;

    //кнопка Войти
    private final By enterButton = By.xpath(".//button[text()='Войти']");
    //гиперссылка Зарегистрироваться
    private final By registrationButton = By.xpath(".//a[text()='Зарегистрироваться']");
    //поле Email
    private final By emailField = By.xpath(".//input[@name='name']");
    //поле Пароль
    private final By passwordField = By.xpath(".//input[@name='Пароль']");
    //гиперссылка Восстановить пароль
    private final By recoverPasswordButton = By.xpath(".//a[text()='Восстановить пароль']");


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на гиперссылку Зарегистрироваться")
    public void clickRegisterButton() {
        driver.findElement(registrationButton).click();
    }

    @Step("Ожидание загрузки страницы авторизации")
    public void waitOfVisibilityEnterButton() {
        new WebDriverWait(driver, Duration.ofSeconds(3))
                .until(ExpectedConditions.visibilityOfElementLocated(enterButton));
    }

    @Step("Проверка видимости кнопки Войти")
    public boolean enterButtonIsDisplayed() {
        return driver.findElement(enterButton).isDisplayed();
    }

    @Step("Нажатие на кнопку Войти")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }

    @Step("Заполнение поля Email")
    public void setEmailField(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Заполнение поля Пароль")
    public void setPasswordField(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Заполнение формы Вход")
    public void loginUser(String email, String password) {
        setEmailField(email);
        setPasswordField(password);
        clickEnterButton();
    }

    @Step("Нажатие на кнопку \"Восстановить пароль\"")
    public void clickRecoverPasswordButton() {
        driver.findElement(recoverPasswordButton).click();
    }

}