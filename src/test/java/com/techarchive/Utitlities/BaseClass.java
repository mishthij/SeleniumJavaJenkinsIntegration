package com.techarchive.Utitlities;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
public class BaseClass {
    public static WebDriver driver  = null;
    public static Map<String, String> testdata_map= new HashMap<String, String>();


    public static WebDriver initilize()
    {


        //Use Of Singleton Concept and Initilize webDriver
        if(driver == null)
        {
            if(ConsttantVariables.browserName.equalsIgnoreCase("chrome"))
            {
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
            else if(ConsttantVariables.browserName.equalsIgnoreCase("chrome headless"))
            {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--headless");
                driver=new ChromeDriver(options);
            }
            else if(ConsttantVariables.browserName.equalsIgnoreCase("Firefox"))
            {
                WebDriverManager.firefoxdriver().setup();
                driver=new FirefoxDriver();
            }
            else if(ConsttantVariables.browserName.equalsIgnoreCase("IE"))
            {
                WebDriverManager.iedriver().setup();
                driver=new EdgeDriver();
            }

        }

        //Perform Basic Operations
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        return driver;
    }
    public static void quit()
    {
        driver.quit();
        driver=null; // we destroy the driver object after quit operation
    }
    public static void close()
    {
        driver.close();
        driver=null;  // we destroy the driver object after quit operation
    }
    public  static void openurl(String URL)
    {
        driver.get(URL);
    }




}
