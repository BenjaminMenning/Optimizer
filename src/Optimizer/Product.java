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
public class Product {
    private double height;
    private double width;
    private double depth;
    private String productID;
    private String productNumber;
    private String productType;
    private String productName;
//    private ProductImage gui;

    public Product() {
        
    }

    public Product(String productID, String productNumber, String productName, 
            String productType, double height, double width, double depth) 
    {
        this.productID = productID;
        this.productNumber = productNumber;
        this.productName = productName;
        this.productType = productType;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setLength(double depth) {
        this.depth = depth;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }
    
    public void setProductNumber(String productNumber)
    {
        this.productNumber = productNumber;
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
        return depth;
    }

    public String getProductID() {
        return productID;
    }
    
    public String getProductNumber()
    {
        return productNumber;
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
                + ", depth=" + depth + ", productID=" + productID 
                + ", productType=" + productType + '}';
    }
    
    
     
    
    
}
