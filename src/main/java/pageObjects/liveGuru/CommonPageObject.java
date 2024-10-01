package pageObjects.liveGuru;

import org.openqa.selenium.WebDriver;

import commons.BasePage;
import pageUIs.liveGuru.CommonPageUI;

public class CommonPageObject extends BasePage {
	WebDriver driver;

	public CommonPageObject(WebDriver driver) {
		this.driver = driver;
	}

	public void clickToDynamicLink(String linkName) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_LINK, linkName);
		clickToElement(driver, CommonPageUI.DYNAMIC_LINK, linkName);
	}

	public void inputToDynamicTextbox(String fieldName, String textValue) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTBOX, fieldName);
		if (fieldName.equals("Date of Birth")) {
			removeAttributeInDOM(driver, CommonPageUI.DYNAMIC_TEXTBOX, "type", fieldName);
		}
		sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTBOX, textValue, fieldName);
	}

	public void inputToDynamicTextarea(String fieldName, String textValue) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_TEXTAREA, fieldName);
		sendKeyToElement(driver, CommonPageUI.DYNAMIC_TEXTAREA, textValue, fieldName);
	}

	public void inputToDynamicRadioButton(String value) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, value);
		checkToDefaultCheckboxRadio(driver, CommonPageUI.DYNAMIC_RADIO_BUTTON, value);
	}

	public void clickToDynamicButton(String buttonName) {
		waitForElementClickable(driver, CommonPageUI.DYNAMIC_BUTTON, buttonName);
		clickToElement(driver, CommonPageUI.DYNAMIC_BUTTON, buttonName);
	}

	public boolean isDynamicSuccessMessageDisplayed(String message) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE, message);
		return isElementDisplayed(driver, CommonPageUI.DYNAMIC_SUCCESS_MESSAGE, message);
	}

	public String getDynamicRowValue(String rowName) {
		waitForElementVisible(driver, CommonPageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
		return getElementText(driver, CommonPageUI.DYNAMIC_VALUE_BY_ROW_NAME, rowName);
	}
}
