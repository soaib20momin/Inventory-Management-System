/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Bradley Blanchard
 */
public class ConOracle {
    private static Connection con;
    public static Connection getCon (){
   
        // 1.Put Database statements in try to catch any error
        try {
           //2. Search for the driver for the specific database
            Class.forName("oracle.jdbc.driver.OracleDriver");
            //Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Found");
            
            //3. Create connection object 
            //and get the connection string, username and password
            con = DriverManager.getConnection
            ("jdbc:oracle:thin:@dilbert.humber.ca:1521:grok","n01187747","oracle");
            System.out.println("Connection Done");
           
            
             

    }
        catch (SQLException | ClassNotFoundException ex){
            System.err.println("There was either a connection or class not found");
            System.err.println(ex.toString());
            
        
        }
        finally {
           return con;
        }
}
}