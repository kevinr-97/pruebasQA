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
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera un máximo de 10 segundos
        WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(By.linkText("Samsung galaxy s6")));
        element.click();

    }

    private By placeOrderButton = By.xpath("//button[text()='Place Order']");
    private By productTitle = By.xpath("//td[2]");

    public void clickPlaceOrderButton() {
        wait.until(ExpectedConditions.elementToBeClickable(placeOrderButton));
        driver.findElement(placeOrderButton).click();
    }

    public String getProductTitle() {
        WebElement productTitleElement = wait.until(ExpectedConditions.visibilityOfElementLocated(productTitle));
        return productTitleElement.getText();
    }

    public void navigateToCart() {
        driver.get("https://www.demoblaze.com/cart.html");
    }
}
