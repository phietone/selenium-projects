package com.olenick.pageobjects;

import com.olenick.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

/**
 * LoginPage.java - class representing Login Page
 * @author  Roman Shulman
 * @version 1.0
 * @see WebUtil
 */
public class LoginPage {

    /**
     * enter "User Name" into text box
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param emailAddress of type String
     * @see WebUtil
     */
    public void enterUserName(WebDriver driver, String emailAddress) {
        WebUtil.sendKeysTextBoxByLocator(driver, By.xpath(".//*[@id='Email']"), emailAddress);
    }

    /**
     * enter "Password" into text box
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param password of type String
     * @see WebUtil
     */
    public void enterPassword(WebDriver driver, String password) {

        WebUtil.waitForVisibilityOfElementLocated(driver, By.xpath(".//*[@id='Passwd']"));

        WebUtil.sendKeysTextBoxByLocator(driver, By.xpath(".//*[@id='Passwd']"), password);
    }

    /**
     * click 'Next' button
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public void clickNext(WebDriver driver) {

        WebUtil.clickByLocator(driver, By.xpath(".//*[@id='next']"));
    }

    /**
     * select or unselect "Stay Signed In" checkbox
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param checkBoxState of type boolean
     * @see WebUtil
     */
    public void selectStaySignedIn(WebDriver driver, boolean checkBoxState) {

        WebUtil.selectCheckBoxByLocator(driver, By.id("PersistentCookie"), checkBoxState);

    }

    /**
         * click "Sign In" button
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public HomePage clickSignIn(WebDriver driver) {

        WebUtil.clickByLocator(driver, By.xpath(".//*[@id='signIn']"));

        //wait for page to load
        WebUtil.waitForVisibilityOfElementLocated(driver,By.partialLinkText("Inbox"));

        //return home page object
        return PageFactory.initElements(driver, HomePage.class);

    }

    /**
     * verify "Login Page" is displayed
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @return boolean true=displayed, false=not displayed
     * @see WebUtil
     */
    public boolean isPageDisplayed(WebDriver driver) {
        return WebUtil.isDisplayed(driver, By.xpath(".//*[@id='Email']"));
    }


}
