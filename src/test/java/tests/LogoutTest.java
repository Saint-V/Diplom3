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
import pageobject.PersonalAccountPage;
import pageobject.RegisterPage;


import static org.junit.Assert.assertTrue;

public class LogoutTest extends BaseTest {

    MainPage objMainPage;
    LoginPage objLoginPage;
    RegisterPage objRegisterPage;
    PersonalAccountPage objPersonalAccountPage;
    User user;
    protected UserClient client;
    protected String token;

    @Before
    public void setUp() {
        objMainPage = new MainPage(driver);
        objLoginPage = new LoginPage(driver);
        objRegisterPage = new RegisterPage(driver);
        objPersonalAccountPage = new PersonalAccountPage(driver);
        client = new UserClient();
        user = UserGenerator.getRandomUser();
        Response response = client.createUser(user);
        token = response.then().extract().path("accessToken");
        objMainPage.clickLoginButton();
        objLoginPage.loginUser(user.getEmail(), user.getPassword());
        objMainPage.clickPersonalAccountButton();
    }

    @After
    public void cleanUp() {
        if(token != null) {
            client.deleteUser(token);
        }
    }

    @Test
    @DisplayName("LogOut нажатием кнопки Выход в Личном кабинете")
    @Description("Проверка выхода пользователя")
    public void logoutUserTest(){
        objPersonalAccountPage.waitOfVisibilityExitButton();
        objPersonalAccountPage.clickExitButton();
        objLoginPage.waitOfVisibilityEnterButton();
        assertTrue(objLoginPage.enterButtonIsDisplayed());
    }
}
