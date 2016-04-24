package Optimizer;



import java.util.ArrayList;

/** 
 * Author:          Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * 
 * Date:            04/26/2016 
 *                
 * Course:          CS 410 - 01, Spring 2016
 * 
 * Assignment:      Software Engineering Project
 * 
 * Description:     This program is a shelving space management program that 
 *                  utilizes an SQLite relational database management system to 
 *                  allow users to input and view information about stores and 
 *                  products, and then uses that information to optimize the 
 *                  shelving space utilized for each product within a store.
 *                  It allows users to add and remove products or stores and 
 *                  also allows a user to optimize the product layout for a 
 *                  store and display a report containing the layout 
 *                  information. 
 */

/** 
 * This class contains a main method that allows a user to run the Optimizer 
 * shelving space management program.
 * 
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016 
 */
public class Store {
    
    private String storeID;
    private String storeNumber;
    private String storeName;
    private int totalUnits; // 50 for food 
    
    private ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<Unit> unitList = new ArrayList<Unit>();
    
    /**
     * This method sets the store id to the passed parameter
     * 
     * @param storeID This is the new store ID
     */
    public void setStoreID(String storeID)
    {
        this.storeID = storeID;
    }
    
    /**
     * This method returns the store id of the store
     * 
     * @return The Store ID
     */
    public String getStoreID()
    {
        return storeID;
    }
    
    /**
     * This method sets the store name based on the passed parameter
     * 
     * @param storeName The new store name 
     */
    public void setStoreName(String storeName)
    {
        this.storeName = storeName;
    }
    
    /**
     * This method returns the store name
     * 
     * @return The name of the store
     */
    public String getStoreName()
    {
        return storeName;
    }
    
    /**
     * This method sets the store number based on the passed parameter
     * 
     * @param storeNumber The new store number
     */
    public void setStoreNumber(String storeNumber)
    {
        this.storeNumber = storeNumber;
    }
    
    /**
     * This method returns the store number
     * 
     * @return the store number
     */
    public String getStoreNumber()
    {
        return storeNumber;
    }
    
    /**
     * This method sets the total number of units
     * 
     * @param totalUnits the new number of units
     */
    public void setTotalUnits(int totalUnits)
    {
        this.totalUnits = totalUnits;
    }
    
    /**
     * This method returns the total number of units 
     * 
     * @return the total number of units
     */
    public int getTotalUnits()
    {
        return totalUnits;
    }
}