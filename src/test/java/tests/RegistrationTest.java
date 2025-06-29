package tests;

import com.github.javafaker.Faker;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.example.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import pageobject.Registration;

import static org.example.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static org.example.UserApiGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class RegistrationTest {

    Faker faker = new Faker();
    UserApiMethod userApiMethod = new UserApiMethod();
    UserApi user = randomUser();

    @Rule
    public BrowserRule rule;

    public RegistrationTest(BrowserRule rule) {
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
    @DisplayName("Заполнение формы и регистрация с валидными данными")
    public void fillingOutTheRegistrationForm(){
        Registration registration = new Registration(rule.getWebDriver());

        registration
                .openRegistrationPage()
                .enterName(user.getName())
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .tapOnBattonRegistration()

                .checkRegistrationSuccess();
    }

    @Test
    @DisplayName("Заполнение формы регистрации с некорректным паролем: пароль 5 символов")
    public void fillingRegistrationFormWithIncorrectPassword(){
        Registration registration = new Registration(rule.getWebDriver());

        registration
                .openRegistrationPage()
                .enterName(user.getName())
                .enterEmail(user.getPassword())
                .enterPassword(faker.bothify("29???"))
                .tapOnBattonRegistration()

                .checkIncorrectPassword();
    }

    @After
    public void tearDown(){

        Response response = userApiMethod.login(user);
        if(response.statusCode() == 200) {userApiMethod.delete(user);}

        clearBrowserCookies();
    }
}