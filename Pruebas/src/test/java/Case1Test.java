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

public class Case1Test {

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
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        homePage = new HomePage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
    }

    @Test
    public void testCase1() {
        // 1. Un usuario no registrado ingresa a la tienda virtual y realiza la compra de un teléfono.
        // Localiza el teléfono por su texto de enlace
        homePage.selectProduct(By.xpath("//a[contains(text(),'Phones')]"), "Samsung galaxy s6");

        // Verifica que la URL de la página del producto se haya cargado (CORRECCIONES y MEJORAS)
        System.out.println("URL antes de la espera: " + driver.getCurrentUrl()); // Logging antes de la espera
        wait.until(ExpectedConditions.urlContains("prod.html")); // Verificación flexible
        System.out.println("URL después de la espera: " + driver.getCurrentUrl()); // Logging después de la espera

        // Extraer el ID (si es necesario)
        String currentUrl = driver.getCurrentUrl();
        String productId = null;
        if (currentUrl.contains("prod.html")) {
            // Ejemplo de extracción (adapta según el formato real de la URL)
            int startIndex = currentUrl.indexOf("idp_=") + 5; // Si es idp_
            if (startIndex > 4) {
                int endIndex = currentUrl.length();
                if (currentUrl.contains("&")) {
                    endIndex = currentUrl.indexOf("&");
                }
                productId = currentUrl.substring(startIndex, endIndex);
                System.out.println("ID del producto: " + productId); // Log del ID (opcional)
            }
        }

        productPage.clickAddToCartButton();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        cartPage.navigateToCart();
        assertEquals("Samsung galaxy s6", cartPage.getProductTitle());

        cartPage.clickPlaceOrderButton();
        orderPage.completeOrder("Kevin", "Guatemala", "Coban", "1234567890", "12", "2025");

        String confirmationMessage = orderPage.getConfirmationMessage();
        assertTrue(confirmationMessage.contains("Thank you for your purchase!"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}