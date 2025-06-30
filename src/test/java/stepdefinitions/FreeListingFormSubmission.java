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

public class FreeListingFormSubmission {
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	@Given("I am on the Justdial website's {string} page")
	public void i_am_on_the_justdial_website_s_page(String string) {
		driver= DriverManager.driver;
//		driver=DriverManager.getDriver();
		wait=new WebDriverWait(driver,Duration.ofSeconds(10));
		act=new Actions(driver);
		js=(JavascriptExecutor) driver;
		PropsLoader.loadProps();
		driver.get(PropsLoader.URL);
		driver.manage().window().maximize();
		driver.findElement(By.xpath("//*[@class='jsx-50d08bd883f6f57 headnav_item_text pl-5 font14 fw400 color111' and text()='Free Listing']")).click();

	}

	@When("I enter an {string} like {string}")
	public void i_enter_an_like(String string, String string2) {
		String errorMsg="No error found";
		try{
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='modal_modal__zB_6A styles_modal__fSE4U']")));
			driver.findElement(By.xpath("//input[@aria-label='Enter Mobile Number' and @id='4']")).sendKeys("123");
			errorMsg=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='undefined entermobilenumber_error__text__uPM09']"))).getText();
		}
		catch (Exception e){
			WebElement phoneInput=driver.findElement(By.xpath("//input[@aria-label='Enter Mobile Number' and @id='1']"));
			js.executeScript("arguments[0].scrollIntoView90",phoneInput);
			phoneInput.sendKeys(string2);
		}

	}

	@When("I click the {string} button")
	public void i_click_the_button(String string) {
//	    driver.findElement(By.xpath("//button[@aria-label='Create FREE Account']")).click();
		driver.findElement(By.xpath("//*[@class='primarybutton undefined' and @aria-label='Start Now']")).click();
	}

	@Then("I should see an {string} related to the invalid phone number")
	public String i_should_see_an_related_to_the_invalid_phone_number(String string) {
	   String error=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@class='undefined entermobilenumber_error__text__uPM09']"))).getText();
		System.out.println(error);
	   return error;
	}



}
