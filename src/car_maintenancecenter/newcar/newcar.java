/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.newcar;
import car_maintenancecenter.Car_MaintenanceCenter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author akramassi
 */
public class newcar implements Initializable 
{
     @FXML
    private JFXTextField vin;

    @FXML
    private JFXTextField manu;

    @FXML
    private JFXTextField model;

    @FXML
    private JFXTextField plate;

    @FXML
    private JFXButton save;

    @FXML
    private JFXTextField year;

    @FXML
    private JFXComboBox<String> fuel;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField add;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXButton find;

    @FXML
    private Label msg;

boolean ownerIsOld =true;
    @FXML
   public  void find(ActionEvent event) 
    {
       
        finx();
          
    }
   @FXML
    void saveo(ActionEvent event) 
    {
        if (id.getText().isEmpty())
             {
//                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
//                 errorAlert.setHeaderText("enter id for old owner");
//                 errorAlert.setContentText("id field is empty");
//                 errorAlert.showAndWait();
                 
                 if (name.getText().isEmpty())
                    {
                        msg.setText("Enter id & name owner ");
                             return;
                    }
                 msg.setText("enter id for owner");
                 return;
             }
        if (name.getText().isEmpty())
        {
            msg.setText("Enter name for owner");
                 return;
        }
        
        
        try 
          {  
             String checkstmt1 = "insert into owner1 values(?,?,?,?)";
             PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
             stmt1.setInt(1, Integer.parseInt(id.getText()));
             stmt1.setString(2, name.getText());
             stmt1.setString(3, add.getText());
             stmt1.setInt(4, Integer.parseInt(phone.getText()));
             if(stmt1.executeUpdate()>0)msg.setText("add owner Successfully");
          } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
             errorAlert.setHeaderText("error in sql in save  owner ");
             errorAlert.setContentText(ex.getMessage());
             errorAlert.showAndWait();
         }
    }
   
    @FXML
    void save(ActionEvent event) 
    {
     if (!error())   
     { finx();
        
        if (ownerIsOld!= true)
        {
        try 
          {  
             String checkstmt1 = "insert into owner1 values(?,?,?,?)";
             PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
             stmt1.setInt(1, Integer.parseInt(id.getText()));
             stmt1.setString(2, name.getText());
             stmt1.setString(3, add.getText());
             stmt1.setInt(4, Integer.parseInt(phone.getText()));
             if(stmt1.executeUpdate()>0)msg.setText("add owner Successfully");
          } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
             errorAlert.setHeaderText("error in sql in save  owner ");
             errorAlert.setContentText(ex.getMessage());
             errorAlert.showAndWait();
         }
        }
        try 
          {  
             String checkstmt1 = "insert into car1 values(?,?,?,?,?,?,?)";
             PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
             stmt1.setString(1, vin.getText());
             stmt1.setString(2, plate.getText());
             stmt1.setString(3, manu.getText());
             stmt1.setString(4, model.getText());
             stmt1.setString(5, fuel.getValue());
             stmt1.setInt(6, Integer.parseInt(year.getText()));
             stmt1.setInt(7, Integer.parseInt(id.getText()));
             if(stmt1.executeUpdate()>0)msg.setText("add Successfully");
          } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
             errorAlert.setHeaderText("error in sql in save new car ");
             errorAlert.setContentText(ex.getMessage());
             errorAlert.showAndWait();
         }
     }    
    
    }
    @FXML
    void backToHome(MouseEvent event) { Car_MaintenanceCenter.callback.back(); }
    
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        fuel.getItems().addAll("diesel", "petrol", "hybrid petrol", "hybrid diesel", "electric" );
        msg.setText(" ");
        id.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            id.setText(newValue.replaceAll("[^\\d]", ""));});
        
        phone.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            phone.setText(newValue.replaceAll("[^\\d]", ""));});
        
        year.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.matches("\\d*")) return;
            year.setText(newValue.replaceAll("[^\\d]", ""));});
    }    
    
    private void finx() {
        try {
        
            String checkstmt1 = "select * from owner1 where id = ?";
             PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
             if (id.getText().isEmpty())
             {
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                 errorAlert.setHeaderText("enter id for  owner");
                 errorAlert.setContentText("id field is empty");
                 errorAlert.showAndWait();
                 ownerIsOld=true;
                 return;
             }

             stmt1.setInt(1, Integer.parseInt(id.getText()));
             ResultSet rs1 = stmt1.executeQuery();
             if (!rs1.next())
             {
                 msg.setText("not found owner 'owner1 is not old'");ownerIsOld=false;return;
             }
             name.setText(rs1.getString(2));
             phone.setText(rs1.getString(4));
             add.setText(rs1.getString(3));
             msg.setText("");
             ownerIsOld=true;
         } catch (SQLException ex) {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
             errorAlert.setHeaderText("error in sql in find owner ");
             errorAlert.setContentText(ex.getMessage());
             errorAlert.showAndWait();
         }
    }
    boolean error()
    {
        boolean h = false;
        String err="please enter ";
        if (id.getText().isEmpty()){err=err+ " Owner id";h=true;}
        if (name.getText().isEmpty()&& h== false){err=err+"Owner Name ";h=true;}else{if(name.getText().isEmpty()){err=err+ " , name";h=true;}}
        if (vin.getText().isEmpty()&& h== false){err=err+"VIN";h=true;}else{if(vin.getText().isEmpty()){err=err+ " , vin";h=true;}}
        if (manu.getText().isEmpty()&& h== false){err=err+"Manufacture";h=true;}else{if(manu.getText().isEmpty()){err=err+ " , Manufacture";h=true;}}
        if (model.getText().isEmpty()&& h== false){err=err+"Mofel";h=true;}else{if(model.getText().isEmpty()){err=err+ " , model";h=true;}}
        if (year.getText().isEmpty()&& h== false){err=err+"Year";h=true;}else{if(year.getText().isEmpty()){err=err+ " , year";h=true;}}
        if (plate.getText().isEmpty()&& h== false){err=err+"Plate number";h=true;}else{if(plate.getText().isEmpty()){err=err+ " & plateNO";h=true;}}
        if (fuel.getValue()== null&& h== false){err=err+"fuel type";h=true;}else{if(fuel.getValue()== null){err=err+ " , fuel type";h=true;}}
        if (h==true)msg.setText(err);
        return h;
    }
    
}
