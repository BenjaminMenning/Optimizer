package Optimizer;


import Optimizer.GUI.OptimizerGUI;
import java.sql.SQLException;

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
public class OptimizerClient 
{
    
    /** 
     * This method is a main method that allows a user to run the Optimizer
     * shelving space management program.
     * 
     * @throws SQLException if SQL database encounters an error
     */   
   public static void main(String[] args) throws SQLException
   {
        Optimizer optimizer = new Optimizer();
        OptimizerGUI optimizerGUI = new OptimizerGUI(optimizer);
   }
}