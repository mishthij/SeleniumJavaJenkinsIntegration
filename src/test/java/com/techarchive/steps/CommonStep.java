package com.techarchive.steps;

import com.techarchive.Pages.*;
import com.techarchive.Utitlities.BaseClass;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

public class CommonStep extends BaseClass {
    Inventory inventory;
    Cart cart;
    CheckoutStep1 checkoutStep1;
    CheckoutStep2 checkoutStep2;
    CheckoutComplete checkoutComplete;
    @Then("I verify {string} page is loaded")
    public void iVerifyPageIsLoaded(String page) {
        switch (page) {
            case "Home":
                inventory = new Inventory(driver);
                boolean homePageLoaded = inventory.isPageLoaded();
                if (homePageLoaded)
                    System.out.println("Home page is loaded");
                break;
            case "Cart":
                cart = new Cart(driver);
                boolean cartPageLoaded = cart.isPageLoaded();
                if (cartPageLoaded)
                    System.out.println("Cart page is loaded");
                break;
            case "CheckoutStep1":
                checkoutStep1 = new CheckoutStep1(driver);
                boolean checkoutStep1PageLoaded = checkoutStep1.isPageLoaded();
                if (checkoutStep1PageLoaded)
                    System.out.println("Checkout Step 1 page is loaded");
                break;
            case "CheckoutStep2":
                checkoutStep2 = new CheckoutStep2(driver);
                boolean checkoutStep2PageLoaded = checkoutStep2.isPageLoaded();
                if (checkoutStep2PageLoaded)
                    System.out.println("Checkout Step 2 page is loaded");
                break;
            case "CheckoutComplete":
                checkoutComplete = new CheckoutComplete(driver);
                boolean checkoutCompletePageLoaded = checkoutComplete.isPageLoaded();
                if (checkoutCompletePageLoaded)
                    System.out.println("Checkout complete page is loaded");
                break;
        }

        //	driver.get(Util.BASE_URL);
        //Thread.sleep(100000);
    }
}
