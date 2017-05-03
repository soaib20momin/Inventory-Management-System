/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.business;
import humberinventory.data.DCommunicator;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author Bradley Blanchard
 */
/**
 * 
 * Class Used to Provide public methods for presentation layer
 * to interact with business layer
 * first instantiate BCommunicator object then call the needed method
 */

public class BCommunicator {
    ArrayList<Product> prodList;
    DCommunicator dCom;
    ResultSet results;
    Product prod;
    Supplier supplier;
    ArrayList<Supplier> supList;
    Connection con;
    User user;
    int check;
    
    public int checkUser(String username){
       dCom = new DCommunicator();
        results = dCom.getUser(username);
       
        try {
            while (results.next()){
            check=1;
            }
            
        }
        catch(Exception ex) {
            check=-1;
        }
        finally {
            return check;
        } 
    }
    public int checkUser(String username, String password){
        dCom = new DCommunicator();
        results = dCom.getUser(username, password);
        
        try {
            while (results.next()){
            check=1;
            }
             
        }
        catch(Exception ex) {
           check = -1;
        }
        finally {
            return check;
        } 
    }
    public User getUser(String username){
        dCom = new DCommunicator();
        results = dCom.getUser(username);
        user =new User();
        try {
            while (results.next()){
            user.setUsername(results.getString("username"));
            }
        }
        catch(Exception ex) {
            
        }
        finally {
            dCom.closeCon();
            return user;
        }
    }
     public User getUser(String username, String pass){
        dCom = new DCommunicator();
        user =new User();
        
        results = dCom.getUser(username, pass);
        
        try {
            while (results.next()){
            user.setUsername(results.getString("username"));
            }
        }
        catch(Exception ex) {
            
        }
        finally {
            dCom.closeCon();
            return user;
        }
    }
    public int addUser(String user, String pass, String type){
        dCom = new DCommunicator();
        try {
            check=dCom.addUser(user,pass, type);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    public int deleteUser(int userId){
        dCom = new DCommunicator();
        try {
            check=dCom.deleteUser(userId);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    public int updateUser(int userId,String user, String pass, String type){
        dCom = new DCommunicator();
        try {
            check=dCom.updateUser(userId,user,pass,type);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    
    // Product Operations 
    
    // Presentation can add a New Product  with this method
    public int addProduct(String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount){
        dCom = new DCommunicator();
        try {
            check=dCom.addProduct(prodCode, prodName,desc, cost,
                 quantity, retailPrice, discount);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    //Overloaded to accept supplierId
    public int addProduct(String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount,int supId){
        dCom = new DCommunicator();
        try {
            check=dCom.addProduct(prodCode, prodName,desc, cost,
                 quantity, retailPrice, discount,supId);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    //Update Method
    public int updateProduct(int productId, String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount){
        dCom = new DCommunicator();
        try {
            check=dCom.updateProduct(productId,prodCode, prodName,desc, cost,
                 quantity, retailPrice, discount);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    //Overloaded to accept supplierId
    public int updateProduct(int productId, String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount,int supId){
        dCom = new DCommunicator();
        try {
            check=dCom.updateProduct(productId,prodCode, prodName,desc, cost,
                 quantity, retailPrice, discount,supId);
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    public ArrayList<Product> getProductList(){
      dCom = new DCommunicator();  
      results=dCom.getProducts();
      prodList = new ArrayList<>();
      try {
          while (results.next()){
           // Create a product object 
           prod = new Product();
          // set Product Properties 
              prod.setProductId(results.getInt("product_ID"));
              prod.setProductCode(results.getString("product_code"));
              prod.setProductName(results.getString("product_name"));
              prod.setProductDesc(results.getString("description"));
              prod.setCost(results.getDouble("cost"));
              prod.setDiscount(results.getDouble("discount"));
              prod.setRetailPrice(results.getDouble("retail_price"));
              prod.setQuantity(results.getInt("quantity"));
              prod.setSupplierId(results.getInt("supplier_ID"));
          
          // add Product to List
            prodList.add(prod);
            
           }
                   
         }
      catch (Exception ex){
          ex.printStackTrace();
          prodList=null;
      }
      finally {
          dCom.closeCon();
          return prodList;
          
      }
           
    }
    public int deleteProduct(int prodId){
        
        dCom = new DCommunicator();
        try {
         check=dCom.deleteProduct(prodId);   
        }
        catch (Exception ex){
          ex.printStackTrace();
          
      }
      finally {
           dCom.closeCon();
           return check;
          
      }
    }
        
        // Supplier Methods 
    //Get Single Supplier 
    public Supplier getSupplier(String name){
      dCom = new DCommunicator();  
      results=dCom.getSupplier(name);
      
      try {
          while (results.next()){
           // Create a supplier object 
           supplier = new Supplier();
          // set supplier Properties 
              supplier.setSupID(results.getInt("supplier_ID"));
              supplier.setSupName(results.getString("supplier_name"));
              supplier.setSupAddress(results.getString("address"));
              supplier.setSupEmail(results.getString("email"));
              supplier.setSupPhone(results.getString("contact"));
                        
           }
           return supplier;
                   
         }
      catch (Exception ex){
          ex.printStackTrace();
          supplier=null;
          return supplier;
      }
      finally {
          dCom.closeCon();
          
          
      }
           
    }
    
    // Overloaded get Supplier based on id 
     public Supplier getSupplier(int supId){
      dCom = new DCommunicator();  
      results=dCom.getSupplier(supId);
      
      try {
          while (results.next()){
           // Create a supplier object 
           supplier = new Supplier();
          // set supplier Properties 
              supplier.setSupID(results.getInt("supplier_ID"));
              supplier.setSupName(results.getString("supplier_name"));
              supplier.setSupAddress(results.getString("address"));
              supplier.setSupEmail(results.getString("email"));
              supplier.setSupPhone(results.getString("contact"));
                        
           }
           return supplier;
                   
         }
      catch (Exception ex){
          ex.printStackTrace();
          supplier=null;
          return supplier;
      }
      finally {
          dCom.closeCon();
          
          
      }
           
    }
    
    // get Supplier List 
      public ArrayList<Supplier> getSupList(){
      dCom = new DCommunicator();  
      results=dCom.getSuppliers();
      supList = new ArrayList<>();
      try {
          while (results.next()){
           // Create a supplier object 
           supplier = new Supplier();
          // set supplier Properties 
              supplier.setSupID(results.getInt("supplier_ID"));
              supplier.setSupName(results.getString("supplier_name"));
              supplier.setSupAddress(results.getString("address"));
              supplier.setSupEmail(results.getString("email"));
              supplier.setSupPhone(results.getString("contact"));
              
          
          // add Supplier to List
            supList.add(supplier);
            
           }
                   
         }
      catch (Exception ex){
          ex.printStackTrace();
          supList=null;
      }
      finally {
          dCom.closeCon();
          return supList;
          
      }
           
    }
      // Add Supplier 
       public int addSupplier(String supName,
                 String address,String contact,String email){
        dCom = new DCommunicator();
        try {
            check=dCom.addSupplier(supName,
                 address,contact,email );
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
       public int addSupplierReturn(String supName,
                 String address,String contact,String email){
        dCom = new DCommunicator();
        try {
            check=dCom.addSupplierReturn(supName,
                 address,contact,email );
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
       
       
    
       
    public int updateSupplier(int supId,String supName,
                 String address,String contact,String email){
        dCom = new DCommunicator();
        try {
            check=dCom.updateSupplier(supId, supName,
                 address,contact,email );
        }
        catch (Exception ex){
          ex.printStackTrace();
         }
      finally {
          dCom.closeCon();
          return check;
          
      }
    }
    public int deleteSupplier(String name){
        
        dCom = new DCommunicator();
        try {
         check=dCom.deleteSupplier(name);   
        }
        catch (Exception ex){
          ex.printStackTrace();
          
      }
      finally {
           dCom.closeCon();
           return check;
          
      }
    }
     public int deleteSupplier(int supId){
        
        dCom = new DCommunicator();
        try {
         check=dCom.deleteSupplier(supId);   
        }
        catch (Exception ex){
          ex.printStackTrace();
          
      }
      finally {
           dCom.closeCon();
           return check;
          
      }
    }
        
    
}
