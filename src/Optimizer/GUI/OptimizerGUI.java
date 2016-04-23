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
public class OptimizerGUI 
{
    // String variables for Product JComboBox        
    private String productInputStr = "Add a Product";
    private String productRemovalStr = "Remove a Product";
    private String storeInputStr = "Add a Store";
    private String storeRemovalStr = "Remove a Store";
                                        
    // String variable for JFrame title
    private String inputTitleStr = "Optimizer Shelving Space Manager";
        
    // Product JPanel variables
    private JPanel productInputComboP;
    private JPanel productInputPanel;
    private JPanel productRemovalPanel;
    private JPanel productInputPanels;
    private JPanel productInputTabP;
    
    private JPanel optimEvent;

    private JPanel storeInputComboP;
    private JPanel storeInputPanel;
    private JPanel storeRemovalPanel;
    private JPanel storeInputPanels;
    private JPanel storeInputTabP;
    
    // Frame that contains all components
    private JFrame optimizerGUIFrame;
        
    // Medical clinic database object
    private Optimizer optimizer;
    
    // Product input GUI objects
    private ProductInputGUI productInputGUI;
    private ProductRemovalGUI productRemovalGUI;
    private StoreInputGUI storeInputGUI;
    private StoreRemovalGUI storeRemovalGUI;
            
    // JButton variables
    private JButton searchProductB;
    
    // JButton Handlers
//    private searchProductButtonHandler searchProductH;
    
    // Product Input JComboBox and String variables
    private JComboBox productInputCombo = new JComboBox();
    private String productComboBoxItems[] = {productInputStr, 
        productRemovalStr};
    private JComboBox storeInputCombo = new JComboBox();
    private String storeComboBoxItems[] = {storeInputStr, 
        storeRemovalStr};
    
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
        productRemovalGUI = new ProductRemovalGUI(optimizer);
        
        // Initializes product input panels
        productInputPanel = productInputGUI.createProductInputPanel();
        productRemovalPanel = productRemovalGUI.createInputPanel();
        
        // Creates and adds panels for product inputs
        productInputPanels = new JPanel(new CardLayout());
        productInputPanels.add(productInputPanel, productInputStr);
        productInputPanels.add(productRemovalPanel, productRemovalStr);
          
        //======================================================================
        optimEvent = new OptimEvent(optimizer);        
        
        // Adds product input panels to tab and sets layout
        productInputTabP.add(productInputPanels);
        productInputTabP.add(productInputComboP,BorderLayout.PAGE_END);
        
        // Creates input tab panels and sets layout
        storeInputTabP = new JPanel();
        storeInputTabP.setLayout(new BorderLayout());   
        
        // Creates store JComboBox, initializes it, and adds it to panel
        storeInputCombo = new JComboBox(storeComboBoxItems);
        storeInputCombo.setEditable(false);
        storeInputCombo.addActionListener(new ActionListener() 
        {
            @Override
            public void actionPerformed(ActionEvent e) 
            {
                JComboBox jcb = (JComboBox) e.getSource();
                CardLayout cl = (CardLayout) storeInputPanels.getLayout();
                cl.show(storeInputPanels, jcb.getSelectedItem().toString());
            }
        });
        storeInputComboP = new JPanel();
        storeInputComboP.add(storeInputCombo);   
                                
        // Initializes store input GUI components
        storeInputGUI = new StoreInputGUI(optimizer);
        storeRemovalGUI = new StoreRemovalGUI(optimizer);
        
        // Initializes store input panels
        storeInputPanel = storeInputGUI.createStoreInputPanel();
        storeRemovalPanel = storeRemovalGUI.createInputPanel();
        
        // Creates and adds panels for store inputs
        storeInputPanels = new JPanel(new CardLayout());
        storeInputPanels.add(storeInputPanel, storeInputStr);
        storeInputPanels.add(storeRemovalPanel, storeRemovalStr);
               
        // Adds store input panels to tab and sets layout
        storeInputTabP.add(storeInputPanels);
        storeInputTabP.add(storeInputComboP,BorderLayout.PAGE_END);
                               
        // Create JTabbedPane and add tab panels to pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Product Input", productInputTabP);
        tabbedPane.addTab("Store Input", storeInputTabP);
        tabbedPane.addTab("Optimizer", optimEvent);
        
        // Initialize JFrame properties
        optimizerGUIFrame.setTitle(inputTitleStr);
        optimizerGUIFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        optimizerGUIFrame.add(tabbedPane);
        optimizerGUIFrame.pack();
        optimizerGUIFrame.setVisible(true);
        optimizerGUIFrame.setLocationRelativeTo(null);
    }
}