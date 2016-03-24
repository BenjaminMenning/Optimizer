package Optimizer;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Seema
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
    
    //Height sorting clone of "productList"
    public static ArrayList<Product> cloneList = new ArrayList(productList);

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

    
    public static void main(String[] args) throws SQLException {
        Optimizer optimizer = new Optimizer();
        Product product = new Product("NULL", "4J329KF3", "Test", "Pet", 10, 10, 
            10);
        optimizer.connectToDatabase();
//        optimizer.addProduct("4J329KF3", "Test", "1", "10", "10", "10");
//        optimizer.removeProduct("4J329KF3");

//        optimizer.addStore("3J40IO", "Test", "40", "10", "10", "10", "10");
//        optimizer.removeStore("3J40IO");
        // TODO code application logic here
    }    
}