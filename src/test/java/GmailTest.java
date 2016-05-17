import com.olenick.categories.Critical;
import com.olenick.categories.Major;
import com.olenick.pageobjects.HomePage;
import com.olenick.pageobjects.LoginPage;
import com.olenick.util.WebUtil;
import com.olenick.pageobjects.EmailPage;
//import com.olenick.categories.Critical;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

/**
 * GmailTest.java - class representing Test Suit for Gmail application
 * @author  Roman Shulman
 * @version 1.0
 * @see LoginPage
 * @see HomePage
 * @see EmailPage
 */
public class GmailTest {

    private WebDriver driver = new FirefoxDriver();
    private WebDriverWait wait = new WebDriverWait(driver, 30);
    private final static String emailAddress = "intellizentsia@gmail.com";
    private final static String password = "infinit1";

    @Category({Major.class})
    @Test
    /**
     * verify that a user is able to login into application
     * @author  Roman Shulman
     */
    public void loginShouldBeSuccessfull() {

        //maximize browser window
        driver.manage().window().maximize();

        //Navigate to url
        LoginPage loginPage = WebUtil.navigateToLoginPage(driver);

        //Fill in the user name
        loginPage.enterUserName(driver, emailAddress);
//       WebElement userNameTextBox = driver.findElement(By.xpath(".//*[@id='Email']"));
//        userNameTextBox.clear();
//        userNameTextBox.sendKeys(emailAddress);

        //Click Next button
        loginPage.clickNext(driver);
//        WebElement nextButton = driver.findElement(By.xpath(".//*[@id='next']"));
//        nextButton.clickByLocator();

        //Fill in the user password
        loginPage.enterPassword(driver, password);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='Passwd']")));
//        WebElement userPasswordTxtBx = driver.findElement(By.xpath(".//*[@id='Passwd']"));
//        userPasswordTxtBx.clear();
//        userPasswordTxtBx.sendKeys(password);

        //unckeck stay signed in
        loginPage.selectStaySignedIn(driver, false);
//        WebElement staySignedInChkBx = driver.findElement(By.xpath(".//[@id='PersistentCookie']"));
//        if (staySignedInChkBx.isSelected()){
//            staySignedInChkBx.clickByLocator();
//        }
        //Click signIn button
        HomePage homePage = loginPage.clickSignIn(driver);
//        WebElement signInBtn = driver.findElement(By.xpath(".//*[@id='signIn']"));
//        signInBtn.clickByLocator();

        //verify user logged in
//       wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        Assert.assertTrue("Inbox must exist", homePage.isPageDisplayed(driver));

        //sign out
        loginPage = homePage.signOut(driver);
//        WebElement profileButton = driver.findElement(By.cssSelector("[class='gb_2a gbii']"));
//        profileButton.clickByLocator();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Sign out")));
//        WebElement signOutLink = driver.findElement(By.linkText("Sign out"));
//        signOutLink.clickByLocator();

        //verify sign out
        Assert.assertTrue("Login page must display", loginPage.isPageDisplayed(driver));

    }

    @Category({Critical.class})
    @Test
    /**
     * verify that a user is able to login into application
     * and send and receive an email
     * @author  Roman Shulman
     */
    public void sendAndReceiveEmailTest() throws FileNotFoundException {

        //redirect System.out to a file
        System.setOut(new PrintStream(new BufferedOutputStream(new FileOutputStream("C:\\Users\\rshulman\\Desktop\\output.txt"))));

        //maximize browser window
        driver.manage().window().maximize();

        //Navigate to url
        LoginPage loginPage = WebUtil.navigateToLoginPage(driver);

        //Fill in the user name
        loginPage.enterUserName(driver, emailAddress);

        //Click Next button
        loginPage.clickNext(driver);

        //Fill in the user password
        loginPage.enterPassword(driver, password);

        //checkunckeck stay signed in
        loginPage.selectStaySignedIn(driver, false);

        //Click signIn button
        HomePage homePage = loginPage.clickSignIn(driver);

        //verify user logged in
        Assert.assertTrue("Inbox must exist", homePage.isPageDisplayed(driver));

        //clickByLocator compose
        homePage.clickCompose(driver);

        //Fill in receipient
        homePage.enterRecipientAddress(driver, emailAddress);

        //Fill in subject
        final String expectedSubjectLineText = "test Gmail send and receive functionality";
        homePage.enterSubjectLine(driver, expectedSubjectLineText);

        //Fill in email body
        final String expectedEmailBodyText = "Test email to check Gmail emailing functionality";
        homePage.enterBodyText(driver, expectedEmailBodyText);

        //Click send
        homePage.clickSend(driver);

        //Click Inbox
        homePage.clickInbox(driver);

        //Verify 'Primary' tab is active
        Assert.assertTrue("Primary tab must be selected", homePage.isPrimaryTabActive(driver));

        //Click received email//*[contains(., 'unread,')]/*[@email='intellizentsia@gmail.com']/../following-sibling::*
        EmailPage emailPage = homePage.openLatestEmail(driver, emailAddress);


        //Verify Email opened
        Assert.assertTrue("Email must be opened", emailPage.IsEmailOpened(driver));

        //Verify  subject lines of the send and rceived emails match
        String actualSubjectLineText = emailPage.getSubjectLineText(driver);
        Assert.assertEquals("Email send and received subject line text must match", expectedSubjectLineText, actualSubjectLineText);

        //Verify  body text of the send and rceived emails match
        String actualEmailBodyText = emailPage.getEmailBodyText(driver);
        Assert.assertEquals("Email send and received body text must match", expectedEmailBodyText, actualEmailBodyText);

        //sign out
        loginPage = homePage.signOut(driver);

        //verify sign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//*[@id='Email']")));
        Assert.assertTrue("Email entry text box must exist",driver.findElement(By.xpath(".//*[@id='Email']")).isDisplayed());

    }

    @After
    /**
     * Perform required cleanup after the test is finished running
     * @author  Roman Shulman
     */
    public void tearDown() {
        //driver.quit();
    }

}
