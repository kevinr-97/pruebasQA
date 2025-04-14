import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    // Localizadores (Usando IDs donde sea posible, generalmente mejor)
    private By signUpLink = By.id("signin2");
    private By loginLink = By.id("login2");   // ID is excellent and unique!
    private By cartLink = By.id("cartur");
    private By phonesLink = By.xpath("//a[contains(text(),'Phones')]"); // Text is okay, but use CSS if possible
    private By laptopsLink = By.xpath("//a[contains(text(),'Laptops')]");
    private By monitorsLink = By.xpath("//a[contains(text(),'Monitors')]");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickSignUpLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(signUpLink));
        element.click();
    }

    public void clickLoginLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(loginLink));
        element.click();
    }

    public void clickCartLink() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(cartLink));
        element.click();
    }

    public void selectProduct(By by, String product) {
        WebElement productCategoryLink = wait.until(ExpectedConditions.presenceOfElementLocated(by));
        productCategoryLink.click();
        clickProductLink(product);
    }

    private void clickProductLink(String product) {
        By productLocator = By.linkText(product);
        wait.until(ExpectedConditions.elementToBeClickable(productLocator));
        driver.findElement(productLocator).click();
    }

    public void navigateToCart() {
    }

    public void closeSignUpModal() {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='signInModal']//button[@class='close']")))
                    .click();
        } catch (org.openqa.selenium.TimeoutException e) {
            //Modal not present or already closed, that is ok.
        }
    }
}