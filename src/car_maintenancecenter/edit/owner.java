
package car_maintenancecenter.edit;

import car_maintenancecenter.Car_MaintenanceCenter;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

public class owner {

    private String checkstmt;
    PreparedStatement stmt;
    static public editcont e;
    static String id;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField add;

    @FXML
    void CLOSE(MouseEvent event) 
    {
        e.CLOSE();
    }

    @FXML
    void upo(ActionEvent event) 
    {
        if(add.getText().isEmpty())
        {
            try {
                checkstmt = "update owner1 set PHONE = ? where id = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setInt(1,Integer.parseInt(phone.getText()));
                stmt.setInt(2,Integer.parseInt(id));
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.owner();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully Updated");
                        errorAlert.initStyle(StageStyle.TRANSPARENT);
                        errorAlert.initModality(Modality.APPLICATION_MODAL);
                        errorAlert.setResizable(false);
                        errorAlert.initStyle(StageStyle.UNDECORATED);
                        errorAlert.showAndWait();
                    }
                }
                checkstmt="";
                return;
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
        if(phone.getText().isEmpty())
        {
            try {
                checkstmt = "update owner1 set ADDRESS = ? where id = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,add.getText());
               stmt.setInt(2,Integer.parseInt(id));
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.owner();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully Updated");
                        errorAlert.initStyle(StageStyle.TRANSPARENT);
                        errorAlert.initModality(Modality.APPLICATION_MODAL);
                        errorAlert.setResizable(false);
                        errorAlert.initStyle(StageStyle.UNDECORATED);
                        errorAlert.showAndWait();
                    }
                }    
                checkstmt="";
            } catch (SQLException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText(ex.getMessage());
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
                errorAlert.showAndWait();
            }
            return;
        }
        else
        {
            try 
            {
                checkstmt = "update owner1 set ADDRESS = ? ,PHONE= ? where id = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,add.getText());
                stmt.setString(2,phone.getText());
                stmt.setInt(3,Integer.parseInt(id));
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.owner();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully Updated");
                        errorAlert.initStyle(StageStyle.TRANSPARENT);
                        errorAlert.initModality(Modality.APPLICATION_MODAL);
                        errorAlert.setResizable(false);
                        errorAlert.initStyle(StageStyle.UNDECORATED);
                        errorAlert.showAndWait();
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

    @FXML
    void initialize() {
        assert name != null : "fx:id=\"name\" was not injected: check your FXML file 'owner.fxml'.";
        assert phone != null : "fx:id=\"phone\" was not injected: check your FXML file 'owner.fxml'.";
        assert add != null : "fx:id=\"add\" was not injected: check your FXML file 'owner.fxml'.";

    }
}
