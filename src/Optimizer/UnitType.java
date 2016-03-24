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
public class UnitType {
    private String unitTypeID;
    private String type; //food pet clothing cleaning
    private int totalUnitsOfType; // total amount of units allocated to type

    public UnitType(String unitTypeID, String type, int totalUnitsOfType) {
        this.unitTypeID = unitTypeID;
        this.type = type;
        this.totalUnitsOfType = totalUnitsOfType;
    }

    public String getUnitTypeID() {
        return unitTypeID;
    }

    public String getType() {
        return type;
    }

    public int getTotalUnitsOfType() {
        return totalUnitsOfType;
    }

    public void setUnitTypeID(String unitTypeID) {
        this.unitTypeID = unitTypeID;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setTotalUnitsOfType(int totalUnitsOfType) {
        this.totalUnitsOfType = totalUnitsOfType;
    }

    @Override
    public String toString() {
        return "unitType{" + "unitTypeID=" + unitTypeID + ", type=" + type 
                + ", totalUnitsOfType=" + totalUnitsOfType + '}';
    }
    
    
    
}
