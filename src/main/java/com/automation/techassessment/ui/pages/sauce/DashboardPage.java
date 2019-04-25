package com.automation.techassessment.ui.pages.sauce;

import com.automation.techassessment.ui.lib.UIThreadManager;
import com.slickqa.webdriver.FindBy;
import com.slickqa.webdriver.PageElement;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class DashboardPage {
    private final Logger logger = LogManager.getLogger(this.getClass());

    private PageElement productContainer = new PageElement("Product container", FindBy.id("inventory_container"));

    public boolean productContainerIsVisible() {

        return UIThreadManager.getBrowser().exists(productContainer);
    }
}

