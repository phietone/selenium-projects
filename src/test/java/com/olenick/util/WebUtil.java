package com.olenick.util;

import com.olenick.pageobjects.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 * WebUtil.java - class representing operations on the UI
 *                and utility methods
 * @author  Roman Shulman
 * @version 1.0
 */
public class WebUtil {


    private final static int WAIT_TIMEOUT = 30;

    /**
     * navigate to apps "Login Page"
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @return type LoginPage
     */
    public static LoginPage navigateToLoginPage(WebDriver driver) {

        driver.get("http://gmail.com/");
        return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * perform "click" operation based on locator value
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     * @return true=displayed therefore clicked, false=not clicked  of type boolean
     */
    public static boolean clickByLocator(WebDriver driver, By locator) {

        WebElement element = driver.findElement(locator);
        if (element.isDisplayed()) {

            element.click();
            return true;
        }
        else
            return false;

    }

    /**
     * perform "click" operation based on element object
     * @author  Roman Shulman
     * @param element of type WebElement
     * @return true=displayed therefore clicked, false=not clicked  of type boolean
     */
    public static boolean clickByElement(WebElement element) {

        if (element.isDisplayed()) {

            element.click();
            return true;
        }
        else
            return false;
    }

    /**
     * perform "send keys" operation on a text box based on locator value
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     */
    public static void sendKeysTextBoxByLocator(WebDriver driver, By locator, String value) {

        WebElement element = driver.findElement(locator);
        element.clear();
        element.sendKeys(value);

    }

    /**
     * perform "wait" operation on a control based on locator value
     * until control is visible
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     */
    public static void waitForVisibilityOfElementLocated(WebDriver driver, By locator) {

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

    }

    /**
     * perform "wait" operation on a control based on locator value
     * until number of controls to be more then passed in 'number' param
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     * @param number of type int
     */
    public static void waitForNumberOfElementsToBeMoreThan(WebDriver driver, By locator, int  number) {

        WebDriverWait wait = new WebDriverWait(driver, WAIT_TIMEOUT);
        wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(locator, number));


    }

    /**
     * perform "click" operation on checkbox based on locator value
     * and the state of the checkbox to apply passed in 'expectedCheckBoxState' param
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     * @param expectedCheckBoxState of type boolean
     */
    public static void selectCheckBoxByLocator(WebDriver driver, By locator, boolean expectedCheckBoxState) {

        WebElement element = driver.findElement(locator);
        boolean currentCheckBoxState = element.isSelected();
        if (expectedCheckBoxState != currentCheckBoxState) {
            element.click();
        }
    }

    /**
     * return whether element is displayed or not based on locator value
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     * @return true=displayed, false=not displayed of type boolean
     */
    public static boolean isDisplayed(WebDriver driver, By locator) {
        return driver.findElement(locator).isDisplayed();
    }


    /**
     * get an element from a collection based on locator value
     * and passed 'index' param
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     */
    public static WebElement getElementFromCollectionByIndex(WebDriver driver, By locator, int index) {

        List<WebElement> collection = driver.findElements(locator);
        if (collection.size() > 0) {
            return collection.get(index);
        }
        else
            return null;

    }

    /**
     * get element "text" based on locator value
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param locator of type By
     * @return object text of type String
     */
    public static String getElementTextByLocator(WebDriver driver, By locator) {

        WebElement element = driver.findElement(locator);
        return element.getText();

    }
}
