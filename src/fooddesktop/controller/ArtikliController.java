/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.ArtiklModel;
import fooddesktop.model.updateArtiklSession;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivica
 */
public class ArtikliController implements Initializable {
  /**
     * Initializes the controller class.
     */
    @FXML
    TableColumn<ArtiklModel, String> sifraTblCol;
    @FXML
    TableColumn<ArtiklModel, String> nazivTblCol;
    @FXML
    TableColumn<ArtiklModel, Float> cijenaTblCol;
    @FXML
    TableColumn<ArtiklModel, Integer> kolicinaTblCol;
    @FXML
    TableColumn<ArtiklModel, String> kategorijaTblCol;
    /*@FXML
    TableColumn<ArtiklModel, String> vrstaTblCol;
     */@FXML
    TableView artikliTbl;
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<ArtiklModel> data = ArtiklModel.listaArtikala();
        
     sifraTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, String>("SifraArtikla"));
     nazivTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, String>("NazivArtikla"));
     cijenaTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, Float>("Cijena"));
     kolicinaTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, Integer>("Kolicina"));
     kategorijaTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, String>("KategorijaToString"));
     //vrstaTblCol.setCellValueFactory(new PropertyValueFactory<ArtiklModel, String>("VrstaToString"));

    artikliTbl.setItems(data);
    }
  
    public void otvoriDodajArtikle (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/DodajArtikl.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Novi artikli");
            stage.setScene(new Scene(root, 1350, 700));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
     public void brisiArtikl () {
        ArtiklModel artikl = (ArtiklModel) this.artikliTbl.getSelectionModel().getSelectedItem();
        artikl.brisi();
        this.initialize(null, null);
    }
    
       public void otvoriAdmina (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AdminPocetna.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Administracija");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
       
       public void otvoriAzurirajArtikle(ActionEvent e)
       {
        Parent root;
        try {
            ArtiklModel odabrani = (ArtiklModel) this.artikliTbl.getSelectionModel().getSelectedItem();
            if(odabrani == null) return;
            updateArtiklSession.setArtikl(odabrani);
            
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AzurirajArtikl.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Azuriranje artikla");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        } 
       }
}
