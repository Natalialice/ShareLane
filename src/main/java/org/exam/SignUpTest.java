package org.exam;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest {
    WebDriver driver;

    @BeforeClass
    public void SetPathToWebDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    //@BeforeMethod
    //public void setUp(String url) {
    //  WebDriver driver = new ChromeDriver();
    // driver.get("https://www.sharelane.com/cgi-bin/register.py");
    // }


    //1. Открыть браузер
    //2. Зайти на сайт https://www.sharelane.com/cgi-bin/register.py
    //3. ВВести цифры 12345
    //4. нажать на кнопку continue
    //----
    @Test
    public void ZipCodeShouldBeValidMinD() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        // setUp("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("00000");
        // или так вместо 20 и 21 строки написать -  driver.findElement(By.name("zip_code")).sendKeys("00000");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void ZipCodeShouldBeValidMoreMinD() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("1234567890");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void ZipCodeShouldNotBeValidWithLetters() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        driver.findElement(By.name("zip_code")).sendKeys("qwertr");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void SingUpShouldBeValid() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=1234567890");
        driver.findElement(By.name("first_name")).sendKeys("Bodod");
        driver.findElement(By.name("last_name")).sendKeys("Kopbb");
        driver.findElement(By.name("email")).sendKeys("Kopbb@tyu.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");

    }

    @Test
    public void SingUpShouldNotBeValid() {
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=1234567890");
        driver.findElement(By.name("first_name")).sendKeys("Марина");
        driver.findElement(By.name("last_name")).sendKeys("Kopbb");
        driver.findElement(By.name("email")).sendKeys("Kopbb@tyu.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(message.isDisplayed(), "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();

        }
    }
}
