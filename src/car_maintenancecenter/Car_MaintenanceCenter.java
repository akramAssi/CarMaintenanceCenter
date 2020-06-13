/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package car_maintenancecenter;

import car_maintenancecenter.main.Tpean;
import car_maintenancecenter.vic.vic;
import car_maintenancecenter.main.mainController;
import de.jensd.fx.glyphs.testapps.App;
import java.awt.Toolkit;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;


/**
 *
 * @author akramassi
 */

public class Car_MaintenanceCenter extends Application {
    static public Tpean callback;
    static public Stage s;
    static public String x;
    public static Connection connection = null;
    public static vic vas=null;
    public static int xe;
    @Override
    public void start(Stage stage) throws Exception {
        try 
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        connection = DriverManager.getConnection("jdbc:oracle:thin:@localhost:32769:ORCLCDB", "akram1", "akram1");
    } catch (ClassNotFoundException e) 
    {
         Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Could not find the database driver ");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
    } catch (SQLException e) 
    {
        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setHeaderText("Could not find the sql ");
            errorAlert.setContentText(e.getMessage());
            errorAlert.showAndWait();
    }
        try {
            s=stage;
            

//            StackPane pane = FXMLLoader.load(getClass().getResource(()));
//            root.getChildren().setAll(pane);
            Parent pane = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/splash/splash.fxml"));
        
        Scene scene = new Scene(pane);
        
        stage.setScene(scene);
        stage.setTitle("Car Maintenance Center");
        Image imm = new Image("images/logo.jpg");
        stage.getIcons().add(imm);
        stage.show();

            FadeTransition fadeIn = new FadeTransition(Duration.seconds(1), pane);
            fadeIn.setFromValue(0);
            fadeIn.setToValue(1);
            fadeIn.setCycleCount(1);

            FadeTransition fadeOut = new FadeTransition(Duration.seconds(0.2), pane);
            fadeOut.setFromValue(0.5);
            fadeOut.setToValue(0);
            fadeOut.setCycleCount(1);

            fadeIn.play();

            fadeIn.setOnFinished((e) -> {
                fadeOut.play();
            });
            
            fadeOut.setOnFinished((e) -> {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("/car_maintenancecenter/main/main.fxml"));
                    Scene sceneq = new Scene(root);
                    stage.setScene(sceneq);
//                    stage.setTitle("Car Maintenance Center");
//                    stage.show();
                } catch (IOException ex) {
                    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                    errorAlert.setHeaderText("error in in main page");
                    errorAlert.setContentText(ex.getMessage());
                    errorAlert.showAndWait();
                }
        
        
        
            });

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

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
