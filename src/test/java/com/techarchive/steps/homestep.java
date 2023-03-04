package com.techarchive.steps;

import com.techarchive.Pages.Cart;
import com.techarchive.Pages.Inventory;
import com.techarchive.Utitlities.BaseClass;
import com.techarchive.Utitlities.CommonUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.openqa.selenium.WebElement;

import java.util.*;

public class homestep extends BaseClass {

    @And("I verify Number of products on {string} page")
    public void iVerifyOnPage( String page) {
       int productsOnPage;
       int productsInTestData;
       Inventory inventory = new Inventory(driver);
       productsOnPage= inventory.getNumberOfProducts();
       String productDetails = testdata_map.get("ProductDetails");
       String[] productList = productDetails.split(",");
       productsInTestData = productList.length;
       if (productsOnPage==productsInTestData)
           System.out.println("Product count verified and total number of products displayed are: "+productsOnPage);
       inventory.productandpricelist();
    }



    @And("I validate Product name and Price on {string} page")
    public void iValidateProductNameAndPriceOnPage(String arg0) {
        Inventory inventory = new Inventory(driver);
        HashMap <String, String> listOfProductsandPrice= inventory.productandpricelist();
        HashMap <String, String> detailsOfProductsFromTestData = new HashMap<>();
        String [] details = new String[1];
     //   String [] keys = new String[10];
     //   String [] values = new String[10];
        String productDetails = testdata_map.get("ProductDetails");
        String[] productListWithPrice = productDetails.split(",");

        for (int iterator=0; iterator<productListWithPrice.length; iterator++){
            details = productListWithPrice[iterator].split("=");
            detailsOfProductsFromTestData.put(details[0], details[1]);
        }

        Boolean compFlag = CommonUtils.compareMaps(detailsOfProductsFromTestData,listOfProductsandPrice);
        //detailsOfProductsFromTestData.put()
        /*for (Map.Entry m : detailsOfProductsFromTestData.entrySet()) {
                Boolean cmpflag=true;
            if (detailsOfProductsFromTestData.size()==listOfProductsandPrice.size())
            {
                System.out.println(m.getKey() + " " + m.getValue());
                String strkey = m.getKey().toString();
                String strvalue = m.getValue().toString();
                if (listOfProductsandPrice.containsKey(strkey)) {
                    if (listOfProductsandPrice.containsValue(strvalue))
                        cmpflag = true;
                } else {
                    cmpflag = false;
                    break;
                }
            }


        } */

    if (compFlag==true)
        System.out.println("price of all products matches on UI");
    else
        System.out.println("products-price mismatch found!");

    }

    @And("I select {string} from {string} on {string} page")
    public void iSelectFromOnPage(String sortCriteria, String sortDropdown, String page) throws InterruptedException {
        Inventory inventory = new Inventory(driver);
        if (sortCriteria.equalsIgnoreCase("Name (Z to A)"))
        {
            System.out.println("Z to A clicked");
            inventory.selectValueFromDropdown(sortCriteria);

            HashMap <String, String> listOfProductsandPrice= inventory.productandpricelist();
            System.out.println("Product Price List after sort from UI:");
            for (Map.Entry m : listOfProductsandPrice.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
            HashMap <String, String> detailsOfProductsFromTestData = new HashMap<>();
            String [] details = new String[1];
            String productDetails = testdata_map.get("ProductDetails");
            String[] productListWithPrice = productDetails.split(",");

            for (int i=0; i<productListWithPrice.length; i++){
                details = productListWithPrice[i].split("=");
                detailsOfProductsFromTestData.put(details[0], details[1]);
            }

            Map<String, String> sortedTestDataMap = new TreeMap<String,String>(detailsOfProductsFromTestData);
            System.out.println("Sorted test data map:");
            for (Map.Entry m : sortedTestDataMap.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }
            System.out.println("Sorted test data map in reverse order:");
            //ArrayList<String> keys = new ArrayList<String>(sortedTestDataMap.keySet());
            //for (int j=keys.size()-1;j>=0;j--){
             //   System.out.println(sortedTestDataMap.get(keys.get(j)));
            //}

            Map<String, String> sortedInReverseTestDataMap = new HashMap<>();
            sortedInReverseTestDataMap = CommonUtils.sortByKeys(sortedTestDataMap);
            for (Map.Entry m : sortedInReverseTestDataMap.entrySet()) {
                System.out.println(m.getKey() + " " + m.getValue());
            }


            List<String> keyList = new ArrayList(listOfProductsandPrice.keySet());
            List<Integer> valueList = new ArrayList(listOfProductsandPrice.values());

            List tmpkey = new ArrayList(keyList);
            Collections.sort(tmpkey, Collections.reverseOrder());
            boolean revkeysorted = tmpkey.equals(keyList);

           // List tmpvalue = new ArrayList(valueList);
         //   Collections.sort(tmpvalue, Collections.reverseOrder());
          //  boolean revkeyrevsorted = tmpvalue.equals(valueList);

            if (revkeysorted==true)
                System.out.println("Sort test passes");
        }

    }




    @Then("I verify products are sorted based on sort criteria")
    public void iVerifyProductsAreSortedBasedOnSortCriteria() {
    }


    @And("I click on {string} for a Product on {string} page")
    public void iClickOnForAProductOnPage(String addToCartButton, String page) {
        Inventory inventory = new Inventory(driver);
        String product1=testdata_map.get("Product1");
        inventory.addItemToCart(product1);
        String product2=testdata_map.get("Product2");
        inventory.addItemToCart(product2);
    }

    @And("I verify Product is added to cart")
    public void iVerifyProductIsAddedToCart() {
        Inventory inventory = new Inventory(driver);
        String validateNumberOfItems= inventory.validateNumberOfItemAddedToCart();
        System.out.println(validateNumberOfItems + " Items added to cart");

        Cart cartList = new Cart(driver);
        List<String> cartItemList = new ArrayList<>();
        cartItemList=cartList.getItemsInCart();
        boolean compareItemFlag= true;
        List<String> itemsListFromTestData= new ArrayList<>();
        itemsListFromTestData.add(testdata_map.get("Product1"));
        itemsListFromTestData.add(testdata_map.get("Product2"));
        for (int i=0; i<cartItemList.size(); i++){
            if (! cartItemList.get(i).equalsIgnoreCase(itemsListFromTestData.get(i)))
                compareItemFlag = false;
        }
        if (compareItemFlag){
            System.out.println("Items added to cart validation passed");
        }
        else
            System.out.println("Items added to cart validation failed");



    }

    @And("I click on cart link on {string} page")
    public void iClickOnCartLinkOnPage(String arg0) {
        Inventory inventory = new Inventory(driver);
        inventory.shoppingCartClick();
    }
}
