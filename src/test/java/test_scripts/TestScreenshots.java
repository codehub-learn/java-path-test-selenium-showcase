package test_scripts;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

import static junit.framework.TestCase.fail;

public class TestScreenshots extends TestSetUp {

    @Test
    public void screenshot()throws Exception{
        driver.get("https://twitter.com/");
        driver.findElement(By.xpath("//*[@id=\"layers\"]/div/div/div/div/div/div[2]/div")).click();
        Thread.sleep(3000);
        Properties pros = System.getProperties();
        String path = pros.getProperty("user.dir");
        BufferedImage bi = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver).getImage();
        File outputfile = new File(path + "/reporting23/image1.jpg");
        ImageIO.write(bi, "jpg", outputfile);

        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(path + "/reporting23/imageAccepted.jpg");
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(path + "/reporting23/image1.jpg");

        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();

        if (!ImageComparisonState.MATCH.equals(imageComparisonResult.getImageComparisonState())) {
            BufferedImage bufferResultImage = imageComparisonResult.getResult();
            File outputResultfile = new File(path + "/reporting23/result.jpg");
            ImageIO.write(bi, "jpg", outputfile);
            ImageComparisonUtil.saveImage(outputResultfile, bufferResultImage);

            fail("MISMATCH");
        }

    }

}
