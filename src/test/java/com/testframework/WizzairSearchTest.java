package com.testframework;

import com.testframework.apps.wrappers.BaseTest;
import com.testframework.pages.landings.WizzairHomePage;
import com.testframework.pages.landings.WizzairSearchResultsPage;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;

import java.util.concurrent.TimeUnit;
//import com.iselsoft.easyium.exceptions.WebDriverTimeoutException;
// import java.lang.Thread;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@Features("Searching")
@Stories("Search in wizzair")
public class WizzairSearchTest extends BaseTest {

    @Title("Test: search in wizzair fly from Kiev to Copenhagen")
    @Test(groups = "wizzair_search", priority = 10)
    public void successTest() {
        // --------------------- Test Data ----------------------//
        String from = "Kiev - Zhulyany";
        String to = "Copenhagen";
        // --------------------- Test Case ----------------------//
        WizzairHomePage whp = wizzairHomePage().openPage();
        whp.getPageUrl();//for log
        whp.searchFor(from, to);
        // getDriver().navigate().back();
        // Thread.sleep(30);
        // getDriver().manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // getDriver().manage().timeouts().setScriptTimeout(40,TimeUnit.SECONDS);
        // getDriver().manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
        // WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        // WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("article#booking-flow-step-select-flight")));

        // SelenideElement FormElement = $("form[name='flight-select-form']");
        // FormElement.shouldBe(visible);
        
        // SelenideElement addressTitle = whp.getAddressTitle();
        // addressTitle.shouldHave(text(from));
        // addressTitle.shouldHave(text(to));
        
        // WizzairSearchResultsPage results =
                // wizzairHomePage().openPage().searchFor(from, to);
        // results.getPageUrl();
        // results.getPageTitle();
        // SelenideElement addressTitle = results.getAddressTitle();
        // addressTitle.shouldHave(text(from));
        // addressTitle.shouldHave(text(to));
    }


}
