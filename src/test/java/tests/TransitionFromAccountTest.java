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
import pageobject.PersonalAccount;

import static org.example.Urls.STELLAR_BURGERS_HOME_PAGE_URL;
import static org.example.UserApiGenerator.randomUser;
import static org.openqa.selenium.devtools.v85.network.Network.clearBrowserCookies;

@RunWith(Parameterized.class)
public class TransitionFromAccountTest {

    UserApi user = randomUser();
    UserApiMethod userApiMethod = new UserApiMethod();

    @Rule
    public BrowserRule rule;

    public TransitionFromAccountTest(BrowserRule rule) {
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

        Login login = new Login(rule.getWebDriver());
        login
                .openLoginPage()
                .enterEmail(user.getEmail())
                .enterPassword(user.getPassword())
                .clickOnButtonLoginInFormAuth()
                .checkHomePageAfterAuth();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на «Конструктор»")
    public void transitionFromAccountByConstructorButton(){
        PersonalAccount personalAccount = new PersonalAccount(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnConstructorButton();
        homePage
                .checkConstructorHeaderText();
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор - по клику на логотип Stellar Burgers")
    public void transitionFromAccountByLogo(){
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp()
                .clickOnLogoStellarBurgers()
                .checkConstructorHeaderText();
    }

    @Test
    @DisplayName("Выход из личного кабинета")
    public void transitionFromAccountByExitButton()  {
        PersonalAccount personalAccount = new PersonalAccount(rule.getWebDriver());
        Login login = new Login(rule.getWebDriver());
        HomePage homePage = new HomePage(rule.getWebDriver());

        homePage
                .clickOnPersonalAccountButtonHp();
        personalAccount
                .clickOnExitButton();
        login
                .isDisplayedEnterText();
    }

    @After
    public void tearDown(){
        userApiMethod.delete(user);
        clearBrowserCookies();
    }
}