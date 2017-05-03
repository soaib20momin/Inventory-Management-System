/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package humberinventory.business;

import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author Johnny Lin
 */
public class validate {
    
    
    
    public boolean IsPresent(TextField t, String name){
        if(t.getText().trim().isEmpty()){
            Alert conf = new Alert(Alert.AlertType.ERROR);
            conf.setContentText(name+" is a required field");
            conf.showAndWait();
            return false;
        }
        return true;
    }
    
    public boolean IsInteger(TextField t,String name){
        try { 
            Integer.parseInt(t.getText()); 
        }
        catch(NumberFormatException e) { 
            Alert conf = new Alert(Alert.AlertType.ERROR);
            conf.setContentText(name+" should be integer");
            conf.showAndWait();
            return false; 
        } 

        return true;
    }
    
    
    public boolean IsDouble(TextField t,String name){
        try { 
            Double.parseDouble(t.getText()); 
        }
        catch(NumberFormatException e) { 
            Alert conf = new Alert(Alert.AlertType.ERROR);
            conf.setContentText(name+" should be number");
            conf.showAndWait();
            return false; 
        } 

        return true;
    }
    
    public boolean IsWithinRange(TextField t, String name,int min, int max){
        double number = Double.parseDouble(t.getText());
        if(number < min || number > max){
            Alert conf = new Alert(Alert.AlertType.ERROR);
            conf.setContentText(name+" must be between " + min+" and "+ max);
            conf.showAndWait();
            return false;
        }
        return true;
    }
    
}
