package pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoverPasswordPage {

    private WebDriver driver;

    private final By enterButton = By.xpath(".//a[text()='Войти']");

    public RecoverPasswordPage(WebDriver driver) {
        this.driver = driver;
    }

    @Step("Нажатие на кнопку Войти на странице Восстановления пароля")
    public void clickEnterButton() {
        driver.findElement(enterButton).click();
    }
}
