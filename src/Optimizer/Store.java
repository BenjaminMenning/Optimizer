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
    private int totalUnits; // 50 for food 
    private ArrayList<UnitType> storeUnitsTypeList = new ArrayList<UnitType>();
    private ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<Unit> unitList = new ArrayList<Unit>();
    
    public void setStoreID(String storeID)
    {
        this.storeID = storeID;
    }
    
    public String getStoreID()
    {
        return storeID;
    }
    
    public void setStoreNumber(String storeNumber)
    {
        this.storeNumber = storeNumber;
    }
    
    public String getStoreNumber()
    {
        return storeNumber;
    }
    
    public void setTotalUnits(int totalUnits)
    {
        this.totalUnits = totalUnits;
    }
    
    public int getTotalUnits()
    {
        return totalUnits;
    }
    
    public void setStoreUnitsType(ArrayList<UnitType> typeList)
    {
        
    }
    
    public ArrayList<UnitType> getStoreUnitsType()
    {
        ArrayList<UnitType> blah = new ArrayList<UnitType>();
        return blah;
    }

//    public void addProduct(Product product)
//    {
//    }
//
//    public Product removeProduct(String productID)
//    {
//        Product product3 = new Product(0, 0, 0, "", "");
//        return product3;
//    }
//    
//    public void addUnit(Unit unit)
//    {
//        
//    }
//
//    public void removeUnit(String unitID)
//    {
//        
//    }
}
