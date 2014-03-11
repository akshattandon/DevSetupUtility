/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.scene.layout.VBoxBuilder;
import javafx.stage.Modality;
import javax.sound.midi.ControllerEventListener;

/**
 * FXML Controller class
 *
 * @author Akshat.Tandon
 */
public class MainPageController implements Initializable {

    private Stage stage;
    private FXMLConfigurationController configController;
    
    private XMLApiController xMLApiController;
    private SingleOptionController singleOption; 
     @FXML
    Button cancelButton;
     @FXML
     CheckBox setDebugOn;
     @FXML
     CheckBox setJSPDevelopmentFlag;
     @FXML
     CheckBox configureJVMParams;
     @FXML
     CheckBox explodeEar;
     @FXML
     CheckBox copyLicenceFiles;
     @FXML
     CheckBox setupjunitee;
     @FXML
     CheckBox bytemanDebug;
     @FXML
     CheckBox sqlTracing;
     @FXML
     CheckBox debugLogging;
     @FXML
     CheckBox forceSilverData;       
     @FXML
     CheckBox setKronosProperty;
    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
       private void handleNext(ActionEvent event) {
            if(validate()){
                    List<String> selectedList = getSeletctedOptions();
                    
                    if(setupjunitee.isSelected() || configureJVMParams.isSelected() || explodeEar.isSelected()){
                            configController = new FXMLConfigurationController();
                            stage = (Stage) cancelButton.getScene().getWindow();
                            configController.initilzeScreen(stage,selectedList);
                    }
                    else if (sqlTracing.isSelected() || debugLogging.isSelected() || forceSilverData.isSelected() || setKronosProperty.isSelected()){
                            xMLApiController = new XMLApiController();
                            stage = (Stage) cancelButton.getScene().getWindow();
                            xMLApiController.initilzeScreen(stage,selectedList);
               
                    }
                    else
                    {
                            singleOption = new SingleOptionController();
                            stage = (Stage) cancelButton.getScene().getWindow();
                            singleOption.initilzeScreen(stage,selectedList);
                    }
            }else{
                createErrorPopup();
             }       
      
       }
       
       public List<String> getSeletctedOptions(){
           List<String> selectList = new ArrayList<String>();
           if(setDebugOn.isSelected())
               selectList.add("setDebugOn");
           if(setJSPDevelopmentFlag.isSelected())
               selectList.add("setJSPDevelopmentFlag");
           if(configureJVMParams.isSelected())
               selectList.add("configureJVMParams");
           if(explodeEar.isSelected())
               selectList.add("explodeEar");           
           if(copyLicenceFiles.isSelected())
               selectList.add("copyLicenceFiles");
           if(setupjunitee.isSelected())
               selectList.add("setupjunitee");
           if(bytemanDebug.isSelected())
               selectList.add("bytemanDebug");
           
           if(sqlTracing.isSelected())
               selectList.add("sqlTracing");
           if(debugLogging.isSelected())
               selectList.add("debugLogging");
           if(forceSilverData.isSelected())
               selectList.add("forceSilverData");
           if(setKronosProperty.isSelected())
               selectList.add("setKronosProperty");
           
           System.out.println("main page c - seletcted list "+selectList);
           return selectList;
       }
        @FXML
       private void handleCancel(ActionEvent event) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            // do what you have to do
            stage.close();
       }
       
       public boolean validate(){
           boolean isCorrect = false; 
            if(setDebugOn.isSelected() || setJSPDevelopmentFlag.isSelected() || configureJVMParams.isSelected() || explodeEar.isSelected()
                    || copyLicenceFiles.isSelected() || setupjunitee.isSelected() || bytemanDebug.isSelected()
                    || sqlTracing.isSelected() || debugLogging.isSelected() || forceSilverData.isSelected() || setKronosProperty.isSelected())
                isCorrect = true;     
             
             return isCorrect;
       }
       
       private void createErrorPopup(){
            final Stage myDialog = new Stage();
                myDialog.initModality(Modality.WINDOW_MODAL);
                Scene myDialogScene = new Scene(VBoxBuilder.create().children(new Text("Please select any one option"))
                     .alignment(Pos.CENTER)
                     .padding(new Insets(10))
                     .build());
           
             myDialog.setScene(myDialogScene);
             myDialog.show();
       }
    
}
