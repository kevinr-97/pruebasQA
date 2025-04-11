import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By; // Importa la clase By

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
        homePage.selectProduct(By.linkText("Phones"), "Samsung galaxy s6"); // Usa By.linkText
        productPage.clickAddToCartButton();
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();

        homePage.selectProduct(By.linkText("Laptops"), "MacBook Pro"); // Usa By.linkText
        productPage.clickAddToCartButton();
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        alert2.accept();

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
