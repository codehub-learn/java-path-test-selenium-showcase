package test_scripts;


import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class Refactor1 extends TestSetUp{

    String baseUrl = "https://www.sgdigital.com/";

    @Test
    public void validAge(){
        validateHomePageAge("1980");
    }



    @Test
    public void inValidAge(){
        validateHomePageAge("2016");
    }

    private void validateHomePageAge(String yearOB) {
        driver.get(baseUrl);
        WebElement dayDropDown = driver.findElement(By.xpath("//form/select[1]"));
        WebElement monthDropDown = driver.findElement(By.xpath("//form/select[2]"));
        WebElement yearDropDown = driver.findElement(By.xpath("//form/select[3]"));
        Select selectObjectDayDropDown = new Select(dayDropDown);
        Select selectObjectMonthDropDown = new Select(monthDropDown);
        Select selectObjectYearDropDown = new Select(yearDropDown);

        selectObjectDayDropDown.selectByValue("13");
        selectObjectMonthDropDown.selectByValue("10");
        selectObjectYearDropDown.selectByValue(yearOB);

        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement menuButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Toggle menu')]"));
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        if(Integer.parseInt(yearOB)<1999) {
            Cookie cookie1 = driver.manage().getCookieNamed("ageVerification");
            assertEquals("verified", cookie1.getValue());
        }else {
            try {
                menuButton.click();
            } catch (ElementClickInterceptedException e) {

            }
        }

    }
}
