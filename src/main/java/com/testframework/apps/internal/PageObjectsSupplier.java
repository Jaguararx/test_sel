package com.testframework.apps.internal;

import com.testframework.apps.wrappers.BasePage;
import com.testframework.pages.landings.WizzairHomePage;

import static com.testframework.apps.internal.GenericPage.getPageObject;
import static com.testframework.apps.internal.PageObjectsSupplier.PageObject.WIZZAIR_HOME_PAGE;

public interface PageObjectsSupplier<T extends PageObjectsSupplier<T>> {

    enum PageObject implements GenericPage {

        WIZZAIR_HOME_PAGE {
            public BasePage create() {
                return new WizzairHomePage();
            }
        };

    }

    default WizzairHomePage wizzairHomePage() {
        return (WizzairHomePage) getPageObject(WIZZAIR_HOME_PAGE);
    }

}
