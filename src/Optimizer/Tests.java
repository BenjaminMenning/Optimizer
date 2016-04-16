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
        System.out.println(removeProductTest());
        System.out.println(addStoreConfigurationTest());
        System.out.println(removeStoreConfigurationTest());
        System.out.println(getProductListTest());
    }
    
    public static String addProductTest() {
        try {
            optimizer.addProduct("12345", "Test Product", "Test Type", "1", "1", "1");
        }
        catch (Exception e) {
            return e.getMessage() + "\nAdd Product Test Failed";
        }
        return "Add Product Test Passed";
    }
    
    public static String removeProductTest() {
        try {
            optimizer.removeProduct("12345");
        }
        catch (Exception e) {
            return e.getMessage() + "\nRemove Product Test Failed";
        }
        return "Remove Product Test Passed";
    }
    
    public static String addStoreConfigurationTest() {
        try {
            optimizer.addStore("ST101", "Test Store", "100", "25", "25", "25", "25");
        }
        catch (Exception e) {
            return e.getMessage() + "\nAdd Store Configuration Test Failed";
        }
        return "Add Store Configuration Test Passed";
    }
    
    public static String removeStoreConfigurationTest() {
        try {
            optimizer.removeStore("ST101");
        }
        catch (Exception e) {
            return e.getMessage() + "\nRemove Store Configuration Test Failed";
        }
        return "Remove Store Configuration Test Passed";
    }
    
    public static String connectToDatabaseTest() {
        try {
            optimizer.connectToDatabase();
        }
        catch (Exception e) {
            return e.getMessage() + "\nConnect To Database Test Failed";
        }
        return "Connect To Database Test Passed";
    }
    
    public static String getProductListTest() {
        try {
            optimizer.getProductList();
        }
        catch (Exception e) {
            return e.getMessage() + "\nGet Product List Test Failed";
        }
        return "Get Product List Test Passed";
    }
}
