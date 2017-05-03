/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.presentation;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 *
 * @author Bradley Blanchard
 */
public class TestForm   {
    Button btnTest;
    Pane paneTest;
    Scene testScene;
    // Set up everything in constructor
    
    public TestForm () {
      this.initializeForm();
    }
    // Then have a method which creates scene then 
    public Scene getTestForm(){
     return testScene;
    }
    private void initializeForm(){
      btnTest = new Button("Test");
      paneTest = new Pane ();  
      paneTest.getChildren().add(btnTest); 
      testScene = new Scene(paneTest);  
    }
    
    
    
    
    
}
