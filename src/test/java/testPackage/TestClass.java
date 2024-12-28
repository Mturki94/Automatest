package testPackage;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

// todo:: add before and after methods
// todo:: add root path of files and directory


public class TestClass {

    @Test
    public void assert_page_title_1() {

        WebDriver driver = new ChromeDriver();
//        ChromeDriver driver;

        driver.get("https://duckduckgo.com/");
        driver.navigate().to("https://duckduckgo.com");
        String actualTitle = driver.getTitle();
        System.out.println(actualTitle);
        Assert.assertEquals(actualTitle, "Google");

        driver.quit();
    }

    @Test
    public void assert_page_logo_2() {

        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");

        WebElement appLogo = driver.findElement(By.xpath("(//a[@title=\"Learn about DuckDuckGo\"]//img)[2]"));
        Assert.assertTrue(appLogo.isDisplayed());

        driver.quit();
    }

    @Test
    public void chrome_search_3() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");

        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("Selenium WebDriver");
        searchBox.sendKeys(Keys.RETURN);

        WebElement resultLink = driver.findElement(By.xpath("(//*[@data-testid=\"result-extras-url-link\"])[1]"));
        String hrefValue = resultLink.getDomProperty("href");
        Assert.assertEquals(hrefValue, "https://www.selenium.dev/documentation/webdriver/");

        driver.quit();
    }

    @Test
    public void firefox_search_4() {
        WebDriver driver = new FirefoxDriver();
        driver.get("https://duckduckgo.com/");

        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("TestNG");
        searchBox.sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement resulTitle = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//a[@data-testid='result-title-a'])[4]")));

        Assert.assertEquals(resulTitle.getDomProperty("text"), "TestNG Tutorial - GeeksforGeeks");

        driver.quit();
    }


    @Test
    public void assert_link_5() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://duckduckgo.com/");

        WebElement searchBox = driver.findElement(By.id("searchbox_input"));
        searchBox.sendKeys("Cucumber IO");
        searchBox.sendKeys(Keys.RETURN);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement resultLink = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//*[@data-testid=\"result-extras-url-link\"])[2]")));
        String hrefValue = resultLink.getDomProperty("href");

        resultLink.click();

        Assert.assertTrue(hrefValue.contains("https://www.linkedin.com"));

        driver.quit();
    }


    @Test
    public void assert_checkbox_6() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/checkboxes");

        WebElement checkBoxOne = driver.findElement(By.xpath("//form[@id='checkboxes']/input[1]"));
        checkBoxOne.click();

        WebElement checkBoxTwo = driver.findElement(By.xpath("//form[@id='checkboxes']/input[2]"));

        Assert.assertTrue(checkBoxOne.isSelected());
        Assert.assertTrue(checkBoxTwo.isSelected());

        driver.quit();
    }


    @Test
    public void assert_table_content_7() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.w3schools.com/html/html_tables.asp");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement tableContent = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Ernst Handel']/..//*[text()='Austria']")));

        System.out.println(tableContent.getDomProperty("text"));
        tableContent.isDisplayed();

        driver.quit();
    }

    @Test
    public void upload_image_8() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://the-internet.herokuapp.com/upload");


        WebElement SelectImage = driver.findElement(By.xpath("//*[@id='file-upload']"));
//        SelectImage.sendKeys("/Users/areeb/Downloads/180x180.png");
        SelectImage.sendKeys("/Users/areeb/Downloads/180x180.png");

//        System.getProperty("user.dir");


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement ImageUpload = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='file-submit']")));
        ImageUpload.click();

        WebElement SuccessMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='File Uploaded!']")));
        SuccessMessage.isDisplayed();

        driver.quit();
    }

    @Test
    public void drag_and_drop_9() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://jqueryui.com/resources/demos/droppable/default.html");


        WebElement element = driver.findElement(By.id("draggable"));
        WebElement target = driver.findElement(By.id("droppable"));

        (new Actions(driver)).dragAndDrop(element, target).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement SuccessMessage = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[text()='Dropped!']")));
        SuccessMessage.isDisplayed();

        driver.quit();
    }

//    @AfterTest
//    public void terminateBrowser() {
//        driver.close();
//    }


}
