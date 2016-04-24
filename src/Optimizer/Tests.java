package Optimizer;

/**
 * Author: Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 *
 * Date: 04/26/2016
 *
 * Course: CS 410 - 01, Spring 2016
 *
 * Assignment: Software Engineering Project
 *
 * Description: This program is a shelving space management program that
 * utilizes an SQLite relational database management system to allow users to
 * input and view information about stores and products, and then uses that
 * information to optimize the shelving space utilized for each product within a
 * store. It allows users to add and remove products or stores and also allows a
 * user to optimize the product layout for a store and display a report
 * containing the layout information.
 */
/**
 * This class contains a main method that allows testing of methods in the 
 * program. It prints out the status of each test and whether or not it passed
 * or failed.
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Tests {
    
    private static Optimizer optimizer = new Optimizer();
    private static final String DIVIDER = 
            "\n******************************************\n";
    
    public static void main(String[] args) {
        //Run connect to database test and print results
        System.out.println(connectToDatabaseTest());
        
        
        //The add product test is run twice to check the behavior of the program
        //when there is already a product with the same primary key in the
        //database
        System.out.println(addProductTest());
        System.out.println(addProductTest());
        
        //Run determine product ID test and print results
        System.out.println(determineProductIDTest());
        
        
        //Remove product test is run twice to check behavior when the product is
        //already removed
        System.out.println(removeProductTest());
        System.out.println(removeProductTest());
        
        
        //Run determine product ID test again once product is removed to check
        //behavior when product is not in database
        System.out.println(determineProductIDTest());
        
        //Run add store test and print results
        System.out.println(addStoreTest());
        
        
        //Run generate report test and print results
        System.out.println(generateReportTest());
        
        //Run get store test and print results
        System.out.println(getStoreTest());
        
        //Run determine store id test and print results
        System.out.println(determineStoreIDTest());
        
        //Run remove store test and print results
        System.out.println(removeStoreTest());
        
        //Run get produclt list test and print results
        System.out.println(getProductListTest());
        
        //Run get store number list test and print results
        System.out.println(getStoreNumberListTest());
        
        //Run determine type id test and print results
        System.out.println(determineTypeIDTest());
        
        //Run determine type test and print results
        System.out.println(determineTypeTest());
        
        //Run fill units test and print results
        System.out.println(fillUnitsTest());
        
        //Run unit print test and print results
        System.out.println(unitPrintTest());
    }
    
    /**
     * This method tests whether the add product method works
     * 

     * @return Status of test
     */
    public static String addProductTest() {
        System.out.println("Testing Add product");
        try {
            optimizer.addProduct("12345", "Test Product", "Test Type", "1", "1",
                    "1");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Add Product Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER + "Add Product Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the remove product method works
     * 

     * @return Status of test
     */
    public static String removeProductTest() {
        System.out.println("Testing Remove Product");
        try {
            optimizer.removeProduct("12345");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "\nRemove Product Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER + "Remove Product Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the add store method works
     * 

     * @return Status of test
     */
    public static String addStoreTest() {
        System.out.println("Testing Add Store");
        try {
            optimizer.addStore("ST101", "Test Store", "100", "25", "25", "25", 
                    "25");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "\nAdd Store  Test"
                    + " Failed" + DIVIDER;
        }
        return DIVIDER + "Add Store  Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the remove store method works
     * 
     * @return Status of test
     */
    public static String removeStoreTest() {
        System.out.println("Testing Remove Store");
        try {
            optimizer.removeStore("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Remove Store  Test "
                    + "Failed" + DIVIDER;
        }
        return DIVIDER + "Remove Store  Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the connect to database method works
     * 
     * @return Status of test
     */
    public static String connectToDatabaseTest() {
        System.out.println("Testing Connection to Database");
        try {
            optimizer.connectToDatabase();
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Connect To Database Test Failed"
                    + DIVIDER;
        }
        return DIVIDER + "Connect To Database Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the get product list method works
     * 
     * @return Status of test
     */
    public static String getProductListTest() {
        System.out.println("Testing get Product List");
        try {
            optimizer.getProductList();
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Get Product List Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Get Product List Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the get store number list method works
     * 
     * @return Status of test
     */
    public static String getStoreNumberListTest() {
        System.out.println("Testing get Store Number List");
        try {
            System.out.println(optimizer.getStoreNumberList());
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Get Store Number List Test "
                    + "Failed" + DIVIDER;
        }
        return DIVIDER +"Get Store Number List Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the determine type id method works
     * 
     * @return Status of test
     */
    public static String determineTypeIDTest() {
        System.out.println("Testing Determine Type ID");
        try {
            System.out.println("\nTesting for Pet ID Type:");
            System.out.println("Type ID: " + optimizer.determineTypeID("Pet"));
            
            System.out.println("\nTesting for Food ID Type:");
            System.out.println("Type ID: " + optimizer.determineTypeID("Food"));
            
            System.out.println("\nTesting for Clothing ID Type:");
            System.out.println("Type ID: " + optimizer.determineTypeID(
                    "Clothing"));
            
            System.out.println("\nTesting for Cleaning Supplies ID Type:");
            System.out.println("Type ID: " + optimizer.determineTypeID(
                    "Cleaning Supplies"));
            
            System.out.println("\nTesting for Invalid ID Type:");
            System.out.println("Type ID: " + optimizer.determineTypeID(
                    "Invalid"));
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Determine Type ID Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Determine Type ID Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the determine type method works
     * 
     * @return Status of test
     */
    public static String determineTypeTest() {
        System.out.println("Testing Determine Type");
        try {
            System.out.println("\nTesting for Product Type 1:");
            System.out.println("Type is: " + optimizer.determineType(1));
            System.out.println("\nTesting for Product Type 2:");
            System.out.println("Type is: " + optimizer.determineType(2));
            System.out.println("\nTesting for Product Type 3:");
            System.out.println("Type is: " + optimizer.determineType(3));
            System.out.println("\nTesting for Product Type 4:");
            System.out.println("Type is: " + optimizer.determineType(4));
            System.out.println("\nTesting for Product Type 5:");
            System.out.println("Type is: "+ optimizer.determineType(5));
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Determine Type Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Determine Type Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the determine product id method works
     * 
     * @return Status of test
     */
    public static String determineProductIDTest() {
        System.out.println("Testing Determine Product ID");
        try {
            optimizer.determineProductID("12345");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Determine Product ID Test Failed"
                    + DIVIDER;
        }
        return DIVIDER +"Determine Product ID Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the determine store id method works
     * 
     * @return Status of test
     */
    public static String determineStoreIDTest() {
        System.out.println("Testing Determine Store ID");
        try {
            optimizer.determineStoreID("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Determine Store ID Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Determine Store ID Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the generate report method works
     * 
     * @return Status of test
     */
    public static String generateReportTest() {
        try {
            System.out.println("Testing Generate Report, Sorting by Width");
            System.out.println(optimizer.generateReport(true, "ST101", 
                    "Test Store", 100));
            System.out.println("Testing Generate Report, Sorting by Height");
            System.out.println(optimizer.generateReport(false, "ST101", 
                    "Test Store", 100));   
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Generate Report Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Generate Report Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the get store method works
     * 
     * @return Status of test
     */
    public static String getStoreTest() {
        System.out.println("Testing Get Store Test");
        try {
            optimizer.getStore("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Get Store Test Failed" + DIVIDER;
        }
        return DIVIDER +"Get Store Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the fill units method works
     * 
     * @return Status of test
     */
    public static String fillUnitsTest() {
        try {
            System.out.println("Testing Fill Units, Sorting by Width");
            optimizer.fillUnits(true);
            System.out.println("Testing Fill Units, Sorting by Height");
            optimizer.fillUnits(false);
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Fill Units Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Fill Units Test Passed" + DIVIDER;
    }
    
    /**
     * This method tests whether the unit print method works
     * 
     * @return Status of test
     */
    public static String unitPrintTest() {
        System.out.println("Testing Unit Print");
        try {
            optimizer.unitPrint("ST101", "Store Information Goes Here");
        }
        catch (Exception e) {
            return e.getMessage() + DIVIDER + "Unit Print Test Failed" + 
                    DIVIDER;
        }
        return DIVIDER +"Unit Print Test Passed" + DIVIDER;
    }
}
