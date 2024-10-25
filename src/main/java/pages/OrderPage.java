package pages;
import org.openqa.selenium.*;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;

/*Задание: Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
- Нажать кнопку «Заказать». На странице две кнопки заказа.
- Заполнить форму заказа.
- Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.*/

public class OrderPage {
    public OrderPage(WebDriver driver) {
     this.driver = driver;
   }
    private final WebDriver driver;

    // Локаторы для баннера куки
    private static By cookieBannerButton = By.className("App_CookieButton__3cvqF");

    // Локаторы для первой страницы
    private static By orderButtonHeader = By.xpath("//*[@id='root']/div/div[1]/div[1]/div[2]/button[1]");
    private static By orderButtonDown = By.xpath("//*[@id='root']/div/div/div[4]/div[2]/div[5]/button");
    private static By nameButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private static By surnameButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private static By addressButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private static By metroButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    private static By phoneNumberButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[5]/input");
    public static By nextButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button");

    // Локаторы для второй страницы
    private static By dateButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private static By rentalDaysButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[2]/div/div");
    private static By blackButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[3]/label[1]/input");
    private static By greyButton = By.xpath("//*[@id='grey']");
    private static By commentButton = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/input");
    private static By submitButton = By.xpath("//*[@id='root']/div/div[2]/div[3]/button[2]");
    private static By popupButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[1]");
    private static By yesButton = By.xpath("//*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");



    // Метод для закрытия баннера куки
    public void closeCookieBanner() {
        driver.findElement(cookieBannerButton).click();
    }

    //Скролл до элемента кнопки "Заказать"
    public void scrollOrderPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(orderButtonDown));
    }

    // Нажатие на нижнюю кнопку "Заказать"
    public void clickOrderButtonDown() {
        driver.findElement(orderButtonDown).click();
    }

    // Нажатие на верхнюю кнопку "Заказать"
    public void clickOrderButtonHeader() {
        driver.findElement(orderButtonHeader).click();
    }

    // Заполнение первой страницы заказа
    public void enterDataFirstOrderPage(String name, String surname, String address, int metro, String phoneNumber) {
        driver.findElement(nameButton).sendKeys(name);  // Заполнение поля "Имя"
        driver.findElement(surnameButton).sendKeys(surname);  // Заполнение поля "Фамилия"
        driver.findElement(addressButton).sendKeys(address);  // Заполнение поля "Адрес"
        driver.findElement(metroButton).click(); // Нажатие на поле "Станция метро"

        // Выбор станции метро из списка
        By allMetroStation = By.xpath("//*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li");
        List<WebElement> elements = driver.findElements(allMetroStation);
        elements.get(metro - 1).click();  // Выбор станции метро по индексу

        driver.findElement(phoneNumberButton).sendKeys(phoneNumber); }  // Заполнение поля "Телефон"
        public void selectNextButton() {
            driver.findElement(nextButton).click(); // Нажатие на кнопку "Далее"
    }

    // Заполнение второй страницы заказа
    public void enterDataSecondOrderPage(String color, String date, int rentalDays, String comment) {
        selectColor(color);
        selectDate(date);
        selectRentalDays(rentalDays);
        enterComment(comment);
        selectSubmitButton(submitButton);
    }
    private void selectColor(String color) {
        if ("черный".equals(color)) {
            driver.findElement(blackButton).click();
        } else if ("серый".equals(color)) {
            driver.findElement(greyButton).click();
        } else {
            throw new IllegalArgumentException("Неизвестный цвет: " + color);
        }
        }
        private void selectDate(String date) {
            driver.findElement(dateButton).sendKeys(date);
            driver.findElement(dateButton).sendKeys(Keys.RETURN);
        }

        private void selectRentalDays(int rentalDays) {
        driver.findElement(rentalDaysButton).click();
        By allDayForOrder = By.className("Dropdown-option");
        List<WebElement> elements = driver.findElements(allDayForOrder);
        if (rentalDays < 1 || rentalDays > elements.size()) {
            throw new IllegalArgumentException("Некорректное количество дней аренды: " + rentalDays); }
        elements.get(rentalDays - 1).click(); } // Выбор срока аренды

private void enterComment(String comment) {
        driver.findElement(commentButton).sendKeys(comment); } //Заполнение поля "Комментарий"
    private void selectSubmitButton(By submitButton) {
        driver.findElement(OrderPage.submitButton).click(); // Нажатие на кнопку "Заказать"
        driver.findElement(yesButton).click(); // Подтверждение заказа в попапе
        }


    // Попап подтверждения оформления заказа
    public boolean successfullyText() {
        String successfullOrder = driver.findElement(popupButton).getText();
        return successfullOrder.contains("Заказ оформлен");
    }
}