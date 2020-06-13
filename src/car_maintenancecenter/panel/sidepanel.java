
package car_maintenancecenter.panel;

import car_maintenancecenter.main.Tpean;
import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 *
 * @author akramassi
 */
public class sidepanel implements Initializable {
    
    @FXML
    private JFXButton home;

    @FXML
    private JFXButton addCar;

    @FXML
    private JFXButton history;

    @FXML
    private JFXButton tech;
    
    private Tpean callback;
    
    @FXML
    void openHistory(ActionEvent event) 
    {
        callback.spa("mainhistory","main History");

    }
    @FXML
    void edit(ActionEvent event) 
    {
        callback.spa("newcar","car enrollment");

    }
    @FXML
    void stat(ActionEvent event) 
    {
        callback.spa("stat","statistics");

    }
    @FXML
    void pro(ActionEvent event) 
    {
        callback.spa("edit","Profile Edit");
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    public void setCallback(Tpean callback) {
        this.callback = callback;
    }
}
