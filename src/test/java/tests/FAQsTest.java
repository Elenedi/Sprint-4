package tests;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.PageFactory;
import pages.HomePage;


import static org.junit.Assert.assertEquals;
import static pages.HomePage.homePageUrl;

@RunWith(Parameterized.class)
public class FAQsTest {

    private WebDriver driver;
    private HomePage homePage;
    private final String question;
    private final String expectedAnswer;

   public FAQsTest(String question, String expectedAnswer) {
        this.question = question;
        this.expectedAnswer = expectedAnswer;
    }

@Parameterized.Parameters //Вот тут не разобралась как его убрать, вроде добавила в новый класс данные, но если здесь удаляю, то автотесты летят
public static Collection<Object[]> data() {
    return Arrays.asList(new Object[][]{
               {"Сколько это стоит? И как оплатить?", "Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
               {"Хочу сразу несколько самокатов! Так можно?", "Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Как рассчитывается время аренды?", "Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
               {"Можно ли заказать самокат прямо на сегодня?", "Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
               {"Можно ли продлить заказ или вернуть самокат раньше?", "Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
               {"Вы привозите зарядку вместе с самокатом?", "Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
             {"Можно ли отменить заказ?", "Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
              {"Я жизу за МКАДом, привезёте?", "Да, обязательно. Всем самокатов! И Москве, и Московской области."}
              });
              }

              @Before
             public void setUp() {
             ChromeOptions options = new ChromeOptions();
              options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
              driver = new ChromeDriver(options);
              driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
               driver.get(homePageUrl);
             homePage = PageFactory.initElements(driver, HomePage.class);
             }



    @Test
    public void checkQuestion() {
        homePage.scrollHomePage(); // Скролим страницу, чтобы вопросы были видны
        String actualAnswer = homePage.getAnswerForQuestion(question);
        assertEquals(expectedAnswer, actualAnswer);
    }

   @After
    public void tearDown() {
        if (driver != null) {
           driver.quit();
        }
    }
}