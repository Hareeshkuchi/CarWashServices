package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageobjects.FitnessPage;
import pageobjects.JustdialHomePage;
import utilities.PropsLoader;
import java.util.List;

public class FitnessMenuNavigation {
	private JustdialHomePage justdialHomePage;
	private FitnessPage fitnessPage;

	@Given("I am on the Justdial website again")
	public void JustdailPage(){
		justdialHomePage = new JustdialHomePage();
		fitnessPage = new FitnessPage();

//		justdialHomePage.navigateToJustdial();
	}

	@When("I navigate to the Fitness category")
	public void i_navigate_to_the_category() {
		fitnessPage.scrollToFitnessCategory();
		fitnessPage.clickFitnessCategory();
	}

	@When("I then select the Gym sub-category")
	public void i_then_select_the_sub_category() {
		fitnessPage.selectGymsSubCategory();
	}

	@Then("I should be able to retrieve {string} displayed sub-menu items")
	public void i_should_be_able_to_retrieve_all_displayed_sub_menu_items(String string) {
		List<String> gymNames = fitnessPage.getAllGymSubMenuItems();
		Hooks.logger.info("Printing the names of Gyms:");
		for (String name : gymNames) {
//			System.out.println(name);
			Hooks.logger.info(name+"\n");
		}
	}
}

//public class FitnessMenuNavigation {
//	WebDriver driver;
//	WebDriverWait wait;
//	Actions act;
//	JavascriptExecutor js;
//	Logger logger;
//	@Given("I am on the Justdial website again")
//	public void JustdailPage(){
//		driver= Hooks.driver;
//		wait=Hooks.wait;
//		logger=Hooks.logger;
//		act=new Actions(driver);
//		js=(JavascriptExecutor) driver;
//		driver.get(PropsLoader.URL);
//		driver.manage().window().maximize();
//	}
//
//	@When("I navigate to the {string} category")
//	public void i_navigate_to_the_category(String string) {
//		js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
//		WebElement fitnessElem=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@aria-controls='panel4' and @id='tab4']")));
//		js.executeScript("arguments[0].scrollIntoView()",fitnessElem);
//		js.executeScript("window.scrollTo(0, -50);");
//		fitnessElem.click();
//		logger.info("scrolling to the categories..");
//	}
//
//	@When("I then select the {string} sub-category")
//	public void i_then_select_the_sub_category(String string) {
//	    wait.until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Gyms"))).click();
//		logger.info("Selecting Zym substring..");
//	}
//
//	@Then("I should be able to retrieve all displayed sub-menu items")
//	public void i_should_be_able_to_retrieve_all_displayed_sub_menu_items() {
//	    List<WebElement> zymnames=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//h2")));
//		int n=zymnames.size();
//		logger.info("Printing the names of Zyms");
//		for(int i=0;i<Math.max(10,n);i++){
//			try {
//				WebElement elem=zymnames.get(i);
//				System.out.println(elem.getText());
//				logger.info(elem.getText());
//			}
//			catch(Exception e){
//				zymnames=driver.findElements(By.xpath("//h2"));
//				WebElement elem=zymnames.get(i);
//				System.out.println(elem.getText());
//				logger.info(elem.getText());
//			}
//		}
//	}
//}
