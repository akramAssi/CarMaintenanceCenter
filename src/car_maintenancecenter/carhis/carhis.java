
package car_maintenancecenter.carhis;

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
import com.itextpdf.text.DocumentException;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
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

public class carhis implements Initializable {
    @FXML
    
    private TableView<Sample> asa;
    @FXML
    private TableColumn<Sample, Integer> cno;
    @FXML
    private TableColumn<Sample, String> cudesc;
    @FXML
    private TableColumn<Sample, String> note;
    @FXML
    private TableColumn<Sample, String> kilo;
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
    private TableColumn<Sample, String> type;

   private double xOffset = 0;
    private double yOffset = 0;
    
    @FXML
    void backToHome(MouseEvent event) {
        Car_MaintenanceCenter.callback.back();
    }
    
    @FXML
    void PRINT(ActionEvent event) 
    {
        try {   
            new PRINT();
        } catch (IOException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText(ex.getMessage());
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
                errorAlert.showAndWait();
        } catch (DocumentException ex) {
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
asa.setRowFactory( tv -> {
    TableRow<Sample> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) {
           car_maintenancecenter.carhis.Sample rowData = row.getItem();
            Car_MaintenanceCenter.xe=rowData.gx();
            Parent pane;
        try {
            pane = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/part/part.fxml"));
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
        cudesc.setCellValueFactory(param -> param.getValue().cudesc);
        note.setCellValueFactory(param -> param.getValue().note);
        kilo.setCellValueFactory(param -> param.getValue().kilo);
        tdes.setCellValueFactory(param -> param.getValue().ctdes);
        tech.setCellValueFactory(param -> param.getValue().ctech);
        tarrived.setCellValueFactory(param -> param.getValue().ctarrived);
        tstart.setCellValueFactory(param -> param.getValue().ctstart);
        tend.setCellValueFactory(param -> param.getValue().ctend);
        tdel.setCellValueFactory(param -> param.getValue().ctdel);
        type.setCellValueFactory(param -> param.getValue().type);

        
        List<Sample> data = new ArrayList<>(4);

try {
            String checkstmt = "select  h.no,h.desowner, h.destech, h.tid, h.typemn,h.othernote,h.kilometer,h.accessdate,h.tstart,h.tend,h.datadelv from history1 h where h.CAR_VIN = ? order by h.accessdate desc";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1,Car_MaintenanceCenter.x );
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String desowner = rs.getNString(2);
                String destech = rs.getNString(3);
                String typemn = rs.getNString(5);
                int tid = rs.getInt(4);
                String othernote = rs.getNString(6);
                int kilometer = rs.getInt(7);
                Timestamp accessdate=rs.getTimestamp(8);
                Timestamp tstar = rs.getTimestamp(9);
                Timestamp tend1 = rs.getTimestamp(10);
                Timestamp datadelv = rs.getTimestamp(11);
                int no = rs.getInt(1);
                String th="";
                if (tid== 0)
                {
                     th="no select";
                    
                }
                else
                {
                    th=""+tid;
                }
                
                
                 data.add(new Sample(no,desowner,destech,""+th,typemn,othernote,""+kilometer,""+accessdate,""+tstar,""+tend1,""+datadelv));


            }
            rs.close();
            stmt.close();
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in sql in carhis ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }


        asa.setItems(FXCollections.observableArrayList(data));

    }
}
