package bank.stepDefinitions;

import org.openqa.selenium.WebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import cucumberOptions.Hooks;
import pageObjects.liveGuru.LoginPageObject;
import pageObjects.liveGuru.PageGeneratorManager;

public class LoginPageSteps {
	LoginPageObject loginPage;
	WebDriver driver;
	RegisterPageSteps registerStep;
	// static String loginUrl;
	TestContext testContext;

	public LoginPageSteps(TestContext testContext) {
		this.driver = Hooks.openAndQuitBrowser();
		this.testContext = testContext;
		loginPage = PageGeneratorManager.getLoginPage(driver);
	}

	@Given("^Get Login page url$")
	public void getLoginPageUrl() {
		// loginUrl = loginPage.getPageUrl(driver);
		testContext.getDataContext().setDataContext(Context.LOGIN_URL, loginPage.getPageUrl(driver));
	}

	@When("^Open Register page$")
	public void openRegisterPage() {
		loginPage.clickToHereLink();
	}

	@When("^Submit valid infor to Login form$")
	public void submitValidInforToLoginForm() {
		// loginPage.inputToEmailAddressTextbox(RegisterPageSteps.username);
		// loginPage.inputToPasswordTextbox(RegisterPageSteps.password);
		loginPage.inputToEmailAddressTextbox(testContext.getDataContext().getDataContext(Context.USER_ID).toString());
		loginPage.inputToPasswordTextbox(testContext.getDataContext().getDataContext(Context.PASSWORD).toString());
		loginPage.clickLoginButton();
	}
}
