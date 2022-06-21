package org.exam;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SignUpTest {
    WebDriver driver;
    String email;

    @BeforeClass
    public void SetPathVariable() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
    }

    @BeforeMethod
    public void setUp(String url) {
        WebDriver driver = new ChromeDriver();
        driver.get(url);
    }

    String password;
    //1. Открыть браузер
    //2. Зайти на сайт https://www.sharelane.com/cgi-bin/register.py
    //3. ВВести цифры 12345
    //4. нажать на кнопку continue
    //----

    @Test
    public void ZipCodeShouldBeValidMinD() {
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("00000");
        // или так вместо 20 и 21 строки написать -  driver.findElement(By.name("zip_code")).sendKeys("00000");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void ZipCodeShouldBeValidMoreMinD() {
        driver.findElement(By.name("zip_code")).sendKeys("1234567890");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Register']"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");

    }

    @Test
    public void ZipCodeShouldNotBeValidWithLetters() {
        WebElement zipCodeField = driver.findElement(By.name("zip_code"));
        zipCodeField.sendKeys("qwertr");
        driver.findElement(By.cssSelector("input[value='Continue']")).click();
        WebElement registerButton = driver.findElement(By.cssSelector("input[value='Continue']"));
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(registerButton.isDisplayed(), "Oops, error on page. ZIP code should have 5 digits");
    }

    @Test
    public void SingUpShouldBeValid() {

        driver.findElement(By.name("First_name")).sendKeys("Bodod");
        driver.findElement(By.name("Last_name")).sendKeys("Kopbb");
        driver.findElement(By.name("email")).sendKeys("Kopbb@tyu.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");

    }

    @Test
    public void SingUpShouldNotBeValid() {

        driver.findElement(By.name("first_name")).sendKeys("Марина");
        driver.findElement(By.name("last_name")).sendKeys("Kopbb");
        driver.findElement(By.name("email")).sendKeys("Kopbb@tyu.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.error_message"));
        Assert.assertTrue(message.isDisplayed(), "Oops, error on page. Some of your fields have invalid data or email was previously used");

    }

    @Test
    public void authorizationBeValid() {

        driver.findElement(By.name("first_name")).sendKeys("Green");
        driver.findElement(By.name("last_name")).sendKeys("Maxim");
        driver.findElement(By.name("email")).sendKeys("maxim@tyu.ru");
        driver.findElement(By.name("password1")).sendKeys("12345");
        driver.findElement(By.name("password2")).sendKeys("12345");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        WebElement message = driver.findElement(By.cssSelector("span.confirmation_message"));
        Assert.assertTrue(message.isDisplayed(), "Account is created!");
        String Email = driver.findElement(By.xpath("//td[text()='Email']/following::b")).getText();
        String Password = driver.findElement(By.xpath("//td[text()='Password']/following::td")).getText();
        driver.findElement(By.cssSelector("img[src='../images/logo.jpg']")).click();
        driver.findElement(By.name("email")).sendKeys(Email);
        driver.findElement(By.name("password")).sendKeys(Password);
        driver.findElement(By.cssSelector("input[value='Login']")).click();
    }

    @Test
    public void PaymentOneBook() {
        setUp("https://www.sharelane.com/cgi-bin/main.py");
        authorizationBeValid();

    }
}


        
    
