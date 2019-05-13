package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import com.slickqa.webdriver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class MenuBar {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private PageElement buttonMenuBar = new PageElement("Menu Bar Button", FindBy.className("bm-burger-button"));
    private PageElement linkCart = new PageElement("Cart Link", FindBy.id("shopping_cart_container"));
    private PageElement linkAllItems = new PageElement("All Items", FindBy.id("inventory_sidebar_link"));
    private PageElement cartContainer = new PageElement("Cart Link", FindBy.id("cart_contents_container"));
    private PageElement productContainer = new PageElement("ProductPage container", FindBy.id("inventory_container"));


    public boolean menuBarButtonExists() {
        return UIThreadManager.getBrowser().exists(buttonMenuBar);
    }

    public void navigateToCartPage() {
        WebDriverWrapper webDriverWrapper = UIThreadManager.getBrowser();
        webDriverWrapper.waitForVisible(linkCart, 10);
        webDriverWrapper.click(linkCart);
        Assert.assertTrue(webDriverWrapper.exists(cartContainer,10), "The Cart page doesn't appear to have loaded.");
    }

    public void navigateToInventory() {
        WebDriverWrapper webDriverWrapper = UIThreadManager.getBrowser();
        webDriverWrapper.waitForVisible(buttonMenuBar, 10);
        webDriverWrapper.click(buttonMenuBar);
        webDriverWrapper.waitForVisible(linkAllItems, 10);
        webDriverWrapper.click(linkAllItems);
        Assert.assertTrue(webDriverWrapper.exists(productContainer,10), "The Inventory/Products page doesn't appear to have loaded.");
    }
}
