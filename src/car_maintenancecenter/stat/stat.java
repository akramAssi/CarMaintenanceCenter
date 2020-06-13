/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.stat;

import car_maintenancecenter.Car_MaintenanceCenter;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;


/**
 *
 * @author akramassi
 */
public class stat implements Initializable {
    
    @FXML
    void backToHome(MouseEvent event) {
        Car_MaintenanceCenter.callback.back();
    }
    @FXML
    private TableView<st> tpart1;

    @FXML
    private TableColumn<st,String> part;

    @FXML
    private TableColumn<st,Integer> pcount;

    @FXML
    private TableView<st> tcust;

    @FXML
    private TableColumn<st,String> cname;

    @FXML
    private TableColumn<st,Integer> address;

    @FXML
    private TableView<st> ttech;

    @FXML
    private TableColumn<st,String> tname;

    @FXML
    private TableColumn<st,Integer> rcount;

    @FXML
    private TableView<st> tman;

    @FXML
    private TableColumn<st,String> mname;

    @FXML
    private TableColumn<st,Integer> mcount;

    @FXML
    private TableView<st> tmodel;
    @FXML
    private TableColumn<st,Integer> mocount;
    @FXML
    private TableColumn<st,String> model;

    @FXML
    private TableView<st> tpart2;

    @FXML
    private TableColumn<st,String> part2;
      

    @FXML
    private TableColumn<st,Integer> pcount2;
        private String mun ;
        private String mod ;
        List<st> p1 = new ArrayList<>(1);
        List<st> t = new ArrayList<>(1);
        List<st> mn = new ArrayList<>(1);
        List<st> mo = new ArrayList<>(1);
        List<st> p2 = new ArrayList<>(1);
        List<st> data1 = new ArrayList<>(1);    

   public void setTable1()
           
   {
    
    
   }
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        tman.setRowFactory( tv -> {
            TableRow<st> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (event.getClickCount() == 1 && (! row.isEmpty()) )
                {
                    removeAllRows(tmodel);
                    mo.clear();
                    tmodel.setDisable(false);
                    tpart2.setDisable(true);
                    try {
                        String checkstmt4 = "select MODELC ,count(*) from car1 where MANUFACTURE=? group by MODELC order by count(*) desc";
                        PreparedStatement stmt4 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt4);
                        st rowData = row.getItem();
                        stmt4.setString(1, rowData.get());
                        ResultSet rs4 = stmt4.executeQuery();
                        while (rs4.next())
                        {
                            mo.add(new st(rs4.getNString(1),rs4.getInt(2)));
                            
                        }
                    }
                    catch (SQLException ex) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setContentText(ex.getMessage());
                        errorAlert.showAndWait();
                    }
                    tmodel.setItems(FXCollections.observableArrayList(mo));
                }
            });
    return row ;
});
        tmodel.setRowFactory( tv -> {
    TableRow<st> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 1 && (! row.isEmpty()) ) 
        {
            try 
            {
                removeAllRows(tpart2);
                p2.clear();
                tpart2.setDisable(false);
                String checkstmt12= "select p.NAMEP ,count(*)from car1 c,history1 h, part p where MODELC= ? and c.vin=h.CAR_VIN and p.hno=h.no group by p.NAMEP order by count(*) desc";
                PreparedStatement stmt12 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt12);
                st rowData1 = row.getItem();
                stmt12.setString(1, rowData1.get());
                ResultSet rs12 = stmt12.executeQuery();
                while (rs12.next())
                {
                    p2.add(new st(rs12.getNString(1),rs12.getInt(2)));
                }
            } catch (SQLException ex) {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            }
            tpart2.setItems(FXCollections.observableArrayList(p2));
        }
    });
    return row ;
});
        part.setCellValueFactory(param -> param.getValue().name);
    cname.setCellValueFactory(param -> param.getValue().name);
    address.setCellValueFactory(param -> param.getValue().count);
    tname.setCellValueFactory(param -> param.getValue().name);
    mname.setCellValueFactory(param -> param.getValue().name);
    model.setCellValueFactory(param -> param.getValue().name);
    part2.setCellValueFactory(param -> param.getValue().name);
    
    pcount.setCellValueFactory(param -> param.getValue().count);
    pcount2.setCellValueFactory(param -> param.getValue().count);
    mocount.setCellValueFactory(param -> param.getValue().count);
    mcount.setCellValueFactory(param -> param.getValue().count);
    rcount.setCellValueFactory(param -> param.getValue().count);
        
        try {
            String checkstmt1 = "select NAMEP, count(*) from part group by NAMEP order by count(*) desc";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
             ResultSet rs1 = stmt1.executeQuery();
            while (rs1.next())
            {
             p1.add(new st(rs1.getNString(1),rs1.getInt(2)));   
            }
            
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        ////////////part1
         try {
            String checkstmt = "SELECT ts.name, 0 as count from tech ts where ts.name not in( SELECT t.name from tech t,history1 where t.ID= history1.tid group by t.name) union (SELECT t.name ,count(*) as co  from tech t,history1 where t.ID= history1.tid group by t.name) order by count desc";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
             ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
             t.add(new st(rs.getNString(1),rs.getInt(2)));   
            }
            
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
         ////////tech
         try {
            String checkstmt2 = "select ADDRESS ,count(*) from owner1  group by address order by count(*) desc";
            PreparedStatement stmt2 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt2);
             ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next())
            {
             data1.add(new st(rs2.getNString(1),rs2.getInt(2)));
            }
            
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        /////////////address
         try {
            String checkstmt2 = "select MANUFACTURE ,count(*) from car1 group by MANUFACTURE order by count(*) desc";
            PreparedStatement stmt2 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt2);
             ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next())
            {
             mn.add(new st(rs2.getNString(1),rs2.getInt(2)));
            }
            
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
         ///////mun
         
         
         
        
        tpart1.setItems(FXCollections.observableArrayList(p1));
        tman.setItems(FXCollections.observableArrayList(mn));
        ttech.setItems(FXCollections.observableArrayList(t));
        tcust.setItems(FXCollections.observableArrayList(data1));
        
    } 
    
    public void removeAllRows(TableView resultTable){
    for ( int i = 0; i<resultTable.getItems().size(); i++) {
        resultTable.getItems().clear(); 
    } 
}
    
}
