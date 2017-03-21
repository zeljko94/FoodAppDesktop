/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fooddesktop.controller;

import fooddesktop.model.KorisnikModel;

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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Ivica
 */
public class DodajKorisnikaController implements Initializable {
    
    @FXML
     TextField imeTxt;
    @FXML
    TextField prezimeTxt;
    @FXML
     TextField brojTelefonaTxt;
    @FXML
     TextField privilegijaTxt;
    @FXML
    TextField korisnickoImeTxt;
    @FXML
    TextField lozinkaTxt;
    @FXML
    Label errorDodajKorisnikaLbl;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb ) {
        // TODO
    }    
    
        public void dodajKorisnika (ActionEvent e)throws IOException  {
                String ime = imeTxt.getText();
                String prezime = prezimeTxt.getText();
                String brojTelefona = brojTelefonaTxt.getText();
                String privilegija = privilegijaTxt.getText();
                String kime = korisnickoImeTxt.getText();
                String lozinka = lozinkaTxt.getText();
                
       KorisnikModel korisnik = new KorisnikModel(0,this.imeTxt.getText(), this.prezimeTxt.getText(),this.brojTelefonaTxt.getText(),this.privilegijaTxt.getText(),this.korisnickoImeTxt.getText(),this.lozinkaTxt.getText());          
       
       if (ime.equals("") || prezime.equals("")|| brojTelefona.equals("")|| privilegija.equals("")|| kime.equals("")
                 || lozinka.equals("")) {
            errorDodajKorisnikaLbl.setTextFill(Color.RED);
            errorDodajKorisnikaLbl.setText("Morate unijeti sve vrijednosti!");
         }
       
       else if(KorisnikModel.dohvatiKimeLozinku(korisnik.getKorisnicko_ime(),korisnik.getLozinka()) != null)
       {
            errorDodajKorisnikaLbl.setTextFill(Color.RED);
            errorDodajKorisnikaLbl.setText("Promjeni korisnicko ime i lozinku!");
       }
       
       
       
         else{
             korisnik.spasi();
              Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Korisnici");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
         }
          /*      try{
             KorisnikModel korisnik = new KorisnikModel(0,this.imeTxt.getText(), this.prezimeTxt.getText(),this.brojTelefonaTxt.getText(),this.privilegijaTxt.getText(),this.korisnickoImeTxt.getText(),this.lozinkaTxt.getText());
                                                   
        
            
              if(KorisnikModel.dohvatiKimeLozinku(korisnik.getKorisnicko_ime(),korisnik.getLozinka()) == null)
                 
            {
                korisnik.spasi();
                
                     Parent root;
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Korisnici");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
            }
           
                else{
                        
                         errorDodajKorisnikaLbl.setTextFill(Paint.valueOf("RED"));
                errorDodajKorisnikaLbl.setText("Zauzeto korisnicko ime i lozinka");
                        
              }
            
} catch (IOException ex) {
            Logger.getLogger(DodajStolController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        */
        }
       
      
      
  public void otvoriKorisnike (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/Korisnik.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Korisnici");
            stage.setScene(new Scene(root));
            stage.setMaximized(true);

            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(DodajKorisnikaController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }
}
