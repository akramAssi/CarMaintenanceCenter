/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.endservice;

import car_maintenancecenter.Car_MaintenanceCenter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


/**
 *
 * @author akramassi
 */
public class end implements Initializable {
    
    @FXML
    private FontAwesomeIcon cc;
    @FXML
    private JFXTextArea des;
    @FXML
    private JFXButton start;

    @FXML
    private JFXButton end;
    @FXML
    private JFXTextField part;
    @FXML
    private JFXButton del;
    
    @FXML
    private JFXComboBox<String> tech;
    public static int xa;
    
    @FXML
    void del(ActionEvent event) 
    {
        try {
            String checkstmt1 = "update history1 set DATADELV = sysdate where no= ?";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
            stmt1.setInt(1,Car_MaintenanceCenter.xe);
            stmt1.executeUpdate();
             String checkstmt = "delete from vlc where no= ?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1,Car_MaintenanceCenter.xe);
            stmt.executeUpdate();
            Car_MaintenanceCenter.vas.wq();
            
            
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
    
    @FXML
    void start(ActionEvent event) 
    {
        try {
            String checkstmt1 = "select id from tech where name = ?";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
            stmt1.setString(1,tech.getValue());
            ResultSet rs1 = stmt1.executeQuery();
            rs1.next();
            String checkstmt = "update history1 set TID= ?,TSTART = sysdate where no= ?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1,rs1.getNString(1));
            stmt.setInt(2, Car_MaintenanceCenter.xe);
            stmt.executeUpdate();
            
            String checkstmt2 = "update vlc set STATUS='in service' where no= ?";
            PreparedStatement stmt2 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt2);
            stmt2.setInt(1,Car_MaintenanceCenter.xe);
            stmt2.executeUpdate();
                Car_MaintenanceCenter.vas.wq();
            des.setDisable(false);
            part.setDisable(false);
            end.setDisable(false);
            start.setDisable(true);
            tech.setDisable(true);
            
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

    @FXML
    public void close(MouseEvent event) 
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
    }
    
    @FXML
    void end(ActionEvent event) 
    {
        try
        {
            String checkstmt3 = "update history1 set OTHERNOTE = ? where no= ?";
            PreparedStatement stmt3 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt3);
            stmt3.setString(1, des.getText());
            stmt3.setInt(2, Car_MaintenanceCenter.xe);
            stmt3.executeUpdate();
            
            String checkstmt = "update history1 set Tend = sysdate where no= ?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1, Car_MaintenanceCenter.xe);
            stmt.executeUpdate();
            
            String checkstmt2 = "update vlc set STATUS='finish' where no= ?";
            PreparedStatement stmt2 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt2);
            stmt2.setInt(1,Car_MaintenanceCenter.xe);
            stmt2.executeUpdate();
            Car_MaintenanceCenter.vas.wq();
            
           if(!part.getText().isEmpty())
           {
            String[] s= part.getText().split(",");
            for(int i=0;i<s.length;i++)
            {
            String checkstmt0 = "insert into  part values(?,?)";
            PreparedStatement stmt0 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt0);
            stmt0.setInt(1,Car_MaintenanceCenter.xe);
            stmt0.setString(2, s[i]);
            stmt0.executeUpdate();
            }
           }
            des.setDisable(true);
            part.setDisable(true);
            tech.setDisable(true);
            end.setDisable(true);
            start.setDisable(true);
            del.setDisable(false);
            
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String checkstmt1 = "select name from tech ";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
            ResultSet rs1 = stmt1.executeQuery();
              while (rs1.next())
              {
                  tech.getItems().add(rs1.getNString(1));
              }
              
              String checkstmt = "select STATUS from vlc where no= ?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1,Car_MaintenanceCenter.xe);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            if (!rs.getString(1).isEmpty())
            {
                if (rs.getString(1).contains("in service"))
                {
                    des.setDisable(false);
                    part.setDisable(false);
                    end.setDisable(false);
                    start.setDisable(true);
                    tech.setDisable(true);
                   
                }
                else
                    if (rs.getString(1).contains("finish"))
                    {
                        des.setDisable(true);
                        part.setDisable(true);
                        tech.setDisable(true);
                        end.setDisable(true);
                        start.setDisable(true);
                        del.setDisable(false);
                    }
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