/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop;


import com.sun.javaws.Main;
import java.awt.TrayIcon;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;


public class FoodDesktop extends Application {
    @Override
    
     
    public void start(Stage primaryStage) throws IOException {
       
        
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        
        Scene scene = new Scene(root);
      

        primaryStage.setTitle("Dobrodosli!");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setResizable(true);
        primaryStage.show();
       
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}