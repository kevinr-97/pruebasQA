import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class Case5Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        ChromeOptions options = new ChromeOptions();

        // 1. Deshabilitar el gestor de contraseñas de Chrome a través de los switches de la línea de comandos
        options.addArguments("password-store=basic");
        options.addArguments("profile.password_manager_enabled=false");

        // 2. intentar modo incógnito
        //options.addArguments("--incognito");

        // 3. intentar evitar autocompletado del navegador
        //options.addArguments("disable-infobars");
        //options.addArguments("disable-save-password-bubble");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void testCase5() {
        // Caso 5: Un usuario no registrado no puede crear una cuenta porque ya existe el nombre de usuario.
        homePage.clickSignUpLink();
        signUpPage.signUp("testuser123", "password123$%&");

        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();

        homePage.closeSignUpModal(); // Close the modal

        homePage.clickSignUpLink();

        // The core issue is likely in SignUpPage's signUp method, not here.
        // I'm not changing anything here as it's the test logic, not the bug.
        signUpPage.signUp("testuser123", "password123#$");
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("This user already exist.", alert2.getText());
        alert2.accept();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}