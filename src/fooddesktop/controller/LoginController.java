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
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import fooddesktop.model.KorisnikModel;
import fooddesktop.model.Sesija;
import javafx.application.Platform;
import javafx.scene.effect.Reflection;
import javafx.scene.text.TextAlignment;


public class LoginController implements Initializable {
    @FXML
    Label statusLbl;
    
    @FXML
    TextField kimeTxt;
    
    @FXML
    PasswordField lozinkaTxt;
    
   
    public static Stage narudzbeStage;
    
    public void prijaviSe (ActionEvent e) throws IOException {
        String kime = kimeTxt.getText();
        String lozinka = lozinkaTxt.getText();
        
        if (kime.equals("") || lozinka.equals("")) {
            statusLbl.setTextFill(Color.RED);
            statusLbl.setTextAlignment(TextAlignment.CENTER);
            statusLbl.setText("Morate unijeti sve vrijednosti!");
        } else {
            KorisnikModel korisnik = KorisnikModel.logirajkorisnika(kime, lozinka);
            if (korisnik != null) {
                Sesija.setKorisnik(korisnik);
                try {
                    statusLbl.setTextFill(Color.GREEN);
                    statusLbl.setText("Uspješno ste se prijavili");
                     statusLbl.setTextAlignment(TextAlignment.CENTER);
                    Parent root;
                    if(korisnik.getPrivilegije().equals("admin"))
                    {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/AdminPocetna.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Administracija");
                        stage.setScene(new Scene(root));
                        stage.setResizable(true);
                        stage.setMaximized(true);
                        stage.show();
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                        kimeTxt.setText("");
                        lozinkaTxt.setText("");
                        statusLbl.setText("");
                    }
                    else if(korisnik.getPrivilegije().equals("sanker"))
                        
                    {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/PregledNarucenog.fxml"));
                        narudzbeStage = new Stage();
                        narudzbeStage.setTitle("Pregled narudzbi");
                        Scene scene = new Scene(root);
                        narudzbeStage.setScene(scene);
                        narudzbeStage.setResizable(true);
                        narudzbeStage.setMaximized(true);
                        narudzbeStage.show();
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                        kimeTxt.setText("");
                        lozinkaTxt.setText("");
                        statusLbl.setText("");
                        narudzbeStage.toBack();
                        narudzbeStage.getScene().getWindow().hide();
                        Platform.setImplicitExit(false);
                    }
                    
                    else 
                    {
                        root = FXMLLoader.load(getClass().getClassLoader().getResource("fooddesktop/view/SefPocetna.fxml"));
                        Stage stage = new Stage();
                        stage.setTitle("Sef");
                         stage.setScene(new Scene(root));
                        stage.setResizable(true);
                        stage.setMaximized(true);
                        stage.show();
                        ((Node)(e.getSource())).getScene().getWindow().hide();
                        kimeTxt.setText("");
                        lozinkaTxt.setText("");
                        statusLbl.setText("");
                    }
                        
                    
                                        
                    
                  
                } catch (IOException ex) {
                    Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                statusLbl.setTextFill(Color.RED);
                statusLbl.setText("Korisnički podaci nisu ispravni!");
            }
        }
    }
    /*
     public void otvoriDodajKorisnika (ActionEvent e) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("rentacar/view/DodajKorisnikaPocetna.fxml"));
            Stage stage = new Stage();
            stage.setTitle("Registrirajte se");
             stage.setScene(new Scene(root));
            stage.setResizable(false);
            stage.show();
            ((Node)(e.getSource())).getScene().getWindow().hide();
        } catch (IOException ex) {
            Logger.getLogger(KorisnikController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    } 
    */
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        }    
    
}