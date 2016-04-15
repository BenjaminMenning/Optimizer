package Optimizer;

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
public class Product {
    private double height;
    private double width;
    private double depth;
    private String productID;
    private String productNumber;
    private String productType;
    private String productName;
//    private ProductImage gui;

    public Product() {
        
    }

    public Product(String productNumber, String productName, 
            String productType, double height, double width, double depth) 
    {
        this.productID = productID;
        this.productNumber = productNumber;
        this.productName = productName;
        this.productType = productType;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public void setProductNumber(String productNumber)
    {
        this.productNumber = productNumber;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return depth;
    }

    public String getProductID() {
        return productID;
    }
    
    public String getProductNumber()
    {
        return productNumber;
    }

    public String getProductType() {
        return productType;
    }
    
    public String getProductName()
    {
        return productName;
    }

    @Override
    public String toString() {
        return  String.format("\t%-16s%-20s%-20s%-16s%-16s%-16s\n", productNumber, productName, productType, height, width, depth);
    }
}