/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author SMMane3074
 */
public class Product {
    private double height;
    private double width;
    private double length;
    private String productID;
    private String productType;
    private String productName;
//    private ProductImage gui;

    public Product() {
    }

    public Product(double height, double width, double length, String productID, String productType) {
        this.height = height;
        this.width = width;
        this.length = length;
        this.productID = productID;
        this.productType = productType;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }
    
    public void setProductName(String productName)
    {
        this.productName = productName;
    }

    public double getHeight() {
        return height;
    }

    public double getWidth() {
        return width;
    }

    public double getLength() {
        return length;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductType() {
        return productType;
    }
    
    public String getProductName()
    {
        return productName;
    }

    @Override
    public String toString() {
        return "Product{" + "height=" + height + ", width=" + width 
                + ", length=" + length + ", productID=" + productID 
                + ", productType=" + productType + '}';
    }
    
    
     
    
    
}
