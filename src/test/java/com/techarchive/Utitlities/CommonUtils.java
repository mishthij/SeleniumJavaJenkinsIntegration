package com.techarchive.Utitlities;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.*;


public abstract class CommonUtils {


    public WebDriver _driver;
    public WebDriverWait wait;
    public Actions actions;
    public static Boolean cmpflag=true;

    public CommonUtils() {
        //      _driver = DriverUtils.getFirefoxDriver();
    }

    public void navigateToURL(String URL) {
        System.out.println("Navigating to: " + URL);
        System.out.println("Thread id = " + Thread.currentThread().getId());

        try {
            _driver.navigate().to(URL);
        } catch (Exception e) {
            System.out.println("URL did not load: " + URL);

        }
    }

    public String getPageTitle() {
        try {
            System.out.print(String.format("The title of the page is: %s\n\n", _driver.getTitle()));
            return _driver.getTitle();
        } catch (Exception e) {
            System.out.println("Current page title is: %s" + _driver.getTitle());
        }
        return null;
    }

    public WebElement getElement(By selector) {
        try {
            return _driver.findElement(selector);
        } catch (Exception e) {
            System.out.println("Element %s does not exist - proceeding" + selector);
        }
        return null;
    }


    public void sendKeys(By selector, String value) {
        WebElement element = getElement(selector);
        clearField(element);
        try {
            element.sendKeys(value);
        } catch (Exception e) {
            System.out.println("Error in sending [%s] to the following element: [%s]" + value + selector.toString());
        }
    }

    public void clearField(WebElement element) {
        try {
            element.clear();
        } catch (Exception e) {
            System.out.println("The following element could not be cleared: [%s]" + element.getText());
        }
    }

    public void click(By selector) {
        WebElement element = getElement(selector);
        //   waitForElementToBeClickable(selector);
        try {
            element.click();
        } catch (Exception e) {
            System.out.println(("The following element is not clickable: [%s]" + selector));
        }
    }

    public static boolean compareMaps (HashMap <String, String> map1, HashMap <String, String> map2){
        for (Map.Entry<String, String> m : map1.entrySet()) {

            if (map1.size()==map2.size())
            {
                System.out.println(m.getKey() + " " + m.getValue());
                String strkey = m.getKey().toString();
                String strvalue = m.getValue().toString();
                if (map2.containsKey(strkey)) {
                    if (map2.containsValue(strvalue))
                        cmpflag = true;
                } else {
                    cmpflag = false;
                    break;
                }
            }


        }
        return cmpflag;

    }

    public static <K extends Comparable, V> Map<K, V> sortByKeys(Map<K, V> map)
    {
        Map<K, V> treeMap = new TreeMap<>(new Comparator<K>() {
            @Override
            public int compare(K a, K b) {
                return b.compareTo(a);
            }
        });
        treeMap.putAll(map);
        return treeMap;
    }

    public static HashMap<String, String> sortValues(HashMap map)
    {
        List list = new LinkedList(map.entrySet());
//Custom Comparator
        Collections.sort(list, new Comparator()
        {
            public int compare(Object o1, Object o2)
            {
                return ((Comparable) ((Map.Entry) (o1)).getValue()).compareTo(((Map.Entry) (o2)).getValue());
            }
        });
//copying the sorted list in HashMap to preserve the iteration order
        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();)
        {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }
        return sortedHashMap;
    }
}
