/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import humberinventory.HumberInventory;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley Blanchard
 */
public class MainMenu {
    
    BorderPane bp;
    Menu Product, Supplier,Log;
    MenuItem viewp, addp, views, adds,log;
    MenuBar mnuMain;
    public MenuBar getMenu( Stage primaryStage){
       //Product
        Product = new Menu("Products");
        viewp=new MenuItem("View Products");
         viewp.setOnAction(e ->
        {
            InventoryView IView = new InventoryView();
            IView.start(primaryStage);
        });
        Product.getItems().add(viewp);
        //Product.getItems().add(new SeparatorMenuItem());
        addp = new MenuItem("Add Product");
        addp.setOnAction(e ->
        {
            InventoryAdd IAdd = new InventoryAdd();
            IAdd.start(primaryStage);
        });
        Product.getItems().add(addp);

        
        
        //Supplier
        Supplier = new Menu("Supplier");
        views = new MenuItem("View Suppliers");
        views.setOnAction(e ->
        {
            SuppliersView SView = new SuppliersView();
            SView.start(primaryStage);
        });
        Supplier.getItems().add(views);
        adds = new MenuItem("Add Supplier");
        adds.setOnAction(e ->
        {
            SupplierAdd SAdd = new SupplierAdd();
            SAdd.start(primaryStage);
        });
        Supplier.getItems().add(adds);

        
        
        //Logout
        Log=new Menu("Logout");
        log = new MenuItem("Logout");
        Log.getItems().add(log);
        log.setOnAction(e ->
        {
           Alert conf = new Alert(Alert.AlertType.CONFIRMATION);
            conf.setContentText("Are you sure you want to logout");
                    conf.showAndWait().ifPresent(response -> {
     if (response == ButtonType.OK) {
         try {
           HumberInventory m=new HumberInventory();
           m.start(primaryStage);
         } catch (Exception ex) {
             Logger.getLogger(InventoryHome.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
                    });
                   
        });

        MenuBar menu = new MenuBar();
        menu.getMenus().addAll(Product, Supplier,Log);
        return menu;
        
    }
    
}
