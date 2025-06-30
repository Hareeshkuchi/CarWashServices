package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class definition1 {
    @Given("User is on the login page")
    public void user_is_on_the_login_page() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("given..");
    }
    @When("User enters {string} and {string}")
    public void user_enters_and(String string, String string2) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("when1..");

    }
    @When("Clicks the login button")
    public void clicks_the_login_button() {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Given2..");
    }
    @Then("User should be on {string}")
    public void user_should_be_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        System.out.println("Then..");
    }

}
