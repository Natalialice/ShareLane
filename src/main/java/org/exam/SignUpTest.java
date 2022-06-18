package org.exam;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignUpTest {

    //1. Открыть браузер
    //2. Зайти на сайт https://www.sharelane.com/cgi-bin/register.py
    //3. ВВести цифры 12345
    //4. нажать на кнопку continue
    //----
    @Test
    public void ZipCodeShouldBeValidMinD() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("00000");
        // или так вместо 20 и 21 строки написать -  driver.findElement(By.name("zip_code")).sendKeys("00000");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void ZipCodeShouldBeValidMoreMinD() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("1234567890");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void ZipCodeShouldNotBeValidWithLetters() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py");
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("qwertr");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton= driver.findElement(By.cssSelector("input[value='Continue']"));
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void SingUpShouldBeValid() {
        
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=1234567890");
        WebElement FirstNameField = driver.findElement(By.name("first_name"));
        FirstNameField.sendKeys("Bodod");
        WebElement LastNameField = driver.findElement(By.name("last_name"));
        LastNameField.sendKeys("Kopbb");
        WebElement EmailField = driver.findElement(By.name("email"));
        EmailField.sendKeys("Kopbb@tyu.ru");
        WebElement PasswordField = driver.findElement(By.name("password1"));
        PasswordField.sendKeys("12345");
        WebElement Password2Field = driver.findElement(By.name("password2"));
        Password2Field.sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");

    }
    @Test
    public void SingUpShouldNotBeValid() {

        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=1234567890");
        WebElement FirstNameField = driver.findElement(By.name("first_name"));
        FirstNameField.sendKeys("Марина");
        WebElement LastNameField = driver.findElement(By.name("last_name"));
        LastNameField.sendKeys("Kopbb");
        WebElement EmailField = driver.findElement(By.name("email"));
        EmailField.sendKeys("Kopbb@tyu.ru");
        WebElement PasswordField = driver.findElement(By.name("password1"));
        PasswordField.sendKeys("12345");
        WebElement Password2Field = driver.findElement(By.name("password2"));
        Password2Field.sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(message.isDisplayed(), "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }
//     @Test
//    public void authorizationBeValid() {
//        String email;
//        String password;
//        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("https://www.sharelane.com/cgi-bin/register.py?page=1&zip_code=1234567890");
//        WebElement FirstNameField = driver.findElement(By.name("first_name"));
//        FirstNameField.sendKeys("Марина");
//        WebElement LastNameField = driver.findElement(By.name("last_name"));
//        LastNameField.sendKeys("Kopbb");
//        WebElement EmailField = driver.findElement(By.name("email"));
//        EmailField.sendKeys("Kopbb@tyu.ru");
//        WebElement PasswordField = driver.findElement(By.name("password1"));
//        PasswordField.sendKeys("12345");
//        WebElement Password2Field = driver.findElement(By.name("password2"));
//        Password2Field.sendKeys("12345");
//        driver.findElement(By.cssSelector("input[value='Register']")).click();
//        WebElement message = driver.findElement(By.cssSelector("span.confirmation_message"));
//        Assert.assertTrue(message.isDisplayed(), "Account is created!");
//
//    }
}

