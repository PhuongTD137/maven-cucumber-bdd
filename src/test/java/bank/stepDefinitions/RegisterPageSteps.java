package bank.stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.liveGuru.PageGeneratorManager;
import pageObjects.liveGuru.RegisterPageObject;
import utilities.DataHelper;

public class RegisterPageSteps {
	WebDriver driver;
	RegisterPageObject registerPage;
	DataHelper dataTest;
	LoginPageSteps loginStep;
	// static String username, password;
	TestContext testContext;

	public RegisterPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		registerPage = PageGeneratorManager.getRegisterPage(driver);
		dataTest = DataHelper.getDataHelper();
	}

	@When("^Input Email$")
	public void inputEmail() {
		registerPage.inputToEmailID(dataTest.getEmailAddress());
	}

	@When("^Click Submit$")
	public void clickSubmit() {
		registerPage.clickSubmitButton();
	}

	@Then("^Get UserID and Password infor$")
	public void getUserIDAndPasswordInfor() {
		// username = registerPage.getUsername();
		// password = registerPage.getPassword();
		testContext.getDataContext().setDataContext(Context.USER_ID, registerPage.getUsername());
		testContext.getDataContext().setDataContext(Context.PASSWORD, registerPage.getPassword());
	}

	@When("^Return to Login page$")
	public void returnToLoginPage() {
		// registerPage.openLoginPage(LoginPageSteps.loginUrl);
		registerPage.openLoginPage(testContext.getDataContext().getDataContext(Context.LOGIN_URL).toString());
	}
}
