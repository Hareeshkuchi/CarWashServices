package pageobjects;

import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import stepdefinitions.Hooks;

import java.util.List;
import java.util.stream.Collectors;

public class FitnessPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private Logger logger;

    // Locators
    private final By fitnessCategoryTab = By.xpath("//*[@aria-controls='panel4' and @id='tab4']");
    private final By gymsLink = By.linkText("Gyms");
    private final By gymNames = By.xpath("//h2");

    public FitnessPage() {
        this.driver = Hooks.driver;
        this.wait = Hooks.wait;
        this.js = (JavascriptExecutor) driver;
        this.logger = Hooks.logger;
    }

    public void scrollToFitnessCategory() {
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        WebElement fitnessElem = wait.until(ExpectedConditions.visibilityOfElementLocated(fitnessCategoryTab));
        js.executeScript("arguments[0].scrollIntoView()", fitnessElem);
        js.executeScript("window.scrollTo(0, -50);"); // Adjust scroll to make it fully visible
        logger.info("Scrolled to Fitness category.");
    }

    public void clickFitnessCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(fitnessCategoryTab)).click();
        logger.info("Clicked on Fitness category tab.");
    }

    public void selectGymsSubCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(gymsLink)).click();
        logger.info("Selected 'Gyms' sub-category.");
    }

    public List<String> getAllGymSubMenuItems() {
        List<WebElement> gymNameElements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(gymNames));
        return gymNameElements.stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
    }
}