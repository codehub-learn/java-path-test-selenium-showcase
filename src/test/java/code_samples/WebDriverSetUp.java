package code_samples;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test_scripts.TestSetUp;

public class WebDriverSetUp extends TestSetUp {

    @Test
    public void responsivetest(){
        driver.get("http://playgroundsel.vectordesign.gr/");
        driver.manage().window().setSize(new Dimension(200, 350));
        WebElement elementlocation = driver.findElement(By.id("user_login"));
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", elementlocation);
        driver.findElement(By.id("user_login")).sendKeys("TestUser");
    }
}
