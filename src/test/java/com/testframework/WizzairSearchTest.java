package com.testframework;

import com.testframework.apps.wrappers.BaseTest;
import com.testframework.apps.internal.Validator;
import com.testframework.pages.landings.WizzairHomePage;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;
import ru.yandex.qatools.allure.annotations.Features;
import ru.yandex.qatools.allure.annotations.Stories;
import ru.yandex.qatools.allure.annotations.Title;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Date;
import java.util.Locale;
import java.text.SimpleDateFormat;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;

@Features("Searching")
@Stories("Search in wizzair")
public class WizzairSearchTest extends BaseTest {
    Logger logger = LogManager.getLogger(WizzairSearchTest.class);

    
    @DataProvider(name = "Searchdata")
    public static Object[][] searchdata() {
      // --------------------- Test Data ----------------------//
      return new Object[][] {
        { "Kiev - Zhulyany", "Copenhagen", "Ivan", "Yankovyi", "Male"}, 
        { "Kiev - Zhulyany", "Copenhagen", "Katya", "Rozumenko", "Female" }
      };
    }
    
    @Title("Test: search in wizzair fly from Kiev to Copenhagen")
    @Test(groups = "wizzair_search", priority = 10, dataProvider = "Searchdata")
    public void searchFlyTest(
      String from, 
      String to,
      String firstname,
      String lastname,
      String sex
      ) {
        // --------------------- Test Case ----------------------//
        WizzairHomePage whp = wizzairHomePage().openPage();
        String departure_date_str = whp.searchFor(from, to);
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy", Locale.ENGLISH);
        Date departure_date = new Date();
        try {
          departure_date = format.parse(departure_date_str);
        }
        catch (Exception e) {
        }        
        
        whp.switchToSecondTab();

        //check that form with selecting fligh is visible
        SelenideElement FormElement = $("form[name='flight-select-form']");
        FormElement.shouldBe(visible);
        
        //check that title is text with html tag in the middle and break for two parts 
        String route = whp.getItineraryRoute();
        String[] route_parts = route.split("\u2014");
        //check that route devided by \u2014 into 2 parts 
        verifyIntEquals(route_parts.length,2);
        //check that first part is our Source
        verifyTextEquals(route_parts[0].trim(),from);
        //check that first part is our Destiation
        verifyTextEquals(route_parts[1].trim(),to);
        
        String pass_number = whp.getPassengerNumber();
        String[] pass_number_parts = pass_number.trim().split(" ");
        //check that pass number devided by " " into 2 parts 
        verifyIntEquals(pass_number_parts.length,2);
        //check that first part is equal to 1
        verifyTextEquals(pass_number_parts[0].trim(),"1");

        WebDriverWait wait = new WebDriverWait(getDriver(), 60);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".booking-flow__flight-select__chart__day--selected")));
        SelenideElement selected_date = whp.getSelectedDate();
        //get selected date string
        String date_str = selected_date.find("time").attr("datetime").split("T")[0];
        SimpleDateFormat format2 = new SimpleDateFormat("yyyy-MM-dd");
        //check that selected departure date is our date
        verifyTextEquals(date_str,format2.format(departure_date));

        ElementsCollection prices = whp.getPrices();
        //get prices collection and check that there's 6 prices (3 prices , but as they are doubled )
        verifyIntEquals(prices.size(), 6);
        
        SelenideElement select_date_for_return = whp.getSelectDateForReturn();
        //check date return fly selector doesn't exists
        verifyElementNotExists(select_date_for_return);
        
        //check that middle price exists and click it
        SelenideElement element = whp.clickToPriceInMiddle();
        verifyElementExists(element);
  
        //check that continue button exists and click it
        element = whp.clickToContinueFirst(getDriver());
        verifyElementExists(element);
  
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form[name=passengers-form]")));

        //enter lastname, firstname and select Male/Female
        whp.enterPersonalData(firstname, lastname, sex);
        
        //select luggage
        whp.selectLuggage();
        
        //check route of baggage
        element = whp.getPassengerRoute();
        String pass_route = " "+element.innerText();
        //verify that "from" and "to" have postion in passanger route and position of "to" greater than position of "from"
        verifyIntGreater(pass_route.indexOf(from),0);
        verifyIntGreater(pass_route.indexOf(to),0);
        verifyIntGreater(pass_route.indexOf(to),pass_route.indexOf(from));
        
        //click Continue
        element = whp.clickToContinueSecond(getDriver());
        
        //check that signup popup exists
        element = whp.getPopupLogin();
        verifyElementExists(element);
        verifyElementVisible(element);
        
    }


}
