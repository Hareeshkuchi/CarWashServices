package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.DriverManager;
import utilities.PropsLoader;

import java.time.Duration;
import java.util.List;

public class FitnessMenuNavigation {
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	@Given("I am on the Justdial website again")
	public void JustdailPage(){
		driver= DriverManager.driver;
//		driver=DriverManager.getDriver();
		wait=new WebDriverWait(driver, Duration.ofSeconds(10));
		act=new Actions(driver);
		js=(JavascriptExecutor) driver;
		PropsLoader.loadProps();
		driver.get(PropsLoader.URL);
		driver.manage().window().maximize();
		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Maybe Later"))).click();
		}
		catch(Exception e) {
			System.out.println("No Login Popup Found");
		}
	}

	@When("I navigate to the {string} category")
	public void i_navigate_to_the_category(String string) {
		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
		WebElement fitnessElem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-controls='panel4' and @id='tab4']")));
		js.executeScript("arguments[0].scrollIntoView()",fitnessElem);
		js.executeScript("window.scrollTo(0, -50);");
		fitnessElem.click();
	}

	@When("I then select the {string} sub-category")
	public void i_then_select_the_sub_category(String string) {
	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gyms"))).click();
	}

	@Then("I should be able to retrieve all displayed sub-menu items")
	public void i_should_be_able_to_retrieve_all_displayed_sub_menu_items() {
	    List<WebElement> zymnames=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2")));
		int n=zymnames.size();
		for(int i=0;i<Math.max(10,n);i++){
			try {
				WebElement elem=zymnames.get(i);
				System.out.println(elem.getText());
			}
			catch(Exception e){
				zymnames=driver.findElements(By.xpath("//h2"));
				WebElement elem=zymnames.get(i);
				System.out.println(elem.getText());
			}
		}
	}
}
