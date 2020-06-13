/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.main;

import car_maintenancecenter.home.homecont;
import car_maintenancecenter.Car_MaintenanceCenter;
import com.jfoenix.controls.JFXTabPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Tab;
import javafx.scene.layout.AnchorPane;


/**
 *
 * @author akramassi
 */
public class mainController implements Initializable ,Tpean{
    
    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane vicroot;
    @FXML
        private JFXTabPane troots;
    @FXML
    private Tab TAB;
    

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       Car_MaintenanceCenter.callback=this;
       homecont.callback=this;
        try {            
            AnchorPane parentContent  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/home/home.fxml")));
//            bhome.getChildren().setAll(parentContent);
            root.getChildren().setAll(parentContent);
            AnchorPane parentContent1  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/vic/vic.fxml")));
//            bhome.getChildren().setAll(parentContent);
            vicroot.getChildren().setAll(parentContent1);
           
           
        } catch (IOException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in in home page cannot open main  page");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        
       
    }    
     
    public void spa(String a,String tital) {
        try {
            AnchorPane parentContent  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/"+a+"/"+a+".fxml")));
            root.getChildren().setAll(parentContent);
            TAB.setText(tital);
        } catch (IOException ex) {
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in in home page cannot open "+a+" page");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        
    }
    public void back()
    {
        try {
            AnchorPane bhome= FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/home/home.fxml")));
            root.getChildren().setAll(bhome);
            TAB.setText("HOME");
        } catch (IOException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in in home page cannot back");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
    }

}