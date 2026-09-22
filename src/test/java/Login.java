import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class Login {
    WebDriver driver;

    @BeforeAll
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    }

    @Test
    public void autoLogin() {
        driver.get("https://www.automationexercise.com");

        WebElement signupLoginLink = driver.findElement(By.linkText("Signup / Login"));
        signupLoginLink.click();

        WebElement emailInput = driver.findElement(By.cssSelector("input[data-qa='login-email']"));
        emailInput.sendKeys("Testred@gmail.com");

        WebElement passInput = driver.findElement(By.cssSelector("input[data-qa='login-password']"));
        passInput.sendKeys("reday@026");


        WebElement logBtn = driver.findElement(By.cssSelector("button[data-qa='login-button']"));
        logBtn.click();

        //Assertion Part ---
        WebElement loggedInUser = driver.findElement(By.xpath("//a[contains(text(), 'Logged in as')]"));
        String actualText = loggedInUser.getText();
        Assertions.assertTrue(actualText.contains("Test Red"), "Expected username not found in header!");
    }

   @AfterAll
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}