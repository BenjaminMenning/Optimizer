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
 * This class contains a main method that allows a user to run the Optimizer
 * shelving space management program.
 *
 * @author Jarrud Diercks, Zach Ellefson, Seema Mane, Benjamin Menning
 * @version 04/26/2016
 */
public class Optimizer implements OptimizerADT {

    // String variables for database connectivity
    private String className = "org.sqlite.JDBC";
    private String databaseURL = "jdbc:sqlite:Optimizer.db";
    private String databaseUsername = "blah";
    private String databasePassword = "blah";

    //True for Width
    //False for Height
    public static boolean sortType = true;

    // Connection variable for connecting to the database
    private Connection connection = null;

    public int shelfIndex = 0;

    public static ArrayList<ArrayList<Product>> shelf = new ArrayList<ArrayList<Product>>();

    public static ArrayList<Unit> Units = new ArrayList<Unit>();

    public static ArrayList<Product> productList = new ArrayList<Product>();
    public static Product sortedWidth = new Product();
    public static Product sortedHeight = new Product();
    //public static Product Shelf = new Product();

    public static ArrayList<Product> Shelf = new ArrayList<>();

//    public ArrayList<Product> heightList = heightSorter();
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
    @Override
    public ArrayList<Product> heightSorter(ArrayList<Product> prodList) {

//        int temp = 0;
//        Product tempProductHeight = null;
        ArrayList<Product> tempHeight = new ArrayList<Product>(prodList);
        Collections.sort(tempHeight, new HeightComparator());
//        tempProductHeight = tempHeight.get(0);
//        temp = productList.indexOf(tempProductHeight);
//        tempProductHeight = productList.remove(temp);

        return tempHeight;
    }

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    @Override
    public ArrayList<Product> widthSorter(ArrayList<Product> prodList) {

//        int temp = 0;
//        Product tempProductWidth = null;
        ArrayList<Product> tempWidth = new ArrayList<Product>(prodList);
        Collections.sort(tempWidth, new WidthComparator());
//        tempProductWidth = tempWidth.get(0);
//        temp = productList.indexOf(tempProductWidth);
//        tempProductWidth = productList.remove(temp);

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
     * This class is a comparator that compares PCB objects. It orders the PCBs
     * with the shortest job at the front of the queue. It is used by the SJF
     * algorithm.
     *
     * @author Benjamin Menning
     * @version 02/14/2016
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
            System.out.println(query);
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
            System.out.println(query);
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
            System.out.println(query);
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
            System.out.println(query);
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
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
            // do nothing
        } else {
//          rs.next();
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
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
            // do nothing
        } else {
//          rs.next();
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
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
            // do nothing
        } else {
//          rs.next();
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
        System.out.println(query);
        stmt = connection.createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = stmt.executeQuery(query);
        if (!rs.next()) {
            // do nothing
        } else {
//          rs.next();
            storeID = rs.getString("storeID");
        }
        return storeID;
    }

    @Override
    public ArrayList<Product> getProductList() throws SQLException {
        ArrayList<Product> productList = new ArrayList<Product>();
//        productList.add("");
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
        return "";
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
        // ArrayList<Product> productList = new ArrayList<Product>();
        productList = optimizer.getProductList();
//        System.out.println(productList.toString());
        ArrayList<Product> widthList = optimizer.heightSorter(productList);
        //System.out.println(heightList.toString());

        /*        
         //shelf Hight----------------------------------------------------------
         double templargest = 0;       
         for(int i =0; i < heightList.size(); i++){
                           
         }//end for        
         System.out.println("Tallset prod: " + templargest);
        
         //Finding shelf Hight
         double nextShelf;
         nextShelf = templargest + 2;        
         System.out.println("Next Shelf Height is: " + nextShelf);                        
        
         double listSize = heightList.size();
        
        
         //Filling Shelf one EDI++++++++++++++++++++++++++++++++++++++++++++++++
         tallest = 0;
         listSize = heightList.size();
        
         shelfAheight = tallest;
        
         for (double i = 0; i < listSize; i++) {
         if (totalProductWidth < maxWidth) {
         shelf.get(0).add(heightList.get(0));            
         totalProductWidth += heightList.get(0).getWidth();
                
         if(tallest < heightList.get(0).getHeight()){
         tallest = heightList.get(0).getHeight();                        
         }//end if                 
                
         heightList.remove(0);
         }//end if
         else{
         break;
         }            
         }//end for       
              
         //Shelve 2 Hight -------------------------------------------------------------------
         shelfHeight += (tallest + individuelShelfHeight);
         System.out.println("Next Shelf high is  " + shelfHeight+ "\n");
         shelfBheight = shelfHeight;

         //Filling shelf Two------------------------------------------------------                      
         if (shelfHeight < maxHeight){
         tallest = 0;
         listSize = heightList.size();
         totalProductWidth = 0;
        
         for (double i = 0; i < listSize; i++) {
         if (totalProductWidth < maxWidth) {
         shelf.get(1).add(heightList.get(0));            
         totalProductWidth += heightList.get(0).getWidth();
                
         if(tallest < heightList.get(0).getHeight()){
         tallest = heightList.get(0).getHeight();                        
         }//end if                 
                
         heightList.remove(0);
         }//end if
         else{
         break;
         }            
         }//end for

    
         }//end if
       
       
         //Filling shelf Three------------------------------------------------------  
         //Height for Shelf 3
         shelfHeight += (tallest + individuelShelfHeight);               
         shelfCheight = shelfHeight;
           
         //Filling Shelf 3
         if (shelfHeight < maxHeight){
         tallest = 0;
         listSize = heightList.size();
         totalProductWidth = 0;
        
         for (double i = 0; i < listSize; i++) {
         if (totalProductWidth < maxWidth) {
         shelf.get(2).add(heightList.get(0));            
         totalProductWidth += heightList.get(0).getWidth();
                
         if(tallest < heightList.get(0).getHeight()){
         tallest = heightList.get(0).getHeight();                        
         }//end if                 
                
         heightList.remove(0);
         }//end if
         else{
         break;
         }            
         }//end for
    
         }//end if                                   
       
         //Filling shelf Four------------------------------------------------------ 
         //Shelf 4 Height
         shelfHeight += (tallest + individuelShelfHeight);               
         shelfDheight = shelfHeight;

       
         if (shelfHeight < maxHeight){
         tallest = 0;
         listSize = heightList.size();
         totalProductWidth = 0;
        
         for (double i = 0; i < listSize; i++) {
         if (totalProductWidth < maxWidth) {
         shelf.get(3).add(heightList.get(0));            
         totalProductWidth += heightList.get(0).getWidth();
                
         if(tallest < heightList.get(0).getHeight()){
         tallest = heightList.get(0).getHeight();                        
         }//end if                 
                
         heightList.remove(0);
         }//end if
         else{
         break;
         }            
         }//end for           
         }//end if
         */
        System.out.println("here we are");
        fillUnits();
        unitPrint();

       //Calling Print out
        //shelfPrintOut(shelfAheight, shelfBheight, shelfCheight, shelfDheight);
//       //Shelf PrintOUT--------------------------------------------------------
//       int indexA = 1;
//       int indexB = 1;
//       int indexC = 1;
//       int indexD = 1;       
//       
//       System.out.println("\n\nShelf 1");
//       System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
//       
//       for(int i =0; i < shelf.get(0).size(); i++){
//            System.out.print("A" + indexA + ".\t");
//            System.out.print(shelf.get(0).get(i));
//            indexA++;
//       }//end A forLoop
//       
//       
//       if(!shelf.get(1).isEmpty()){
//           System.out.println("\n\nShelf 2");
//            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");           
//           for(int i =0; i < shelf.get(1).size(); i++){
//            System.out.print("B" + indexB + ".\t");
//            System.out.print(shelf.get(1).get(i));
//            indexB++;
//
//            }//end B forLoop
//        }//end B if   
//       
//        if(!shelf.get(2).isEmpty()){
//            System.out.println("\n\nShelf 3");
//            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");            
//           for(int i =0; i < shelf.get(2).size(); i++){
//            System.out.print("C" + indexC + ".\t");
//            System.out.print(shelf.get(1).get(i));
//            indexC++;
//
//            }//end C forLoop
//       }//end C if 
//       
//        if(!shelf.get(3).isEmpty()){
//           System.out.println("\n\nShelf 4");
//           
//            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");           
//           for(int i =0; i < shelf.get(3).size(); i++){
//            System.out.print("D" + indexD + ".\t");
//            System.out.print(shelf.get(3).get(i));
//            indexD++;
//
//            }//end B forLoop
//       }//end B if 
        ArrayList<Product> heightList = optimizer.heightSorter(productList);

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
    }//end main

    public static void shelfPrintOut(double A, double B, double C, double D) {

        File fileName = new File("Test File");

        int indexA = 1;
        int indexB = 1;
        int indexC = 1;
        int indexD = 1;

        System.out.println("\n\nShelf 1");
        System.out.println("\nHeight of Shelf 1 is: " + A);

        System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");

        for (int i = 0; i < shelf.get(0).size(); i++) {
            System.out.print("A" + indexA + ".\t");
            System.out.print(shelf.get(0).get(i));
            indexA++;
        }//end A forLoop                          

        if (!shelf.get(1).isEmpty()) {
            System.out.println("\n\nShelf 2");
            System.out.println("\nHeight of Shelf 2 is: " + B);
            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
            for (int i = 0; i < shelf.get(1).size(); i++) {
                System.out.print("B" + indexB + ".\t");
                System.out.print(shelf.get(1).get(i));
                indexB++;

            }//end B forLoop
        }//end B if   

        if (!shelf.get(2).isEmpty()) {
            System.out.println("\n\nShelf 3");
            System.out.println("\nHeight of Shelf 3 is: " + C);
            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
            for (int i = 0; i < shelf.get(2).size(); i++) {
                System.out.print("C" + indexC + ".\t");
                System.out.print(shelf.get(1).get(i));
                indexC++;

            }//end C forLoop
        }//end C if 

        if (!shelf.get(3).isEmpty()) {
            System.out.println("\n\nShelf 4");
            System.out.println("\nHeight of Shelf 4 is: " + D);
            System.out.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
            for (int i = 0; i < shelf.get(3).size(); i++) {
                System.out.print("D" + indexD + ".\t");
                System.out.print(shelf.get(3).get(i));
                indexD++;

            }//end B forLoop
        }//end B if 

//to Doc starts here========================================================================================================        
        try {

            PrintWriter output = new PrintWriter(fileName);
            output.println("\n\nShelf 1");
            output.println("\nHeight of Shelf 1 is: " + A);
            output.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");

            for (int i = 0; i < shelf.get(0).size(); i++) {
                output.print("A" + indexA + ".\t");
                output.println(shelf.get(0).get(i));
                indexA++;
            }//end A forLoop
            output.println("\n");

            if (!shelf.get(1).isEmpty()) {
                output.println("\n\nShelf 2");
                output.println("\nHeight of Shelf 2 is: " + B);
                output.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
                for (int i = 0; i < shelf.get(1).size(); i++) {
                    output.print("B" + indexB + ".\t");
                    output.println(shelf.get(1).get(i));
                    indexB++;

                }//end B forLoop
                output.println("\n");
            }//end B if  

            if (!shelf.get(2).isEmpty()) {
                output.println("\n\nShelf 3");
                output.println("\nHeight of Shelf 3 is: " + C);
                output.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
                for (int i = 0; i < shelf.get(2).size(); i++) {
                    output.print("C" + indexC + ".\t");
                    output.println(shelf.get(1).get(i));
                    indexC++;

                }//end C forLoop
                output.println("\n");
            }//end C if 

            if (!shelf.get(3).isEmpty()) {
                output.println("\n\nShelf 4");
                output.println("\nHeight of Shelf 4 is: " + D);
                output.println("Placement\tProduct Number\tProduct Name\tProduct Type\t\tProduct Height\tProduct Width\tProduct Depth");
                for (int i = 0; i < shelf.get(3).size(); i++) {
                    output.print("D" + indexD + ".\t");
                    output.println(shelf.get(3).get(i));
                    indexD++;

                }//end B forLoop
                output.println("\n");
            }//end B if 

            output.close();

        }//end try
        catch (Exception e) {
            JOptionPane.showMessageDialog(null, "I cannot create that file");
        }//end catch

    }//end shelf print out   

    public static void fillUnits() {

        Optimizer optimizer = new Optimizer();

        //True for Width
        //False for Height
        sortType = true;

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

            }

        }

    }//end fillUnits

    public static void unitPrint() throws IOException {
        int index = 0;
        for (int i = 0; i < Units.size(); i++) {
            System.out.println(Units.get(i));

        }

        PrintWriter printOut = new PrintWriter("StorePrintOut.txt");
//        while(!Units.isEmpty()){           
//            printOut.println(Units.get(index));
//            Units.remove(0);
//        }
        printOut.println(Units.get(index));
        printOut.close();
    }

}//end class
