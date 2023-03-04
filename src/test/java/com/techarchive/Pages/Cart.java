package com.techarchive.Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

public class Cart extends PageObject{

    @FindBy(xpath = "//button[@id='checkout']")
    private WebElement checkOut;

    @FindBy(xpath = "//div[@class='cart_item']//*[@class='inventory_item_name']")
    private List<WebElement> itemsInCart;

    @FindBy(xpath = "//*[@class='title']")
    private WebElement pageTitle;

    public Cart(WebDriver driver) {
        super(driver);
    }

    public void checkout(){
        this.checkOut.click();
    }

    public List<String> getItemsInCart(){
        List<String> itemsAddedToCart = new ArrayList<>();
        for (int i=0; i<itemsInCart.size(); i++ ){
            itemsAddedToCart.add(itemsInCart.get(i).getText());

        }
        return itemsAddedToCart;
    }

    public boolean isPageLoaded() {
        String title = pageTitle.getText();
        if (title.equalsIgnoreCase("Your Cart"))
            return true;
        else
            return false;
    }
}
