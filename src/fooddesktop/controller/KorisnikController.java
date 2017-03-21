/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.KorisnikModel;
import java.io.IOException;

import fooddesktop.model.StoloviModel;
import fooddesktop.model.UpdateKorisnikHelper;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Daniel
 */
public class KorisnikController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    TableColumn<KorisnikModel, String> imeTblCol;
    @FXML
    TableColumn<KorisnikModel, String> prezimeTblCol;
    @FXML
    TableColumn<KorisnikModel, String> korisnickoImeTblCol;
    @FXML
    TableColumn<KorisnikModel, String> lozinkaTblCol;
    @FXML
    TableColumn<KorisnikModel, String> brojTelefonaTblCol;
    @FXML
    TableColumn<KorisnikModel, String> privilegijaTblCol;
     @FXML
    TableView korisniciTbl;
    

    /**
     * Initializes the controller class.
     */
     @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<KorisnikModel> data = KorisnikModel.listaKorisnika();
        
     imeTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Ime"));
     prezimeTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Prezime"));
     brojTelefonaTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Broj_telefona"));
     privilegijaTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Privilegije"));
     korisnickoImeTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Korisnicko_ime"));
     lozinkaTblCol.setCellValueFactory(new PropertyValueFactory<KorisnikModel, String>("Lozinka"));

    korisniciTbl.setItems(data);
    }
    
      public void otvoriDodajKorisnika (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/DodajKorisnika.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Dodaj korisnika");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
      
       public void otvoriAdmin (ActionEvent e) {
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
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
      
        public void brisiKorisnika () {
        KorisnikModel korisnik = (KorisnikModel) this.korisniciTbl.getSelectionModel().getSelectedItem();
        korisnik.brisi();
        this.initialize(null, null);
    }
        
        public void otvoriUrediKorisnika (ActionEvent e)
         {
              Parent root;
        try {
            KorisnikModel odabrani = (KorisnikModel) this.korisniciTbl.getSelectionModel().getSelectedItem();
            if(odabrani == null) return;
            UpdateKorisnikHelper.setKorisnik(odabrani);
            
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AzurirajKorisnik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Azuriranje korisnika");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        } 
        }
    }   

   
    

