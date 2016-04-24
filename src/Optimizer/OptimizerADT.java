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
 * This interface details all of the methods to be implemented by the Optimizer 
 * class. It contains methods to be implemented for adding products and stores, 
 * removing products and stores, generating a report, and many other functions. 
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
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
     * @param isWidthSort   the Boolean to be assigned as true if width sort, 
     *                      false otherwise
     * @param storeNumber   the String to be assigned as the store number
     * @param name  the String to be assigned as the name of the store
     * @param totalUnits    the int to be assigned as the total shelving units
     * @return  String  returns a String containing the report information
     */
    public String generateReport(boolean isWidthSort, String storeNumber, String name, int totalUnits);

    /**
     * This method retrieves a list of products from the database.
     * 
     * @return  ArrayList<Product>  returns an ArrayList containing the products
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<Product> getProductList() throws SQLException;

    /**
     * This method retrieves a list of product numbers from the database.
     * 
     * @return  ArrayList<String>  returns an ArrayList containing the product
     *                             numbers
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getProductNumberList() throws SQLException;

    /**
     * This method retrieves a list of store numbers from the database.
     * 
     * @return  ArrayList<String>  returns an ArrayList containing the store
     *                             numbers
     * @throws SQLException if SQL database encounters an error
     */
    public ArrayList<String> getStoreNumberList() throws SQLException;

    /**
     * This method sorts a list of products by height.
     * 
     * @param prodList  the ArrayList to be assigned as the product list
     * @return  ArrayList<Product> returns an ArrayList containing the products
     */
    public ArrayList<Product> heightSorter(ArrayList<Product> prodList);

    /**
     * This method removes a product from the database by performing an SQL 
     * query with the product number of the product.
     * 
     * @param productNumber the String to be assigned as the product number
     * @throws SQLException if SQL database encounters an error
     */
    public void removeProduct(String productNumber) throws SQLException;

    /**
     * This method removes a store from the database by performing an SQL 
     * query with the store number of the store.
     * 
     * @param storeNumber the String to be assigned as the store number
     * @throws SQLException if SQL database encounters an error
     */
    public void removeStore(String storeNumber) throws SQLException;

    /**
     * This method sorts a list of products by width.
     * 
     * @param prodList  the ArrayList to be assigned as the product list
     * @return  ArrayList<Product> returns an ArrayList containing the products
     */
    public ArrayList<Product> widthSorter(ArrayList<Product> prodList);
    
    /**
     * This method retrieves a Store object from the database.
     * 
     * @param storeNum  the String to be assigned as the store number
     * @return  Store   returns a Store object
     * @throws SQLException if SQL database encounters an error
     */
    public Store getStore(String storeNum) throws SQLException;
}