package com.techarchive.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.*;

public class Inventory extends PageObject {

    @FindBy(xpath = "//span[@class='title']")
    private WebElement pageTitle;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']/preceding-sibling::div[@class='inventory_item_price']")
    private WebElement itemPrice1;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addCartItem1;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']/preceding-sibling::div[@class='inventory_item_price']")
    private WebElement itemPrice2;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-bike-light']")
    private WebElement addCartItem2;

    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCart;

    @FindBy(xpath = "//div[@class='inventory_list']/div[@class='inventory_item']")
    private List<WebElement> productslist;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    private WebElement sortDropdown;

    @FindBy(xpath = "//select[@class='product_sort_container']/option")
    private List<WebElement> sortCriteria;

    @FindBy(xpath = "//button[@id='add-to-cart-sauce-labs-backpack']")
    private WebElement addToCartBackpack;


    @FindBy(xpath = "//a[@class='shopping_cart_link']")
    private WebElement shoppingCartLink;

    public Inventory(WebDriver driver) {
        super(driver);
    }

    public static List<String> productPriceList = new ArrayList<>();

    public boolean isPageLoaded() {
        String title = pageTitle.getText();
        if (title.equalsIgnoreCase("Products"))
            return true;
        else
            return false;
    }

    public int getNumberOfProducts() {
        return productslist.size();
    }

    public String verifyPageTitle() {
        return this.pageTitle.getText();
    }

    /*public void addItemsToCart() {
        addFirstItemToCart();
        addSecondItemToCart();
        shoppingCartClick();
    }*/

    public void addFirstItemToCart(String item) {
        this.addCartItem1.click();
    }

    public void addSecondItemToCart() {
        this.addCartItem2.click();
    }

    public void shoppingCartClick() {
        this.shoppingCart.click();
    }

    public void addItemToCart(String item){
        String path = "//div[@class='inventory_list']/div[@class='inventory_item']";

        for (int i=1; i<= this.getNumberOfProducts(); i++){
            String productval = path + "[" + i + "]" + "//div[@class='inventory_item_name']";
            WebElement productname = driver.findElement(By.xpath(productval));
            if (item.equalsIgnoreCase(productname.getText())){
                String pricePath = path + "[" + i + "]" +"//div[@class='inventory_item_price']";
                productPriceList.add(driver.findElement(By.xpath(pricePath)).getText());
                String buttonPath = path + "[" + i + "]" + "//div[@class='pricebar']//button[@class='btn btn_primary btn_small btn_inventory']";
                System.out.println("Button Path: "+ buttonPath);
                WebElement buttonAddToCart = driver.findElement(By.xpath(buttonPath));
                buttonAddToCart.click();
                break;
            }

        }
    }

    public HashMap<String, String> productandpricelist() {

        String path = "//div[@class='inventory_list']/div[@class='inventory_item']";
        LinkedHashMap<String, String> listOfProducts = new LinkedHashMap<>();
        for (int i = 1; i <= this.getNumberOfProducts(); i++) {
            System.out.println(i);
            String productval = path + "[" + i + "]" + "//div[@class='inventory_item_name']";
            System.out.println(productval);
            String priceval = path + "[" + i + "]" + "//div[@class='inventory_item_price']";
            System.out.println(priceval);
            WebElement productname = driver.findElement(By.xpath(productval));
            // Print all elements of ArrayList
            System.out.println(productname.getText());
            WebElement pricename = driver.findElement(By.xpath(priceval));
            // Print all elements of ArrayList
            System.out.println(pricename.getText());
            if (productname != null && pricename != null) {
                listOfProducts.put(productname.getText(), pricename.getText());
            }


        }
        for (Map.Entry m : listOfProducts.entrySet()) {
            System.out.println("Captured list" + m.getKey() + " " + m.getValue());
        }
        return  listOfProducts;
    }

    public List<String> getSortCriteriaList (){
            List<String> sortCriteriaList = new ArrayList<>();
            String path = "//select[@class='product_sort_container']/option";
            for (int i=1; i<=sortCriteria.size(); i++){
                String pathVal = path+"["+i+"]";
                WebElement sortCriteria1 = driver.findElement(By.xpath(pathVal));
                sortCriteriaList.add(sortCriteria1.getText());

            }
            for (int j=0; j<sortCriteriaList.size();j++){
                System.out.println("Sort Criteria: "+ sortCriteriaList.get(j));
            }

    return sortCriteriaList;
    }

    public void selectValueFromDropdown(String sortCriteria) throws InterruptedException {
        sortDropdown.click();
        Thread.sleep(2000);
        List<WebElement> sortCriteriaList1= driver.findElements(By.xpath("//select[@class='product_sort_container']/option"));
        for (int j=0; j<sortCriteriaList1.size();j++){
            if (sortCriteriaList1.get(j).getText().equalsIgnoreCase(sortCriteria)) {
                sortCriteriaList1.get(j).click();
                break;
            }
        }
    }

    public String validateNumberOfItemAddedToCart(){
        String numberOfItems= shoppingCartLink.getText();
        return numberOfItems;

    }

}