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

import static org.junit.Assert.assertTrue;

public class Case6Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private SignUpPage signUpPage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        //pre-requisito
        homePage.clickSignUpLink();
        signUpPage.signUp("testuser123", "password123");
        Alert alert1 = wait.until(ExpectedConditions.alertIsPresent());
        alert1.accept();
    }

    @Test
    public void testCase6() {
        // Caso 6: Un usuario inicia sesión exitosamente
        homePage.clickLoginLink();
        loginPage.login("testuser123", "password123");
        assertTrue(driver.getCurrentUrl().contains("https://www.demoblaze.com/"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}