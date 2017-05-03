/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory;
import humberinventory.presentation.*;
import humberinventory.business.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author Bradley Blanchard
 */
public class HumberInventory extends Application {
    GridPane gPane = new GridPane();
    StackPane sp = new StackPane();
    VBox vbtitle = new VBox();
    
    HBox hb = new HBox();
    Label lblUser = null;
    Label lblPass = null;
    TextField txtUid = null;
    PasswordField pwPass = null;
    Button btnLogin = null;
    Button btnReset = null;
    int check=0;
    Stage stage;
    Scene scene;
    
    
    @Override
    public void start(Stage primaryStage) {
        lblUser = new Label("Username");
        lblPass = new Label("Password");
        pwPass = new PasswordField();
        txtUid = new TextField();
        btnLogin = new Button("Login");
        btnReset = new Button("Reset");
        
        gPane.add(lblUser,0,0);
        gPane.add(txtUid,1,0);
        gPane.add(lblPass,0,1);
        gPane.add(pwPass,1,1);
        gPane.setHgap(20.0);
        gPane.setVgap(20.0);
        gPane.setPadding(new Insets(10));
        
        
        hb.getChildren().addAll(btnLogin,btnReset);
        hb.setPadding(new Insets(10));
        hb.setSpacing(25);
        gPane.add(hb,1,2);
        
        
        txtUid.setPromptText("Enter Username");
        pwPass.setPromptText("Enter Password");
        
        btnLogin.setOnMouseReleased (e ->
        {
           BCommunicator bCom= new BCommunicator ();
           check=bCom.checkUser(txtUid.getText(),pwPass.getText());
            switch (check) {
                case 1:
                    {
//                       
                        InventoryView inView = new InventoryView();
                        inView.start(primaryStage);
                        break;
                        
                    }
                case 0:
                    {
                        Alert conf = new Alert(Alert.AlertType.ERROR);
                        conf.setContentText("Not an Authorized User");
                        conf.showAndWait();
                        btnLogin.setText("Log In");
                        txtUid.setText("");
                        pwPass.setText("");
                        break;
                    }
                case -1:
                    {
                        Alert conf = new Alert(Alert.AlertType.ERROR);
                        conf.setContentText("Error Connecting to database");
                        conf.showAndWait();
                        btnLogin.setText("Log In");
                        txtUid.setText("");
                        pwPass.setText("");
                        break;
                    }
                default:
                    break;
            }
        });
       
        sp.getChildren().add(gPane);
        scene = new Scene(sp, 1000, 550);
        
        //CSS
        gPane.getStyleClass().add("gPane");
        txtUid.getStyleClass().add("txt");
        pwPass.getStyleClass().add("txt");
        btnLogin.getStyleClass().add("btnLogin");
        btnReset.getStyleClass().add("btnReset");
        lblUser.getStyleClass().add("label");
        lblPass.getStyleClass().add("label");
        scene.getStylesheets().clear();
        String cssURL = this.getClass().getResource("humberinventory.css").toExternalForm();
        scene.getStylesheets().add(cssURL);
        //CSS End
        
        primaryStage.setTitle("Inventory Login");
        primaryStage.setScene(scene);
        primaryStage.show();
       
        
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
