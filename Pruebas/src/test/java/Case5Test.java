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

public class Case5Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private SignUpPage signUpPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
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
        signUpPage.signUp("testuser123", "password123");

        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();

        homePage.clickSignUpLink();
        signUpPage.signUp("testuser123", "password123");
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());

        assertEquals("This user already exist.", alert2.getText());
        alert2.accept();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}