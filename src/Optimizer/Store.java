package Optimizer;



import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author SMMane3074
 */
public class Store {
    
    private String storeID;
    private String storeNumber;
    private int totalUnits; // 50 for food 
    private ArrayList<UnitType> storeUnitsTypeList = new ArrayList<UnitType>();
    private ArrayList<Product> productList = new ArrayList<Product>();
    private ArrayList<Unit> unitList = new ArrayList<Unit>();
    
    public void setStoreID(String storeID)
    {
        this.storeID = storeID;
    }
    
    public String getStoreID()
    {
        return storeID;
    }
    
    public void setStoreNumber(String storeNumber)
    {
        this.storeNumber = storeNumber;
    }
    
    public String getStoreNumber()
    {
        return storeNumber;
    }
    
    public void setTotalUnits(int totalUnits)
    {
        this.totalUnits = totalUnits;
    }
    
    public int getTotalUnits()
    {
        return totalUnits;
    }
    
    public void setStoreUnitsType(ArrayList<UnitType> typeList)
    {
        
    }
    
    public ArrayList<UnitType> getStoreUnitsType()
    {
        ArrayList<UnitType> blah = new ArrayList<UnitType>();
        return blah;
    }

//    public void addProduct(Product product)
//    {
//    }
//
//    public Product removeProduct(String productID)
//    {
//        Product product3 = new Product(0, 0, 0, "", "");
//        return product3;
//    }
//    
//    public void addUnit(Unit unit)
//    {
//        
//    }
//
//    public void removeUnit(String unitID)
//    {
//        
//    }
}
