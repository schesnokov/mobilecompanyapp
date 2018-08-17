package mobilecompany.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class LoginTest {

    private WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void loginPositiveTest() {
        driver.get(Url.LOGIN);

        driver.findElement(By.id("email")).sendKeys("chesnokov.sergei@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin");

        driver.findElement(By.className("btn-danger")).click();
        assertEquals(Url.INDEX, driver.getCurrentUrl());
    }

    @Test
    public void loginNegativeTest() {
        driver.get(Url.LOGIN);

        driver.findElement(By.id("email")).sendKeys("chesnokov.sergei@gmail.com");
        driver.findElement(By.id("password")).sendKeys("sergio");

        driver.findElement(By.className("btn-danger")).click();

        assertEquals(Url.LOGIN + "?error", driver.getCurrentUrl());
    }


    @After
    public void destroy() {
        driver.close();
    }
}
