/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Optimizer;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author bmenning13
 */
public interface OptimizerADT {

    void addProduct(String productNumber, String productName, String productType, String height, String width, String depth) throws SQLException;

    void addStore(String storeNumber, String storeName, String totalUnits, String petUnits, String foodUnits, String clothingUnits, String cleaningUnits) throws SQLException;

    void connectToDatabase();

    String determineProductID(String productNumber) throws SQLException;

    String determineStoreID(String storeNumber) throws SQLException;

    String determineType(int typeID) throws SQLException;

    String determineTypeID(String type) throws SQLException;

    String generateReport(boolean isWidthSort, String storeNumber, String name, int totalUnits);

    ArrayList<Product> getProductList() throws SQLException;

    ArrayList<String> getProductNumberList() throws SQLException;

    ArrayList<String> getStoreNumberList() throws SQLException;

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
    ArrayList<Product> heightSorter(ArrayList<Product> prodList);

    void removeProduct(String productNumber) throws SQLException;

    void removeStore(String storeNumber) throws SQLException;

    //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
    ArrayList<Product> widthSorter(ArrayList<Product> prodList);
    
}
