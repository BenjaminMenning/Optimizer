package Optimizer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SMMane3074
 */
public interface OptimizerADT {
    
  public void addProduct(Product pet);
  public void removeProduct(Product pet);
  public void addStore(Store StoreId);
  public void removeStore(Store StoreId);
  public void loadStore(Store storeID);
  public void connectToDatabase();
  public void loadDatabaseInfo();
  public void optimizeShelves(Product[] product, Shelf[] shelfId);
  public void displayReport();
  public void generateReport();
  
  
    
}
