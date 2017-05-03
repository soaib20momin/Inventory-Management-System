/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import humberinventory.business.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Bradley Blanchard
 */
public class ViewSupplier extends Application {
        BorderPane bp = new BorderPane();
        GridPane gpSupView = new GridPane();
        HBox hbtop = new HBox();
        VBox vbright = new VBox();
    
        Label title = null;
        
        Label lblSupName = null;
        Label lblSupEmail = null;
        Label lblSupAddress = null;
        Label lblSupPhone = null;
       
        TextField txtSupName = null;
        TextField txtSupEmail = null;
        TextField txtSupAddress = null;
        TextField txtSupPhone=null;
        
        Button btnProducts = null;
        Button btnSuppliers = null;
        int check = 0;
        int supId;
        BCommunicator bCom;
        Supplier supplier;
        SuppliersView supView;
        InventoryView inView;
        Product product;
           
    ViewSupplier(){}
    ViewSupplier(Supplier sup) {
        this.supplier = sup;
    }
    ViewSupplier(Product prod) {
        this.product = prod;
        this.supId=this.product.getSupplierId();
    }
       @Override
       public void start(Stage primaryStage){
        bCom = new BCommunicator();
        this.supplier=bCom.getSupplier(this.supId);
        title = new Label("View Supplier");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        lblSupName = new Label("Supplier Name: ");
        lblSupEmail = new Label("Supplier Email: ");
        lblSupAddress = new Label("Supplier Address: ");
        lblSupPhone = new Label("Supplier Phone: ");
        
        txtSupName = new TextField(this.supplier.getSupName());
        txtSupEmail = new TextField(this.supplier.getSupEmail());
        txtSupAddress = new TextField(this.supplier.getSupAddress());
        txtSupPhone = new TextField(this.supplier.getSupPhone());
        txtSupName.setDisable(true);
        txtSupEmail.setDisable(true);
        txtSupAddress.setDisable(true);
        txtSupPhone.setDisable(true);
        txtSupName.setMinWidth(260);
        txtSupEmail.setMinWidth(260);
        txtSupAddress.setMinWidth(260);
        txtSupPhone.setMinWidth(260);
        txtSupName.setStyle("-fx-text-inner-color: black;-fx-font-weight: bold;");
        txtSupAddress.setStyle("-fx-text-inner-color: black;-fx-font-weight: bold;");
        txtSupPhone.setStyle("-fx-text-inner-color: black;-fx-font-weight: bold;");
        txtSupEmail.setStyle("-fx-text-inner-color: black;-fx-font-weight: bold;");
        
        btnProducts = new Button("Return to Products");
        btnSuppliers = new Button("Go to Suppliers");
        btnProducts.minWidth(140);
        btnSuppliers.minWidth(10);
        gpSupView.setAlignment(Pos.CENTER);
        gpSupView.setHgap(10.0);
        gpSupView.setVgap(10.0);
        gpSupView.setPadding(new Insets(10));
        gpSupView.add(lblSupName,0,1);
        gpSupView.add(txtSupName,1,1);
        gpSupView.add(lblSupEmail,0,2);
        gpSupView.add(txtSupEmail,1,2);
        gpSupView.add(lblSupPhone,0,3);
        gpSupView.add(txtSupPhone,1,3);
        gpSupView.add(lblSupAddress,0,4);
        gpSupView.add(txtSupAddress,1,4);
        
        
        btnProducts.setOnAction(e ->{
                     
            inView= new InventoryView();
            inView.start(primaryStage);
            
                });
        btnSuppliers.setOnAction(e ->{
               
            supView= new SuppliersView();
            supView.start(primaryStage);
            
        });
        
         //top or header
        hbtop.getChildren().add(title);
        hbtop.setSpacing(10);
        hbtop.setPadding(new Insets(15, 12, 15, 12));
        
        //rightside
        vbright.getChildren().addAll(btnProducts,btnSuppliers);
        vbright.setSpacing(10);
        vbright.setPadding(new Insets(15, 12, 15, 12));
        vbright.setStyle("-fx-background-color: #0277BD;");
        
         // Creating new Menu Bar
        MainMenu mMenu = new MainMenu();
        MenuBar mBar = mMenu.getMenu(primaryStage);
        // Creating vBox and adding top title and menuBar to it
        VBox topVBox = new VBox();
        topVBox.getChildren().add(hbtop);
        topVBox.getChildren().add(mBar);
       
        // Add to Border Pain
        bp.setTop(topVBox);
        
        
        bp.setRight(vbright);
        bp.setLeft(gpSupView);
        //bp.setBottom(hbbottom);
        bp.setPadding(new Insets(5));
        
        Scene scene = new Scene(bp, 1000, 550);
        
        //css
        btnProducts.getStyleClass().add("addProduct");
        btnSuppliers.getStyleClass().add("ViewSuppliers");
        hbtop.getStyleClass().add("hbtop");
        gpSupView.getStyleClass().add("gp");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        
        
        primaryStage.setTitle("View Product Supplier ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
       
       public boolean IsValidData(){
            validate v = new validate();
            return 
                    v.IsPresent(txtSupName, "Supplier Name")&&
                    v.IsPresent(txtSupAddress, "Supplier Address")&&
                    v.IsPresent(txtSupEmail, "Supplier Email")&&
                    v.IsPresent(txtSupPhone, "Supplier Phone");
        }
}
