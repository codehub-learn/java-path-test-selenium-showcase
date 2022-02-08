package code_samples;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import test_scripts.TestSetUp;

public class IframeTests extends TestSetUp {

    @Test
    public void iframeExample(){
        driver.get("http://playgroundsel.vectordesign.gr/iframes/");
        WebElement iframe = driver.findElement(By.tagName("iframe"));
        driver.switchTo().frame(iframe);
        driver.findElement(By.id("wt-cli-accept-all-btn")).click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        driver.switchTo().defaultContent();
    }
}
