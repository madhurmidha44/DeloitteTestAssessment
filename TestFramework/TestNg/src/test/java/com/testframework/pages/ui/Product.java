package com.testframework.pages.ui;

import com.testframework.corelibrary.ui.SeleniumMethods;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Product {
    public RemoteWebDriver driver;

    //Initialises selenium webdriver using constructor so that field locators of the page are initialised
    public Product(RemoteWebDriver driver)
    {
        this.driver=driver;
    }

    public void waitForProductDetailsPage() {
        SeleniumMethods.waitForElement(driver, productDetailPage, 30);
    }

    public void selectSizeOfProduct(String size) throws Exception {
        WebElement productInfo=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetailPage), productDetail);
        WebElement prodInfoSection=SeleniumMethods.getElementInShadowDom(driver, productInfo, prodDetailSection);
        WebElement pickers=prodInfoSection.findElement(By.cssSelector("div[class='pickers']"));
        WebElement productSize=pickers.findElement(sizeOfProduct);
        SeleniumMethods.setOptionByText(driver, productSize, size);
    }

    public void selectQtyOfProduct(String qty) throws Exception {
        WebElement productInfo=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetailPage), productDetail);
        WebElement prodInfoSection=SeleniumMethods.getElementInShadowDom(driver, productInfo, prodDetailSection);
        WebElement pickers=prodInfoSection.findElement(By.cssSelector("div[class='pickers']"));
        WebElement productQty=pickers.findElement(qtyOfProduct);
        SeleniumMethods.setOptionByText(driver, productQty, qty);
    }

    public List<String> getNamePriceOfProdAndAddToCart() throws Exception {
        WebElement productInfo=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetailPage), productDetail);
        WebElement prodInfoSection=SeleniumMethods.getElementInShadowDom(driver, productInfo, prodDetailSection);
        String name=prodInfoSection.findElement(By.tagName("h1")).getText();
        String price=prodInfoSection.findElement(productPrice).getText();
        List<String> nameAndPrice=new ArrayList();
        nameAndPrice.add(name);
        nameAndPrice.add(price);
        prodInfoSection.findElement(addToCart).click();
        return nameAndPrice;
    }

    public void clickViewCartButton() throws Exception {
        WebElement shopCardDialog=SeleniumMethods.getElementInShadowDom(driver, driver.findElement(productDetailPage), By.cssSelector("shop-cart-modal[role='dialog']"));
        WebElement addToCart=SeleniumMethods.getElementInShadowDom(driver, shopCardDialog, viewCartBtn);
        addToCart.click();
    }

    public float getTotalPriceOfAllProducts(List<List<String>> combinedPrice)
    {
        float totalPrice=0;
        for(List<String> price:combinedPrice)
        {
            float qtyofProduct=Float.parseFloat(price.get(0));
            float pricePerProduct=Float.parseFloat(price.get(1).substring(1));
            totalPrice=totalPrice+(qtyofProduct*pricePerProduct);
        }
        return totalPrice;
    }

    public float getTotalPriceOfEachProduct(String qty, String pricePerProduct)
    {
        float totalPrice=0;
        float qtyofProduct=Float.parseFloat(qty);
        float price=Float.parseFloat(pricePerProduct.substring(1));
        totalPrice=totalPrice+(qtyofProduct*price);
        return totalPrice;
    }

    private By productDetailPage=By.xpath("//shop-app[@page='detail']");
    private By productDetail=By.cssSelector("shop-detail[name='detail']");
    private By prodDetailSection=By.cssSelector("div[class='detail']");
    private By productPrice=By.cssSelector("div[class='price']");
    private By addToCart=By.tagName("shop-button");
    private By qtyOfProduct=By.id("quantitySelect");
    private By sizeOfProduct=By.id("sizeSelect");

    private By viewCartBtn=By.cssSelector("shop-button[class='modal-button']");
}
