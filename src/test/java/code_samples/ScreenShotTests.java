package code_samples;

import com.github.romankh3.image.comparison.ImageComparison;
import com.github.romankh3.image.comparison.ImageComparisonUtil;
import com.github.romankh3.image.comparison.model.ImageComparisonResult;
import com.github.romankh3.image.comparison.model.ImageComparisonState;
import junit.extensions.TestSetup;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import test_scripts.TestSetUp;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;
import static junit.framework.TestCase.fail;


public class ScreenShotTests extends TestSetUp {

    @Test
    public void screenShots() throws Exception {
        driver.get("http://www.google.gr");
        WebDriverWait wait = new WebDriverWait(driver, 30);
        // close confirmation modal
        WebElement confirmButton = driver.findElement(By.id("L2AGLb"));
        confirmButton.click();
        wait.until(ExpectedConditions.invisibilityOf(confirmButton));
        // type some search
        WebElement searchField = driver.findElement(By.name("q"));
          searchField.sendKeys("Test test to trigger fail");
        Properties pros = System.getProperties();
        String path = pros.getProperty("user.dir");
        BufferedImage bi = new AShot()
                .shootingStrategy(ShootingStrategies.viewportPasting(100))
                .takeScreenshot(driver).getImage();
        File outputfile = new File(path + "/reporting/imageAccepted.jpg");
        ImageIO.write(bi, "jpg", outputfile);
// Print the absolute path to see where the file is created
        System.out.println(outputfile.getAbsolutePath());

        //load images to be compared:
        BufferedImage expectedImage = ImageComparisonUtil.readImageFromResources(path + "/reporting/imageAccepted.jpg");
        BufferedImage actualImage = ImageComparisonUtil.readImageFromResources(path + "/reporting/image3.jpg");

        //Create ImageComparison object and compare the images.

        ImageComparisonResult imageComparisonResult = new ImageComparison(expectedImage, actualImage).compareImages();
     //    assertEquals(ImageComparisonState.MATCH, imageComparisonResult.getImageComparisonState());
        if (!ImageComparisonState.MATCH.equals(imageComparisonResult.getImageComparisonState())) {
            BufferedImage bufferResultImage = imageComparisonResult.getResult();
            File outputResultfile = new File(path + "/reporting/result.jpg");
            ImageIO.write(bi, "jpg", outputfile);
            ImageComparisonUtil.saveImage(outputResultfile, bufferResultImage);

            fail("MISMATCH");
        }
        //Check the result

    }
}
