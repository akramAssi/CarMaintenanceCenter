
package car_maintenancecenter.infopage;


import car_maintenancecenter.Car_MaintenanceCenter;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 *
 * @author akramassi
 */
public class infopage implements Initializable {
    
    @FXML
    private FontAwesomeIcon cc;
    @FXML
    private Label name;
    @FXML
    private Label name1;
    @FXML
    private Label phone;

    @FXML
    private Label address;

    @FXML
    private Label manname;

    @FXML
    private Label model;

    @FXML
    private Label year;

    @FXML
    private Label vin;

    @FXML
    private Label plate;

    @FXML
    private Label date;

    @FXML
    private Label tech;

//    public infopage() {
//        this.x = "ds";
//    }

    @FXML
    public void close(MouseEvent event) 
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
    }
    @FXML
    public void newS(ActionEvent event)
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
        Car_MaintenanceCenter.callback.spa("newservice","new service");
    }
    @FXML
    public void openhis(ActionEvent event)
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
        Car_MaintenanceCenter.callback.spa("carhis","car history");
        
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            String checkstmt = "select  o.name,o.phone,o.address,c.manufacture, c.modelc, c.mfyear,c.plateno from owner1 o ,car1 c where c.owner_id=o.id and c.vin=?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1, Car_MaintenanceCenter.x);
//            stmt.setString(1, );
            ResultSet rs = stmt.executeQuery();
//            List<Sample> data = new ArrayList<>(1);
            while (rs.next()) {
                
                name.setText(rs.getNString(1));
                phone.setText(rs.getNString(2));
                address.setText(rs.getNString(3));
                manname.setText(rs.getNString(4));
                model.setText(rs.getNString(5));
                year.setText(rs.getNString(6));
                vin.setText(Car_MaintenanceCenter.x);
                plate.setText(rs.getNString(7));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("error in sql in infopage ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        try {
            String checkstmt = "select  MAX(accessdate) from history1 where CAR_VIN=?";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1, Car_MaintenanceCenter.x);
//            stmt.setString(1, );
            ResultSet rs = stmt.executeQuery();
//            List<Sample> data = new ArrayList<>(1);
            if  (rs.next()) {
                date.setText(rs.getNString(1));
                
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("error in sql in infopage ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        
        try {
            String checkstmt = "select t.name from history1 h, tech t where h.tid=t.id and h.CAR_VIN=? and h.accessdate=(select  MAX(accessdate) from history1 where CAR_VIN=?)";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1, Car_MaintenanceCenter.x);
            stmt.setString(2, Car_MaintenanceCenter.x);
            ResultSet rs = stmt.executeQuery();
            if  (rs.next()) {
                tech.setText(rs.getNString(1));
            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex)
        {
            Alert errorAlert = new Alert(AlertType.ERROR);
            errorAlert.setHeaderText("error in sql in infopage ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        name=makeSelectable(name);
        name1=makeSelectable(name1);
        phone=makeSelectable(phone);
        address=makeSelectable(address);
        manname=makeSelectable(manname);
        model=makeSelectable(model);
        year=makeSelectable(year);
        vin=makeSelectable(vin);
        plate=makeSelectable(plate);
    }    
    
    private Label makeSelectable(Label label) {
  StackPane textStack = new StackPane();
  TextField textField = new TextField(label.getText());
  textField.setEditable(false);
  textField.setStyle(
    "-fx-background-color: transparent; -fx-background-insets: 0; -fx-background-radius: 0; -fx-padding: 0;"
  );
  // the invisible label is a hack to get the textField to size like a label.
  Label invisibleLabel = new Label();  
  invisibleLabel.textProperty().bind(label.textProperty());
  invisibleLabel.setVisible(false);
  textStack.getChildren().addAll(invisibleLabel, textField);
  label.textProperty().bindBidirectional(textField.textProperty());
  label.setGraphic(textStack);
  label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);

  return label;
}
}
