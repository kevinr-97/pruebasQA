import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Inicializa WebDriverWait
    }

    private By signUpLink = By.id("signin2");
    private By loginLink = By.id("login2");
    private By cartLink = By.id("cartur");
    private By phonesLink = By.xpath("//a[contains(text(),'Phones')]"); // Localizador actualizado
    private By laptopsLink = By.xpath("//a[contains(text(),'Laptops')]"); // Localizador actualizado
    private By monitorsLink = By.xpath("//a[contains(text(),'Monitors')]");

    public void clickSignUpLink() {
        wait.until(ExpectedConditions.elementToBeClickable(signUpLink)); // Espera a que el elemento sea clickeable
        driver.findElement(signUpLink).click();
    }

    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)); // Espera a que el elemento sea clickeable
        driver.findElement(loginLink).click();
    }

    public void clickCartLink() {
        wait.until(ExpectedConditions.elementToBeClickable(cartLink)); // Espera a que el elemento sea clickeable
        driver.findElement(cartLink).click();
    }

    public void selectProduct(By by, String product) {
        WebElement productCategoryLink = wait.until(ExpectedConditions.presenceOfElementLocated(by)); // Espera a que el elemento esté presente
        productCategoryLink.click();
        driver.findElement(By.linkText(product)).click();
    }
}
