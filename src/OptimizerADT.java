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
    
  public void AddProduct(Product pet);
  public void RemoveProduct(Product pet);
  public void AddStore(Store StoreId);
  public void RemoveStore(Store StoreId);
  public void optimise(Product[] product, Shelf[] shelfId);
  
    
}
