package TestCases;
import java.lang.*;

import PageObjects.HomePage;
import SetUp.SetUpDriver;
import Utils.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.openqa.selenium.support.ui.WebDriverWait;
public class HomePageTest extends SetUpDriver {

    public static Properties prop = new Properties();
    public Actions actions;
   public HomePageTest(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public HomePageTest() {
        try {
            FileInputStream file = new FileInputStream(System.getProperty("user.dir") + File.separator + "resources" + File.separator + "testData" + File.separator + "config.properties");
            prop.load(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    @Test(testName = "TC-1", priority = 1, groups = {"regression"})
    public void verifyItemIsAddedAndAmountAvailability() {
        String title = driver.getTitle();
        System.out.println(title);
        driver.get(prop.getProperty("url"));
        homePage.VerifyPageLogoIsDisplayed();
        homePage.ClickOnBuyNowButton();
        Assert.assertTrue(homePage.Verify_Midtran_PillowPrice_IsDisplayed());
    }

    @Test(testName = "TC-2", priority = 2, groups = {"regression", "smoke"})
    public void VerifyClicking_BuyNowButton_redirectUserToCheckoutPopup() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
    }
    @Test(testName = "TC-3", priority = 3, groups = {"regression"})
    public void VerifyName_Email_Phone_City_Address_Postalcode_On_CheckoutPage() {
        homePage.ClickOnBuyNowButton();
        homePage.Verify_Name_IsDisplayed();
        //System.out.println(homePage.NameOnCheckoutPage.getAttribute("value"));
        homePage.Verify_Email_IsDisplayed();
        //System.out.println(homePage.EmailOnCheckoutPage.getAttribute("value"));
        homePage.Verify_Phone_IsDisplayed();
        //System.out.println(homePage.PhoneOnCheckoutPage.getAttribute("value"));
        homePage.Verify_City_IsDisplayed();
        //System.out.println(homePage.CityOnCheckoutPage.getAttribute("value"));
        homePage.Verify_Address_IsDisplayed();
        //System.out.println(homePage.AddressOnCheckoutPage.getAttribute("value"));
    }
    @Test(testName = "TC-4", priority = 4, groups = {"regression"})
    public void Verify_Customer_Details_Are_Editable() {
        homePage.ClickOnBuyNowButton();
        homePage.clickClearAndType(homePage.NameOnCheckoutPage, prop.getProperty("name"));
        homePage.clickClearAndType(homePage.EmailOnCheckoutPage, prop.getProperty("email"));
        homePage.clickClearAndType(homePage.PhoneOnCheckoutPage, prop.getProperty("phone"));
        homePage.clickClearAndType(homePage.CityOnCheckoutPage, prop.getProperty("city"));
        homePage.clickClearAndType(homePage.AddressOnCheckoutPage, prop.getProperty("address"));
        homePage.clickClearAndType(homePage.PostalCodeOnCheckoutPage, prop.getProperty("postcode"));
        homePage.Click_On_CheckOutButton();
    }
    @Test(testName = "TC-5", priority = 5, groups = {"regression", "smoke"})
    public void Verify_clicking_on_CheckoutButton_redirect_User_on_Order_SummaryPopup() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
        driver.switchTo().frame("snap-midtrans");
        System.out.println(homePage.DemostorePageLogo.getText());
        homePage.total_OrderID_on_popout();
    }
    @Test(testName = "TC-6", priority = 6, groups = {"regression"})
    public void Verify_Product_Details_on_Order_Summery() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
        //driver.switchTo().frame("snap-midtrans");
        homePage.paymentSection();
        homePage.OrderSummary();
    }
    @Test(testName = "TC-7", priority = 7, groups = {"smoke", "regression"})
    public void verifySelectPaymentPageFromOrderSummary() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
       // driver.switchTo().frame("snap-midtrans");
        homePage.paymentSection();
        homePage.Verify_PaymentSection_IsDisplayed();
    }
    @Test(testName = "TC-8", priority = 8, groups = {"smoke"})
    public void verifyPaymentOption_onPaymentPage() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
        //driver.switchTo().frame("snap-midtrans");
        homePage.paymentSection();
        homePage.AllPayments();
    }
    @Test(testName = "TC-9", priority = 9, groups = {"smoke", "regression"})
    public void verifyUser_clickOn_CreditDebitCard_payment_method() {
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
        //driver.switchTo().frame("snap-midtrans");
        homePage.paymentSection();
        homePage.ClickOnCreditCardPayment();
        Assert.assertTrue(homePage.Verify_CardDetailScreen_IsDisplayed());
    }
    @Test(testName = "TC-10", priority = 10, groups = {"regression"})
public void verifyOrderAmountAfterApplyingCoupon(){
    homePage.ClickOnBuyNowButton();
    homePage.Click_On_CheckOutButton();
    //driver.switchTo().frame("snap-midtrans");
    homePage.paymentSection();
    homePage.ClickOnCreditCardPayment();
    homePage.Verify_orderAmountBeforeAddingCart_is_Displayed();
    homePage.clickClearAndType(homePage.cardNumberInputField.get(0), prop.getProperty("card.number"));
    homePage.amountUpdateAfterApplyingCoupon();
    }
    @Test(testName = "TC-11", priority = 11, groups = {"smoke","regression"})
    public void enterValidCardDetailsAndClickingOnPayButton(){
        homePage.ClickOnBuyNowButton();
        homePage.Click_On_CheckOutButton();
        homePage.paymentSection();
        homePage.ClickOnCreditCardPayment();
        homePage.clickClearAndType(homePage.cardNumberInputField.get(0), prop.getProperty("card.number"));
        homePage.clickClearAndType(homePage.expirationDateInputField, prop.getProperty("card.expiry.date"));
        homePage.clickClearAndType(homePage.cvvInputField, prop.getProperty("card.cvv"));
        homePage.PayNow();
    }
    @Test(testName = "TC-12", priority = 12, groups = {"regression"})
    public void clickingOnPayButtonAndVerifyVariousDetails()  {
        wait = new WebDriverWait(driver, 30);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.iFrame2));
        driver.switchTo().frame(homePage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(homePage.merchantName));
        Assert.assertEquals(homePage.merchantName.getText(),prop.getProperty("merchant.name"));
        Assert.assertEquals(homePage.amount.getText(),prop.getProperty("final.amount"));
        Assert.assertEquals(homePage.CardNumber.getText(),prop.getProperty("payment.card.number"));
    }
    @Test(testName = "TC-13", priority = 13, groups = {"smoke", "regression"})
    public void verifyClickingOkWithValidOTPRedirectsToOrderSuccessful(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.iFrame2));
        driver.switchTo().frame(homePage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(homePage.OTP));
        homePage.clickClearAndType(homePage.OTP, prop.getProperty("otp"));
        homePage.Click_on_OK_Button();
        Assert.assertTrue(homePage.Verify_Payment_Successfill_isDisplayed());
    }
    @Test(testName = "TC-14", priority = 14, groups = {"regression"})
    public void verifyClickingOkWithInvalidOTPRedirectsToOrderDeclined(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        Util.holdExecution(10);
        wait.until(ExpectedConditions.visibilityOf(homePage.iFrame2));
        driver.switchTo().frame(homePage.iFrame2);
        wait.until(ExpectedConditions.visibilityOf(homePage.OTP));
        homePage.clickClearAndType(homePage.OTP, prop.getProperty("invalid.otp"));
        homePage.Click_on_OK_Button();
        driver.switchTo().parentFrame();
        Assert.assertTrue(homePage.Verify_Payment_Declined_isDisplayed());
    }
    @Test(testName = "TC-15", priority = 15, groups = {"regression"})
    public void verifyClickingCancelRedirectsToOrderDeclined(){
        wait = new WebDriverWait(driver, 20);
        enterValidCardDetailsAndClickingOnPayButton();
        wait.until(ExpectedConditions.visibilityOf(homePage.iFrame2));
        driver.switchTo().frame(homePage.iFrame2);
        homePage.Click_Cancel_Button();
        driver.switchTo().parentFrame();
        Assert.assertTrue(homePage.Verify_Payment_Declined_isDisplayed());
    }
}

