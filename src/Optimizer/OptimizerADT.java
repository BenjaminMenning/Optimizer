package Optimizer;

import java.sql.SQLException;
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
 *
 * @author bmenning13
 */
public interface OptimizerADT 
{
    
    /**
     * This method adds a product to the database by running an SQL query with
     * the provided parameters.
     * 
     * @param productNumber the String to be assigned as the product number
     * @param productName   the String to be assigned as the product name
     * @param productType   the String to be assigned as the product type
     * @param height    the String to be assigned as the product height
     * @param width the String to be assigned as the product width
     * @param depth the String to be assigned as the product depth
     * @throws SQLException if SQL database encounters an error
     */
    public void addProduct(String productNumber, String productName, 
            String productType, String height, String width, String depth) 
            throws SQLException;

    /**
     * This method adds a store to the database by running an SQL query with the
     * provided parameters.
     * 
     * @param storeNumber   the String to be assigned as the store number
     * @param storeName the String to be assigned as the store name
     * @param totalUnits the String to be assigned as the total units
     * @param petUnits  the String to be assigned as the pet units
     * @param foodUnits the String to be assigned as the food units
     * @param clothingUnits the String to be assigned as the clothing units
     * @param cleaningUnits the String to be assigned as the cleaning units
     * @throws SQLException if SQL database encounters an error
     */
    public void addStore(String storeNumber, String storeName, 
            String totalUnits, String petUnits, String foodUnits, 
            String clothingUnits, String cleaningUnits) throws SQLException;

    /**
     * This method connects to the Optimizer database.
     * 
     */
    public void connectToDatabase();

    /**
     * This method retrieves the product ID from the database based on the 
     * provided product number.
     * 
     * @param productNumber the String to be assigned as the product number
     * @return  String  returns a String containing the product ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineProductID(String productNumber) throws SQLException;

    /**
     * This method retrieves the store ID from the database based on the 
     * provided store number.
     * 
     * @param storeNumber the String to be assigned as the store number
     * @return  String  returns a String containing the store ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineStoreID(String storeNumber) throws SQLException;

    /**
     * This method retrieves the type of a product from the database based on 
     * the type ID.
     * 
     * @param typeID the String to be assigned as the type ID
     * @return  String  returns a String containing the type
     * @throws SQLException if SQL database encounters an error
     */
    public String determineType(int typeID) throws SQLException;

    /**
     * This method retrieves the type ID of a product from the database based on
     * the type.
     * 
     * @param type  the String to be assigned as the type
     * @return  String  returns a String containing the type ID
     * @throws SQLException if SQL database encounters an error
     */
    public String determineTypeID(String type) throws SQLException;

    /**
     * This method generates a report containing the store layout information. 
     * The store layout information includes the products contained on each 
     * shelf of each shelving unit within a store.
     * 
     * @param isWidthSort   the 
     * @param storeNumber
     * @param name
     * @param totalUnits
     * @return
     */
    public String generateReport(boolean isWidthSort, String storeNumber, String name, int totalUnits);

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<Product> getProductList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getProductNumberList() throws SQLException;

    /**
     *
     * @return
     * @throws SQLException
     */
    public ArrayList<String> getStoreNumberList() throws SQLException;

    /**
     *
     * @param prodList
     * @return
     */
    public ArrayList<Product> heightSorter(ArrayList<Product> prodList);

    /**
     *
     * @param productNumber
     * @throws SQLException
     */
    public void removeProduct(String productNumber) throws SQLException;

    /**
     *
     * @param storeNumber
     * @throws SQLException
     */
    public void removeStore(String storeNumber) throws SQLException;

    /**
     *
     * @param prodList
     * @return
     */
    public ArrayList<Product> widthSorter(ArrayList<Product> prodList);    
}