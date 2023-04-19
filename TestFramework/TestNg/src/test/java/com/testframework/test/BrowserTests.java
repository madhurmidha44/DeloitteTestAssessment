package com.testframework.test;
import com.relevantcodes.extentreports.LogStatus;
import com.testframework.pages.ui.Cart;
import com.testframework.pages.ui.Checkout;
import com.testframework.pages.ui.Homepage;
import com.testframework.pages.ui.Product;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Test cases for UI
public class BrowserTests extends TestBase {
	//Sample test case for demo purpose
	@Test
	public void addProductsAndValidateInCartAndCheckout() throws Exception {
		//Fetch URL from properties file
		String url=properties.getProperty("url");

		//Put the description of URL for the test case in the report
		test.log(LogStatus.INFO, "URL in test data is "+url);

		//Open browser
		driver.navigate().to(url);
		//Create page object as part of Page Object Model
		Homepage homepageObj=new Homepage(driver);
		homepageObj.waitForHomepage();
		test.log(LogStatus.INFO, "Homepage is launched");

		//Adding one M Fleece Full Zip Hoodie to cart
		homepageObj.clickMensOutwearLink();
		test.log(LogStatus.INFO, "Clicked Men's Outwear link");
		homepageObj.selectFleeceFullZipHoodie();
		test.log(LogStatus.INFO, "Select 'Fleece Full Zip Hoodie' product");
		Product productObj=new Product(driver);
		productObj.waitForProductDetailsPage();
		test.log(LogStatus.INFO, "Product details page is opened");
		String hoodieSize=properties.getProperty("hoodieSize");
		productObj.selectSizeOfProduct(properties.getProperty("hoodieSize"));
		test.log(LogStatus.INFO, "Selected size of Hoodie is "+hoodieSize);
		String qtyOfHoodie=properties.getProperty("hoodieQty");
		productObj.selectQtyOfProduct(qtyOfHoodie);
		test.log(LogStatus.INFO, "selected qty of Hoodie is "+qtyOfHoodie);
		List<String> namePriceOfHoodie=productObj.getNamePriceOfProdAndAddToCart();
		test.log(LogStatus.INFO, "Fetched name "+namePriceOfHoodie.get(0)+" & price "+namePriceOfHoodie.get(1)+" of Hoodie product in Product details page and then add product to cart");

		//Creating List to add prices of all products for calculation. List contains list which contains qty & price per product
		List<List<String>> combinedPrice=new ArrayList();
		//Adding qty & price of Hoodie to the combined List
		List<String> qtyPriceOfHoodie=new ArrayList();
		qtyPriceOfHoodie.add(qtyOfHoodie);
		qtyPriceOfHoodie.add(namePriceOfHoodie.get(1));
		combinedPrice.add(qtyPriceOfHoodie);
		test.log(LogStatus.INFO, "Added qty "+qtyOfHoodie+" & price "+namePriceOfHoodie.get(1)+" of Hoodie to the combined List which is used to add prices of all products for calculation");

		//Adding one M Ladies Sonoma Hybrid Knit Jacket
		homepageObj.clickLadiesOutwearLink();
		test.log(LogStatus.INFO, "Clicked Ladies Outwear link");
		homepageObj.waitForShopList();
		homepageObj.selectLadiesSonomaKnitJacket();
		test.log(LogStatus.INFO, "Select 'Ladies Sonoma Knit Jacket' product");
		productObj.waitForProductDetailsPage();
		test.log(LogStatus.INFO, "Product details page is opened");
		String sizeOfJacket=properties.getProperty("jacketSize");
		productObj.selectSizeOfProduct("L");
		test.log(LogStatus.INFO, "Selected size of Jacket is "+sizeOfJacket);
		String qtyOfJacket=properties.getProperty("JacketQty");
		productObj.selectQtyOfProduct(qtyOfJacket);
		test.log(LogStatus.INFO, "Selected qty of Jacket is "+qtyOfJacket);
		List<String> namePriceOfJacket=productObj.getNamePriceOfProdAndAddToCart();
		test.log(LogStatus.INFO, "Fetched name "+namePriceOfJacket.get(0)+" & price "+namePriceOfJacket.get(1)+" of Jacket product in Product details page and then add product to cart");
		//Adding qty & price of Jacket to the combined List
		List<String> qtyPriceOfJacket=new ArrayList();
		qtyPriceOfJacket.add(qtyOfJacket);
		qtyPriceOfJacket.add(namePriceOfJacket.get(1));
		combinedPrice.add(qtyPriceOfJacket);
		test.log(LogStatus.INFO, "Added qty "+qtyOfJacket+" & price "+namePriceOfJacket.get(1)+" of Jacket to the combined List which is used to add prices of all products for calculation");
		//Getting total price of Jacket product
		float totalPriceOfJacket=productObj.getTotalPriceOfEachProduct(qtyOfJacket, namePriceOfJacket.get(1));
		test.log(LogStatus.INFO, "Added total price of Jacket " +totalPriceOfJacket+ " so that it can be validated on Checkout page");

		//Adding two XL Men's Vintage Heather T-shirt
		homepageObj.clickMensTShirtsLink();
		test.log(LogStatus.INFO, "Clicked Mens' Tshirt- link");
		homepageObj.waitForShopList();
		homepageObj.selectMensVintageHeatherTShirts();
		test.log(LogStatus.INFO, "Select 'Men's Vintage Heather T-shirt' product");
		productObj.waitForProductDetailsPage();
		test.log(LogStatus.INFO, "Product details page is opened");
		String sizeOfTShirt=properties.getProperty("tShirtSize");
		productObj.selectSizeOfProduct(sizeOfTShirt);
		test.log(LogStatus.INFO, "Selected size of tshirt is "+sizeOfTShirt);
		String qtyOfTShirt=properties.getProperty("tShirtQty");
		productObj.selectQtyOfProduct(qtyOfTShirt);
		test.log(LogStatus.INFO, "Selected qty of tshirt is "+qtyOfTShirt);
		List<String> namePriceOfTShirts=productObj.getNamePriceOfProdAndAddToCart();
		test.log(LogStatus.INFO, "Fetched name "+namePriceOfTShirts.get(0)+" & price "+namePriceOfTShirts.get(1)+" of Tshirt product in Product details page and then add product to cart");
		productObj.clickViewCartButton();
		//Adding qty & price of TShirt to the combined List
		List<String> qtyPriceOfTShirt=new ArrayList();
		qtyPriceOfTShirt.add(qtyOfTShirt);
		qtyPriceOfTShirt.add(namePriceOfTShirts.get(1));
		combinedPrice.add(qtyPriceOfTShirt);
		test.log(LogStatus.INFO, "Added qty "+qtyOfTShirt+" & price "+namePriceOfTShirts.get(1)+" of TShirt to the combined List which is used to add prices of all products for calculation");
		//Getting total price of Jacket product
		float totalPriceOfTShirts=productObj.getTotalPriceOfEachProduct(qtyOfTShirt, namePriceOfTShirts.get(1));
		test.log(LogStatus.INFO, "Added total price of tshirt " +totalPriceOfTShirts+ " so that it can be validated on Checkout page");

		//Calculate total price of all the products added
		float totalPriceOfAllProducts=productObj.getTotalPriceOfAllProducts(combinedPrice);
		test.log(LogStatus.INFO, "Added total price of all products " +totalPriceOfAllProducts+ " so that it can be validated on Checkout page");

		//Get products' description, number and individual & combined price in the Cart
		Cart cartObj=new Cart(driver);
		cartObj.waitForCartPage();
		test.log(LogStatus.INFO, "Cart page is opened");
		//Fleece Full Zip Hoodie details in the cart
		String hoodieDescriptionInCart=cartObj.getProductDescription(0);//index is 0 since sequence of this product is first on Cart page
		test.log(LogStatus.INFO, "Hoodie product description on Cart page is "+hoodieDescriptionInCart);
		String hoodieQtyInCart=cartObj.getProductQty(0);
		test.log(LogStatus.INFO, "Hoodie product qty on Cart page is "+hoodieQtyInCart);
		String hoodiePriceInCart=cartObj.getProductPrice(0);
		test.log(LogStatus.INFO, "Hoodie product price on Cart page is "+hoodiePriceInCart);

		//Ladies Sonoma Hybrid Knit Jacket details in the cart
		String jacketDescriptionInCart=cartObj.getProductDescription(1);//index is 1 since sequence of this product is second on Cart page
		test.log(LogStatus.INFO, "Jacket product description on Cart page is "+jacketDescriptionInCart);
		String jacketQtyInCart=cartObj.getProductQty(1);
		test.log(LogStatus.INFO, "Jacket product qty on Cart page is "+jacketQtyInCart);
		String jacketPriceInCart=cartObj.getProductPrice(1);
		test.log(LogStatus.INFO, "Jacket product price on Cart page is "+jacketPriceInCart);

		//Men's Vintage Heather T-shirt details in the cart
		String tShirtDescriptionInCart=cartObj.getProductDescription(2);//index is 2 since sequence of this product is third on Cart page
		test.log(LogStatus.INFO, "Tshirt product description on Cart page is "+tShirtDescriptionInCart);
		String tShirtQtyInCart=cartObj.getProductQty(2);
		test.log(LogStatus.INFO, "Tshirt product qty on Cart page is "+tShirtQtyInCart);
		String tShirtPriceInCart=cartObj.getProductPrice(2);
		test.log(LogStatus.INFO, "Tshirt product price on Cart page is "+tShirtPriceInCart);

		//Validate details of cart vs added data in the below code
		//Validate Hoodie data in the cart vs added data
		Assert.assertEquals(hoodieDescriptionInCart, namePriceOfHoodie.get(0), "Comparing Hoodie description in cart vs added one");
		test.log(LogStatus.INFO, "Hoodie description in cart is matching with the added one and it is "+hoodieDescriptionInCart);
		Assert.assertEquals(hoodiePriceInCart, namePriceOfHoodie.get(1), "Comparing Hoodie price in cart vs added one");
		test.log(LogStatus.INFO, "Hoodie price in cart is matching with the added one and it is "+hoodiePriceInCart);
		Assert.assertEquals(hoodieQtyInCart, qtyOfHoodie, "Comparing Hoodie quantity in cart vs added one");
		test.log(LogStatus.INFO, "Hoodie qty in cart is matching with the added one and it is "+hoodieQtyInCart);

		//Validate Jacket data in the cart vs added data
		Assert.assertEquals(jacketDescriptionInCart, namePriceOfJacket.get(0), "Comparing Jacket description in cart vs added one");
		test.log(LogStatus.INFO, "Jacket description in cart is matching with the added one and it is "+jacketDescriptionInCart);
		Assert.assertEquals(jacketPriceInCart, namePriceOfJacket.get(1), "Comparing Jacket price in cart vs added one");
		test.log(LogStatus.INFO, "Jacket price in cart is matching with the added one and it is "+jacketPriceInCart);
		Assert.assertEquals(jacketQtyInCart, qtyOfJacket, "Comparing Jacket qyantity in cart vs added one");
		test.log(LogStatus.INFO, "Jacket qty in cart is matching with the added one and it is "+jacketQtyInCart);

		//Validate TShirt data in the cart vs added data
		Assert.assertEquals(tShirtDescriptionInCart, namePriceOfTShirts.get(0), "Comparing T-Shirt description in cart vs added one");
		test.log(LogStatus.INFO, "Tshirt description in cart is matching with the added one and it is "+tShirtDescriptionInCart);
		Assert.assertEquals(tShirtPriceInCart, namePriceOfTShirts.get(1), "Comparing T-Shirt price in cart vs added one");
		test.log(LogStatus.INFO, "Tshirt price in cart is matching with the added one and it is "+tShirtPriceInCart);
		Assert.assertEquals(tShirtQtyInCart, qtyOfTShirt, "Comparing Jacket T-Shirt in cart vs added one");
		test.log(LogStatus.INFO, "Tshirt qty in cart is matching with the added one and it is "+tShirtQtyInCart);

		//Validate combined price in the cart vs calculated combined price of all products while adding
		float totalPriceInCart=cartObj.getTotalPriceFromcart();
		Assert.assertEquals(totalPriceInCart, totalPriceOfAllProducts, "Comparing total price of all products in Cart vs total price calculated while adding products");
		test.log(LogStatus.INFO, "Total price in the cart is matching with the combined price of all products and it is "+totalPriceInCart);

		//Remove Fleece Full-Zip Hoodie product
		cartObj.removeProductFromCart(0);//index is 0 since hoodie is first product in the list
		test.log(LogStatus.INFO, "removed 'Fleece Full Zip Hoodie' product from the cart");

		//Click Checkout button
		cartObj.clickCheckoutBtn();
		Checkout checkoutObj=new Checkout(driver);
		checkoutObj.waitForCheckoutPage();
		test.log(LogStatus.INFO, "Navigated to Checkout screen");

		//Validate details of product and prices on Checkout page in below code

		//Get description of 'Ladies Sonoma Hybrid Knit Jacket' product from checkout page
		String jacketNameOnCheckout=checkoutObj.getProductDescriptionCheckoutPage(0);//index is 0 because Jacket product is first in the list
		Assert.assertEquals(jacketNameOnCheckout, namePriceOfJacket.get(0), "Comparing Jacket description in Checkout page vs added one");
		test.log(LogStatus.INFO, "Jacket description on Checkout page is matching with the added one and it is "+jacketNameOnCheckout);

		//Get description of 'Men's Vintage Heather T-shirt' product from checkout page
		String tShirtNameOnCheckout=checkoutObj.getProductDescriptionCheckoutPage(1);//index is 1 because this product is second in the list
		Assert.assertEquals(tShirtNameOnCheckout, namePriceOfTShirts.get(0), "Comparing Tshirt description in Checkout page vs added one");
		test.log(LogStatus.INFO, "Tshirt description on Checkout page is matching with the added one and it is "+tShirtNameOnCheckout);

		//Get total price of 'Ladies Sonoma Hybrid Knit Jacket' product from checkout page
		float jacketPriceOnCheckout=checkoutObj.getProductPriceCheckoutPage(0);//index is 0 because Jacket product is first in the list
		//Comparing Jacket price in Checkout page vs added one
		Assert.assertEquals(jacketPriceOnCheckout, totalPriceOfJacket, "Comparing Jacket price in Checkout page vs added one");
		test.log(LogStatus.INFO, "Jacket price on Checkout page is matching with the added one and it is "+jacketPriceOnCheckout);

		//Get total price of 'Men's Vintage Heather T-shirt' product from checkout page
		float tShirtPriceOnCheckout=checkoutObj.getProductPriceCheckoutPage(1);//index is 1 because this product is second in the list
		Assert.assertEquals(tShirtPriceOnCheckout, totalPriceOfTShirts, "Comparing T-Shirt price in Checkout page vs added one");
		test.log(LogStatus.INFO, "Tshirt price on Checkout page is matching with the added one and it is "+tShirtPriceOnCheckout);

		float expectedTotalPriceOnCheckout=jacketPriceOnCheckout+tShirtPriceOnCheckout;
		float actualTotalPriceOnCheckout=checkoutObj.getTotalPriceCheckoutPage();
		Assert.assertEquals(expectedTotalPriceOnCheckout, actualTotalPriceOnCheckout, "Comparing expected total price on Checkout page to actual total price on Checkout page");
		test.log(LogStatus.INFO, "Total price on Checkout page is matching with the price of all the added products and it is "+expectedTotalPriceOnCheckout);
	}

}
