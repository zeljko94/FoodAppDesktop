/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.ArtiklModel;
import fooddesktop.model.Kategorija;
import fooddesktop.model.KorisnikModel;
import fooddesktop.model.Vrsta;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivica
 */
public class DodajArtiklController implements Initializable {
    @FXML
    private TextField sifraArtiklaTxt;
    @FXML
    private TextField nazivArtiklaTxt;
    @FXML
    private TextField cijenaArtiklaTxt;
    @FXML
    private TextField kolicinaArtiklaTxt;
    @FXML
    ChoiceBox<Kategorija> kategorijaChoiceBox;
    /*@FXML
    ChoiceBox<Vrsta> vrstaChoiceBox;*/
    @FXML
    private Label  errorDodajArtiklLbl; 
    @FXML
    private Button dodajArtiklBtn;
     @FXML
    private Button otvoriArtikleBtn;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ObservableList<Kategorija> kategorije = Kategorija.dohvatiSve();
        //ObservableList<Vrsta> vrste = Vrsta.dohvatiSve();
        
        kategorijaChoiceBox.setItems(kategorije);
        //vrstaChoiceBox.setItems(vrste);
    }    

       public void dodajArtikl (ActionEvent e) throws IOException {
           
           
             if(sifraArtiklaTxt!=null || nazivArtiklaTxt!=null ||cijenaArtiklaTxt!=null||kolicinaArtiklaTxt!=null
                 ||kategorijaChoiceBox!=null) {
       
            errorDodajArtiklLbl.setTextFill(Paint.valueOf("RED"));
            errorDodajArtiklLbl.setText("Niste unijeli sve vrijednosti!");
        }
            
            
                
             ArtiklModel artikl = new ArtiklModel (0,this.sifraArtiklaTxt.getText(), this.nazivArtiklaTxt.getText(),Double.parseDouble(this.cijenaArtiklaTxt.getText()),
                                                    Integer.parseInt(this.kolicinaArtiklaTxt.getText()),
                                                    kategorijaChoiceBox.getSelectionModel().getSelectedItem().getId());
                                                   
                                  
            
             ArtiklModel izBaze = ArtiklModel.dohvatiPrekoSifraArtikla(artikl.getSifraArtikla());
            
              if(ArtiklModel.dohvatiPrekoSifraArtikla(artikl.getSifraArtikla()) == null)
                 
            {
                artikl.spasi();
                
                     Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Artikli.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Artikli");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
            }
                else{
                        
                         errorDodajArtiklLbl.setTextFill(Paint.valueOf("RED"));
                errorDodajArtiklLbl.setText("Vec postoji artikl sa tom sifrom!");
                        
              }
              
            
         
      
            
    }
       
    
    
         public void otvoriArtikle (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Artikli.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Artikli");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(DodajArtiklController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
}
