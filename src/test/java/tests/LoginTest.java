package tests;


import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.Response;
import org.example.User;
import org.example.UserClient;
import org.example.UserGenerator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import pageobject.LoginPage;
import pageobject.MainPage;
import pageobject.RecoverPasswordPage;
import pageobject.RegisterPage;


import static org.junit.Assert.assertEquals;

public class LoginTest extends BaseTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegisterPage objRegisterPage;
    RecoverPasswordPage objRecoverPasswordPage;
    User user;
    protected UserClient client;
    protected String token;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objRecoverPasswordPage = new RecoverPasswordPage(driver);
        client = new UserClient();
        user = UserGenerator.getRandomUser();
        Response response = client.createUser(user);
        token = response.then().extract().path("accessToken");
    }

    @After
    public void cleanUp() {
        if(token != null) {
            client.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Вход по кнопке \"Войти в аккаунт\" на главной странице")
    @Description("Проверка авторизации пользователя")
    public void loginToLoginButtonTest() {
        objMainPage.clickLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку \"Личный кабинет\"")
    @Description("Проверка авторизации пользователя")
    public void loginToPersonalAccountButtonTest() {
        objMainPage.clickPersonalAccountButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка авторизации пользователя")
    public void loginEnterButtonOnRegisterPageTest() {
        objMainPage.clickLoginButton();
        objLoginPage.clickRegisterButton();
        objRegisterPage.clickEnterButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка авторизации пользователя")
    public void loginButtonOnRecoveryPasswordPageTest() {
        objMainPage.clickLoginButton();
        objLoginPage.clickRecoverPasswordButton();
        objRecoverPasswordPage.clickEnterButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.waitLoadMainPage();
        assertEquals(URL,driver.getCurrentUrl());
    }
}