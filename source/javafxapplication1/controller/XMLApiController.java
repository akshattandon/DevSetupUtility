/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1.controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Akshat.Tandon
 */
public class XMLApiController {
     public void initilzeScreen(Stage stage,List<String> selectedList){        
          Parent root;
        try {   
            root = FXMLLoader.load(getClass().getResource("/javafxapplication1/fxml/XMLApi.fxml"));
            root.setUserData(selectedList);
          
            Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
          } catch (IOException ex) {
            Logger.getLogger(XMLApiController.class.getName()).log(Level.SEVERE, null, ex);
        }
     } 
         @FXML
         private void handleNext(ActionEvent event) {
         }
         
       @FXML
       private void handleCancel(ActionEvent event) {
            // do what you have to do
          
       }
    
    
   
}
