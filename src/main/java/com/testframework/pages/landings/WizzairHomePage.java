package com.testframework.pages.landings;

import com.testframework.apps.wrappers.BasePage;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.testframework.apps.utils.properties.SystemProperty.BASE_URL;

public class WizzairHomePage extends BasePage<WizzairHomePage> {

    @Override
    protected String getUrl() {
        return BASE_URL.getValue();
    }

    @Step("Search fly '{from , to}'")
    public WizzairHomePage searchFor(String from, String to) {
        $("#search-departure-station").val(from).pressEnter();
        $("#search-arrival-station").val(to).pressEnter();
        $("button[data-test=flight-search-submit]").click();
                
        // return wizzairSearchResultsPage();
        return this;
    }
    
    @Step("Get address title")
    public SelenideElement getAddressTitle() {
        return $("form[name='flight-select-form'] address");
    }
}
