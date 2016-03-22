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
public class Shelf {
    
    private final double height =2;
    private final double length =24;
    private final double width =48;
    private String shelfId;

    public Shelf(String shelfId) {
        this.shelfId = "NA";
        this.shelfId = shelfId;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public String getShelfId() {
        return shelfId;
    }

    @Override
    public String toString() {
        return "Shelf{" + "height=" + height + ", length=" + length 
                + ", width=" + width + ", shelfId=" + shelfId + '}';
    }
   
     

   
    
}
