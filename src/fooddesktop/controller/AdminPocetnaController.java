/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

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
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Bloom;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivica
 */
public class AdminPocetnaController implements Initializable {
    @FXML
    private Button stolovi_bt;
    @FXML
    private Button konobari_bt;
    @FXML
    private Button artikli_bt;
    @FXML
    private Button pica_bt;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    /*   Bloom bloom = new Bloom();
        
        artikli_bt.setOnMouseEntered(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
               bloom.setThreshold(0.37);
               artikli_bt.setEffect(bloom);
            }
        
        });   */
    }    
    
    public void otvoriStolove (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Stolovi.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled stolova");
            stage.setScene(new Scene(root, 1350, 700));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AdminPocetnaController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERRORX" + ex.getMessage());
        }  
    }
    
      public void otvoriKorisnike (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled korisnika");
            stage.setScene(new Scene(root, 1350, 700));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AdminPocetnaController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
       public void otvoriArtikle (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Artikli.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled artikala");
            stage.setScene(new Scene(root, 1350, 700));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AdminPocetnaController.class.getName()).log(Level.SEVERE, null, ex);
            ex.printStackTrace();
        }  
    }
       
        public void otvoriLogin (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Login.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Logiraj se");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AdminPocetnaController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
        
       
}
