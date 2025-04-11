import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By productTitle = By.xpath("//td[2]");

    public void clickPlaceOrderButton() {
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        element.click();
    }

    public String getProductTitle() {
        // ¡¡¡VERIFICAR Y CORREGIR EL LOCALIZADOR!!!
        By titleLocator = By.xpath("//td[2]"); // Ejemplo: Usar el mismo XPath (VERIFICAR)
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(titleLocator));
        return productTitleElement.getText();
    }

    public void navigateToCart() {
        driver.get("https://www.demoblaze.com/cart.html");
        // Usar nuevo selector
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#page-wrapper div.row div.col-lg-8")));
    }
}