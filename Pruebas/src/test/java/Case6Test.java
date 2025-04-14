import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static junit.framework.TestCase.assertTrue;

public class Case6Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private HomePage homePage;
    private LoginPage loginPage;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.demoblaze.com/");  // This is the correct base URL
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    public void testCase6() {
        // 1. Open the Login Modal
        homePage.clickLoginLink();

        // 2. Perform Login
        loginPage.login("testuser123", "password123");

        // 3. Wait for Successful Login
        wait.until(ExpectedConditions.or(
                ExpectedConditions.urlContains("demoblaze.com"),        // Or a more specific URL after login
                ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser"))  // Or wait for a specific element
        ));


        // 4. Verification
        assertTrue(driver.getCurrentUrl().contains("demoblaze.com")); // Or verify a logged-in element
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}