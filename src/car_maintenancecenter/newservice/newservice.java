
package car_maintenancecenter.newservice;


import car_maintenancecenter.Car_MaintenanceCenter;
import car_maintenancecenter.vic.vic;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.stage.Modality;
import javafx.stage.StageStyle;

/**
 *
 * @author akramassi
 */
public class newservice implements Initializable {
    
    @FXML
    void backToHome(MouseEvent event) 
    {
        Car_MaintenanceCenter.callback.back();
    }
    @FXML
    private StackPane eoil;

    @FXML
    private StackPane escr;

    @FXML
    private StackPane goil;

    @FXML
    private StackPane sp;
    @FXML
    private JFXTextArea odesc;

    @FXML
    private JFXTextArea tdesc;

    @FXML
    private JFXComboBox<String> mt;
    @FXML
    private JFXTextField kilo;
    static public String vin;
private  RingProgressIndicator indicator ;
private  RingProgressIndicator i1 ;
private  RingProgressIndicator i2 ;
private  RingProgressIndicator i3 ;
    @FXML
    void cal(ActionEvent event) 
    {
        try {
            String checkstmt = "select max(KILOMETER) from history1 where CAR_VIN= ? ";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setString(1,Car_MaintenanceCenter.x);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            double x=Integer.parseInt(kilo.getText())-rs.getInt(1);
            i1.setProgress(0);
            i2.setProgress(0);
            i3.setProgress(0);
            indicator.setProgress(0);
            new work(i1, (int) ((x/10000)*100)).start();
            new work(i2, (int) ((x/12000)*100)).start();
            new work(i3, (int) ((x/20000)*100)).start();
            new work(indicator, (int) ((x/30000)*100)).start();
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
    void save(ActionEvent event) 
    {
        try {
             String checkstmt = "select count(*) from history1";
            PreparedStatement stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            int x=rs.getInt(1)+1;
            String checkstmt1 = "insert into history1(no,TYPEMN,DESOWNER,DESTECH,CAR_VIN,ACCESSDATE,KILOMETER)values(?,?,?,?,?,sysdate,?)";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
            stmt1.setInt(1,x);
            stmt1.setString(2, mt.getValue());
            stmt1.setString(3, odesc.getText());
            stmt1.setString(4, tdesc.getText());
            stmt1.setString(5, Car_MaintenanceCenter.x);
            stmt1.setInt(6, Integer.parseInt(kilo.getText()));
            stmt1.executeUpdate();
            String checkstmt2 = "insert into vlc values(?,'waiting')";
            PreparedStatement stmt2 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt2);
            stmt2.setInt(1,x);
            stmt2.executeUpdate();
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        indicator = new RingProgressIndicator(); 
        indicator.setRingWidth(200);
        indicator.makeIndeterminate();
        sp.getChildren().add(indicator);
//        new work(indicator,20).start();
        
         i1 = new RingProgressIndicator(); 
        i1.setRingWidth(200);
        i1.makeIndeterminate();
        eoil.getChildren().add(i1);
//        new work(i1,80).start();
        
         i2 = new RingProgressIndicator(); 
        i2.setRingWidth(200);
        i2.makeIndeterminate();
        escr.getChildren().add(i2);
        
        
         i3 = new RingProgressIndicator(); 
        i3.setRingWidth(200);
        i3.makeIndeterminate();
        goil.getChildren().add(i3);
//        new work(i3,42).start();
        
        mt.getItems().addAll("fast", "normal");
        
    }    
    class work extends Thread
    {
        private RingProgressIndicator rpi;
        int x=0;
        private int fi;
        public work(RingProgressIndicator a,int fi)
        {
            this.rpi=a;
            this.fi=fi;
        }
        
        @Override
        public void run()
        {
            while(true)
            {
                try {
                    Thread.sleep(70);
                } catch (InterruptedException ex) {
                   Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("error in Thread ");
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
                }
                Platform.runLater(()->{rpi.setProgress(Double.valueOf(x).intValue());});
                x++;
                if(x>=fi)break;
            }
        }
    }
}
