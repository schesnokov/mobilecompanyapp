package mobilecompany.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.Assert.assertEquals;

public class AdminPanelTest {

    private WebDriver driver;

    @Before
    public void init(){
        System.setProperty("webdriver.chrome.driver", "D:/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void registrationPositiveTest() {
        driver.get(Url.LOGIN);

        driver.findElement(By.id("email")).sendKeys("chesnokov.sergei@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin");

        driver.findElement(By.className("btn-danger")).click();

        driver.get(Url.ADMIN);

        driver.findElement(By.name("firstName")).sendKeys("Pavel");
        driver.findElement(By.name("secondName")).sendKeys("Durov");
        driver.findElement(By.name("dateOfBirth")).sendKeys("1988-05-03");
        driver.findElement(By.name("passportNumber")).sendKeys("5555544444");
        driver.findElement(By.name("adress")).sendKeys("Ne dom i ne ulica");
        driver.findElement(By.name("email")).sendKeys("durov@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12301230");

        driver.findElement(By.className("register")).click();
        assertEquals(Url.ADMIN, driver.getCurrentUrl());
    }

    @Test
    public void registrationNegativeTest() {
        driver.get(Url.LOGIN);

        driver.findElement(By.id("email")).sendKeys("chesnokov.sergei@gmail.com");
        driver.findElement(By.id("password")).sendKeys("admin");

        driver.findElement(By.className("btn-danger")).click();

        driver.get(Url.ADMIN);

        driver.findElement(By.name("firstName")).sendKeys("Pavel");
        driver.findElement(By.name("secondName")).sendKeys("Durov");
        driver.findElement(By.name("dateOfBirth")).sendKeys("1800-0-03");
        driver.findElement(By.name("passportNumber")).sendKeys("5555544444");
        driver.findElement(By.name("adress")).sendKeys("Ne dom i ne ulica");
        driver.findElement(By.name("email")).sendKeys("durov@gmail.com");
        driver.findElement(By.name("password")).sendKeys("12301230");

        driver.findElement(By.className("register")).click();
        assertEquals(Url.ADMIN, driver.getCurrentUrl());
        Boolean isError = driver.findElements(By.className("error")).size() > 0;
        assertEquals(isError, true);
    }



    @After
    public void destroy() {

        driver.close();
    }
}
