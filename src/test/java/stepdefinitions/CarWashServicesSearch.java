package stepdefinitions;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utilities.DriverManager;
import utilities.PropsLoader;

public class CarWashServicesSearch {
	
	WebDriver driver;
	WebDriverWait wait;
	Actions act;
	JavascriptExecutor js;
	@Given("I am on the Justdial website")
	public void i_am_on_the_justdial_website() {
	    driver=DriverManager.getDriver();
	    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    wait=new WebDriverWait(driver,Duration.ofSeconds(10));
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

	@When("I specify the Location as detected location")
	public void i_specify_my_as() throws InterruptedException {
//		driver.findElement(By.id("city-auto-sug")).sendKeys(string2);
//		act.click(driver.findElement(By.id("city-auto-sug")));
//		Thread.sleep(2000);
		driver.findElement(By.id("city-auto-sug")).click();
//		act.click(driver.findElement(By.id("city-auto-sug"))).click().perform();
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='location_text font14 fw400 color007']"))).click();
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='location_text font14 fw400 color007']")));
	}
	
	@When("I search for {string}")
	public void i_search_for(String string) throws InterruptedException {
	    driver.findElement(By.id("main-auto")).sendKeys(string);
	    act.click(driver.findElement(By.id("main-auto")));
	    Thread.sleep(2000);
		driver.findElement(By.id("react-autowhatever-main-auto-suggest--item-0")).click();
//	    act.moveToElement(driver.findElement(By.id("react-autowhatever-main-auto-suggest--item-0"))).click();

	}

	@When("I apply filters for Rating > {string} stars and Customer Votes > {string}")
	public void i_apply_filters_for_and(String string, String string2) throws InterruptedException {

		try {
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@onclick='closePopUp(\'best_deal_div\');']"))).click();
		}
		catch(Exception e) {
			System.out.println("No next popup found");
		}
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='jsx-679474515 resfilter_item gray_whitefill_animat font15 color111 fw500 all_filter_container']"))).click();
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[ @aria-label='4.0+']")));
		Thread.sleep(2000);
		WebElement rating=wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[ @aria-label='"+string+".0+']")));
		js.executeScript("arguments[0].scrollIntoView()",rating);
		rating.click();
		driver.findElement(By.xpath("//span[ @aria-label='Rating']")).click();
//		driver.findElement(By.id("side_menu_close")).click();
		driver.findElement(By.xpath("//button[text()='Apply Filters']")).click();
	}

	@Then("I should see a list of {string} car washing services")
	public void i_should_see_a_list_of_car_washing_services(String string) throws InterruptedException {
//		try{
//			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']"))).click();
//		}
//		catch(Exception e){
//			System.out.println("no popup found after sorting");
//		}
		List<WebElement> servicesList=wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='jsx-7cbb814d75c86232 resultbox ']")));
		int n=Integer.parseInt(string);
		for(int i=0;i<n;i++) {
			WebElement service;
			String name="";
			String phone="";
			try {
				service=servicesList.get(i);
				js.executeScript("arguments[0].scrollIntoView()",service);
				 name=service.findElement(By.xpath(".//h3[@class='jsx-7cbb814d75c86232 resultbox_title_anchor font22 fw500 color111 line_clamp_1 ']")).getText();
				 try{
					 service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232  greenfill_animate callbutton font15 fw500 colorFFF mr-15 ']")).click();
					 Thread.sleep(2000);
					 phone=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='font20 fw600 colorFFF mt-15']"))).getText();
					 driver.findElement(By.xpath("//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")).click();
				 }
				 catch(Exception e){
					phone= service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232 callcontent callNowAnchor']")).getText();

				 }
			}
			catch(Exception e) {
				servicesList=driver.findElements(By.xpath("//div[@class='jsx-7cbb814d75c86232 resultbox ']"));
				service=servicesList.get(i);
				js.executeScript("arguments[0].scrollIntoView()",service);
				name=service.findElement(By.xpath(".//h3[@class='jsx-7cbb814d75c86232 resultbox_title_anchor font22 fw500 color111 line_clamp_1 ']")).getText();
				try{
					service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232  greenfill_animate callbutton font15 fw500 colorFFF mr-15 ']")).click();
					Thread.sleep(2000);
					phone=wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='font20 fw600 colorFFF mt-15']"))).getText();
					driver.findElement(By.xpath("//*[@class='jsx-dcde576cdf171c2a jd_modal_close jdicon']")).click();
				}
				catch(Exception ee){
					phone= service.findElement(By.xpath(".//*[@class='jsx-7cbb814d75c86232 callcontent callNowAnchor']")).getText();

				}
			}
			finally {
				System.out.println("Name: "+name);
				System.out.println("phone: "+phone);
			}
		}
	}



}
