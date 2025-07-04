package pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class CarWashServicesPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Logger logger;

    // Locators
    private final By bestDealPopupCloseButton = By.xpath("//span[@onclick='closePopUp(\'best_deal_div\');']");
    private final By allFilterContainer = By.xpath("//*[@class='jsx-679474515 resfilter_item gray_whitefill_animat font15 color111 fw500 all_filter_container']");
    private By ratingFilter(String rating) {
        return By.xpath("//span[ @aria-label='" + rating + "+']");
    }
    private final By ratingFilterHeader = By.xpath("//span[ @aria-label='Rating']"); // To close rating filter
    private final By applyFiltersButton = By.xpath("//button[text()='Apply Filters']");
    private final By serviceResultBoxes = By.xpath("//div[@class='jsx-7cbb814d75c86232 resultbox ']");
    private final By serviceName = By.xpath(".//h3[@class='jsx-7cbb814d75c86232 resultbox_title_anchor font22 fw500 color111 line_clamp_1 ']");
    private final By callNowButton = By.xpath(".//*[@class='jsx-7cbb814d75c86232  greenfill_animate callbutton font15 fw500 colorFFF mr-15 ']");
    private final By phoneNumberText = By.xpath("//*[@class='font20 fw600 colorFFF mt-15']");
    private final By popupCloseIcon = By.xpath("//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']");
    private final By directPhoneNumber = By.xpath(".//*[@class='jsx-7cbb814d75c86232 callcontent callNowAnchor']");


    public CarWashServicesPage() {
        this.driver = Hooks.driver;
        this.wait = Hooks.wait;
        this.js = (JavascriptExecutor) driver;
        this.logger = Hooks.logger;
    }

    public void handleBestDealPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(bestDealPopupCloseButton)).click();
            logger.info("Best Deal popup handled on Car Wash page.");
        } catch (Exception e) {
            logger.info("No 'Best Deal' popup found.");
        }
    }

    public void clickAllFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(allFilterContainer)).click();
        logger.info("Clicked 'All Filters' button.");
    }

    public void applyRatingFilter(String rating) throws InterruptedException {
        Thread.sleep(2000); // Consider replacing with explicit wait for filters to load
        WebElement ratingElement = wait.until(ExpectedConditions.elementToBeClickable(ratingFilter(rating)));
//        WebElement ratingElement = wait.until(ExpectedConditions.visibilityOfElementLocated(ratingFilter(rating)));
        js.executeScript("arguments[0].scrollIntoView()", ratingElement);
        ratingElement.click();
        wait.until(ExpectedConditions.elementToBeClickable(ratingFilterHeader)).click(); // Close the rating dropdown
        logger.info("Applied rating filter: " + rating + "+ stars.");
    }

    public void clickApplyFilters() {
        wait.until(ExpectedConditions.elementToBeClickable(applyFiltersButton)).click();
        logger.info("Clicked 'Apply Filters' button.");
    }

    public List<String[]> getServiceDetails(int count) throws InterruptedException {
        List<String[]> serviceDetails = new ArrayList<>();
        List<WebElement> servicesList = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(serviceResultBoxes));

        logger.info("Retrieving details for the first " + Math.min(servicesList.size(), count) + " car wash services.");
        wait=new WebDriverWait(driver, Duration.ofSeconds(5));
        for (int i = 0; i < Math.min(servicesList.size(), count); i++) {
            WebElement service = servicesList.get(i);
            js.executeScript("arguments[0].scrollIntoView()", service);

            String name = "";
            String phone = "";

            try {
                name = service.findElement(serviceName).getText();
                try {
                    service.findElement(callNowButton).click();
                    Thread.sleep(2000); // Wait for phone number popup
//                    phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberText)).getText();
                    phone=driver.findElement(phoneNumberText).getText();
                    driver.findElement(popupCloseIcon).click();
//                    wait.until(ExpectedConditions.elementToBeClickable(popupCloseIcon)).click();
                } catch (Exception e) {
                    // If call now button is not present, try to get direct number
                    phone = service.findElement(directPhoneNumber).getText();
                }
            } catch (StaleElementReferenceException e) {
                // Re-find elements if StaleElementReferenceException occurs
                servicesList = driver.findElements(serviceResultBoxes);
                service = servicesList.get(i);
                js.executeScript("arguments[0].scrollIntoView()", service);
                name = service.findElement(serviceName).getText();
                try {
                    service.findElement(callNowButton).click();
                    Thread.sleep(2000); // Wait for phone number popup
//                    phone = wait.until(ExpectedConditions.visibilityOfElementLocated(phoneNumberText)).getText();
                    phone=driver.findElement(phoneNumberText).getText();
                    driver.findElement(popupCloseIcon).click();
//                    wait.until(ExpectedConditions.elementToBeClickable(popupCloseIcon)).click();
                } catch (Exception ee) {
                    phone = service.findElement(directPhoneNumber).getText();
                }
            }
            serviceDetails.add(new String[]{name, phone});
        }
        return serviceDetails;
    }
}