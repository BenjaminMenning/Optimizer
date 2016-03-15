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
    private String productId;
    private String productType;
    private String productName;
//    private ProductImage gui;

    public Product() {
    }

    public Product(double height, double width, double length, String productId, String productType) {
        this.height = height;
        this.width = width;
        this.length = length;
        this.productId = productId;
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

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setProductType(String productType) {
        this.productType = productType;
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

    public String getProductId() {
        return productId;
    }

    public String getProductType() {
        return productType;
    }

    @Override
    public String toString() {
        return "Product{" + "height=" + height + ", width=" + width 
                + ", length=" + length + ", productId=" + productId 
                + ", productType=" + productType + '}';
    }
    
    
     
    
    
}
