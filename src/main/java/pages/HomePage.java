package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.HashMap;

public class HomePage {
    public static void main(String[] args) {
    }

    private final WebDriver driver;
    public static String homePageUrl = "https://qa-scooter.praktikum-services.ru/";

    // Локаторы вопросов с 1-8 из раздела "Вопросы о важном":
    //Вопрос 1:
    private static By howMuch = By.id("accordion__heading-0");
    //Вопрос 2:
    private static By wantSeveral = By.id("accordion__heading-1");
    //Вопрос 3:
    private static By rentTime = By.id("accordion__heading-2");
    //Вопрос 4:
    private static By orderingToday = By.id("accordion__heading-3");
    //Вопрос 5:
    private static By changingRentTime = By.id("accordion__heading-4");
    //Вопрос 6:
    private static By scooterCharger = By.id("accordion__heading-5");
    //Вопрос 7:
    private static By cancellingOrder = By.id("accordion__heading-6");
    //Вопрос 8:
    private static By livingFar = By.id("accordion__heading-7");

    // Локаторы ответов с 1 - 8 на вопросы из раздела "Вопросы о важном":
    //Ответ 1:
    private static By answerHowMuch = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[1]/div[2]/p");
    //Ответ 2:
    private static By answerWantSeveral = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[2]/div[2]/p");
    //Ответ 3:
    private static By answerRentTime = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[3]/div[2]/p");
    //Ответ 4:
    private static By answerOrderingToday = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[4]/div[2]/p");
    //Ответ 5:
    private static By answerChangingRentTime = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[5]/div[2]/p");
    //ОТвет 6:
    private static By answerScooterCharger = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[6]/div[2]/p");
    //Ответ 7:
    private static By answerCancellingOrder = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[7]/div[2]/p");
    //Ответ 8:
    private static By answerLivingFar = By.xpath("/html/body/div/div/div/div[5]/div[2]/div/div[8]/div[2]/p");

    // Ключ: значение (вопрос: ответ):
    static HashMap<By, By> map = new HashMap<>();
    static {
        //Сколько это стоит? И как оплатить? == Сутки — 400 рублей. Оплата курьеру — наличными или картой.
        map.put(howMuch, answerHowMuch);
        //Хочу сразу несколько самокатов! Так можно? == Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим.
        map.put(wantSeveral, answerWantSeveral);
        //Как рассчитывается время аренды? == Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30.
        map.put(rentTime, answerRentTime);
        //Можно ли заказать самокат прямо на сегодня? == Только начиная с завтрашнего дня. Но скоро станем расторопнее.
        map.put(orderingToday, answerOrderingToday);
        //Можно ли продлить заказ или вернуть самокат раньше? == Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010.
        map.put(changingRentTime, answerChangingRentTime);
        //Вы привозите зарядку вместе с самокатом? == Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится.
        map.put(scooterCharger, answerScooterCharger);
        //Можно ли отменить заказ? == Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои.
        map.put(cancellingOrder, answerCancellingOrder);
        //Я живу за МКАДом, привезёте? == Да, обязательно. Всем самокатов! И Москве, и Московской области.
        map.put(livingFar, answerLivingFar);
    }

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Скролим главную страницу до первого вопроса:
    public void scrollHomePage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(howMuch));
    }

    // Открыть элемент списка вопросов, ждем появления текста с ответом:
    public String getAnswer(By questionLocator) {
        driver.findElement(questionLocator).click();
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(driver ->
                driver.findElement(map.get(questionLocator)).getText() != null
                        && !driver.findElement(map.get(questionLocator)).getText().isEmpty());
        return driver.findElement(map.get(questionLocator)).getText();
    }

    // Новый метод для получения ответа на вопрос по тексту:
    public String getAnswerForQuestion(String question) {
        // Логика получения ответа на вопрос
        // Предполагаем, что вопрос будет использоваться для получения локатора
        for (By questionLocator : map.keySet()) {
            if (driver.findElement(questionLocator).getText().contains(question)) {
                return getAnswer(questionLocator);
            }
        }
        return "Ответ не найден";
    }
}