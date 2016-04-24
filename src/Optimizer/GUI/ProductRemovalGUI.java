package Optimizer.GUI;

import Optimizer.Optimizer;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

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
 * This class constructs the user interface for the Product removal tab within
 * the OptimizerGUI class JFrame.
 * 
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016 
 */
public class ProductRemovalGUI 
{
    // JLabel variables
    protected JLabel productNumberL;
        
    // String and ArrayList of string variables for product numbers
    protected String productNumberStr = "Product Number:";
    protected String errorStr = "<html><body><p style='width: "
            + "200px;'>The Product could not be removed from the database. Please try "
            + "again. </p></body></html>";
    protected String invalidEntryStr0 =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty and "
            + "values entered must match those that are listed. Please "
            + "try again.</p></body></html>";
    protected String validEntryStr0 = "<html><body><p style='width: "
            + "200px;'>The item has been successfully removed from the "
            + "database.</p></body></html>";
    protected String validEntryStr1 = "<html><body><p style='width: "
            + "200px;'>'";
    protected String validEntryStr2
            = "' has been added successfully to the product.</p></body></html>";
    protected ArrayList<String> productNumberList;
        
    // JTextField varables
    protected JComboBox productNumberCB;
    
    // JButton variables
    private JButton removeProductB;

    // JPanel variables
    protected JPanel productNumberP;
    protected JPanel basePanel;
    private JPanel removeProductButtonP;

    
    // Declares Optimizer object
    protected Optimizer optimizer;
    protected ProductRemovalGUI productRemovalGUI;
    
    // JButton Handlers
    private removeProductButtonHandler removeProductH;

    
    /**
     * This constructor contains a parameter to assign the Optimizer object for 
     * the product removal GUI panels.
     * 
     * @param optimizerObj the Optimizer object to be assigned
     */
    public ProductRemovalGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
    
    /**
     * This method creates and retrieves the components for a product removal 
     * panel.
     * 
     * @return JPanel   returns the JPanel containing GUI components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createRemovalPanel() throws SQLException
    {
        // Initialize and assign base panel layout
        basePanel = new JPanel();
        basePanel.setLayout(new GridLayout(10,1)); 

        // Initialize and assign product number list and labels
        productNumberList = optimizer.getProductNumberList();
        productNumberL = new JLabel(productNumberStr, SwingConstants.LEFT);

        // Assign product number combo box information and adds list information
        productNumberCB = new JComboBox(productNumberList.toArray());
        productNumberCB.setEditable(true);
        AutoCompleteDecorator.decorate(productNumberCB);
        
        // Initialize add product JButton and JButton handler
        removeProductB = new JButton("Remove Product");
        removeProductH = new removeProductButtonHandler();
        removeProductB.addActionListener(removeProductH);
        removeProductButtonP = new JPanel();
        removeProductButtonP.add(removeProductB);
        
        // Assign product number panel components and add to base panel
        productNumberP = new JPanel();
        productNumberP.add(productNumberL);
        productNumberP.add(productNumberCB);
        basePanel.add(productNumberP);
        basePanel.add(removeProductButtonP);
        return basePanel;
    }
    
    /** 
     * This class performs the action of removing a product by pressing
     * a button.
     * 
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    private class removeProductButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String productNumber = productNumberCB.getSelectedItem().toString();
            try 
            {
                String productID = optimizer.determineProductID(productNumber);
                optimizer.removeProduct(productNumber);
                clearFields();
                productNumberCB.removeItem(productNumber);
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | IllegalArgumentException/*| ParseException */ ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                Logger.getLogger(ProductInputGUI.class.getName()).log(Level.SEVERE, 
//                        null, ex);
            }
        }
    }
    
    /**
     * This method clears the fields within the removal panel.
     * 
     */
    public void clearFields()
    {
        productNumberCB.setSelectedItem("");
    }
}