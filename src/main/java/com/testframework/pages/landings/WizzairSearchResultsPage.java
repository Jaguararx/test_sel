package com.testframework.pages.landings;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.testframework.apps.wrappers.BasePage;
import ru.yandex.qatools.allure.annotations.Step;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class WizzairSearchResultsPage extends BasePage<WizzairSearchResultsPage> {

    @Override
    protected String getUrl() {
        return null;
    }

    @Step("Get address title")
    public SelenideElement getAddressTitle() {
        return $("form[name='flight-select-form'] address");
    }
}
