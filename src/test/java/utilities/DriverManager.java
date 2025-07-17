package utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.Arrays;

public class DriverManager {
	public static WebDriver driver;
	public static WebDriver getDriver(String browser) {
		if(browser.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
					+ "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
			driver = new ChromeDriver(options);
		}else if(browser.equalsIgnoreCase("edge")){
			EdgeOptions options=new EdgeOptions();
			options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
			options.setExperimentalOption("useAutomationExtension", false);
			options.addArguments("--disable-blink-features=AutomationControlled");
			options.addArguments("user-agent=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 "
					+ "(KHTML, like Gecko) Chrome/115.0.0.0 Safari/537.36");
			driver=new EdgeDriver(options);
		}
		return driver;
	}

    public static void quitDriver() {
		driver.quit();
    }
}

