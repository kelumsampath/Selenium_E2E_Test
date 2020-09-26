package net.phptravels;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PHPTravelsLoginTest {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {

        System.setProperty("webdriver.chrome.driver","chromedriver.exe");
        driver=new ChromeDriver();
        driver.get("https://www.phptravels.net/admin");
        driver.manage().window().maximize();
    }

    @Test
    public void testLogin() throws InterruptedException {

        loginAs("admin@phptravels.com","demoadmin");
        Thread.sleep(3000);

        String message=driver.findElement(By.xpath("//*[contains(text(),'Hi Admin!')]")).getText();
        Assert.assertEquals(message,"Hi Admin!","Unable to Login to the system. ");
        driver.findElement(By.xpath("//*[contains(text(),'Logout')]")).click();

    }

    @Test
    public void testLoginUnSuccess() throws InterruptedException {
        loginAs("admin123@phptravels.com","demoadmin");
        Thread.sleep(3000);
        String message=driver.findElement(By.xpath("//*[contains(text(),'Invalid Login Credentials')]")).getText();
        Assert.assertEquals(message,"Invalid Login Credentials");
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    private void loginAs(String username, String password){
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.xpath("//*[@type='password']")).sendKeys(password);
        driver.findElement(By.xpath("//*[@type='submit']")).click();
    }

}
