/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.ArtiklModel;
import fooddesktop.model.OdabranaNarudzbaHelper;
import fooddesktop.model.Racun;
import fooddesktop.model.RacunStavka;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javax.swing.JOptionPane;


public class PregledNarudzbeController implements Initializable {
    
    @FXML
    TableView<RacunStavka> stavkeTbl;
    
    @FXML
    TableColumn<RacunStavka, String> nazivArtiklaTblCol;
    
    @FXML
    TableColumn<RacunStavka, Integer> kolicinaTblCol;
    
    @FXML
    TableColumn<RacunStavka, Double> cijenaTblCol;
    
    @FXML
    Label lblTotal;
    
    @FXML
    Button btnPotvrdi;
    
    @FXML
    Button btnOtkazi;
    
    
    static double TOTAL = 0f;
    ObservableList<RacunStavka> stavke = FXCollections.observableArrayList();
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        TOTAL = 0f;
        stavke = RacunStavka.dohvatiStavkeSaRacuna(OdabranaNarudzbaHelper.getNarudzba().getRacun().getId());
        
        nazivArtiklaTblCol.setCellValueFactory(new PropertyValueFactory<RacunStavka, String>("NazivArtikla"));
        kolicinaTblCol.setCellValueFactory(new PropertyValueFactory<RacunStavka, Integer>("Kolicina"));
        cijenaTblCol.setCellValueFactory(new PropertyValueFactory<RacunStavka, Double>("CijenaStavke"));
        
        stavkeTbl.setItems(stavke);
        
        for(RacunStavka rs: stavke)
        {
            TOTAL += rs.getCijenaStavke();
        }
        lblTotal.setText("TOTAL: " + TOTAL + " KM");
    }
    
    public void btnOtkazi_onClick(ActionEvent e)
    {
        Racun racun = OdabranaNarudzbaHelper.getNarudzba().getRacun();
        for(RacunStavka rs: stavke)
        {
            rs.brisi();
        }
        racun.brisi();
        OdabranaNarudzbaHelper.setNarudzba(null);
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/PregledNarucenog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled narudzbi");
           stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    public void btnPotvrdi_onClick(ActionEvent e)
    {
        Racun racun = OdabranaNarudzbaHelper.getNarudzba().getRacun();
        racun.setNotifikacija(true);
        racun.azuriraj();
        OdabranaNarudzbaHelper.setNarudzba(null);
        
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/PregledNarucenog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled narudzbi");
           stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
   public void otvoriPregledNarucenog(ActionEvent e)
   {
       Parent root;
       try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/PregledNarucenog.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Pregled narudzbi");
           stage.setScene(new Scene(root));
            stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
           
           
           
       } catch (Exception ex) {
           Logger.getLogger(ArtikliController.class.getName()).log(Level.SEVERE, null, ex);

       }
       
       
       
   }
}
