/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;
import humberinventory.business.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javafx.geometry.Insets;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
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
 * @author Soaib
 */
public class InventoryView extends Application {
    
    GridPane gp = new GridPane();
    BorderPane bp = new BorderPane();
    HBox hbbottom = new HBox();
    HBox hbtop = new HBox();
    VBox vb = new VBox();
    VBox vbright = new VBox();
    
    TableView table = new TableView();
    ArrayList prodList ;
    FilteredList<Product> filteredproduct;
    ObservableList<Product> products;
    BCommunicator bCom;
    
    
    Button addProduct = null;
    Button updateProduct = null;
    Button deleteProduct = null;
    Button AddSupplier = null;
    Button ViewSuppliers = null;
    Button ViewSupplier = null;
    Label title = null;
    TextField search = null;
    
    TableColumn<Product, Integer> productId = null;
    TableColumn<Product, String> productCode = null;
    TableColumn<Product, String>  productName = null;
    TableColumn<Product, Double> productPrice = null;
    TableColumn<Product, Integer> productQuantity = null;
    
    

    @Override
    public void start(Stage primaryStage)  {
        
        addProduct = new Button("Add Product");
        updateProduct = new Button("Update Product");
        deleteProduct = new Button("Delete Product");
        AddSupplier = new Button("Add Supplier");
       // ViewSuppliers = new Button("View Suppliers");
       ViewSupplier = new Button("View Product Supplier");
        title = new Label("Product List");
        
        title.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        title.setStyle("-fx-text-fill:#444");
        
        //Table header columns
        productId = new TableColumn("Product Id");
        productId.setMinWidth(102);
        productId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        
        productCode = new TableColumn("Product Code");
        productCode.setMinWidth(160);
        productCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
        
        productName = new TableColumn("Product Name");
        productName.setMinWidth(192);
        productName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        
        productPrice = new TableColumn("Product Price");
        productPrice.setMinWidth(160);
        productPrice.setCellValueFactory(new PropertyValueFactory<>("retailPrice"));
        
        productQuantity = new TableColumn("Product Quantity");
        productQuantity.setMinWidth(160);
        productQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
            
        //top or header
        hbtop.getChildren().add(title);
        hbtop.setSpacing(10);
        hbtop.setPadding(new Insets(15, 12, 15, 12));
        //hbtop.setStyle("-fx-background-color: #336699;");
        
        //search textfield
        search = new TextField();
        search.setPromptText("Enter Product Code or Name");
        search.setFocusTraversable(false);
        //rightside
        vbright.getChildren().addAll(search,addProduct,updateProduct,deleteProduct,ViewSupplier);
        vbright.setSpacing(10);
        vbright.setPadding(new Insets(15, 12, 15, 12));
        vbright.setStyle("-fx-background-color: #0277BD;");
        
        
        bCom = new BCommunicator();
        prodList=bCom.getProductList();
        products = FXCollections.observableArrayList(prodList);    
        table.getColumns().addAll(productId,productCode,productName,productPrice,productQuantity);
        table.setItems(products);
        vb.getChildren().addAll(table);
        
        
        
        filteredproduct = new FilteredList<>(products, e->true);
        
        search.setOnMouseReleased(e -> {
            search.textProperty().addListener((observableValue, oldValue, newValue) -> {
                filteredproduct.setPredicate((Predicate<? super Product>) product -> {

                    if(newValue == null || newValue.isEmpty())
                    {
                        return true;
                    }

                    String lowerCaseFilter = newValue.toLowerCase();

                    if(product.getProductCode().contains(newValue))
                    {
                        return true;
                    }else if (product.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                        return true; // Filter matches first name.
                    } 

                    return false;
                });
            });
            SortedList<Product> sortedData = new SortedList<>(filteredproduct);
            // 4. Bind the SortedList comparator to the TableView comparator.
            sortedData.comparatorProperty().bind(table.comparatorProperty());
            // 5. Add sorted (and filtered) data to the table.
            table.setItems(sortedData);
        });
        
        //delete product
        deleteProduct.setOnMouseClicked(e ->
        {
            //Get selected product 
            Product prod = (Product) table.getSelectionModel().getSelectedItem();
            
            //Then Try to delete from database 
            bCom = new BCommunicator();
            bCom.deleteProduct(prod.getProductId());
            products.remove(prod);
           
        });
        // Add  Supplier Button
//        ViewSuppliers.setOnMouseClicked(e ->
//        {
//            
//            //SuppliersView supView = new SuppliersView();
//            //supView.start(primaryStage);
//        });
        ViewSupplier.setOnMouseClicked(e ->
        {
            //Get selected product 
            Product prod = (Product) table.getSelectionModel().getSelectedItem();
            
            ViewSupplier viewSup = new ViewSupplier(prod);
            viewSup.start(primaryStage);
        });
        addProduct.setOnMouseClicked(e ->
        {
            InventoryAdd inAdd = new InventoryAdd();
            inAdd.start(primaryStage);
        });
        
        // Update Button
        
        updateProduct.setOnMouseClicked(e ->
        {
             //Get selected product 
            Product prod = (Product) table.getSelectionModel().getSelectedItem();
            
            InventoryUpdate inUpdate = new InventoryUpdate(prod);
            inUpdate.start(primaryStage);
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
        
        bp.setRight(vbright);
        bp.setCenter(vb);
        //bp.setBottom(hbbottom);
        bp.setPadding(new Insets(5));
        
        Scene scene = new Scene(bp, 1000, 550);
        
        //CSS
        bp.getStyleClass().add("bp");
        vb.getStyleClass().add("vb");
        addProduct.getStyleClass().add("addProduct");
        updateProduct.getStyleClass().add("updateProduct");
        deleteProduct.getStyleClass().add("deleteProduct");
        ViewSupplier.getStyleClass().add("ViewSuppliers");
        table.getStyleClass().add("table");
        hbtop.getStyleClass().add("hbtop");
        gp.getStyleClass().add("gPane");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("presentation.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        //CSS End
        
        primaryStage.setTitle("Product List");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
   public static void main(String[] args) {
        launch(args);
    }
    
}
