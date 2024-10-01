package facebook.stepDefinitions;

import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import cucumber.api.DataTable;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import io.github.bonigarcia.wdm.WebDriverManager;

public class FacebookSteps {
	WebDriver driver;

	@Before
	@Given("^Open Facebook Application$")
	public void openFacebookApplication() {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		driver.get("https://www.facebook.com");
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@After
	@And("^Close application$")
	public void closeApplication() {
		driver.quit();
	}

	@Then("^Input to Email textbox$")
	public void inputToEmailTextbox() {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys("autofc@gmail.com");
	}

	@And("^Input to Password textbox$")
	public void inputToPasswordTextbox() {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys("abc@1234");
	}

	@And("^Click Submit button$")
	public void clickSubmitButton() {
		// driver.findElement(By.name("login")).click();
	}

	// @Then("^Verify email textbox is displayed$")
	// public void verifyEmailTextboxIsDisplayed() {
	// Assert.assertTrue(driver.findElement(By.id("email")).isDisplayed());
	// }
	//
	// @And("^Verify password textbox is displayed$")
	// public void erifyPasswordTextboxIsDisplayed() {
	// Assert.assertTrue(driver.findElement(By.id("pass")).isDisplayed());
	// }

	@Then("^Input to Email textbox with \"([^\"]*)\"$")
	public void inputToEmailTextboxWith(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Then("^Input to Password textbox with \"([^\"]*)\"$")
	public void inputToPasswordTextboxWith(String password) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Then("^Input to Email textbox with ([^\"]*)$")
	public void inputToEmailTextbox(String email) {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
	}

	@Then("^Input to Password textbox with ([^\"]*)$")
	public void inputToPasswordTextbox(String password) {
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Then("^Input to Email textbox with \"([^\"]*)\" And Password textbox with \"([^\"]*)\"$")
	public void inputToEmailTextboxWithAndPasswordTextboxWith(String email, String password) throws Throwable {
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("email")).sendKeys(email);
		driver.findElement(By.id("pass")).sendKeys(password);
	}

	@Then("^Input to Email textbox and Password textbox$")
	public void inputToEmailTextboxAndPasswordTextbox(DataTable table) {
		List<Map<String, String>> user = table.asMaps(String.class, String.class);
		driver.findElement(By.id("email")).clear();
		driver.findElement(By.id("pass")).clear();
		driver.findElement(By.id("email")).sendKeys(user.get(0).get("Username"));
		driver.findElement(By.id("pass")).sendKeys(user.get(0).get("Password"));
	}

}
