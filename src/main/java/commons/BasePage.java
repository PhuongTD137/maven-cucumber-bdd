package commons;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

//import pageUIs.nopCommerce.user.BasePageUI;

public class BasePage { // Các hàm trong class này chỉ cho kế thừa dùng public

	public static BasePage getBasePageObject() {
		return new BasePage();
	}

	// Các hàm tương tác với trình duyệt
	public void openPageUrl(WebDriver driver, String pageUrl) {
		driver.get(pageUrl);
	}

	public String getPageTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public String getPageUrl(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public String getPageSourceCode(WebDriver driver) {
		return driver.getPageSource();
	}

	public void backToPage(WebDriver driver) {
		driver.navigate().back();
	}

	public void forwardToPage(WebDriver driver) {
		driver.navigate().forward();
	}

	public void refreshCurrentPage(WebDriver driver) {
		driver.navigate().refresh();
	}

	public Alert waitForAlertPresence(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		return explicitWait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(WebDriver driver) {
		waitForAlertPresence(driver).accept();
	}

	public void cancelAlert(WebDriver driver) {
		waitForAlertPresence(driver).dismiss();
	}

	public String getAlertText(WebDriver driver) {
		return waitForAlertPresence(driver).getText();
	}

	public void sendKeyToAlert(WebDriver driver, String textValue) {
		waitForAlertPresence(driver).sendKeys(textValue);
	}

	public void getCurrentWindowHandle(WebDriver driver) {
		driver.getWindowHandle();
	}

	public void switchWindowByID(WebDriver driver, String windowID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (id.equals(windowID)) {
				driver.switchTo().window(id);
				break;
			}
		}
	}

	public void switchWindowByTitle(WebDriver driver, String tabTitle) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			driver.switchTo().window(id);
			String actualTitle = driver.getTitle();
			if (actualTitle.equals(tabTitle)) {
				break;
			}
		}
	}

	public void closeAllTabWithoutParent(WebDriver driver, String parentID) {
		Set<String> allWindowIDs = driver.getWindowHandles();
		for (String id : allWindowIDs) {
			if (!id.equals(parentID)) {
				driver.switchTo().window(id);
				driver.close();
			}
			driver.switchTo().window(parentID);
		}
	}

	// Các hàm tương tác với element

	private By getByLocator(String locatorType) {
		By by = null;
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath")) {
			by = By.xpath(locatorType.substring(6));
		} else if (locatorType.startsWith("CSS=") || locatorType.startsWith("Css=") || locatorType.startsWith("css=")) {
			by = By.cssSelector(locatorType.substring(4));
		} else if (locatorType.startsWith("ID=") || locatorType.startsWith("Id=") || locatorType.startsWith("id=")) {
			by = By.id(locatorType.substring(3));
		} else if (locatorType.startsWith("NAME=") || locatorType.startsWith("Name=") || locatorType.startsWith("name=")) {
			by = By.name(locatorType.substring(5));
		} else if (locatorType.startsWith("CLASSNAME=") || locatorType.startsWith("ClassName=") || locatorType.startsWith("Classname=") || locatorType.startsWith("classname=")) {
			by = By.className(locatorType.substring(10));
		} else {
			throw new RuntimeException("Locator By is not supported");
		}
		return by;
	}

	private String getDynamicXpath(String locatorType, String... dynamicValues) {
		if (locatorType.startsWith("XPATH=") || locatorType.startsWith("XPath=") || locatorType.startsWith("Xpath=") || locatorType.startsWith("xpath=")) {
			locatorType = String.format(locatorType, (Object[]) dynamicValues);
		}
		return locatorType;
	}

	private WebElement getWebElement(WebDriver driver, String locatorType) {
		return driver.findElement(getByLocator(locatorType));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType) {
		return driver.findElements(getByLocator(locatorType));
	}

	protected List<WebElement> getListWebElement(WebDriver driver, String locatorType, String... dynamicValues) {
		return driver.findElements(getByLocator(getDynamicXpath(locatorType, dynamicValues)));
	}

	public void clickToElement(WebDriver driver, String locatorType) {
		if (driver.toString().equals("internet explorer")) {
			clickToElementByJS(driver, locatorType);
			sleepInSecond(3);
		} else {
			this.getWebElement(driver, locatorType).click();
		}
	}

	public void clickToElement(WebDriver driver, String locatorType, String... dynamicValues) {
		if (driver.toString().equals("internet explorer")) {
			clickToElementByJS(driver, getDynamicXpath(locatorType, dynamicValues));
			sleepInSecond(3);
		} else {
			this.getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).click();
		}
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue) {
		WebElement element = getWebElement(driver, locatorType);
		element.clear();
		element.sendKeys(textValue);
	}

	public void sendKeyToElement(WebDriver driver, String locatorType, String textValue, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		element.clear();
		element.sendKeys(textValue);
	}

	public void clearValueInElementByPressKey(WebDriver driver, String locatorType) {
		WebElement element = this.getWebElement(driver, locatorType);
		element.sendKeys(Keys.chord(Keys.CONTROL, "a", Keys.DELETE));
	}

	public String getElementText(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).getText();
	}

	public String getElementText(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getText();
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem) {
		new Select(getWebElement(driver, locatorType)).selectByVisibleText(textItem);
	}

	public void selectItemInDefaultDropdown(WebDriver driver, String locatorType, String textItem, String... dynamicValues) {
		new Select(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))).selectByVisibleText(textItem);
	}

	public String getSelectedItemDefaultDropdown(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isDropdownMultiple(WebDriver driver, String locatorType) {
		Select select = new Select(getWebElement(driver, locatorType));
		return select.isMultiple();
	}

	public void selectItemInCustomDropdown(WebDriver driver, String parentlocatorType, String childlocatorType, String expectedTextItem) {
		clickToElement(driver, parentlocatorType);
		sleepInSecond(1);

		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		List<WebElement> allItems = explicitWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByLocator(childlocatorType)));

		for (WebElement item : allItems) {
			if (item.getText().trim().equals(expectedTextItem)) {
				// JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
				((JavascriptExecutor) driver).executeScript("argument[0].scrollIntoView(true);", item);
				sleepInSecond(1);
				item.click();
				break;
			}
		}
	}

	protected void sleepInSecond(long timeInSecond) {
		try {
			Thread.sleep(timeInSecond * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName) {
		return getWebElement(driver, locatorType).getAttribute(attributeName);
	}

	public String getElementAttribute(WebDriver driver, String locatorType, String attributeName, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).getAttribute(attributeName);
	}

	public String getElementCssValue(WebDriver driver, String locatorType, String propertyName) {
		return getWebElement(driver, locatorType).getCssValue(propertyName);
	}

	public String getHexaColorFromRGBA(String rgbaValue) {
		return Color.fromString(rgbaValue).asHex();
	}

	public int getElementSize(WebDriver driver, String locatorType) {
		return getListWebElement(driver, locatorType).size();
	}

	public int getElementSize(WebDriver driver, String locatorType, String... dynamicValues) {
		return getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).size();
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void checkToDefaultCheckboxRadio(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (!element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType) {
		WebElement element = getWebElement(driver, locatorType);
		if (element.isSelected()) {
			element.click();
		}
	}

	public void uncheckToDefaultCheckbox(WebDriver driver, String locatorType, String... dynamicValues) {
		WebElement element = getWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		if (element.isSelected()) {
			element.click();
		}
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isDisplayed();
	}

	public boolean isElementDisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		return getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)).isDisplayed();
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, locatorType);
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isElementUndisplayed(WebDriver driver, String locatorType, String... dynamicValues) {
		overrideImplicitTimeout(driver, shortTimeout);
		List<WebElement> elements = getListWebElement(driver, getDynamicXpath(locatorType, dynamicValues));
		overrideImplicitTimeout(driver, longTimeout);
		if (elements.size() == 0) {
			return true;
		} else if (elements.size() > 0 && !elements.get(0).isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public void overrideImplicitTimeout(WebDriver driver, long timeout) {
		driver.manage().timeouts().implicitlyWait(timeout, TimeUnit.SECONDS);
	}

	public boolean isElementEnabled(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isEnabled();
	}

	public boolean isElementSelected(WebDriver driver, String locatorType) {
		return getWebElement(driver, locatorType).isSelected();
	}

	public void switchToFrameIframe(WebDriver driver, String locatorType) {
		driver.switchTo().frame(getWebElement(driver, locatorType));
	}

	public void switchToDefaultContent(WebDriver driver) {
		driver.switchTo().defaultContent();
	}

	public void hoverMouseToElement(WebDriver driver, String locatorType) {
		Actions action = new Actions(driver);
		action.moveToElement(getWebElement(driver, locatorType)).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, locatorType), key).perform();
	}

	public void pressKeyToElement(WebDriver driver, String locatorType, Keys key, String... dynamicValues) {
		Actions action = new Actions(driver);
		action.sendKeys(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)), key).perform();
	}

	public void scrollToBottomPage(WebDriver driver) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void highlightElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		WebElement element = getWebElement(driver, locatorType);
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", "border: 2px solid red; border-style: dashed;");
		sleepInSecond(1);
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", originalStyle);
	}

	public void clickToElementByJS(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].click();", getWebElement(driver, locatorType));
	}

	public void scrollToElement(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locatorType));
	}

	public void removeAttributeInDOM(WebDriver driver, String locatorType, String attributeRemove, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
	}

	public boolean areJQueryAndJSLoadedSuccess(WebDriver driver) {
		WebDriverWait explicitWait = new WebDriverWait(driver, 30);
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;

		ExpectedCondition<Boolean> jQueryLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				try {
					return ((Long) jsExecutor.executeScript("return jQuery.active") == 0);
				} catch (Exception e) {
					return true;
				}
			}
		};

		ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return jsExecutor.executeScript("return document.readyState").toString().equals("complete");
			}
		};

		return explicitWait.until(jQueryLoad) && explicitWait.until(jsLoad);
	}

	public String getElementValidationMessage(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		return (String) jsExecutor.executeScript("return arguments[0].validationMessage;", getWebElement(driver, locatorType));
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", getWebElement(driver, locatorType));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public boolean isImageLoaded(WebDriver driver, String locatorType, String... dynamicValues) {
		JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		boolean status = (boolean) jsExecutor.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0",
				getWebElement(driver, getDynamicXpath(locatorType, dynamicValues)));
		if (status) {
			return true;
		} else {
			return false;
		}
	}

	public void waitForElementVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(locatorType)));
	}

	public void waitForElementVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(locatorType)));
	}

	public void waitForAllElementsVisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
	}

	/*
	 * Wait for element undisplayed in DOM or not in DOM and override implicit timeout
	 */
	public void waitForElementUndisplayed(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, shortTimeout);
		overrideImplicitTimeout(driver, shortTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(locatorType)));
		overrideImplicitTimeout(driver, longTimeout);
	}

	public void waitForElementInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfElementLocated(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, locatorType)));
	}

	public void waitForAllElementsInvisible(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.invisibilityOfAllElements(getWebElement(driver, getDynamicXpath(locatorType, dynamicValues))));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(locatorType)));
	}

	public void waitForElementClickable(WebDriver driver, String locatorType, String... dynamicValues) {
		WebDriverWait explicitWait = new WebDriverWait(driver, longTimeout);
		explicitWait.until(ExpectedConditions.elementToBeClickable(getByLocator(getDynamicXpath(locatorType, dynamicValues))));
	}

	public Set<Cookie> getAllCookies(WebDriver driver) {
		return driver.manage().getCookies();
	}

	public void setCookies(WebDriver driver, Set<Cookie> cookies) {
		for (Cookie cookie : cookies) {
			driver.manage().addCookie(cookie);
		}
		sleepInSecond(3);
	}

	public boolean isRadioButtonChecked(WebDriver driver, String labelName) {
		// TODO Auto-generated method stub
		return false;
	}

	private long longTimeout = GlobalConstants.LONG_TIMEOUT;
	private long shortTimeout = GlobalConstants.SHORT_TIMEOUT;

}
