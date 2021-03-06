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
 *                  stores, and then uses that information to optimize the 
 *                  shelving space utilized for each store within a store.
 *                  It allows users to add and remove stores or stores and 
 *                  also allows a user to optimize the store layout for a 
 *                  store and display a report containing the layout 
 *                  information. 
 */

/** 
 * This class constructs the user interface for the Store removal tab within
 * the OptimizerGUI class JFrame.
 * 
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016 
 */
public class StoreRemovalGUI 
{
    // JLabel variables
    protected JLabel storeNumberL;
        
    // String and ArrayList of string variables for store numbers
    protected String storeNumberStr = "Store Number:";
    protected String errorStr = "<html><body><p style='width: "
            + "200px;'>The Store could not be removed from the database. Please try "
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
            = "' has been added successfully to the store.</p></body></html>";
    protected ArrayList<String> storeNumberList;
        
    // JTextField varables
    protected JComboBox storeNumberCB;
    
    // JButton variables
    private JButton removeStoreB;

    // JPanel variables
    protected JPanel storeNumberP;
    protected JPanel basePanel;
    private JPanel removeStoreButtonP;

    
    // Declares Optimizer object
    protected Optimizer optimizer;
    protected StoreRemovalGUI storeRemovalGUI;
    
    // JButton Handlers
    private removeStoreButtonHandler removeStoreH;

    
    /**
     * This constructor contains a parameter to assign the Optimizer object for 
     * the store removal GUI panels.
     * 
     * @param optimizerObj the Optimizer object to be assigned
     */
    public StoreRemovalGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
    
    /**
     * This method creates and retrieves the components for a store removal 
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

        // Initialize and assign store number list and labels
        storeNumberList = optimizer.getStoreNumberList();
        storeNumberL = new JLabel(storeNumberStr, SwingConstants.LEFT);

        // Assign store number combo box information and adds list information
        storeNumberCB = new JComboBox(storeNumberList.toArray());
        storeNumberCB.setEditable(true);
        AutoCompleteDecorator.decorate(storeNumberCB);
        
        // Initialize add store JButton and JButton handler
        removeStoreB = new JButton("Remove Store");
        removeStoreH = new removeStoreButtonHandler();
        removeStoreB.addActionListener(removeStoreH);
        removeStoreButtonP = new JPanel();
        removeStoreButtonP.add(removeStoreB);
        
        // Assign store number panel components and add to base panel
        storeNumberP = new JPanel();
        storeNumberP.add(storeNumberL);
        storeNumberP.add(storeNumberCB);
        basePanel.add(storeNumberP);
        basePanel.add(removeStoreButtonP);
        return basePanel;
    }
    
    /** 
     * This class performs the action of removing a store by pressing
     * a button.
     * 
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    private class removeStoreButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String storeNumber = storeNumberCB.getSelectedItem().toString();
            try 
            {
                String storeID = optimizer.determineStoreID(storeNumber);
                optimizer.removeStore(storeNumber);
                clearFields();
                storeNumberCB.removeItem(storeNumber);
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | IllegalArgumentException /*| ParseException*/ ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                Logger.getLogger(StoreInputGUI.class.getName()).log(Level.SEVERE, 
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
        storeNumberCB.setSelectedItem("");
    }
}