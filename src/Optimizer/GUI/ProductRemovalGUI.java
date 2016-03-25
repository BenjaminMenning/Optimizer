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
 * Author:          Benjamin Menning, Dan Johnson, Holly Schreader
 * 
 * Date:            05/05/2015 
 *                
 * Course:          CS 485 - 01, Spring 2015
 * 
 * Assignment:      Database Project
 * 
 * Description:     This program is a medical database program that utilizes a
 *                  MySQL relational database management system to allow users
 *                  to input and view information about products and visits.
 *                  It allows users to input information on a wide variety of 
 *                  things, including product conditions and assistive devices,
 *                  visit diagnoses and studies, as well as information about
 *                  healthcare providers or systems used. It also allows a user
 *                  to search and lookup information about products based on a
 *                  wide variety of criteria like name, diagnoses, date of birth
 *                  and more. It also allows users to see more detailed 
 *                  information about products and their visits.
 */

/** 
 * This class is an abstract class that extends various product input panels. It
 * contains common components across all product input panels including product
 * number input components.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class ProductRemovalGUI 
{
    // JLabel variables
    protected JLabel productNumberL;
        
    // String and ArrayList of string variables for product numbers
    protected String productNumberStr = "Product Number:";
    private String errorStr = "";
    protected String invalidEntryStr0 =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty and "
            + "values entered must match those that are listed. Please "
            + "try again.</p></body></html>";
    protected String validEntryStr0;
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

    
    // Declares medical product database object
    protected Optimizer optimizer;
    protected ProductRemovalGUI productRemovalGUI;
    
    // JButton Handlers
    private removeProductButtonHandler removeProductH;

    
    /**
     * This constructor contains a parameter to assign the medical product 
     * database for the product input GUI panels.
     * 
     * @param optimizerObj the medical product DB to be assigned
     */
    public ProductRemovalGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
    
    /**
     * This method creates and retrieves the components for a product input 
     * panel.
     * 
     * @return JPanel   returns the JPanel containing product number components
     * @throws SQLException if SQL database encounters an error
     */
    public JPanel createInputPanel() throws SQLException
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
     * This class performs the action of adding a product by pressing
     * a button.
     * 
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
     */
    private class removeProductButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            errorStr = "";
            String productNumber = productNumberCB.getSelectedItem().toString();
            try 
            {
                String productID = optimizer.determineProductID(productNumber);
//                isFieldEmpty(productNumber);
//                validateClinicNum(productNumber);
//                validateBirthDate(depth);
//                validateSize(height);
//                validateSize(width);
                optimizer.removeProduct(productNumber);
                clearFields();
                productNumberCB.removeItem(productNumber);
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException /*| ParseException | IllegalArgumentException*/ ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
                Logger.getLogger(ProductInputGUI.class.getName()).log(Level.SEVERE, 
                        null, ex);
            }
        }
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearFields()
    {
        productNumberCB.setSelectedItem("");
    }
}