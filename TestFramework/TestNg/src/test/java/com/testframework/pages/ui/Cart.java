package com.testframework.pages.ui;

import com.testframework.corelibrary.ui.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Cart {
    public RemoteWebDriver driver;

    //Initialises selenium webdriver using constructor so that field locators of the page are initialised
    public Cart(RemoteWebDriver driver)
    {
        this.driver=driver;
    }
    public void waitForCartPage() {
        SeleniumMethods.waitForElement(driver, cartPage, 30);
    }

    public String getProductDescription(int productNumber) throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement prodList=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, productList);
        WebElement shopCartItem=prodList.findElements(By.tagName("shop-cart-item")).get(productNumber);
        WebElement nameSection=SeleniumMethods.getElementInShadowDom(driver, shopCartItem, By.cssSelector("div[class='name']"));
        return nameSection.findElement(By.tagName("a")).getText();
    }

    public String getProductQty(int productNumber) throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement prodList=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, productList);
        WebElement shopCartItem=prodList.findElements(By.tagName("shop-cart-item")).get(productNumber);
        WebElement prodDetailsSection=SeleniumMethods.getElementInShadowDom(driver, shopCartItem, By.cssSelector("div[class='detail']"));
        WebElement qtyCombo=prodDetailsSection.findElement(By.id("quantitySelect"));
        Select select = new Select(qtyCombo);
        WebElement option = select. getFirstSelectedOption();
        return option. getText();
    }

    public String getProductPrice(int productNumber) throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement prodList=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, productList);
        WebElement shopCartItem=prodList.findElements(shopCartItemList).get(productNumber);
        WebElement prodDetailsSection=SeleniumMethods.getElementInShadowDom(driver, shopCartItem, detailsSection);
        WebElement priceOfProduct=prodDetailsSection.findElement(By.cssSelector("div[class='price']"));
        return priceOfProduct.getText();
    }

    public float getTotalPriceFromcart() throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement checkOutBox=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, checkoutBox);
        WebElement totalPriceInCart=checkOutBox.findElement(priceInCart);
        String priceInFloat= totalPriceInCart.getText().substring(1);
        return Float.parseFloat(priceInFloat);
    }

    public void removeProductFromCart(int productNumber) throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement prodList=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, productList);
        WebElement shopCartItem=prodList.findElements(shopCartItemList).get(productNumber);
        WebElement prodDetailsSection=SeleniumMethods.getElementInShadowDom(driver, shopCartItem, detailsSection);
        prodDetailsSection.findElement(removeProduct).click();
    }

    public void clickCheckoutBtn() throws Exception {
        WebElement shopCartSection=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(cartPage), shopCart);
        WebElement checkOutBox=SeleniumMethods.getElementInShadowDom(driver, shopCartSection, checkoutBox);
        checkOutBox.findElement(checkoutBtn).click();
    }

    private By cartPage=By.cssSelector("shop-app[page='cart']");
    private By shopCart=By.cssSelector("shop-cart[name='cart']");
    private By shopCartItemList=By.tagName("shop-cart-item");
    private By productList=By.cssSelector("div[class='list']");
    private By checkoutBox=By.cssSelector("div[class='checkout-box']");
    private By detailsSection=By.cssSelector("div[class='detail']");
    private By priceInCart=By.cssSelector("span[class='subtotal']");
    private By removeProduct=By.cssSelector("paper-icon-button[class='delete-button']");

    private By checkoutBtn=By.tagName("shop-button");
}
