/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.data;

import java.sql.*;

/**
 *
 * @author Bradley Blanchard
 */
public class DCommunicator {
    ResultSet result;
    Connection con;
    int check;
    
    
    public Connection getCon(){
            return con;
        }
    public void closeCon(){
       try {
         this.con.close();    
       }
       catch (Exception ex){
           ex.getMessage();
       }  
       
        
    }
    
    public int updateUser(int userId,String user, String pass, String type){
        con=ConOracle.getCon();
         try {
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
             System.out.println("Statement Object Ready");
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("update login  "
                    + " set username=?, password=?,type=? where user_ID=?"); 
            prestmt.setString(1, user);
            prestmt.setString(2, pass);
            prestmt.setString(3, type);
            prestmt.setInt(4, userId);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
        }
        finally {
          return check;
                 
        }
    }
    
    public int addUser(String user, String pass, String type){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("insert into login "
                    + "(username, password,) values (?,?,?)"); 
            prestmt.setString(1, user);
            prestmt.setString(2, pass);
            prestmt.setString(3, pass);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
        }
        finally {
          
          return check;
                 
        }
    }
    public ResultSet getUser(String user, String pass){
        
        con=ConOracle.getCon();
         try {
        // Prepared Statement Object 
            PreparedStatement prestmt = null;
            System.out.println("Statement Object Ready");
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from login "
                    + "where username=? and password=?"); 
            prestmt.setString(1, user);
            prestmt.setString(2, pass);
            result = prestmt.executeQuery();
            
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
           
        }
        finally {
          return result;
                 
        }
    }
     public ResultSet getUser(String username){
        
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
            
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from login "
                    + "where username=?"); 
            prestmt.setString(1, username);
            result = prestmt.executeQuery();
            
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            
          
        }
        finally {
          return result;
                 
        }
    }
     
     public int deleteUser(int userId){
            
            con=ConOracle.getCon();
         try {
        //4. Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
           
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("delete from login "
                    + "where user_ID=?"); 
            prestmt.setInt(1,userId);
            prestmt.executeUpdate();
            check=1;
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            check=-1;    
        }
        finally {
          return check;
                 
        }
            
        }
      //Get a supplier record with id
    public ResultSet getSupplier(int supId){
        
       
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
            
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from supplier "
                    + "where supplier_ID=?"); 
            prestmt.setInt(1, supId);
            result = prestmt.executeQuery();
            
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            }
        finally {
          return result;
                 
        }
    } 
     // Get a Supplier 
    public ResultSet getSupplier(String supName){
           con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
            
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from supplier "
                    + "where supplier_name=?"); 
            prestmt.setString(1, supName);
            result = prestmt.executeQuery();
            return result;
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
           return result; 
        }
        finally {
          
            return result;     
        }
    } 
    
    
    public ResultSet getSuppliers(){
       
        con=ConOracle.getCon();
         try {
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
             System.out.println("Statement Object Ready");
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from supplier"); 
            result = prestmt.executeQuery();
            
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
         }
        finally {
          return result;
                 
        }
    }
    
    // Add Supplier 
    
         public int addSupplier(String supName,
                 String address,String contact,String email ){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("insert into supplier "
                    + "(supplier_name,address,contact,email) "
                    + "values (?,?,?,?)"); 
            prestmt.setString(1,supName );
            prestmt.setString(2, address);
            prestmt.setString(3, contact);
            prestmt.setString(4, email);
            
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
         }
          public int addSupplierReturn(String supName,
                 String address,String contact,String email ){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("insert into supplier "
                    + "(supplier_name,address,contact,email) "
                    + "values (?,?,?,?) returning supplier_ID into sup_id"); 
            prestmt.setString(1,supName );
            prestmt.setString(2, address);
            prestmt.setString(3, contact);
            prestmt.setString(4, email);
            
            check=prestmt.executeUpdate();
            return check;
            
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
         }
    
    
    
    // Update supplier record
         public int updateSupplier(
                 int supplierId,String supName,
                 String address,String contact,String email){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("update supplier "
                    + "set supplier_name=?,address=?, contact=?, email=? "
                    + "where supplier_ID=?"); 
            prestmt.setString(1, supName);
            prestmt.setString(2, address);
            prestmt.setString(3, contact);
            prestmt.setString(4, email);
            prestmt.setInt(5, supplierId);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
    }
         public int deleteSupplier(String supplierName){
            
            con=ConOracle.getCon();
         try {
        //4. Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
           
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("delete from supplier "
                    + "where supplier_name=?"); 
            prestmt.setString(1,supplierName);
            prestmt.executeUpdate();
            check=1;
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            check=-1;    
        }
        finally {
          return check;
                 
        }
            
        }
        
          public int deleteSupplier(int supId){
            
            con=ConOracle.getCon();
         try {
        //4. Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
           
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("delete from supplier "
                    + "where supplier_ID=?"); 
            prestmt.setInt(1,supId);
            prestmt.executeUpdate();
            check=1;
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            check=-1;    
        }
        finally {
          return check;
                 
        }
            
        }
         
         
    
         // Product table Queries 
         //Get List of Products
      public ResultSet getProducts(){
       
        con=ConOracle.getCon();
         try {
        //4. Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
            
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("select * from product"); 
            result = prestmt.executeQuery();
            
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            
           
        
        }
        finally {
          return result;
                 
        }
    }  
        
        public int deleteProduct(int prodId){
            
            con=ConOracle.getCon();
         try {
        //4. Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
           
            // Select Statement
            
            //to check if only user is available
            prestmt = con.prepareStatement("delete from product "
                    + "where product_ID=?"); 
            prestmt.setInt(1,prodId);
            prestmt.executeUpdate();
            check=1;
    }
    
        catch (SQLException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            check=-1;    
        }
        finally {
          return check;
                 
        }
            
        }
        
        // Add a product record 
        
         public int addProduct(String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount ){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("insert into product"
                    + " (product_code,product_name,description,cost,quantity,retail_price,discount) "
                    + "values (?,?,?,?,?,?,?)"); 
            prestmt.setString(1,prodCode );
            prestmt.setString(2, prodName);
            prestmt.setString(3, desc);
            prestmt.setDouble(4, cost);
            prestmt.setInt(5, quantity);
             prestmt.setDouble(6, retailPrice);
            prestmt.setDouble(7, discount);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
         }
         
         // Add a product record overloaded for supplier 
        
         public int addProduct(String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount,int supId ){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("insert into product"
            + " (product_code,product_name,description,cost,quantity,retail_price,discount,supplier_ID) "
            + "values (?,?,?,?,?,?,?,?)"); 
            prestmt.setString(1,prodCode );
            prestmt.setString(2, prodName);
            prestmt.setString(3, desc);
            prestmt.setDouble(4, cost);
            prestmt.setInt(5, quantity);
            prestmt.setDouble(6, retailPrice);
            prestmt.setDouble(7, discount);
            prestmt.setDouble(8, supId);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
         }
    
    
        
         // Update an existing product record
         public int updateProduct(
                 int productId, String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("update product "
            + "set product_code=?,product_name=?, description=?, cost=?, "
            + "quantity=?, retail_price=?,discount=? where product_ID=?"); 
            prestmt.setString(1, prodCode);
            prestmt.setString(2, prodName);
            prestmt.setString(3, desc);
            prestmt.setDouble(4, cost);
            prestmt.setInt(5, quantity);
            prestmt.setDouble(6, retailPrice);
            prestmt.setDouble(7, discount);
            prestmt.setDouble(8, productId);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
    }
         
         // Update an existing product record overloaded for supplier id
         public int updateProduct(
                 int productId, String prodCode,
                 String prodName,String desc, double cost,
                 int quantity, double retailPrice, double discount, int supId){
        con=ConOracle.getCon();
         try {
        // Create a statement object
            Statement stmt =  con.createStatement();
            System.out.println("Statement Object Ready");
            
            // Prepared Statement Object 
            PreparedStatement prestmt = null;
                                   
            //to check if only user is available
            prestmt = con.prepareStatement("update product "
            + "set product_code=?,product_name=?, description=?, cost=?, "
            + "quantity=?, retail_price=?,discount=?, supplier_ID=? where product_ID=?"); 
            prestmt.setString(1, prodCode);
            prestmt.setString(2, prodName);
            prestmt.setString(3, desc);
            prestmt.setDouble(4, cost);
            prestmt.setInt(5, quantity);
            prestmt.setDouble(6, retailPrice);
            prestmt.setDouble(7, discount);
            prestmt.setDouble(8, supId);
            prestmt.setDouble(9, productId);
            prestmt.executeUpdate();
            check = 1;
    }
    
        catch (SQLException ex){
            System.err.println("There was an error with this operation: ");
            System.err.println(ex.toString());
            check = -1;
           
        
        }
        finally {
          
          return check;
                 
        }
    }
    
         
        
}

