/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.business;

public class Product {
    private int productId, Quantity,supplierId;
    private String productCode,productName,productDesc;
    private double cost,retailPrice,discount;
    public Product(){
        
    }
    public Product(String productcode){
       this.productCode=productcode; 
    }
    public Product(int productid, String productcode, String productname,  double retail, int productquantity){
        this.productId = productid;
        this.productCode = productcode;
        this.productName = productname;
        this.retailPrice = retail;
        this.Quantity = productquantity;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
    

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public double getRetailPrice() {
        return retailPrice;
    }

    public void setRetailPrice(double retailPrice) {
        this.retailPrice = retailPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    
    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }
    
    public int getProductId() {
        return productId;
    }

    

    public int getQuantity() {
        return Quantity;
    }

    public String getProductCode() {
        return productCode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductId(int productid) {
        this.productId = productid;
    }

   

    public void setQuantity(int productquantity) {
        this.Quantity = productquantity;
    }

    public void setProductCode(String productcode) {
        this.productCode = productcode;
    }

    public void setProductName(String productname) {
        this.productName = productname;
    }
    
    
    
}

    

