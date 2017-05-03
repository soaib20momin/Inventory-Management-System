/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import humberinventory.business.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
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
 * @author Humber College Group Project 
 * @author Members : Bradley Blanchard , Johnny Lin, Soaib Momin, Sukhpreet Kaur
 */

public class InventoryAdd extends Application {
        BorderPane bp = new BorderPane();
        HBox hbtop = new HBox();
        GridPane gp = new GridPane();
        StackPane sp = new StackPane();
        VBox vbright = new VBox();
        Supplier supplier=null;
        Product product=null;
        Label lblProdCode = null;
        Label lblProdName = null;
        Label lblProdDesc = null;
        Label lblQuantity = null;
        Label lblPrice = null;
        Label lblCost;
        Label lblDiscount = null;
        Label title = null;
        TextField txtProdCode = null;
        TextField txtProdName = null;
        TextField txtProdDesc = null;
        TextField txtQuantity = null;
        TextField txtCost=null;
        TextField txtPrice = null;
        TextField txtDiscount = null;
        Button btnAdd = null;
        Button btnAddNewSup=null;
        Button btnAddExistSup=null; 
        Button btnClear = null;
        int check = 0;
        BCommunicator bCom;
        
        public InventoryAdd(){
            
        }
        public InventoryAdd(Supplier sup){
            this.supplier=sup;
        }
        public InventoryAdd(Product prod, Supplier sup){
            this.product=prod;
            this.supplier=sup;
        }
         public void populateProduct(){
            this.product = new Product();
            this.product.setCost(Double.parseDouble(this.txtCost.getText()));
            this.product.setDiscount(Double.parseDouble(this.txtDiscount.getText()));
            this.product.setProductCode(this.txtProdCode.getText());
            this.product.setProductDesc(this.txtProdDesc.getText());
            this.product.setProductName( this.txtProdName.getText());
            this.product.setQuantity(Integer.parseInt(this.txtQuantity.getText()));
            this.product.setRetailPrice(Double.parseDouble(this.txtPrice.getText()));
            
        }
       @Override
       public void start(Stage primaryStage){
        title = new Label("Add Product");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill:#444");
        lblProdCode = new Label("Product Code: ");
        lblProdName = new Label("Product Name: ");
        lblProdDesc = new Label("Product Description: ");
        lblQuantity = new Label("Quantity: ");
        lblCost = new Label("Cost: ");
        lblPrice = new Label("Price: ");
        lblDiscount = new Label("Discount: ");
        
        if (this.product!=null){
           
        txtProdCode = new TextField(this.product.getProductCode());
        txtProdName = new TextField(this.product.getProductName());
        txtProdDesc = new TextField(this.product.getProductDesc());
        txtQuantity = new TextField(Integer.toString(this.product.getQuantity()));
        txtCost = new TextField(Double.toString(this.product.getCost()));
        txtPrice = new TextField(Double.toString(this.product.getRetailPrice()));
        txtDiscount = new TextField(Double.toString(this.product.getDiscount()));
        
        }
        else {
        txtProdCode = new TextField();
        txtProdName = new TextField();
        txtProdDesc = new TextField();
        txtQuantity = new TextField();
        txtCost = new TextField();
        txtPrice = new TextField();
        txtDiscount = new TextField();
        }
        btnAdd = new Button("Add");
        btnClear = new Button("Clear");
        btnAddExistSup = new Button("Attach Existing Supplier");
        btnAddNewSup = new Button("Attach New Supplier");
        
        btnAdd.minWidth(140);
        btnClear.minWidth(10);
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10.0);
        gp.setVgap(10.0);
        gp.setPadding(new Insets(10));
        gp.add(lblProdCode,0,1);
        gp.add(txtProdCode,1,1);
        gp.add(lblProdName,0,2);
        gp.add(txtProdName,1,2);
        gp.add(lblProdDesc,0,3);
        gp.add(txtProdDesc,1,3);
        gp.add(lblQuantity,0,4);
        gp.add(txtQuantity,1,4);
        gp.add(lblCost,0,5);
        gp.add(txtCost,1,5);
        
        gp.add(lblPrice,0,6);
        gp.add(txtPrice,1,6);
        gp.add(lblDiscount,0,7);
        gp.add(txtDiscount,1,7);
        
        btnAdd.setOnMouseMoved(e ->{
            //id.setTooltip(new Tooltip("Enter Product Id"));
        });
        btnAdd.setOnAction(e ->{
            if(IsValidData()){
            this.bCom = new BCommunicator();
            this.supplier=bCom.getSupplier(this.supplier.getSupName());
            if (this.supplier.getSupID()>0){
             check=bCom.addProduct(this.txtProdCode.getText(),
            this.txtProdName.getText(),this.txtProdDesc.getText(),
            Double.parseDouble(this.txtCost.getText()),
            Integer.parseInt(this.txtQuantity.getText()),
            Double.parseDouble(this.txtPrice.getText()),
            Double.parseDouble(this.txtDiscount.getText()),this.supplier.getSupID());   
             InventoryView inView= new InventoryView();
            inView.start(primaryStage);
            }
            else {
            check=bCom.addProduct(this.txtProdCode.getText(),
            this.txtProdName.getText(),this.txtProdDesc.getText(),
            Double.parseDouble(this.txtCost.getText()),
            Integer.parseInt(this.txtQuantity.getText()),
            Double.parseDouble(this.txtPrice.getText()),
            Double.parseDouble(this.txtDiscount.getText()));
            InventoryView inView= new InventoryView();
            inView.start(primaryStage);
            }
            }
                });
        
        btnAddExistSup.setOnAction(e ->{
            if(IsValidData()){
            populateProduct();
            InventoryExistingSupplier existSup=
            new InventoryExistingSupplier (this.product);
            existSup.start(primaryStage);
            }
                });
        btnAddNewSup.setOnAction(e ->{
            if(IsValidData()){
            populateProduct();
            InventoryNewSupplier newSup= new InventoryNewSupplier (this.product);
            newSup.start(primaryStage);
            }
                });
        btnClear.setOnAction(e ->{
            txtProdCode.setText("");
            txtProdName.setText("");
            txtProdDesc.setText("");
            txtQuantity.setText("");
            txtCost.setText("");
            txtPrice.setText("");
            txtDiscount.setText("");
        });
        
       
        
        //top or header
        hbtop.getChildren().add(title);
        hbtop.setSpacing(10);
        hbtop.setPadding(new Insets(15, 12, 15, 12));
        
        //rightside
        if (this.supplier!=null){
         vbright.getChildren().addAll(btnAdd,btnClear);
           
        }
        else {
          vbright.getChildren().addAll(btnAddNewSup,btnAddExistSup,btnClear);
          
        }
        
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
        bp.setLeft(gp);
        //bp.setBottom(hbbottom);
        bp.setPadding(new Insets(5));
        
        Scene scene = new Scene(bp, 1000, 550);
        
        //css
        btnAdd.getStyleClass().add("addProduct");
        btnClear.getStyleClass().add("updateProduct");
        btnAddExistSup.getStyleClass().add("addSupplier");
        btnAddNewSup.getStyleClass().add("addSupplier");
        hbtop.getStyleClass().add("hbtop");
        gp.getStyleClass().add("gp");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        
        primaryStage.setTitle("Add Product ");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
       
       public boolean IsValidData(){
            validate v = new validate();
            return 
                    v.IsPresent(txtProdCode, "Product Code")&&
                    v.IsPresent(txtProdName, "Product Name")&&
                    v.IsPresent(txtProdDesc, "Product Description")&&
                    v.IsPresent(txtQuantity, "Product Quantity")&&
                    v.IsInteger(txtQuantity, "Product Quantity")&&
                    v.IsPresent(txtCost, "Product Cost")&&
                    v.IsDouble(txtCost, "Product Cost")&&
                    v.IsPresent(txtPrice, "Product Price")&&
                    v.IsDouble(txtPrice, "Product Price")&&
                    v.IsPresent(txtDiscount, "Product Discount")&&                  
                    v.IsDouble(txtDiscount, "Product Discount")&&
                    v.IsWithinRange(txtDiscount, "Product Discount", 0, 100);
        }
       
       public static void main(String[] args) {
        launch(args);
    }
}
