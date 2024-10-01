package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.RegisterPageUI;

public class RegisterPageObject extends BasePage {
	WebDriver driver;

	public RegisterPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void inputToFirstNameTextBox(String firstName) {
		waitForElementVisible(driver, RegisterPageUI.FIRST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.FIRST_NAME_TEXTBOX, firstName);
	}

	public void inputToLastNameTextBox(String lastName) {
		waitForElementVisible(driver, RegisterPageUI.LAST_NAME_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.LAST_NAME_TEXTBOX, lastName);
	}

	public void inputToEmailTextBox(String emailAddress) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_TEXTBOX, emailAddress);
	}

	public void inputToPasswordTextBox(String password) {
		waitForElementVisible(driver, RegisterPageUI.PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.PASSWORD_TEXTBOX, password);
	}

	public void inputToConfirmPasswordTextBox(String password) {
		waitForElementVisible(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.CONFIRM_PASSWORD_TEXTBOX, password);
	}

	public MyAccountPageObject clickRegisterButton() {
		waitForElementClickable(driver, RegisterPageUI.REGISTER_BUTTON);
		clickToElement(driver, RegisterPageUI.REGISTER_BUTTON);
		return PageGeneratorManager.getMyAccountPage(driver);
	}

	public void inputToEmailID(String emailID) {
		waitForElementVisible(driver, RegisterPageUI.EMAIL_ID_TEXTBOX);
		sendKeyToElement(driver, RegisterPageUI.EMAIL_ID_TEXTBOX, emailID);
	}

	public void clickSubmitButton() {
		waitForElementClickable(driver, RegisterPageUI.SUBMIT_BUTTON);
		clickToElement(driver, RegisterPageUI.SUBMIT_BUTTON);
	}

	public String getUsername() {
		waitForElementVisible(driver, RegisterPageUI.ACCESS_EMAIL_TEXT);
		return getElementText(driver, RegisterPageUI.ACCESS_EMAIL_TEXT);
	}

	public String getPassword() {
		waitForElementVisible(driver, RegisterPageUI.ACCESS_PASSWORD_TEXT);
		return getElementText(driver, RegisterPageUI.ACCESS_PASSWORD_TEXT);
	}

	public void openLoginPage(String url) {
		openPageUrl(driver, url);
	}
}
