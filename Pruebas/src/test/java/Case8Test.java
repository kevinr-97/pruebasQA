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

public class Case8Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;
    private ProductPage productPage;
    private CartPage cartPage;
    private OrderPage orderPage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        productPage = new ProductPage(driver);
        cartPage = new CartPage(driver);
        orderPage = new OrderPage(driver);
        signUpPage = new SignUpPage(driver);
        //pre-requisito
        homePage.clickSignUpLink();
        signUpPage.signUp("testuser123", "password123");
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();
        homePage.clickLoginLink();
        loginPage.login("testuser123", "password123");
    }

    @Test
    public void testCase8() {
        // Caso 8: Un usuario que inicia sesión exitosamente y compra un monitor
        homePage.selectProduct(By.linkText("Monitors"), "Apple monitor 24"); // Usa By.linkText
        productPage.clickAddToCartButton();

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        cartPage.navigateToCart();
        assertEquals("Apple monitor 24", cartPage.getProductTitle());

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