/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.KorisnikModel;
import fooddesktop.model.UpdateKorisnikHelper;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author Ivica
 */
public class AzurirajKorisnikController implements Initializable {

    @FXML
    TextField imeAzr;
    @FXML 
    TextField prezimeAzr;
    @FXML
    TextField korImeAzr;
    @FXML 
    TextField lozinkaAzr;
    @FXML
    TextField brTelAzr;
    @FXML
    TextField privilegijaAzr;
    
    
    
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
     imeAzr.setText(UpdateKorisnikHelper.getKorisnik().getIme());
     prezimeAzr.setText(UpdateKorisnikHelper.getKorisnik().getPrezime());
     korImeAzr.setText(UpdateKorisnikHelper.getKorisnik().getKorisnicko_ime());
     lozinkaAzr.setText(UpdateKorisnikHelper.getKorisnik().getLozinka());
     brTelAzr.setText(UpdateKorisnikHelper.getKorisnik().getBroj_telefona());
     privilegijaAzr.setText(UpdateKorisnikHelper.getKorisnik().getPrivilegije());

    }

     public void azurirajKorisnika(ActionEvent e)

     {
        KorisnikModel korisnik = UpdateKorisnikHelper.getKorisnik();
        
         
        korisnik.setIme(this.imeAzr.getText());
        korisnik.setPrezime(this.prezimeAzr.getText());
        korisnik.setKorisnicko_ime (this.korImeAzr.getText());
       korisnik.setLozinka (this.lozinkaAzr.getText());
       korisnik.setBroj_telefona (this.brTelAzr.getText());
       korisnik.setPrivilegije(this.privilegijaAzr.getText());
        korisnik.azuriraj();
          {
        try {
            
            Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
            stage.setMaximized(true);

            stage.setTitle("Prikaz svih korisnika u bazi podataka");
            stage.setScene(new Scene(root));
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(AzurirajKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
          
          
     }
        
    public void otvoriKorisnike(ActionEvent e)
    {
      
         try {
             Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
           

            stage.setTitle("Prikaz svih korisnika u bazi podataka");
            stage.setScene(new Scene(root));
           stage.setMaximized(true);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (Exception ex) {
             Logger.getLogger(AzurirajKorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
