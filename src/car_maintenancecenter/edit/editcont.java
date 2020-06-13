/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.edit;
import car_maintenancecenter.Car_MaintenanceCenter;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.KeyStroke;


/**
 *
 * @author akramassi
 */
public class editcont implements Initializable 
{
    private double xOffset = 0;
    private double yOffset = 0;
    @FXML
    private HBox hbox;
    @FXML
    private FontAwesomeIcon findc;

    @FXML
    private JFXTextField vin;

    @FXML
    private JFXTextField manu;

    @FXML
    private JFXComboBox<String> fuel;

    @FXML
    private JFXButton delc;

    @FXML
    private JFXButton upc;

    @FXML
    private JFXTextField id;

    @FXML
    private JFXTextField name;

    @FXML
    private JFXTextField phone;

    @FXML
    private JFXTextField add;

    @FXML
    private JFXButton delo;

    @FXML
    private JFXButton upo;

    @FXML
    private FontAwesomeIcon findc1;

    @FXML
    private JFXTextField tid;
    @FXML
    private TableView<Sample1> table;
    @FXML
    private JFXTextField tname;
    @FXML
    private JFXRadioButton rtech;

    @FXML
    private JFXRadioButton rcar;

    @FXML
    private JFXRadioButton rowner;
    @FXML
    private Label msg;
    
    @FXML
    private TextField sh;
    @FXML
    private Line line;
    private String checkstmt;
    PreparedStatement stmt;
    
    
    @FXML
    void delete(MouseEvent event) 
    {
     if(rtech.isSelected())
     {
         tesl();
     }
     if(rowner.isSelected())
     {
         delo();
     }
     if(rcar.isSelected())
     {
        delc();
     }
    }
    @FXML
    void edit (MouseEvent event) 
    {
        if(rtech.isSelected())
     {
            try {
                
                AnchorPane parentContent  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/edit/tech.fxml")));
                techcont.e=this;
                if (hbox.getChildren().size()>1)CLOSE();
                hbox.getChildren().add(1,parentContent);
                line.setVisible(true);
            } catch (IOException ex) 
            {
                Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText(ex.getMessage());
                errorAlert.initStyle(StageStyle.TRANSPARENT);
                errorAlert.initModality(Modality.APPLICATION_MODAL);
                errorAlert.setResizable(false);
                errorAlert.initStyle(StageStyle.UNDECORATED);
                errorAlert.showAndWait();
            }
         
     }
     if(rcar.isSelected())
     {
         
         try {
                
                AnchorPane parentContent  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/edit/car.fxml")));
                car.e=this;
                if(table.getSelectionModel().getSelectedItem()==null)return;
                car.vin=table.getSelectionModel().getSelectedItem().id();
                if (hbox.getChildren().size()>1)CLOSE();
                hbox.getChildren().add(1,parentContent);
                line.setVisible(true);
            } catch (IOException ex) 
            {
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.initStyle(StageStyle.TRANSPARENT);
            errorAlert.initModality(Modality.APPLICATION_MODAL);
            errorAlert.setResizable(false);
            errorAlert.initStyle(StageStyle.UNDECORATED);
            errorAlert.showAndWait();
            }
     }
     if(rowner.isSelected())
     {
         try {
                findc.setGlyphName("EDIT");
                AnchorPane parentContent  = FXMLLoader.load(getClass().getResource(("/car_maintenancecenter/edit/owner.fxml")));
                owner.e=this;
                if(table.getSelectionModel().getSelectedItem()==null)return;
                owner.id=table.getSelectionModel().getSelectedItem().id();
                if (hbox.getChildren().size()>1)CLOSE();
                hbox.getChildren().add(1,parentContent);
                line.setVisible(true);
            } catch (IOException ex) {
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
    void CLOSE( ) 
    {
        
        hbox.getChildren().remove(1);
        line.setVisible(false);
    }
    @FXML
    void car(ActionEvent event) {
        car();
        findc.setGlyphName("EDIT");
    }
     @FXML
    void sh(KeyEvent event) 
    {
        table.getItems().stream().filter(item -> item.name().contains(sh.getText())||item.id().contains(sh.getText()) ).findAny().ifPresent(item -> 
        {
        table.getSelectionModel().select(item);
        table.scrollTo(item);
        });

    }
    @FXML
    void owner(ActionEvent event) {
       owner();findc.setGlyphName("EDIT");
    }

    @FXML
    void tech(ActionEvent event) 
    {
        findc.setGlyphName("PLUS");
        tech();
    }

    @FXML
    void tesl()
    {
        try {
            checkstmt = "delete from tech where id = ?";
            stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            stmt.setInt(1,Integer.parseInt(table.getSelectionModel().getSelectedItem().id()));
            if(stmt.executeUpdate()>0)
            {
                    {
                        tech();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully delete");
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
                errorAlert.setHeaderText("error in in home page cannot open infopage");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
        }
    }
    
    
    @FXML
    void delc()
    {
        if (table.getSelectionModel().getSelectedItem()!=null)
        {
            try
            {
                checkstmt = "delete from car1 where vin = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setString(1,table.getSelectionModel().getSelectedItem().id());
                if(stmt.executeUpdate()>0)
                {
                    {
                        car();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully delete");
                        errorAlert.initStyle(StageStyle.TRANSPARENT);
                        errorAlert.initModality(Modality.APPLICATION_MODAL);
                        errorAlert.setResizable(false);
                        errorAlert.initStyle(StageStyle.UNDECORATED);
                        errorAlert.showAndWait();
                    }
                }
                checkstmt="";
            } 
            catch (SQLException ex) 
            {
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
    void backToHome(MouseEvent event) {
        Car_MaintenanceCenter.callback.back();
    }
    @FXML
    void delo()
    {
        if (table.getSelectionModel().getSelectedItem()!=null)
        {
            try
            {
                
                checkstmt = "delete from owner1 where id = ?";
                stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
                stmt.setInt(1,Integer.parseInt(table.getSelectionModel().getSelectedItem().id()));
                if(stmt.executeUpdate()>0)
                {
                    {
                        owner();
                        Alert errorAlert = new Alert(Alert.AlertType.INFORMATION);
                        errorAlert.setContentText("Successfully delete");
                        errorAlert.initStyle(StageStyle.TRANSPARENT);
                        errorAlert.initModality(Modality.APPLICATION_MODAL);
                        errorAlert.setResizable(false);
                        errorAlert.initStyle(StageStyle.UNDECORATED);
                        errorAlert.showAndWait();
                    }
                }
                checkstmt="";
            } 
            catch (SQLException ex) 
            {
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
   
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
//        fuel.getItems().addAll("diesel", "petrol", "hybrid petrol", "hybrid diesel", "electric" );
        table.autosize();
        tech();
        table.setRowFactory( tv -> {
        TableRow<Sample1> row = new TableRow<>();
        row.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2 && (! row.isEmpty())&& rowner.isSelected() ) 
            {
                Parent pane;
            try {
    //            pane = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/infopage/infopage.fxml"));
               // infopage.x=133;//Integer.parseInt(vin.getText());
               List<Sample1> p1 = new ArrayList<>(1);
                try {
            String checkstmt1 = "select v.vin, v.MANUFACTURE, v.MODELC,(select  MAX(accessdate) from history1 where CAR_VIN=v.vin) from car1 v,owner1 o where o.id=v.OWNER_ID and o.id= ?";
            PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
            stmt1.setInt(1, Integer.parseInt(table.getSelectionModel().getSelectedItem().id()));
             ResultSet rs1 = stmt1.executeQuery();
             
             
             if(!rs1.next())
             {
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setContentText("not have any car");
                errorAlert.showAndWait();
                return;
             }
        
            do
            {
             p1.add(new Sample1(rs1.getNString(1),rs1.getNString(2),rs1.getNString(3),rs1.getNString(4),"","",""));   
            }
            while (rs1.next());
            
        } catch (SQLException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setContentText(ex.getMessage());
            errorAlert.showAndWait();
        }
               ocar.p1=p1;
               ocar.id=table.getSelectionModel().getSelectedItem().name();
               FXMLLoader loader = new FXMLLoader(getClass().getResource("/car_maintenancecenter/edit/ocar.fxml"));
                pane = loader.load();
                Scene scene = new Scene(pane);
                Stage s=new Stage();
                scene.setFill(Color.TRANSPARENT);
                s.setScene(scene);
                s.initStyle(StageStyle.TRANSPARENT);
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
                errorAlert.setHeaderText("error in in edit page cannot open  ocar");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            }
            }
        });
        return row ;
    });
        table.getSelectionModel().setCellSelectionEnabled(true);
table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

MenuItem item = new MenuItem("Copy");
item.setOnAction(new EventHandler<ActionEvent>() {
    @Override
    public void handle(ActionEvent event) {
        ObservableList<TablePosition> posList = table.getSelectionModel().getSelectedCells();
        int old_r = -1;
        StringBuilder clipboardString = new StringBuilder();
        for (TablePosition p : posList) {
            int r = p.getRow();
            int c = p.getColumn();
            Object cell = table.getColumns().get(c).getCellData(r);
            if (cell == null)
                cell = "";
            if (old_r == r)
                clipboardString.append('\t');
            else if (old_r != -1)
                clipboardString.append('\n');
            clipboardString.append(cell);
            old_r = r;
        }
        final ClipboardContent content = new ClipboardContent();
        content.putString(clipboardString.toString());
        Clipboard.getSystemClipboard().setContent(content);
    }
});
ContextMenu menu = new ContextMenu();
menu.getItems().add(item);
table.setContextMenu(menu);

final KeyCodeCombination keyCodeCopy = new KeyCodeCombination(KeyCode.C, KeyCombination.META_ANY);
final KeyCodeCombination keyCodeCopy1 = new KeyCodeCombination(KeyCode.C, KeyCombination.CONTROL_ANY);


    table.setOnKeyPressed(event -> {
        if (keyCodeCopy.match(event)||keyCodeCopy1.match(event)) 
        {
        ObservableList<TablePosition> posList = table.getSelectionModel().getSelectedCells();
        int old_r = -1;
        StringBuilder clipboardString = new StringBuilder();
        for (TablePosition p : posList) {
            int r = p.getRow();
            int c = p.getColumn();
            Object cell = table.getColumns().get(c).getCellData(r);
            if (cell == null)
                cell = "";
            if (old_r == r)
                clipboardString.append('\t');
            else if (old_r != -1)
                clipboardString.append('\n');
            clipboardString.append(cell);
            old_r = r;
        }
        final ClipboardContent content = new ClipboardContent();
        content.putString(clipboardString.toString());
        Clipboard.getSystemClipboard().setContent(content);
    }
    });
        
    }    

    public void tech() {
        findc.setGlyphName("PLUS");
        table.getColumns().clear();
        TableView<Sample1> tab = new TableView<>();
        tab.autosize();
        TableColumn<Sample1, String> col1 = new TableColumn<>("name");
        col1.setCellValueFactory(param -> param.getValue().col2);
        col1.setPrefWidth(331.5);
        TableColumn<Sample1, String> col2 = new TableColumn<>("id");
        col2.setCellValueFactory(param -> param.getValue().col1);
        col2.setPrefWidth(331.5);       
        
        table.getColumns().addAll(col1,col2);
        List<Sample1> data = new ArrayList<>(1);
        try {
            checkstmt = "select * from tech";
            stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                data.add(new Sample1(rs.getString(1),rs.getString(2),"","","","",""));
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
        table.setItems(FXCollections.observableArrayList(data));
    }
    public void car(){
        findc.setGlyphName("EDIT");
        rcar.setSelected(true);
        table.getColumns().clear();
        TableView<Sample1> tab = new TableView<>();
        tab.autosize();
        TableColumn<Sample1, String> col1 = new TableColumn<>("name");
        col1.setCellValueFactory(param -> param.getValue().col2);
        
        TableColumn<Sample1, String> col2 = new TableColumn<>("vin");
        col2.setCellValueFactory(param -> param.getValue().col1);
               
        TableColumn<Sample1, String> col3 = new TableColumn<>("MANUFACTURE");
        col3.setCellValueFactory(param -> param.getValue().col3);
        
        TableColumn<Sample1, String> col4 = new TableColumn<>("MODEL");
        col4.setCellValueFactory(param -> param.getValue().col4);
        
        TableColumn<Sample1, String> col5 = new TableColumn<>("YEAR");
        col5.setCellValueFactory(param -> param.getValue().col5);
        
        TableColumn<Sample1, String> col6 = new TableColumn<>("FULETYPE");
        col6.setCellValueFactory(param -> param.getValue().col6);
        
        TableColumn<Sample1, String> col7 = new TableColumn<>("Plate");
        col7.setCellValueFactory(param -> param.getValue().col7);
        table.getColumns().addAll(col1,col2,col3,col4,col5,col6,col7);
//        table=tab;
        List<Sample1> data = new ArrayList<>(1);
        try {
            checkstmt = "select o.name,v.vin, v.MANUFACTURE,v.MODELC,v.MFYEAR,v.FULETYPE,v.PLATENO from car1 v,owner1 o where o.id=v.OWNER_ID";
            stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                data.add(new Sample1(rs.getString(2),rs.getString(1),rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7)));
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
        table.setItems(FXCollections.observableArrayList(data));
    }

    public void owner() {
        findc.setGlyphName("EDIT");
        rowner.setSelected(true);
        table.getColumns().clear();
        TableView<Sample1> tab = new TableView<>();
        tab.autosize();
        TableColumn<Sample1, String> col1 = new TableColumn<>("name");
        col1.setCellValueFactory(param -> param.getValue().col2);
        col1.setPrefWidth(165.75);
        TableColumn<Sample1, String> col2 = new TableColumn<>("id");
        col2.setCellValueFactory(param -> param.getValue().col1);
        col2.setPrefWidth(165.75);       
        TableColumn<Sample1, String> col3 = new TableColumn<>("address");
        col3.setCellValueFactory(param -> param.getValue().col3);
        col3.setPrefWidth(165.75);
        TableColumn<Sample1, String> col4 = new TableColumn<>("phone");
        col4.setCellValueFactory(param -> param.getValue().col4);
        col4.setPrefWidth(165.75);
        table.getColumns().addAll(col1,col2,col3,col4);
//        table=tab;
        List<Sample1> data = new ArrayList<>(1);
        try {
            checkstmt = "select * from owner1";
            stmt = Car_MaintenanceCenter.connection.prepareStatement(checkstmt);
            ResultSet rs = stmt.executeQuery();
            while (rs.next())
            {
                data.add(new Sample1(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4),"","",""));
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
        table.setItems(FXCollections.observableArrayList(data));
    }
    
    
}
