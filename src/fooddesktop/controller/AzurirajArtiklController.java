/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.ArtiklModel;
import fooddesktop.model.Kategorija;
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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 *
 * @author Ivica
 */
public class AzurirajArtiklController implements Initializable  {
    /**
     * Initializes the controller class.
     */
    @FXML
    TextField sifraartiklaAzr;
    @FXML
    TextField nazivartiklaAzr;
    @FXML
    TextField cijenaArtiklaAzr;
    @FXML
    TextField kolicinaArtiklaAzr;
   @FXML
    ChoiceBox<Kategorija> kategorijaChoiceBoxAzr;
  
    /*@FXML
    TextField vrstaArtiklaAzr;*/
    
    @FXML
    Button btnOdustani;
    
    @FXML
    Button btnDodaj;
    
      
    @Override
    public void initialize(URL url, ResourceBundle rb) {
     ObservableList<Kategorija> kategorije = Kategorija.dohvatiSve();

        sifraartiklaAzr.setText(updateArtiklSession.getArtikl().getSifraArtikla());
        nazivartiklaAzr.setText(updateArtiklSession.getArtikl().getNazivArtikla());
        cijenaArtiklaAzr.setText(String.valueOf(updateArtiklSession.getArtikl().getCijena()));
         kolicinaArtiklaAzr.setText(updateArtiklSession.getArtikl().getKolicina().toString());
         kategorijaChoiceBoxAzr.setItems(kategorije);
    

      
        
       
    }    
    
    
    public void azurirajArtikl(ActionEvent e)
    {
        ArtiklModel artikl = updateArtiklSession.getArtikl();
        
        artikl.setSifraArtikla(this.sifraartiklaAzr.getText());
        artikl.setNazivArtikla(this.nazivartiklaAzr.getText());
        artikl.setCijena(Double.parseDouble(this.cijenaArtiklaAzr.getText()));
        artikl.setKolicina(Integer.parseInt(this.kolicinaArtiklaAzr.getText()));
        artikl.setIdKategorije(kategorijaChoiceBoxAzr.getSelectionModel().getSelectedItem().getId());
       
      
    artikl.azuriraj();
 
     {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Artikli.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prikaz svih artikala u bazi podataka");
            stage.setScene(new Scene(root));
            stage.setResizable(true);
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AzurirajArtiklController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    }
    
    public void otvoriArtikle(ActionEvent e)
    {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Artikli.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Prikaz svih artikala u bazi podataka");
             stage.setScene(new Scene(root));
             stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AzurirajArtiklController.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
