package Optimizer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.util.Calendar;

/**
 * Author: Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 *
 * Date: 04/26/2016
 *
 * Course: CS 410 - 01, Spring 2016
 *
 * Assignment: Software Engineering Project
 *
 * Description: This program is a shelving space management program that
 * utilizes an SQLite relational database management system to allow users to
 * input and view information about stores and products, and then uses that
 * information to optimize the shelving space utilized for each product within a
 * store. It allows users to add and remove products or stores and also allows a
 * user to optimize the product layout for a store and display a report
 * containing the layout information.
 */

/**
 * This class implements all of the methods detailed by the Optimizer interface.
 * It contains method implementations for adding products and stores, removing 
 * products and stores, generating a report, and many other functions. 
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Optimizer implements OptimizerADT {

    private String className = "org.sqlite.JDBC";
    private String databaseURL = "jdbc:sqlite:Optimizer.db";
    private String databaseUsername = "blah";
    private String databasePassword = "blah";

    //True for Width
    //False for Height
    public static boolean sortType = true;

    private Connection connection = null;

    public int shelfIndex = 0;

    public static ArrayList<ArrayList<Product>> shelf = new ArrayList<ArrayList<Product>>();

    public static ArrayList<Unit> Units = new ArrayList<Unit>();

    public static ArrayList<Product> productList = new ArrayList<Product>();
    public static Product sortedWidth = new Product();
    public static Product sortedHeight = new Product();


    public static ArrayList<Product> Shelf = new ArrayList<>();

    @Override
    public ArrayList<Product> heightSorter(ArrayList<Product> prodList) {

        ArrayList<Product> tempHeight = new ArrayList<Product>(prodList);
        Collections.sort(tempHeight, new HeightComparator());
        return tempHeight;
    }

    @Override
    public ArrayList<Product> widthSorter(ArrayList<Product> prodList) {
        ArrayList<Product> tempWidth = new ArrayList<Product>(prodList);
        Collections.sort(tempWidth, new WidthComparator());
        return tempWidth;
    }

    /**
     * This class is a comparator that compares Product objects. It orders the Products
     * with the shortest Product at the front of the queue. 
     *
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    public class HeightComparator implements Comparator<Product> {

        @Override
        public int compare(Product x, Product y) {

            double prodHeightX = x.getHeight();
            double prodHieghtY = y.getHeight();
            if (prodHeightX > prodHieghtY) {
                return 1;
            } else if (prodHeightX == prodHieghtY) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    /**
     * This class is a comparator that compares Product objects. It orders the Products
     * with the thinnest Product at the front of the queue. 
     *
     * @author Benjamin Menning
     * @version 04/26/2016
     */
    public class WidthComparator implements Comparator<Product> {

        @Override
        public int compare(Product x, Product y) {

            double prodWidthX = x.getWidth();
            double prodWidthY = y.getWidth();
            if (prodWidthX > prodWidthY) {
                return 1;
            } else if (prodWidthX == prodWidthY) {
                return 0;
            } else {
                return -1;
            }
        }
    }

    @Override
    public void connectToDatabase() {
        int timeout = 0;
        try {
            if (connection == null || connection.isValid(timeout) == false) {
                try {
                    Class.forName(className);
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }

                try {
                    connection = DriverManager.getConnection(databaseURL);
                    System.out.println("Connection Successful");
                } catch (SQLException e) {
                    System.out.println("Connection Failed");
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Optimizer.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }

    @Override
    public void addProduct(String productNumber, String productName,
            String productType, String height, String width, String depth)
            throws SQLException {
        Statement stmt = null;
        String productNumberStr = "'" + productNumber + "'";
        String productNameStr = "'" + productName + "'";
        String productTypeStr = "'" + productType + "'";
        String query = "INSERT INTO Product(productID, productNumber, name, "
                + "typeID, height, width, depth)"
                + "\nVALUES (null, " + productNumberStr + ", "
                + productNameStr + ", "
                + productTypeStr + ", "
                + height + ", "
                + width + ", "
                + depth + ")";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void removeProduct(String productNumber) throws SQLException {
        Statement stmt = null;
        String productNumberStr = "'" + productNumber + "'";
        String query = "DELETE FROM Product"
                + "\nWHERE ProductNumber = " + productNumberStr;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public ArrayList<String> getProductNumberList() throws SQLException {
        ArrayList<String> productNumberList = new ArrayList<String>();
        productNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Product";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String productNumber = rs.getString("productNumber");
                productNumberList.add(productNumber);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return productNumberList;
    }

    @Override
    public void addStore(String storeNumber, String storeName,
            String totalUnits, String petUnits, String foodUnits, String clothingUnits, String cleaningUnits)
            throws SQLException {
        Statement stmt = null;
        String storeNumberStr = "'" + storeNumber + "'";
        String storeNameStr = "'" + storeName + "'";
        String query = "INSERT INTO Store(storeID, storeNumber, name, "
                + "totalUnits, totalPetUnits, totalFoodUnits, "
                + "totalClothingUnits, totalCleaningUnits)"
                + "\nVALUES (null, " + storeNumberStr + ", "
                + storeNameStr + ", "
                + totalUnits + ", "
                + petUnits + ", "
                + foodUnits + ", "
                + clothingUnits + ", "
                + cleaningUnits + ")";
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    @Override
    public void removeStore(String storeNumber) throws SQLException {
        Statement stmt = null;
        String storeNumberStr = "'" + storeNumber + "'";
        String query = "DELETE FROM Store"
                + "\nWHERE StoreNumber = " + storeNumberStr;
        try {
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    
    @Override
    public Store getStore(String storeNum) throws SQLException {
        Store store = new Store();
        String storeNumberStr = "'" + storeNum + "'";
        Statement stmt = null;
        String query = "SELECT *\n" +
                        "FROM Store\n" +
                        "WHERE storeNumber = " + storeNumberStr + "\n" +
                        "LIMIT 1";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            String storeNumber = rs.getString("storeNumber");
            String storeName = rs.getString("name");
            int totalUnits = rs.getInt("totalUnits");
            store.setStoreNumber(storeNumber);
            store.setStoreName(storeName);
            store.setTotalUnits(totalUnits);
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return store;
    }

    @Override
    public ArrayList<String> getStoreNumberList() throws SQLException {
        ArrayList<String> storeNumberList = new ArrayList<String>();
        storeNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Store";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                String storeNumber = rs.getString("storeNumber");
                storeNumberList.add(storeNumber);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return storeNumberList;
    }

    @Override
    public String determineTypeID(String type) throws SQLException {
        connectToDatabase();
        Statement stmt = null;
        String typeID = "";
        String typeStr = "'" + type + "'";
        String query = "SELECT typeID \nFROM Type"
                + "\nWHERE type = " + typeStr;
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
        } else {
            typeID = rs.getString("typeID");
        }
        return typeID;
    }

    @Override
    public String determineType(int typeID) throws SQLException {
        connectToDatabase();
        Statement stmt = null;
        String type = "";
        String typeIDStr = "'" + typeID + "'";
        String query = "SELECT type \nFROM Type"
                + "\nWHERE typeID = " + typeIDStr;
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
        } else {
            type = rs.getString("type");
        }
        return type;
    }

    @Override
    public String determineProductID(String productNumber) throws SQLException {
        connectToDatabase();
        Statement stmt = null;
        String productID = "";
        String productNumberStr = "'" + productNumber + "'";
        String query = "SELECT productID \nFROM Product"
                + "\nWHERE productNumber = " + productNumberStr;
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
        } else {
            productID = rs.getString("productID");
        }
        return productID;
    }

    @Override
    public String determineStoreID(String storeNumber) throws SQLException {
        connectToDatabase();
        Statement stmt = null;
        String storeID = "";
        String storeNumberStr = "'" + storeNumber + "'";
        String query = "SELECT storeID \nFROM Store"
                + "\nWHERE storeNumber = " + storeNumberStr;
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
        } else {
            storeID = rs.getString("storeID");
        }
        return storeID;
    }

    @Override
    public ArrayList<Product> getProductList() throws SQLException {
        ArrayList<Product> productList = new ArrayList<Product>();
        Statement stmt = null;
        String query = "SELECT *\nFROM Product";
        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {

                Product product = new Product();
                int typeID = rs.getInt("typeID");
                String type = determineType(typeID);

                product.setProductNumber(rs.getString("productNumber"));
                product.setProductName(rs.getString("name"));
                product.setProductType(type);
                product.setHeight(rs.getDouble("height"));
                product.setWidth(rs.getDouble("width"));
                product.setDepth(rs.getDouble("depth"));
                product.toString();
                productList.add(product);
            }

      
        } catch (SQLException e) {
            throw e;
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
        return productList;
    }

    @Override
    public String generateReport(boolean isWidthSort, String storeNumber, String name, int totalUnits) {
                    
        try {
            productList = getProductList();
        } catch (SQLException ex) {
            Logger.getLogger(Optimizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //True for Width
        //False for Height
        fillUnits(isWidthSort);        
        
        
        
        //Creating doc to print out
        String returnString = "";
        returnString += "Store Name: " + name + "\n" 
                + "Store Number: " + storeNumber + "\n"
                + "Total Store Units: " + totalUnits + "\n";
        
        for (Unit unit : Units) {
            returnString += unit;
        }
                       
        
        try {
            unitPrint(storeNumber, returnString);
        } catch (IOException ex) {
            Logger.getLogger(Optimizer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return returnString;
    }

    public static void main(String[] args) throws SQLException, IOException {

        boolean done = false;
        double maxWidth = 48;
        double maxHeight = 84;
        double totalProductWidth = 0;
        double shelfHeight = 0;
        double individuelShelfHeight = 2;
        double tallest = 0;

        double shelfAheight = 0;
        double shelfBheight = 0;
        double shelfCheight = 0;
        double shelfDheight = 0;

        Optimizer optimizer = new Optimizer();

        shelf.add(new ArrayList<Product>());
        shelf.add(new ArrayList<Product>());
        shelf.add(new ArrayList<Product>());
        shelf.add(new ArrayList<Product>());
        shelf.add(new ArrayList<Product>());

        optimizer.connectToDatabase();        
        productList = optimizer.getProductList();
        ArrayList<Product> widthList = optimizer.heightSorter(productList);
        
        System.out.println(optimizer.generateReport(true, "12345678", "store", 10));
        
    }//end main
  
    /**
     * This method fills the shelves of the units with products.
     * 
     * @param sortType  sorts by width if true, height if false
     */
    public static void fillUnits(boolean sortType) {
        Optimizer optimizer = new Optimizer();
        if (!sortType) {

            ArrayList<Product> heightList = optimizer.heightSorter(productList);
            int unitCount = 0;
            while (!heightList.isEmpty()) {
                Unit tempUnit = new Unit();
                tempUnit.setUnitNumber(unitCount);
                tempUnit.fillShelf(heightList);
                Units.add(tempUnit);
                unitCount++;

            }

        } else {
            ArrayList<Product> widthtList = optimizer.widthSorter(productList);
            int unitCount = 0;
            while (!widthtList.isEmpty()) {
                Unit tempUnit = new Unit();
                tempUnit.setUnitNumber(unitCount);
                tempUnit.fillShelf(widthtList);
                Units.add(tempUnit);
                unitCount++;
            }//end while
        }
    }//end fillUnits

    /**
     * This method prints out the contents of each unit within a store to a 
     * text file. 
     * 
     * @param storeNum  the String to be assigned as the store number
     * @param storeInfo the String to be assigned as the store information
     * @throws IOException  if PrintWriter encounters an error writing file out
     */
    public static void unitPrint(String storeNum, String storeInfo) throws IOException {

            Calendar calendar = Calendar.getInstance();
            java.util.Date now = calendar.getTime();
            storeNum += calendar;
            storeNum += now;             

        try (PrintWriter printOut = new PrintWriter(storeNum + ".txt")) {
            printOut.printf(storeInfo);
            printOut.close();
        }//end try
    }//end unitPrint
}//end class