import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OrderPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public OrderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By nameField = By.id("name");
    private By countryField = By.id("country");
    private By cityField = By.id("city");
    private By creditCardField = By.id("card");
    private By monthField = By.id("month");
    private By yearField = By.id("year");
    private By purchaseButton = By.xpath("//button[text()='Purchase']");
    private By confirmationMessage = By.xpath("//h2[text()='Thank you for your purchase!']");

    public void enterOrderDetails(String name, String country, String city, String creditCard, String month, String year) {
        WebElement nameElement = wait.until(ExpectedConditions.elementToBeClickable(nameField));
        nameElement.sendKeys(name);
        WebElement countryElement = wait.until(ExpectedConditions.elementToBeClickable(countryField));
        countryElement.sendKeys(country);
        WebElement cityElement = wait.until(ExpectedConditions.elementToBeClickable(cityField));
        cityElement.sendKeys(city);
        WebElement creditCardElement = wait.until(ExpectedConditions.elementToBeClickable(creditCardField));
        creditCardElement.sendKeys(creditCard);
        WebElement monthElement = wait.until(ExpectedConditions.elementToBeClickable(monthField));
        monthElement.sendKeys(month);
        WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(yearField));
        yearElement.sendKeys(year);
    }

    public void clickPurchaseButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(purchaseButton));
        element.click();
    }

    public String getConfirmationMessage() {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMessage));
        return element.getText();
    }

    public void completeOrder(String name, String country, String city, String creditCard, String month, String year) {
        enterOrderDetails(name, country, city, creditCard, month, year);
        clickPurchaseButton();
    }
}