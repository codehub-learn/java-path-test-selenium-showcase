package test_scripts;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Properties;

import static junit.framework.TestCase.fail;

public class TestScreenshots extends TestSetUp {

    @Test
    public void screenshot()throws Exception{
        driver.get("https://sport.toto.nl/");
        driver.findElement(By.id("accept-cookie-consent")).click();
        Thread.sleep(3000);
        Properties pros = System.getProperties();
        String path = pros.getProperty("user.dir");
        // Java Script Executor
        JavascriptExecutor js = (JavascriptExecutor)driver;
        // Remove dynamic content
        List<WebElement> priceTags = driver.findElements(By.className("event-list__item-link"));
        String gamesTemplate = "<div>Test Text For UI tests </div>";
        for(WebElement priceElement:priceTags){
            js.executeScript("arguments[0].innerHTML='"+gamesTemplate+"'",priceElement);
        }
        WebElement carousel = driver.findElement(By.className("carousel-root"));
        js.executeScript("arguments[0].setAttribute('style', 'display:none')",carousel);
        BufferedImage bi = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver).getImage();
        File outputfile = new File(path + "/reporting/image1.jpg");
        ImageIO.write(bi, "jpg", outputfile);

        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(path + "/reporting/imageAccepted.jpg");
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(path + "/reporting/image1.jpg");

        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();

        if (!ImageComparisonState.MATCH.equals(imageComparisonResult.getImageComparisonState())) {

            BufferedImage bufferResultImage = imageComparisonResult.getResult();
            File outputResultfile = new File(path + "/reporting/result.jpg");
            ImageIO.write(bi, "jpg", outputfile);
            ImageComparisonUtil.saveImage(outputResultfile, bufferResultImage);

            fail("MISMATCH");
        }

    }

}
