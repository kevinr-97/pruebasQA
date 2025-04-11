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

public class Case7Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;
    private SignUpPage SignUpPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
        SignUpPage = new SignUpPage(driver);
        //pre-requisito
        homePage.clickSignUpLink();
        SignUpPage.signUp("testuser123", "password123");
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();
    }

    @Test
    public void testCase7() {
        // Caso 7: Un usuario no puede iniciar sesión porque la contraseña es inválida.
        homePage.clickLoginLink();
        loginPage.login("testuser123", "wrongpassword");
        Alert alert2 = wait.until(ExpectedConditions.alertIsPresent());
        assertEquals("Wrong password.", alert2.getText());
        alert2.accept();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}