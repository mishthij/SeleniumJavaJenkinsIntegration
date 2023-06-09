package com.techarchive.Utitlities;



    import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class ReadConfigProperties {
        static List<String> contentFromConfigFile = new ArrayList<String>();
        static List<String> listOfSuiteNames = new ArrayList<String>();
        static Map<String, String> map = new HashMap<String, String>();
        public static String configpath;
        Properties properties = null;

        public String getConfigValues(String elementValue) {

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

        public Map<String, String> readConfigFile() {
            String line;
            if (properties == null) {
                try {
                    File file = new File(configpath + "\\config.properties");

                    FileInputStream fileInput = new FileInputStream(file);

                    BufferedReader buffReader = new BufferedReader(
                            new InputStreamReader(fileInput));
                    while ((line = buffReader.readLine()) != null) {

                        String[] temp3 = line.split("=");
                        if (temp3.length > 1) {
                            String key = temp3[0];
                            String value = temp3[1];

                            if (key.contains("TestSuite_SHEETNAME")) {
                                map.put(key, value);
                            }

                        }

                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

            }
            return map;
        }

    }

