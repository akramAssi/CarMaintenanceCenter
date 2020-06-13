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
import javafx.stage.Modality;
import javafx.stage.StageStyle;
import car_maintenancecenter.edit.editcont;
import javafx.scene.input.MouseEvent;

public class techcont {

    static public editcont e;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private JFXTextField tid;

    @FXML
    private JFXTextField tname;
 private String checkstmt;
    PreparedStatement stmt;
    @FXML
    void add(ActionEvent event) 
    {
        if (tid.getText().isEmpty()||tname.getText().isEmpty())
        {
         Alert errorAlert = new Alert(Alert.AlertType.WARNING);
//            errorAlert.setHeaderText("Could not find the sql ");
            errorAlert.setContentText("please enter text field");
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.showAndWait();
            return;
        }
        try {
            checkstmt = "insert into tech values(?,?)";
            stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1,Integer.parseInt(tid.getText()));
            stmt.setString(2,tname.getText());
            if(stmt.executeUpdate()>0)
            {
                e.tech();
                Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
//            errorAlert.setHeaderText("Could not find the sql ");
            errorAlert.setContentText("Successful technical addition");
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.showAndWait();
            }
            checkstmt="";
        } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
            errorAlert.setHeaderText("No technician added");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.initStyle(StageStyle.TRANSPARENT);
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.setResizable(false);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.showAndWait();
        }
    }
    @FXML
    void CLOSE(MouseEvent event) 
    {
        e.CLOSE();
    }
    @FXML
    void initialize() {
      tid.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            tid.setText(newValue.replaceAll("[^\\d]", ""));});

    }
}