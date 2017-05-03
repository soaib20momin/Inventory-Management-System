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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Junyu
 */
public class InventoryUpdate extends Application{
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
        HBox hbtop = new HBox();
        VBox vbright = new VBox();
    
        Label title = null;
        Label lblProdCode = null;
        Label lblProdName = null;
        Label lblProdDesc = null;
        Label lblQuantity = null;
        Label lblPrice = null;
        Label lblCost;
        Label lblDiscount = null;
        TextField txtProdCode = null;
        TextField txtProdName = null;
        TextField txtProdDesc = null;
        TextField txtQuantity = null;
        TextField txtCost=null;
        TextField txtPrice = null;
        TextField txtDiscount = null;
        Button btnUpdate = null;
        Button btnClear = null;
        int check = 0;
        BCommunicator bCom;
        Product product;
    InventoryUpdate(){
        
    }
    InventoryUpdate(Product prod){
        this.product=prod;
    }
        @Override
    public void start(Stage primaryStage){
        title = new Label("Update Supplier");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        lblProdCode = new Label("Product Code: ");
        lblProdName = new Label("Product Name: ");
        lblProdDesc = new Label("Product Description: ");
        lblQuantity = new Label("Quantity: ");
        lblCost = new Label("Cost: ");
        lblPrice = new Label("Price: ");
        lblDiscount = new Label("Discount: ");
        txtProdCode = new TextField(this.product.getProductCode());
        txtProdName = new TextField(this.product.getProductName());
        txtProdDesc = new TextField(this.product.getProductDesc());
        txtQuantity = new TextField(Integer.toString(this.product.getQuantity()));
        txtCost = new TextField(Double.toString(this.product.getCost()));
        txtPrice = new TextField(Double.toString(this.product.getRetailPrice()));
        txtDiscount = new TextField(Double.toString(this.product.getDiscount()));
        btnUpdate = new Button("Update");
        btnClear = new Button("Clear");
        btnUpdate.minWidth(140);
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
        gp.add(btnUpdate,0,8);
        gp.add(btnClear,1,8);
        
        btnUpdate.setOnAction(e ->{
            if(IsValidData()){
            this.bCom = new BCommunicator();
            check=bCom.updateProduct(this.product.getProductId(),
            this.txtProdCode.getText(),
            this.txtProdName.getText(),this.txtProdDesc.getText(),
            Double.parseDouble(this.txtCost.getText()),
            Integer.parseInt(this.txtQuantity.getText()),
            Double.parseDouble(this.txtPrice.getText()),
            Double.parseDouble(this.txtDiscount.getText()));
            
            //Redirect to View Form
            InventoryView inView= new InventoryView();
            inView.start(primaryStage);
            }  
        });
        btnUpdate.setOnMouseMoved(e ->{
            
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
        vbright.getChildren().addAll(btnUpdate,btnClear);
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
        btnUpdate.getStyleClass().add("updateProduct");
        btnClear.getStyleClass().add("updateProduct");
        hbtop.getStyleClass().add("hbtop");
        gp.getStyleClass().add("gp");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        
        
        
        
        primaryStage.setTitle("Update Product ");
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
}