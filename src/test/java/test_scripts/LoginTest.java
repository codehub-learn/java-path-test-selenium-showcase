package test_scripts;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import static org.junit.Assert.assertEquals;
public class LoginTest extends TestSetUp{

    @Test
    public void playgroundLogin(){
        driver.get("http://playgroundsel.vectordesign.gr/");
        driver.findElement(By.id("user_login")).sendKeys("testDummy");
        driver.findElement(By.id("user_pass")).sendKeys("test@111");
        driver.findElement(By.id("wp-submit")).click();
        String welcome_message = driver.findElement(By.className("user_first_name")).getText();
        assertEquals("Hello there test Logout", welcome_message);
    }
}