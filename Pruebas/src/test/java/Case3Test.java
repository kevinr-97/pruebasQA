import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Case3Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private ProductPage productPage;
    private CartPage cartPage;
    private OrderPage orderPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testCase3() {
        // Caso 3: Un usuario no registrado ingresa a la tienda virtual y realiza la compra de un Teléfono y una laptop.

        // 1. Compra del Teléfono
        homePage.selectProduct(By.linkText("Phones"), "Samsung galaxy s6");
        productPage.clickAddToCartButton();
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());  // Alerta "Product added"
        alert1.accept();

        homePage.navigateToCart(); // Navega al carrito para verificar
        WebDriverWait waitCart1 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera para el primer carrito
        waitCart1.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#page-wrapper div.row div.col-lg-8")));
        assertEquals("Samsung galaxy s6", cartPage.getProductTitle());

        // 2. Continúa con la Compra del Teléfono
        cartPage.clickPlaceOrderButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("orderModal"))); // Espera modal
        orderPage.completeOrder("Kevin", "Guatemala", "Coban", "1234567890", "12", "2025");
        Alert alert3 = wait.until(ExpectedConditions.alertIsPresent());  // Alerta después de finalizar la compra
        alert3.accept();

        String confirmationMessage1 = orderPage.getConfirmationMessage(); //Mensaje primera compra
        assertTrue(confirmationMessage1.contains("Thank you for your purchase!"));

        driver.get("https://www.demoblaze.com/"); // Regresa a la página principal para comprar la laptop
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nava"))); // Espera a que la página cargue

        // 3. Compra de la Laptop
        homePage.selectProduct(By.linkText("Laptops "), "MacBook Pro");
        productPage.clickAddToCartButton();
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());  // Alerta "Product added"
        alert2.accept();

        homePage.navigateToCart(); // Navega al carrito para verificar
        WebDriverWait waitCart2 = new WebDriverWait(driver, Duration.ofSeconds(10)); // Espera para el segundo carrito
        waitCart2.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div#page-wrapper div.row div.col-lg-8")));
        assertEquals("MacBook Pro", cartPage.getProductTitle());

        // 4. Continúa con la Compra de la Laptop
        cartPage.clickPlaceOrderButton();
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("orderModal"))); // Espera modal
        orderPage.completeOrder("Kevin", "Guatemala", "Coban", "1234567890", "12", "2025");
        Alert alert4 = wait.until(ExpectedConditions.alertIsPresent());  // Alerta después de finalizar la compra
        alert4.accept();

        String confirmationMessage2 = orderPage.getConfirmationMessage(); //Mensaje segunda compra
        assertTrue(confirmationMessage2.contains("Thank you for your purchase!"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}