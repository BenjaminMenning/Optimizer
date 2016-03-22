
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                + "\nVALUES (null,   " + productNumberStr + ", " +
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
    
    public static void main(String[] args) throws SQLException {
        Optimizer optimizer = new Optimizer();
        Product product = new Product("NULL", "4J329KF3", "Test", "Pet", 10, 10, 
            10);
        optimizer.connectToDatabase();
//        optimizer.addProduct("4J329KF3", "Test", "1", "10", "10", "10");
        optimizer.removeProduct("4J329KF3");

        // TODO code application logic here
    }    
}