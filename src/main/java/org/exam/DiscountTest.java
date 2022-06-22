package org.exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class DiscountTest {
    WebDriver driver;


    @BeforeClass
    public void SetPathToWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

//  @BeforeMethod
//    public void setUp() {
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.sharelane.com/cgi-bin/register.py");
//    }
    @Test
    public void Login(){
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("00000");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.cssSelector("input[value='Register']"));
        driver.findElement(By.name("first_name")).sendKeys("Woddy");
        driver.findElement(By.name("last_name")).sendKeys("Makar");
        driver.findElement(By.name("email")).sendKeys("Makar@vu.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String email = driver.findElement(By.xpath("//td[text()='Email']/following::b")).getText();
        String password = driver.findElement(By.xpath("//td[text()='Password']/following::td")).getText();
        driver.findElement(By.cssSelector("img[src='../images/logo.jpg']")).click();
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.cssSelector("input[value='Login']")).click();
        driver.get("https://www.sharelane.com/cgi-bin/show_book.py?book_id=6");
        driver.findElement(By.xpath("//img[@src ='../images/add_to_cart.gif']")).click();
        driver.findElement(By.xpath("//a[@href='./shopping_cart.py']")).click();
//        driver.findElement(By.name("q")).clear();
        WebElement discount = driver.findElement(By.xpath("//b[normalize-space()='0']"));
        //Assert.assertEquals(driver.findElement(By.xpath("//tr[5]//td[5]//p//b")).getText(),discount);
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
