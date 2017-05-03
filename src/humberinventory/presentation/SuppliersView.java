/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import humberinventory.business.*;
import humberinventory.business.BCommunicator;
import humberinventory.business.Product;
import humberinventory.business.Supplier;
import java.util.ArrayList;
import java.util.function.Predicate;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

/**
 *
 * @author osheen
 */
public class SuppliersView extends Application {
    GridPane gp = new GridPane();
    TableView table = new TableView();
    VBox vb = new VBox();
    HBox hbbottom = new HBox();
    HBox hbtop = new HBox();
    VBox vbright = new VBox();
    
    ArrayList supList ;
    FilteredList<Supplier> filteredsupplier;
    ObservableList<Supplier> suppliers;
    BCommunicator bCom;
    BorderPane bp = new BorderPane();
    
    Button addSupplier = null;
    Button updateSupplier = null;
    Button deleteSupplier = null;
    Label title = null;
    TextField search = null;
    
    TableColumn<Supplier, Integer> supID = null;
    TableColumn<Supplier, String> supName = null;
    TableColumn<Supplier, String>  supAddress = null;
    TableColumn<Supplier, String> supPhone = null;
    TableColumn<Supplier, String> supEmail = null;
    
    

    @Override
    public void start(Stage primaryStage)  {
        
        addSupplier = new Button("Add Supplier");
        updateSupplier = new Button("Update Supplier");
        deleteSupplier = new Button("Delete Supplier");
        title = new Label("Suppliers List");
        
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill:#444");
        
        //Table header columns
        supID = new TableColumn("Supplier ID");
        supID.setMinWidth(100);
        supID.setCellValueFactory(new PropertyValueFactory<>("supID"));
        
        supName = new TableColumn("Supplier Name");
        supName.setMinWidth(194);
        supName.setCellValueFactory(new PropertyValueFactory<>("supName"));
        
        supAddress = new TableColumn("Supplier Address");
        supAddress.setMinWidth(176);
        supAddress.setCellValueFactory(new PropertyValueFactory<>("supAddress"));
        
        supPhone = new TableColumn("Supplier Phone");
        supPhone.setMinWidth(152);
        supPhone.setCellValueFactory(new PropertyValueFactory<>("supPhone"));
        
        supEmail = new TableColumn("Supplier Email");
        supEmail.setMinWidth(152);
        supEmail.setCellValueFactory(new PropertyValueFactory<>("supEmail"));
            
        //top or header
        hbtop.getChildren().add(title);
        hbtop.setSpacing(10);
        hbtop.setPadding(new Insets(15, 12, 15, 12));
        //hbtop.setStyle("-fx-background-color: #336699;");
        
        //search textfield
        search = new TextField();
        search.setPromptText("Enter Supplier Name or Address");
        
        //rightside
        vbright.getChildren().addAll(search,addSupplier,updateSupplier,deleteSupplier);
        vbright.setSpacing(10);
        vbright.setPadding(new Insets(15, 12, 15, 12));
        vbright.setStyle("-fx-background-color: #0277BD;");

        bCom = new BCommunicator();
        supList=bCom.getSupList();
        suppliers = FXCollections.observableArrayList(supList);    
        table.getColumns().addAll(supID,supName,supAddress,supPhone,supEmail);
        table.setItems(suppliers);
        vb.getChildren().addAll(table);
        
        
        filteredsupplier = new FilteredList<>(suppliers, e->true);
        
        search.setOnMouseReleased(e -> {
            search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredsupplier.setPredicate((Predicate<? super Supplier>) supplier -> {

                    if(newValue == null || newValue.isEmpty())
                    {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(supplier.getSupName().contains(newValue))
                    {
                        return true;
//                    }else if (supplier.getSupEmail().toLowerCase().contains(lowerCaseFilter)) {
//                        return true; // Filter matches email.
                    }else if (supplier.getSupAddress().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches address.
                    }

                    return false;
                });
            });
            SortedList<Supplier> sortedData = new SortedList<>(filteredsupplier);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            table.setItems(sortedData);
        });
        
        
//        //delete product
        deleteSupplier.setOnMouseClicked(e ->
        {
            //Get selected product 
            Supplier sup = (Supplier) table.getSelectionModel().getSelectedItem();
            
            //Then Try to delete from database 
            bCom = new BCommunicator();
            bCom.deleteSupplier(sup.getSupID());
            suppliers.remove(sup);
           
        });
//        // Add Button
        addSupplier.setOnMouseClicked(e ->
        {
            SupplierAdd supAdd = new SupplierAdd();
            supAdd.start(primaryStage);
        });
        
//        // Update Button
        
        updateSupplier.setOnMouseClicked(e ->
        {
             //Get selected product 
            Supplier sup = (Supplier) table.getSelectionModel().getSelectedItem();
            
           SupplierUpdate supUpdate = new SupplierUpdate(sup);
           supUpdate.start(primaryStage);
        });
        // Creating new Menu Bar
        MainMenu mMenu = new MainMenu();
        MenuBar mBar = mMenu.getMenu(primaryStage);
        // Creating vBox and adding top title and menuBar to it
        VBox topVBox = new VBox();
        topVBox.getChildren().add(hbtop);
        topVBox.getChildren().add(mBar);
       
        // Add to Border Pain
        bp.setTop(topVBox);
        
        bp.setCenter(vb);
        bp.setRight(vbright);
        bp.setPadding(new Insets(5));
        
        Scene scene = new Scene(bp, 1000, 550);
        
        //CSS
        bp.getStyleClass().add("bp");
        vb.getStyleClass().add("vb");
        addSupplier.getStyleClass().add("addSupplier");
        updateSupplier.getStyleClass().add("updateSupplier");
        deleteSupplier.getStyleClass().add("deleteSupplier");
     
        table.getStyleClass().add("table");
        hbtop.getStyleClass().add("hbtop");
        gp.getStyleClass().add("gPane");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        //CSS End
        
        primaryStage.setTitle("Supplier List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
    
}