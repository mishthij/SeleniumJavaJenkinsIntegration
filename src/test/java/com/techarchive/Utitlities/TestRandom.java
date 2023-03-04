package com.techarchive.Utitlities;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class TestRandom {

    public static void main(String[] args) throws IOException, InvalidFormatException {
        Map<String, String> dtmap= new HashMap<String, String>();
        dtmap= ExcelReader.getTestData("Login to Application","C:\\Cucumber Project\\CucumberMavenReporting-master\\src\\test\\resources\\TestData.xlsx","SauceLabs");
        System.out.println(dtmap.get("Password"));
      //  for(HashMap.Entry entry:dtmap.entrySet()) {


       System.out.println("Vallllo=== " + TestRandom.getConfigValues("ExcelsheetPath"));
     //   }





    }



    public  static String getConfigValues(String elementValue) {
        Properties properties =null;
        String configpath;
        if (properties == null) {
            try {

                File file = new File( "C:\\Cucumber Project\\CucumberMavenReporting-master\\src\\test\\resources\\Config\\testQA.properties");


                FileInputStream fileInput = new FileInputStream(file);
                properties = new Properties();
                properties.load(fileInput);

            } catch (IOException e) {

                System.out.println("IOException"+e.getMessage());
            }
        }
        return properties.getProperty(elementValue);
    }
}
