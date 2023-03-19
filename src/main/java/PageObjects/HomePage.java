package PageObjects;
import SetUp.SetUpDriver;
import Utils.Util;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import javax.xml.xpath.XPath;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.List;
public class HomePage extends SetUpDriver {
    public HomePage homePage;
    public WebDriver driver;
    public WebDriverWait wait;
    public static Properties prop ;
    public HomePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//a[normalize-space()='Coco']")
    public WebElement WebsiteLogo;
   @FindBy(css = "a[data-reactid='.0.0.0.2.0.0.5']")
    public WebElement BuyNowButton;
    @FindBy(xpath = "//span[text()='20,000']")
    public WebElement MidtransPillowPrice;
    @FindBy(className = "cart-checkout")
    public WebElement CheckoutButton;
    @FindBy(xpath = "(//input[@type='text'])[1]")
    public WebElement NameOnCheckoutPage;
    @FindBy(xpath = "//input[@value='budi@utomo.com']")
    public WebElement EmailOnCheckoutPage;
    @FindBy(xpath = "//input[@value='081808466410']")
    public WebElement PhoneOnCheckoutPage;
    @FindBy(xpath = "//input[@value='Jakarta']")
    public WebElement CityOnCheckoutPage;
    @FindBy(xpath = "//body[1]/div[1]/div[1]/div[1]/div[2]/div[1]/div[4]/table[1]/tbody[1]/tr[5]/td[2]/textarea")
    public WebElement AddressOnCheckoutPage;
    @FindBy(xpath = "//input[@value='10220']")
    public WebElement PostalCodeOnCheckoutPage;
    @FindBy(xpath = "//div[@class='merchant-name']")
    public WebElement DemostorePageLogo;
    @FindBy(xpath = "//td[@class='order-summary-content float-right']")
    public WebElement OrderProductPrice;
    @FindBy(className = "header-order-id")
    public WebElement orderId;
    @FindBy(xpath = "//span[text() ='Midtrans Pillow']")
    public WebElement orderProductName;
    @FindBy(xpath = "//div[@class='page-container scroll']")
    public WebElement paymentSection;
    @FindBy (xpath = "//img[@alt='expand']")
    public WebElement ScrollDown;
    @FindBy(xpath = "//div[@class='list-title text-actionable-bold']")
    public List<WebElement> paymentOptions;
    @FindBy(xpath = "//div[text()='Credit/debit card']")
    public WebElement CreditCradPayment;
    @FindBy(css = ".title-text.text-actionable-bold")
    public WebElement cardDetailsScreen;
    @FindBy(className ="header-amount")
    public WebElement orderAmountBeforeAddingCart;
    @FindBy(className = "valid-input-value")
    public List<WebElement> cardNumberInputField;
    @FindBy(id = "card-expiry")
    public WebElement expirationDateInputField;
    @FindBy(id = "card-cvv")
    public WebElement cvvInputField;
    @FindBy(className ="card-pay-button-part" )
    public WebElement PayNowButton;

    @FindBy(css = "iframe[title='3ds-iframe']")
    public WebElement iFrame2;

//    @FindBy (xpath = "//iframe [@title='3ds-iframe']")
//    public WebElement iFrame;

    @FindBy(id = "snap-midtrans")
    public WebElement iFrame;


    @FindBy(id = "merchant_name")
    public WebElement merchantName;
    @FindBy(id = "txn_amount")
    public WebElement amount;
    @FindBy (xpath = "//p[@id='card_number']")
    public WebElement CardNumber;
    @FindBy (xpath = "//button[@name='ok']")
    public WebElement OKbutton;
    @FindBy (css = ".form-control " )
    public WebElement OTP;
    @FindBy (xpath = "//span[normalize-space()='Thank you for your purchase.']")
    public WebElement PaymentSuccessfull;
    @FindBy (css = ".cancel-modal-subtitle")
    public WebElement PaymentDeclined;
    @FindBy(xpath = "//button[text()='OK']")
    public WebElement CardDeclined_OK_Button;
    @FindBy(xpath = "//button[@title='Abort authentication']")
    public WebElement CancelButton;
    //--------------------------------------------------------------------------------------------------------
    public boolean VerifyPageLogoIsDisplayed() {
        return WebsiteLogo.isDisplayed();
    }
    public void ClickOnBuyNowButton() {
        explicitWait().until(ExpectedConditions.visibilityOf(BuyNowButton));
        BuyNowButton.click();
    }
    public boolean Verify_Midtran_PillowPrice_IsDisplayed() {
        return MidtransPillowPrice.isDisplayed();
    }
    public void Click_On_CheckOutButton(){
        Util.holdExecution(1);
        wait.until(ExpectedConditions.visibilityOf(CheckoutButton));
        CheckoutButton.click();
    }
    public void Verify_Name_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(NameOnCheckoutPage));
    }
    public void Verify_Email_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(EmailOnCheckoutPage));
    }
    public void Verify_Phone_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(PhoneOnCheckoutPage));
    }
    public void Verify_City_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(CityOnCheckoutPage));
    }
    public void Verify_Address_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(AddressOnCheckoutPage));
    }
    public void Verify_PostalCode_IsDisplayed() {
        explicitWait().until(ExpectedConditions.visibilityOf(PostalCodeOnCheckoutPage));
    }
    public void total_OrderID_on_popout() {
        explicitWait().until(ExpectedConditions.visibilityOf(orderId));
        System.out.println(orderId.getText());
    }
    public void OrderSummary() {
        //prop = new Properties();
        explicitWait().until(ExpectedConditions.elementToBeClickable(orderId)).click();
        Assert.assertEquals(orderProductName.getText(),"Midtrans Pillow");
        Assert.assertEquals(OrderProductPrice.getText(),"Rp20.000");
    }
    public void clickClearAndType(WebElement webElement, String text) {
        explicitWait().until(ExpectedConditions.elementToBeClickable(webElement)).click();
        webElement.clear();
        webElement.sendKeys(text);
    }
    public boolean Verify_PaymentSection_IsDisplayed() {
        return paymentSection.isDisplayed();
    }

    public void paymentSection() {
        driver.switchTo().frame(iFrame);
        explicitWait().until(ExpectedConditions.visibilityOf(paymentSection));

    }
    public void AllPayments() {
        String[] expected = {"GoPay","Bank transfer", "Credit/debit card",  "ShopeePay", "QRIS", "Alfa Group","Indomaret","Akulaku PayLater","Kredivo", "UOB EZ Pay","BCA KlikPay", "OCTO Clicks","BRImo", "Danamon Online Banking","klikBCA"};
        // assert that the number of found <option> elements matches the expectations
        System.out.println(paymentOptions.size());
        System.out.println(expected.length);
        Assert.assertEquals(expected.length, paymentOptions.size());
        // assert that the value of every <option> element equals the expected value
        for (int i = 0; i < paymentOptions.size(); i++) {
            if(expected[i].contains(paymentOptions.get(i).getText())){
                assert true;
                System.out.println(paymentOptions.get(i).getText());
            }
        }
    }
    public void ClickOnCreditCardPayment() {
        //paymentOptions.get(2).click();
        CreditCradPayment.click();
    }
    public boolean Verify_CardDetailScreen_IsDisplayed() {
        return cardDetailsScreen.isDisplayed();
    }
    public boolean Verify_orderAmountBeforeAddingCart_is_Displayed() {
        String previousAmount = orderAmountBeforeAddingCart.getText();
        System.out.println("Previous amount: " + previousAmount);
        return orderAmountBeforeAddingCart.isDisplayed();
    }
    public WebDriverWait explicitWait() {
        wait = new WebDriverWait(driver, 20);
        return wait;
    }

    public void amountUpdateAfterApplyingCoupon() {
      String updatedAmount = orderAmountBeforeAddingCart.getText();
        System.out.println("Updated amount: " + updatedAmount);
        Assert.assertNotEquals("Rp20.000", updatedAmount);
    }
    public void PayNow (){
        PayNowButton.click();
    }
    public void Click_on_OK_Button(){
        OKbutton.click();
    }
    public boolean Verify_Payment_Successfill_isDisplayed(){
        return PaymentSuccessfull.isDisplayed();
    }
    public boolean Verify_Payment_Declined_isDisplayed() {
        return PaymentDeclined.isDisplayed();
    }
    public void click_OK_ToDecline(){
        CardDeclined_OK_Button.click();
    }
    public void Click_Cancel_Button(){
        explicitWait().until(ExpectedConditions.visibilityOf(CancelButton));
        CancelButton.click();
    }
}
