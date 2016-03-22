/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author Seema
 */


import java.util.ArrayList;


public class Optimizer {

    
    
    
    public static ArrayList<Product> productList = new ArrayList<>();
    
    //Height sorting clone of "productList"
    public static ArrayList<Product> cloneList = new ArrayList(productList);
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Store store[];
        // TODO code application logic here
    }
    
    public static void heightSorter() {
        
        //ArrayList<Product> cloneList = new ArrayList(productList);
        
        
        int smallInt = 0;
        int j = 0;
        int smallIntIndex = 0;
        
        for(int i=1; i < cloneList.size()-1; i++) {            
            smallInt = (int) cloneList.get(i-1).getHeight();
            smallIntIndex = (i-1);
            
            for(j=i; j < cloneList.size(); j++){
                if(cloneList.get(j).getHeight() < smallInt){
                    smallInt = (int) cloneList.get(j).getHeight();
                    smallIntIndex = j;
                }//end if                
            }//end for
                   
            swap(i-1, smallIntIndex);
            
        }//end for
        
        
    }//end heightSorter

    private  void swap(int sourceIndex, int destIndex) {
        int temp = cloneList.get(destIndex);
        cloneList.set(destIndex, cloneList.get(sourceIndex));
        cloneList.set(sourceIndex, temp); 
    }
    
    
    
    
}//end 
