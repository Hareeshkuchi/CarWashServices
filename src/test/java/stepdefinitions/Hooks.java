package stepdefinitions;

import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario; // Import Scenario for hooks

import utilities.DriverManager; // Assuming this class manages WebDriver instances
import utilities.PropsLoader;   // Assuming this class loads properties

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {

    public  static final Logger logger = LogManager.getLogger(Hooks.class);
    public  static WebDriver driver; // Declare WebDriver here to be accessible within hooks
    public static WebDriverWait wait; // Declare WebDriverWait here for use in @Before hook


    @Before
    public void setup(Scenario scenario) {
        logger.info("Starting scenario: " + scenario.getName());

        PropsLoader.loadProps();

        // Initialize the WebDriver using DriverManager
        driver = DriverManager.getDriver(PropsLoader.browser);
        driver.manage().window().maximize();
        // Set up common WebDriver utilities
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));


        // Navigate to the base URL and maximize the window
        driver.get(PropsLoader.URL);
        logger.info("Navigated to Justdial homepage: " + PropsLoader.URL);
        // Handle the "Maybe Later" login popup if it appears
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Maybe Later"))).click();
            logger.info("Clicked 'Maybe Later' popup during setup.");
        } catch (Exception e) {
            logger.info("No 'Maybe Later' popup found during setup.");
        }
    }
    @After
    public void teardown(Scenario scenario) {
        // Check if the scenario has failed
        if (scenario.isFailed()) {
            logger.error("Scenario failed: " + scenario.getName());
            try {
                // Cast WebDriver to TakesScreenshot interface
                TakesScreenshot tss = (TakesScreenshot) driver;
                // Get the screenshot as a byte array
                byte[] screenshot = tss.getScreenshotAs(OutputType.BYTES);
                // Attach the screenshot to the Cucumber report
                scenario.attach(screenshot, "image/png", scenario.getName() + " - Failure Screenshot");
                logger.info("Screenshot taken and attached for failed scenario: " + scenario.getName());
            } catch (WebDriverException e) {
                // Catch WebDriver-specific exceptions during screenshot
                logger.error("WebDriver error while taking screenshot: " + e.getMessage(), e);
            } catch (Exception e) {
                // Catch any other exceptions
                logger.error("Failed to take or attach screenshot: " + e.getMessage(), e);
            }
        }
        // Quit the WebDriver to close the browser
        DriverManager.quitDriver();
        logger.info("Finished scenario: " + scenario.getName() + " with status: " + scenario.getStatus());
    }
}

