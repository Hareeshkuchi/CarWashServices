package pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

public class FreeListingPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger;

    // Locators
    private final By popupModal = By.xpath("//*[@class='modal_modal__zB_6A styles_modal__fSE4U']");
    private final By closePopupButton = By.xpath("//*[@class='iconwrap closeicon__grey']");
    private final By mobileNumberInput = By.xpath("//input[@aria-label='Enter Mobile Number' and @id='1']");
    private final By startNowButton = By.xpath("//*[@class='primarybutton undefined' and @aria-label='Start Now']");
    private final By phoneNumberErrorText = By.xpath("//span[@class='undefined entermobilenumber_error__text__uPM09']");

    public FreeListingPage() {
        this.driver = Hooks.driver;
        this.wait = Hooks.wait;
        this.logger = Hooks.logger;
    }

    public void handleFreeListingPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(popupModal));
            wait.until(ExpectedConditions.elementToBeClickable(closePopupButton)).click();
            logger.info("Free Listing popup handled.");
        } catch (Exception e) {
            logger.info("No Free Listing popup found.");
        }
    }

    public void enterMobileNumber(String mobileNumber) {
        WebElement phoneInput = wait.until(ExpectedConditions.visibilityOfElementLocated(mobileNumberInput));
        phoneInput.sendKeys(mobileNumber);
        logger.info("Entered '" + mobileNumber + "' into mobile number input.");
    }

    public void clickStartNow() {
        wait.until(ExpectedConditions.elementToBeClickable(startNowButton)).click();
        logger.info("Clicked 'Start Now' button.");
    }

    public String getPhoneNumberErrorMessage() {
        try{
            WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberErrorText));
            String errorMsg = errorElement.getText();
            logger.info("Retrieved phone number error message: " + errorMsg);
            return errorMsg;
        }
        catch (Exception e){
            return "";
        }
    }
}