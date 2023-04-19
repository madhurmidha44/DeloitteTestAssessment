package com.testframework.pages.ui;

import com.testframework.corelibrary.ui.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class Checkout {
    public RemoteWebDriver driver;

    public Checkout(RemoteWebDriver driver)
    {
        this.driver=driver;
    }

    public void waitForCheckoutPage() {
        SeleniumMethods.waitForElement(driver, checkoutPage, 30);
    }

    public String getProductDescriptionCheckoutPage(int productNumber) throws Exception {
        WebElement shopCheckoutSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(checkoutPage), shopCheckout);
        WebElement formPostSection=SeleniumMethods.getElementInShadowDom(driver, shopCheckoutSection, formPost);
        WebElement orderList=formPostSection.findElements(By.cssSelector("div[class='row order-summary-row']")).get(productNumber);
        WebElement prodDescription=orderList.findElement(By.cssSelector("div[class='flex']"));
        return prodDescription.getText();
    }

    public float getProductPriceCheckoutPage(int productNumber) throws Exception {
        WebElement shopCheckoutSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(checkoutPage), shopCheckout);
        WebElement formPostSection=SeleniumMethods.getElementInShadowDom(driver, shopCheckoutSection, formPost);
        WebElement orderList=formPostSection.findElements(By.cssSelector("div[class='row order-summary-row']")).get(productNumber);
        WebElement prodPrice=orderList.findElements(By.tagName("div")).get(1);
        String priceString= prodPrice.getText().substring(1);
        return Float.parseFloat(priceString);
    }

    public float getTotalPriceCheckoutPage() throws Exception {
        WebElement shopCheckoutSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(checkoutPage), shopCheckout);
        WebElement formPostSection=SeleniumMethods.getElementInShadowDom(driver, shopCheckoutSection, formPost);
        WebElement totalPriceRow=formPostSection.findElement(By.cssSelector("div[class='row total-row']"));
        WebElement prodPrice=totalPriceRow.findElements(By.tagName("div")).get(1);
        String priceString= prodPrice.getText().substring(1);
        return Float.parseFloat(priceString);
    }

    private By checkoutPage=By.cssSelector("shop-app[page='checkout']");
    private By shopCheckout=By.cssSelector("shop-checkout[name='checkout']");
    private By productList=By.cssSelector("div[class='list']");
    private By formPost=By.cssSelector("form[method='post']");
}
