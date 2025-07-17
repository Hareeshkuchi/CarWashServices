package runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "@target/rerun.txt", // <--- IMPORTANT: Points to the generated rerun file
        glue = "stepdefinitions",       // Path to your step definition classes (same as original runner)
        plugin = {
                "pretty",
                "html:test-output/cucumber-reports/rerun-html-report.html" // Optional: Generate a separate report for reruns
//                ,"json:target/cucumber-reports/rerun.json"
//                ,"junit:target/cucumber-reports/rerun.xml"
        },
        monochrome = true
)
public class FailedScenariosRunner {
    // This runner will execute only the scenarios listed in rerun.txt
}
