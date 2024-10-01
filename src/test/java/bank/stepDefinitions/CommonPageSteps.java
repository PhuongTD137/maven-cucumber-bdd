package bank.stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.liveGuru.CommonPageObject;
import pageObjects.liveGuru.PageGeneratorManager;
import utilities.DataHelper;

public class CommonPageSteps {
	WebDriver driver;
	CommonPageObject commonPage;
	String emailAddress;
	DataHelper dataTest;

	public CommonPageSteps() {
		driver = Hooks.openAndQuitBrowser();
		commonPage = PageGeneratorManager.getCommonObjectPage(driver);
		dataTest = DataHelper.getDataHelper();
		emailAddress = dataTest.getEmailAddress();
	}

	@Given("^Click \"([^\"]*)\" link$")
	public void clickLink(String linkName) {
		commonPage.clickToDynamicLink(linkName);
	}

	@When("^Input to \"([^\"]*)\" textbox with value \"([^\"]*)\"$")
	public void inputToTextboxWithValue(String fieldName, String textValue) {
		if (fieldName.equals("E-mail")) {
			textValue = emailAddress;
		}
		commonPage.inputToDynamicTextbox(fieldName, textValue);
	}

	@When("^Input to \"([^\"]*)\" textarea with value \"([^\"]*)\"$")
	public void inputToTextareaWithValue(String fieldName, String textValue) {
		commonPage.inputToDynamicTextarea(fieldName, textValue);
	}

	@When("^Input to radio button with value \"([^\"]*)\"$")
	public void inputToRadioButtonWithValue(String value) {
		commonPage.inputToDynamicRadioButton(value);
	}

	@When("^Click \"([^\"]*)\" button$")
	public void clickButton(String buttonName) {
		commonPage.clickToDynamicButton(buttonName);
	}

	@Then("^\"([^\"]*)\" message is displayed$")
	public void messageIsDisplayed(String message) {
		Assert.assertTrue(commonPage.isDynamicSuccessMessageDisplayed(message));
	}

	@Then("^\"([^\"]*)\" is displayed correctly with value \"([^\"]*)\"$")
	public void isDisplayedCorrectlyWithValue(String rowName, String expectedValue) {
		if (rowName.equals("Email")) {
			expectedValue = emailAddress;
		}
		Assert.assertEquals(commonPage.getDynamicRowValue(rowName), expectedValue);
	}

}
