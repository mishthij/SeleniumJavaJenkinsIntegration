package com.techarchive.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStep2 extends PageObject{


    @FindBy(xpath = "//button[@id='finish']")
    private WebElement finishButton;

    @FindBy (xpath = "//*[@class='title']")
    private WebElement pageTitle;

    @FindBy (xpath = "//div[@class='summary_subtotal_label']")
    private WebElement itemTotal;

    public CheckoutStep2(WebDriver driver) {
        super(driver);
    }

    public void finishCheckout(){
        this.finishButton.click();
    }

    public boolean isPageLoaded() {
        String title = pageTitle.getText();
        if (title.equalsIgnoreCase("Checkout: Overview"))
            return true;
        else
            return false;
    }

    public String getItemTotal() {

        String itemTotalPrice=itemTotal.getText();
        System.out.println("Total item price"+ itemTotalPrice.substring(13));
        return itemTotalPrice.substring(13);
    }
}
