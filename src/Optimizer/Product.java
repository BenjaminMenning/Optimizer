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
 * This class contains methods for getting and setting the information of a 
 * Product object, including the Product name, number, type, width, depth, and
 * height.
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Product {

    private double height;
    private double width;
    private double depth;
    private String productID;
    private String productNumber;
    private String productType;
    private String productName;
//    private ProductImage gui;

    /**
     * This is the default constructor, it does not initialize any data members
     */
    public Product() {

    }

    /**
     *This constructor initializes the values for a product
     * 
     * @param productNumber The product number of the product
     * @param productName The product name of a product
     * @param productType The type of a product
     * @param height The height of a product
     * @param width The width of a product
     * @param depth The depth of a product
     */
    public Product(String productNumber, String productName,
            String productType, double height, double width, double depth) {
        this.productID = productID;
        this.productNumber = productNumber;
        this.productName = productName;
        this.productType = productType;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    /**
     * This method sets the height of the product based on the passed parameter
     * 
     * @param height the double that height is being set to
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * This method sets the width of the product based on the passed parameter
     * 
     * @param width the double that width is being set to
     */
    public void setWidth(double width) {
        this.width = width;
    }

    /**
     * This method sets the depth of the product based on the passed parameter
     * 
     * @param depth the double that depth is being set to
     */
    public void setDepth(double depth) {
        this.depth = depth;
    }

    /**
     * This method sets the product ID of the product based on the passed 
     * parameter
     * 
     * @param productID the String that productID is being set to
     */
    public void setProductID(String productID) {
        this.productID = productID;
    }

    /**
     * This method sets the product number of the product based on the passed 
     * parameter
     * 
     * @param productNumber the String that productNumber is being set to
     */
    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    /**
     * This method sets the product type of the product based on the passed 
     * parameter
     * 
     * @param productType the String that productType is being set to
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * This method sets the product name of the product based on the passed 
     * parameter
     * 
     * @param productName the String that productName is being set to
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * This method returns the height of the product
     * 
     * @return height of the product
     */
    public double getHeight() {
        return height;
    }

    /**
     * This method returns the width of the product
     * 
     * @return width of the product
     */
    public double getWidth() {
        return width;
    }

    /**
     * This method returns the depth of the product
     * 
     * @return depth of the product
     */
    public double getDepth() {
        return depth;
    }

    /**
     * This method returns the product ID of the product
     * 
     * @return productID of the product
     */
    public String getProductID() {
        return productID;
    }

    /**
     * This method returns the product number of the product
     * 
     * @return productNumber of the product
     */
    public String getProductNumber() {
        return productNumber;
    }

    /**
     * This method returns the product type of the product
     * 
     * @return productType of the product
     */
    public String getProductType() {
        return productType;
    }

    /**
     * This method returns the product name of the product
     * 
     * @return productName of the product
     */
    public String getProductName() {
        return productName;
    }

    @Override
    public String toString() {
        return String.format("\t%-21s%-21s%-20s%-16s%-16s%-16s\n", productNumber, productName, productType, height, width, depth);
    }
}
