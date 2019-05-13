package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import com.slickqa.webdriver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;


public class DashboardPage {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private PageElement productContainer = new PageElement("ProductPage container", FindBy.id("inventory_container"));
    private PageElement titleProduct;
    private PageElement productDetails = new PageElement("ProductPage container", FindBy.className("inventory_details"));


    public boolean productContainerIsVisible() {
        return UIThreadManager.getBrowser().exists(productContainer);
    }

    public void selectProduct(String productName) {
        WebDriverWrapper webDriverWrapper = UIThreadManager.getBrowser();
        titleProduct = new PageElement("Item in Cart", FindBy.text(productName));
        webDriverWrapper.waitForVisible(titleProduct, 10);
        webDriverWrapper.click(titleProduct);
        Assert.assertTrue(webDriverWrapper.exists(productDetails,10), "Unable to find the product details page. Item must not have been selected.");
    }
}


