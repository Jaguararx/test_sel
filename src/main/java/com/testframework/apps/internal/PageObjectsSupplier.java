package com.testframework.apps.internal;

import com.testframework.apps.wrappers.BasePage;
import com.testframework.pages.landings.WizzairHomePage;
import com.testframework.pages.landings.WizzairSearchResultsPage;

import static com.testframework.apps.internal.GenericPage.getPageObject;
import static com.testframework.apps.internal.PageObjectsSupplier.PageObject.WIZZAIR_HOME_PAGE;
import static com.testframework.apps.internal.PageObjectsSupplier.PageObject.WIZZAIR_SEARCH_RESULTS_PAGE;

public interface PageObjectsSupplier<T extends PageObjectsSupplier<T>> {

    enum PageObject implements GenericPage {

        WIZZAIR_HOME_PAGE {
            public BasePage create() {
                return new WizzairHomePage();
            }
        },
        WIZZAIR_SEARCH_RESULTS_PAGE {
            public BasePage create() {
                return new WizzairSearchResultsPage();
            }
        };

    }

    default WizzairHomePage wizzairHomePage() {
        return (WizzairHomePage) getPageObject(WIZZAIR_HOME_PAGE);
    }

    default WizzairSearchResultsPage wizzairSearchResultsPage() {
        return (WizzairSearchResultsPage) getPageObject(WIZZAIR_SEARCH_RESULTS_PAGE);
    }

}
