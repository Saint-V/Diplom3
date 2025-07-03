package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import java.time.Duration;

public class BaseTest {
    protected WebDriver driver;
    String browser = "chrome";
    public static final String URL = "https://stellarburgers.nomoreparties.site/";

    @Before
    public void prepare() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        // Для Yandex
        if (browser.equalsIgnoreCase("yandex")) {
            options.setBinary("C:\\Users\\Professional\\AppData\\Local\\Yandex\\YandexBrowser\\Application\\browser.exe");
        }

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(URL);
    }

    @After
    public void tearDown() {
        if (driver != null) {
            try {
                driver.quit();
            } catch (Exception e) {
                System.err.println("Error while closing WebDriver: " + e.getMessage());
            }
        }
    }
}