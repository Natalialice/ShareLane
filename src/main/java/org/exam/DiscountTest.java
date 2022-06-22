package org.exam;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import static org.testng.Assert.assertTrue;

public class DiscountTest {
    WebDriver driver;
    String email;
    String password;


    @BeforeClass
    public void SetPathToWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
    }
    public void Login(){
        driver.findElement(By.name("zip_code")).sendKeys("00000");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        driver.findElement(By.cssSelector("input[value='Register']"));
        driver.findElement(By.name("First_name")).sendKeys("Woddy");
        driver.findElement(By.name("Last_name")).sendKeys("Makar");
        driver.findElement(By.name("email")).sendKeys("Makar@vu.com");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String Email = driver.findElement(By.xpath("//td[text()='Email']/following::b")).getText();
        String Password = driver.findElement(By.xpath("//td[text()='Password']/following::td")).getText();
        driver.findElement(By.cssSelector("img[src='../images/logo.jpg']")).click();
        driver.findElement(By.name("email")).sendKeys(Email);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.cssSelector("input[value='Login']")).click();
    }
    public void discountOneBook(){
        Login();
        driver.get("https:");
  }


    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
