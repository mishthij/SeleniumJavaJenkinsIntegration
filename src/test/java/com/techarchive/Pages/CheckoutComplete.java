package com.techarchive.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutComplete extends PageObject{

    @FindBy(xpath = "//button[@id='back-to-products']")
    private WebElement backToHome;

    @FindBy(xpath = "//*[@class='title']")
    private WebElement pageTitle;

    @FindBy (xpath = "//h2[@class='complete-header']")
    private WebElement completeHeader;

    public CheckoutComplete(WebDriver driver) {
        super(driver);
    }

    public void backToHome(){
        this.backToHome.click();
    }

    public boolean isPageLoaded() {
        String title = pageTitle.getText();
        if (title.equalsIgnoreCase("Checkout: Complete!"))
            return true;
        else
            return false;
    }

    public boolean verifyOrderComplete(){
        String verifyText = completeHeader.getText();
        if (verifyText.equalsIgnoreCase("THANK YOU FOR YOUR ORDER"))
            return true;
        else
            return false;
    }
}
