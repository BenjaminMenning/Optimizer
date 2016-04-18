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
public class Unit {
    ArrayList<Shelf> shelves = new ArrayList<Shelf>(4);
      
    public Unit() {
        shelves.add(new Shelf());
        shelves.add(new Shelf());
        shelves.add(new Shelf());
        shelves.add(new Shelf());                        
        
    }
    
    public void fillShelf(ArrayList<Product> heightList) {
        for(Shelf shelf : shelves) {
            shelf.fillShelfByHeight(heightList);             
        }
        
}
    public ArrayList<Shelf> getShelves() {
        
        
        
        return shelves;
        
        
        
        
    }
    
}
