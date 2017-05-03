package humberinventory.presentation;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */



import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 *
 * @author Bradley Blanchard
 */
public class InventoryMain extends Application {
    
    GridPane mainGrid  = new GridPane();
       Button btnMainView = new Button("View Products");
       Button btnMainUpdate  = new Button ("Update Products");
       Button btnMainAdd = new Button("Add Product");
       Button btnMainDelete = new Button("Add Product");
       Label  lblMainViewDesc = new Label("This section enables user to View Inventory List");
       Label  lblMainUpdateDesc = new Label("This section enables user to Update details of individual Product");
       Label  lblMainAddDesc = new Label("This section enables user to add a product to Inventory List");
       Label  lblMainDeleteDesc = new Label("This section enables user to Delete a specific product from Inventory List");
       
    
    
    @Override
    public void start(Stage primaryStage) {
       // organizing padding
        mainGrid.setHgap(20.0);
        mainGrid.setVgap(10.0);
        mainGrid.setPadding(new Insets(10));
        mainGrid.setAlignment (Pos.CENTER);
        
        // Adjust Sizes for labels and buttons 
        
        btnMainView.setMinWidth(200);
        btnMainUpdate.setMinWidth(200);
        btnMainAdd.setMinWidth(200);
        btnMainDelete.setMinWidth(200);
        lblMainViewDesc.setMinWidth(600);
        lblMainUpdateDesc.setMinWidth(600);
        lblMainAddDesc.setMinWidth(600);
        lblMainDeleteDesc.setMinWidth(600);
        
       // adding controls to grid 
       mainGrid.add(lblMainViewDesc, 0, 0);
       mainGrid.add(btnMainView, 1, 0);
       mainGrid.add(lblMainUpdateDesc, 0, 1);
       mainGrid.add(btnMainUpdate, 1, 1);
       mainGrid.add(lblMainAddDesc, 0, 2);
       mainGrid.add(btnMainAdd, 1, 2);
       mainGrid.add(lblMainDeleteDesc, 0, 3);
       mainGrid.add(btnMainDelete, 1, 3);
       
      // Adding Event Handlers
      
      btnMainView.setOnAction(e->
      {
           InventoryView viewForm = new InventoryView();
          
           viewForm.start(primaryStage);
          
      }
      );
        
        Scene scene = new Scene(mainGrid);
        primaryStage.setTitle("Login Form");
        primaryStage.setScene(scene);
        primaryStage.show(); 
    }

    
    
}
