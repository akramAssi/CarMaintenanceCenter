/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.vic;

import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import  car_maintenancecenter.Car_MaintenanceCenter;
import  car_maintenancecenter.endservice.end;
import car_maintenancecenter.home.homecont;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableRow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
/**
 *
 * @author akramassi
 */

public class vic implements Initializable {
    @FXML
    
    private TableView<Sample> asa;
    @FXML
    private TableColumn<Sample, Integer> cno;
    @FXML
    private TableColumn<Sample, String> status;
    @FXML
    private TableColumn<Sample, String> oname;
    @FXML
    private TableColumn<Sample, String> nmanuf;
    @FXML
    private TableColumn<Sample, String> model;
    @FXML
    private TableColumn<Sample, String> tdes;
    @FXML
    private TableColumn<Sample, String> tech;

    @FXML
    private TableColumn<Sample, String> tarrived;
    @FXML
    private TableColumn<Sample, String> tstart;
    @FXML
    private TableColumn<Sample, String> tend;

    @FXML
    private TableColumn<Sample, String> tdel;
    @FXML
    private TableColumn<Sample, String> cudesc;
    @FXML
    private TableColumn<Sample, String> type;
    private double xOffset = 0;
    private double yOffset = 0;
   
    
    @FXML
    void backToHome(MouseEvent event) {
        Car_MaintenanceCenter.callback.back();
    }
   

    @Override
    public  void initialize(URL url, ResourceBundle rb) {
        Car_MaintenanceCenter.vas=this;
//        TableColumn<Sample, Integer> idColumn = new TableColumn<>("Id");
asa.setRowFactory( tv -> {
    TableRow<Sample> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
            Sample rowData = row.getItem();
            Car_MaintenanceCenter.xe=rowData.gx();
            Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/endservice/endservice.fxml"));
            Scene scene = new Scene(pane);
             Stage s=new Stage();
            s.setScene(scene);
//            s.setTitle("info for car");
            s.initModality(Modality.APPLICATION_MODAL);
            s.setResizable(false);
            s.initStyle(StageStyle.UNDECORATED);
            pane.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        pane.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                s.setX(event.getScreenX() - xOffset);
                s.setY(event.getScreenY() - yOffset);
            }
        });
            s.showAndWait();
            
        } catch (IOException ex) {
           Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in error end service ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
        
        
        }
    });
    return row ;
});
        cno.setCellValueFactory(param -> param.getValue().id);
//        cid.setPrefWidth(100);

//        TableColumn<Sample, String> nameColumn = new TableColumn<>("Name");
        oname.setCellValueFactory(param -> param.getValue().cnowner);
        status.setCellValueFactory(param -> param.getValue().status);
        nmanuf.setCellValueFactory(param -> param.getValue().cnmanuf);
        model.setCellValueFactory(param -> param.getValue().cmodel);
        tdes.setCellValueFactory(param -> param.getValue().ctdes);
        tech.setCellValueFactory(param -> param.getValue().ctech);
        tarrived.setCellValueFactory(param -> param.getValue().ctarrived);
        tstart.setCellValueFactory(param -> param.getValue().ctstart);
        tend.setCellValueFactory(param -> param.getValue().ctend);
        tdel.setCellValueFactory(param -> param.getValue().ctdel);
        cudesc.setCellValueFactory(param -> param.getValue().cudesc);
        type.setCellValueFactory(param -> param.getValue().type);
//        cfoo.setPrefWidth(70);       

//        asa.getColumns().addAll(cid, cfoo);
        
        wq();

    }
    
    public void wq()
    {
        List<Sample> data = new ArrayList<>(1);
try {
            String checkstmt = "select  v.no,v.STATUS,a.name,c.manufacture, c.modelc,h.desowner, h.destech,h.tid,h.typemn,h.accessdate,h.tstart,h.tend,h.datadelv from owner1 a,car1 c,history1 h,vlc v where a.id=c.owner_id and h.CAR_VIN =c.vin and v.no = h.no order by h.accessdate desc";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
//            stmt.setString(1, );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int no = rs.getInt(1);
                String STATUS=rs.getString(2);
                String name = rs.getNString(3);
                String manufacture = rs.getNString(4);
                String modelc = rs.getNString(5);
                String desowner = rs.getNString(6);
                String destech = rs.getNString(7);
                String tid = rs.getNString(8);
                String typemn=rs.getString(9);
                Timestamp accessdate=rs.getTimestamp(10);
                Timestamp tstar = rs.getTimestamp(11);
                Timestamp tend1 = rs.getTimestamp(12);
                Timestamp datadelv = rs.getTimestamp(13);                
                 if (tid!= null)
                {
                    if(tid.equals(" ")||tid.equals("")){ tid="no select";}
                    else
                    {
                        String checkstmt1 = "select name from tech where id = ?";
                        PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
                         stmt1.setInt(1, Integer.parseInt(tid));
                         ResultSet rs1 = stmt1.executeQuery();
                         rs1.next();
                         tid=rs1.getString(1);
                    }
                    
                }
                
                else{tid="no select";}
                 if (typemn!= null)
                {
                    if(typemn.contains("f"))
                    { typemn="fast";}
                    else 
                        {typemn="Normal";}
                    
                }else{tid="no select";}
                
                 data.add(new Sample(no,STATUS,name,manufacture,modelc,desowner,destech,tid,typemn,""+accessdate,""+tstar,""+tend1,""+datadelv));


            }
            
            rs.close();
            stmt.close();
        } catch (SQLException ex)
        {
             Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in sql in vlc ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }

        asa.setItems(FXCollections.observableArrayList(data));

    }
}
