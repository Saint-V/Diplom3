package tests;

import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.Test;
import pageobject.MainPage;

import static org.junit.Assert.assertTrue;

public class SwitchingBetweenSectionsConstructorTest extends BaseTest {

    MainPage objMainPage;

    @Test
    @DisplayName("Переход к разделу Булки из раздела Соусы")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsSaucesToBunsTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickSaucesButton();
        objMainPage.clickBunsButton();
        String className = objMainPage.getClassBunsSection();
        assertTrue(className.contains("type_current"));
    }

    @Test
    @DisplayName("Переход к разделу Булки из раздела Начинки")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsFillingsToBunsTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickFillingsButton();
        objMainPage.clickBunsButton();
        String className = objMainPage.getClassBunsSection();
        assertTrue(className.contains("type_current"));
    }

    @Test
    @DisplayName("Переход к разделу Соусы из раздела Булки")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsBunsToSaucesTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickSaucesButton();
        String className = objMainPage.getClassSaucesSection();
        assertTrue(className.contains("type_current"));
    }

    @Test
    @DisplayName("Переход к разделу Соусы из раздела Начинки")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsFillingsToSaucesTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickFillingsButton();
        objMainPage.clickSaucesButton();
        String className = objMainPage.getClassSaucesSection();
        assertTrue(className.contains("type_current"));
    }

    @Test
    @DisplayName("Переход к разделу Начинки из раздела Булки")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsBunsToFillingsTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickFillingsButton();
        String className = objMainPage.getClassFillingsSection();
        assertTrue(className.contains("type_current"));
    }

    @Test
    @DisplayName("Переход к разделу Начинки из раздела Соусы")
    @Description("Тест перехода между разделами в конструкторе бургера")
    public void switchingSectionsSaucesToFillingsTest() {
        objMainPage = new MainPage(driver);
        objMainPage.clickSaucesButton();
        objMainPage.clickFillingsButton();
        String className = objMainPage.getClassFillingsSection();
        assertTrue(className.contains("type_current"));
    }
}