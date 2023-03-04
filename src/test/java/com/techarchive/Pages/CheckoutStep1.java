package com.techarchive.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutStep1 extends PageObject{

    @FindBy(xpath = "//input[@id='first-name']")
    private WebElement firstName;

    @FindBy(xpath = "//input[@id='last-name']")
    private WebElement lastName;

    @FindBy(xpath = "//input[@id='postal-code']")
    private WebElement postalCode;

    @FindBy(xpath = "//input[@id='continue']")
    private WebElement continueButton;

    @FindBy(xpath = "//*[@class='title']")
    private WebElement pageTitle;

    public CheckoutStep1(WebDriver driver) {
        super(driver);
    }

    public void checkoutStepOne(String fName, String lName, String postal){
        enterFirstName(fName);
        enterLastName(lName);
        enterPostalCode(postal);
        pressContinue();
    }

    public void enterFirstName(String firstName1){
        this.firstName.sendKeys(firstName1);
    }

    public void enterLastName(String lastName1){
        this.lastName.sendKeys(lastName1);
    }

    public void enterPostalCode(String postalCode1){
        this.postalCode.sendKeys(postalCode1);
    }

    public void pressContinue(){
        this.continueButton.click();
    }

    public boolean isPageLoaded() {
        String title = pageTitle.getText();
        if (title.equalsIgnoreCase("Checkout: Your Information"))
            return true;
        else
            return false;
    }
}
