/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.part;

import car_maintenancecenter.Car_MaintenanceCenter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author akramassi
 */
public class part implements Initializable {
    
    @FXML
    private Label part;
    @FXML
    private FontAwesomeIcon cc;
    @FXML
    public void close(MouseEvent event) 
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String checkstmt = "select NAMEP from part where hno= ? ";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1,Car_MaintenanceCenter.xe);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            { int i=2;
                part.setText("Patrt add : 1) " +rs.getNString(1));
                while(rs.next())
                {
                    part.setText(part.getText().concat(" "+i+") " +rs.getNString(1)));i++;
                }
            }
            while(rs.next())
            {
                
            }
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText(ex.getMessage());
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
                errorAlert.showAndWait();
        }
    }    
    
}