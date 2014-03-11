/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1.controller;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafxapplication1.util.RunAnt;
import static javafxapplication1.util.RunAnt.executeAntTask;
import org.apache.tools.ant.Project;

/**
 *
 * @author Akshat.Tandon
 */
public class FXMLConfigurationController implements Initializable {
    
     @FXML
     TextField instanceNameId;
     @FXML
     TextField backupDirId;     
     @FXML
     TextField thirdPartyId;
     @FXML
     TextField wfpJarsId;
     @FXML
     TextField wtkJarsId;
     @FXML
     ProgressBar progressbar;     
     @FXML
     Label responseLabel;
    
    @FXML
    private TextField labelSelectedDirectory;
    @FXML
    Label errorLabel;
    @FXML
    Button nextButton;
    @FXML
    Button cancelButton;
    String absolutePath;
    private Stage stage;
    
    @FXML
    private void handleDirectory(ActionEvent event) {
     DirectoryChooser directoryChooser = new DirectoryChooser(); 
     //Show open file dialog
    File file = directoryChooser.showDialog(null);
    if(file!=null){
           labelSelectedDirectory.setText(file.getPath());   
        }
    else  
           errorLabel.setText("No Directory selected");
               
    
    }
    
       @FXML
       private void handleNext(ActionEvent event) {
           Node node = (Node)event.getSource();
               List<String> selectedList = (List<String>)node.getScene().getRoot().getUserData();
                progressbar.setVisible(true);     
                progressbar.setProgress(-1.0f);
                StringBuilder response = new StringBuilder(); 
                absolutePath = RunAnt.class.getProtectionDomain().getCodeSource().getLocation().getPath();
                absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
                System.out.println("absolutePath--"+absolutePath);
                
                for(int i=0;i<selectedList.size();i++)
                {
                     Project project = RunAnt.intilizeAnt(absolutePath);
                     project = setPathMap(project);
               
                    response.append("\n"+(i+1));
                    response.append(". For ");
                    response.append(selectedList.get(i));
                    response.append(" Response= ");
                    System.out.print("target--"+selectedList.get(i));
                    response.append(run(project, selectedList.get(i)));
                    
                }
                             
                responseLabel.setText(response.toString());
                responseLabel.setWrapText(true);
                progressbar.setVisible(false);
                nextButton.setVisible(false);
            
      }
       
       
       
       private String run(Project project,String target){
               
                // Running specified target of ant script
                
                String response = executeAntTask(project,absolutePath, target);
                responseLabel.setVisible(true);
                System.out.println("------"+response);
                return response;
       }
       
       private Project setPathMap(Project project){
             
             
             String kronosFolder = labelSelectedDirectory.getText();
             String instanceName = instanceNameId.getText();
             
             project.setProperty(javafxapplication1.util.Constants.STANDALONE_PROJECT_LOCATION, absolutePath);
             project.setProperty(javafxapplication1.util.Constants.DEPLOYMENT_DIR, kronosFolder+"/jboss/"+instanceName+"/deployments/");
             project.setProperty(javafxapplication1.util.Constants.LICENSE_LOCATION, kronosFolder+"/"+instanceName+"/licensing");
             project.setProperty(javafxapplication1.util.Constants.CONF_DIR,kronosFolder+"/jboss/"+instanceName+"/configuration");
             project.setProperty(javafxapplication1.util.Constants.CONF_XML_FILE,"standalone.xml");
             project.setProperty(javafxapplication1.util.Constants.JBOSS_BIN_DIR,kronosFolder+"/jboss/bin/");
             project.setProperty(javafxapplication1.util.Constants.CONF_BAT_DIR,"standalone.conf.bat");             
             project.setProperty(javafxapplication1.util.Constants.EAR_NAME,instanceName+".ear");
             project.setProperty(javafxapplication1.util.Constants.WAR_NAME,instanceName+".war");
             project.setProperty(javafxapplication1.util.Constants.DLL_DIR,kronosFolder+"/jdk/jre/bin/server");
             project.setProperty(javafxapplication1.util.Constants.WFC_BIN_DIR,kronosFolder+"/wfc/bin/");
             project.setProperty(javafxapplication1.util.Constants.START_BAT,"start.bat");
             project.setProperty(javafxapplication1.util.Constants.BACKUP_LOCATION,backupDirId.getText());
             project.setProperty(javafxapplication1.util.Constants.WFP_TEST_JARS,wfpJarsId.getText());
             project.setProperty(javafxapplication1.util.Constants.WTK_TEST_JARS,wtkJarsId.getText());
             project.setProperty(javafxapplication1.util.Constants.THIRD_PARTY,thirdPartyId.getText());
             return project;
       }
       
       
        @FXML
       private void handleCancel(ActionEvent event) {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
    // do what you have to do
    stage.close();
       }
 

    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
    }
    
    
    public boolean validate(String textBoxVal){
        boolean isCorrect = false;
        return isCorrect;
    }
    
    
   
    
    public void initilzeScreen(Stage stage,List<String> selectedList){        
          Parent root;
        try {   
            root = FXMLLoader.load(getClass().getResource("/javafxapplication1/fxml/Configuration.fxml"));
            root.setUserData(selectedList);
          
            Scene scene = new Scene(root);
          stage.setScene(scene);
          stage.show();
          } catch (IOException ex) {
            Logger.getLogger(FXMLConfigurationController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
       
    }
    
       
    
}
