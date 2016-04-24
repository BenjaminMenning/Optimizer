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
 *                  products, and then uses that information to optimize the 
 *                  shelving space utilized for each product within a store.
 *                  It allows users to add and remove products or stores and 
 *                  also allows a user to optimize the product layout for a 
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
public class ProductInputGUI 
{
    // JLabel variables
    private JLabel productIDL;
    private JLabel productNumberL;
    private JLabel nameL;                                    
    private JLabel typeL;
    private JLabel depthL;
    private JLabel heightL;
    private JLabel widthL;
    
    // String variables for JLabels
    private String productIDStr = "Product ID:";
    private String productNumberStr = "Product Number(########):";
    private String nameStr = "Name:";
    private String typeStr = "Type:";
    private String heightStr = "Height(inches):";
    private String widthStr = "Width(inches):";
    private String depthStr = "Depth(inches):";
    
    // Arrays of string variables for JComboBoxes
    private String[] typeStrings = {"Pet", "Food", "Clothing", "Cleaning "
            + "Supplies"};
    private String[] addProductStrings = {"Add Product", "Remove Product"};
    
    // String variables for error messages
    protected String errorStr = "<html><body><p style='width: "
            + "200px;'>The Product could not be added to the database. Please try "
            + "again. </p></body></html>";
    private String emptyFieldsStr =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty. "
            + "Please try again.</p></body></html>";
    private String prodNumStr =  "<html><body><p style='width: "
            + "200px;'>Invalid product number entered. Product number must be "
            + "numeric and follow the format 'XXXXXXX'. Please try again."
            + "</p></body></html>";
    private String invalidSizeStr =  "<html><body><p style='width: "
            + "200px;'>Invalid height/width/depth entered. Height/width/depth "
            + "must be whole numbers and only three digits long. Please try "
            + "again.</p></body></html>";
    private String validEntryStr0 = "<html><body><p style='width: "
            + "200px;'>Product has been added successfully.</p></body></html>";
    
    // JTextField varables
    private JTextField productIDTF;
    private JTextField productNumberTF;
    private JTextField nameTF, middleNameTF, lastNameTF;                                    
    private JTextField typeTF;
    private JTextField depthTF;
    private JTextField heightTF;
    private JTextField widthTF;
    
    // JButton variables
    private JButton addProductB;
    
    // JComboBox variables
    private JComboBox typeCB = new JComboBox(typeStrings);
    private JComboBox addProductList = new JComboBox(addProductStrings);
    
    // JButton Handlers
    private addProductButtonHandler addProductH;
                        
    // JPanel variables
    private JPanel productIDP;
    private JPanel productNumberP;
    private JPanel nameP;                                    
    private JPanel typeP;
    private JPanel heightP;
    private JPanel widthP;
    private JPanel depthP;
    private JPanel addProductButtonP;
            
    // Optimizer object
    private Optimizer optimizer;
    
    /**
     * This method creates and retrieves the components for a product 
     * input panel.
     * 
     * @return JPanel   returns the JPanel containing product components
     */
    public JPanel createProductInputPanel()
    {
        // Declare overall JPanel and set layout
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(10,1)); 
        
        // Initialize JLabels
        productIDL = new JLabel(productIDStr, SwingConstants.LEFT);
        productNumberL = new JLabel(productNumberStr, SwingConstants.LEFT);
        nameL = new JLabel(nameStr, SwingConstants.LEFT);
        typeL = new JLabel(typeStr, SwingConstants.LEFT);
        depthL = new JLabel(depthStr, SwingConstants.LEFT);
        heightL = new JLabel(heightStr, SwingConstants.LEFT);
        widthL = new JLabel(widthStr, SwingConstants.LEFT);

        // Initialize JTextFields
        productIDTF = new JTextField(20);
        productNumberTF = new JTextField(20);
        nameTF = new JTextField(20);
        middleNameTF = new JTextField(20);
        lastNameTF = new JTextField(20);                                    
        typeTF = new JTextField(20);
        depthTF = new JTextField(20);
        heightTF = new JTextField(20);
        widthTF = new JTextField(20);
        
        // Initialize add product JButton and JButton handler
        addProductB = new JButton("Add Product");
        addProductH = new addProductButtonHandler();
        addProductB.addActionListener(addProductH);
        addProductList.setSelectedIndex(0);
//        addProductList.addActionListener();        
        addProductButtonP = new JPanel();
        addProductButtonP.add(addProductB);
                
        // Set optional layout
        int rows = 1;
        int columns = 2;
        GridLayout gridLayout = new GridLayout(rows, columns);
        
        // Initialize JPanels and add JPanel components
        productIDP = new JPanel();
//        productIDP.setLayout(gridLayout);
        productIDP.add(productIDL);
        productIDP.add(productIDTF);
        productNumberP = new JPanel();
//        productNumberP.setLayout(gridLayout);
        productNumberP.add(productNumberL);
        productNumberP.add(productNumberTF);
        nameP = new JPanel();
//        nameP.setLayout(gridLayout);
        nameP.add(nameL);
        nameP.add(nameTF);
        typeP = new JPanel();
//        typeP.setLayout(gridLayout);
        typeP.add(typeL);
        typeP.add(typeCB);
//        lastNameP.add(typeP);
        heightP = new JPanel();
//        heightP.setLayout(gridLayout);
        heightP.add(heightL);
        heightP.add(heightTF);
        widthP = new JPanel();
//        widthP.setLayout(gridLayout);
        widthP.add(widthL);
        widthP.add(widthTF);
        depthP = new JPanel();
//        depthP.setLayout(gridLayout);
        depthP.add(depthL);
        depthP.add(depthTF);
        
        // Nest JPanels within base JPanel
        panel.add(productNumberP);
        panel.add(nameP);
        panel.add(typeP);
        panel.add(heightP);
        panel.add(widthP);
        panel.add(depthP);
        panel.add(addProductButtonP);
        return panel;
    }
    
    /**
     * This method clears the fields within the input panel.
     * 
     */
    public void clearAddProductTF()
    {
        productIDTF.setText("");
        productNumberTF.setText("");
        nameTF.setText("");
        middleNameTF.setText("");
        lastNameTF.setText("");                                    
//        typeCB.setSelectedItem("");
        depthTF.setText("");
        heightTF.setText("");
        widthTF.setText("");
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
     * This method determines whether or not a product number is valid and throws
     * an IllegalArgumentException if it isn't.
     * 
     * @param productNum the String of the product number to be validated
     */
    public void validateProductNum(String productNum)
    {
        String productNumStr = productNum;
        Pattern productNumPtn = Pattern.compile("\\d{8}");
        Matcher matcher = productNumPtn.matcher(productNumStr);  
        if(!matcher.matches())
        {
            errorStr = prodNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    
    /**
     * This method determines whether or not a height/width/depth is valid and throws
     * an NumberFormatException if it isn't.
     * 
     * @param size the String of the height depth or width to be validated
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
     * This class performs the action of adding a product by pressing
     * a button.
     * 
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    private class addProductButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            errorStr = "";
            String productNumber = productNumberTF.getText();
            String name = nameTF.getText();
            String middleName = middleNameTF.getText();
            String lastName = lastNameTF.getText();
            String type = typeCB.getSelectedItem().toString();
            String depth = depthTF.getText();
            String height = heightTF.getText();
            String width = widthTF.getText();
            try 
            {
                String typeID = optimizer.determineTypeID(type);
                isFieldEmpty(productNumber);
                isFieldEmpty(name);
                isFieldEmpty(depth);
                isFieldEmpty(height);
                isFieldEmpty(width);
                validateSize(depth);
                validateSize(height);
                validateSize(width);
                validateProductNum(productNumber);
                optimizer.addProduct(productNumber, name, typeID, depth, height, 
                        width);
                clearAddProductTF();
                JOptionPane.showMessageDialog(null, validEntryStr0, 
                        "Success", JOptionPane.INFORMATION_MESSAGE);        
            } 
            catch (SQLException | IllegalArgumentException /*| ParseException*/ ex) 
            {
                JOptionPane.showMessageDialog(null, errorStr, 
                        "Error", JOptionPane.ERROR_MESSAGE);        
//                Logger.getLogger(ProductInputGUI.class.getName()).log(Level.SEVERE, 
//                        null, ex);
            }
        }
    }
    
    /**
     * This constructor contains a parameter to assign the Optimizer 
     * object for the product input GUI.
     * 
     * @param optimizerObj the Optimizer object to be assigned
     */
    public ProductInputGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
}