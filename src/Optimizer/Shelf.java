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

    public Shelf(double shelfHeight, int shelfNumber) {
        this.shelfHeight = shelfHeight;
        this.shelfNumber = shelfNumber;

    }

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

    public Shelf() {

    }//end shelf

    public double shelfCount(int index) {

        return index;
    }

    public double getShelfHeight() {
        return shelfHeight;
    }

    public int getShelfNumber() {
        return shelfNumber;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setShelfHeight(double shelfHeight) {
        this.shelfHeight = shelfHeight;
    }

    public void setShelfNumber(int shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

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
