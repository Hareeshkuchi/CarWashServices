package pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;
import utilities.PropsLoader;

public class JustdialHomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Logger logger;

    // Locators
    private final By maybeLaterLink = By.linkText("Maybe Later");
//    private final By freeListingLink = By.xpath("//*[@class='jsx-50d08bd883f6f57 headnav_item_text pl-5 font14 fw400 color111' and text()='Free Listing']");
    private final By freeListingLink = By.xpath("//*[@id='header_freelisting']");
    private final By cityAutoSugInput = By.id("city-auto-sug");
    private final By detectedLocationText = By.xpath("//*[@class='location_text font14 fw400 color007']");
    private final By mainAutoSuggestInput = By.id("main-auto");
    private final By mainAutoSuggestFirstItem = By.id("react-autowhatever-main-auto-suggest--item-0");


    public JustdialHomePage() {
        this.driver = Hooks.driver;
        this.wait = Hooks.wait;
        this.logger = Hooks.logger;
    }

    public void navigateToJustdial() {
        PropsLoader.loadProps();
        driver.get("https://www.justdial.com");
        driver.manage().window().maximize();
        logger.info("Navigated to Justdial homepage: " + PropsLoader.URL);
    }

    public void handleLoginPopup() {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(maybeLaterLink)).click();
            logger.info("Login popup handled by clicking 'Maybe Later'.");
        } catch (Exception e) {
            logger.info("No Login Popup Found.");
        }
    }

    public void clickFreeListing() {
        wait.until(ExpectedConditions.elementToBeClickable(freeListingLink)).click();
//        wait.until(ExpectedConditions.visibilityOfElementLocated(freeListingLink)).click();
        logger.info("Clicked on 'Free Listing' link.");

    }

    public void clickCityAutoSuggest() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cityAutoSugInput)).click();
        logger.info("Clicked on city auto suggest input.");
    }

    public void selectDetectedLocation() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(detectedLocationText)).click();
//        wait.until(ExpectedConditions.invisibilityOfElementLocated(detectedLocationText)); // Wait for the element to disappear
        logger.info("Selected detected location.");
    }

    public void enterSearchQuery(String query) throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(mainAutoSuggestInput)).sendKeys(query);
        Thread.sleep(2000); // Consider replacing with explicit wait for suggestions
        logger.info("Entered '" + query + "' into search bar.");
    }

    public void selectFirstAutoSuggestItem() {
        wait.until(ExpectedConditions.elementToBeClickable(mainAutoSuggestFirstItem)).click();
        logger.info("Selected the first auto-suggest item.");
    }
}