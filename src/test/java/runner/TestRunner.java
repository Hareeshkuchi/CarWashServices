package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features", // Path to feature files
        glue = "stepdefinitions",    // Path to step definition classes
        plugin = {"pretty",          // Prints Gherkin steps to console
                "html:target/cucumber-reports/cucumber-html-report.html",
//                ,"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:"
        },
        monochrome = true                       // Makes console output more readable
        ,tags = "@Carwash"       // Runs scenarios with these tags
//        ,tags="@FreeList"
//        ,tags="@fitness"
)

public class TestRunner {
    // This class runs the Cucumber features
}