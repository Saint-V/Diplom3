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
import pageobject.RegisterPage;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegistrationTest extends BaseTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegisterPage objRegisterPage;
    User user;
    protected UserClient client;
    protected String token;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        client = new UserClient();
        objMainPage.clickLoginButton();
        objLoginPage.clickRegisterButton();
    }

    @After
    public void cleanUp() {
        if(token != null) {
            client.deleteUser(token);
        }
    }

    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка регистрации пользователя")
    public void successRegisterTest() {
        user = UserGenerator.getRandomUser();
        objRegisterPage.registerUser(user.getName(), user.getEmail(), user.getPassword());
        objLoginPage.waitOfVisibilityEnterButton();
        Response response = client.loginUser(user);
        token = response.then().extract().path("accessToken");
        assertTrue(objLoginPage.enterButtonIsDisplayed());
    }

    @Test
    @DisplayName("Проверка ошибки для некорректного пароля")
    @Description("Проверка регистрации пользователя")
    public void registerWithIncorrectPasswordTest() {
        user = new User("user-test@ya.ru", "pass", "Ivan");
        objRegisterPage.registerUser(user.getName(), user.getEmail(), user.getPassword());
        String actualErrorMessage = objRegisterPage.getErrorMessagePasswordField();
        assertEquals("Неверное сообщение об ошибке", actualErrorMessage, "Некорректный пароль");
    }
}