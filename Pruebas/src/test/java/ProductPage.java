import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private WebDriver driver;
    private WebDriverWait wait;

    public ProductPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(20)); // Aumentado a 20 segundos
    }

    // Localizadores CORRECTOS y ROBUSTOS
    private By addToCartButton = By.cssSelector("a.btn.btn-success.btn-lg");
    private By productPageLoadedIndicator = By.cssSelector("h2.name");

    public void clickAddToCartButton() {
        // Espera a que la página del producto esté completamente cargada
        wait.until(ExpectedConditions.visibilityOfElementLocated(productPageLoadedIndicator));
        // Espera a que el botón esté clickeable
        wait.until(ExpectedConditions.elementToBeClickable(addToCartButton));
        driver.findElement(addToCartButton).click();
    }
}