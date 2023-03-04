package com.techarchive.Utitlities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
//import org.testng.Reporter;

public  class ExcelReader {
    static Map<String, Workbook> workbooktable = new HashMap<String, Workbook>();
    public static Map<String, Integer> dict = new Hashtable<String, Integer>();
    public static List list = new ArrayList();
    static ReadConfigProperties config = new ReadConfigProperties();

    /**
     * To get the excel sheet workbook
     */
    public static Workbook getWorkbook(String path) {
        Workbook workbook = null;
        if (workbooktable.containsKey(path)) {
            workbook = workbooktable.get(path);
        } else {

            try {


                File file = new File(path);

                workbook = WorkbookFactory.create(file);

                workbooktable.put(path, workbook);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.out.println("FileNotFoundException" + e);
            } catch (InvalidFormatException e) {
                e.printStackTrace();
                System.out.println("InvalidFormatException" + e);
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("IOException" + e);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return workbook;
    }

    /**
     * To get the number of sheets in excel suite
     */
    public static List<String> getNumberOfSheetsinSuite(String testPath) {
        List<String> listOfSheets = new ArrayList<String>();

        Workbook workbook = getWorkbook(testPath);

        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            listOfSheets.add(workbook.getSheetName(i));
        }

        return listOfSheets;
    }

    /**
     * To get the number of sheets in test data sheet
     */
    public static List<String> getNumberOfSheetsinTestDataSheet(String testPath) {
        List<String> listOfSheets = new ArrayList<String>();

        Workbook workbook = getWorkbook(testPath);
        for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
            if (!(workbook.getSheetName(i)).equalsIgnoreCase(config
                    .getConfigValues("TestCase_SheetName"))) {
                listOfSheets.add(workbook.getSheetName(i));

            }
        }
        return listOfSheets;

    }

    /**
     * Get the total rows present in excel sheet
     */
    public static int getRows(String testSheetName, String pathOfFile)
            throws InvalidFormatException, IOException {
        Workbook workbook = getWorkbook(pathOfFile);
        System.out.println("getting total number of rows");

        Sheet sheet = workbook.getSheet(testSheetName);

        return sheet.getLastRowNum();

    }

    /**
     * Get the total columns inside excel sheet
     */
    public static int getColumns(String testSheetName, String pathOfFile)
            throws InvalidFormatException, IOException {
        Workbook workbook = getWorkbook(pathOfFile);
        System.out.println("getting total number of columns");
        Sheet sheet = workbook.getSheet(testSheetName);
        return sheet.getRow(0).getLastCellNum();

    }

    /**
     * Get the column names inside excel sheet
     */
    public static List getColumnNames(String testSheetName, String pathOfFile,
                                      int j) throws InvalidFormatException, IOException {
        Workbook workbook = getWorkbook(pathOfFile);
        Sheet sheet = workbook.getSheet(testSheetName);

        for (int i = 0; i <= j; i++) {
            if (sheet.getRow(0).getCell(i) != null) {
                list.add(sheet.getRow(0).getCell(i).getStringCellValue()
                        .toString());
            }
        }

        return list;

    }

    /**
     * Get the total number of rows for each column inside excel sheet
     */
    public static void getNumberOfRowsPerColumn(String testSheetName,
                                                String pathOfFile, int j) throws InvalidFormatException,
            IOException {
        Workbook workbook = getWorkbook(pathOfFile);
        Sheet sheet = workbook.getSheet(testSheetName);
        int totColumns = sheet.getRow(0).getLastCellNum();
        for (int i = 0; i <= totColumns; i++) {
            if (sheet.getRow(0).getCell(i) != null) {
                list.add(sheet.getRow(0).getCell(i).getStringCellValue()
                        .toString());
            }
        }
    }

    /**
     * Read the content of the cell
     */
    public static String readCell(int rowNum, int colNum, String testSheetName,
                                  String pathOfFile) {
        Workbook workbook;
        String cellValue = null;

        workbook = getWorkbook(pathOfFile);
        Sheet sheet = workbook.getSheet(testSheetName);
        Row row = sheet.getRow(rowNum);
        if (row != null) {
            Cell cell = row.getCell(colNum);
            if (cell != null) {
                DataFormatter dataFormatter = new DataFormatter();
                String data = dataFormatter.formatCellValue(cell);
                cellValue = data;
            }
        }
        return cellValue;
    }
    public static Map<String, String> getTestData(String testcasename,String excelpath,String sheetname ) throws IOException, InvalidFormatException {
        Map<String, String> dataMap = new HashMap<String, String>();
        Workbook workbook;
        String cellValue = null;
        try {
            workbook = WorkbookFactory.create(new File(
                    excelpath));
            Sheet sheet = workbook.getSheet(sheetname);


            int totalrows = sheet.getLastRowNum();
           // int totalcolumns = sheet.getRow(0).getLastCellNum();

            for (int rowval = 0; rowval <= totalrows; rowval++)
                for (int columnval = 1; columnval <= 2; columnval++) {
                    Row row = sheet.getRow(rowval);
                    String cellVal=null;
                    DataFormatter dataFormatter = new DataFormatter();
                    if (row != null) {
                        Cell cell = row.getCell(columnval);
                        if (cell != null) {

                            String data = dataFormatter.formatCellValue(cell);
                            cellVal = data;
                        }
                        if (testcasename.equalsIgnoreCase(cellVal)) {
                            System.out.println("Value found in Excel  " + cellVal + " row found  " + rowval);

                            for (int colval = 2; colval < sheet.getRow(0).getLastCellNum(); colval++) {
                                Row row_1 = sheet.getRow(rowval);
                                Cell cellvelue = row_1.getCell(colval);
                                if (cellvelue!=null) {
                                    //  String data_2 = cellvelue.getStringCellValue().trim();
                                    String key = sheet.getRow(0).getCell(colval).getStringCellValue().toString();
                                    dataMap.put(key, dataFormatter.formatCellValue(cellvelue));
                                }


                            }
                            break;


                        }
                        else
                        {
                            System.out.println("Test Case Name NOT matched");
                        }
                    }
                }



        } catch (InvalidFormatException | IOException e) {

            System.out.println("InvalidFormatException");
        }
        return dataMap;
    }


    /**
     * To clear the worktable and list
     */
    public void clean() {
        workbooktable.clear();
        list.clear();
    }

}
