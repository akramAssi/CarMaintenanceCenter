/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter.home;
import car_maintenancecenter.main.Tpean;
import car_maintenancecenter.panel.sidepanel;
import com.jfoenix.controls.JFXDrawer;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.transitions.hamburger.HamburgerSlideCloseTransition;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import com.jfoenix.controls.JFXTextField;
import car_maintenancecenter.Car_MaintenanceCenter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.MenuButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

public class homecont implements Initializable ,Tpean{

     @FXML
    private CheckMenuItem svin;

    @FXML
    private CheckMenuItem sname;

    @FXML
    private CheckMenuItem splate;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    @FXML
    private JFXTextField vin;
    @FXML
    private MenuButton sh;
    @FXML
    private AnchorPane res;
    
    private int select=1;
    
    static public Tpean callback;
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/car_maintenancecenter/panel/sidepanel.fxml"));
            AnchorPane box = loader.load();
            sidepanel controller = loader.getController();
            controller.setCallback(callback);
            drawer.setSidePane(box);
        } catch (IOException ex) {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("error in in home page cannot open list");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
        }
        HamburgerSlideCloseTransition transition = new HamburgerSlideCloseTransition(hamburger);
//        HamburgerBackArrowBasicTransition transition = new HamburgerBackArrowBasicTransition(hamburger);
        transition.setRate(-1);
        hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (e) -> {
            transition.setRate(transition.getRate() * -1);
            transition.play();

            if (drawer.isShown()) {
                drawer.close();
            } else {
                drawer.open();
               
            }
        });
        
        
    }
    @FXML
    void newcar(ActionEvent event) 
    {
        callback.spa("newcar","car enrollment");
    }
    
    void infop(String v) 
    {
        if(!vin.getText().isEmpty())
        {
            
            Car_MaintenanceCenter.x=v;
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
        
        }

    }
    @FXML
    void search (ActionEvent event) 
    {
    if(!vin.getText().isEmpty())
    {
        switch(select)
        {
            case 1 :
            {
              try 
            {
                String checkstmt1 = "select count(*) from car1 where  vin= ?";
                PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
                stmt1.setString(1,vin.getText());
                ResultSet rs1 = stmt1.executeQuery();
                if (rs1.next())
                {
                    if(rs1.getInt(1)>0)
                    {
                        infop(vin.getText());
                        
                    }
                    else
                    {
                        res.setVisible(true);
                    }
                }
                
            } catch (SQLException ex) 
            {
                 Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("error in in home page cannot open infopage");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            } break;  
            }
            case 3:
            {
              try 
            {
                String checkstmt1 = "select vin from car1 where  PLATENO= ?";
                PreparedStatement stmt1 = Car_MaintenanceCenter.connection.prepareStatement(checkstmt1);
                stmt1.setString(1,vin.getText());
                ResultSet rs1 = stmt1.executeQuery();
                if (rs1.next())
                {
                    infop(rs1.getNString(1));
                }
                 else
                    {
                        res.setVisible(true);
                    }
                
            } catch (SQLException ex) 
            {
               Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                errorAlert.setHeaderText("error in in home page cannot open infopage");
                errorAlert.setContentText(ex.getMessage());
                errorAlert.showAndWait();
            }    break;
            }
            case 2:
            {
                
            }
                    
        }
    }
    else
    {
        
    }
}

    @FXML
    void sevin (ActionEvent event) 
    {
        vin.setPromptText("Enter VIN .......");
        if(!svin.isSelected())
        {
            svin.setSelected(true);
            return;
        }
        select= 1;
        sname.setSelected(false);
        splate.setSelected(false);
    }
     @FXML
    void sename (ActionEvent event) 
    {
        vin.setPromptText("Enter Owner name .......");
        if(!sname.isSelected())
        {
            sname.setSelected(true);
            return;
        } 
        select= 2;
        splate.setSelected(false);
        svin.setSelected(false);
    }
     @FXML
    void seplate (ActionEvent event) 
    {
        vin.setPromptText("Enter Plate Number.......");
        if(splate.isSelected())
        {
            select= 3;
            sname.setSelected(false);
            svin.setSelected(false);
            return;
        } 
        splate.setSelected(true);
        
    }
    
    public void setCallback(Tpean callback) {
        this.callback = callback;
    }



    @Override
    public void back() {
       
    }
    

    @Override
    public void spa(String namep, String tital) {
       
    }
        @FXML
    void disable(KeyEvent event) 
    {
        res.setVisible(false);
    }
}