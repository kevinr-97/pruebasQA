import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class Case4Test {

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
    public void testCase4() {
        homePage.clickSignUpLink();
        signUpPage.signUp("testuser123", "password123");

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        alert.accept();

        // Check if the home page is loaded, by waiting for a home page element.
        wait.until(ExpectedConditions.visibilityOfElementLocated(org.openqa.selenium.By.id("nava")));
        assertTrue(driver.getCurrentUrl().contains("https://www.demoblaze.com/"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}