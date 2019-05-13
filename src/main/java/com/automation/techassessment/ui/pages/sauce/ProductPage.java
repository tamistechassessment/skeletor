package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import com.slickqa.webdriver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class ProductPage {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private PageElement buttonAddToCart = new PageElement("Add To Cart button", FindBy.xpath("//button [text()='ADD TO CART']"));
    private PageElement buttonRemove = new PageElement("Remove button", FindBy.xpath("//button [text()='REMOVE']"));

    public void addProductToCart()
    {
        WebDriverWrapper webDriverWrapper = UIThreadManager.getBrowser();
        webDriverWrapper.waitForVisible(buttonAddToCart, 10);
        webDriverWrapper.click(buttonAddToCart);
        Assert.assertTrue(webDriverWrapper.exists(buttonRemove, 10), "Unable to find REMOVE button. Item must not have been added to the cart.");
    }
}

