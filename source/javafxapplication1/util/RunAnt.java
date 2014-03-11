/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package javafxapplication1.util;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.DefaultLogger;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.ProjectHelper;
/**
 *
 * @author Akshat.Tandon
 */
public class RunAnt {
    private static final String ANTFILE_NAME = "buildFile.xml";
     
    /**
     * To execute a target specified in the Ant build.xml file
     * 
     * @param buildXmlFileFullPath
     * @param target
     */
    public static String executeAntTask(Project project, String antBuildPath , String target) {
        String response = "";
       
        // Capture event for Ant script build start / stop / failure
        try {
            File buildFile = new File(antBuildPath+"/ant/"+ANTFILE_NAME);
            project.fireBuildStarted();
            project.init();
            ProjectHelper projectHelper = ProjectHelper.getProjectHelper();
            project.addReference("ant.projectHelper", projectHelper);
            projectHelper.parse(project, buildFile);
             
            // If no target specified then default target will be executed.
            String targetToExecute = (target != null && target.trim().length() > 0) ? target.trim() : project.getDefaultTarget();
            project.executeTarget(targetToExecute);
            project.fireBuildFinished(null);
            response = "sucess";
        } catch (BuildException buildException) {

            response = buildException.getMessage();
        }
         
        return response;
    }
     
    /**
     * Logger to log output generated while executing ant script in console
     * 
     * @return
     */
    private static DefaultLogger getConsoleLogger() {
        DefaultLogger consoleLogger = new DefaultLogger();
        consoleLogger.setErrorPrintStream(System.err);
        consoleLogger.setOutputPrintStream(System.out);
        consoleLogger.setMessageOutputLevel(Project.MSG_INFO);
         
        return consoleLogger;
    }
     
    
    public static Project intilizeAnt(String antBuildPath){
         DefaultLogger consoleLogger = getConsoleLogger();
 
        // Prepare Ant project
        Project project = new Project();
        String url = RunAnt.class.getResource("").getPath();
        
        System.out.println(url);
        File buildFile = new File(antBuildPath+"/ant/"+ANTFILE_NAME);
        project.setUserProperty("ant.file", buildFile.getAbsolutePath());
        project.addBuildListener(consoleLogger);
 
        return project;
    }
    
   
    /**
     * Main method to test code
     * 
     * @param args
     */
    public static void main(String[] args) {
        // Running default target of ant script
       // executeAntTask("ant/build.xml");
 
        String absolutePath = RunAnt.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        absolutePath = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
        System.out.println("-----------------------------");
        Project project = intilizeAnt(absolutePath);
        project.setProperty("Standalone_Location", absolutePath+"/ant/");
        project.setProperty("WFC_Deployment_Folder","C:\\Kronos\\jboss\\wfc\\deployments");
        // Running specified target of ant script
        System.out.println("----"+executeAntTask(project,absolutePath, "byteman"));
    }
 
    
}
