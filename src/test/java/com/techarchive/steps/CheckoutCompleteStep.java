package com.techarchive.steps;

import com.techarchive.Pages.CheckoutComplete;
import com.techarchive.Utitlities.BaseClass;
import io.cucumber.java.en.And;
import org.openqa.selenium.support.FindBy;


public class CheckoutCompleteStep extends BaseClass {
    @And("I verify order is completed")
    public void iVerifyOrderIsCompleted() {
        CheckoutComplete checkoutComplete = new CheckoutComplete(driver);
        boolean verifyOrderCompletion = checkoutComplete.verifyOrderComplete();
        if (verifyOrderCompletion)
            System.out.println("Order Completed!!");
        else
            System.out.println("Order Completion Validation failed");
    }
}
