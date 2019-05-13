package com.automation.techassessment.ui.Sauce;

import com.automation.techassessment.ui.pages.sauce.SauceDemo;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SaucedemoUitests  extends BaseUITest{

    private SoftAssert softAssert = new SoftAssert();

    @Test
    public void sauceDemo()
    {
        String productOnesie = "Sauce Labs Onesie";
        String productBikeLight = "Sauce Labs Bike Light";

        SauceDemo.Login.login("standard_user", "secret_sauce");
        SauceDemo.Dashboard.selectProduct(productOnesie);
        SauceDemo.Product.addProductToCart();   //Add Onesie to cart
        SauceDemo.MenuBar.navigateToInventory();
        SauceDemo.Dashboard.selectProduct(productBikeLight);
        SauceDemo.Product.addProductToCart();   //Add Bike Light to cart
        SauceDemo.MenuBar.navigateToCartPage();
        softAssert.assertTrue(SauceDemo.Cart.verifyItemInCart(productOnesie), "Unable to find "+productOnesie+" product in cart, adding the product to the cart must have failed");
        softAssert.assertTrue(SauceDemo.Cart.verifyItemInCart(productBikeLight), "Unable to find "+productBikeLight+" product in cart, adding the product to the cart must have failed");
        softAssert.assertAll();
    }
}






