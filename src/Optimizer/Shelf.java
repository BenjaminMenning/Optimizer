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

    double shelfHeight;
    double shelfNumber;
    private ArrayList<Product> productList = new ArrayList<>();

    public Shelf(double shelfHeight, double shelfNumber) {
        this.shelfHeight = shelfHeight;
        this.shelfNumber = shelfNumber;
    }

    public double fillShelfByHeight(ArrayList<Product> widthList) {
        double tallest = 0;
        double listSize = 0;
        double totalProductWidth = 0;

        listSize = widthList.size();

        for (double i = 0; i < listSize; i++) {
            if (totalProductWidth < width) {
                productList.add(widthList.get(0));
                totalProductWidth += widthList.get(0).getWidth();

                if (tallest < widthList.get(0).getHeight()) {
                    tallest = widthList.get(0).getHeight();
                }//end if                 

                widthList.remove(0);
            }//end if
            else {
                break;
            }//end else            
        }//end for       
                
        
        this.shelfHeight = tallest;
                
        return tallest;

        
    }//end fillShelfByHeight

    public Shelf() {
        
    }//end shelf

    public double getShelfHeight() {
        return shelfHeight;
    }

    public double getShelfNumber() {
        return shelfNumber;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setShelfHeight(double shelfHeight) {
        this.shelfHeight = shelfHeight;
    }

    public void setShelfNumber(double shelfNumber) {
        this.shelfNumber = shelfNumber;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    
    
    @Override
    public String toString() {
        return ("Shelf Number: " + shelfNumber +
                "\nShelf Height is: " + shelfHeight
                + "\nPlacement      Product Number      Product Name    Product Type        Product Height  Product Width   Product Depth\n\t" 
                + productList + "\n\n");
        
        
        
    }
    
    
    //Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth
    
    
}//end class
