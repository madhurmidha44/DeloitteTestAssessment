package com.testframework.corelibrary.ui;
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SeleniumMethods {

    public static void waitForElement(RemoteWebDriver driver, By by, int time)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(time));
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }


    public static void setOptionByText(RemoteWebDriver driver, WebElement element, String text) {

        Select selector = new Select(element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
        selector.selectByVisibleText(text);
    }

    public static String setOptiobByIndex(RemoteWebDriver driver, By by, int id) {

        Select selector = new Select(driver.findElement(by));
        selector.selectByIndex(id);

        String selectedText = selector.getFirstSelectedOption().getText();
        return selectedText;
    }

    public static void runJavascriptForScrollInToView(RemoteWebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
        }
    }

    public static Boolean isElementExists(RemoteWebDriver driver, By by) {
        try {
            int cnt = driver.findElements(by).size();
            if (cnt > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            return false;
        }

    }

    public static void clickUsingJavaScript(RemoteWebDriver driver, By by) throws Exception {

        try {
            JavascriptExecutor executor = (JavascriptExecutor) driver;
            executor.executeScript("arguments[0].click();", driver.findElement(by));
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }



    public static Boolean isElementEnabled(RemoteWebDriver driver, By by) {
        if (driver.findElement(by).isEnabled()) {
            return true;
        } else {
            return false;
        }

    }

    public static void sendKeysMethod(RemoteWebDriver driver, By by, String data) {
        driver.findElement(by).clear();
        driver.findElement(by).sendKeys(data);
    }





    public static void clickMethod(RemoteWebDriver driver, By by) {
        driver.findElement(by).click();
    }

    public static String getTextMethod(RemoteWebDriver driver, By by) {
        return driver.findElement(by).getText();
    }

    public static String getAttributeMethod(RemoteWebDriver driver, By by, String attribute_name) {
        return driver.findElement(by).getAttribute(attribute_name);
    }

    public static Boolean compareStringAndRegularExpression(String pattern, String string_to_compare) {
        return Pattern.matches(pattern, string_to_compare);
    }

    public static void openNewTab() throws InterruptedException, AWTException {
        Robot r = new Robot();
        r.keyPress(KeyEvent.VK_CONTROL);
        r.keyPress(KeyEvent.VK_T);
        r.keyRelease(KeyEvent.VK_CONTROL);
        r.keyRelease(KeyEvent.VK_T);

        // driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL +
        // "t");
        //waitFor2Seconds();
    }

    public static void switchToWindow(RemoteWebDriver driver, int index_window) {
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(index_window));
    }

    public static Boolean validateCheckboxOrRadiobuttonIsChecked(RemoteWebDriver driver, By by) {
        return driver.findElement(by).isSelected();
    }



    public static int countOfWebElements(RemoteWebDriver driver, By by) throws Exception {
        return driver.findElements(by).size();
    }

    public static List<WebElement> listOfWebElements(RemoteWebDriver driver, By by) throws Exception {
        return driver.findElements(by);
    }

    public static void dragAndDropUsingActionClass(RemoteWebDriver driver, By from, By to) throws Exception {
        Actions action = new Actions(driver);
        action.clickAndHold(driver.findElement(from)).moveToElement(driver.findElement(to))
                .release(driver.findElement(to)).build();
    }

    public static void popupHandle(RemoteWebDriver driver) throws Exception {
        String currentHandle = driver.getWindowHandle();
        driver.switchTo().alert().accept();
        driver.switchTo().window(currentHandle);
    }

    public static void doubleClickUsingJavascript(RemoteWebDriver driver, By by) throws Exception {
        ((JavascriptExecutor) driver).executeScript("arguments[0].dblclick();", driver.findElement(by));

    }

    public static void doubleClickUsingActionClass(RemoteWebDriver driver, By by) throws Exception {
        Actions action = new Actions(driver);
        action.doubleClick(driver.findElement(by)).perform();
    }

    public static void mouseHoverUsingActionClass(RemoteWebDriver driver, By by) throws Exception {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(by)).perform();
    }




    public static WebElement retrieveWebelementFromByLocator(RemoteWebDriver driver, By by) throws Exception {
        return driver.findElement(by);
    }

    public static String getLocatorBasedOnElementAndPageObjectModel(String element, String page) throws Exception {
        Class classOfPage = Class.forName("pageObjectModel."+page);
        Object pageObj=classOfPage.newInstance();
        String valueOfLocatorOfElement=(String) classOfPage.getDeclaredField(element).get(pageObj);
        return valueOfLocatorOfElement;
    }

    public static WebElement getElementInShadowDom(RemoteWebDriver driver, WebElement parentShadowHost, By shadowElementLocator) throws Exception {
        WebElement shadowHost = parentShadowHost;
        Thread.sleep(1000);//Without this wait, Selenium was unstable somewhat
        SearchContext shadowRoot = shadowHost.getShadowRoot();
        WebElement shadowContent = shadowRoot.findElement(shadowElementLocator);
        return shadowContent;
    }

    public static WebElement getElementInNestedShadowDom(RemoteWebDriver driver, WebElement parentShadowHost, By firstShadowElementLocator, By nestedShadowElementLocator) throws Exception {
        /*SearchContext firstShadowRoot = (SearchContext) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot", parentShadowHost);
        WebElement elementInParentShadowDom=firstShadowRoot.findElement(firstShadowElementLocator);
        SearchContext secondShadowRoot = (SearchContext) ((JavascriptExecutor) driver).executeScript(
                "return arguments[0].shadowRoot", elementInParentShadowDom);
        WebElement elementInNestedShadowDom=secondShadowRoot.findElement(nestedShadowElementLocator);
        return elementInNestedShadowDom;*/

        //To implement shadow-dom functionality with Selenium command, its version should be 4.0.0 or more:
         WebElement shadowHost = parentShadowHost;
         SearchContext shadowRoot = shadowHost.getShadowRoot();
         WebElement shadowContent = shadowRoot.findElement (firstShadowElementLocator);
         return shadowContent;

    }

    public static void switchToIframe(RemoteWebDriver driver, String nameOrId) throws Exception {
        driver.switchTo().frame(nameOrId);
    }

    public static void switchToMainContentFromIframe(RemoteWebDriver driver, String nameOrId) throws Exception {
        driver.switchTo().defaultContent();
    }


}

