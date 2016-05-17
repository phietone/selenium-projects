package com.olenick.pageobjects;

import com.olenick.util.WebUtil;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


/**
 * EmailPage.java - class representing opened Email
 * @author  Roman Shulman
 * @version 1.0
 * @see WebUtil
 */
public class EmailPage {

    private WebDriverWait wait;


    /**
     * check if the email has been opened
     * @author  Roman Shulman
     * @return boolean true=displayed, false=not displayed
     * @see WebUtil
     */
    public boolean IsEmailOpened(WebDriver driver) {
        //System.out.println(driver.getPageSource());
        //wait for email to be opened
        WebUtil.waitForVisibilityOfElementLocated(driver, By.xpath(".//*[@aria-label='Reply']"));

        //return display status
        return WebUtil.isDisplayed(driver, By.xpath(".//*[@aria-label='Reply']"));

    }

    /**
     * Retrieve emails subject line text
     * @author  Roman Shulman
     * @return String data type
     * @see WebUtil
     */
    public String getSubjectLineText(WebDriver driver) {

        return WebUtil.getElementTextByLocator(driver, By.cssSelector("[class='ha'] h2"));

    }

    /**
     * Retrieve emails body text
     * @author  Roman Shulman
     * @return String data type
     * @see WebUtil
     */
    public String getEmailBodyText(WebDriver driver) {

        WebElement emailBody = driver.findElement(By.cssSelector("[class*=\"a3s aXjCH\"] div[dir='ltr']"));//div[@class='G-Ni J-J5-Ji' and not (contains(@style, 'none'))]//div[@aria-label='Delete']
        return emailBody.getText();


    }
}
