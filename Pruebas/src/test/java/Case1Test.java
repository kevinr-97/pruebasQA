import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
        // Espera a que el botón "Add to cart" esté clickeable
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Add to cart']")));
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