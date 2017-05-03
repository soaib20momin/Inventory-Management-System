/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import humberinventory.business.BCommunicator;
import humberinventory.business.Supplier;
import humberinventory.business.validate;
import javafx.application.Application;
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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author Johnny Lin
 */
public class SupplierUpdate extends Application{
        BorderPane bp = new BorderPane();
        GridPane gp = new GridPane();
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
        
        Button btnUpdate = null;
        Button btnClear = null;
        int check = 0;
        BCommunicator bCom;
        Supplier supplier;
        SuppliersView view;
    SupplierUpdate(){}
    SupplierUpdate(Supplier sup) {
        this.supplier = sup;
    }
       @Override
       public void start(Stage primaryStage){
        
        title = new Label("Update Supplier");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        
        lblSupName = new Label("Supplier Name: ");
        lblSupEmail = new Label("Supplier Email: ");
        lblSupAddress = new Label("Supplier Address: ");
        lblSupPhone = new Label("Supplier Phone: ");
        
        txtSupName = new TextField(this.supplier.getSupName());
        txtSupEmail = new TextField(this.supplier.getSupEmail());
        txtSupAddress = new TextField(this.supplier.getSupAddress());
        txtSupPhone = new TextField(this.supplier.getSupPhone());
        
        btnUpdate = new Button("Update");
        btnClear = new Button("Clear");
        btnUpdate.minWidth(140);
        btnClear.minWidth(10);
        gp.setAlignment(Pos.CENTER);
        gp.setHgap(10.0);
        gp.setVgap(10.0);
        gp.setPadding(new Insets(10));
        gp.add(lblSupName,0,1);
        gp.add(txtSupName,1,1);
        gp.add(lblSupEmail,0,2);
        gp.add(txtSupEmail,1,2);
        gp.add(lblSupPhone,0,3);
        gp.add(txtSupPhone,1,3);
        gp.add(lblSupAddress,0,4);
        gp.add(txtSupAddress,1,4);
        
        
        btnUpdate.setOnMouseMoved(e ->{
            //id.setTooltip(new Tooltip("Enter Product Id"));
        });
        btnUpdate.setOnAction(e ->{
            
            if(IsValidData()){
            this.bCom = new BCommunicator();

            check=bCom.updateSupplier(this.supplier.getSupID(),
            this.txtSupName.getText(),this.txtSupAddress.getText(),
           this.txtSupPhone.getText(),this.txtSupEmail.getText());
                      
               //Add Error handling       
               
            view= new SuppliersView();
            view.start(primaryStage);
            }
                });
        btnClear.setOnAction(e ->{
           
            txtSupName.setText("");
            txtSupAddress.setText("");
            txtSupEmail.setText("");
            txtSupPhone.setText("");
            
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
        btnUpdate.getStyleClass().add("addSupplier");
        btnClear.getStyleClass().add("updateSupplier");
        hbtop.getStyleClass().add("hbtop");
        gp.getStyleClass().add("gp");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        
        
        primaryStage.setTitle("Update Supplier ");
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
