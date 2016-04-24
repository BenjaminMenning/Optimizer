package Optimizer.GUI;

import Optimizer.Optimizer;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

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
 * This class constructs the user interface for the Product input tab within
 * the OptimizerGUI class JFrame.
 * 
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016 
 */
public class StoreInputGUI 
{
    // JLabel variables
    private JLabel storeIDL;
    private JLabel storeNumberL;
    private JLabel nameL;                                    
    private JLabel totalUnitsL;
    private JLabel totalPetUnitsL;
    private JLabel totalFoodUnitsL;
    private JLabel totalClothingUnitsL;
    private JLabel totalCleaningUnitsL;
    
    // String variables for JLabels
    private String storeIDStr = "Store ID:";
    private String storeNumberStr = "Store Number(#######):";
    private String nameStr = "Name:";
    private String totalUnitsStr = "Total Units:";
    private String totalPetUnitsStr = "Total Pet Units:";
    private String totalFoodUnitsStr = "Total Food Units:";
    private String totalClothingUnitsStr = "Total Clothing Units:";
    private String totalCleaningUnitsStr = "Total Cleaning Units:";

    
    // Arrays of string variables for JComboBoxes
    private String[] addStoreStrings = {"Add Store", "Remove Store"};
    
    // String variables for error messages
    protected String errorStr = "<html><body><p style='width: "
            + "200px;'>The Store could not be added to the database. Please try "
            + "again. </p></body></html>";
    private String emptyFieldsStr =  "<html><body><p style='totalClothingUnits: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty. "
            + "Please try again.</p></body></html>";
    private String invalidStoreNumStr =  "<html><body><p style='totalClothingUnits: "
            + "200px;'>Invalid store number entered. Store number must be "
            + "numeric and follow the format 'XXXXXX'. Please try again."
            + "</p></body></html>";
    private String invalidSizeStr =  "<html><body><p style='totalClothingUnits: "
            + "200px;'>Invalid totalFoodUnits and/or totalClothingUnits entered. Height and totalClothingUnits "
            + "must be whole numbers and only three digits long. Please try "
            + "again.</p></body></html>";
    private String validEntryStr0 = "<html><body><p style='totalClothingUnits: "
            + "200px;'>Store has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField storeIDTF;
    private JTextField storeNumberTF;
    private JTextField nameTF;                                    
    private JTextField totalUnitsTF;
    private JTextField totalPetUnitsTF;
    private JTextField totalFoodUnitsTF;
    private JTextField totalClothingUnitsTF;
    private JTextField totalCleaningUnitsTF;
    
    // JButton variables
    private JButton addStoreB;
    
    // JComboBox variables
    private JComboBox addStoreList = new JComboBox(addStoreStrings);
    
    // JButton Handlers
    private addStoreButtonHandler addStoreH;
                        
    // JPanel variables
    private JPanel storeIDP;
    private JPanel storeNumberP;
    private JPanel nameP;                                    
    private JPanel totalUnitsP;
    private JPanel totalPetUnitsP;
    private JPanel totalFoodUnitsP;
    private JPanel totalClothingUnitsP;
    private JPanel totalCleaningUnitsP;
    private JPanel addStoreButtonP;
            
    // Optimizer object
    private Optimizer optimizer;
    
    /**
     * This method creates and retrieves the components for a store 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing store components
     */
    public JPanel createStoreInputPanel()
    {
        // Declare overall JPanel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        // Initialize JLabels
        storeIDL = new JLabel(storeIDStr, SwingConstants.LEFT);
        storeNumberL = new JLabel(storeNumberStr, SwingConstants.LEFT);
        nameL = new JLabel(nameStr, SwingConstants.LEFT);
        totalUnitsL = new JLabel(totalUnitsStr, SwingConstants.LEFT);
        totalPetUnitsL = new JLabel(totalPetUnitsStr, SwingConstants.LEFT);
        totalFoodUnitsL = new JLabel(totalFoodUnitsStr, SwingConstants.LEFT);
        totalClothingUnitsL = new JLabel(totalClothingUnitsStr, SwingConstants.LEFT);
        totalCleaningUnitsL = new JLabel(totalCleaningUnitsStr, SwingConstants.LEFT);

        // Initialize JTextFields
        storeIDTF = new JTextField(20);
        storeNumberTF = new JTextField(20);
        nameTF = new JTextField(20);
        totalUnitsTF = new JTextField(20);
        totalPetUnitsTF = new JTextField(20);
        totalFoodUnitsTF = new JTextField(20);
        totalClothingUnitsTF = new JTextField(20);
        totalCleaningUnitsTF = new JTextField(20);
        
        // Initialize add store JButton and JButton handler
        addStoreB = new JButton("Add Store");
        addStoreH = new addStoreButtonHandler();
        addStoreB.addActionListener(addStoreH);
        addStoreList.setSelectedIndex(0);
//        addStoreList.addActionListener();        
        addStoreButtonP = new JPanel();
        addStoreButtonP.add(addStoreB);
                
        // Set optional layout
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
        
        // Initialize JPanels and add JPanel components
        storeIDP = new JPanel();
//        storeIDP.setLayout(gridLayout);
        storeIDP.add(storeIDL);
        storeIDP.add(storeIDTF);
        storeNumberP = new JPanel();
//        storeNumberP.setLayout(gridLayout);
        storeNumberP.add(storeNumberL);
        storeNumberP.add(storeNumberTF);
        nameP = new JPanel();
//        nameP.setLayout(gridLayout);
        nameP.add(nameL);
        nameP.add(nameTF);
        totalUnitsP = new JPanel();
//        totalUnitsP.setLayout(gridLayout);
        totalUnitsP.add(totalUnitsL);
        totalUnitsP.add(totalUnitsTF);
//        lastNameP.add(totalUnitsP);
        totalPetUnitsP = new JPanel();
//        totalPetUnitsP.setLayout(gridLayout);
        totalPetUnitsP.add(totalPetUnitsL);
        totalPetUnitsP.add(totalPetUnitsTF);
        totalFoodUnitsP = new JPanel();
//        totalFoodUnitsP.setLayout(gridLayout);
        totalFoodUnitsP.add(totalFoodUnitsL);
        totalFoodUnitsP.add(totalFoodUnitsTF);
        totalClothingUnitsP = new JPanel();
//        totalClothingUnitsP.setLayout(gridLayout);
        totalClothingUnitsP.add(totalClothingUnitsL);
        totalClothingUnitsP.add(totalClothingUnitsTF);
        totalCleaningUnitsP = new JPanel();
//        totalCleaningUnitsP.setLayout(gridLayout);
        totalCleaningUnitsP.add(totalCleaningUnitsL);
        totalCleaningUnitsP.add(totalCleaningUnitsTF);
        
        // Nest JPanels within base JPanel
        panel.add(storeNumberP);
        panel.add(nameP);
        panel.add(totalUnitsP);
        panel.add(totalPetUnitsP);
        panel.add(totalFoodUnitsP);
        panel.add(totalClothingUnitsP);
        panel.add(totalCleaningUnitsP);
        panel.add(addStoreButtonP);
        return panel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearAddStoreTF()
    {
        storeIDTF.setText("");
        storeNumberTF.setText("");
        nameTF.setText("");
        totalUnitsTF.setText("");
        totalPetUnitsTF.setText("");
        totalFoodUnitsTF.setText("");
        totalClothingUnitsTF.setText("");
        totalCleaningUnitsTF.setText("");
    }
    
    /**
     * This method determines whether or not a field is a empty and throws an
     * IllegalArgumentException if it is.
     * 
     * @param field the String to be evaluated as empty or not
     */
    public void isFieldEmpty(String field)
    {
        boolean isEmpty = false;
        String fieldStr = field;
        isEmpty = fieldStr.equals("");
        if(isEmpty == true)
        {
            errorStr = emptyFieldsStr;
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * This method determines whether or not a store number is valid and throws
     * an IllegalArgumentException if it isn't.
     * 
     * @param storeNum the String of the store number to be validated
     */
    public void validateStoreNum(String storeNum)
    {
        String storeNumStr = storeNum;
        Pattern storeNumPtn = Pattern.compile("\\d{6}");
        Matcher matcher = storeNumPtn.matcher(storeNumStr);  
        if(!matcher.matches())
        {
            errorStr = invalidStoreNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    
    /**
     * This method determines whether or not a height/width is valid and throws
     * an NumberFormatException if it isn't.
     * 
     * @param size the String of the width or height to be validated
     */
    public void validateSize(String size)
    {
        String sizeStr = size;
        try 
        {
            int sizeInt = Integer.parseInt(sizeStr);
            if(sizeStr.length() > 3)
            {
                throw new NumberFormatException();
            }
        } 
        catch (NumberFormatException e) 
        {
            errorStr = invalidSizeStr;
            throw e;
        }
    }

    /** 
     * This class performs the action of adding a store by pressing
     * a button.
     * 
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    private class addStoreButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            errorStr = "";
            String storeNumber = storeNumberTF.getText();
            String name = nameTF.getText();
            String totalUnits = totalUnitsTF.getText();
            String totalPetUnits = totalPetUnitsTF.getText();
            String totalFoodUnits = totalFoodUnitsTF.getText();
            String totalClothingUnits = totalClothingUnitsTF.getText();
            String totalCleaningUnits = totalCleaningUnitsTF.getText();
            try 
            {
                String totalUnitsID = optimizer.determineTypeID(totalUnits);
                isFieldEmpty(storeNumber);
                isFieldEmpty(name);
                isFieldEmpty(totalUnits);
                isFieldEmpty(totalPetUnits);
                isFieldEmpty(totalFoodUnits);
                isFieldEmpty(totalClothingUnits);
                isFieldEmpty(totalCleaningUnits);
                validateStoreNum(storeNumber);
                optimizer.addStore(storeNumber, name, totalUnits, totalPetUnits, totalFoodUnits, 
                        totalClothingUnits, totalCleaningUnits);
                clearAddStoreTF();
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | IllegalArgumentException/*| ParseException */ ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                Logger.getLogger(StoreInputGUI.class.getName()).log(Level.SEVERE, 
//                        null, ex);
            }
        }
    }
    
    /**
     * This constructor contains a parameter to assign the Optimizer object
     *  for the store input GUI.
     * 
     * @param optimizerObj the Optimizer object to be assigned
     */
    public StoreInputGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
}