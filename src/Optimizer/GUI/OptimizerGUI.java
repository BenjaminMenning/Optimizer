package Optimizer.GUI;

import Optimizer.Optimizer;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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
 * This class constructs the overall user interface for the medical clinic 
 * database and implements a wide array of different components into a single
 * frame.
 * 
 * @author Benjamin Menning, Dan Johnson, Holly Schreader
 * @version 05/05/2015 
 */
public class OptimizerGUI 
{
    // String variables for Product JComboBox        
    private String productInputStr = "Add a Product";
    private String productRemovalStr = "Remove a Product";
                                        
    // String variable for JFrame title
    private String inputTitleStr = "Optimizer Shelving Space Manager";
    
    // Product JPanel variables
    private JPanel productInputComboP;
    private JPanel productInputPanel;
    private JPanel productRemovalPanel;
    private JPanel productInputPanels;
    private JPanel productInputTabP;
    
    // Frame that contains all components
    private JFrame optimizerGUIFrame;
        
    // Medical clinic database object
    private Optimizer optimizer;
    
    // Product input GUI objects
    private ProductInputGUI productInputGUI;
    private ProductRemovalGUI productRemovalGUI;
            
    // JButton variables
    private JButton searchProductB;
    
    // JButton Handlers
//    private searchProductButtonHandler searchProductH;
    
    // Product Input JComboBox and String variables
    private JComboBox productInputCombo = new JComboBox();
    private String productComboBoxItems[] = {productInputStr, 
        productRemovalStr};
    
    /**
     * This constructor contains a parameter to assign the medical clinic 
     * database and create the main frame that utilizes all of the GUI 
     * components.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public OptimizerGUI(Optimizer optimizerObj) throws SQLException
    {
        // Initializes medical clinic database object and connects to database
        optimizer = optimizerObj;
        optimizer.connectToDatabase();
        
        // Creates JFrame
        optimizerGUIFrame = new JFrame();
        
        // Creates input tab panels and sets layout
        productInputTabP = new JPanel();
        productInputTabP.setLayout(new BorderLayout());   
        
        // Creates product JComboBox, initializes it, and adds it to panel
        productInputCombo = new JComboBox(productComboBoxItems);
        productInputCombo.setEditable(false);
        productInputCombo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) productInputPanels.getLayout();
                cl.show(productInputPanels, jcb.getSelectedItem().toString());
            }
        });
        productInputComboP = new JPanel();
        productInputComboP.add(productInputCombo);   
                                
        // Initializes product input GUI components
        productInputGUI = new ProductInputGUI(optimizer);
//        productRemovalGUI = new ProductRemovalGUI(optimizer);
        
        // Initializes product input panels
        productInputPanel = productInputGUI.createProductInputPanel();
//        productRemovalPanel = productRemovalGUI.createInputPanel();
        
        // Creates and adds panels for product inputs
        productInputPanels = new JPanel(new CardLayout());
        productInputPanels.add(productInputPanel, productInputStr);
//        productInputPanels.add(productRemovalPanel, productRemovalStr);
               
        // Adds product input panels to tab and sets layout
        productInputTabP.add(productInputPanels);
        productInputTabP.add(productInputComboP,BorderLayout.PAGE_END);
                       
        // Create JTabbedPane and add tab panels to pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Product Input", productInputTabP);
//        tabbedPane.addTab("Visit Input", productInputTabP);
//        tabbedPane.addTab("Miscellaneous Input", productInputTabP);
        
        // Initialize JFrame properties
        optimizerGUIFrame.setTitle(inputTitleStr);
        optimizerGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optimizerGUIFrame.add(tabbedPane);
        optimizerGUIFrame.pack();
        optimizerGUIFrame.setVisible(true);
        optimizerGUIFrame.setLocationRelativeTo(null);
    }
}