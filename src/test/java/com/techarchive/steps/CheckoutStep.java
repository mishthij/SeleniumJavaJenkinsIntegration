package com.techarchive.steps;

import com.techarchive.Pages.CheckoutStep2;
import com.techarchive.Pages.Inventory;
import com.techarchive.Utitlities.BaseClass;
import io.cucumber.java.en.And;

import java.util.List;

public class CheckoutStep extends BaseClass {
    @And("I verify item total matches with product price")
    public void iVerifyItemTotalMatchesWithProductPrice() {
        Inventory inventory = new Inventory(driver);
        double sum = 0;
        for (int i=0; i<inventory.productPriceList.size(); i++){

            String temp = inventory.productPriceList.get(i).substring(1);
            double d=Double.parseDouble(temp);
            sum= sum + d;
        }
        System.out.println("Total item of Products:"+ sum);
        CheckoutStep2 checkoutStep2 = new CheckoutStep2(driver);
        String getTotalPrice= checkoutStep2.getItemTotal();
        double total = Double.parseDouble(getTotalPrice);
        if(sum==total)
            System.out.println("Total price verified");
        else
            System.out.println("Total price verification failed");

    }
}
