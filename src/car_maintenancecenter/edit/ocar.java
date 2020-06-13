package car_maintenancecenter.edit;

import car_maintenancecenter.Car_MaintenanceCenter;
import car_maintenancecenter.stat.st;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ocar {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    @FXML
    private TableView<Sample1> table;

    @FXML
    private FontAwesomeIcon cc;

    @FXML
    private TableColumn<Sample1, String> vin;

    @FXML
    private TableColumn<Sample1, String> mm;

    @FXML
    private TableColumn<Sample1, String> model;

    @FXML
    private TableColumn<Sample1, String> last;

    @FXML
    private Label name;
    private double xOffset = 0;
    private double yOffset = 0;
   @FXML
    public void close(MouseEvent event) 
    {
        Stage stage = (Stage) cc.getScene().getWindow();
        stage.close();
    }
    public static String id="m";
    public static List<Sample1> p1 ;
    @FXML
    void initialize() {
       vin.setCellValueFactory(param -> param.getValue().col1);
       mm.setCellValueFactory(param -> param.getValue().col2);
       model.setCellValueFactory(param -> param.getValue().col3);
       last.setCellValueFactory(param -> param.getValue().col4);
       
        name.setText(id);
        table.setItems(FXCollections.observableArrayList(p1));
        table.setRowFactory( tv -> {
    TableRow<Sample1> row = new TableRow<>();
    row.setOnMouseClicked(event -> {
        if (event.getClickCount() == 2 && (! row.isEmpty()) ) 
        {
            Car_MaintenanceCenter.x=table.getSelectionModel().getSelectedItem().id();
            Parent pane;
            try {
    //            pane = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/infopage/infopage.fxml"));
               // infopage.x=133;//Integer.parseInt(vin.getText());
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/car_maintenancecenter/infopage/infopage.fxml"));
                pane = loader.load();
                Scene scene = new Scene(pane);
                Stage s=new Stage();
                scene.setFill(Color.TRANSPARENT);
                s.setScene(scene);
                s.initStyle(StageStyle.TRANSPARENT);
                s.setTitle("info for car");
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
                errorAlert.setHeaderText("error in in home page cannot open infopage");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            }
            close(null);
        }
    });
    return row ;
});
        
    }
}
