/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Optimizer;

/**
 *
 * @author zellefson14
 */
public class Tests {
    
    private static Optimizer optimizer = new Optimizer();
    
    public static void main(String[] args) {
        System.out.println(connectToDatabaseTest());
        System.out.println(addProductTest());
        System.out.println(determineProductIDTest());
        System.out.println(removeProductTest());
        System.out.println(addStoreConfigurationTest());
        System.out.println(generateReportTest());
        System.out.println(determineStoreIDTest());
        System.out.println(removeStoreConfigurationTest());
        System.out.println(getProductListTest());
        System.out.println(getStoreNumberListTest());
        System.out.println(determineTypeIDTest());
        System.out.println(determineTypeTest());
    }
    
    public static String addProductTest() {
        try {
            optimizer.addProduct("12345", "Test Product", "Test Type", "1", "1", "1");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nAdd Product Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" + "Add Product Test Passed" + "\n******************************************\n";
    }
    
    public static String removeProductTest() {
        try {
            optimizer.removeProduct("12345");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nRemove Product Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" + "Remove Product Test Passed" + "\n******************************************\n";
    }
    
    public static String addStoreConfigurationTest() {
        try {
            optimizer.addStore("ST101", "Test Store", "100", "25", "25", "25", "25");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nAdd Store Configuration Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" + "Add Store Configuration Test Passed" + "\n******************************************\n";
    }
    
    public static String removeStoreConfigurationTest() {
        try {
            optimizer.removeStore("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nRemove Store Configuration Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" + "Remove Store Configuration Test Passed" + "\n******************************************\n";
    }
    
    public static String connectToDatabaseTest() {
        try {
            optimizer.connectToDatabase();
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nConnect To Database Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" + "Connect To Database Test Passed" + "\n******************************************\n";
    }
    
    public static String getProductListTest() {
        try {
            optimizer.getProductList();
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nGet Product List Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Get Product List Test Passed" + "\n******************************************\n";
    }
    
    public static String getStoreNumberListTest() {
        try {
            optimizer.getStoreNumberList();
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nGet Store Number List Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Get Store Number List Test Passed" + "\n******************************************\n";
    }
    
    public static String determineTypeIDTest() {
        try {
            optimizer.determineTypeID("Pet");
            optimizer.determineTypeID("Food");
            optimizer.determineTypeID("Clothing");
            optimizer.determineTypeID("Cleaning Supplies");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nDetermine Type ID Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Determine Type ID Test Passed" + "\n******************************************\n";
    }
    
    public static String determineTypeTest() {
        try {
            optimizer.determineType(1);
            optimizer.determineType(2);
            optimizer.determineType(3);
            optimizer.determineType(4);
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nDetermine Type Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Determine Type Test Passed" + "\n******************************************\n";
    }
    
    public static String determineProductIDTest() {
        try {
            optimizer.determineProductID("12345");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nDetermine Product ID Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Determine Product ID Test Passed" + "\n******************************************\n";
    }
    
    public static String determineStoreIDTest() {
        try {
            optimizer.determineStoreID("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nDetermine Store ID Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Determine Store ID Test Passed" + "\n******************************************\n";
    }
    
    public static String generateReportTest() {
        try {
            System.out.println(optimizer.generateReport(true, "ST101", "Test Store", 100));
            System.out.println(optimizer.generateReport(true, "ST101", "Test Store", 100));   
        }
        catch (Exception e) {
            return e.getMessage() + "\n******************************************\n" + "\nGenerate Report Test Failed" + "\n******************************************\n";
        }
        return "\n******************************************\n" +"Generate Report Test Passed" + "\n******************************************\n";
    }
}
