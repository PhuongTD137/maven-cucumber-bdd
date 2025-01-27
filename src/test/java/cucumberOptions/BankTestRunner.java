package cucumberOptions;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/bankGuru/features", glue = "bank.stepDefinitions", monochrome = true, plugin = { "pretty", "html:target/site/cucumber-report-default",
		"json:target/site/cucumber_bankguru.json" }, snippets = SnippetType.CAMELCASE, tags = { "@Customer" })
public class BankTestRunner {

}
