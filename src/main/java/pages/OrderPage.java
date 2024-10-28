package pages;
import org.openqa.selenium.*;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.HomePage;

/*Задание: Заказ самоката. Нужно проверить весь флоу позитивного сценария с двумя наборами данных.
Проверить точки входа в сценарий, их две: кнопка «Заказать» вверху страницы и внизу.
Из чего состоит позитивный сценарий:
- Нажать кнопку «Заказать». На странице две кнопки заказа.
- Заполнить форму заказа.
- Проверить, что появилось всплывающее окно с сообщением об успешном создании заказа.*/

public class OrderPage {
    private final WebDriver driver;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }

        public void waitForElementToBeVisible (By locator){
           new WebDriverWait(driver, Duration.ofSeconds(20)).until(ExpectedConditions.visibilityOfElementLocated(locator));
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Ждать до 10 секунд
            //wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }



    // Локаторы для баннера куки
    private static By cookieBannerButton = By.className("App_CookieButton__3cvqF");

    // Локаторы для первой страницы
    private static By orderButtonHeader = By.xpath("//button[@class='Button_Button__ra12g'][text()='Заказать']");
            //div[1]/div[2]/button[1]");
            ////*[@id='root']/div/div[1]/div[1]/div[2]/button[1]");
    private static By orderButtonDown = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Заказать']");
                    //div/button[@class='Button_Button__ra12g Button_Middle__1CSJM']");
                    //div[2]/div[5]/button");
                    ////*[@id='root']/div/div/div[4]/div[2]/div[5]/button");
    private static By nameButton = By.xpath("//*[@placeholder='* Имя']");
                            //div[2]/div[1]/input");
                            //*[@id='root']/div/div[2]/div[2]/div[1]/input");
    private static By surnameButton = By.xpath("//*[@placeholder='* Фамилия']");
                                    //div[2]/div[2]/input");
                                    //*[@id='root']/div/div[2]/div[2]/div[2]/input");
    private static By addressButton = By.xpath("//*[@placeholder='* Адрес: куда привезти заказ']");
                                            //div[2]/div[3]/input");
                                            //*[@id='root']/div/div[2]/div[2]/div[3]/input");
    private static By metroButton = By.xpath("//*[@class='select-search__input']");
                                                    //div[4]/div/div/input");
                                                    //*[@id='root']/div/div[2]/div[2]/div[4]/div/div/input");
    private static By phoneNumberButton = By.xpath("//*[@placeholder='* Телефон: на него позвонит курьер']");
                                                            //div[2]/div[5]/input");
                                                            //*[@id='root']/div/div[2]/div[2]/div[5]/input");
    public static By nextButton = By.xpath("//button[text()='Далее']");
                                                                    //div/div[2]/div[3]/button");
                                                                    //*[@id='root']/div/div[2]/div[3]/button");

    // Локаторы для второй страницы
    private static By dateButton = By.xpath("//*[@placeholder='* Когда привезти самокат']");
            //div[1]/div/div/input");
            //*[@id='root']/div/div[2]/div[2]/div[1]/div/div/input");
    private static By rentalDaysButton = By.xpath("//*[@class='Dropdown-placeholder']");
                    //*[@id='root']/div/div[2]/div[2]/div[2]/div/div");
    private static By blackButton = By.xpath("//*[@id='black']");
                            //div[2]/div[3]/label[1]/input");
                            //*[@id='root']/div/div[2]/div[2]/div[3]/label[1]/input");
    private static By greyButton = By.xpath("//*[@id='grey']");
    private static By commentButton = By.xpath("//*[@placeholder='Комментарий для курьера']");
            //div[2]/div[2]/div[4]/input");
            //*[@id='root']/div/div[2]/div[2]/div[4]/input");
    private static By submitButton = By.xpath("//div[@class='Order_Buttons__1xGrp']/button[text()='Заказать']");
                    //div[2]/div[3]/button[2]");
                    //*[@id='root']/div/div[2]/div[3]/button[2]");
    private static By popupButton = By.xpath("//*[@class='Order_Modal__YZ-d3']");
                            //div[2]/div[5]/div[1]");
                            //*[@id='root']/div/div[2]/div[5]/div[1]");
    private static By yesButton = By.xpath("//button[@class='Button_Button__ra12g Button_Middle__1CSJM'][text()='Да']");
                                    //div[2]/div[5]/div[2]/button[2]");
                                    //*[@id='root']/div/div[2]/div[5]/div[2]/button[2]");



    // Метод для закрытия баннера куки
    public void closeCookieBanner() {
        waitForElementToBeVisible(cookieBannerButton);
        driver.findElement(cookieBannerButton).click();
    }

    //Скролл до элемента кнопки "Заказать"
    public void scrollOrderPage() {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
                driver.findElement(orderButtonDown));
        //waitForElementToBeVisible(orderButtonDown);
    }

    // Нажатие на нижнюю кнопку "Заказать"
    public void clickOrderButtonDown() {
        waitForElementToBeVisible(orderButtonDown);
        driver.findElement(orderButtonDown).click();
    }

    // Нажатие на верхнюю кнопку "Заказать"
    public void clickOrderButtonHeader() {
        waitForElementToBeVisible(orderButtonHeader);
        driver.findElement(orderButtonHeader).click();
    }

    // Заполнение первой страницы заказа
    public void enterDataFirstOrderPage(String name, String surname, String address, int metro, String phoneNumber) {
        driver.findElement(nameButton).sendKeys(name);  // Заполнение поля "Имя"
        driver.findElement(surnameButton).sendKeys(surname);  // Заполнение поля "Фамилия"
        driver.findElement(addressButton).sendKeys(address);  // Заполнение поля "Адрес"
        driver.findElement(metroButton).click(); // Нажатие на поле "Станция метро"

        // Выбор станции метро из списка
        By allMetroStation = By.xpath("//*[@class='Order_SelectOption__82bhS select-search__option']");
        //div/div[2]/ul/li");
                ////*[@id='root']/div/div[2]/div[2]/div[4]/div/div[2]/ul/li");
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