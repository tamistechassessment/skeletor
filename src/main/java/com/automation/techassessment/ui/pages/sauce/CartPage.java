package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import com.slickqa.webdriver.WebDriverWrapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CartPage {
    private final Logger logger = LogManager.getLogger(this.getClass());
    private PageElement titleProduct;

    public boolean verifyItemInCart(String productName) {
        WebDriverWrapper webDriverWrapper = UIThreadManager.getBrowser();
        titleProduct = new PageElement("Item in Cart", FindBy.text(productName));
        return (webDriverWrapper.exists(titleProduct, 10));
    }
}


