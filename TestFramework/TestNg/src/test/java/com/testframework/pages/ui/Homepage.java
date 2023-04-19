package com.testframework.pages.ui;

import com.testframework.corelibrary.ui.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

//This class represents all the fields & methods of 'Homepage' of the application
public class Homepage {
	public RemoteWebDriver driver;

	//Initialises selenium webdriver using constructor so that field locators of the page are initialised
	public Homepage(RemoteWebDriver driver)
	{
		this.driver=driver;
	}

	public void waitForHomepage() {
		SeleniumMethods.waitForElement(driver, homePage, 30);
	}
	public void waitForShopList() {
		SeleniumMethods.waitForElement(driver, shopListPage, 30);
	}

	public void clickMensOutwearLink() throws Exception {
		WebElement mensOutwearLink=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(homePage), mensOutwear);
		mensOutwearLink.click();
		SeleniumMethods.waitForElement(driver, shopListPage, 30);
	}

	public void clickLadiesOutwearLink() throws Exception {
		WebElement ladiesOutwearLink=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetail), ladiesOutwear);
		ladiesOutwearLink.click();
		SeleniumMethods.waitForElement(driver, shopListPage, 30);
	}

	public void clickMensTShirtsLink() throws Exception {
		WebElement mensTShirtLink=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetail), mensTShirt);
		mensTShirtLink.click();
		SeleniumMethods.waitForElement(driver, shopListPage, 30);
	}

	public void selectFleeceFullZipHoodie() throws Exception {
		WebElement shopList=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(shopListPage), listItems);
		WebElement fleeceFullZipHoodie=SeleniumMethods.getElementInShadowDom(driver, shopList, hoodie);
		SeleniumMethods.runJavascriptForScrollInToView(driver, fleeceFullZipHoodie);
		fleeceFullZipHoodie.click();
	}

	public void selectLadiesSonomaKnitJacket() throws Exception {
		WebElement shopList=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(shopListPage), listItems);
		WebElement ladiesSonomaJacket=SeleniumMethods.getElementInShadowDom(driver, shopList, jacket);
		SeleniumMethods.runJavascriptForScrollInToView(driver, ladiesSonomaJacket);
		ladiesSonomaJacket.click();
	}

	public void selectMensVintageHeatherTShirts() throws Exception {
		WebElement shopList=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(shopListPage), listItems);
		WebElement mensHeatherTshirt=SeleniumMethods.getElementInShadowDom(driver, shopList, tShirt);
		SeleniumMethods.runJavascriptForScrollInToView(driver, mensHeatherTshirt);
		mensHeatherTshirt.click();
	}
	private By homePage=By.xpath("//shop-app[@page='home']");
	private By productDetail=By.xpath("//shop-app[@page='detail']");
	private By mensOutwear=By.cssSelector("a[href='/list/mens_outerwear']");
	private By ladiesOutwear=By.cssSelector("a[href='/list/ladies_outerwear']");

	private By mensTShirt=By.cssSelector("a[href='/list/mens_tshirts']");
	private By shopListPage=By.xpath("//shop-app[@page='list']");
	private By listItems=By.cssSelector("shop-list[name='list']");
	private By hoodie=By.cssSelector("a[href='/detail/mens_outerwear/Fleece+Full-Zip+Hoodie']");
	private By jacket=By.cssSelector("a[href='/detail/ladies_outerwear/Ladies+Sonoma+Hybrid+Knit+Jacket']");
	private By tShirt=By.cssSelector("a[href='/detail/mens_tshirts/Men+s+Vintage+Heather+T-Shirt']");
}
