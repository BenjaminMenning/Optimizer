package Optimizer;

import java.util.ArrayList;

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
 * This class contains a main method that allows a user to run the Optimizer
 * shelving space management program.
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Shelf {

    private final double width = 48;
    private final double height = 84;
    private final double depth = 24;
    private final double physicalShelfHeight = 2;

    double shelfHeight;
    int shelfNumber;

    private ArrayList<Product> productList = new ArrayList<>();

    /**
     * This constructor initializes the shelf height and shelf number
     * 
     * @param shelfHeight The height of the shelf
     * @param shelfNumber The number of the shelf, used as an identifier
     */
    public Shelf(double shelfHeight, int shelfNumber) {
        this.shelfHeight = shelfHeight;
        this.shelfNumber = shelfNumber;

    }

    /**
     * This method fills a shelf by height by sorting the product list then
     * adding products shortest to tallest in the shelf
     * 
     * @param heightList This is a list of products sorted by height
     * @param tempUnitHeight This is used for setting the shelf height
     * @return tallest product height as shelf height
     */
    public double fillShelfByHeight(ArrayList<Product> heightList, double tempUnitHeight) {
        double tallest = 0;
        double listSize = 0;
        double totalProductWidth = 0;

        listSize = heightList.size();
        if (listSize > 0) {
            for (double i = 0; i < listSize; i++) {
                if (totalProductWidth < width) {
                    productList.add(heightList.get(0));
                    totalProductWidth += heightList.get(0).getWidth();

                    if (tallest < heightList.get(0).getHeight()) {
                        tallest = heightList.get(0).getHeight();
                    }//end if                 

                    heightList.remove(0);
                }//end if
                else {
                    break;
                }//end else            
            }//end for                                               

            if (tempUnitHeight > 0) {
                shelfHeight = tempUnitHeight;
            }

            return tallest;
        }
        return -1;

    }//end fillShelfByHeight

    /**
     * This is the default constructor, it does not initialize any data members
     */
    public Shelf() {

    }//end shelf

    /**
     * This returns the index as the shelf count
     * 
     * @param index The index of ? //Ben or Jarrud, this method does nothing?
     * @return index As shelf count?
     */
    public double shelfCount(int index) {

        return index;
    }

    /**
     * This method returns the shelf height
     * 
     * @return The shelf height
     */
    public double getShelfHeight() {
        return shelfHeight;
    }

    /**
     * This method returns the shelf number
     * 
     * @return The shelf number
     */
    public int getShelfNumber() {
        return shelfNumber;
    }

    /**
     * This method returns the product list
     * 
     * @return The product list
     */
    public ArrayList<Product> getProductList() {
        return productList;
    }

    /**
     * This method sets the shelf height based on the passed parameter
     * 
     * @param shelfHeight This is the new shelf height
     */
    public void setShelfHeight(double shelfHeight) {
        this.shelfHeight = shelfHeight;
    }

    /**
     * This method sets the shelf number based on the passed parameter
     * 
     * @param shelfNumber This is the new shelf number
     */
    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    /**
     * This method sets the product list based on the passed parameter
     * 
     * @param productList This is the new product list
     */
    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    @Override
    public String toString() {

        String returnString = "";

        returnString = "Shelf Number: " + shelfNumber
                + "\nShelf Height is: " + shelfHeight
                + "\nPlacement       Product Number       Product Name         Product Type        Product Height  Product Width   Product Depth\n";

        String letterHeight = "";
        int prodCount = 1;
        for (Product product : productList) {

            if (shelfNumber == 1) {
                letterHeight = "A";
            } else if (shelfNumber == 2) {
                letterHeight = "B";
            } else if (shelfNumber == 3) {
                letterHeight = "C";
            } else if (shelfNumber == 4) {
                letterHeight = "D";
            }

            returnString += letterHeight + prodCount + "          " + product;
            prodCount++;
        }

        return returnString;
    }

}//end class
