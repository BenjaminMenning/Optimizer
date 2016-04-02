package Optimizer;


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
public class Optimizer {

    // String variables for database connectivity
    private String className = "org.sqlite.JDBC";
    private String databaseURL =  "jdbc:sqlite:Optimizer.db";
    private String databaseUsername = "blah";
    private String databasePassword = "blah";
    
    
    // Connection variable for connecting to the database
    private Connection connection = null;
    
    public static ArrayList<Product> productList = new ArrayList<>();
    public static Product sortedWidth = new Product();
    public static Product sortedHeight = new Product();
    //public static Product Shelf = new Product();
    
    public static ArrayList<Product> Shelf = new ArrayList<>();
    
//    public ArrayList<Product> widthList = heightSorter();
//    public ArrayList<Product> heightList = widthSorter();
    

    //Height sorting clone of "productList"
    //public static ArrayList<Product> cloneList = new ArrayList(productList);
    
    

//    public static void heightSorter() {
//        
//        //ArrayList<Product> cloneList = new ArrayList(productList);
//        
//        
//        int smallInt = 0;
//        int j = 0;
//        int smallIntIndex = 0;
//        
//        for(int i=1; i < cloneList.size()-1; i++) {            
//            smallInt = (int) cloneList.get(i-1).getHeight();
//            smallIntIndex = (i-1);
//            
//            for(j=i; j < cloneList.size(); j++){
//                if(cloneList.get(j).getHeight() < smallInt){
//                    smallInt = (int) cloneList.get(j).getHeight();
//                    smallIntIndex = j;
//                }//end if                
//            }//end for
//                   
//            swap(i-1, smallIntIndex);
//            
//        }//end for
//        
//        
//    }//end heightSorter
//
//    private  void swap(int sourceIndex, int destIndex) {
//        int temp = cloneList.get(destIndex);
//        cloneList.set(destIndex, cloneList.get(sourceIndex));
//        cloneList.set(sourceIndex, temp); 
//    }


    
    public ArrayList<Product> heightSorter(ArrayList<Product> prodList) {
        
        int temp = 0;
        Product tempProductHeight = null;
        ArrayList<Product> tempHeight = new ArrayList<Product>(productList);
        Collections.sort(tempHeight, new HeightComparator());
        tempProductHeight = tempHeight.get(0);
        temp = productList.indexOf(tempProductHeight);
        tempProductHeight = productList.remove(temp);
                
        return tempHeight;    
    }
        
    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
        
        public ArrayList<Product> widthSorter(ArrayList<Product> prodList) {
        
        int temp = 0;
        Product tempProductWidth = null;
        WidthComparator comparator = new WidthComparator();
        ArrayList<Product> tempWidth = new ArrayList<Product>(productList);
        Collections.sort(tempWidth, new WidthComparator());
        tempProductWidth = tempWidth.get(0);
        temp = productList.indexOf(tempProductWidth);
        tempProductWidth = productList.remove(temp);
                
        return tempWidth;    
    }
        
    /** 
     * This class is a comparator that compares PCB objects. It orders the PCBs
     * with the shortest job at the front of the queue. It is used by the SJF
     * algorithm.
     * 
     * @author Benjamin Menning
     * @version 02/14/2016
     */
    public class HeightComparator implements Comparator<Product>
    {
        @Override
        public int compare(Product x, Product y){
        
        double prodHeightX = x.getHeight();
        double prodHieghtY = y.getHeight();
        if (prodHeightX > prodHieghtY) {
            return 1;
        }
        else if (prodHeightX == prodHieghtY){
            return 0;
        }
        else {
        return -1;
        }
        }
    }


    /** 
     * This class is a comparator that compares PCB objects. It orders the PCBs
     * with the shortest job at the front of the queue. It is used by the SJF
     * algorithm.
     * 
     * @author Benjamin Menning
     * @version 02/14/2016
     */
    public class WidthComparator implements Comparator<Product>
    {
        @Override
        public int compare(Product x, Product y)
        {
        
        double prodWidthX = x.getWidth();
        double prodWidthY = y.getWidth();
        if (prodWidthX > prodWidthY) {
            return 1;
        }
        else if (prodWidthX == prodWidthY){
            return 0;
        }
        else {
        return -1;
        }
        }
    }
    
    public void connectToDatabase()
    {
        int timeout = 0;
        try {
            if(connection == null || connection.isValid(timeout) == false) 
            {
                try
                {
                    Class.forName(className);
                }
                catch (ClassNotFoundException e)
                {
                    e.printStackTrace();
                }
                
                try
                {
                    connection = DriverManager.getConnection(databaseURL);
                    System.out.println("Connection Successful");
                }
                catch (SQLException e)
                {
                    System.out.println("Connection Failed");
                    e.printStackTrace();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(Optimizer.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    
    public void addProduct(String productNumber, String productName, 
            String productType, String height, String width, String depth) 
            throws SQLException
    {
        Statement stmt = null;
        String productNumberStr = "'" + productNumber + "'";
        String productNameStr = "'" + productName + "'";
        String productTypeStr = "'" + productType + "'";
        String query = "INSERT INTO Product(productID, productNumber, name, "
                + "typeID, height, width, depth)"
                + "\nVALUES (null, " + productNumberStr + ", " +
                productNameStr + ", " +
                productTypeStr + ", " +
                height + ", " +
                width + ", " +
                depth + ")";
        try 
        {
            System.out.println(query);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
    }
    
    public void removeProduct(String productNumber) throws SQLException
    {
        Statement stmt = null;
        String productNumberStr = "'" + productNumber + "'";
        String query = "DELETE FROM Product"
                + "\nWHERE ProductNumber = " + productNumberStr;
        try 
        {
            System.out.println(query);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
    }
    
    public ArrayList<String> getProductNumberList() throws SQLException
    {
        ArrayList<String> productNumberList = new ArrayList<String>();
        productNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Product";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String productNumber = rs.getString("productNumber");
                productNumberList.add(productNumber);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return productNumberList;
    }
    
    public void addStore(String storeNumber, String storeName, 
            String totalUnits, String petUnits, String foodUnits, String 
                    clothingUnits, String cleaningUnits) 
            throws SQLException
    {
        Statement stmt = null;
        String storeNumberStr = "'" + storeNumber + "'";
        String storeNameStr = "'" + storeName + "'";
        String query = "INSERT INTO Store(storeID, storeNumber, name, "
                + "totalUnits, totalPetUnits, totalFoodUnits, "
                + "totalClothingUnits, totalCleaningUnits)"
                + "\nVALUES (null, " + storeNumberStr + ", " +
                storeNameStr + ", " +
                totalUnits + ", " +
                petUnits + ", " +
                foodUnits + ", " +
                clothingUnits + ", " +
                cleaningUnits + ")";
        try 
        {
            System.out.println(query);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
    }
    
    public void removeStore(String storeNumber) throws SQLException
    {
        Statement stmt = null;
        String storeNumberStr = "'" + storeNumber + "'";
        String query = "DELETE FROM Store"
                + "\nWHERE StoreNumber = " + storeNumberStr;
        try 
        {
            System.out.println(query);
            stmt = connection.createStatement();
            stmt.executeUpdate(query);
        }
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
    }
    
    public ArrayList<String> getStoreNumberList() throws SQLException
    {
        ArrayList<String> storeNumberList = new ArrayList<String>();
        storeNumberList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Store";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                String storeNumber = rs.getString("storeNumber");
                storeNumberList.add(storeNumber);
            }
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return storeNumberList;
    }
    
    public String determineTypeID(String type) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        String typeID = "";
        String typeStr = "'" + type + "'";
        String query = "SELECT typeID \nFROM Type" 
                + "\nWHERE type = " + typeStr;
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);        
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
//          rs.next();
          typeID = rs.getString("typeID");
        }    
        return typeID;
    }
    
    public String determineProductID(String productNumber) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        String productID = "";
        String productNumberStr = "'" + productNumber + "'";
        String query = "SELECT productID \nFROM Product" 
                + "\nWHERE productNumber = " + productNumberStr;
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);        
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
//          rs.next();
          productID = rs.getString("productID");
        }    
        return productID;
    }
    
    public String determineStoreID(String storeNumber) throws SQLException
    {
        connectToDatabase();
        Statement stmt = null;
        String storeID = "";
        String storeNumberStr = "'" + storeNumber + "'";
        String query = "SELECT storeID \nFROM Store" 
                + "\nWHERE storeNumber = " + storeNumberStr;
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);        
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next())
        {
            // do nothing
        }
        else 
        {
//          rs.next();
          storeID = rs.getString("storeID");
        }    
        return storeID;
    }
    
    public ArrayList<Product> getProductList() throws SQLException
    {
        ArrayList<Product> productList = new ArrayList<Product>();
//        productList.add("");
        Statement stmt = null;
        String query = "SELECT *\nFROM Product";
        try 
        {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) 
            {
                 
                Product product = new Product();
                product.setProductNumber(rs.getString("productNumber"));
                productList.add(product);
            }
            
//List<Entity> entities = new ArrayList<Entity>();
//// ...
//while (resultSet.next()) {
//    Entity entity = new Entity();
//    entity.setId(resultSet.getLong("id"));
//    entity.setName(resultSet.getString("name"));
//    entity.setValue(resultSet.getInt("value"));
//    // ...
//    entities.add(entity);
//}        
            
        } 
        catch (SQLException e ) 
        {
            throw e;
        } 
        finally 
        {
            if (stmt != null) { stmt.close(); }
        }    
        return productList;
    }
    
    public static void main(String[] args) throws SQLException {
        
        boolean done = false;
        double maxWidth = 48;
        double maxHeight = 84;
        double totalProductWidth = 0;
        
        ArrayList<Product> productList = new ArrayList<Product>();
        
        
        
//        Optimizer optimizer = new Optimizer();
//        Product product = new Product("NULL", "4J329KF3", "Test", "Pet", 10, 10, 
//            10);
//        optimizer.connectToDatabase();
//        System.out.println(optimizer.getProductNumberList());
////        optimizer.addProduct("4J329KF3", "Test", "1", "10", "10", "10");
////        optimizer.removeProduct("4J329KF3");
//
////        optimizer.addStore("3J40IO", "Test", "40", "10", "10", "10", "10");
////        optimizer.removeStore("3J40IO");
//        // TODO code application logic here
//        
//        //Filling a Shelf based on width
//        int index = 0;
//        while (!done) {
//        if(maxWidth > totalProductWidth) {
//            //Shelf = heightList.get(index);
//            Shelf.add(index, heightList.get(index));
//            index++;
//            totalProductWidth += heightList.get(index).getWidth();
//        }//end if
//        
//        if (totalProductWidth > maxWidth) {
//            totalProductWidth -= heightList.get(index).getWidth();            
//            //Shelf; //remove last element 
//            Shelf.remove(index);
//            index--;
//            done = true;
//        }//end if
//        
//        }//end while
//        
//        
//        
    }    
}
