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


import static org.junit.Assert.assertEquals;

public class ConstructorTest extends BaseTest {

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
    @DisplayName("Нажатие в личном кабинете на конструктор")
    @Description("Проверка перехода в конструктор")
    public void goPersonalAccountToConstructorTest() {
        objPersonalAccountPage.clickConstructorButton();
        assertEquals(URL,driver.getCurrentUrl());
    }

    @Test
    @DisplayName("Нажатие в личном кабинете на логотип бургерной")
    @Description("Проверка перехода на главную страницу")
    public void clickLogoBurgerTest() {
        objPersonalAccountPage.clickLogoBurger();
        assertEquals(URL,driver.getCurrentUrl());
    }
}