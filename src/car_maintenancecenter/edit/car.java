package car_maintenancecenter.edit;

import car_maintenancecenter.Car_MaintenanceCenter;
import car_maintenancecenter.edit.editcont;
import static car_maintenancecenter.edit.techcont.e;

import com.jfoenix.controls.JFXComboBox;
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

public class car {

    private String checkstmt;
    PreparedStatement stmt;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField oid;

    @FXML
    private JFXTextField manu;

    @FXML
    private JFXComboBox<String> fuel;
    static public editcont e;
    static String vin;
    @FXML
    void CLOSE(MouseEvent event) 
    {
        e.CLOSE();
    }

    @FXML
    void upc(ActionEvent event)
    {
        ////////update fuel type only
        if(manu.getText().isEmpty()&& oid.getText().isEmpty()&&fuel.getValue()!=null)
        {
            try {
                checkstmt = "update car1 set FULETYPE = ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,fuel.getValue());
                stmt.setString(2,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        /////update plate only
        if(fuel.getValue() == null&& oid.getText().isEmpty()&&!manu.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set PLATENO = ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,manu.getText());
                stmt.setString(2,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        }
        ////update oid only
        if(fuel.getValue() == null&& !oid.getText().isEmpty()&&manu.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set OWNER_ID = ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,oid.getText());
                stmt.setString(2,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        }
        ////update plate & fuel only 
        if(!manu.getText().isEmpty()&&fuel.getValue() != null&& oid.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set PLATENO = ? ,FULETYPE= ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,manu.getText());
                stmt.setString(2,fuel.getValue());
                stmt.setString(3,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        /////update owner id & plate 
        if(!manu.getText().isEmpty()&&fuel.getValue() == null&& !oid.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set PLATENO = ? ,OWNER_ID= ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,manu.getText());
                stmt.setString(2,oid.getText());
                stmt.setString(3,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        //////updata fuel & owner id 
        if(manu.getText().isEmpty()&&fuel.getValue() != null&& !oid.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set FULETYPE = ? ,OWNER_ID= ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,fuel.getValue());
                stmt.setString(2,oid.getText());
                stmt.setString(3,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        //// upadate all
         if(!manu.getText().isEmpty()&&fuel.getValue() != null&& !oid.getText().isEmpty())
        {
            try {
                checkstmt = "update car1 set FULETYPE = ? ,OWNER_ID= ? where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,fuel.getValue());
                stmt.setString(2,oid.getText());
                stmt.setString(3,vin);
                if(stmt.executeUpdate()>0)
                {
                    {
                        e.car();
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
        assert oid != null : "fx:id=\"oid\" was not injected: check your FXML file 'car.fxml'.";
        assert manu != null : "fx:id=\"manu\" was not injected: check your FXML file 'car.fxml'.";
        assert fuel != null : "fx:id=\"fuel\" was not injected: check your FXML file 'car.fxml'.";
        fuel.getItems().addAll("diesel", "petrol", "hybrid petrol", "hybrid diesel", "electric" );
        oid.textProperty().addListener((observable, oldValue, newValue) -> 
        {
            if (newValue.matches("\\d*")) return;
            oid.setText(newValue.replaceAll("[^\\d]", ""));}
        );

    }
}
