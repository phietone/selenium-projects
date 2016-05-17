package com.olenick.pageobjects;

import com.olenick.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * HomePage.java - class representing Home Page
 * @author  Roman Shulman
 * @version 1.0
 * @see WebUtil
 */
public class HomePage {

    private WebDriverWait wait;

    /**
     * check if the Primary Tab is active
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @return boolean true=activate, false=not active
     * @see WebUtil
     */
    public boolean isPrimaryTabActive(WebDriver driver) {

        //wait for primary tab
        WebUtil.waitForVisibilityOfElementLocated(driver, By.xpath("//*[@aria-selected='true']//*[contains(.,'Primary')]"));

        //return found status
        return WebUtil.isDisplayed(driver, By.xpath("//*[@aria-selected='true']//*[contains(.,'Primary')]"));

    }

    /**
     * click 'Compose' button
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public void clickCompose(WebDriver driver) {

        WebUtil.clickByLocator(driver, By.xpath("//*[normalize-space(text())='COMPOSE']"));

    }

    /**
     * Enter recipients email address into New Email
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param emailAddress of type String
     * @see WebUtil
     */
    public void enterRecipientAddress(WebDriver driver, String emailAddress) {

        //wait for "New Message" email entry to display
        WebUtil.waitForVisibilityOfElementLocated(driver, By.xpath("//*[normalize-space(text())='New Message']"));

        //Enter recipients address
        WebUtil.sendKeysTextBoxByLocator(driver, By.cssSelector("textarea[name='to']"), emailAddress);
    }

    /**
     * Enter Subject text into New Email
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param emailSubject of type String
     * @see WebUtil
     */
    public void enterSubjectLine(WebDriver driver, String emailSubject) {

        //Enter email subject
        WebUtil.sendKeysTextBoxByLocator(driver, By.cssSelector("input[name='subjectbox']"), emailSubject);
    }

    /**
     * Enter Email body text into New Email
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param emailBody of type String
     * @see WebUtil
     */
    public void enterBodyText(WebDriver driver, String emailBody) {

        //Enter email body
        WebUtil.sendKeysTextBoxByLocator(driver, By.cssSelector("div[aria-label='Message Body']"), emailBody);
    }

    /**
     * click email 'Send' button
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public void clickSend(WebDriver driver) {

        WebUtil.clickByLocator(driver, By.cssSelector("div[aria-label*=\"Send\"]"));

    }

    /**
     * open 'Inbox'
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public void clickInbox(WebDriver driver) {

        //wait for arrival of new email
        WebUtil.waitForVisibilityOfElementLocated(driver, By.partialLinkText("Inbox"));

        // clickByLocator on inbox link
        WebUtil.clickByLocator(driver, By.partialLinkText("Inbox"));

    }

    /**
     * open latest email based on email address
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @param emailAddress of type String
     * @see WebUtil
     */
    public EmailPage openLatestEmail(WebDriver driver, String emailAddress) {

        //wait for new email to arrive
        WebUtil.waitForNumberOfElementsToBeMoreThan(driver, By.xpath(".//*[contains(., 'unread,') and @class='afn']/*[@email='" + emailAddress + "']"), 0);

        // retrieve fist element of a collection
        WebElement email = WebUtil.getElementFromCollectionByIndex(driver, By.xpath(".//*[contains(., 'unread,') and @class='afn']/*[@email='" + emailAddress + "']/../following-sibling::*"), 0);

        // Open email and create Email Page object
        if (email != null) {
            WebUtil.clickByElement(email);
            return PageFactory.initElements(driver, EmailPage.class);
        }
        else
            //report error
            return null;
    }

    /**
     * open latest email based on email address
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public LoginPage signOut(WebDriver driver) {

        //clickByLocator profile button
        WebUtil.clickByLocator(driver, By.cssSelector("[class='gb_2a gbii']"));

        //wait for sign out control to display
        WebUtil.waitForVisibilityOfElementLocated(driver,By.linkText("Sign out"));

        //perform a sign out
        WebUtil.clickByLocator(driver, By.linkText("Sign out"));

        //wait for email entry text box to display
        WebUtil.waitForVisibilityOfElementLocated(driver, By.xpath(".//*[@id='Email']"));

        //return login page object
        return PageFactory.initElements(driver, LoginPage.class);
    }

    /**
     * open latest email based on email address
     * @author  Roman Shulman
     * @param driver of type WebDriver
     * @see WebUtil
     */
    public boolean isPageDisplayed(WebDriver driver) {

        return WebUtil.isDisplayed(driver, By.partialLinkText("Inbox"));
    }

}
