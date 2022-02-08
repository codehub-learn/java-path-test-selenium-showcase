package code_samples;

import org.junit.Test;
import org.openqa.selenium.By;
import test_scripts.TestSetUp;

import static org.junit.Assert.assertEquals;

public class PlayGroundTests  extends TestSetUp {

    @Test
    public void playgroundLogin(){
        driver.get("http://playgroundsel.vectordesign.gr/");
        driver.findElement(By.id("user_login")).sendKeys("testDummy");
        driver.findElement(By.id("user_pass")).sendKeys("test@111");
        driver.findElement(By.id("wp-submit")).click();
        String userGreeting = driver.findElement(By.className("user_first_name")).getText();
        assertEquals("Hello there test Logout",userGreeting);

    }

    @Test
    public void fillform(){
        driver.get("http://playgroundsel.vectordesign.gr/selenium-playground/");
        driver.findElement(By.id("id-playground-field")).sendKeys("Random Text dfasdljkfghas;oidfgjhas");
        driver.findElement(By.name("name-playground-field")).sendKeys("Name Selector");
        driver.findElement(By.className("class-playground-field")).sendKeys("Class Selector");
        driver.findElement(By.cssSelector(".class-playground-field")).sendKeys("Css");
        driver.findElement(By.xpath("//div[4]/input[contains(@class, 'playground-field')]")).sendKeys("Xpath");

    }




}
