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
 * This class contains a main method that allows a user to run the Optimizer 
 * shelving space management program.
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
    private String depthStr = "Depth(inches):";
    private String heightStr = "Height(inches):";
    private String widthStr = "Width(inches):";
    
    // Arrays of string variables for JComboBoxes
    private String[] typeStrings = {"Pet", "Food", "Clothing", "Cleaning "
            + "Supplies"};
    private String[] addProductStrings = {"Add Product", "Remove Product"};
    
    // String variables for error messages
    private String errorStr = "";
    private String emptyFieldsStr =  "<html><body><p style='width: "
            + "200px;'>Invalid value(s) entered. Fields cannot be empty. "
            + "Please try again.</p></body></html>";
    private String invalidClinNumStr =  "<html><body><p style='width: "
            + "200px;'>Invalid clinic number entered. Clinic number must be "
            + "numeric and follow the format 'X-XXX-XXX'. Please try again."
            + "</p></body></html>";
    private String invalidBirthDateStr =  "<html><body><p style='width: "
            + "200px;'>Invalid birth date entered. Birth date must "
            + "follow the format 'YYYY-MM-DD'. Please try again."
            + "</p></body></html>";
    private String invalidSizeStr =  "<html><body><p style='width: "
            + "200px;'>Invalid height and/or width entered. Height and width "
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
    private JPanel depthP;
    private JPanel heightP;
    private JPanel widthP;
    private JPanel addProductButtonP;
            
    // Medical Clinic database object
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
        depthP = new JPanel();
//        depthP.setLayout(gridLayout);
        depthP.add(depthL);
        depthP.add(depthTF);
        heightP = new JPanel();
//        heightP.setLayout(gridLayout);
        heightP.add(heightL);
        heightP.add(heightTF);
        widthP = new JPanel();
//        widthP.setLayout(gridLayout);
        widthP.add(widthL);
        widthP.add(widthTF);
        
        // Nest JPanels within base JPanel
        panel.add(productNumberP);
        panel.add(nameP);
        panel.add(typeP);
        panel.add(depthP);
        panel.add(heightP);
        panel.add(widthP);
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
     * This method determines whether or not a clinic number is valid and throws
     * an IllegalArgumentException if it isn't.
     * 
     * @param clinicNum the String of the clinic number to be validated
     */
    public void validateClinicNum(String clinicNum)
    {
        String clinicNumStr = clinicNum;
        Pattern clinicNumPtn = Pattern.compile("\\d{1}-\\d{3}-\\d{3}");
        Matcher matcher = clinicNumPtn.matcher(clinicNumStr);  
        if(!matcher.matches())
        {
            errorStr = invalidClinNumStr;
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * This method determines whether or not a birth date is valid and throws
     * an ParseException if it isn't.
     * 
     * @param depth the String of the birth date to be validated
     * @throws java.text.ParseException if birth date is invalid
     */
    public void validateBirthDate(String depth) throws ParseException
    {
        String depthStr = depth;
        String dateFmtStr = "yyyy-MM-dd";
        SimpleDateFormat simpDateFmt = new SimpleDateFormat(dateFmtStr);
        try 
        {
            simpDateFmt.parse(depthStr);
        } 
        catch (ParseException e) 
        {
            errorStr = invalidBirthDateStr;
            throw e;
        }
    }
    
    /**
     * This method determines whether or not a height/width is valid and throws
     * an NumberFormatException if it isn't.
     * 
     * @param size the String of the height or width to be validated
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
     * @author Benjamin Menning, Dan Johnson, Holly Schreader
     * @version 05/05/2015
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
//                validateClinicNum(productNumber);
//                validateBirthDate(depth);
//                validateSize(height);
//                validateSize(width);
                optimizer.addProduct(productNumber, name, typeID, depth, height, 
                        width);
                clearAddProductTF();
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
     * This constructor contains a parameter to assign the medical clinic 
     * database for the product input GUI.
     * 
     * @param medicalClinicObj the medical clinic DB to be assigned
     */
    public ProductInputGUI(Optimizer optimizerObj)
    {
        optimizer = optimizerObj;
    }
}