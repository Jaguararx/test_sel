package com.testframework.apps.internal;

import com.testframework.apps.wrappers.BasePage;

import static com.testframework.apps.wrappers.BaseTest.getPages;

public interface GenericPage {

    static BasePage getPageObject(final GenericPage page) {
        getPages().putIfAbsent(page, page.create());
        return getPages().get(page);
    }

    BasePage create();
}
