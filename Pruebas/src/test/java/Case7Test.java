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

import static junit.framework.TestCase.assertTrue;

public class Case7Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;
    //private SignUpPage SignUpPage; // No se usa, se puede eliminar

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        // SignUpPage = new SignUpPage(driver); // No se usa, se puede eliminar
        // pre-requisito
        // homePage.clickSignUpLink(); // Se eliminó el registro
        /*SignUpPage.signUp("testuser123", "password123");
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();*/
    }

    @Test
    public void testCase7() {
        // Caso 7: Un usuario no puede iniciar sesión porque la contraseña es inválida.
        homePage.clickLoginLink();

        // 2. Perform Login
        loginPage.login("testuser123", "wrongpassword"); // Usamos una contraseña incorrecta

        // 3. Wait for Successful Login (o error)
        // Modifiqué esto para esperar un error específico o la ausencia de éxito
        // Esto es importante para una prueba de contraseña inválida
        wait.until(ExpectedConditions.or(
                ExpectedConditions.alertIsPresent(), // Espera una alerta (probablemente de error)
                ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")), // O un elemento de éxito (que NO debería aparecer)
                ExpectedConditions.urlToBe("https://www.demoblaze.com/") // O un cambio de URL (si hay redirección)
        ));

        // 4. Verification
        // La verificación depende de cómo la aplicación maneja el error de contraseña
        // Por ejemplo, puedes verificar la presencia de una alerta de error
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        assertTrue(alert.getText().contains("Wrong password."));
        alert.accept();
        assertTrue(driver.getCurrentUrl().contains("https://www.demoblaze.com/")); // Verifica que la URL no cambie
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}