package tests;

import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.HomePage;
import pageobject.Login;
import pageobject.Registration;

import static org.example.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static org.example.UserApiGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class LoginTest {

    private UserApi user = randomUser();
    UserApiMethod userApiMethod = new UserApiMethod();

    @Rule
    public BrowserRule rule;

    public LoginTest(BrowserRule rule) {
        this.rule = rule;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                { new YandexRule() },
                { new ChromeRule() }
        };
    }

    @Before
    public void setUp(){
        RestAssured.baseURI = STELLAR_BURGERS_HOME_PAGE_URL;
        userApiMethod.create(user);
    }

    @Test
    @DisplayName("Вход в аккаунт по кнопке «Войти в аккаунт» на главной")
    public void inputByButtonToEnterAccountHp(){
        Login login = new Login(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .openHomePage()
                .clickOnButtonToEnterAccountHp();
        login
                .waitingForLoading()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку «Личный кабинет» на главной")
    public void inputByPersonalAccountButtonHp(){
        Login login = new Login(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .openHomePage()
                .clickOnPersonalAccountButtonHp();
        login
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме регистрации")
    public void inputByLoginButtonInFormRegistration(){
        Registration registration = new Registration(rule.getWebDriver());
        Login login = new Login(rule.getWebDriver());

        registration
                .openRegistrationPage();
        login
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Вход в аккаунт через кнопку в форме восстановления пароля")
    public void inputByLoginButtonInFormRestorePassword(){
        Login login = new Login(rule.getWebDriver());

        login
                .openPasswordRestorePage()
                .clickOnLoginButtonInForms()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @After
    public void tearDown(){
        userApiMethod.delete(user);
        clearBrowserCookies();
    }
}