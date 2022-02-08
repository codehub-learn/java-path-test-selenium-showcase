package test_scripts;

import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertEquals;

public class AgeValidationTests extends TestSetUp{

    @Test
    public void verifyCookieValidation(){
        driver.get("https://www.sgdigital.com/");
        WebElement dayDropDown = driver.findElement(By.xpath("//form/select[1]"));
        WebElement monthDropDown = driver.findElement(By.xpath("//form/select[2]"));
        WebElement yearDropDown = driver.findElement(By.xpath("//form/select[3]"));
        Select selectObjectDayDropDown = new Select(dayDropDown);
        Select selectObjectMonthDropDown = new Select(monthDropDown);
        Select selectObjectYearDropDown = new Select(yearDropDown);
        selectObjectDayDropDown.selectByValue("13");
        selectObjectMonthDropDown.selectByValue("10");
        selectObjectYearDropDown.selectByValue("1980");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement menuButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Toggle menu')]"));
        wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        Cookie cookie1 = driver.manage().getCookieNamed("ageVerification");
        assertEquals("verified", cookie1.getValue());

    }

    @Test
    public void verifyCookieValidationWickedPath(){
        driver.get("https://www.sgdigital.com/");
        WebElement dayDropDown = driver.findElement(By.xpath("//form/select[1]"));
        WebElement monthDropDown = driver.findElement(By.xpath("//form/select[2]"));
        WebElement yearDropDown = driver.findElement(By.xpath("//form/select[3]"));
        Select selectObjectDayDropDown = new Select(dayDropDown);
        Select selectObjectMonthDropDown = new Select(monthDropDown);
        Select selectObjectYearDropDown = new Select(yearDropDown);

        selectObjectDayDropDown.selectByValue("13");
        selectObjectMonthDropDown.selectByValue("10");
        selectObjectYearDropDown.selectByValue("2016");

        WebDriverWait wait = new WebDriverWait(driver, 30);
        WebElement menuButton = driver.findElement(By.xpath("//button[contains(@aria-label, 'Toggle menu')]"));

        try {
            menuButton.click();
        } catch (ElementClickInterceptedException e) {

        }
    }



}
