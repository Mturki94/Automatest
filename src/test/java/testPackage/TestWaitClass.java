package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotInteractableException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import java.time.Duration;

public class TestWaitClass {

    @Test
    public void controlTest() {

        WebDriver driver = new ChromeDriver();

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        driver.findElement(By.id("reveal")).click();
        driver.findElement(By.id("revealed")).sendKeys("test");

        driver.quit();
    }

    @Test
    public void implicitWait() {

        WebDriver driver = new ChromeDriver();

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        driver.findElement(By.id("reveal")).click();
        driver.findElement(By.id("revealed")).sendKeys("test");

        driver.quit();
    }

    @Test
    public void explicitWait() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        Wait<WebDriver> wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.findElement(By.id("reveal")).click();

        // this doesn't handle ElementNotInteractableException exception
        wait.until(d -> {
            d.findElement(By.id("revealed")).sendKeys("test");
            return true;
        });

        driver.quit();
    }

    @Test
    public void customExplicitWait() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.selenium.dev/selenium/web/dynamic.html");

        Wait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(5))
                .pollingEvery(Duration.ofMillis(300))
                .ignoring(ElementNotInteractableException.class);

        driver.findElement(By.id("reveal")).click();

        wait.until(d -> {
            d.findElement(By.id("revealed")).sendKeys("test");
            return true;
        });

        driver.quit();
    }

}