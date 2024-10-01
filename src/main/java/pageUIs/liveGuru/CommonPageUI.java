package pageUIs.liveGuru;

public class CommonPageUI {
	public static final String DYNAMIC_LINK = "xpath=//ul//a[text()='%s']";
	public static final String DYNAMIC_TEXTBOX = "xpath=//td[text()='%s']/following-sibling::td/input";
	// public static final String DYNAMIC_RADIO_BUTTON = "xpath=//td[text()='%s']/following-sibling::td/input[@value='%s']";
	public static final String DYNAMIC_RADIO_BUTTON = "xpath=//input[@type='radio' and @value='%s']";
	public static final String DYNAMIC_TEXTAREA = "xpath=//td[text()='%s']/following-sibling::td/textarea";
	public static final String DYNAMIC_BUTTON = "xpath=//td/input[@value='%s']";
	public static final String DYNAMIC_SUCCESS_MESSAGE = "xpath=//tbody//p[text()='%s']";
	public static final String DYNAMIC_VALUE_BY_ROW_NAME = "xpath=//tr/td[text()='%s']/following-sibling::td";
}
