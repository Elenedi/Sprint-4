package tests;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pages.OrderPage;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import pages.HomePage;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import tests.TestsSetUp;

import static org.junit.Assert.assertTrue;
import static pages.HomePage.homePageUrl;



@RunWith(Parameterized.class)
public class OrderPageTest {


    private WebDriver driver;
   private OrderPage orderPage;

    // Параметры теста
    @Parameterized.Parameter(0)
    public String name;
    @Parameterized.Parameter(1)
    public String surname;
    @Parameterized.Parameter(2)
    public String address;
    @Parameterized.Parameter(3)
    public int metro;
    @Parameterized.Parameter(4)
    public String phoneNumber;
    @Parameterized.Parameter(5)
    public String color;
    @Parameterized.Parameter(6)
    public String date;
    @Parameterized.Parameter(7)
    public int rentalDays;
    @Parameterized.Parameter(8)
    public String comment;

    public OrderPageTest(/*WebDriver driver*/) {
       // super(driver);
    }


    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get(homePageUrl);
        orderPage = new OrderPage(driver);


        // Закрытие баннера с cookie, если он есть
        orderPage.closeCookieBanner();
    }

    //Заказ по верхней кнопке:
    @Test
    public void orderPageThroughHeaderButtonTest() {
        orderPage.clickOrderButtonHeader();
     orderPage.waitForElementToBeVisible(By.xpath("//body/div/div/div[2]"));
             //*[@class='Order_Content__bmtHS']"));
     //body/div/div/div[2]"));
        orderPage.enterDataFirstOrderPage(name, surname, address, metro, phoneNumber);
        orderPage.selectNextButton();/*nextButton*/
        orderPage.enterDataSecondOrderPage(color, date, rentalDays, comment);
        assertTrue(orderPage.successfullyText());
    }
    /*private void waitForElementToBeVisible(By locator) {
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10)); // Ждать до 10 секунд
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }*/

    //для заказа по нижней кнопке:
    @Test
    public void orderPageThroughDownButtonTest() {
        orderPage.scrollOrderPage(); // Скролим страницу
        orderPage.clickOrderButtonDown(); // Нажатие на нижнюю кнопку "Заказать"
        orderPage.enterDataFirstOrderPage(name, surname, address, metro, phoneNumber);
        orderPage.enterDataSecondOrderPage(color, date, rentalDays, comment);
        assertTrue(orderPage.successfullyText());
    }


    @After
    public void tearDown() {
       if (driver != null) {
           driver.quit();
       }
    }

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {
        return Arrays.asList(new Object[][]{
                {"Галина", "Петровна", "Москва", 3, "+79667653344", "черный", "16.09.2024", 7, "Позвоните за 30 минут"},
                {"Роман", "Максимов", "Москва", 15, "+79541112233", "серый", "18.10.2024", 5, ""},
                {"Петр", "Первый", "Санкт-Петербург", 10, "+75126778894", "черный", "27.09.2024", 3, "Оставить около двери"}
        });
    }
}
