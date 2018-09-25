package com.testframework.pages.landings;

import com.testframework.apps.wrappers.BasePage;
import org.openqa.selenium.By;
import ru.yandex.qatools.allure.annotations.Step;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import com.codeborne.selenide.ElementsCollection;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static com.testframework.apps.utils.properties.SystemProperty.BASE_URL;

public class WizzairHomePage extends BasePage<WizzairHomePage> {

    @Override
    protected String getUrl() {
        return BASE_URL.getValue();
    }

    @Step("Search fly '{from , to}'")
    public String searchFor(String from, String to) {
        $("#search-departure-station").val(from).pressEnter();
        $("#search-arrival-station").val(to).pressEnter();
        String departure_date_str = $("#search-departure-date").innerText().trim();
        $("button[data-test=flight-search-submit]").click();
                
        // return wizzairSearchResultsPage();
        return departure_date_str;
    }
    
    @Step("Get itinerary route")
    public String getItineraryRoute() {
        return $(".booking-flow__itinerary__route").innerText();
    }
    
    @Step("Get passenger number")
    public String getPassengerNumber() {
        return $(".booking-flow__itinerary__pax-number").innerText();
    }

    @Step("Get selected date")
    public SelenideElement getSelectedDate() {
        return $(".booking-flow__flight-select__chart__day--selected");
    }
    
    @Step("Get prices")
    public ElementsCollection getPrices() {
        return $$(".booking-flow__prices-table__content .rf-fare__price__current");
    }
    
    @Step("Get select date for return")
    public SelenideElement getSelectDateForReturn() {
        return $("#fare-selector-return .booking-flow__flight-select__chart");
    }
    
    @Step("Click to price in middle column")
    public SelenideElement clickToPriceInMiddle() {
        SelenideElement element = $(".booking-flow__prices-table__content__column--middle .fare-selector__fare");
        element.click();
        return element;
    }
    
    @Step("Click to Continue on first step")
    public SelenideElement clickToContinueFirst(WebDriver driver) {
        SelenideElement element = $("#flight-select-continue-btn");
        waitAndClick(driver, element);
        return element;
    }

    @Step("Enter Personal data '{firstname, lastname, sex}'")
    public void enterPersonalData(String firstname, String lastname, String sex) {
        $("input#passenger-first-name-0").val(firstname);
        $("input#passenger-last-name-0").val(lastname);
        if (sex=="Male") {
          $("label[data-test=passenger-gender-0-male]").click();
        } else {
          $("label[data-test=passenger-gender-0-female]").click();
        }
    }
    
    @Step("Select first variant of luggage")
    public void selectLuggage() {
      $(".rf-switch--baggage .rf-switch__label", 0).click();  
    }
    
    @Step("Click to Continue on second step")
    public SelenideElement clickToContinueSecond(WebDriver driver) {
        SelenideElement element = $("#passengers-continue-btn");
        waitAndClick(driver, element);
        return element;
    }

    @Step("Get popup login section")
    public SelenideElement getPopupLogin() {
        return $("#login-modal");
    }
    

    private void scrollToElement(WebDriver driver, WebElement el) {
      if (driver instanceof JavascriptExecutor) {
          ((JavascriptExecutor) driver)
              .executeScript("arguments[0].scrollIntoView(true);", el);
      }
    }
    
    public void waitAndClick(WebDriver driver, SelenideElement sel) {
      WebElement el = sel.toWebElement();
      try {
          WebDriverWait wait = new WebDriverWait(driver, 5, 200);
          wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("loader")));
          wait.until(ExpectedConditions.elementToBeClickable(el)).click();
      }
      catch (WebDriverException wde) {
          scrollToElement(driver, el);
          el.click();
      }
    }

    @Step("Get passenger route")
    public SelenideElement getPassengerRoute() {
        return $(".booking-flow__passengers__tabs");
    }
    
}
